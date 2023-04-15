package com.fooddelivery.fooddelivery.controllers;

import com.fooddelivery.fooddelivery.services.skeletons.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * OrderController
 * - Handles all the requests related to the orders
 */
@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService orderService;

    //get all orders by customer id
    @GetMapping("/all/{customerId}")
    public ResponseEntity<?> getOrdersByCustomerId(@PathVariable("customerId") Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    //get all orders by restaurant id
    @GetMapping("/all/restaurants{restaurantId}")
    public ResponseEntity<?> getOrdersByRestaurantId(@PathVariable("restaurantId") Long restaurantId) {
        return orderService.getOrdersByRestaurantId(restaurantId);
    }

    //get all orders by status
    @GetMapping("/all/{customerId}/{status}")
    public ResponseEntity<?> getOrdersByStatus(@PathVariable("customerId") Long customerId, @PathVariable("status") String status) {
        return orderService.getOrdersByStatus(customerId, status);
    }

    @GetMapping("/createOrder/{customerId}/{menuItemId}")
    public ResponseEntity<?> createOrder(@PathVariable("customerId") Long customerId, @PathVariable("menuItemId") Long menuItemId) {
        return orderService.createOrder(customerId, menuItemId);
    }

    @GetMapping("/changeStatus/{id}/{status}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") Long id, @PathVariable("status") String status) {
        return orderService.changeStatus(id, status);
    }

    //complete all orders
    @GetMapping("/completeAll/{customerId}/{deliveryAddress}/{paymentMethod}")
    public ResponseEntity<?> completeAllOrders(@PathVariable("customerId") Long customerId, @PathVariable("deliveryAddress") String deliveryAddress, @PathVariable("paymentMethod") String paymentMethod) {
        return orderService.completeAllOrders(customerId, deliveryAddress, paymentMethod);
    }

    //increase quantity of an item in an order
    @GetMapping("/increaseQuantity/{id}")
    public ResponseEntity<?> increaseQuantity(@PathVariable("id") Long id) {
        return orderService.increaseQuantity(id);
    }

    //decrease quantity of an item in an order
    @GetMapping("/decreaseQuantity/{id}")
    public ResponseEntity<?> decreaseQuantity(@PathVariable("id") Long id) {
        return orderService.decreaseQuantity(id);
    }

    //delete order
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        return orderService.deleteOrder(id);
    }

    //get total amount of all orders by customer id
    @GetMapping("/totalAmount/{customerId}")
    public ResponseEntity<?> getTotalAmount(@PathVariable("customerId") Long customerId) {
        return orderService.getTotalAmount(customerId);
    }

    //getOrderById
    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) {
        return orderService.readOrder(id);
    }

    //get all orders that are Pending
    @GetMapping("/getAlOrdersByStatus/{status}")
    public ResponseEntity<?> getPendingOrders(@PathVariable("status") String status) {
        return orderService.readAllOrdersByStatus(status);
    }

    //get restaurant by order id
    @GetMapping("/getRestaurantByOrderId/{id}")
    public ResponseEntity<?> getRestaurantByOrderId(@PathVariable("id") Long id) {
        return orderService.getRestaurantByOrderId(id);
    }

    //get customer by order id
    @GetMapping("/getCustomerByOrderId/{id}")
    public ResponseEntity<?> getCustomerByOrderId(@PathVariable("id") Long id) {
        return orderService.getCustomerByOrderId(id);
    }


}
