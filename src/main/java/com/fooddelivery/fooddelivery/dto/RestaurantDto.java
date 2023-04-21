package com.fooddelivery.fooddelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class RestaurantDto {

    private Long id;
    @NotNull(message = "Restaurant name cannot be null")
    private String restaurantName;
    @NotNull(message = "Image cannot be null")
    private String image;
    @NotNull(message = "Cuisine type cannot be null")
    private String cuisineType;
    @NotNull(message = "Location cannot be null")
    private String location;
    @NotNull(message = "Rating cannot be null")
    private String rating;

    //entity to dto
    public static RestaurantDto toDto(com.fooddelivery.fooddelivery.entities.Restaurant restaurant) {
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .restaurantName(restaurant.getRestaurantName())
                .image(restaurant.getImage())
                .cuisineType(restaurant.getCuisineType())
                .location(restaurant.getLocation())
                .rating(restaurant.getRating())
                .build();
    }

    //dto to entity
    public com.fooddelivery.fooddelivery.entities.Restaurant buildEntity() {
        return com.fooddelivery.fooddelivery.entities.Restaurant.builder()
                .id(this.id)
                .restaurantName(this.restaurantName)
                .image(this.image)
                .cuisineType(this.cuisineType)
                .location(this.location)
                .rating(this.rating)
                .build();
    }
}