package com.example.application.backend.entities;

public class UsersEntity {
    private int id;
    private String username;
    private String password;
    private String surname;
    private String firstname;
    private int addressId;
    private String iban;
    private char center;
    private int roomnumber;
    private int telefon;
    private String email;
    private String jobDescription;

    public UsersEntity() {

    }

    public UsersEntity(int id, String firstname, String surname, int addressId, String iban, char center, int roomnumber, int telefon, String email, String jobDescription) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.addressId = addressId;
        this.iban = iban;
        this.center = center;
        this.roomnumber = roomnumber;
        this.telefon = telefon;
        this.email = email;
        this.jobDescription = jobDescription;
    }

    public String getId() {
        return "" + id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddressId() {
        return "" + addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getCenter() {
        return "" + center;
    }

    public void setCenter(char center) {
        this.center = center;
    }

    public String getRoomnumber() {
        return "" + roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getTelefon() {
        return "" + telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
