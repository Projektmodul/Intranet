package com.example.application.backend.utils.images;

import com.example.application.backend.entities.ImageEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;


/**
 * This class represents an image component, it receives an imageEntity and
 * the files input stream.
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 1.0
 * @since   21-12-2020
 * @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */

public class Image extends Div {
    private ImageEntity imageEntity;

    private NativeButton deleteButton;
    private Component imageViewer;
    private StreamResource stream;


    private InputStream inputStream;

    private Div filenameAndButtonContainer;
    private H4 imageFileName;

    public Image(ImageEntity imageEntity, InputStream inputStream){
        this.imageEntity = imageEntity;
        this.inputStream = inputStream;

        initializePDF();

    }

    public void initializePDF(){
        imageFileName = new H4(imageEntity.getFileName());
        deleteButton = new NativeButton("Datei löschen");

        filenameAndButtonContainer = new Div();


        initializeImageViewer();

        filenameAndButtonContainer.add(imageFileName, deleteButton);
        this.add(filenameAndButtonContainer, imageViewer);

    }

    public void initializeImageViewer(){
        imageViewer = new ImageViewer(new StreamResource(imageEntity.getFileName(), () -> {

            try {
                System.out.println("PATH: "+ imageEntity.getPath());
                return new FileInputStream(imageEntity.getPath());
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

}

