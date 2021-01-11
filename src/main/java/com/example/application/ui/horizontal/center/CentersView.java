/*created @ Litharshiga Sivarasa */

package com.example.application.ui.horizontal.center;

import com.example.application.ui.ContentHolder;
import com.example.application.ui.MainView;
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

@Route(value = "centers", layout = ContentHolder.class)
@PageTitle("Centers")
@CssImport("./styles/views/main/overViewPage.css")
public class CentersView extends Div {

    public CentersView() {
        setId("centers-view");
        this.setClassName("pageContentPositionCenter");

        H1 headLineCenter = new H1("Center");
        headLineCenter.setId("headlineCenter");

        Label firstQuote = new Label("Wir halten");
        firstQuote.setId("firstQuote");

        Label secondQuote = new Label("zusammen");
        secondQuote.setId("secondQuote");


        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.setHeight("450px");
        layout.setWidth("300px");
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);

        Component component1 = createComponent(new Icon(VaadinIcon.CLUSTER), "#0A5396", "Center I", "centerI");

        layout.add(component1);

        add(headLineCenter,firstQuote, secondQuote, layout);

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


