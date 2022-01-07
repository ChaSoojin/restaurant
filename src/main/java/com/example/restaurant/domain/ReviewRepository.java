package com.example.restaurant.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Modifying
    @Query(value = "delete from review colums where user_id = ?1", nativeQuery = true)
    public void deleteByAllId(String user_id);
}
