package com.example.application.backend.services.addresses;

import com.example.application.backend.entities.AddressEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.repositories.AddressRepository;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DESCRIPTION
 *
 * @author  Jessica Reistel and Laura Neuendorf
 * @version 2.0
 * @since   11.01.2021
 * @lastUpdated 12.01.2021
 */

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

    /*public AddressEntity findById (int addressId) {
        return getAddressRepository().findByAddressId(addressId);
    }*/

    public void update(AddressEntity addressEntity, TextField updateStreet, IntegerField updateNumber,
                       IntegerField updatePostcode, TextField updateCity){
        addressEntity.setStreetName(updateStreet.getValue());
        addressEntity.setStreetNumber(updateNumber.getValue());
        addressEntity.setPostcode(updatePostcode.getValue());
        addressEntity.setCity(updateCity.getValue());
        getAddressRepository().saveAndFlush(addressEntity);
    }
}
