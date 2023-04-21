package com.fooddelivery.fooddelivery.services;

import com.fooddelivery.fooddelivery.entities.Customer;
import com.fooddelivery.fooddelivery.entities.MenuItem;
import com.fooddelivery.fooddelivery.entities.Order;
import com.fooddelivery.fooddelivery.entities.Restaurant;
import com.fooddelivery.fooddelivery.repositories.CustomerRepository;
import com.fooddelivery.fooddelivery.repositories.MenuItemRepository;
import com.fooddelivery.fooddelivery.repositories.OrderRepository;
import com.fooddelivery.fooddelivery.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public ResponseEntity<?> createOrder(Order order) {
        if (order.validate()) return ResponseEntity.ok(orderRepository.save(order));
        else return ResponseEntity.badRequest().body("Invalid order");
    }

    public ResponseEntity<?> readOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) return ResponseEntity.ok(order);
        else return ResponseEntity.badRequest().body("Order not found");
    }

    public ResponseEntity<?> updateOrder(Order order, Long id) {
        Order order1 = orderRepository.findById(id).orElse(null);
        if (order1 != null) {
            if (!order.validate()) return ResponseEntity.badRequest().body("Invalid order");
            order.setId(id);
            return ResponseEntity.ok(orderRepository.save(order));
        } else {
            return ResponseEntity.badRequest().body("Order not found");
        }
    }

    public ResponseEntity<?> deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            orderRepository.delete(order);
            return ResponseEntity.ok("Order deleted");
        } else {
            return ResponseEntity.badRequest().body("Order not found");
        }
    }
    public ResponseEntity<?> readAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }



    public ResponseEntity<?> changeStatus(Long id, String status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(status);
            return ResponseEntity.ok(orderRepository.save(order));
        } else {
            return ResponseEntity.badRequest().body("Order not found");
        }
    }


    public ResponseEntity<?> createOrder(Long customerId, Long menuItemId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) return ResponseEntity.badRequest().body("Customer not found");
        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElse(null);
        if (menuItem == null) return ResponseEntity.badRequest().body("Menu item not found");
        //check if menu item exists and if it is Pending
        List<Order> pendingOrders = orderRepository.findAllByCustomerId(customerId);
        for (Order order : pendingOrders) {
            if (order.getMenuItemId().equals(menuItemId) && order.getStatus().equals("Pending")) {
                order.setQuantity(order.getQuantity() + 1);
                order.setTotalAmount(order.getQuantity() * menuItem.getPrice());
                return ResponseEntity.ok(orderRepository.save(order));
            }
        }

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setMenuItemId(menuItemId);
        order.setStatus("Pending");
        order.setOrderDate(String.valueOf(System.currentTimeMillis()));
        order.setTotalAmount(menuItem.getPrice());
        order.setQuantity(1);
        order.setItemName(menuItem.getItemName());
        return ResponseEntity.ok(orderRepository.save(order));
    }


    public ResponseEntity<?> getOrdersByCustomerId(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) return ResponseEntity.badRequest().body("Customer not found");
        return ResponseEntity.ok(orderRepository.findAllByCustomerId(id));
    }


    public ResponseEntity<?> getOrdersByRestaurantId(Long id) {
        List<Order> orders = orderRepository.findAll();
        List<Order> ordersByRestaurant = new ArrayList<>();
        for (Order order : orders) {
            MenuItem menuItem = menuItemRepository.findById(order.getMenuItemId()).orElse(null);
            if (menuItem != null && menuItem.getRestaurantId().equals(id)) ordersByRestaurant.add(order);
        }
        //sort by status (Pending first)
        ordersByRestaurant = ordersByRestaurant.stream().sorted(Comparator.comparing(Order::getStatus)).toList();
        return ResponseEntity.ok(ordersByRestaurant);
    }


    public ResponseEntity<?> getOrdersByStatus(Long id, String status) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) return ResponseEntity.badRequest().body("Customer not found");
        List<Order> orders = orderRepository.findAllByCustomerId(id);
        List<Order> ordersByStatus = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus().equals(status)) ordersByStatus.add(order);
        }
        //sort by date
        ordersByStatus.sort((o1, o2) -> Long.compare(Long.parseLong(o2.getOrderDate()), Long.parseLong(o1.getOrderDate())));
        return ResponseEntity.ok(ordersByStatus);
    }


    public ResponseEntity<?> increaseQuantity(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setQuantity(order.getQuantity() + 1);
            order.setTotalAmount(order.getQuantity() * order.getTotalAmount() / (order.getQuantity() - 1));
            return ResponseEntity.ok(orderRepository.save(order));
        } else {
            return ResponseEntity.badRequest().body("Order not found");
        }
    }


    public ResponseEntity<?> decreaseQuantity(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            if (order.getQuantity() > 1) {
                order.setQuantity(order.getQuantity() - 1);
                MenuItem menuItem = menuItemRepository.findById(order.getMenuItemId()).orElse(null);
                if (menuItem != null) order.setTotalAmount(order.getQuantity() * menuItem.getPrice());
                return ResponseEntity.ok(orderRepository.save(order));
            } else {
                return ResponseEntity.badRequest().body("Quantity cannot be less than 1");
            }
        } else {
            return ResponseEntity.badRequest().body("Order not found");
        }
    }


    public ResponseEntity<?> completeAllOrders(Long customerId, String deliveryAddress, String paymentMethod) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) return ResponseEntity.badRequest().body("Customer not found");
        List<Order> orders = orderRepository.findAllByCustomerId(customerId);
        for (Order order : orders) {
            if (order.getStatus().equals("Pending")) {
                order.setStatus("Waiting");
                order.setDeliveryAddress(deliveryAddress);
                order.setPaymentMethod(paymentMethod);
                orderRepository.save(order);
            }
        }
        return ResponseEntity.ok("All orders completed");
    }


    public ResponseEntity<?> getTotalAmount(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) return ResponseEntity.badRequest().body("Customer not found");
        List<Order> orders = orderRepository.findAllByCustomerId(customerId);
        double totalAmount = 0;
        for (Order order : orders) {
            if (order.getStatus().equals("Pending")) {
                totalAmount += order.getTotalAmount();
            }
        }
        totalAmount = Math.round(totalAmount * 100.0) / 100.0;
        return ResponseEntity.ok(totalAmount);
    }


    public ResponseEntity<?> readAllOrdersByStatus(String pending) {
        return ResponseEntity.ok(orderRepository.findAllByStatus(pending));
    }


    public ResponseEntity<?> getRestaurantByOrderId(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            MenuItem menuItem = menuItemRepository.findById(order.getMenuItemId()).orElse(null);
            if (menuItem != null) {
                Restaurant restaurant = restaurantRepository.findById(menuItem.getRestaurantId()).orElse(null);
                if (restaurant != null) return ResponseEntity.ok(restaurant);
                else return ResponseEntity.badRequest().body("Restaurant not found");
            } else {
                return ResponseEntity.badRequest().body("Menu item not found");
            }
        } else {
            return ResponseEntity.badRequest().body("Order not found");
        }
    }


    public ResponseEntity<?> getCustomerByOrderId(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            Customer customer = customerRepository.findById(order.getCustomerId()).orElse(null);
            if (customer != null) return ResponseEntity.ok(customer);
            else return ResponseEntity.badRequest().body("Customer not found");
        } else {
            return ResponseEntity.badRequest().body("Order not found");
        }
    }
}
