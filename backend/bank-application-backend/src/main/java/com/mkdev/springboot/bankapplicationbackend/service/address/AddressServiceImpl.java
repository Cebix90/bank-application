package com.mkdev.springboot.bankapplicationbackend.service.address;

import com.mkdev.springboot.bankapplicationbackend.dao.AddressRepository;
import com.mkdev.springboot.bankapplicationbackend.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(int theId) {
        Optional<Address> result = addressRepository.findById(theId);

        Address theAddress = null;

        if (result.isPresent()) {
            theAddress = result.get();
        } else {
            throw new RuntimeException("Address not found: " + theId);
        }

        return theAddress;
    }

    @Override
    public Address save(Address theAddress) {
        return addressRepository.save(theAddress);
    }

    @Override
    public void deleteById(int theId) {
        addressRepository.deleteById(theId);
    }
}
