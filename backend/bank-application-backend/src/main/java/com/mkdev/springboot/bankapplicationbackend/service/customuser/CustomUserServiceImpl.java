package com.mkdev.springboot.bankapplicationbackend.service.customuser;

import com.mkdev.springboot.bankapplicationbackend.dao.CustomUserRepository;
import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;
import com.mkdev.springboot.bankapplicationbackend.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserServiceImpl implements CustomUserService {
    private final CustomUserRepository customUserRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public CustomUserServiceImpl(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public List<CustomUser> findAll() {
        return customUserRepository.findAll();
    }

    @Override
    public CustomUser findById(int theId) {
        Optional<CustomUser> result = customUserRepository.findById(theId);

        CustomUser theCustomUser = null;

        if (result.isPresent()) {
            theCustomUser = result.get();
        } else {
            throw new RuntimeException("Custom User not found: " + theId);
        }

        return theCustomUser;
    }

    @Override
    public CustomUser addUser(CustomUser theCustomUser) {
        if (!userExists(theCustomUser)) {
            String hashedPassword = passwordEncoder.encode(theCustomUser.getPasswordHash());

            theCustomUser.setPasswordHash(hashedPassword);
            theCustomUser.setStatus("ACTIVE");

            return customUserRepository.save(theCustomUser);
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public CustomUser updateUser(CustomUser theCustomUser) {
        CustomUser existingUser = customUserRepository.findById(theCustomUser.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + theCustomUser.getUserId()));

        if (!existingUser.getEmail().equals(theCustomUser.getEmail()) && userExists(theCustomUser)) {
            throw new RuntimeException("Email already in use");
        }

        existingUser.setName(theCustomUser.getName());
        existingUser.setSurname(theCustomUser.getSurname());
        existingUser.setEmail(theCustomUser.getEmail());
        if (theCustomUser.getPasswordHash() != null && !theCustomUser.getPasswordHash().isBlank()) {
            existingUser.setPasswordHash(passwordEncoder.encode(theCustomUser.getPasswordHash()));
        }

        return customUserRepository.save(existingUser);
    }

    @Override
    public void deleteById(int theId) {
        customUserRepository.deleteById(theId);
    }

    private boolean userExists(CustomUser customUser) {
        return customUserRepository.findByEmail(customUser.getEmail()).isPresent();
    }
}
