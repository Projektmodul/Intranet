package com.example.application.backend.entities;

import javax.persistence.*;
import java.util.List;


/**
 * This is a basic user class.
 *
 * @author  Jessica Reistel, Laura Neuendorf and Sabrine Gamdou
 * @version 7.0
 * @since   21-12-2020
 * @lastUpdated 02.02.2021 from Marieke Menna de Boer, Lea Schünemann
 */

@Entity(name= "users")
public class UserEntity {

    @Id
    private String username;

    private String password;

    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name ="first_name")
    private String firstName;

    @ManyToOne
    @JoinColumn(name ="address_id")
    private AddressEntity address;

    private String iban;
    private char center;

    @Column(name ="room_number")
    private int roomNumber;

    @Column(name ="telephone_number")
    private int telephoneNumber;

    @Column(name ="job_description")
    private String jobDescription;

    @Column(name = "securityquestion")
    private String securityQuestion;

    @Column(name = "securityanswer")
    private String securityAnswer;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToMany(mappedBy = "user")
    private List<PageEntity> pages;

    @OneToMany(mappedBy = "user")
    private List<NotificationEntity> notifications;

    @OneToOne
    @JoinColumn(name="setting_id")
    private SettingEntity settings;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
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

    public String getRoomNumber() {
        return "" + roomNumber;
    }

    public void setRoomNumber(int roomnumber) {
        this.roomNumber = roomnumber;
    }

    public String getTelephoneNumber() {
        return "" + telephoneNumber;
    }

    public void setTelephoneNumber(int telefon) {
        this.telephoneNumber = telefon;
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

    public List<PageEntity> getPages() {
        return pages;
    }

    public void setPages(List<PageEntity> pages) {
        this.pages = pages;
    }

    public String getSecurityQuestion() {return securityQuestion;}

    public void setSecurityQuestion(String securityQuestion) {this.securityQuestion = securityQuestion;}

    public String getSecurityAnswer() {return securityAnswer;}

    public void setSecurityAnswer(String securityAnswer) {this.securityAnswer = securityAnswer;}

    public RoleEntity getRole() { return role; }

    public void setRole(RoleEntity role) { this.role = role; }

    public SettingEntity getSetting() {
        return settings;
    }

    public void setSetting(SettingEntity settings) {
        this.settings = settings;
    }
}