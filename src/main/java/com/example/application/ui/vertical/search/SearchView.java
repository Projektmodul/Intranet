package com.example.application.ui.vertical.search;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Search View shows ...
 *
 *  @author ??, Jessica Reistel
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 27.01.2021 by Jessica Reistel
 */
@Route(value = "search", layout = MainView.class)
@PageTitle("Suchen")
public class SearchView extends Div {
    private PageService pageService;
    private H1 pageTitle;
    private Paragraph pageText;
    private PageEntity pageEntity;

    public SearchView(PageService pageService) {
        setId("search");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        pageEntity = pageService.findPageById(27);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new Paragraph(pageEntity.getContent());

        add(pageTitle);
    }
}
