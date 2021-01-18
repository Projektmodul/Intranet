package com.example.application.backend.utils.pdfs;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.entities.NotificationEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.documents.DocumentService;
import com.example.application.backend.services.notifications.NotificationService;


/**
 * This class is for creating, deleting or updating a file/document added in the database.
 * It also creates notification once the document is added.
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   18.01.2021
 * @lastUpdated  18.01.2021
 */


public class DatabaseDocumentManager {

    private NotificationService notificationService;
    private DocumentService documentService;

    private String notificationCategorie;
    private String documentType;
    private UserEntity user;


    private String fileName;

    public void savePdfDataInDatabase(DocumentEntity documentEntity){
        this.documentService.save(documentEntity);
    }

    public void deletePdfDataFromDatabase(DocumentEntity documentEntity){
        try{
            this.documentService.delete(documentEntity);
        }catch(NullPointerException e)
        {
            System.out.println("No such file exists.");
        }
    }

    public DocumentEntity initializeDocumentForDatabase(String fileName, String path,
                                                         String keyword, PageEntity pageEntity,
                                                         NotificationEntity notificationEntity){
        return new DocumentEntity(fileName,path,keyword,pageEntity, notificationEntity,user);
    }

    public NotificationEntity initializeNotificationForDocument(){

        NotificationEntity notificationEntity = new NotificationEntity("Es wurde eine neue Datei hinzugefügt!",
                "Die Datei '"+fileName + "' wurde vor kurzem hinzugefügt.",
                notificationCategorie, false, user);
        notificationEntity.setDate();

        notificationService.save(notificationEntity);

        return notificationEntity;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public String getNotificationCategorie() {
        return notificationCategorie;
    }

    public void setNotificationCategorie(String notificationCategorie) {
        this.notificationCategorie = notificationCategorie;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
