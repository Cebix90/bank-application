package com.mkdev.springboot.bankapplicationbackend.controller;

import com.mkdev.springboot.bankapplicationbackend.entity.SecurityQuestion;
import com.mkdev.springboot.bankapplicationbackend.service.securityquestion.SecurityQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SecurityQuestionController {
    private final SecurityQuestionService securityQuestionService;

    @Autowired
    public SecurityQuestionController(SecurityQuestionService theSecurityQuestionService) {
        this.securityQuestionService = theSecurityQuestionService;
    }

    @GetMapping("/security-questions")
    public List<SecurityQuestion> findAllSecurityQuestions() {
        return securityQuestionService.findAll();
    }

    @GetMapping("/security-questions/{questionId}")
    public SecurityQuestion getSecurityQuestion (@PathVariable int questionId) {
        SecurityQuestion theSecurityQuestion = securityQuestionService.findById(questionId);

        if(theSecurityQuestion == null) {
            throw new RuntimeException("Security question is not found: " + questionId);
        }

        return theSecurityQuestion;
    }

    @PostMapping("/security-questions")
    public SecurityQuestion addSecurityQuestion (@RequestBody SecurityQuestion theSecurityQuestion) {
        theSecurityQuestion.setQuestionId(0);

        SecurityQuestion dbSecurityQuestion = securityQuestionService.save(theSecurityQuestion);

        return dbSecurityQuestion;
    }

    @PutMapping("/security-questions")
    public SecurityQuestion updateSecurityQuestion (@RequestBody SecurityQuestion theSecurityQuestion) {
        SecurityQuestion dbSecurityQuestion = securityQuestionService.save(theSecurityQuestion);

        return dbSecurityQuestion;
    }

    @DeleteMapping("/security-questions/{questionId}")
    public String deleteSecurityQuestion (@PathVariable int questionId) {
        SecurityQuestion theSecurityQuestion = securityQuestionService.findById(questionId);

        if(theSecurityQuestion == null) {
            throw new RuntimeException("Security question is not found: " + questionId);
        }

        securityQuestionService.deleteById(questionId);

        return "Deleted security question id: " + questionId;
    }
}
