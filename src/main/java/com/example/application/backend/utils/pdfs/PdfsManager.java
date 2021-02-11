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
 * @version 5.0
 * @since 19.01.2021
 * @lastUpdated 01.02.2021 by Anastasiya Jackwerth, Sabrine Gamdou
 */
public class PdfsManager extends Div {

    private NotificationService notificationService;
    private DocumentService documentService;

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

    /**
     * This method creates a documentManager with a given documentService and a role, sets the entities of the documentManager,
     * creates an PDF component (not Vaadin, see PDF.java file) out of the documentEntities, these documents are then
     * added to a documents container. Depending the size of the documentEntities-List the isOnePdf will be set to true
     * or false
     */
    public void initializeAllPdfs(){
        pdfManager = new PdfManager(notificationService, documentService, role);
        pdfManager.setAllDocumentEntitiesData(keyword, pageEntity, userEntity);
        for (DocumentEntity documentEntity : documentEntities){
            pdfManager.setDocumentEntity(documentEntity);
            pdfManager.setOnePdf(isOnePdf);
            pdfs.add(pdfManager.createPDF());
            pdfManager.setDeleteButtonEvent();
        }
        if (documentEntities.size() > 1){
            pdfManager.setOnePdf(isOnePdf);
        } else if (documentEntities.size() == 0){
            pdfManager.setOnePdf(true);
        } else{
            pdfManager.setOnePdf(isOnePdf);
        }
    }

    public void setDocumentEntities(List<DocumentEntity> documentEntities){
        this.documentEntities = documentEntities;
    }

    public void setAllDocumentEntitiesData(String keyword, PageEntity pageEntity,
                                           UserEntity userEntity){
        setKeyword(keyword);
        setPageEntity(pageEntity);
        setUserEntity(userEntity);
    }

    public void initializeUploadContainer(){
        pdfsUploader = new Div();
        pdfManager.setUploaderEvents();
        pdfsUploader.add(pdfManager.getUploaderContainer());
    }

    public void initializeUploadContainerForJobOffers(){
        pdfsUploader = new Div();
        pdfManager.setUploaderEventForJobOffers();
        pdfsUploader.add(pdfManager.getUploaderContainer());
    }

    public void setOnePdf(boolean isOnePdf){
        this.isOnePdf = !isOnePdf;
    }

    public void setUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

    public void setPageEntity(PageEntity pageEntity){
        this.pageEntity = pageEntity;
    }

    public List<PDF> getPdfs(){
        return pdfs;
    }

    public Div getPdfsUploader(){
        return pdfsUploader;
    }

    public PdfManager getPdfManager(){
        return pdfManager;
    }

    public int getDocumentOfJobOfferId(){
        return this.pdfManager.getDocumentOfJobOfferId();
    }

    public boolean isPdfUploaded(){
        return pdfManager.isPdfUploaded();
    }
}

