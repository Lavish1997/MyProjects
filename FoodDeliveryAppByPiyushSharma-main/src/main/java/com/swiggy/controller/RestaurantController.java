package com.swiggy.controller;


import com.swiggy.entity.Menu;
import com.swiggy.entity.Order;
import com.swiggy.entity.Restaurant;
import com.swiggy.entity.UserInfo;
import com.swiggy.payload.MenuDto;
import com.swiggy.payload.OrderDto;
import com.swiggy.payload.RestaurantDto;
import com.swiggy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    // http://localhost:8080/api/create

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> CreateRestaurant(@RequestBody RestaurantDto restaurantDto) {
        RestaurantDto restaurant1 = restaurantService.saveRestaurant(restaurantDto);
        return new ResponseEntity<>(restaurant1, HttpStatus.CREATED);
    }

    @PostMapping("/Add")
    public ResponseEntity<?> AddMenu(@RequestBody MenuDto menuDto, @RequestParam long id) {
        Menu menus = restaurantService.saveMenu(menuDto, id);
        return new ResponseEntity<>(menus, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/restaurants/1/menu/2
    @DeleteMapping("/restaurants/{restaurantId}/menu/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable long restaurantId, @PathVariable long id) {
        restaurantService.deleteFood(restaurantId, id);
        return new ResponseEntity<>("Item is deleted!!!", HttpStatus.OK);
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return new ResponseEntity<>("Restaurant is deleted!!!", HttpStatus.OK);
    }

    @GetMapping("/makemyorder")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> makemyorder(@RequestParam long itemId, @RequestParam int qty, Principal principal) {
        String username = principal.getName();
        Order order = restaurantService.makeMyOrder(itemId, qty, username);
        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewmyorder")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> viewmyorder(Principal principal) {
        OrderDto dto = restaurantService.viewMyOrder(principal);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);

    }
}