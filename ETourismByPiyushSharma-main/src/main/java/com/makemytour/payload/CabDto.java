package com.makemytour.payload;

import com.makemytour.entity.TouristPoint;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabDto {

    private String cabnumber;
    private String drivername;
    private String mobile;

    private String touristPoint;

}
