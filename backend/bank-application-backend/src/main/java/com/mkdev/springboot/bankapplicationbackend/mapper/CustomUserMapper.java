package com.mkdev.springboot.bankapplicationbackend.mapper;

import com.mkdev.springboot.bankapplicationbackend.dto.RegisterCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.dto.UpdateCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;
import org.springframework.stereotype.Component;

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

    public CustomUser mapUpdateRequestToEntity(UpdateCustomUserDTO userDTO) {
        CustomUser user = new CustomUser();
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            user.setPasswordHash(userDTO.getPassword());
        }
        return user;
    }
}
