package com.ecomsec.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cid;

    @OneToOne
    private User user;

    @OneToMany
    @Column(unique = true)
    private List<Item> items;

    private double total;
}
