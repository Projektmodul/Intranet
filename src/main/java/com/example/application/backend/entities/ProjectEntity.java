package com.example.application.backend.entities;


import javax.persistence.*;

/**
 * This is a basic project class.
 *
 * @author  Monika Martius and Laura Neuendorf
 * @version 1.0
 * @since   26.01.2021
 */

@Entity(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private int projectId;

    @Column(name = "titel_text_box_one")
    private String titelTextBoxOne;

    @Column(name = "titel_text_box_two")
    private String titelTextBoxTwo;

    @Column(name = "text_box_one")
    private String textBoxOne;

    @Column(name = "text_box_two")
    private String textBoxTwo;

    @OneToOne
    @JoinColumn(name = "image_id_one")
    private ImageEntity imageOne;

    @OneToOne
    @JoinColumn(name = "image_id_two")
    private ImageEntity imageTwo;

    @Column(name = "link_id_one")
    private int linkOne;

    @Column(name = "link_id_two")
    private int linkTwo;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitelTextBoxOne() {
        return titelTextBoxOne;
    }

    public void setTitelTextBoxOne(String titelTextBoxOne) {
        this.titelTextBoxOne = titelTextBoxOne;
    }

    public String getTitelTextBoxTwo() {
        return titelTextBoxTwo;
    }

    public void setTitelTextBoxTwo(String titelTextBoxTwo) {
        this.titelTextBoxTwo = titelTextBoxTwo;
    }

    public String getTextBoxOne() {
        return textBoxOne;
    }

    public void setTextBoxOne(String textBoxOne) {
        this.textBoxOne = textBoxOne;
    }

    public String getTextBoxTwo() {
        return textBoxTwo;
    }

    public void setTextBoxTwo(String textBoxTwo) {
        this.textBoxTwo = textBoxTwo;
    }

    public ImageEntity getImageOne() {
        return imageOne;
    }

    public void setImageOne(ImageEntity imageOne) {
        this.imageOne = imageOne;
    }

    public ImageEntity getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(ImageEntity imageTwo) {
        this.imageTwo = imageTwo;
    }

    public int getLinkOne(){
        return linkOne;
    }

    public void setLinkOne(int linkOne) {
        this.linkOne = linkOne;
    }

     public int getLinkTwo() {
        return linkTwo;
    }

    public void setLinkTwo(int linkTwo) {
        this.linkTwo = linkTwo;
    }
}
