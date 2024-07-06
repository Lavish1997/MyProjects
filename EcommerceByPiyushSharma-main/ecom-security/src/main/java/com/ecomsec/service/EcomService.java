package com.ecomsec.service;

import com.ecomsec.entity.Item;
import com.ecomsec.entity.User;
import com.ecomsec.payload.CartDetailDto;
import com.ecomsec.payload.CartDto;
import com.ecomsec.payload.RemoveFromCartDto;
import com.ecomsec.payload.UserDto;

import java.util.List;

public interface EcomService {
    UserDto addUser(User user);
    Item addItem(Item item);
    List<Item> searchItem(String search);
    List<Item> searchByKeyword(String search);

    CartDto addToCart(Long itemNumber, String name);
    CartDetailDto viewMyCart(String userName);
    String applyForSeller(String userName);

    UserDto viewMyProfile(String name);

    RemoveFromCartDto removeFromCart(long itemNumber, String name);
}
