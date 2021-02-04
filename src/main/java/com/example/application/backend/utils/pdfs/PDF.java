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
 * @version 3.0
 * @since   21.12.2020
 * @lastUpdated 04.02.2021 Sabrine Gamdou
 */

public class PDF extends Div {

    private DocumentEntity documentEntity;

    private Icon deleteButton;
    private Component pdfDocumentViewer;



    private InputStream inputStream;
    private int role;



    public PDF(DocumentEntity documentEntity, InputStream inputStream, int role){
        this.documentEntity = documentEntity;
        this.inputStream = inputStream;
        this.role = role;
        initializePDF();

    }

    public void initializePDF(){
        deleteButton = new Icon(VaadinIcon.TRASH);
        initializePdfViewer();
        if(role == 1 || role == 3) {
            this.add(deleteButton);
        }
        this.add(pdfDocumentViewer);
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

}
