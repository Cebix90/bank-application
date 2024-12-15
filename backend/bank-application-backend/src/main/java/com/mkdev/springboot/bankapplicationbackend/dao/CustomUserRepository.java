package com.mkdev.springboot.bankapplicationbackend.dao;

import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomUserRepository extends JpaRepository<CustomUser, Integer> {
    Optional<CustomUser> findByEmail(String email);
}
