package com.mkdev.springboot.bankapplicationbackend.dto.login;

import lombok.Data;

@Data
public class LoginRequestCustomUserDTO {
    private String email;
    private String password;
}
