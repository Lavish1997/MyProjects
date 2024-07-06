package com.swiggy.repository;

import com.swiggy.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByUserId(Long userId);

    Optional<List<Order>> findByUserName(String userName);
}