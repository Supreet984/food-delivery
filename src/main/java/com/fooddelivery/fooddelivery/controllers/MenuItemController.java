package com.fooddelivery.fooddelivery.controllers;

import com.fooddelivery.fooddelivery.dto.MenuItemDto;
import com.fooddelivery.fooddelivery.entities.MenuItem;
import com.fooddelivery.fooddelivery.services.MenuItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> createMenuItem(@RequestBody @Valid MenuItemDto menuItem) {
        MenuItem menuItem1 = menuItem.buildEntity();
        return menuItemService.createMenuItem(menuItem1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuItemById(@PathVariable Long id) {
        return menuItemService.readMenuItem(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMenuItem(@PathVariable Long id, @RequestBody @Valid MenuItemDto menuItem) {
        MenuItem menuItem1 = menuItem.buildEntity();
        return menuItemService.updateMenuItem(menuItem1, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id) {
        return menuItemService.deleteMenuItem(id);
    }


}
