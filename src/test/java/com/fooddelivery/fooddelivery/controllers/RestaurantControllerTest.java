package com.fooddelivery.fooddelivery.controllers;

import com.fooddelivery.fooddelivery.entities.Restaurant;
import com.fooddelivery.fooddelivery.repositories.RestaurantRepository;
import com.fooddelivery.fooddelivery.repositories.ReviewRepository;
import com.fooddelivery.fooddelivery.services.RestaurantService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestaurantControllerTest {

    @Mock
    RestaurantRepository restaurantRepository;
    @Mock
    ReviewRepository reviewRepository;
    RestaurantService restaurantService;
    AutoCloseable closeable;
    Restaurant restaurantDto;

    @BeforeEach
    public void setUp() throws Exception {
        closeable = org.mockito.MockitoAnnotations.openMocks(this);
        restaurantService = new RestaurantService(restaurantRepository, reviewRepository);
        restaurantDto = Restaurant.builder()
                .restaurantName("Test Restaurant")
                .rating("5.0")
                .cuisineType("Test Cuisine")
                .image("Test Image")
                .location("Test Location")
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @org.junit.Test
    public void getAllRestaurants() {
        mock(RestaurantController.class);
        mock(RestaurantRepository.class);
        List<Restaurant> restaurantDto = new ArrayList<>();
        when(restaurantRepository.findAll()).thenReturn(restaurantDto);
        assert (restaurantDto.size() == 0);
    }

    @org.junit.Test
    public void getRestaurantById() {
        mock(RestaurantController.class);
        mock(RestaurantRepository.class);
        when(restaurantRepository.getById(1L)).thenReturn(restaurantDto);
    }

    @Test
    public void createRestaurant() {
        mock(RestaurantController.class);
        mock(RestaurantRepository.class);
        when(restaurantRepository.save(restaurantDto)).thenReturn(restaurantDto);
    }
}