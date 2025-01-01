package com.mkdev.springboot.bankapplicationbackend.service.securityservice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserSecurityService extends UserDetailsService {
    UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException;
}
