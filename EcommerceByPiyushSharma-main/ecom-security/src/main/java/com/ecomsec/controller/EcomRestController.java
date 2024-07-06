package com.ecomsec.controller;

import com.ecomsec.entity.User;
import com.ecomsec.payload.UserDto;
import com.ecomsec.service.EcomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ecom")
public class EcomRestController {
    @Autowired
    private EcomService ecomService;



    @PostMapping("/public/signUp")
    public UserDto createUser(@RequestBody User user){
        UserDto added = ecomService.addUser(user);
        return added;
    }

}
