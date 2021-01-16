/*created @ Litharshiga Sivarasa */

package com.example.application.ui.horizontal.projects;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Projects View shows ...
 *
 *  @author Litharshi Sivarasa
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 17.01.2021
 */
@Route(value = "projects", layout = MainView.class)
@PageTitle("Projekte")
public class ProjectsView extends Div {

    public ProjectsView() {
        setClassName("pageContentPosition");
        addClassName("projectsColorscheme");

        H1 headLineProject = new H1("Projekte");

        Label firstQuote = new Label("Dynamisch in die");
        firstQuote.setClassName("firstQuote");

        Label secondQuote = new Label("Überseestadt");
        secondQuote.setClassName("secondQuote");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component component1 = createComponent(new Icon(VaadinIcon.TRAIN), "#581092", "Nordlicht", "nordlicht");

        layout.add(component1);

        add(headLineProject, firstQuote, secondQuote, layout);

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
        return layout;
    }
}