package com.fooddelivery.fooddelivery.controllers;

import com.fooddelivery.fooddelivery.dto.CustomerDto;
import com.fooddelivery.fooddelivery.entities.Customer;
import com.fooddelivery.fooddelivery.services.skeletons.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * CustomerController
 * - Handles all the requests related to the customer
 */
@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {
    private final CustomerService customerService;

    //crud
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDto customer) {
        Customer customer1 = CustomerDto.build(0L, customer.getName(), customer.getEmail(), customer.getPassword(), customer.getPhoneNumber(), customer.getDeliveryAddress()).toEntity();
        return customerService.createCustomer(customer1);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return customerService.readAllCustomer();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        return customerService.readCustomer(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDto customer) {
        Customer customer1 = CustomerDto.build(id, customer.getName(), customer.getEmail(), customer.getPassword(), customer.getPhoneNumber(), customer.getDeliveryAddress()).toEntity();
        return customerService.updateCustomer(customer1, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
}
