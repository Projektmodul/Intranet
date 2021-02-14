package com.example.application.ui.horizontal.ourCompany.news;

import com.example.application.backend.entities.ImageEntity;
import com.example.application.backend.entities.NewsEntity;

import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.news.NewsService;
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

/**
 *  A news article represents a single newsEntity, where the image, title etc will
 *  be shown in a div container
 *
 *  @author Anastasiya Jackwerth, Sabrine Gamdou
 *  @version 2.0
 *  @since 02.02.2021
 *  @lastUpdated 06.02.2021 by Sabrine Gamdou
 */
public class NewsArticle extends HorizontalLayout {

    private final ImageEntity imageEntity;
    private final NewsEntity newsEntity;
    private final ImageService imageService;
    private final NewsService newsService;

    private Paragraph description;
    private Image image;

    private VerticalLayout textContainer;
    private final int role;

    public NewsArticle(ImageEntity imageEntity, NewsEntity newsEntity,
                       ImageService imageService, NewsService newsService, int role){
        this.imageEntity = imageEntity;
        this.newsEntity = newsEntity;
        this.imageService = imageService;
        this.newsService = newsService;
        this.role = role;

        setData();

        initializeImage();

        add(image, textContainer);
        this.setId("newsArticle");
        this.description.setId("paragraph");
    }

    public void setData(){
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        H4 title = new H4(newsEntity.getTitle());

        Label date = new Label(newsEntity.getDate().toString());
        date.setId("date");

        description = new Paragraph(newsEntity.getDescription());

        Button readMoreButton = new Button("Mehr lesen", e -> {
            NewsDialog newsDialog = new NewsDialog(newsEntity);
            newsDialog.open();
        });

        Button deleteButton = new Button("Löschen", e -> {
            NewsDeletionDialog newsDeletionDialog = new NewsDeletionDialog(newsEntity,
                    newsService, imageService);
            newsDeletionDialog.open();
        });

        if(role == 1){
            horizontalLayout.add(deleteButton);
        }

        horizontalLayout.add(readMoreButton);
        readMoreButton.setId("readMoreButton");
        deleteButton.setId("readMoreButton");
        textContainer = new VerticalLayout(date, title,description,horizontalLayout);
    }

    public void initializeImage(){
        image = new Image( new StreamResource(imageEntity.getFileName(), () -> {
            try{
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