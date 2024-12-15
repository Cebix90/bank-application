package com.mkdev.springboot.bankapplicationbackend.controller;

import com.mkdev.springboot.bankapplicationbackend.dto.RegisterCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.dto.UpdateCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;
import com.mkdev.springboot.bankapplicationbackend.mapper.CustomUserMapper;
import com.mkdev.springboot.bankapplicationbackend.service.customuser.CustomUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CustomUserController {
    private final CustomUserService customUserService;
    private final CustomUserMapper customUserMapper;

    @Autowired
    public CustomUserController(CustomUserService theCustomUserService, CustomUserMapper customUserMapper) {
        this.customUserService = theCustomUserService;
        this.customUserMapper = customUserMapper;
    }

    @GetMapping("/custom-users")
    public List<CustomUser> findAllUsers() {
        return customUserService.findAll();
    }

    @GetMapping("/custom-users/{userId}")
    public CustomUser getUser (@PathVariable int userId) {
        CustomUser theCustomUser = customUserService.findById(userId);

        if(theCustomUser == null) {
            throw new RuntimeException("User is not found: " + userId);
        }

        return theCustomUser;
    }

    @PostMapping("/custom-users")
    public CustomUser addUser (@Valid @RequestBody RegisterCustomUserDTO registerCustomUserDTO) {
        CustomUser mappedCustomUser = customUserMapper.mapRequestToEntity(registerCustomUserDTO);
        mappedCustomUser.setUserId(0);

        CustomUser dbCustomUser = customUserService.addUser(mappedCustomUser);

        return dbCustomUser;
    }

    @PutMapping("/custom-users")
    public CustomUser updateUser (@Valid @RequestBody UpdateCustomUserDTO updateCustomUserDTO) {
        CustomUser mappedCustomUser = customUserMapper.mapUpdateRequestToEntity(updateCustomUserDTO);

        CustomUser dbCustomUser = customUserService.updateUser(mappedCustomUser);

        return dbCustomUser;
    }

    @DeleteMapping("/custom-users/{userId}")
    public String deleteUser (@PathVariable int userId) {
        CustomUser theCustomUser = customUserService.findById(userId);

        if(theCustomUser == null) {
            throw new RuntimeException("User is not found: " + userId);
        }

        customUserService.deleteById(userId);

        return "Deleted user id: " + userId;
    }
}
