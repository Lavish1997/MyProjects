package com.ecomsec.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "add_to_cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class AddToCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long cartId;
    private long userId;
    private long itemId;
    private String itemName;
    private double total;

}
