package com.mkdev.springboot.bankapplicationbackend.dao;

import com.mkdev.springboot.bankapplicationbackend.entity.SecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, Integer> {
}
