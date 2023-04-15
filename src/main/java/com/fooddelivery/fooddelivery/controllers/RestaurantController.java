package com.fooddelivery.fooddelivery.controllers;

import com.fooddelivery.fooddelivery.entities.Restaurant;
import com.fooddelivery.fooddelivery.services.skeletons.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * RestaurantController
 * - Handles all the requests related to the restaurants
 */
@RestController
@RequestMapping("/api/v1/restaurants")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/list")
    public ResponseEntity<?> listRestaurants() {
        return restaurantService.readAllRestaurants();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchRestaurant(@RequestParam("name") String name, @RequestParam("category") String category) {
        return restaurantService.searchRestaurant(name, category);
    }

    //rate a restaurant
    @GetMapping("/rate/{id}/{userId}/{rating}")
    public ResponseEntity<?> rateRestaurant(@PathVariable("id") Long id, @PathVariable("userId") Long userId, @PathVariable("rating") int rating) {
        return restaurantService.rateRestaurant(id, userId, rating);
    }

    //get rating of a restaurant
    @GetMapping("/getRating/{id}")
    public ResponseEntity<?> getRating(@PathVariable("id") Long id) {
        return restaurantService.getRating(id);
    }

    //number of reviews of a restaurant
    @GetMapping("/getNumberOfReviews/{id}")
    public ResponseEntity<?> getNumberOfReviews(@PathVariable("id") Long id) {
        return restaurantService.getNumberOfReviews(id);
    }

    //get restaurant by id
    @GetMapping("/getRestaurant/{id}")
    public ResponseEntity<?> getRestaurant(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    //crud

    //create restaurant
    @PostMapping
    public ResponseEntity<?> createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    //update restaurant
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(restaurant, id);
    }

    //delete restaurant
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        return restaurantService.deleteRestaurant(id);
    }

}
