package com.mkdev.springboot.bankapplicationbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private int addressId;

    @Column(nullable = false)
    private String street;

    @Column(name="house_number", nullable = false)
    private String houseNumber;

    private int flat;

    @Column(name="postal_code", nullable = false)
    private int postalCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    @JsonBackReference
    private CustomUser users;

    public Address() {}

    public Address(String street, String houseNumber, int flat, int postalCode, String city, String country, CustomUser users) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.flat = flat;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.users = users;
    }
}
