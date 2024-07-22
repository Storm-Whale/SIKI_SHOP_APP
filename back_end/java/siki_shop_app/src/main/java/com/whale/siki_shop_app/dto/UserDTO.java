package com.whale.siki_shop_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String fullName;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String address;

    @NotBlank(message = "Password is cannot be black")
    private String password;

    private String retypePassword;

    private Date dateOfBirth;

    private Integer facebookAccountId;

    private Integer googleAccountId;

    @NotNull(message = "Role ID is required")
    private Integer rodeId;
}
