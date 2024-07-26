package com.whale.siki_shop_app.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @NotEmpty(message = "Category's name can not be empty")
    private String name;
}
