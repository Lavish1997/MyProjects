package com.ecomsec.service.impl;

import com.ecomsec.entity.*;

import com.ecomsec.payload.CartDetailDto;
import com.ecomsec.payload.CartDto;
import com.ecomsec.payload.RemoveFromCartDto;
import com.ecomsec.payload.UserDto;
import com.ecomsec.repository.*;
import com.ecomsec.service.EcomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EcomServiceImpl implements EcomService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AddToCartRepository addToCartRepository;

  //  private double total,price;

    @Override
    public UserDto addUser(User user) {

        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));


        Role role=new Role();

        role.setName("ROLE_USER");
        Role savedRole = roleRepository.save(role);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        Cart cart=new Cart();
        cart.setUser(savedUser);
        cart.setTotal(0);
        cart.setCid(savedUser.getId());
        Cart savedCart = cartRepository.save(cart);
        savedUser.setCart(savedCart);

        User save = userRepository.save(savedUser);

        UserDto dto=new UserDto();
        dto.setEmail(save.getEmail());
        dto.setName(save.getName());
        dto.setRole("ROLE_USER");
        dto.setUsername(save.getUsername());
        dto.setPassword(save.getPassword());

        return dto;
    }

    @Override
    public Item addItem(Item item) {
        Item save = itemRepository.save(item);
        return save;
    }

    @Override
    public List<Item> searchItem(String search) {
        List<Item> items = itemRepository.findByItemNameOrCategoryOrSearchKey(search,search,search);
        return items;
    }

    @Override
    public List<Item> searchByKeyword(String search) {
        List<Item> items = itemRepository.findByKeyword(search);
        return items;
    }

    @Override
    public CartDto addToCart(Long itemNumber, String name) {
        User user = userRepository.findByUsernameOrEmail(name, name).orElseThrow(() -> new RuntimeException("User is not found"));
        Item item = itemRepository.findById(itemNumber).orElseThrow(() -> new RuntimeException("Item is not found"));
        double total=0;
        Long cartId = Long.valueOf(user.getId());
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Item is not found"));
        //total=item.getPrice();
        if(cart.getTotal()!=0.0){
            total = cart.getTotal();
            double price = item.getPrice();
            total=total+price;
            cart.setTotal(total);
        }
        else{
            total=item.getPrice();
        }
        Cart newcart=new Cart();
        newcart.setCid(user.getId());
        newcart.setItems(List.of(item));
        newcart.setTotal(total);
        newcart.setUser(user);
        Cart save = cartRepository.save(newcart);

        AddToCart atc=new AddToCart();
        atc.setItemId(item.getIid());
        atc.setUserId(user.getId());
        atc.setCartId(cart.getCid());
        atc.setItemName(item.getItemName());
        atc.setTotal(cart.getTotal());
        AddToCart addToCart = addToCartRepository.save(atc);

        CartDto cartDto=new CartDto();
        cartDto.setItemName(item.getItemName());
        cartDto.setItemPrice(item.getPrice());
        cartDto.setTotal(total);
        cartDto.setUserName(user.getName());
        cartDto.setItemNo(item.getIid());
        return cartDto;
    }

    @Override
    public CartDetailDto viewMyCart(String userName) {
        User user = userRepository.findByUsernameOrEmail(userName,userName).orElseThrow(() -> new RuntimeException("User not found"));
        Integer cartId = user.getId();
        Cart cart = cartRepository.findById(Long.valueOf(cartId)).orElseThrow(() -> new RuntimeException("Cart not found"));
        List<AddToCart> cartDetails = addToCartRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Resource not found"));
        List<Item> items =new ArrayList<>();
        for(AddToCart atc:cartDetails){
            Item item = itemRepository.findById(atc.getItemId()).orElseThrow(() -> new RuntimeException("Resource not found"));
            items.add(item);
        }

        CartDetailDto cdd=new CartDetailDto();
        cdd.setUserName(userName);
        cdd.setItems(items);
        cdd.setTotal(cart.getTotal());
        return cdd;
    }

    @Override
    public String applyForSeller(String userName) {
        User user = userRepository.findByUsernameOrEmail(userName, userName).orElseThrow(() -> new RuntimeException("user is not valid"));
        Set<Role> roles = user.getRoles();
        Role role=new Role();
        role.setName("ROLE_SELLER");
        Role savedRole = roleRepository.save(role);
        roles.add(savedRole);

        user.setRoles(roles);
        userRepository.save(user);
        return "You are seller now,you can add items";
    }

    @Override
    public UserDto viewMyProfile(String name) {
        User user = userRepository.findByUsernameOrEmail(name,name).orElseThrow(() -> new RuntimeException("User Not Found"));
        UserDto dto=new UserDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setRole(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()).toString());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }

    @Override
    public RemoveFromCartDto removeFromCart(long itemNumber, String name) {
        //finding user and item
        User user = userRepository.findByUsernameOrEmail(name, name).orElseThrow(() -> new RuntimeException("User is not found"));
        Item item = itemRepository.findById(itemNumber).orElseThrow(() -> new RuntimeException("Item is not found"));

        Long cartId = Long.valueOf(user.getId());
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Item is not found"));
        double total= cart.getTotal();

        List<AddToCart> cartDetails = addToCartRepository.findByItemId(itemNumber).orElseThrow(() -> new RuntimeException("Resource not found"));
        for(AddToCart atc:cartDetails){
            if(cart.getTotal()==0.0){
                return null;
            }
            else{
                total=total-item.getPrice();
            }

            addToCartRepository.deleteById(atc.getId());
        }

        List<AddToCart> cartDetails2 = addToCartRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Resource not found"));
        List<Item> items =new ArrayList<>();
        for(AddToCart atc:cartDetails2){
            Item newitem = itemRepository.findById(atc.getItemId()).orElseThrow(() -> new RuntimeException("Item is not found"));
            items.add(newitem);
        }

        //total=item.getPrice();

        Cart newcart=new Cart();
        newcart.setCid(user.getId());
        newcart.setItems(items);
        newcart.setTotal(total);
        newcart.setUser(user);
        Cart save = cartRepository.save(newcart);

        RemoveFromCartDto rdto=new RemoveFromCartDto();
        rdto.setMessage("Item is removed from cart");
        rdto.setItems(items);
        rdto.setTotal(total);
        rdto.setUserName(name);


        return rdto;
    }

}



