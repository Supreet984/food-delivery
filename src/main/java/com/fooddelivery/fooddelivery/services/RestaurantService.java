package com.fooddelivery.fooddelivery.services;

import com.fooddelivery.fooddelivery.entities.Restaurant;
import com.fooddelivery.fooddelivery.entities.Review;
import com.fooddelivery.fooddelivery.repositories.RestaurantRepository;
import com.fooddelivery.fooddelivery.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;


    public ResponseEntity<?> createRestaurant(Restaurant restaurant) {
        return ResponseEntity.ok(restaurantRepository.save(restaurant));
    }


    public ResponseEntity<?> readRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant != null)
            return ResponseEntity.ok(restaurant);
        else
            return ResponseEntity.badRequest().body("Restaurant not found");
    }


    public ResponseEntity<?> updateRestaurant(Restaurant restaurant, Long id) {
        Restaurant restaurant1 = restaurantRepository.findById(id).orElse(null);
        if (restaurant1 != null) {
            restaurant.setId(id);
            return ResponseEntity.ok(restaurantRepository.save(restaurant));
        } else {
            return ResponseEntity.badRequest().body("Restaurant not found");
        }
    }


    public ResponseEntity<?> deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant != null) {
            restaurantRepository.delete(restaurant);
            return ResponseEntity.ok("Restaurant deleted");
        } else {
            return ResponseEntity.badRequest().body("Restaurant not found");
        }
    }


    public ResponseEntity<?> readAllRestaurants() {
        return ResponseEntity.ok(restaurantRepository.findAll());
    }


    public ResponseEntity<?> searchRestaurant(String name, String category) {
        if (name.equals("null") && category.equals("null") || name.equals("") && category.equals("")) {
            return ResponseEntity.ok(restaurantRepository.findAll());
        } else if (category.toLowerCase().contains("all categories"))
            return ResponseEntity.ok(restaurantRepository.searchAll(name));
        else if (category.toLowerCase().contains("restaurant name"))
            return ResponseEntity.ok(restaurantRepository.searchRestaurant(name));
        else if (category.toLowerCase().contains("cuisine"))
            return ResponseEntity.ok(restaurantRepository.searchRestaurantByCuisine(name));
        else if (category.toLowerCase().contains("location"))
            return ResponseEntity.ok(restaurantRepository.searchRestaurantByLocation(name));
        else {
            return ResponseEntity.ok(restaurantRepository.searchRestaurant(name));
        }
    }


    public ResponseEntity<?> rateRestaurant(Long id, Long userId, double rating) {
        Review review = reviewRepository.findByRestaurantIdAndUserId(id, userId);
        if (review != null) {
            review.setRating(rating);
            reviewRepository.save(review);
            return ResponseEntity.ok("Rating updated");
        } else {
            review = new Review();
            review.setRating(rating);
            review.setRestaurantId(id);
            review.setCustomerId(userId);
            reviewRepository.save(review);
            return ResponseEntity.ok("Rating added");
        }
    }


    public ResponseEntity<?> getRating(Long id) {
        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
        if (reviews.size() == 0)
            return ResponseEntity.ok(0.0);
        else {
            double sum = 0;
            for (Review review : reviews) {
                sum += review.getRating();
            }
            Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
            if (restaurant != null) {
                restaurant.setRating(String.valueOf(sum / reviews.size()));
                restaurantRepository.save(restaurant);
            }
            return ResponseEntity.ok(sum / reviews.size());
        }
    }


    public ResponseEntity<?> getNumberOfReviews(Long id) {
        Long count = reviewRepository.countByRestaurantId(id);
        return ResponseEntity.ok(count);
    }

    public ResponseEntity<?> getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        } else {
            return ResponseEntity.badRequest().body("Restaurant not found");
        }
    }
}
