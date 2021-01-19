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
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 2.0
 * @since   21-12-2020
 * @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */

public class PdfsManager extends Div {

    NotificationService notificationService;
    DocumentService documentService;

    private List<DocumentEntity> documentEntities;
    private List<PDF> pdfs;

    private PdfManager pdfManager;

    //Variables to create a new documentEntity, should be set by the View
    private String keyword;
    private PageEntity pageEntity;
    private UserEntity userEntity;

    private boolean isOnePdf;

    public PdfsManager(List<DocumentEntity> documentEntities, NotificationService notificationService,
                       DocumentService documentService){

        this.notificationService = notificationService;
        this.documentService = documentService;

        this.documentEntities = documentEntities;

        this.pdfs = new ArrayList<>();

    }

    //will be called by the view after setting list
    public void initializeAllPdfs(){
        pdfManager = new PdfManager(notificationService, documentService);
        pdfManager.setAllDocumentEntitiesData(keyword,pageEntity,userEntity);
        for(DocumentEntity documentEntity : documentEntities){
            pdfManager.setDocumentEntity(documentEntity);
            pdfManager.setOnePdf(isOnePdf);
            pdfs.add(pdfManager.createPDF());
            pdfManager.setCancelButtonEvent();
            System.out.println("CREATED PDF");
            System.out.println("PATH: "+documentEntity.getPath());
        }

        System.out.println("Image List Size: " + documentEntities.size());
        if(documentEntities.size() > 1){
            System.out.println("isOneImage: " + isOnePdf);
            pdfManager.setOnePdf(isOnePdf);
        }else if(documentEntities.size() == 0){
            System.out.println("isOneImage: " + isOnePdf);
            pdfManager.setOnePdf(true);
        }
        else{
            System.out.println("isOneImage: " + isOnePdf);
            pdfManager.setOnePdf(isOnePdf);
        }
        readList();

        pdfManager.setUploaderEvents();
        this.add(pdfManager.getUploaderContainer());
    }


    //will be set by the view :: gets the list of the page.
    public void setDocumentEntities(List<DocumentEntity> documentEntities) {
        this.documentEntities = documentEntities;
    }

    //Set by the view: global data for all documentEntities
    public void setAllDocumentEntitiesData(String keyword, PageEntity pageEntity,
                                           UserEntity userEntity){
        setKeyword(keyword);
        setPageEntity(pageEntity);
        setUserEntity(userEntity);
    }

    public void setOnePdf(boolean isOnePdf){
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

    public void readList(){
        for(DocumentEntity d : pageEntity.getDocuments()){
            System.out.println(d.toString());
        }
    }


}

