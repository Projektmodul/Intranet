package com.example.application.ui.vertical.canteen;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.entities.RoleEntity;
import com.example.application.backend.entities.UserEntity;
import com.example.application.backend.security.GetUserController;
import com.example.application.backend.services.files.DocumentService;
import com.example.application.backend.services.notifications.NotificationService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.services.users.UserService;
import com.example.application.backend.utils.pdfs.PDF;
import com.example.application.backend.utils.pdfs.PdfsManager;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Canteen View shows a View for all menus.
 *
 * @author  Sabrine Gamdou, Anastasiya Jackwerth
 * @version 4.0
 * @since   14.12.2020
 * @lastUpdated 04.02.2021 by Sabrine Gamdou
 */
@Route(value = "canteen", layout = MainView.class)
@PageTitle("Betriebsrestaurant")
public class CanteenView extends Div {

    private final PageService pageService;
    private final DocumentService documentService;
    private final NotificationService notificationService;

    private PageEntity pageEntity;
    private PdfsManager pdfsManager;

    private Div bigContainer;

    private final int role;

    public CanteenView(PageService pageService, UserService userService, NotificationService notificationService, DocumentService documentService) {
        setId("canteen");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        this.pageService = pageService;
        this.documentService = documentService;
        this.notificationService = notificationService;
        GetUserController getUserController = new GetUserController();
        String username = getUserController.getUsername();
        UserEntity userEntity = userService.findByUsername(username);
        RoleEntity roleEntity = userEntity.getRole();
        role = roleEntity.getRoleId();
        setData();

        initializePdfsManager();
        initializeBigContainer();
        initializeUploadContainer();

        pdfsManager.setOnePdf(true);
    }

    public void setData(){
        pageEntity = pageService.findPageById(26);
        H1 pageTitle = new H1(pageEntity.getTitle());

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        Paragraph pageContent = new Paragraph(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        HorizontalLayout menuDeleteLayout = new HorizontalLayout(pageTitle);
        menuDeleteLayout.setId("menuDeleteLayout");

        this.add(breadcrumbs, pageTitle, pageContent, menuDeleteLayout);
    }

    public void initializePdfsManager(){
        pdfsManager = new PdfsManager(pageEntity.getDocuments(), notificationService, documentService, role);
        pdfsManager.setDocumentEntities(pageEntity.getDocuments());
        pdfsManager.setAllDocumentEntitiesData("Speiseplan", pageEntity, pageEntity.getUser());
        pdfsManager.initializeAllPdfs();
        pdfsManager.getPdfManager().setNotificationCategory("Speiseplan");
    }

    private void initializePDFs(){
        Div pdfsContainer = new Div();
        for (PDF pdf : pdfsManager.getPdfs()) pdfsContainer.add(pdf);
        bigContainer.add(pdfsContainer);
        bigContainer.add(pdfsManager);
    }

    public void initializeBigContainer(){
        bigContainer = new Div();
        initializePDFs();
        this.add(bigContainer);
    }

    public void initializeUploadContainer() {
        pdfsManager.initializeUploadContainer();
        Div pdfsUploader = pdfsManager.getPdfsUploader();
        this.add(bigContainer);
        if(role == 1 || role == 3) {
            this.add(pdfsUploader);
        }
    }
}
