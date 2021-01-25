package com.example.application.ui.horizontal.center;

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
 *  Centers View shows ...
 *
 *  @author Litharshi Sivarasa, Vanessa Skowronsky
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 25.01.2021 by Vanessa Skowronsky
 */
@Route(value = "centers", layout = MainView.class)
@PageTitle("Centers")
public class CentersView extends Div {

    private PageService pageService;
    private H1 pageTitle;
    private H2 pageText;
    private PageEntity pageEntity;

    public CentersView(PageService pageService) {
        setId("centers");
        setClassName("pageContentPosition");
        addClassName("centerColorscheme");

        pageEntity = pageService.findPageById(9);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new H2(pageEntity.getContent());

        Label secondQuote = new Label("zusammen");
        secondQuote.setClassName("secondQuote");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component componentCenterI = OverviewComponents.createComponent(new Icon(VaadinIcon.CLUSTER), "#0A5396", "Center I", "centerI");

        layout.add(componentCenterI);

        add(pageTitle,pageText, secondQuote, layout);
    }
}