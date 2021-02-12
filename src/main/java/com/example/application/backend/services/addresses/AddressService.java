package com.example.application.backend.services.addresses;

import com.example.application.backend.entities.AddressEntity;
import com.example.application.backend.repositories.AddressRepository;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is service class for the entity address.
 * The service layer processes requests from the UI layer.
 * @author  Jessica Reistel and Laura Neuendorf
 * @version 2.0
 * @since   11.01.2021
 * @lastUpdated 12.01.2021
 */

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    /**
     * This method updates the entered information from the MyProfilView in the database
     * @param addressEntity
     * @param updateStreet
     * @param updateNumber
     * @param updatePostcode
     * @param updateCity
     */
    public void update(AddressEntity addressEntity, TextField updateStreet, IntegerField updateNumber,
                       IntegerField updatePostcode, TextField updateCity){
        addressEntity.setStreetName(updateStreet.getValue());
        addressEntity.setStreetNumber(updateNumber.getValue());
        addressEntity.setPostcode(updatePostcode.getValue());
        addressEntity.setCity(updateCity.getValue());
        getAddressRepository().saveAndFlush(addressEntity);
    }
}
