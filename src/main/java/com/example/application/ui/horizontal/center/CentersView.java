package com.example.application.ui.horizontal.center;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.OverviewComponents;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Centers View shows ...
 *
 *  @author Litharshi Sivarasa, Vanessa Skowronsky
 *  @version 3.0
 *  @since 15.12.2020
 *  @lastUpdated 27.01.2021 by Jessica Reistel
 */
@Route(value = "centers", layout = MainView.class)
@PageTitle("Centers")
public class CentersView extends Div {

    private PageService pageService;
    private PageEntity pageEntity;

    private H1 pageTitle;
    private H2 firstQuote;
    private H2 secondQuote;
    private HorizontalLayout layout;

    public CentersView(PageService pageService) {
        this.pageService = pageService;

        setId("centers");
        setClassName("pageContentPosition");
        addClassName("centerColorscheme");

        setContent();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        add(breadcrumbs,pageTitle, firstQuote, secondQuote, layout);
    }

    private void setContent(){
        pageEntity = pageService.findPageById(9);

        pageTitle = new H1(pageEntity.getTitle());
        firstQuote = new H2(pageEntity.getContent());
        secondQuote = new H2("zusammen");
        firstQuote.setClassName("firstQuote");
        secondQuote.setClassName("secondQuote");

        layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component componentCenterI = OverviewComponents.createComponent(new Icon(VaadinIcon.CLUSTER), "#1481de", "Center I", "centerI");

        layout.add(componentCenterI);

    }
}