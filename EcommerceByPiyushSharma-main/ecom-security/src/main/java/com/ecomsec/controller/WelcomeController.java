package com.ecomsec.controller;

import com.ecomsec.entity.*;
import com.ecomsec.payload.UserDto;
import com.ecomsec.repository.UserRepository;
import com.ecomsec.service.EcomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.Set;

@Controller
public class WelcomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private EcomService ecomService;


    @GetMapping("/check")
    public String getWelcome( Principal principal) {
        String userName = principal.getName();
        System.out.println(userName);
        User user = userRepository.findByUsernameOrEmail(userName, userName).get();
        Set<Role> roles = user.getRoles();
        for (Role r:user.getRoles()) {
            System.out.println(r.getName());
            if (r.getName().equals("ROLE_ADMIN")) {
                return "welcomeAdmin";
            } else
                return "welcome";
        }
        return "welcome";
    }


    @GetMapping("/welcome")
    public String getWelcome() {
        return "welcome";
    }

    @GetMapping("/public/signupform")
    public String signupPage() {
        return "SignUpForm";
    }



    @PostMapping("/public/signUp")
    public String createUser(@RequestBody User user){
        UserDto added = ecomService.addUser(user);
        return "login";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
