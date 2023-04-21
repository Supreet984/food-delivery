package com.fooddelivery.fooddelivery.repositories;

import com.fooddelivery.fooddelivery.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("SELECT m FROM MenuItem m WHERE m.restaurantId = ?1")
    List<MenuItem> findAllByRestaurantId(Long restaurantId);
}
