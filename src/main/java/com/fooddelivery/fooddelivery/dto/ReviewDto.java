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
public class ReviewDto {
    private Long id;
    @NotNull(message = "Customer id cannot be null")
    private Long customerId;
    @NotNull(message = "Restaurant id cannot be null")
    private Long restaurantId;
    @NotNull(message = "Rating cannot be null")
    private double rating;
    @NotNull(message = "Review cannot be null")
    private String review;

    //entity to dto
    public static ReviewDto toDto(com.fooddelivery.fooddelivery.entities.Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .customerId(review.getCustomerId())
                .restaurantId(review.getRestaurantId())
                .rating(review.getRating())
                .review(review.getReview())
                .build();
    }

    //dto to entity
    public com.fooddelivery.fooddelivery.entities.Review buildEntity() {
        return com.fooddelivery.fooddelivery.entities.Review.builder()
                .id(this.id)
                .customerId(this.customerId)
                .restaurantId(this.restaurantId)
                .rating(this.rating)
                .review(this.review)
                .build();
    }
}