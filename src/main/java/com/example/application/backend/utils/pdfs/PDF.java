package com.example.application.backend.utils.pdfs;

import com.example.application.backend.entities.DocumentEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;


/**
 * This is class that creates the file in the database and the server.
 *
 * @author Anastasiya Jackwerth, Sabrine Gamdou
 * @version 1.0
 * @since   21-12-2020
 * @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */

public class PDF extends Div {

    private DocumentEntity documentEntity;

    private Icon deleteButton;
    private Component pdfDocumentViewer;
    private StreamResource stream;


    private InputStream inputStream;



    public PDF(DocumentEntity documentEntity, InputStream inputStream){
        this.documentEntity = documentEntity;
        this.inputStream = inputStream;

        initializePDF();

    }

    public void initializePDF(){
        deleteButton = new Icon(VaadinIcon.TRASH);
        initializePdfViewer();
        this.add(deleteButton, pdfDocumentViewer);
    }

    public void initializePdfViewer(){
        pdfDocumentViewer = new PdfDocumentViewer(new StreamResource(documentEntity.getFileName(), () -> {

            try {
                System.out.println("PATH: "+documentEntity.getPath());
                return new FileInputStream(documentEntity.getPath());
            } catch (Exception e) {
                return new ByteArrayInputStream(new byte[]{});
            }
        }));


    }


    public Icon getDeleteButton() {
        return deleteButton;
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

   /* public void setDeleteButton(NativeButton deleteButton) {
        this.deleteButton = deleteButton;
    }


    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        initializePdfViewer();
    }*/


}
