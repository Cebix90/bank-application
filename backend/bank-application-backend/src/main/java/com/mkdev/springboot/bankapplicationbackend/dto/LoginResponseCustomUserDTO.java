package com.mkdev.springboot.bankapplicationbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseCustomUserDTO {
    private String token;
    private int userId;
}
