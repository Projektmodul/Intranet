package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.InitData;
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
 *  @author Vanessa Skowronsky, Laura Neuendorf
 *  @version 4.0
 *  @since 15.12.2020
 *  @lastUpdated 07.02.2021 by Jessica Reistel
 */
@Route(value = "archive", layout = MainView.class)
@PageTitle("Archiv")
public class ArchiveView extends Div {
    private PageService pageService;

    public ArchiveView(PageService pageService) {
        setId("archive");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        this.pageService = pageService;

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Bibliothek"), new Breadcrumb("Archiv"));

        Div archiveLayout = new Div();
        archiveLayout.add(new Button("Zum Dokumentenmanagementsystem"));

        InitData initArchive = new InitData(pageService);
        this.add(breadcrumbs, initArchive.setData(15), archiveLayout);
    }
}
