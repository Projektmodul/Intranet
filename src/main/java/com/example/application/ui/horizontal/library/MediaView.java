package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  FAQ View shows ...
 *
 *  @author Monika Martius
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated 22.01.2021
 */
@Route(value = "media", layout = MainView.class)
@PageTitle("Medien")
public class MediaView extends Div {
    private PageService pageService;
    private H1 pageTitle;
    private H2 pageText;
    private PageEntity pageEntity;

    public MediaView(PageService pageService) {
        setId("media");
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        pageEntity = pageService.findPageById(16);
        pageTitle = new H1(pageEntity.getTitle());

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Bibliothek"), new Breadcrumb(pageEntity.getTitle()));

        add(breadcrumbs, pageTitle);
    }
}
