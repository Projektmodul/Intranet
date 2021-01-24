package com.example.application.ui;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  InProgress View shows ...
 *
 *  @author Vanessa Skowronsky
 *  @version 1.0
 *  @since 04.01.2021
 *  @lastUpdated 17.01.2021
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

        add(pageTitle, pageText);
    }
}