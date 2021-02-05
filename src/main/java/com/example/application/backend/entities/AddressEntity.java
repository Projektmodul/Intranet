package com.example.application.backend.entities;

import javax.persistence.*;

/**
 * This is a basic address class.
 *
 * @author  Jessica Reistel, Laura Neuendorf, and Sabrine Gamdou
 * @version 3.0
 * @since   21-12-2020
 * @lastUpdated 25.01.2021 from Jessica Reistel, Monika Martius and Laura Neuendorf
 */

/**
 * Name of the database table
 */
@Entity(name = "addresses")
public class AddressEntity {

    /**
     * Specifies the primary key of the table
     */
    @Id
    /**
     * Specifies how the primary key gets generated
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * If the name in the database and the variable name in the code are different,
     * they must be marked with the following tag
     */
    @Column(name ="address_id")
    private int addressId;

    @Column(name ="streetname")
    private String streetName;

    @Column(name ="street_number")
    private int streetNumber;

    @Column(name ="postcode")
    private int postcode;

    private String city;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetname) {
        this.streetName = streetname;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetnumber) {
        this.streetNumber = streetnumber;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
