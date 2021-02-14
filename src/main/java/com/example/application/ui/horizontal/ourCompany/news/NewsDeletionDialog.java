package com.example.application.ui.horizontal.ourCompany.news;

import com.example.application.backend.entities.NewsEntity;
import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.news.NewsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This is a dialog for deleting news.
 *
 * @author Sabrine Gamdou
 * @version 1.0
 * @since   06.02.2020
 * @lastUpdated 06.02.2021 by Sabrine Gamdou
 */
public class NewsDeletionDialog extends Dialog {

    private final NewsEntity newsEntity;
    private final NewsService newsService;
    private final ImageService imageService;

    private Notification notification;

    public NewsDeletionDialog(NewsEntity newsEntity, NewsService newsService,
                              ImageService imageService){
        this.newsEntity = newsEntity;
        this.newsService = newsService;
        this.imageService = imageService;

        initializeButtons();
    }

    public void initializeButtons(){
        H3 message = new H3("Sind Sie sich sicher, dass Sie diese Nachricht löschen wollen?");

        Button deleteButton = new Button("Löschen", e -> {
            try {
                newsService.delete(newsEntity);
                imageService.delete(newsEntity.getImage());
                deleteImageFromServer();
                setDeletionNotification(new Icon(VaadinIcon.CHECK_CIRCLE), "Die Nachricht wurde erfolgreich gelöscht!");
                notification.open();
                this.close();
            } catch (IOException ioException) {
                setDeletionNotification(new Icon(VaadinIcon.CLOSE_CIRCLE), "Löschen der Nachricht fehlgeschlagen.");
                ioException.printStackTrace();
            }
        });
        deleteButton.getStyle().set("cursor","pointer");

        Button cancelButton = new Button("Abbrechen", e -> this.close());
        cancelButton.getStyle().set("cursor","pointer");

        HorizontalLayout horizontalLayout = new HorizontalLayout(deleteButton, cancelButton);
        horizontalLayout.getStyle().set("justify-content","center");
        add(message, horizontalLayout);
    }

    private void deleteImageFromServer() throws IOException{
        Files.deleteIfExists(
                    Paths.get(newsEntity.getImage().getPath()));
    }

    private void setDeletionNotification(Icon icon, String message){
        H3 content = new H3(message);
        VerticalLayout notificationLayout = new VerticalLayout(content,icon);
        notificationLayout.getStyle().set("align-items","center");
        notification = new Notification(notificationLayout);
        notification.setDuration(3000);
        notification.setPosition(Notification.Position.MIDDLE);
    }
}
