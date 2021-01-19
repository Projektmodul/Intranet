package com.example.application.backend.utils.pdfs;

import com.example.application.backend.entities.DocumentEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;


/**
 * This is class represents a pdf gui component, linking a documentEntity with
 * with a delete button and a pdfViewer component.
 *
 * @author Anastasiya Jackwerth, Sabrine Gamdou
 * @version 3.0
 * @since   21-12-2020
 * @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */

public class PDF extends Div {

    private DocumentEntity documentEntity;

    private NativeButton deleteButton;
    private Component pdfDocumentViewer;
    private StreamResource stream;


    private InputStream inputStream;

    private Div filenameAndButtonContainer;
    private H4 pdfFileName;

    public PDF(DocumentEntity documentEntity, InputStream inputStream){
        this.documentEntity = documentEntity;
        this.inputStream = inputStream;

        initializePDF();

    }

    public void initializePDF(){
        pdfFileName = new H4(documentEntity.getFileName());
        deleteButton = new NativeButton("Datei löschen");

        filenameAndButtonContainer = new Div();


        initializePdfViewer();

        filenameAndButtonContainer.add(pdfFileName, deleteButton);
        this.add(filenameAndButtonContainer, pdfDocumentViewer);

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


    public NativeButton getDeleteButton() {
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
