package com.mkdev.springboot.bankapplicationbackend.service.customuser;

import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;

import java.util.List;

public interface CustomUserService {
    List<CustomUser> findAll();

    CustomUser  findById(int theId);

    CustomUser save(CustomUser theCustomUser);

    void deleteById(int theId);
}
