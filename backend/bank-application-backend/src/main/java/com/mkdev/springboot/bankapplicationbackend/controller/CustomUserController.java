package com.mkdev.springboot.bankapplicationbackend.controller;

import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;
import com.mkdev.springboot.bankapplicationbackend.service.customuser.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomUserController {
    private final CustomUserService customUserService;

    @Autowired
    public CustomUserController(CustomUserService theCustomUserService) {
        this.customUserService = theCustomUserService;
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
    public CustomUser addUser (@RequestBody CustomUser theCustomUser) {
        theCustomUser.setUserId(0);

        CustomUser dbCustomUser = customUserService.save(theCustomUser);

        return dbCustomUser;
    }

    @PutMapping("/custom-users")
    public CustomUser updateUser (@RequestBody CustomUser theCustomUser) {
        CustomUser dbCustomUser = customUserService.save(theCustomUser);

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
