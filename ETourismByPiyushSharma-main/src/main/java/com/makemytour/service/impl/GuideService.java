package com.makemytour.service.impl;

import com.makemytour.entity.Cab;
import com.makemytour.entity.Guide;
import com.makemytour.entity.TouristPoint;
import com.makemytour.exception.ResourceNotFoundException;
import com.makemytour.payload.CabDto;
import com.makemytour.payload.GuideDto;
import com.makemytour.repository.GuideRepository;
import com.makemytour.repository.TouristPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuideService {

    @Autowired
    private GuideRepository guideRepository;
    @Autowired
    private TouristPointRepository touristPointRepository;

    public GuideDto addGuide(GuideDto guideDto, long id) {
        TouristPoint touristPoint = touristPointRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Invalid Tourist Point")
        );

        Guide guide = new Guide();
        guide.setName(guideDto.getName());
        guide.setLanguage(guideDto.getLanguage());
        guide.setAddress(guideDto.getAddress());
        guide.setMobile(guideDto.getMobile());
        guide.setTouristPoint(touristPoint);

        Guide savedguide = guideRepository.save(guide);
        GuideDto dto = new GuideDto();
        dto.setName(savedguide.getName());
        dto.setLanguage(savedguide.getLanguage());
        dto.setAddress(savedguide.getAddress());
        dto.setMobile(savedguide.getMobile());
        dto.setTouristPoint(savedguide.getTouristPoint().getName());
        return dto;
    }

}
