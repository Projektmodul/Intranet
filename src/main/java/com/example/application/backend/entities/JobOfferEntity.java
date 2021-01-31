package com.example.application.backend.entities;

import javax.persistence.*;


/**
 * This is a basic jobOffer class.
 *
 * @author  Sabrine Gamdou
 * @version 3.0
 * @since   05.01.2020
 * @lastUpdated 31.01.2021 from Litharshi ga Sivarasa
 */

@Entity(name="job_offers")
public class JobOfferEntity implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="job_offer_id")
    private int jobOfferId;

    @Column(name ="file_name")
    private String fileName;
    private String path;
    private String keyword;

    @Column(name ="title")
    private String title;
    private String category;
    private String type;
    private String location;

    @ManyToOne
    @JoinColumn(name ="page_id")
    private PageEntity page;

   /* @ManyToOne
    @JoinColumn(name ="username")
    private UserEntity user;*/

    /* @OneToOne
    @JoinColumn(name="document_id")
    private DocumentEntity document;

    @ManyToOne
    @JoinColumn(name ="username")
    private UsersEntity user;*/
    public  JobOfferEntity(){

    }
    public JobOfferEntity(String fileName, String path, String keyword,
                          PageEntity page,String title,String category, String type, String location) {
        this.fileName = fileName;
        this.path = path;
        this.keyword = keyword;
        this.page = page;
    }

    public int getJobOfferId() {
        return jobOfferId;
    }

    public void setJobOfferId(int jobOfferId) {
        this.jobOfferId = jobOfferId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


   /* public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
*/
}
