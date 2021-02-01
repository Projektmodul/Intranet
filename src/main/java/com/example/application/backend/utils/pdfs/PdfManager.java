package com.example.application.backend.utils.pdfs;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.entities.NotificationEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.files.DocumentService;
import com.example.application.backend.services.notifications.NotificationService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.InputStream;
import java.util.Date;


/**
 * This is class manages the upload and pdf components and uses the
 * pdfCreationManager and the pdfDeletionManager to create and delete documents.
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 4.0
 * @since   19.01.2021
 * @lastUpdated 24.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */
public class PdfManager {

    NotificationService notificationService;
    DocumentService documentService;



    private DocumentEntity documentEntity;
    private PDF pdf;

    private PdfCreationManager pdfCreationManager;
    private PdfDeletionManager pdfDeletionManager;

    private Upload upload;
    private MemoryBuffer buffer;

    private InputStream inputStream;

    private Div uploaderContainer;

    private Div errorContainer;

    private boolean isOnePdf;
    private int role;

    private String keyword;
    private PageEntity pageEntity;
    private UserEntity userEntity;



    /*PDF files path on the web server to save the files to*/
    private final String RESOURCES_DIR = "~/uploads";

    public PdfManager(NotificationService notificationService,
                      DocumentService documentService, int role){
        this.notificationService = notificationService;
        this.documentService = documentService;
        this.role = role;

        initializeUploader();
    }

    public PDF createPDF(){
        pdf = new PDF(documentEntity, inputStream, role);
        pdf.setHeight("55rem");
        return pdf;
    }

    private void initializeUploader(){
        buffer = new MemoryBuffer();
        upload = new Upload(buffer);
        uploaderContainer = new Div();

        errorContainer = new Div();
        uploaderContainer.add(errorContainer,upload);
    }

    public void setUploaderEvents(){
        upload.setAcceptedFileTypes("application/pdf");

        NativeButton uploadButton = new NativeButton("PDF-Datei hochladen");
        upload.setUploadButton(uploadButton);

        Span label = new Span("Ziehen Sie die Datei per Drag & Drop hierher!");
        upload.setDropLabel(label);

        upload.setVisible(isOnePdf);
        System.out.println("isOnePdf: " + isOnePdf);
        upload.addSucceededListener(e -> {
            inputStream = buffer.getInputStream();
            createDocumentEntity(changeGlobalFileName(e.getFileName()));
            documentEntity.setKeyword(keyword);
            pdfCreationManager = new PdfCreationManager(inputStream, documentEntity, documentService);
            pdfCreationManager.setMimeType(e.getMIMEType());
            pdfCreationManager.save();
            UI.getCurrent().getPage().reload();
        });

        upload.addFileRejectedListener(e -> errorContainer.add(new Span(e.getErrorMessage())));

        upload.addFailedListener(e -> errorContainer.add(new Span("Hochladen der Datei fehlgeschlagen")));
    }

    public void setDeleteButtonEvent(){
        pdf.getDeleteButton().addClickListener(e -> {
            pdfDeletionManager.delete();
            UI.getCurrent().getPage().reload();

        });
    }

    public void createDocumentEntity(String fileName){
        documentEntity = new DocumentEntity(fileName,createPath(fileName),keyword,
                pageEntity,initializeNotificationForDocument(fileName) ,userEntity);
    }


    public String changeGlobalFileName(String pdfFileName){
        return new Date().getTime() + "-" + pdfFileName;
    }

    //Data set by the PDFsManager
    public void setAllDocumentEntitiesData(String keyword, PageEntity pageEntity,
                                           UserEntity userEntity){
        setKeyword(keyword);
        setPageEntity(pageEntity);
        setUserEntity(userEntity);
    }

    //Categories in database should be changed
    public NotificationEntity initializeNotificationForDocument(String fileName){

        NotificationEntity notificationEntity = new NotificationEntity("Es wurde eine neue Datei hinzugefügt!",
                "Die Datei '"+fileName + "' wurde vor kurzem hinzugefügt.",
                "Speiseplan", false, userEntity);
        notificationEntity.setDate();

        notificationService.save(notificationEntity);

        return notificationEntity;
    }

    public void setOnePdf(boolean isOnePdf){
        this.isOnePdf = isOnePdf;
    }

    public String createPath(String fileName){
        return RESOURCES_DIR + fileName;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setPageEntity(PageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    public Div getUploaderContainer() {
        return uploaderContainer;
    }

    public DocumentEntity getDocumentEntity() {
        return documentEntity;
    }

    public void setDocumentEntity(DocumentEntity documentEntity) {
        this.documentEntity = documentEntity;
        this.pdfDeletionManager = new PdfDeletionManager(documentEntity, documentService);
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
