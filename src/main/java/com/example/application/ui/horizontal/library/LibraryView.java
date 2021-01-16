/*created @ Litharshiga Sivarasa */

package com.example.application.ui.horizontal.library;

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
 *  Library View shows ...
 *
 *  @author Litharshi Sivarasa
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 17.01.2021
 */
@Route(value = "library", layout = MainView.class)
@PageTitle("Bibliothek")

public class LibraryView extends Div {

    public LibraryView() {
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

        H1 headLineLibrary = new H1("Bibliothek");

        Label firstQuote = new Label("Gut gelaunt die Welt");
        firstQuote.setClassName("firstQuote");

        Label secondQuote = new Label("entdecken");
        secondQuote.setClassName("secondQuote");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component component1 = createComponent(new Icon(VaadinIcon.CLIPBOARD_TEXT), "#2F7C78", "Unterlagen", "documents");

        Component component2 = createComponent(new Icon(VaadinIcon.BOOK), "#2F7C78", "Wiki", "wiki");

        Component component3 = createComponent(new Icon(VaadinIcon.ARCHIVE), "#2F7C78", "Archiv", "archive");

        Component component4 = createComponent(new Icon(VaadinIcon.FILM), "#2F7C78", "Medien", "media");

        Component component5 = createComponent(new Icon(VaadinIcon.QUESTION), "#2F7C78", "FAQ", "fAQ");

        layout.add(component1, component2, component3, component4, component5);


        add(headLineLibrary,firstQuote,secondQuote, layout);

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

