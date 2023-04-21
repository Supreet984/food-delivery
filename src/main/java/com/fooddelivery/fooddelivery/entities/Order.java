package com.fooddelivery.fooddelivery.entities;

import lombok.*;

import javax.persistence.*;

/**
 * Order
 * - Represents an order in the database
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;
    @Column(name = "menu_item_id")
    private Long menuItemId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "quantity")
    private int quantity;
    @Column(name = "total_amount")
    private double totalAmount;
    @Column(name = "status")
    private String status;

    public boolean validate() {
        return !deliveryAddress.isEmpty() && !deliveryAddress.isBlank() && !paymentMethod.isEmpty() && !paymentMethod.isBlank() && customerId != null && menuItemId != null;
    }
}
