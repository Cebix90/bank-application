package com.mkdev.springboot.bankapplicationbackend.mapper;

import com.mkdev.springboot.bankapplicationbackend.dto.register.RegisterCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.dto.UpdateCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomUserMapperTests {
    private final CustomUserMapper customUserMapper = new CustomUserMapper();

    @Test
    void shouldMapRegisterCustomUserDTOToEntity() {
        RegisterCustomUserDTO registerCustomUserDTO = new RegisterCustomUserDTO();
        registerCustomUserDTO.setName("John");
        registerCustomUserDTO.setSurname("Doe");
        registerCustomUserDTO.setEmail("john.doe@example.com");
        registerCustomUserDTO.setPassword("password123!");

        CustomUser mappedUser = customUserMapper.mapRequestToEntity(registerCustomUserDTO);

        assertThat(mappedUser).isNotNull();
        assertThat(mappedUser.getName()).isEqualTo("John");
        assertThat(mappedUser.getSurname()).isEqualTo("Doe");
        assertThat(mappedUser.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(mappedUser.getPasswordHash()).isEqualTo("password123!");
    }

    @Test
    void shouldMapUpdateCustomUserDTOToEntity() {
        UpdateCustomUserDTO updateCustomUserDTO = new UpdateCustomUserDTO();
        updateCustomUserDTO.setUserId(1);
        updateCustomUserDTO.setName("Jane");
        updateCustomUserDTO.setSurname("Smith");
        updateCustomUserDTO.setEmail("jane.smith@example.com");
        updateCustomUserDTO.setPassword("newPassword123!");

        CustomUser mappedUser = customUserMapper.mapUpdateRequestToEntity(updateCustomUserDTO);

        assertThat(mappedUser).isNotNull();
        assertThat(mappedUser.getUserId()).isEqualTo(1);
        assertThat(mappedUser.getName()).isEqualTo("Jane");
        assertThat(mappedUser.getSurname()).isEqualTo("Smith");
        assertThat(mappedUser.getEmail()).isEqualTo("jane.smith@example.com");
        assertThat(mappedUser.getPasswordHash()).isEqualTo("newPassword123!");
    }

    @Test
    void shouldNotSetPasswordHashWhenNullInUpdateCustomUserDTO() {
        UpdateCustomUserDTO updateCustomUserDTO = new UpdateCustomUserDTO();
        updateCustomUserDTO.setUserId(1);
        updateCustomUserDTO.setName("Jane");
        updateCustomUserDTO.setSurname("Smith");
        updateCustomUserDTO.setEmail("jane.smith@example.com");
        updateCustomUserDTO.setPassword(null);

        CustomUser mappedUser = customUserMapper.mapUpdateRequestToEntity(updateCustomUserDTO);

        assertThat(mappedUser.getPasswordHash()).isNull();
    }
}
