package com.ecomsec.repository;

import com.ecomsec.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByItemNameOrCategoryOrSearchKey(String itemName,String category,String searchKey);

    @Query("SELECT i FROM Item i WHERE i.itemName LIKE %:keyword% OR i.category LIKE %:keyword% OR i.searchKey LIKE %:keyword%")
    List<Item> findByKeyword(@Param("keyword") String keyword);
}
