package com.example.application.backend.entities;

import javax.persistence.*;
import java.util.List;


/**
 * This is a basic user class.
 *
 * @author  Jessica Reistel, Laura Neuendorf and Sabrine Gamdou
 * @version 3.0
 * @since   21-12-2020
 * @lastUpdated 05.01.2021
 */

@Entity(name= "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private int id;

    private String username;
    private String password;
    private String surname;
    private String firstname;

    @ManyToOne
    @JoinColumn(name ="address_id")
    private AddressesEntity address;

    private String iban;
    private char center;

    @Column(name ="room_number")
    private int roomNumber;

    @Column(name ="telephone_number")
    private int telephoneNumber;

    private String email;

    @Column(name ="job_description")
    private String jobDescription;

    @ManyToMany
    @JoinTable( //this defines the relationship and the foreign key columns
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RolesEntity> roles;

    @OneToMany(mappedBy = "user")
    private List<DocumentEntity> documents;

    @OneToMany(mappedBy = "user")
    private List<ImageEntity> images;

    @OneToMany(mappedBy = "user")
    private List<LinkEntity> links;

    @OneToMany(mappedBy = "user")
    private List<PageEntity> pages;

    @OneToMany(mappedBy = "user")
    private List<IdeaEntity> ideas;

    @OneToMany(mappedBy = "user")
    private List<JobOfferEntity> jobOffers;

    @OneToOne
    @JoinColumn(name="setting_id")
    private SettingsEntity settings;

    public List<ImageEntity> getImages() {
        return images;
    }

    public UsersEntity() {

    }

    public UsersEntity(int id, String firstname, String surname, AddressesEntity address, String iban, char center, int roomNumber, int telephoneNumber, String email, String jobDescription) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.address = address;
        this.iban = iban;
        this.center = center;
        this.roomNumber = roomNumber;
        this.telephoneNumber = telephoneNumber;
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

    public AddressesEntity getAddress() {
        return address;
    }

    public void setAddress(AddressesEntity address) {
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

    public List<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEntity> roles) {
        this.roles = roles;
    }

    public List<DocumentEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentEntity> documents) {
        this.documents = documents;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public List<LinkEntity> getLinks() {
        return links;
    }

    public void setLinks(List<LinkEntity> links) {
        this.links = links;
    }

    public List<PageEntity> getPages() {
        return pages;
    }

    public void setPages(List<PageEntity> pages) {
        this.pages = pages;
    }

    public List<IdeaEntity> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<IdeaEntity> ideas) {
        this.ideas = ideas;
    }

    public List<JobOfferEntity> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOfferEntity> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public SettingsEntity getSettings() {
        return settings;
    }

    public void setSettings(SettingsEntity settings) {
        this.settings = settings;
    }

}
