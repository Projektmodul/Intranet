package com.example.application.ui.horizontal.projects;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.backend.utils.images.Image;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Nordlicht View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "nordlicht", layout = MainView.class)
@PageTitle("Nordlicht")
public class NordlichtView extends Div {

    private PageService pageService;
    private H1 pageTitle;
    private H2 pageText;
    private PageEntity pageEntity;
    private Image nordlichtOne;
    private Image nordlichtTwo;

    private HorizontalLayout layoutSplit;
    private Component leftComponent;
    private Component rightComponent;

    public NordlichtView(PageService pageService) {
        this.pageService = pageService;
        setId("nordlicht");
        setClassName("pageContentPosition");
        addClassName("projectsColorscheme");

        initializeLeftContainer();
        initializeRightContainer();
        initializeSplitLayout();
    }

    private void initializeSplitLayout() {

    }

    private void initializeRightContainer() {

    }

    private void initializeLeftContainer() {
        pageEntity = pageService.findPageById(12);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new H2(pageEntity.getContent());

        Div box = new Div();
        this.add(pageTitle);
        box.setId("pageTitle");
    }
}



