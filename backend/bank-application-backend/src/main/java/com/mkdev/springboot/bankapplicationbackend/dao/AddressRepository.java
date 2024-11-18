package com.mkdev.springboot.bankapplicationbackend.dao;

import com.mkdev.springboot.bankapplicationbackend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
