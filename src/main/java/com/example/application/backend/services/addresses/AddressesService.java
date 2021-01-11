package com.example.application.backend.services.addresses;

import com.example.application.backend.entities.AddressesEntity;
import com.example.application.backend.repositories.AddressRepository;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressesService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressesService(AddressRepository addressRepositiory){
        this.addressRepository = addressRepositiory;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public void save (AddressesEntity addressesEntity){
        getAddressRepository().saveAndFlush(addressesEntity);
    }

    public AddressesEntity findById (int addressId) {
        return getAddressRepository().findByAddressId(addressId);
    }
}
