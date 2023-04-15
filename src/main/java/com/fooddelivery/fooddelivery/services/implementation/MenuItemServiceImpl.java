package com.fooddelivery.fooddelivery.services.implementation;

import com.fooddelivery.fooddelivery.entities.MenuItem;
import com.fooddelivery.fooddelivery.repositories.MenuItemRepository;
import com.fooddelivery.fooddelivery.services.skeletons.MenuItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Override
    public ResponseEntity<?> createMenuItem(MenuItem menuItem) {
        if (menuItem.validate()) {
            return ResponseEntity.ok(menuItemRepository.save(menuItem));
        } else {
            return ResponseEntity.badRequest().body("Invalid menu item");
        }
    }

    @Override
    public ResponseEntity<?> readMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id).orElse(null);
        if (menuItem != null) {
            return ResponseEntity.ok(menuItem);
        } else {
            return ResponseEntity.badRequest().body("Menu item not found");
        }
    }

    @Override
    public ResponseEntity<?> updateMenuItem(MenuItem menuItem, Long id) {
        MenuItem menuItem1 = menuItemRepository.findById(id).orElse(null);
        if (menuItem1 != null) {
            if (!menuItem.validate()) {
                return ResponseEntity.badRequest().body("Invalid menu item");
            }
            menuItem.setId(id);
            return ResponseEntity.ok(menuItemRepository.save(menuItem));
        } else {
            return ResponseEntity.badRequest().body("Menu item not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id).orElse(null);
        if (menuItem != null) {
            menuItemRepository.delete(menuItem);
            return ResponseEntity.ok("Menu item deleted");
        } else {
            return ResponseEntity.badRequest().body("Menu item not found");
        }
    }

    @Override
    public ResponseEntity<?> readAllMenuItems(Long restaurantId) {
        return ResponseEntity.ok(menuItemRepository.findAllByRestaurantId(restaurantId));
    }
}
