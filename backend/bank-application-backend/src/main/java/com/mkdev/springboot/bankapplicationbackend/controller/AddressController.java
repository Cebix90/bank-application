package com.mkdev.springboot.bankapplicationbackend.controller;

import com.mkdev.springboot.bankapplicationbackend.entity.Address;
import com.mkdev.springboot.bankapplicationbackend.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService theAddressService) {
        this.addressService = theAddressService;
    }

    @GetMapping("/addresses")
    public List<Address> findAllAddresses() {
        return addressService.findAll();
    }

    @GetMapping("/addresses/{addressId}")
    public Address getAddress (@PathVariable int addressId) {
        Address theAddress = addressService.findById(addressId);

        if(theAddress == null) {
            throw new RuntimeException("Address is not found: " + addressId);
        }

        return theAddress;
    }

    @PutMapping("/addresses")
    public Address updateAddress (@RequestBody Address theAddress) {
        Address dbAddress = addressService.save(theAddress);

        return dbAddress;
    }

    @DeleteMapping("/addresses/{addressId}")
    public String deleteAddress (@PathVariable int addressId) {
        Address theAddress = addressService.findById(addressId);

        if(theAddress == null) {
            throw new RuntimeException("Address is not found: " + addressId);
        }

        addressService.deleteById(addressId);

        return "Deleted address id: " + addressId;
    }
}
