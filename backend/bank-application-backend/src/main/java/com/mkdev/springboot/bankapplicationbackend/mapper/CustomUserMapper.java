package com.mkdev.springboot.bankapplicationbackend.mapper;

import com.mkdev.springboot.bankapplicationbackend.dto.RegisterCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomUserMapper {
    public CustomUser mapRequestToEntity(RegisterCustomUserDTO userDTO) {
        CustomUser user = new CustomUser();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(userDTO.getPassword());

        return user;
    }
}
