package com.makemytour.service.impl;

import com.makemytour.entity.Cab;
import com.makemytour.entity.Hotel;
import com.makemytour.entity.TouristPoint;
import com.makemytour.exception.ResourceNotFoundException;
import com.makemytour.payload.CabDto;
import com.makemytour.payload.HotelDto;
import com.makemytour.repository.HotelRepository;
import com.makemytour.repository.TouristPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    private TouristPointRepository touristPointRepository;

    @Autowired
    private HotelRepository hotelRepository;


    public HotelDto addHotel(HotelDto hotelDto, long id) {
        TouristPoint touristPoint = touristPointRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Invalid Tourist Point")
        );

        Hotel hotel = new Hotel();
        hotel.setHotelname(hotelDto.getHotelname());
        hotel.setAddress(hotelDto.getAddress());
        hotel.setMobile(hotelDto.getMobile());
        hotel.setTouristPoint(touristPoint);

        Hotel savedhotel = hotelRepository.save(hotel);
        HotelDto dto = new HotelDto();

        dto.setHotelname(savedhotel.getHotelname());
        dto.setAddress(savedhotel.getAddress());
        dto.setMobile(savedhotel.getMobile());
        dto.setTouristPoint(savedhotel.getTouristPoint().getName());
        dto.setId(savedhotel.getId());
        return dto;
    }

}
