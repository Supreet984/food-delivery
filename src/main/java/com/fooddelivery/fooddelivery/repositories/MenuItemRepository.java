package com.fooddelivery.fooddelivery.repositories;

import com.fooddelivery.fooddelivery.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("SELECT m FROM MenuItem m WHERE m.restaurantId = ?1")
    List<MenuItem> findAllByRestaurantId(Long restaurantId);
}
