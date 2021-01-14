package com.example.application.ui.vertical.canteen;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * This is a View for all menus.
 *
 * @author  Sabrine Gamdou, Anastasiya Jackwerth
 * @version 1.0
 * @since   14.12.2020
 * @lastUpdated 12.01.2021
 */

@Route(value = "canteen", layout = ContentHolder.class)
@PageTitle("Betriebsrestaurant")
@CssImport("./styles/views/main/canteen.css")
public class CanteenView extends Div {

    private Component leftComponent;
    private Component rightComponent;

    private H1 pageTitle;
    private Span pageContent;
    private Image pdfImage;
    private Icon deleteIcon;

    private Upload uploadButton;

    private SplitLayout splitLayout;

    private Dialog imageDialog;

    public CanteenView() {
        setId("contentViewBlue");
        setClassName("pageContentPosition");

        initializeLeftContainer();
        initializeRightContainer();
        initializeImageDialog();
        initializeSplitLayout();
    }

    public void initializeLeftContainer(){
        pageTitle = new H1("Speiseplan");
        pageTitle.setId("pageTitle");

        deleteIcon = new Icon(VaadinIcon.TRASH);
        deleteIcon.setId("deleteIcon");

        HorizontalLayout menuDeleteLayout = new HorizontalLayout(pageTitle,deleteIcon);
        menuDeleteLayout.setId("menuDeleteLayout");

        /*For frontend this is enough, later for backend this will be changed */
        pdfImage = new Image("images/speiseplanBeispiel.png","Speiseplan-PDF");
        pdfImage.setId("pdfImage");
        /*-------------------------------------------------------------------*/

        pageContent = new Span("Werfen Sie doch schon vorab einen Blick in unsere Speisekarte.");

        leftComponent = new VerticalLayout(menuDeleteLayout,pdfImage,pageContent);
        leftComponent.setId("leftComponent");
    }

    public void initializeRightContainer(){
        initializeUploadButton();
        rightComponent = new VerticalLayout(uploadButton);
        rightComponent.setId("rightComponent");
    }

    public void initializeSplitLayout(){
        splitLayout = new SplitLayout(leftComponent,rightComponent);
        splitLayout.setId("splitLayout");
        this.add(splitLayout);
    }

    public void initializeUploadButton(){
        MemoryBuffer buffer = new MemoryBuffer();
        uploadButton = new Upload(buffer);

        uploadButton.setUploadButton(new Button("Speiseplan hochladen"));
        uploadButton.setDropLabel(new Span("PDF-Dokument hier reinziehen"));

    }

    public void initializeImageDialog(){
        Image dialogImage = new Image("images/speiseplanBeispiel.png","Speiseplan-PDF");
        dialogImage.setId("dialogPdfImage");

        imageDialog = new Dialog();
        initializeCloseIcon();
        imageDialog.add(dialogImage);

        imageDialog.setId("imageDialog");
        add(imageDialog);

        setDialogClickEvent();
    }

    public void setDialogClickEvent(){
        pdfImage.addClickListener(e-> imageDialog.open());
    }

    public void initializeCloseIcon(){
        Icon closeIcon = new Icon(VaadinIcon.CLOSE);
        closeIcon.addClickListener(e -> imageDialog.close());
        imageDialog.add(closeIcon);
        closeIcon.setId("closeIcon");
    }

}
