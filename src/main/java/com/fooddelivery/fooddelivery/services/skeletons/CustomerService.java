package com.fooddelivery.fooddelivery.services.skeletons;

import com.fooddelivery.fooddelivery.entities.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface CustomerService {
    //crud
    public ResponseEntity<?> createCustomer(Customer customer);

    public ResponseEntity<?> readCustomer(Long id);

    public ResponseEntity<?> updateCustomer(Customer customer, Long id);

    public ResponseEntity<?> deleteCustomer(Long id);

    //other
    public ResponseEntity<?> readAllCustomer();

    ResponseEntity<?> searchCustomer(String name);

    ResponseEntity<?> loginCustomer(Customer customer);
}
