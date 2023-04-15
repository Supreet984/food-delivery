package com.fooddelivery.fooddelivery.services.skeletons;

import com.fooddelivery.fooddelivery.entities.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    //crud
    public ResponseEntity<?> createOrder(Order order);

    public ResponseEntity<?> readOrder(Long id);

    public ResponseEntity<?> updateOrder(Order order, Long id);

    public ResponseEntity<?> deleteOrder(Long id);
    //other
    public ResponseEntity<?> readAllOrders();

    ResponseEntity<?> changeStatus(Long id, String status);

    ResponseEntity<?> createOrder(Long customerId, Long menuItemId);

    //get orders by customer id
    public ResponseEntity<?> getOrdersByCustomerId(Long id);

    //get orders by restaurant id
    public ResponseEntity<?> getOrdersByRestaurantId(Long id);

    //get all orders by status
    public ResponseEntity<?> getOrdersByStatus(Long id, String status);

    ResponseEntity<?> increaseQuantity(Long id);

    ResponseEntity<?> decreaseQuantity(Long id);

    ResponseEntity<?> completeAllOrders(Long customerId, String deliveryAddress, String paymentMethod);

    ResponseEntity<?> getTotalAmount(Long customerId);

    ResponseEntity<?> readAllOrdersByStatus(String pending);

    ResponseEntity<?> getRestaurantByOrderId(Long id);

    ResponseEntity<?> getCustomerByOrderId(Long id);
}
