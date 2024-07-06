package com.swiggy.payload;


import com.swiggy.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    List<Order> orders;
    private double totalPrice;
}
