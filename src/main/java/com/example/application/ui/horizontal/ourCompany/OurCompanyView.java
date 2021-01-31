package com.example.application.ui.horizontal.ourCompany;

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
 *  OurCompany View shows ...
 *
 *  @author Litharshi Sivarasa, Vanessa Skowronsky
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 25.01.2021 by Vanessa Skowronsky
 */
@Route(value = "ourCompany", layout = MainView.class)
@PageTitle("Unser Unternehmen")
public class OurCompanyView extends Div {
    private PageService pageService;
    private H1 pageTitle;
    private H2 pageText;
    private PageEntity pageEntity;

    public OurCompanyView(PageService pageService) {
        setId("ourCompany");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        pageEntity = pageService.findPageById(3);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new H2(pageEntity.getContent());

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        Label secondQuote = new Label("für sie da");
        secondQuote.setClassName("secondQuote");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component componentWelcome = OverviewComponents.createComponent(new Icon(VaadinIcon.HANDSHAKE), "#A00505", "Willkommen", "welcome");
        Component componentAboutUs = OverviewComponents.createComponent(new Icon(VaadinIcon.INFO), "#A00505", "Über uns", "aboutUs");
        Component componentNews = OverviewComponents.createComponent(new Icon(VaadinIcon.NEWSPAPER), "#A00505", "Nachrichten", "news");
        Component componentSport = OverviewComponents.createComponent(new Icon(VaadinIcon.EXIT), "#A00505", "Sport & Freizeit", "sport");
        Component componentCareer = OverviewComponents.createComponent(new Icon(VaadinIcon.DOLLAR), "#A00505", "Stellenangebote", "career");
        layout.add(componentWelcome, componentAboutUs, componentNews, componentSport, componentCareer);

        add(breadcrumbs, pageTitle, pageText, secondQuote, layout);
    }
}

