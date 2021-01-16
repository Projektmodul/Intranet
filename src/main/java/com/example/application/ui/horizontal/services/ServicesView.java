/*created @ Litharshiga Sivarasa */

package com.example.application.ui.horizontal.services;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "services", layout = MainView.class)
@PageTitle("Services")
public class ServicesView extends Div {

    public ServicesView() {
        setId("servicesViewLayout");
        setClassName("pageContentPositionServices");

        H1 headLineServices = new H1("Services");
        headLineServices.setId("headlineServices");
        headLineServices.setClassName("overviewHStyle");

        Label firstQuote = new Label("Mit 100 Prozent");
        firstQuote.setClassName("firstQuote");

        Label secondQuote = new Label("Ökostrom");
        secondQuote.setClassName("secondQuote");


        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.setHeight("450px");
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);

        Component component1 = createComponent(new Icon(VaadinIcon.CHART_TIMELINE), "#FF5621", "Zeitkonto", "");

        Component component2 = createComponent(new Icon(VaadinIcon.PYRAMID_CHART), "#FF5621", "LSA-Meldungen", "");

        Component component3 = createComponent(new Icon(VaadinIcon.CAR), "#FF5621", "Fahrdienst", "");

        Component component4 = createComponent(new Icon(VaadinIcon.FLIGHT_TAKEOFF), "#FF5621", "Dienstreisen", "businessTrip");

        layout.add(component1, component2, component3, component4);

        add(headLineServices,firstQuote,secondQuote, layout);

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


