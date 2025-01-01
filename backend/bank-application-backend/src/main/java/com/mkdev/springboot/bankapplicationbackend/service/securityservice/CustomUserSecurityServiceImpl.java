package com.mkdev.springboot.bankapplicationbackend.service.securityservice;

import com.mkdev.springboot.bankapplicationbackend.dao.CustomUserRepository;
import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserSecurityServiceImpl implements CustomUserSecurityService {
    private final CustomUserRepository customUserRepository;

    public CustomUserSecurityServiceImpl(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        CustomUser customUser = customUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        return User.withUsername(customUser.getEmail())
                .password(customUser.getPasswordHash())
                .authorities("USER")
                .build();
    }
}
