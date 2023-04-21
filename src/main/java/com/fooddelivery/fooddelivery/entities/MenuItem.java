package com.fooddelivery.fooddelivery.entities;

import javax.persistence.*;

import lombok.*;
/**
 * MenuItem
 * - Represents a menu item in the database
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long id;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "item_image")
    private String itemImage;
    @Column(name = "item_name")
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "availability")
    private boolean availability;
}
