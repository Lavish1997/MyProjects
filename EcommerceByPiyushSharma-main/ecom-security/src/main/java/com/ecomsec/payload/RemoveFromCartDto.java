package com.ecomsec.payload;

import com.ecomsec.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor


public class RemoveFromCartDto {
        private String message;
        private String userName;
        private List<Item> items;
        private double total;


}
