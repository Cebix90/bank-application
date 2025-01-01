package com.mkdev.springboot.bankapplicationbackend.dto;

import lombok.Data;

@Data
public class LoginRequestCustomUserDTO {
    private String email;
    private String password;
}
