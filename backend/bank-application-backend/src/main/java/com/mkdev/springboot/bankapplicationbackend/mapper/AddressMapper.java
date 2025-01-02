package com.mkdev.springboot.bankapplicationbackend.mapper;

import com.mkdev.springboot.bankapplicationbackend.dto.register.RegisterAddressDTO;
import com.mkdev.springboot.bankapplicationbackend.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address mapToEntity(RegisterAddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setHouseNumber(addressDTO.getHouseNumber());
        address.setFlat(addressDTO.getFlat());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        return address;
    }

    public RegisterAddressDTO mapToDTO(Address address) {
        return new RegisterAddressDTO(
                address.getStreet(),
                address.getHouseNumber(),
                address.getFlat(),
                address.getPostalCode(),
                address.getCity(),
                address.getCountry()
        );
    }
}
