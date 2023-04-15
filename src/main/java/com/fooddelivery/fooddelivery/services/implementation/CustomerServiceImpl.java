package com.fooddelivery.fooddelivery.services.implementation;

import com.fooddelivery.fooddelivery.entities.Customer;
import com.fooddelivery.fooddelivery.repositories.CustomerRepository;
import com.fooddelivery.fooddelivery.services.skeletons.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<?> createCustomer(Customer customer) {
        try {
            if (!customer.check()) {
                return ResponseEntity.badRequest().body("Please enter valid details");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        Customer customer1 = customerRepository.findByEmail(customer.getEmail()).orElse(null);
        if (customer1 != null) {
            return ResponseEntity.badRequest().body("Email already exists, please login");
        }
        customer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    @Override
    public ResponseEntity<?> readCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }

    @Override
    public ResponseEntity<?> updateCustomer(Customer customer, Long id) {
        Customer customer1 = customerRepository.findById(id).orElse(null);
        if (customer1 != null) {
            try {
                if (!customer.check()) {
                    return ResponseEntity.badRequest().body("Invalid customer");
                }
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            customer.setId(id);
            customer.setPassword(Base64.getEncoder().encodeToString(customer.getPassword().getBytes()));
            return ResponseEntity.ok(customerRepository.save(customer));
        } else {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customerRepository.delete(customer);
            return ResponseEntity.ok("Customer deleted");
        } else {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }

    @Override
    public ResponseEntity<?> readAllCustomer() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @Override
    public ResponseEntity<?> searchCustomer(String name) {
        ArrayList<Customer> customers = customerRepository.findBySimilarName(name);
        if (customers.size() > 0) {
            return ResponseEntity.ok(customers);
        } else {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }

    @Override
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
