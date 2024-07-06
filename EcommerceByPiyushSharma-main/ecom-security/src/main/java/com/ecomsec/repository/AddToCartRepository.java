package com.ecomsec.repository;

import com.ecomsec.entity.AddToCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddToCartRepository extends JpaRepository<AddToCart,Long> {
    Optional<List<AddToCart>> findByUserId(long id);
    Optional<List<AddToCart>> findByItemId(long id);
}
