package com.mkdev.springboot.bankapplicationbackend.dto;

import com.mkdev.springboot.bankapplicationbackend.security.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCustomUserDTO {
    private int userId;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @ValidPassword
    private String password;
}
