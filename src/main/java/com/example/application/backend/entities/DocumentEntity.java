package com.example.application.backend.entities;

import javax.persistence.*;


/**
 * This is a basic document class.
 *
 * @author  Sabrine Gamdou
 * @version 2.0
 * @since   05.01.2020
 * @lastUpdated 19.01.2021
 */

@Entity(name = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="document_id")
    private int documentId;

    @Column(name ="file_name")
    private String fileName;

    private String path;

    private String keyword;

    @ManyToOne
    @JoinColumn(name ="page_id")
    private PageEntity page;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="notification_id")
    private NotificationEntity notification;

   /* @OneToOne(mappedBy = "document")
    private JobOfferEntity jobOffer;

   */
   public DocumentEntity() {

   }

    public DocumentEntity(String fileName, String path, String keyword,
                          PageEntity page, NotificationEntity notification,
                          UserEntity user) {
        this.fileName = fileName;
        this.path = path;
        this.keyword = keyword;
        this.page = page;
        this.notification = notification;
        this.user = user;
    }


    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

   /* public JobOfferEntity getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOfferEntity jobOffer) {
        this.jobOffer = jobOffer;
    }

  */
   public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public NotificationEntity getNotification() {
        return notification;
    }

    public void setNotification(NotificationEntity notification) {
        this.notification = notification;
    }


}
