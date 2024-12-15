package com.mkdev.springboot.bankapplicationbackend.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class SecurityQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_id")
    private int questionId;

    @Column(nullable=false)
    private String question;

    @Column(name="answer_hash", nullable=false)
    private String answerHash;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private CustomUser customUser;

    public SecurityQuestion() {}

    public SecurityQuestion(String question, String answerHash, CustomUser customUser) {
        this.question = question;
        this.answerHash = answerHash;
        this.customUser = customUser;
    }
}
