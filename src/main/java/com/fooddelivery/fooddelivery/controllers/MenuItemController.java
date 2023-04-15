package com.fooddelivery.fooddelivery.controllers;

import com.fooddelivery.fooddelivery.entities.MenuItem;
import com.fooddelivery.fooddelivery.services.skeletons.MenuItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * MenuItemController
 * - Handles all the requests related to the menu items
 */
@RestController
@RequestMapping("/api/v1/menu-items")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MenuItemController {
    private final MenuItemService menuItemService;

    @GetMapping("/all/{restaurantId}")
    public ResponseEntity<?> listAllMenuItems(@PathVariable("restaurantId") Long restaurantId) {
        return menuItemService.readAllMenuItems(restaurantId);
    }

    //crud
    @PostMapping
    public ResponseEntity<?> createMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.createMenuItem(menuItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuItemById(@PathVariable Long id) {
        return menuItemService.readMenuItem(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return menuItemService.updateMenuItem(menuItem, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id) {
        return menuItemService.deleteMenuItem(id);
    }


}
