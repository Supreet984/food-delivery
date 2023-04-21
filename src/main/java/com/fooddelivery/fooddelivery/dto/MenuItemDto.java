package com.fooddelivery.fooddelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MenuItemDto {
    private Long id;
    @NotNull(message = "Restaurant id cannot be null")
    private Long restaurantId;
    @NotNull(message = "Item image cannot be null")
    private String itemImage;
    @NotNull(message = "Item name cannot be null")
    private String itemName;
    @NotNull(message = "Description cannot be null")
    private String description;
    @NotNull(message = "Price cannot be null")
    private Double price;
    @NotNull(message = "Availability cannot be null")
    private boolean availability;

    //entity to dto
    public static MenuItemDto toDto(com.fooddelivery.fooddelivery.entities.MenuItem menuItem) {
        return MenuItemDto.builder()
                .id(menuItem.getId())
                .restaurantId(menuItem.getRestaurantId())
                .itemImage(menuItem.getItemImage())
                .itemName(menuItem.getItemName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .availability(menuItem.isAvailability())
                .build();
    }

    //dto to entity
    public com.fooddelivery.fooddelivery.entities.MenuItem buildEntity() {
        return com.fooddelivery.fooddelivery.entities.MenuItem.builder()
                .id(this.id)
                .restaurantId(this.restaurantId)
                .itemImage(this.itemImage)
                .itemName(this.itemName)
                .description(this.description)
                .price(this.price)
                .availability(this.availability)
                .build();
    }
}