package com.example.application.backend.services.addresses;

import com.example.application.backend.entities.AddressEntity;
import com.example.application.backend.repositories.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepositiory){
        this.addressRepository = addressRepositiory;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public void save (AddressEntity addressEntity){
        getAddressRepository().saveAndFlush(addressEntity);
    }

    public AddressEntity findById (int addressId) {
        return getAddressRepository().findByAddressId(addressId);
    }
}
