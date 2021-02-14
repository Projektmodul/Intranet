package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.example.application.ui.auxiliary.OverviewComponents;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Library View shows the overview of Library
 *
 *  @author Litharshi Sivarasa, Vanessa Skowronsky
 *  @version 3.0
 *  @since 15.12.2020
 *  @lastUpdated 02.02.2021 by Vanessa Skowronsky
 */
@Route(value = "library", layout = MainView.class)
@PageTitle("Bibliothek")

public class LibraryView extends Div {

    private final PageService pageService;
    private PageEntity pageEntity;

    private H1 pageTitle;
    private H2 firstQuote;
    private H2 secondQuote;
    private HorizontalLayout layout;

    public LibraryView(PageService pageService) {
        this.pageService = pageService;
        setId("library");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        setContent();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        add(breadcrumbs, pageTitle, firstQuote, secondQuote, layout);
    }

    /**
     * This method creates and puts Components in the middle of the page.
     * And also make the listed pages accessible from this initial Page.
     */
    private void setContent(){
        pageEntity = pageService.findPageById(13);

        pageTitle = new H1(pageEntity.getTitle());
        firstQuote = new H2(pageEntity.getContent());
        secondQuote = new H2("entdecken");
        firstQuote.setClassName("firstQuote");
        secondQuote.setClassName("secondQuote");

        layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component componentDocuments = OverviewComponents.createComponent(new Icon(VaadinIcon.CLIPBOARD_TEXT), "#2F7C78", "Unterlagen", "documents");

        Component componentWiki = OverviewComponents.createLinkedComponent(new Icon(VaadinIcon.BOOK), "#2F7C78", "Wiki", "https://de.wikipedia.org/wiki/Bremer_Stra%C3%9Fenbahn_AG");

        Component componentArchive = OverviewComponents.createComponent(new Icon(VaadinIcon.ARCHIVE), "#2F7C78", "Archiv", "archive");

        Component componentMedia = OverviewComponents.createComponent(new Icon(VaadinIcon.CAMERA), "#2F7C78", "Medien", "media");

        Component componentFAQ = OverviewComponents.createComponent(new Icon(VaadinIcon.QUESTION), "#2F7C78", "FAQ", "fAQ");

        layout.add(componentDocuments, componentWiki, componentArchive, componentMedia, componentFAQ);




    }
    }

