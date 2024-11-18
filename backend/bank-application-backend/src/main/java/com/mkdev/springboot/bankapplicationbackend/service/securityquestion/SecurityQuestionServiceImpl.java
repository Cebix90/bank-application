package com.mkdev.springboot.bankapplicationbackend.service.securityquestion;

import com.mkdev.springboot.bankapplicationbackend.dao.SecurityQuestionRepository;
import com.mkdev.springboot.bankapplicationbackend.entity.SecurityQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityQuestionServiceImpl implements SecurityQuestionService {
    private final SecurityQuestionRepository securityQuestionRepository;

    @Autowired
    public SecurityQuestionServiceImpl(SecurityQuestionRepository securityQuestionRepository) {
        this.securityQuestionRepository = securityQuestionRepository;
    }

    @Override
    public List<SecurityQuestion> findAll() {
        return securityQuestionRepository.findAll();
    }

    @Override
    public SecurityQuestion findById(int theId) {
        Optional<SecurityQuestion> result = securityQuestionRepository.findById(theId);

        SecurityQuestion theSecurityQuestion = null;

        if (result.isPresent()) {
            theSecurityQuestion = result.get();
        } else {
            throw new IllegalArgumentException("Security Question not found: " + theId);
        }

        return theSecurityQuestion;
    }

    @Override
    public SecurityQuestion save(SecurityQuestion theSecurityQuestion) {
        return securityQuestionRepository.save(theSecurityQuestion);
    }

    @Override
    public void deleteById(int theId) {
        securityQuestionRepository.deleteById(theId);
    }
}
