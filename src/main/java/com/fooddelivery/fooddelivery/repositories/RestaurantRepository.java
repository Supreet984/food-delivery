package com.fooddelivery.fooddelivery.repositories;

import com.fooddelivery.fooddelivery.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE r.restaurantName LIKE %?1%")
    ArrayList<Restaurant> searchRestaurant(String name);

    @Query("SELECT r FROM Restaurant r WHERE r.cuisineType LIKE %?1%")
    ArrayList<Restaurant> searchRestaurantByCuisine(String name);


    @Query("SELECT r FROM Restaurant r WHERE r.location LIKE %?1%")
    ArrayList<Restaurant> searchRestaurantByLocation(String name);

    @Query("SELECT r FROM Restaurant r WHERE r.restaurantName LIKE %?1% OR r.cuisineType LIKE %?1% OR r.location LIKE %?1%")
    ArrayList<Restaurant> searchAll(String name);
}
