package com.example.application.ui.horizontal.services;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
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
 *  Services View shows ...
 *
 *  @author Litharshi Sivarasa, Vanessa Skowronsky
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 25.01.2021 by Vanessa Skowronsky
 */
@Route(value = "services", layout = MainView.class)
@PageTitle("Services")
public class ServicesView extends Div {

    private PageService pageService;
    private H1 pageTitle;
    private H2 pageText;
    private PageEntity pageEntity;

    public ServicesView(PageService pageService) {
        setId("services");
        setClassName("pageContentPosition");
        addClassName("servicesColorscheme");

        pageEntity = pageService.findPageById(18);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new H2(pageEntity.getContent());

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        Label secondQuote = new Label("Ökostrom");
        secondQuote.setClassName("secondQuote");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component componentTimeAccount = OverviewComponents.createComponent(new Icon(VaadinIcon.CHART_TIMELINE), "#FF5621", "Zeitkonto", "inProgress");

        Component component2LSA = OverviewComponents.createComponent(new Icon(VaadinIcon.PYRAMID_CHART), "#FF5621", "LSA-Meldungen", "inProgress");

        Component componentRailService = OverviewComponents.createComponent(new Icon(VaadinIcon.CAR), "#FF5621", "Fahrdienst", "inProgress");

        Component componentBusinessTrip = OverviewComponents.createComponent(new Icon(VaadinIcon.FLIGHT_TAKEOFF), "#FF5621", "Dienstreisen", "inProgress");

        layout.add(componentTimeAccount, component2LSA, componentRailService, componentBusinessTrip);

        add(breadcrumbs, pageTitle, pageText, secondQuote, layout);
    }
}
