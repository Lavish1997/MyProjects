package com.makemytour.payload;

import com.makemytour.entity.TouristPoint;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideDto {

    private String name;
    private String language;
    private String address;
    private String mobile;

    private String touristPoint;
}
