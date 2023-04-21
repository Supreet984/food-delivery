package com.fooddelivery.fooddelivery.entities;

import lombok.*;

import javax.persistence.*;

/**
 * Customer
 * - Represents a customer in the database
 * - The customer can be an admin or a regular customer
 */
@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    @NonNull
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    public Customer() {

    }
}
