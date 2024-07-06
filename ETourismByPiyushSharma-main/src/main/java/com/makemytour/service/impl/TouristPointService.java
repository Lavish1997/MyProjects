package com.makemytour.service.impl;

import com.makemytour.entity.*;
import com.makemytour.exception.ResourceNotFoundException;
import com.makemytour.payload.JustTouristPointDto;
import com.makemytour.payload.TouristPointDto;
import com.makemytour.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristPointService {

    @Autowired
    private TouristPointRepository touristPointRepository;
    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ResturantRepository resturantRepository;



    public TouristPointDto createCompleteTouristPoint(TouristPointDto touristPointDto) {
        TouristPoint touristPoint = new TouristPoint();
//        Cab cab=new Cab();
//        Guide guide=new Guide();
//        Hotel hotel=new Hotel();
//        Restaurant resturant=new Restaurant();

        List<Cab> cabs = touristPointDto.getCabs();
        List<Guide> guides = touristPointDto.getGuides();
        List<Hotel> hotels = touristPointDto.getHotels();
        List<Restaurant> restaurants = touristPointDto.getRestaurants();


        touristPoint.setName(touristPointDto.getName());
        touristPoint.setAddress(touristPointDto.getAddress());
        TouristPoint savedtp = touristPointRepository.save(touristPoint);

        for(Cab c:cabs){
            c.setTouristPoint(savedtp);
            cabRepository.save(c);
        }

        for (Guide g:guides){
            g.setTouristPoint(savedtp);
            guideRepository.save(g);
        }

        for (Hotel h:hotels){
            h.setTouristPoint(savedtp);
            hotelRepository.save(h);
        }

        for (Restaurant r:restaurants){
            r.setTouristPoint(savedtp);
            resturantRepository.save(r);
        }

        
        touristPoint.setCabs(cabs);
        touristPoint.setHotels(hotels);
        touristPoint.setGuides(guides);
        touristPoint.setRestaurants(restaurants);

        TouristPoint savedtourist = touristPointRepository.save(touristPoint);


        TouristPointDto dto = new TouristPointDto();
        dto.setName(savedtourist.getName());
        dto.setAddress(savedtourist.getAddress());
        return dto;
    }



    public TouristPointDto getTouristPoint(Long id) {
        TouristPoint touristPoint = touristPointRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("Record Not Found with id:"+ id)
        );

        if (touristPoint != null) {
            TouristPointDto touristPointDto = new TouristPointDto();
            BeanUtils.copyProperties(touristPoint, touristPointDto);
            return touristPointDto;
        } else {
            return null;
        }
    }


    public TouristPointDto updateTouristPoint(Long id, TouristPointDto updatedTouristPointDto) {
        TouristPoint existingTouristPoint = touristPointRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Record Not Found with id:"+ id)
        );

        return null;

    }


    public JustTouristPointDto createTouristPoint(JustTouristPointDto touristPointDto) {
        TouristPoint touristPoint = new TouristPoint();
        touristPoint.setName(touristPointDto.getName());
        touristPoint.setAddress(touristPointDto.getAddress());
        TouristPoint savedtp = touristPointRepository.save(touristPoint);

        JustTouristPointDto dto=new JustTouristPointDto();
        dto.setId(savedtp.getId());
        dto.setName(savedtp.getName());
        dto.setAddress(savedtp.getAddress());
        return dto;


    }

    public TouristPoint searchTouristPoint(String key) {
        TouristPoint touristpoint = touristPointRepository.findByKey(key);

        touristpoint.setCabs(cabRepository.findByTouristPoint(touristpoint));
        touristpoint.setGuides(guideRepository.findByTouristPoint(touristpoint));
        touristpoint.setHotels(hotelRepository.findByTouristPoint(touristpoint));
        touristpoint.setRestaurants(resturantRepository.findByTouristPoint(touristpoint));


        return touristpoint;
    }
}
