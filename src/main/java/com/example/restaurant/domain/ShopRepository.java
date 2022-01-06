package com.example.restaurant.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    @Modifying
    @Query(value = "delete from shop colums where restaurant_id = ?1", nativeQuery = true)
    public void deleteByStringId(String id);
}