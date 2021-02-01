package com.example.application.ui;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  InProgress View is used for sides that have no functionality. It is a fallback class with an informative text.
 *
 *  @author Vanessa Skowronsky
 *  @version 2.0
 *  @since 04.01.2021
 *  @lastUpdated 01.02.2021 by Jessica Reistel
 */
@Route(value = "inProgress", layout = MainView.class)
@PageTitle("Im Aufbau")
public class InProgressView extends Div {
    private PageService pageService;
    private H1 pageTitle;
    private H2 pageText;
    private PageEntity pageEntity;

    public InProgressView(PageService pageService){
        setId("inProgress");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        pageEntity = pageService.findPageById(2);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new H2(pageEntity.getContent());
        pageText.setId("inProgressH2");

        Icon icon = new Icon(VaadinIcon.TOOLS);
        Div iconDiv = new Div(icon);

        add(pageTitle, pageText, iconDiv);
    }
}