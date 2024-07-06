package com.makemytour.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JustTouristPointDto {
    private Long id;
    private String name;
    private String address;
}
