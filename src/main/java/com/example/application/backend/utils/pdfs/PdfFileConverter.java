package com.example.application.backend.utils.pdfs;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.documents.DocumentService;
import com.example.application.backend.services.notifications.NotificationService;
import com.example.application.backend.utils.interfaces.Convertible;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.server.StreamResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * This class has the necessary functions to read a byte stream, convert it from/to file.
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   18.01.2021
 * @lastUpdated  18.01.2021
 */


public class PdfFileConverter implements Convertible<DocumentEntity> {


    private DatabaseDocumentManager databaseDocumentManager;

    private DocumentService documentService;
    /*PDF files path on the web server to save the files to*/
    private final String RESOURCES_DIR = "C:\\Users\\Sabrine\\IdeaProjects\\Intranet\\Intranet\\src\\main\\resources\\" +
            "META-INF\\resources\\otherFiles\\";

    private String globalFileName;

    private byte[] fileAsBytes;
    private String fileName;

    private PageEntity page;

    private  DocumentEntity pdfDocument;

    public PdfFileConverter(){
        this.databaseDocumentManager = new DatabaseDocumentManager();
    }

    /*
     * Gets the file after conversion to a byte array
     * Write the file to the server
     * !!!!!Path should be read and saved in the database!!!!!
     * Displays the byte file as a component
     *
     * */
    public Component createFileFromStream(InputStream stream, byte[] fileAsBytes, String fileName) {
        this.fileAsBytes = fileAsBytes;

        this.databaseDocumentManager.setFileName(fileName);
        this.fileName = fileName;

        if(saveFile()){
            return new PdfDocumentViewer(new StreamResource(fileName, () -> {

                try {
                    return new FileInputStream(globalFileName);
                } catch (Exception e) {
                    return new ByteArrayInputStream(new byte[]{});
                }
            }));
        }else{
            return new PdfDocumentViewer();
        }
    }

    /*public List<Component> createAllDocumentViewers(){
        List<Component> viewersList = new ArrayList<>();
        for(DocumentEntity documentEntity : page.getDocuments()){
            viewersList.add(createViewerForFileFromServer(documentEntity));
        }
        return viewersList;
    }*/


    /*Generates a file from the byte array and a given name
     * name always an extra timestamp, to avoid overwriting existing files with the same names*/

    public boolean generateFileFromByteStream(byte[] fileContent, String fileName) throws IOException {
        String newFilePath = RESOURCES_DIR + changeGlobalFileName(fileName);
        this.globalFileName = newFilePath;
        File file = new File(newFilePath);
        pdfDocument = databaseDocumentManager.initializeDocumentForDatabase(fileName,globalFileName,
                databaseDocumentManager.getDocumentType(),
                page,databaseDocumentManager.initializeNotificationForDocument());
        databaseDocumentManager.savePdfDataInDatabase(pdfDocument);

        try(BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(file))){
            fileOutputStream.write(fileContent);
            fileOutputStream.flush();
            return true;
        }
    }


    /*Transforms the inputStream into a byte array, so it can be later converted to a file*/
    public byte[] getArrayFromInputStream(InputStream inputStream) throws IOException {
        byte[] bytes;
        byte[] buffer = new byte[1048576];
        try(BufferedInputStream is = new BufferedInputStream(inputStream)){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int length;
            while ((length = is.read(buffer)) > -1 ) {
                bos.write(buffer, 0, length);
            }
            bos.flush();
            bytes = bos.toByteArray();
        }
        return bytes;
    }

    public boolean deleteFile(){
        try{
            databaseDocumentManager.deletePdfDataFromDatabase(pdfDocument);
            System.out.println("VARIABLE: "+ globalFileName);
            return Files.deleteIfExists(
                    Paths.get(globalFileName));
        }
        catch(NoSuchFileException e)
        {
            System.out.println("No such file exists.");
            return false;
        }
        catch(IOException e)
        {
            System.out.println("Invalid File.");
            return false;
        }
    }

    public boolean saveFile(){
        try {
            System.out.println("Saving successful.");
            return generateFileFromByteStream(fileAsBytes, fileName);
        } catch (IOException e) {
            System.out.println("Could not save file.");
            e.printStackTrace();
        }
        return false;
    }

    private String changeGlobalFileName(String pdfFileName){
        return new Date().getTime() + "-" + pdfFileName;
    }

    public void initializeView(DocumentService documentService, PageEntity pageEntity,
                               UserEntity userEntity,
                               NotificationService notificationService){

        databaseDocumentManager.setDocumentService(documentService);
        setPage(pageEntity);
        databaseDocumentManager.setUser(userEntity);

        databaseDocumentManager.setNotificationService(notificationService);

    }

    public void initializeDocumentEntity(DocumentEntity documentEntity){
        setPdfDocument(documentEntity);
    }

    public NativeButton setDeleteButton(){
        NativeButton cancelButton = new NativeButton("Datei löschen");

        //cancelButton.setClassName("uploadButtons");
/*
        cancelButton.setEnabled(true);
        cancelButton.setVisible(true);*/

        cancelButton.addClickListener(e -> {
            if (deleteFile()) {
                cancelButton.setEnabled(false);
                cancelButton.setVisible(false);
            }else{
                cancelButton.setEnabled(true);
                cancelButton.setVisible(true);
            }
            UI.getCurrent().getPage().reload();
        });

        return cancelButton;

    }

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }

    public DocumentEntity getPdfDocument() {
        return pdfDocument;
    }

    public void setPdfDocument(DocumentEntity pdfDocument) {
        this.pdfDocument = pdfDocument;
    }

    public DatabaseDocumentManager getDatabaseDocumentManager() {
        return databaseDocumentManager;
    }

    public void setDatabaseDocumentManager(DatabaseDocumentManager databaseDocumentManager) {
        this.databaseDocumentManager = databaseDocumentManager;
    }

    public String getGlobalFileName() {
        return globalFileName;
    }

    public void setGlobalFileName(String globalFileName) {
        this.globalFileName = globalFileName;
    }

}
