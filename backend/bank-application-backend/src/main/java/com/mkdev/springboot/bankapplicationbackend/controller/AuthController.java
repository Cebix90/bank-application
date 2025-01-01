package com.mkdev.springboot.bankapplicationbackend.controller;

import com.mkdev.springboot.bankapplicationbackend.config.JwtTokenProvider;
import com.mkdev.springboot.bankapplicationbackend.dao.CustomUserRepository;
import com.mkdev.springboot.bankapplicationbackend.dto.LoginRequestCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.dto.LoginResponseCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.dto.RegisterCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;
import com.mkdev.springboot.bankapplicationbackend.exception.UserAlreadyExistsException;
import com.mkdev.springboot.bankapplicationbackend.mapper.CustomUserMapper;
import com.mkdev.springboot.bankapplicationbackend.service.customuser.CustomUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final CustomUserRepository customUserRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserMapper customUserMapper;
    private final CustomUserService customUserService;

    @Autowired
    public AuthController(CustomUserRepository theCustomUserRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, CustomUserMapper customUserMapper, CustomUserService customUserService) {
        this.customUserRepository = theCustomUserRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserMapper = customUserMapper;
        this.customUserService = customUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterCustomUserDTO registerCustomUserDTO) {
        try {
            CustomUser mappedCustomUser = customUserMapper.mapRequestToEntity(registerCustomUserDTO);
            mappedCustomUser.setUserId(0);

            CustomUser dbCustomUser = customUserService.addUser(mappedCustomUser);

            customUserRepository.save(dbCustomUser);
            return ResponseEntity.ok("User registered successfully");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestCustomUserDTO loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            CustomUser user = customUserRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtTokenProvider.generateToken(loginRequest.getEmail());

            return ResponseEntity.ok(new LoginResponseCustomUserDTO(token, user.getUserId()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
