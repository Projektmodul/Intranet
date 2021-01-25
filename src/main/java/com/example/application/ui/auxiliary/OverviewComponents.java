package com.example.application.ui.auxiliary;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;

/**
 *  OverviewComponents offers reusable methods that create the components for the overview pages.
 *
 *  @author Vanessa Skowronsky
 *  @version 1.0
 *  @since 25.01.2021
 *  @lastUpdated 25.01.2021 by Vanessa Skowronsky
 */

public class OverviewComponents {
    public OverviewComponents(){

    }

    public static Component createLinkedComponent(Icon icon, String backgroundColor, String spanText, String href){
        icon.setClassName("horizontalBarIcons");

        Span span = new Span(spanText);
        span.setClassName("spanStyle");

        VerticalLayout layout = new VerticalLayout(span, icon);

        layout.setClassName("submenu");
        layout.getStyle().set("background-color", backgroundColor);

        Anchor anchorComponent = new Anchor(href,layout);

        anchorComponent.setTarget("_blank");
        anchorComponent.setClassName("anchorComponent");
        anchorComponent.getStyle().set("color", backgroundColor);

        return anchorComponent;
    }

    public static Component createComponent(Icon icon, String backgroundcolor, String spanText, String route) {

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
