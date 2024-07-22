package com.whale.siki_shop_app.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String name;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 10000000, message = "Price must be less than equal to 10.000.000")
    private Float price;

    private String thumbnail;

    private String description;

    @NotNull(message = "Category_ID is required")
    private Integer categoryId;
//    private LocalDateTime createAt;
//    private LocalDateTime updateAt;

    private List<MultipartFile> listImage;
}
