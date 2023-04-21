package com.fooddelivery.fooddelivery.services;

import com.fooddelivery.fooddelivery.entities.MenuItem;
import com.fooddelivery.fooddelivery.repositories.MenuItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public ResponseEntity<?> createMenuItem(MenuItem menuItem) {
        return ResponseEntity.ok(menuItemRepository.save(menuItem));
    }

    public ResponseEntity<?> readMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id).orElse(null);
        if (menuItem != null) {
            return ResponseEntity.ok(menuItem);
        } else {
            return ResponseEntity.badRequest().body("Menu item not found");
        }
    }

    public ResponseEntity<?> updateMenuItem(MenuItem menuItem, Long id) {
        MenuItem menuItem1 = menuItemRepository.findById(id).orElse(null);
        if (menuItem1 != null) {
            menuItem.setId(id);
            return ResponseEntity.ok(menuItemRepository.save(menuItem));
        } else {
            return ResponseEntity.badRequest().body("Menu item not found");
        }
    }

    public ResponseEntity<?> deleteMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id).orElse(null);
        if (menuItem != null) {
            menuItemRepository.delete(menuItem);
            return ResponseEntity.ok("Menu item deleted");
        } else {
            return ResponseEntity.badRequest().body("Menu item not found");
        }
    }

    public ResponseEntity<?> readAllMenuItems(Long restaurantId) {
        return ResponseEntity.ok(menuItemRepository.findAllByRestaurantId(restaurantId));
    }
}
