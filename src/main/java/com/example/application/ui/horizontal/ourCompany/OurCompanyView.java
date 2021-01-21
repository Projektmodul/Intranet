package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  OurCompany View shows ...
 *
 *  @author Litharshi Sivarasa
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 17.01.2021
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

        pageEntity = pageService.findPageById(6);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new H2(pageEntity.getContent());

        Label secondQuote = new Label("für sie da");
        secondQuote.setClassName("secondQuote");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component component1 = createComponent(new Icon(VaadinIcon.HANDSHAKE), "#A00505", "Willkommen", "welcome");
        Component component2 = createComponent(new Icon(VaadinIcon.INFO), "#A00505", "Über uns", "aboutUs");
        Component component3 = createComponent(new Icon(VaadinIcon.NEWSPAPER), "#A00505", "Nachrichten", "news");
        Component component4 = createComponent(new Icon(VaadinIcon.EXIT), "#A00505", "Sport & Freizeit", "sport");
        Component component5 = createComponent(new Icon(VaadinIcon.DOLLAR), "#A00505", "Stellenangebote", "career");
        layout.add(component1, component2, component3, component4, component5);

        add(pageTitle,pageText, secondQuote, layout);
    }

    private Component createComponent(Icon icon, String backgroundcolor, String spanText, String route) {

        icon.setClassName("horizontalBarIcons");
        Tab tab = new Tab(icon);

        Span span = new Span(spanText);
        span.setClassName("spanStyle");

        VerticalLayout layout = new VerticalLayout(span, tab);
        layout.setClassName("submenu");
        layout.getStyle().set("background-color", backgroundcolor);
        layout.addClickListener(e -> layout.getUI().ifPresent(ui -> ui.navigate(route)));
        return  layout;
    }


}

