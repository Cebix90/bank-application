package com.mkdev.springboot.bankapplicationbackend.service.address;

import com.mkdev.springboot.bankapplicationbackend.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();

    Address  findById(int theId);

    Address save(Address theAddress);

    void deleteById(int theId);
}
