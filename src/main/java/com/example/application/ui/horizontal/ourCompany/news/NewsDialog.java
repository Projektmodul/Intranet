package com.example.application.ui.horizontal.ourCompany.news;

import com.example.application.backend.entities.NewsEntity;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

/**
 * This is a dialog for showing the complete news article.
 *
 * @author Sabrine Gamdou
 * @version 1.0
 * @since   06.02.2020
 * @lastUpdated 06.02.2021 by Sabrine Gamdou
 */
public class NewsDialog extends Dialog {

    private Label date;
    private H1 title;
    private Paragraph description;
    private Image image;
    private VerticalLayout verticalLayout;

    private NewsEntity newsEntity;

    public NewsDialog(NewsEntity newsEntity){
        this.newsEntity = newsEntity;
        setContent();
        this.add(verticalLayout);
    }

    public void setContent(){
        date = new Label(newsEntity.getDate().toString());
        date.setId("date");

        title = new H1(newsEntity.getTitle());
        description = new Paragraph(newsEntity.getText());
        initializeImage();

        verticalLayout = new VerticalLayout(title,image,date,description);
        this.setMaxWidth("80%");
    }

    public void initializeImage(){
        image = new Image( new StreamResource(newsEntity.getImage().getFileName(), () -> {
            try{
                return new FileInputStream(newsEntity.getImage().getPath());
            } catch (Exception e){
                return new ByteArrayInputStream(new byte[]{});
            }
        }), "Unternehmensnachrichtenbild");
        image.getStyle().set("width","100%");
        image.getStyle().set("height","auto");
    }
}
