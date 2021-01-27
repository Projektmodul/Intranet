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

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Services View shows ...
 *
 *  @author Litharshi Sivarasa, Vanessa Skowronsky
 *  @version 3.0
 *  @since 15.12.2020
 *  @lastUpdated 27.01.2021 by Jessica Reistel
 */
@Route(value = "services", layout = MainView.class)
@PageTitle("Services")
public class ServicesView extends Div {

    private PageService pageService;
    private H1 pageTitle;
    private H2 firstQuote;
    private H2 secondQuote;
    private HorizontalLayout layout;

    public ServicesView(PageService pageService) {
        this.pageService = pageService;

        setId("services");
        setClassName("pageContentPosition");
        addClassName("servicesColorscheme");

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        setContent();

        add(breadcrumbs,pageTitle, firstQuote, secondQuote, layout);
    }

    private void setContent(){
        PageEntity pageEntity = pageService.findPageById(18);

        pageTitle = new H1(pageEntity.getTitle());
        firstQuote = new H2(pageEntity.getContent());
        secondQuote = new H2("Ökostrom");
        firstQuote.setClassName("firstQuote");
        secondQuote.setClassName("secondQuote");

        layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component componentTimeAccount = OverviewComponents.createComponent(new Icon(VaadinIcon.CHART_TIMELINE), "#FF5621", "Zeitkonto", "inProgress");

        Component componentLSA = OverviewComponents.createComponent(new Icon(VaadinIcon.PYRAMID_CHART), "#FF5621", "LSA-Meldungen", "inProgress");

        Component componentRailService = OverviewComponents.createComponent(new Icon(VaadinIcon.CAR), "#FF5621", "Fahrdienst", "inProgress");

        Component componentBusinessTrip = OverviewComponents.createComponent(new Icon(VaadinIcon.FLIGHT_TAKEOFF), "#FF5621", "Dienstreisen", "inProgress");

        Component componentFormDesigner = OverviewComponents.createLinkedComponent(new Icon(VaadinIcon.FORM), "#FF5621", "Formular Designer", "https://www.microsoft.com/de-de/microsoft-365/online-surveys-polls-quizzes");

        layout.add(componentTimeAccount, componentLSA, componentRailService, componentBusinessTrip, componentFormDesigner);

    }
}
