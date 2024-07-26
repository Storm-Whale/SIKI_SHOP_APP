package com.whale.siki_shop_app.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    @Min(value = 1, message = "Order_ID's must be greater than 0")
    private Integer orderId;

    @Min(value = 1, message = "Product_ID's must be greater than 0")
    private Integer productId;

    @Min(value = 1, message = "Quality's must be greater than 0")
    private Integer quantity;

    @Min(value = 0, message = "Price's must be greater than 0")
    private Float price;

    @Min(value = 0, message = "Total_Price's must be greater than 0")
    private Float totalPrice;

    private String color;
}
