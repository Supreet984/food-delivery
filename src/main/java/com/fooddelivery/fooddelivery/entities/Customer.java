package com.fooddelivery.fooddelivery.entities;

import lombok.*;

import javax.persistence.*;

/**
 * Customer
 * - Represents a customer in the database
 * - The customer can be an admin or a regular customer
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    @NonNull
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "is_admin")
    private boolean isAdmin = false;

    public boolean check() throws Exception {
        //validate email regex
        if (this.email != null && !this.email.isEmpty() && !this.email.isBlank()) {
            String emailRegex = "^(.+)@(.+)$";
            if (!this.email.matches(emailRegex))
                throw new Exception("Invalid email");
        }else{
            throw new Exception("Invalid email");
        }
        //validate phone number regex
        if (this.phoneNumber != null && !this.phoneNumber.isEmpty() && !this.phoneNumber.isBlank()) {
            String phoneRegex = "^[0-9]{10}$";
            if (!this.phoneNumber.matches(phoneRegex))
                throw new Exception("Invalid phone number");
        }else{
            throw new Exception("Invalid phone number");
        }
        //validate name
        if (this.name.isEmpty() || this.name.isBlank())
            throw new Exception("Invalid name");
        return true;
    }

}
