package com.mkdev.springboot.bankapplicationbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @NotBlank(message = "Name cannot be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Column(nullable = false)
    private String surname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password hash cannot be blank")
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    private String status;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

//    @OneToOne
//    @Column(name="card_id")
//    private Card card;
//
    @OneToMany(mappedBy = "customUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SecurityQuestion> securityQuestions = new ArrayList<>();

    public CustomUser() {}

    public CustomUser(String name, String surname, String email, String passwordHash, LocalDateTime createdAt, LocalDateTime updatedAt, String status, Address address, List<SecurityQuestion> securityQuestions) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.address = address;
        this.securityQuestions = securityQuestions;
    }
}
