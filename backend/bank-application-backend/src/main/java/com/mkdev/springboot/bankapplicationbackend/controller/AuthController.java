package com.mkdev.springboot.bankapplicationbackend.controller;

import com.mkdev.springboot.bankapplicationbackend.config.JwtTokenProvider;
import com.mkdev.springboot.bankapplicationbackend.dao.CustomUserRepository;
import com.mkdev.springboot.bankapplicationbackend.dto.login.LoginRequestCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.dto.login.LoginResponseCustomUserDTO;
import com.mkdev.springboot.bankapplicationbackend.dto.register.RegisterUserWithAddressDTO;
import com.mkdev.springboot.bankapplicationbackend.entity.Address;
import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;
import com.mkdev.springboot.bankapplicationbackend.exception.UserAlreadyExistsException;
import com.mkdev.springboot.bankapplicationbackend.mapper.AddressMapper;
import com.mkdev.springboot.bankapplicationbackend.mapper.CustomUserMapper;
import com.mkdev.springboot.bankapplicationbackend.service.customuser.CustomUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin()
public class AuthController {
    private final CustomUserRepository customUserRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserMapper customUserMapper;
    private final AddressMapper addressMapper;
    private final CustomUserService customUserService;

    @Autowired
    public AuthController(CustomUserRepository theCustomUserRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, CustomUserMapper customUserMapper, AddressMapper addressMapper, CustomUserService customUserService) {
        this.customUserRepository = theCustomUserRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserMapper = customUserMapper;
        this.addressMapper = addressMapper;
        this.customUserService = customUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserWithAddressDTO registerUserWithAddressDTO) {
        try {
            CustomUser userEntity = customUserMapper.mapRequestToEntity(registerUserWithAddressDTO.getUser());
            Address addressEntity = addressMapper.mapToEntity(registerUserWithAddressDTO.getAddress());
            CustomUser registeredUser = customUserService.registerUserWithAddress(userEntity, addressEntity);

            return ResponseEntity.ok("User registered successfully with ID: " + registeredUser.getUserId());
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
