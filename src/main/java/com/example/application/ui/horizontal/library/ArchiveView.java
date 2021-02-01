package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Archive View shows a short text and a non-functional button
 *
 *  @author Vanessa Skowronsky
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 18.01.2021
 */
@Route(value = "archive", layout = MainView.class)
@PageTitle("Archiv")
public class ArchiveView extends Div {
    private PageService pageService;
    private H1 pageTitle;
    private Span pageText;
    private PageEntity pageEntity;

    public ArchiveView(PageService pageService) {
        setId("archive");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        pageEntity = pageService.findPageById(15);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new Span(pageEntity.getContent());

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Bibliothek"), new Breadcrumb(pageEntity.getTitle()));

        add(breadcrumbs, pageTitle);
        VerticalLayout archiveLayout = new VerticalLayout();
        archiveLayout.add(pageText);
        archiveLayout.add(new Button("Zum Dokumentenmanagementsystem"));

        add(archiveLayout);
    }
}
