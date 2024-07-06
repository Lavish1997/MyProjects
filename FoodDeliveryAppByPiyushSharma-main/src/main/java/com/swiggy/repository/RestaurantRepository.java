package com.swiggy.repository;


import com.swiggy.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
//    Optional<List<Restaurant>> findByMenusNameContainingIgnoreCase(String foodName);
//
//    Optional<List<Restaurant>> findByName(String name);

    @Query("SELECT r FROM Restaurant r WHERE UPPER(r.name) LIKE %:name%")
    Optional<List<Restaurant>> findByRestaurantName(@Param("name") String name);

    @Query("SELECT r FROM Restaurant r JOIN r.menus m WHERE UPPER(m.name) LIKE %:foodName%")
    Optional<List<Restaurant>> findRestaurantByMenuName(@Param("foodName") String foodName);


}