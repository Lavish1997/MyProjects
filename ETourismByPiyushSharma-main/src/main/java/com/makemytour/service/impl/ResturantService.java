package com.makemytour.service.impl;

import com.makemytour.entity.Restaurant;
import com.makemytour.entity.TouristPoint;
import com.makemytour.exception.ResourceNotFoundException;
import com.makemytour.payload.ResturantDto;
import com.makemytour.repository.ResturantRepository;
import com.makemytour.repository.TouristPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResturantService {

    @Autowired
    private ResturantRepository resturantRepository;

    @Autowired
    private TouristPointRepository touristPointRepository;

    public ResturantDto addResturant(ResturantDto resturantDto, long id) {
            TouristPoint touristPoint = touristPointRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Invalid Tourist Point")
            );

        Restaurant resturant = new Restaurant();
        resturant.setName(resturantDto.getName());
        resturant.setAddress(resturantDto.getAddress());
        resturant.setMobile(resturantDto.getMobile());
        resturant.setTouristPoint(touristPoint);

        Restaurant savedResturant = resturantRepository.save(resturant);

        ResturantDto dto = new ResturantDto();
        dto.setId(savedResturant.getId());
        dto.setName(savedResturant.getName());
        dto.setAddress(savedResturant.getAddress());
        dto.setMobile(savedResturant.getMobile());
        dto.setTouristPoint(savedResturant.getTouristPoint().getName());
        return dto;

    }
}
