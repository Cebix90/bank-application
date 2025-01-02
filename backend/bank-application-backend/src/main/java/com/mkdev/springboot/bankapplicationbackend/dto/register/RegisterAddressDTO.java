package com.mkdev.springboot.bankapplicationbackend.dto.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterAddressDTO {
    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotBlank(message = "House Number cannot be blank")
    private String houseNumber;

    private int flat;

    @NotNull(message = "Postal code is required")
    private int postalCode;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Country cannot be blank")
    private String country;
}
