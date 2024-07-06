package com.makemytour.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cab")
public class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cabnumber",  unique = true)
    private String cabnumber;

    @Column(name = "drivername")
    private String drivername;

    @Column(name = "mobile", unique = true)
    private String mobile;

    @ManyToOne
    @JsonIgnore
    private TouristPoint touristPoint;

}