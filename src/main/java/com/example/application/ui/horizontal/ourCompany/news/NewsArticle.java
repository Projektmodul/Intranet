package com.example.application.ui.horizontal.ourCompany.news;

import com.example.application.backend.entities.ImageEntity;
import com.example.application.backend.entities.NewsEntity;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

public class NewsArticle extends HorizontalLayout {

    private ImageEntity imageEntity;
    private NewsEntity newsEntity;

    private H4 title;
    private Label date;
    private Paragraph description;
    private Image image;
    private Button readMoreButton;

    private VerticalLayout textContainer;

    public NewsArticle(ImageEntity imageEntity, NewsEntity newsEntity){
        this.imageEntity = imageEntity;
        this.newsEntity = newsEntity;

        setData();

        initializeImage();

        add(image, textContainer);
        this.setId("newsArticle");
        this.description.setId("paragraph");
    }

    public void setData(){
        title = new H4(newsEntity.getTitle());

        date = new Label(newsEntity.getDate().toString());
        date.setId("date");

        description = new Paragraph(newsEntity.getDescription());

        readMoreButton = new Button("Mehr lesen");
        readMoreButton.setId("readMoreButton");

        textContainer = new VerticalLayout(date,title,description,readMoreButton);

    }

    public void initializeImage(){
        image = new Image( new StreamResource(imageEntity.getFileName(), () -> {

            try {
                System.out.println("Image PATH: " + imageEntity.getPath());
                return new FileInputStream(imageEntity.getPath());
            } catch (Exception e) {
                return new ByteArrayInputStream(new byte[]{});
            }
        }), "Unternehmensnachrichtensbild");
        image.getStyle().set("width","40%");
        image.getStyle().set("height","auto");
        image.getStyle().set("padding","10px");
    }
}