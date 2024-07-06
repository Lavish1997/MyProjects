package com.ecomsec.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private String userName;
    private long itemNo;
    private String itemName;
    private double itemPrice;
    private double total;


}
