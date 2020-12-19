package com.example.application.backend.entity;

public class Addresses {
    private int address_id;
    private String streetname;
    private int streetnumber;
    private int postcode;
    private String city;

    public Addresses() {

    }

    public Addresses(int address_id, String streetname, int streetnumber, int postcode, String city) {
        this.address_id = address_id;
        this.streetname = streetname;
        this.streetnumber = streetnumber;
        this.postcode = postcode;
        this.city = city;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
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
