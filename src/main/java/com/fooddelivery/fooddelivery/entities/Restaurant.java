package com.fooddelivery.fooddelivery.entities;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "restaurant")
public class Restaurant {
    //restaurant_id, restaurant_name, cuisine_type, location, rating
    @Id
    @Column(name = "restaurant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_name")
    private String restaurantName;
    @Column(name = "image")
    private String image;
    @Column(name = "cuisine_type")
    private String cuisineType;

    @Column(name = "location")
    private String location;

    @Column(name = "rating")
    private String rating;

    public boolean validate() {
        return !restaurantName.isEmpty() && !restaurantName.isBlank() && !cuisineType.isEmpty() && !cuisineType.isBlank() && !location.isEmpty() && !location.isBlank() && !rating.isEmpty() && !rating.isBlank();
    }

}
