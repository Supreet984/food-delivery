package com.fooddelivery.fooddelivery.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "rating")
    private double rating;

    @Column(name = "review")
    private String review;
}
