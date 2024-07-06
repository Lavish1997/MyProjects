package com.makemytour.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "tourist_point")
public class TouristPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany
    private List<Hotel> hotels;

    @OneToMany
    private List<Cab> cabs;

    @OneToMany
    private List<Guide> guides;

    @OneToMany
    private List<Restaurant> restaurants;


}