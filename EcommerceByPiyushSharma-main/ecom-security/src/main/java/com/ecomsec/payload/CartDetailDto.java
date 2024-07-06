package com.ecomsec.payload;


import com.ecomsec.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto {
    private String userName;
    private List<Item> items;
    private double total;

}
