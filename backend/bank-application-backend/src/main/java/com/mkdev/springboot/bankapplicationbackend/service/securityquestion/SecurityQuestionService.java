package com.mkdev.springboot.bankapplicationbackend.service.securityquestion;

import com.mkdev.springboot.bankapplicationbackend.entity.SecurityQuestion;

import java.util.List;

public interface SecurityQuestionService {
    List<SecurityQuestion> findAll();

    SecurityQuestion  findById(int theId);

    SecurityQuestion save(SecurityQuestion theSecurityQuestion);

    void deleteById(int theId);
}
