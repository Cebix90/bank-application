package com.mkdev.springboot.bankapplicationbackend.service.customuser;

import com.mkdev.springboot.bankapplicationbackend.dao.CustomUserRepository;
import com.mkdev.springboot.bankapplicationbackend.entity.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserServiceImpl implements CustomUserService {
    private final CustomUserRepository customUserRepository;

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
    public CustomUser save(CustomUser theCustomUser) {
        return customUserRepository.save(theCustomUser);
    }

    @Override
    public void deleteById(int theId) {
        customUserRepository.deleteById(theId);
    }
}
