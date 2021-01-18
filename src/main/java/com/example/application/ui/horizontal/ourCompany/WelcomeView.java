package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.services.documents.DocumentService;
import com.example.application.backend.services.notifications.NotificationService;
import com.example.application.backend.services.ourCompany.WelcomeViewService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.PdfViewerManager;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Welcome View shows ...
 *
 *  @author Sabrine Gamdou
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 11.01.2021
 */
@Route(value = "welcome", layout = MainView.class)
@PageTitle("Willkommen")
public class WelcomeView extends Div {

    private  WelcomeViewService welcomeViewService;

    private PdfViewerManager pdfViewerManager;

    private PageEntity pageEntity;
    private UserEntity userEntity;
    private DocumentEntity documentEntity;

    private PageService pageService;
    private UserService userService;
    private DocumentService documentService;
    private NotificationService notificationService;

    private H1 pageTitle;
    private Span pageContent;
    
    public WelcomeView(WelcomeViewService welcomeViewService,PageService pageService, UserService userService,
                       DocumentService documentService, NotificationService notificationService) {

        this.welcomeViewService = welcomeViewService;
        this.pageService = pageService;
        this.userService = userService;
        this.documentService = documentService;
        this.notificationService = notificationService;

        setId("welcome");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        setData();

        userEntity = pageEntity.getUser();

        initializePageDocuments();
    }

    private void setData(){
        pageEntity = welcomeViewService.findPageById(1);

        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Span(pageEntity.getContent());

        this.add(pageTitle,pageContent);
    }

    private void initializePageDocuments(){
        pdfViewerManager = new PdfViewerManager();

        pdfViewerManager.getPdfFileConverter().initializeView(this.documentService,this.pageEntity,
                this.userEntity,this.documentEntity,this.notificationService);

        pdfViewerManager.getPdfFileConverter().getDatabaseDocumentManager().setNotificationCategorie("Unternehmensneuigkeit");
        pdfViewerManager.getPdfFileConverter().getDatabaseDocumentManager().setDocumentType("Organigramm");

        pdfViewerManager.showPagePdfs();

        this.add(pdfViewerManager);
    }

}
