package com.example.application.backend.utils.pdfs;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.files.DocumentService;
import com.example.application.backend.services.notifications.NotificationService;
import com.vaadin.flow.component.html.Div;

import java.util.ArrayList;
import java.util.List;


/**
 * This class manages the pdfs list and the pdfManagers.
 *
 * @author Anastasiya Jackwerth, Sabrine Gamdou
 * @version 4.0
 * @since 19.01.2021
 * @lastUpdated 24.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */

public class PdfsManager extends Div {

    NotificationService notificationService;
    DocumentService documentService;

    private List<DocumentEntity> documentEntities;
    private List<PDF> pdfs;

    private PdfManager pdfManager;


    private String keyword;
    private PageEntity pageEntity;
    private UserEntity userEntity;
    private int role;

    private boolean isOnePdf;

    private Div pdfsUploader;

    public PdfsManager(List<DocumentEntity> documentEntities, NotificationService notificationService,
                       DocumentService documentService, int role) {

        this.notificationService = notificationService;
        this.documentService = documentService;
        this.role = role;
        this.documentEntities = documentEntities;

        this.pdfs = new ArrayList<>();

    }

    //will be called by the view after setting list
    public void initializeAllPdfs() {
        pdfManager = new PdfManager(notificationService, documentService, role);
        pdfManager.setAllDocumentEntitiesData(keyword, pageEntity, userEntity);
        for (DocumentEntity documentEntity : documentEntities) {
            pdfManager.setDocumentEntity(documentEntity);
            pdfManager.setOnePdf(isOnePdf);
            pdfs.add(pdfManager.createPDF());
            pdfManager.setDeleteButtonEvent();
            System.out.println("CREATED PDF");
            System.out.println("PATH: " + documentEntity.getPath());
        }

        System.out.println("Pdfs List Size: " + documentEntities.size());
        if (documentEntities.size() > 1) {
            System.out.println("isOnePdf: " + isOnePdf);
            pdfManager.setOnePdf(isOnePdf);
        } else if (documentEntities.size() == 0) {
            System.out.println("isOnePdf: " + isOnePdf);
            pdfManager.setOnePdf(true);
        } else {
            System.out.println("isOnePdf: " + isOnePdf);
            pdfManager.setOnePdf(isOnePdf);
        }



    }


    //will be set by the view :: gets the list of the page.
    public void setDocumentEntities(List<DocumentEntity> documentEntities) {
        this.documentEntities = documentEntities;
    }

    //Set by the view: global data for all documentEntities
    public void setAllDocumentEntitiesData(String keyword, PageEntity pageEntity,
                                           UserEntity userEntity) {
        setKeyword(keyword);
        setPageEntity(pageEntity);
        setUserEntity(userEntity);
    }

    public void initializeUploadContainer() {
        pdfsUploader = new Div();
        pdfManager.setUploaderEvents();
        pdfsUploader.add(pdfManager.getUploaderContainer());
    }

    public void setOnePdf(boolean isOnePdf) {
        this.isOnePdf = !isOnePdf;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public void setPageEntity(PageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }

    public List<PDF> getPdfs() {
        return pdfs;
    }

    public void setPdfs(List<PDF> pdfs) {
        this.pdfs = pdfs;
    }

    public Div getPdfsUploader() {
        return pdfsUploader;
    }

    public PdfManager getPdfManager() {
        return pdfManager;
    }

}

