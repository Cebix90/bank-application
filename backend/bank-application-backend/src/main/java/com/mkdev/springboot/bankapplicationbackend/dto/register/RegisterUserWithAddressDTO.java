package com.mkdev.springboot.bankapplicationbackend.dto.register;

import lombok.Data;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Data
public class RegisterUserWithAddressDTO {
    @Valid
    @NotNull
    private RegisterCustomUserDTO user;

    @Valid
    @NotNull
    private RegisterAddressDTO address;
}