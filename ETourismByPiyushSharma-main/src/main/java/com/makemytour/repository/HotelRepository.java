package com.makemytour.repository;

import com.makemytour.entity.Cab;
import com.makemytour.entity.Hotel;
import com.makemytour.entity.TouristPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    List<Hotel> findByTouristPoint(TouristPoint tp);
}
