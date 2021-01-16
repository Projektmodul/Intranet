/*created @ Litharshiga Sivarasa */

package com.example.application.ui.horizontal.ourCompany;

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
import com.vaadin.flow.router.RouteAlias;

@Route(value = "ourCompany", layout = MainView.class)
@PageTitle("Unser Unternehmen")
@CssImport("./styles/views/main/overViewPage.css")
public class OurCompanyView extends Div {

    public OurCompanyView() {
        setId("ourCompanyViewLayout");
        setClassName("pageContentPositionOurCompany");


        H1 headLineOurCompany = new H1("Unsere Unternehmen");
        headLineOurCompany.setId("headlineOurCompany");
        headLineOurCompany.setClassName("overviewHStyle");

        Label firstQuote = new Label("Wir sind täglich");
        firstQuote.setClassName("firstQuote");

        Label secondQuote = new Label("für sie da");
        secondQuote.setClassName("secondQuote");


        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.setHeight("450px");
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);

        Component component1 = createComponent(new Icon(VaadinIcon.HANDSHAKE), "#A00505", "Willkommen", "welcome");
        Component component2 = createComponent(new Icon(VaadinIcon.INFO), "#A00505", "Über uns", "aboutUs");
        Component component3 = createComponent(new Icon(VaadinIcon.NEWSPAPER), "#A00505", "Nachrichten", "news");
        Component component4 = createComponent(new Icon(VaadinIcon.EXIT), "#A00505", "Sport & Freizeit", "sport");
        Component component5 = createComponent(new Icon(VaadinIcon.DOLLAR), "#A00505", "Stellenangebote", "career");
        layout.add(component1, component2, component3, component4, component5);

        add(headLineOurCompany,firstQuote, secondQuote, layout);

    }

    private Component createComponent(Icon icon, String backgroundcolor, String spanText, String route) {

        icon.setClassName("icons");
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

