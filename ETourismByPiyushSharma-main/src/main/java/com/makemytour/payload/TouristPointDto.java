package com.makemytour.payload;

import com.makemytour.entity.Cab;
import com.makemytour.entity.Guide;
import com.makemytour.entity.Hotel;
import com.makemytour.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TouristPointDto {
    private String name;
    private String address;
    private List<Hotel> hotels;
    private List<Cab> cabs;
    private List<Guide> guides;
    private List<Restaurant> restaurants;

}
