package com.makemytour.controller;

import com.makemytour.payload.CabDto;
import com.makemytour.payload.HotelDto;
import com.makemytour.service.impl.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //http://localhost:8080/api/hotel/addhotel/1
    @PostMapping("/addhotel/{id}")
    public ResponseEntity<HotelDto> addHotel(@RequestBody HotelDto hotelDto, @PathVariable long id) {
        HotelDto hotel = hotelService.addHotel(hotelDto,id);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);

    }
}
