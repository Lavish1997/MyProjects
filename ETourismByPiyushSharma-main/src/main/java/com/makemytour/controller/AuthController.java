package com.makemytour.controller;

import com.makemytour.entity.UserInfo;
import com.makemytour.service.impl.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class AuthController {

    @Autowired
    private UserInfoService service;
    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }

}
