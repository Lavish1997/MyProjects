package com.makemytour.service.impl;

import com.makemytour.entity.Cab;
import com.makemytour.entity.TouristPoint;
import com.makemytour.exception.ResourceNotFoundException;
import com.makemytour.payload.CabDto;
import com.makemytour.payload.TouristPointDto;
import com.makemytour.repository.CabRepository;
import com.makemytour.repository.TouristPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabService {

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private TouristPointRepository touristPointRepository;

    public CabDto addCab(CabDto cabDto,long id) {
        TouristPoint touristPoint = touristPointRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Invalid Tourist Point")
        );

        Cab cab = new Cab();
        cab.setCabnumber(cabDto.getCabnumber());
        cab.setDrivername(cabDto.getDrivername());
        cab.setMobile(cabDto.getMobile());
        cab.setTouristPoint(touristPoint);

        Cab savedcab = cabRepository.save(cab);
        CabDto dto = new CabDto();
        dto.setCabnumber(savedcab.getCabnumber());
        dto.setDrivername(savedcab.getDrivername());
        dto.setMobile(savedcab.getMobile());
        dto.setTouristPoint(savedcab.getTouristPoint().getName());
        return dto;
    }

}
