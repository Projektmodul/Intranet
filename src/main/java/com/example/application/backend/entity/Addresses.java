package com.example.application.backend.entity;

public class Addresses {
    private int addressId;
    private String streetname;
    private int streetnumber;
    private int postcode;
    private String city;

    public Addresses() {

    }

    public Addresses(int addressId, String streetname, int streetnumber, int postcode, String city) {
        this.addressId = addressId;
        this.streetname = streetname;
        this.streetnumber = streetnumber;
        this.postcode = postcode;
        this.city = city;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddress_id(int addressId) {
        this.addressId = addressId;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public int getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(int streetnumber) {
        this.streetnumber = streetnumber;
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
