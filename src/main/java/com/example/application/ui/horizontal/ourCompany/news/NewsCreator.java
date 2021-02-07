package com.example.application.ui.horizontal.ourCompany.news;

import com.example.application.backend.entities.ImageEntity;
import com.example.application.backend.entities.NewsEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.files.ImageService;
import com.example.application.backend.services.news.NewsService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.utils.images.ImagesManager;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a dialog for creating news.
 *
 * @author Sabrine Gamdou
 * @version 1.0
 * @since   06.02.2020
 * @lastUpdated 06.02.2021 by Sabrine Gamdou
 */
@CssImport("./styles/views/main/content.css")
public class NewsCreator extends Dialog {
    private Button addNews;
    private Button clearNews;
    private TextField title;
    private TextField description;
    private TextField text;
    private ComboBox<String> type;

    private VerticalLayout newsInformationContainer;
    private Div errorContainer;
    private Div imageContainer;

    private ImagesManager imagesManager;

    private NewsEntity newsEntity;
    private PageEntity pageEntity;
    private UserEntity userEntity;
    private ImageEntity imageEntity;

    private ImageService imageService;
    private NewsService newsService;
    private PageService pageService;

    private boolean isTitleFilled = false;
    private boolean isDescriptionFilled = false;
    private boolean isTypeFilled = false;
    private boolean isTextFilled = false;

    public NewsCreator(PageEntity pageEntity, UserEntity userEntity,
                       ImageService imageService, NewsService newsService,
                       PageService pageService) {
        this.pageEntity = pageEntity;
        this.userEntity = userEntity;
        this.imageService = imageService;
        this.newsService = newsService;
        this.newsEntity = new NewsEntity();
        this.pageService = pageService;

        imageEntity = new ImageEntity();
        imageEntity.setUser(userEntity);
        imageEntity.setPage(pageEntity);
        this.newsEntity.setImage(imageEntity);
        setContent();

        this.setCloseOnEsc(false);
        this.setCloseOnOutsideClick(false);
    }

    public void setContent() {
        errorContainer = new Div();
        errorContainer.setText("Bitte laden Sie erst ein Bild hoch und füllen Sie anschließend die nachfolgenden Felder aus.");

        imageContainer = new Div();
        H3 dialogTitle = new H3("Unternehmensnachrichten erstellen");
        newsInformationContainer = new VerticalLayout(dialogTitle,errorContainer);
        newsInformationContainer.setId("verticalLayoutNews");

        initializeImagesManager();
        initializeUploadContainer();

        initializeAddButton();
        initializeTitle();
        initializeText();
        initializeDescription();
        initializeTypeCombobox();
        initializeClearButton();

        HorizontalLayout titleTypeContainer = new HorizontalLayout(title,type);
        titleTypeContainer.setId("titleTypeContainer");

        newsInformationContainer.add(titleTypeContainer, description, text);
        Button cancelButton = new Button("Abbrechen", e -> this.close());
        cancelButton.getStyle().set("cursor","pointer");

        HorizontalLayout horizontalLayout = new HorizontalLayout(addNews, clearNews,
                cancelButton);
        horizontalLayout.setId("addDeleteButtonsContainer");

        newsInformationContainer.add(horizontalLayout);
        add(newsInformationContainer);
        this.setMaxWidth("80%");
    }

    public void initializeAddButton() {
        addNews = new Button("Hinzufügen", e -> {
            newsEntity.setImage(imageService.findImageById(imagesManager.getIdOfNewsImage()));
            newsEntity.setPages(Arrays.asList(pageEntity, pageService.findPageById(1)));
            newsEntity.setDate();
            pageEntity.getNews().add(newsEntity);
            newsService.save(newsEntity);
            UI.getCurrent().getPage().reload();
        });
        addNews.setEnabled(false);
        addNews.getStyle().set("cursor","pointer");
    }

    public void initializeClearButton(){
        clearNews = new Button("Eingabe löschen", event -> {
           title.clear();
           description.clear();
           type.clear();
           text.clear();
           addNews.setEnabled(false);
        });
        clearNews.getStyle().set("cursor","pointer");
    }

    public void initializeTitle() {
        title = new TextField();
        title.setPlaceholder("Nachrichtentitel");
        title.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                errorContainer.setText("Sie haben nichts eingegeben");
                isTitleFilled = false;
            } else {
                newsEntity.setTitle(event.getValue());
                isTitleFilled = true;
            }
            addNews.setEnabled(checkFilled());
        });
        title.setId("newsTitle");
    }

    public void initializeDescription() {
        description = new TextField();
        description.setPlaceholder("Kurzbeschreibung der Nachricht");
        description.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                errorContainer.setText("Sie haben nichts eingegeben");
                isDescriptionFilled = false;
            } else {
                newsEntity.setDescription(event.getValue());
                isDescriptionFilled = true;
            }
            addNews.setEnabled(checkFilled());
        });
        description.setId("newsDescription");
    }

    public void initializeText() {
        text = new TextField();
        text.setPlaceholder("Der Inhalt der Nachrichten");
        text.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                errorContainer.setText("Sie haben nichts eingegeben");
                isTextFilled = false;
            } else {
                newsEntity.setText(event.getValue());
                isTextFilled = true;
            }
            addNews.setEnabled(checkFilled());
        });
        text.setId("newsText");
    }

    public void initializeTypeCombobox() {
        type = new ComboBox<>();
        type.setItems("Unternehmensnachricht", "Centernachricht", "Allgemeine Nachricht");
        type.setPlaceholder("Kategorie");
        type.addValueChangeListener( event -> {
            if(event.getValue() == null){
                errorContainer.setText("Sie haben nichts eingegeben");
                isTypeFilled = false;
            }else{
                newsEntity.setType(event.getValue());
                isTypeFilled = true;
            }
            addNews.setEnabled(checkFilled());
        });
        type.setId("newsType");
    }

    /**
     * This methods initializes the entity lists + containers
     */
    private  void initializeImagesManager(){
        List<ImageEntity> tempImagesList = initializeNewsImage();
        imagesManager = new ImagesManager(tempImagesList, imageService, 3);

        imagesManager.setImagesEntities(tempImagesList);
        imagesManager.setAllImageEntitiesData(pageEntity,userEntity);
        imagesManager.setOneImage(false);

        imagesManager.initializeAllImages();
    }

    public void initializeUploadContainer(){
        imagesManager.initializeUploadContainerForNews();
        Div imagesUploader = imagesManager.getImagesUploader();
        newsInformationContainer.add(imagesUploader);
    }

    public List<ImageEntity> initializeNewsImage(){
        List<ImageEntity> tempImagesList = new ArrayList<>();
        for(NewsEntity newsEntity : pageEntity.getNews()){
            tempImagesList.add(newsEntity.getImage());
        }
        return tempImagesList;
    }

    public boolean checkFilled(){
        return isTitleFilled && isTypeFilled && isTextFilled
                && isDescriptionFilled && imagesManager.isImageUploaded();
    }
}
