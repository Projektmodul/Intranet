package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.OverviewComponents;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Library View shows ...
 *
 *  @author Litharshi Sivarasa, Vanessa Skowronsky
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 25.01.2021 by Vanessa Skowronsky
 */
@Route(value = "library", layout = MainView.class)
@PageTitle("Bibliothek")

public class LibraryView extends Div {

    private PageService pageService;
    private H1 pageTitle;
    private H2 pageText;
    private PageEntity pageEntity;

    public LibraryView(PageService pageService) {
        setId("library");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        pageEntity = pageService.findPageById(13);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new H2(pageEntity.getContent());

        Label secondQuote = new Label("entdecken");
        secondQuote.setClassName("secondQuote");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component componentDocuments = OverviewComponents.createComponent(new Icon(VaadinIcon.CLIPBOARD_TEXT), "#2F7C78", "Unterlagen", "documents");

        Component componentWiki = OverviewComponents.createLinkedComponent(new Icon(VaadinIcon.BOOK), "#2F7C78", "Wiki", "https://de.wikipedia.org/wiki/Bremer_Stra%C3%9Fenbahn_AG");

        Component componentArchive = OverviewComponents.createComponent(new Icon(VaadinIcon.ARCHIVE), "#2F7C78", "Archiv", "archive");

        Component componentMedia = OverviewComponents.createComponent(new Icon(VaadinIcon.FILM), "#2F7C78", "Medien", "media");

        Component componentFAQ = OverviewComponents.createComponent(new Icon(VaadinIcon.QUESTION), "#2F7C78", "FAQ", "fAQ");

        layout.add(componentDocuments, componentWiki, componentArchive, componentMedia, componentFAQ);


        add(pageTitle,pageText,secondQuote, layout);

    }
    }

