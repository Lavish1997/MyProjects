package com.swiggy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name = "allOrders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private Long userId;
    private String userName;
    private Long restaurantId;
    private String restaurantName;
    private Long menuId;
    private String menuName;
    private int quantity;
    private double totalPrice;
}
