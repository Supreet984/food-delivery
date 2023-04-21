package com.fooddelivery.fooddelivery.services;

import com.fooddelivery.fooddelivery.entities.Customer;
import com.fooddelivery.fooddelivery.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public ResponseEntity<?> createCustomer(Customer customer) {
        Customer customer1 = customerRepository.findByEmail(customer.getEmail()).orElse(null);
        if (customer1 != null) {
            return ResponseEntity.badRequest().body("Email already exists, please login");
        }
        customer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    public ResponseEntity<?> readCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }

    public ResponseEntity<?> updateCustomer(Customer customer, Long id) {
        Customer customer1 = customerRepository.findById(id).orElse(null);
        if (customer1 != null) {
            customer.setId(id);
            customer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
            return ResponseEntity.ok(customerRepository.save(customer));
        } else {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }

    public ResponseEntity<?> deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customerRepository.delete(customer);
            return ResponseEntity.ok("Customer deleted");
        } else {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }

    public ResponseEntity<?> readAllCustomer() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    public ResponseEntity<?> searchCustomer(String name) {
        ArrayList<Customer> customers = customerRepository.findBySimilarName(name);
        if (customers.size() > 0) {
            return ResponseEntity.ok(customers);
        } else {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }


    public ResponseEntity<?> loginCustomer(Customer customer) {
        customer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
        Customer customer1 = customerRepository.findByEmail(customer.getEmail()).orElse(null);
        if (customer1 != null) {
            if (customer1.getPassword().equals(customer.getPassword())) {
                return ResponseEntity.ok(customer1);
            } else {
                return ResponseEntity.badRequest().body("Invalid password");
            }
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}
