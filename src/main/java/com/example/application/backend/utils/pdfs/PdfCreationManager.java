package com.example.application.backend.utils.pdfs;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.services.files.DocumentService;

import java.io.*;

/**
 * This is class represents a pdf gui component, linking a documentEntity
 * with a delete button and a pdfViewer component.
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 3.0
 * @since   21-12-2020
 * @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */

public class PdfCreationManager {

    private DocumentService documentService;

    private InputStream inputStream;
    private DocumentEntity documentEntity;


    private String mimeType;

    public PdfCreationManager(InputStream inputStream, DocumentEntity documentEntity,
                              DocumentService documentService){
        this.inputStream = inputStream;
        this.documentEntity = documentEntity;

        this.documentService = documentService;
    }

    public void save(){
        saveToDatabase();
        saveToServer();
    }

    public void saveToServer(){
        byte[] fileContent = new byte[]{};
        try{
            fileContent= getArrayFromInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No such file exists.");
        }
        try(BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(
                new File(documentEntity.getPath())))){
            fileOutputStream.write(fileContent);
            fileOutputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Conversion failed.");
        }
    }

    public void saveToDatabase(){
        this.documentService.save(documentEntity);
    }

    /*Transforms the inputStream into a byte array, so it can be later converted to a file*/
    public byte[] getArrayFromInputStream(InputStream inputStream) throws IOException {
        byte[] bytes;
        byte[] buffer = new byte[1073741824];
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


    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }




}

