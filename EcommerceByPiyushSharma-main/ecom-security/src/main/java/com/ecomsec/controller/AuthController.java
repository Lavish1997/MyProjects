package com.ecomsec.controller;


import com.ecomsec.entity.Item;
import com.ecomsec.entity.User;
import com.ecomsec.payload.CartDetailDto;
import com.ecomsec.payload.CartDto;
import com.ecomsec.payload.RemoveFromCartDto;
import com.ecomsec.payload.UserDto;
import com.ecomsec.service.EcomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private  EcomService ecomService;


    @GetMapping("search/{search}")
    public List<Item> searchItem(@PathVariable String search){
        List<Item> items = ecomService.searchItem(search);
        return items;
    }


    @PostMapping("/public/signUp")
    public UserDto createUser(@RequestBody User user){
        UserDto added = ecomService.addUser(user);
        return added;
    }


    @GetMapping("bykey/{search}")
    public List<Item> searchByKey(@PathVariable String search){
        List<Item> items = ecomService.searchByKeyword(search);
        return items;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/viewmycart")
    public CartDetailDto viewMYCart(Principal principal){
        String name = principal.getName();
        CartDetailDto cartDetailDto = ecomService.viewMyCart(name);
        return cartDetailDto;
    }

    @PreAuthorize("hasAnyRole('USER','SELLER','ADMIN')")
    @GetMapping("/profile")
    public UserDto viewMyProfile(Principal principal){
        String name = principal.getName();
        UserDto userDto = ecomService.viewMyProfile(name);
        return userDto ;
    }


    @PreAuthorize("hasRole('USER')")
    @PutMapping("/applyforseller")
    public ResponseEntity<?> applyForSeller(Principal principal){
        String name = principal.getName();
        String msg = ecomService.applyForSeller(name);
        return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    @PostMapping("/addItem")
    public Item createItem(@RequestBody Item item){
        Item added = ecomService.addItem(item);
        return added;
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/removeFromCart/{itemNumber}")
    public ResponseEntity<?> removeFromCart(@PathVariable long itemNumber, Principal principal){
        String name = principal.getName();
        RemoveFromCartDto savedcartDto = ecomService.removeFromCart(itemNumber, name);
        if(savedcartDto==null)
            return new ResponseEntity<>("Your cart is empty",HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(savedcartDto,HttpStatus.OK);

    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/addToCart")
    public CartDto addToCart(@RequestParam long itemNumber, Principal principal){
        String name = principal.getName();
        CartDto cartDto = ecomService.addToCart(itemNumber, name);
        return cartDto;
    }
}
