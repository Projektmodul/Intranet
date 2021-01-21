package com.example.application.ui.horizontal.projects;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.awt.*;

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

    private H1 pageTitle;
    private Image nordlichtOne;
    private Image nordlichtTwo;

    private HorizontalLayout layoutSplit;
    private Component leftComponent;
    private Component rightComponent;

    public NordlichtView() {
        setId("nordlicht");
        setClassName("pageContentPosition");
        addClassName("projectsColorscheme");
        add(new Text("Nordlicht"));

        initializeLeftContainer();
        initializeRightContainer();
        initializeSplitLayout();
    }

    private void initializeSplitLayout() {

    }

    private void initializeRightContainer() {

    }

    private void initializeLeftContainer() {
        pageTitle = new H1("Nordlicht");
        pageTitle.setId("pageTitle");

        Div box = new Div();
        box.add(pageTitle);
        box.setId("pageTitle");



    }

}
