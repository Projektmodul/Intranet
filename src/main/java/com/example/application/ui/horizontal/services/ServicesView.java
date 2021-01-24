/*created @ Litharshiga Sivarasa */

package com.example.application.ui.horizontal.services;

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
 *  Services View shows ...
 *
 *  @author Litharshi Sivarasa
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 17.01.2021
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

        Label secondQuote = new Label("Ökostrom");
        secondQuote.setClassName("secondQuote");


        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component component1 = createComponent(new Icon(VaadinIcon.CHART_TIMELINE), "#FF5621", "Zeitkonto", "");

        Component component2 = createComponent(new Icon(VaadinIcon.PYRAMID_CHART), "#FF5621", "LSA-Meldungen", "");

        Component component3 = createComponent(new Icon(VaadinIcon.CAR), "#FF5621", "Fahrdienst", "");

        Component component4 = createComponent(new Icon(VaadinIcon.FLIGHT_TAKEOFF), "#FF5621", "Dienstreisen", "businessTrip");

        layout.add(component1, component2, component3, component4);

        add(pageTitle,pageText,secondQuote, layout);

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
