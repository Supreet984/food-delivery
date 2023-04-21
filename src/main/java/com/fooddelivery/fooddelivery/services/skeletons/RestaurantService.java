package com.fooddelivery.fooddelivery.services.skeletons;

import com.fooddelivery.fooddelivery.entities.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {
    //crud
    public ResponseEntity<?> createRestaurant(Restaurant restaurant);
    public ResponseEntity<?> readRestaurant(Long id);
    public ResponseEntity<?> updateRestaurant(Restaurant restaurant, Long id);
    public ResponseEntity<?> deleteRestaurant(Long id);

    //other
    public ResponseEntity<?> readAllRestaurants();

    ResponseEntity<?> searchRestaurant(String name, String category);

    ResponseEntity<?> rateRestaurant(Long id, Long customerId, int rating);

    ResponseEntity<?> getRating(Long id);

    ResponseEntity<?> getNumberOfReviews(Long id);

    ResponseEntity<?> getRestaurant(Long id);
}
