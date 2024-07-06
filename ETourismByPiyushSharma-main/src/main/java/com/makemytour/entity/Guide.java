package com.makemytour.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "guide")
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "language")
    private String language;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile", unique = true)
    private String mobile;


    @ManyToOne
    @JsonIgnore
    private TouristPoint touristPoint;

}