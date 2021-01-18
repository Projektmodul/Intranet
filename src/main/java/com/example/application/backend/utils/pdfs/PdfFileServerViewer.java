package com.example.application.backend.utils.pdfs;

import com.example.application.backend.entities.DocumentEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

@CssImport("./styles/views/main/content.css")
public class PdfFileServerViewer extends Div {

    private PdfFileConverter pdfFileConverter;

    private Div uploadOutput;

    private Component pdfFile;


    DocumentEntity documentEntity;


    public PdfFileServerViewer(DocumentEntity documentEntity) {
        this.documentEntity = documentEntity;
        this.pdfFileConverter = new PdfFileConverter();

        pdfFile = new PdfDocumentViewer();

        this.setId("pdfViewerContainer");

        initializeUploadOutputContainer();
    }

    private void initializeUploadOutputContainer() {
        uploadOutput = new Div();
        uploadOutput.setId("uploadOutputContainer");

        this.add(uploadOutput);
    }


    public void getPdfFromServer() {
        pdfFile = createPdfFileFromServer();
        showOutput(documentEntity.getFileName(), pdfFile,
                uploadOutput);
    }


    public Component createPdfFileFromServer() {
        Component content = createViewerForFileFromServer(documentEntity);
        content.setId("pdfViewer");
        return content;
    }


    public Component createViewerForFileFromServer(DocumentEntity documentEntity) {
        return new PdfDocumentViewer(new StreamResource(documentEntity.getFileName(), () -> {

            try {
                return new FileInputStream(documentEntity.getPath());
            } catch (Exception e) {
                return new ByteArrayInputStream(new byte[]{});
            }
        }));
    }


    private void showOutput(String text, Component content,
                            HasComponents outputContainer) {
        H4 filenameLabel = new H4(text);

        outputContainer.add(filenameLabel);
        outputContainer.add(pdfFileConverter.setDeleteButton());
        outputContainer.add(content);
    }

    public DocumentEntity getDocumentEntity() {
        return documentEntity;
    }

    public void setDocumentEntity(DocumentEntity documentEntity) {
        this.documentEntity = documentEntity;
    }


/*
        return new PdfDocumentViewer(new StreamResource(documentEntity.getFileName(), () -> {

            try {
                return new FileInputStream(documentEntity.getPath());
            } catch (Exception e) {
                return new ByteArrayInputStream(new byte[]{});
            }
        }));*//*
     */


    /* public void showPagePdfs() {

         for (Component viewer : pdfFileConverter.createAllDocumentViewers()) {
             //empty string should be changed
             Div pdfOutput = new Div();
             pdfOutput.setId("uploadOutputContainer");
             showOutput(pdfFileConverter.getDatabaseDocumentManager().getFileName(), viewer, pdfOutput);
             this.add(pdfOutput);
         }
     }*/
}
