package com.makemytour.service.impl;

import com.makemytour.entity.UserInfo;
import com.makemytour.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;


    public String addUser(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
        return "User added successfully";
    }
}
