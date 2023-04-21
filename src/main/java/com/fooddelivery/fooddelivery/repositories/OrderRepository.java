package com.fooddelivery.fooddelivery.repositories;

import com.fooddelivery.fooddelivery.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT o FROM Order o WHERE o.customerId = ?1")
    List<Order> findAllByCustomerId(Long id);

    @Query("SELECT o FROM Order o WHERE o.status = ?1")
    List<Order> findAllByStatus(String pending);
}
