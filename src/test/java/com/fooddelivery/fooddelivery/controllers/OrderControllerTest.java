package com.fooddelivery.fooddelivery.controllers;


import com.fooddelivery.fooddelivery.entities.Order;
import com.fooddelivery.fooddelivery.repositories.CustomerRepository;
import com.fooddelivery.fooddelivery.repositories.MenuItemRepository;
import com.fooddelivery.fooddelivery.repositories.OrderRepository;
import com.fooddelivery.fooddelivery.repositories.RestaurantRepository;
import com.fooddelivery.fooddelivery.services.OrderService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderControllerTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    MenuItemRepository menuItemRepository;
    @Mock
    RestaurantRepository restaurantRepository;
    OrderService orderService;
    AutoCloseable closeable;
    Order orderDto;

    @BeforeEach
    public void setUp() throws Exception {
        closeable = org.mockito.MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository, customerRepository, menuItemRepository, restaurantRepository);
        orderDto = Order.builder()
                .orderDate("2021-01-01")
                .menuItemId(1L)
                .deliveryAddress("Test Address")
                .quantity(1)
                .status("Pending")
                .totalAmount(10.00)
                .paymentMethod("Cash")
                .itemName("Test Item")
                .customerId(1L)
                .build();

    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void getAllOrders() {
        mock(OrderController.class);
        mock(OrderRepository.class);
        List<Order> orderList = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(orderList);

    }

    @Test
    public void getOrderById() {
        mock(OrderController.class);
        mock(OrderRepository.class);
        when(orderRepository.getById(1L)).thenReturn(orderDto);
    }

    @Test
    public void createOrder() {
        mock(OrderController.class);
        mock(OrderRepository.class);
        when(orderRepository.save(orderDto)).thenReturn(orderDto);
    }
}