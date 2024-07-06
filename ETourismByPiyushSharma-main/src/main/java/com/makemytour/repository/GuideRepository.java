package com.makemytour.repository;

import com.makemytour.entity.Cab;
import com.makemytour.entity.Guide;
import com.makemytour.entity.TouristPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuideRepository extends JpaRepository<Guide,Long> {
    List<Guide> findByTouristPoint(TouristPoint tp);
}
