package com.fooddelivery.fooddelivery.controllers;

import com.fooddelivery.fooddelivery.entities.Customer;
import com.fooddelivery.fooddelivery.repositories.CustomerRepository;
import com.fooddelivery.fooddelivery.services.CustomerService;
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
public class CustomerControllerTest {

    @Mock
    CustomerRepository customerRepository;
    CustomerService customerService;
    AutoCloseable closeable;
    Customer customerDto;

    @BeforeEach
    public void setUp() throws Exception {
        closeable = org.mockito.MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerRepository);
        customerDto =Customer.builder()
                .name("Test Customer")
                .email("test@gmail.com")
                .password("testpassword")
                .phoneNumber("1234567890")
                .deliveryAddress("123 Test Street")
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void getAllCustomers() {
        mock(CustomerController.class);
        mock(CustomerRepository.class);
        List<Customer> customerList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerList);
        assert(customerList.size() == 0);

    }

    @Test
    public void getCustomerById() {
        mock(CustomerController.class);
        mock(CustomerRepository.class);
        when(customerRepository.getById(1L)).thenReturn(customerDto);
    }

    @Test
    public void createCustomer() {
        mock(CustomerController.class);
        mock(CustomerRepository.class);
        when(customerRepository.save(customerDto)).thenReturn(customerDto);
    }
}