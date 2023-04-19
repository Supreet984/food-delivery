package com.fooddelivery.fooddelivery.dto;

import com.fooddelivery.fooddelivery.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Min(value = 8, message = "Password should be at least 8 characters")
    private String password;

    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;

    @NotNull(message = "Delivery address cannot be null")
    private String deliveryAddress;

    //entity to dto
    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.phoneNumber = customer.getPhoneNumber();
        this.deliveryAddress = customer.getDeliveryAddress();
    }

    //dto to entity
    public Customer toEntity() {
        return new Customer(this.id, this.name, this.email, this.password, this.phoneNumber, this.deliveryAddress, false);
    }
}
