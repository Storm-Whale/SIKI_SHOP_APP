package com.whale.siki_shop_app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @Min(value = 1, message = "User's ID must be greater than 0")
    private Integer userId;

    private String fullname;

    private String email;

    @NotBlank(message = "Phone Number is required")
    @Size(min = 10, max = 11, message = "Phone Number must be least 10 characters")
    private String phoneNumber;

    private String address;

    private String note;

    @Min(value = 0, message = "Total Price must be greater than 0 or equal to 0")
    private Float totalPrice;

    private String shippingMethod;

    private String shippingAddress;

    private String paymentMethod;
}
