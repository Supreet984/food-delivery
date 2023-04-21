package com.fooddelivery.fooddelivery.services.skeletons;

import com.fooddelivery.fooddelivery.entities.MenuItem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MenuItemService {
    //crud
    public ResponseEntity<?> createMenuItem(MenuItem menuItem);

    public ResponseEntity<?> readMenuItem(Long id);

    public ResponseEntity<?> updateMenuItem(MenuItem menuItem, Long id);

    public ResponseEntity<?> deleteMenuItem(Long id);

    //other
    public ResponseEntity<?> readAllMenuItems(Long restaurantId);
}
