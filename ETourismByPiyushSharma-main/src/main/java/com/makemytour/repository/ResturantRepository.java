package com.makemytour.repository;

import com.makemytour.entity.Cab;
import com.makemytour.entity.Restaurant;
import com.makemytour.entity.TouristPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResturantRepository extends JpaRepository<Restaurant,Long> {
    List<Restaurant> findByTouristPoint(TouristPoint tp);
}
