package com.makemytour.repository;

import com.makemytour.entity.TouristPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TouristPointRepository extends JpaRepository<TouristPoint,Long> {
    @Query("SELECT tp FROM TouristPoint tp WHERE tp.name LIKE %:name%")
    TouristPoint findByKey(@Param("name") String name);


}
