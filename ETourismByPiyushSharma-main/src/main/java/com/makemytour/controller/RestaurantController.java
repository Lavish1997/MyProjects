package com.makemytour.controller;

import com.makemytour.payload.ResturantDto;
import com.makemytour.service.impl.ResturantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private ResturantService resturantService;

    //http://localhost:8080/api/restaurant/addrestaurant/1
    @PostMapping("/addrestaurant/{id}")
    public ResponseEntity<ResturantDto> addResturant(@RequestBody ResturantDto resturantDto, @PathVariable long id) {
        ResturantDto restaurant = resturantService.addResturant(resturantDto,id);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);


    }
}
