package com.fooddelivery.fooddelivery.repositories;


import com.fooddelivery.fooddelivery.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.restaurantId = ?1 AND r.customerId = ?2")
    Review findByRestaurantIdAndUserId(Long id, Long userId);

    @Query("SELECT r FROM Review r WHERE r.restaurantId = ?1")
    List<Review> findAllByRestaurantId(Long id);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.restaurantId = ?1")
    Long countByRestaurantId(Long id);
}