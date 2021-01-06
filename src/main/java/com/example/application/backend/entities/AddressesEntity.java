package com.example.application.backend.entities;

import javax.persistence.*;
import java.util.List;


/**
 * This is a basic address class.
 *
 * @author  Jessica Reistel, Laura Neuendorf and Sabrine Gamdou
 * @version 2.0
 * @since   21-12-2020
 * @lastUpdated 05.01.2021
 */

@Entity(name = "addresses")//name of the database table
public class AddressesEntity {

    @Id //specifies the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // specifies how the primary key gets generated
    @Column(name ="address_id")
    private int addressId;

    @Column(name ="streetname")
    private String streetName;

    @Column(name ="street_number")
    private int streetNumber;

    @Column(name ="postcode")
    private int postcode;

    private String city;

    @OneToMany(mappedBy = "address")
    private List<UsersEntity> users;

    public AddressesEntity() {

    }

    public AddressesEntity(int addressId, String streetName, int streetNumber, int postcode, String city) {
        this.addressId = addressId;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postcode = postcode;
        this.city = city;
    }

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

    public List<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UsersEntity> users) {
        this.users = users;
    }

}
