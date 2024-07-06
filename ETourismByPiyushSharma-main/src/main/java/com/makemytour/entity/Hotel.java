package com.makemytour.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String hotelname;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile", nullable = false, unique = true)
    private String mobile;


    @ManyToOne
    @JsonIgnore
    private TouristPoint touristPoint;

}