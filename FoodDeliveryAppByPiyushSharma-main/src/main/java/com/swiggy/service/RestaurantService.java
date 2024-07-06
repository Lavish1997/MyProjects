package com.swiggy.service;

import com.swiggy.entity.Menu;
import com.swiggy.entity.Restaurant;
import com.swiggy.entity.*;
import com.swiggy.payload.MenuDto;
import com.swiggy.payload.OrderDto;
import com.swiggy.payload.RestaurantDto;
import com.swiggy.repository.MenuRepository;
import com.swiggy.repository.OrderRepository;
import com.swiggy.repository.RestaurantRepository;
import com.swiggy.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class RestaurantService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OrderRepository orderRepository;

    //created save method
    public RestaurantDto saveRestaurant(RestaurantDto restaurantDto) {
        // Create and save the Restaurant entity first
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        List<Menu> savedMenus = new ArrayList<>();

        for (MenuDto menuDto : restaurantDto.getMenus()) {
            Menu menu = new Menu();
            menu.setName(menuDto.getName());
            menu.setDescription(menuDto.getDescription());
            menu.setPrice(menuDto.getPrice());
            // Associate the Menu with the saved Restaurant entity
            menu.setRestaurant(savedRestaurant);
            savedMenus.add(menuRepository.save(menu));
        }

        savedRestaurant.setMenus(savedMenus);

        // Update the Restaurant entity with the saved Menus
        savedRestaurant = restaurantRepository.save(savedRestaurant);

        // Convert the saved Restaurant entity to DTO
        RestaurantDto dto = new RestaurantDto();
        dto.setId(savedRestaurant.getId());
        dto.setName(savedRestaurant.getName());
        dto.setAddress(savedRestaurant.getAddress());

        List<MenuDto> menuDtos = new ArrayList<>();
        for (Menu savedMenu : savedRestaurant.getMenus()) {
            MenuDto menuDto = new MenuDto();
            menuDto.setFoodId(savedMenu.getFoodId());
            menuDto.setName(savedMenu.getName());
            menuDto.setDescription(savedMenu.getDescription());
            menuDto.setPrice(savedMenu.getPrice());
            menuDtos.add(menuDto);
        }
        dto.setMenus(menuDtos);

        return dto;
    }

    public List<Restaurant> findRestaurantsByFoodName(String foodName) {
        return restaurantRepository.findRestaurantByMenuName(foodName).orElseThrow(() -> new RuntimeException("Restaurant is not found"));
    }

    public Menu saveMenu(MenuDto menuDto, long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            Menu menu = new Menu();
            menu.setName(menuDto.getName());
            menu.setDescription(menuDto.getDescription());
            menu.setPrice(menuDto.getPrice());
            menu.setRestaurant(restaurant.get());
            return menuRepository.save(menu);
        }
        return null;
    }

    public void deleteFood(long restaurantId, long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            menuRepository.deleteById(id);
        }
    }

    public void deleteRestaurant(long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    public List<Restaurant> findRestaurants(String restaurantName) {
        List<Restaurant> restaurants = restaurantRepository.findByRestaurantName(restaurantName).orElseThrow(() -> new RuntimeException("Restaurant is not found"));
        return restaurants;
    }

    public String signIn(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added to system ";
    }

    public Order makeMyOrder(long iid,int qty, String username) {
        UserInfo user = userInfoRepository.findByName(username).orElseThrow(() -> new RuntimeException("User not found"));
        Menu item = menuRepository.findById(iid).orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = item.getRestaurant();
        Order order = new Order();
        order.setRestaurantId(restaurant.getId());
        order.setRestaurantName(restaurant.getName());
        order.setUserId(user.getId());
        order.setUserName(user.getName());
        order.setQuantity(qty);
        order.setMenuId(item.getFoodId());
        order.setMenuName(item.getName());
        order.setTotalPrice(item.getPrice()*qty);
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    public OrderDto viewMyOrder(Principal principal) {
        String name = principal.getName();
        List<Order> orders = orderRepository.findByUserName(name).orElseThrow(() -> new RuntimeException("User not found"));
        double total=0.0;
        OrderDto dto=new OrderDto();
        List<Order> dtos = new ArrayList<>();
        for (Order order : orders) {
            Order orderdto=new Order();
            orderdto.setRestaurantId(order.getOrderId());
            orderdto.setRestaurantName(order.getRestaurantName());
            orderdto.setOrderId(order.getOrderId());
            orderdto.setUserId(order.getUserId());
            orderdto.setUserName(order.getUserName());
            orderdto.setQuantity(order.getQuantity());
            orderdto.setMenuId(order.getMenuId());
            orderdto.setMenuName(order.getMenuName());
            orderdto.setTotalPrice(order.getTotalPrice());
            total=total+order.getTotalPrice();
            dtos.add(orderdto);
        }
        dto.setOrders(dtos);
        dto.setTotalPrice(total);


        return dto;
    }
}
