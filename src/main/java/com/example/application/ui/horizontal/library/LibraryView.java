/*created @ Litharshiga Sivarasa */

package com.example.application.ui.horizontal.library;

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

@Route(value = "library", layout = ContentHolder.class)
@PageTitle("Bibliothek")
@CssImport("./styles/views/main/overViewPage.css")
public class LibraryView extends Div {

    public LibraryView() {
        setId("libraryViewLayout");
        setClassName("pageContentPositionLibrary");

        H1 headLineLibrary = new H1("Bibliothek");
        headLineLibrary.setId("headlineLibrary");
        headLineLibrary.setClassName("overviewHStyle");

        Label firstQuote = new Label("Gut gelaunt die Welt");
        firstQuote.setClassName("firstQuote");

        Label secondQuote = new Label("entdecken");
        secondQuote.setClassName("secondQuote");


        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.setHeight("450px");
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);

        Component component1 = createComponent(new Icon(VaadinIcon.CLIPBOARD_TEXT), "#2F7C78", "Unterlagen", "documents");

        Component component2 = createComponent(new Icon(VaadinIcon.VIMEO), "#2F7C78", "Wiki", "wiki");

        Component component3 = createComponent(new Icon(VaadinIcon.ARCHIVE), "#2F7C78", "Archiv", "archive");

        Component component4 = createComponent(new Icon(VaadinIcon.FILM), "#2F7C78", "Medien", "media");

        Component component5 = createComponent(new Icon(VaadinIcon.QUESTION), "#2F7C78", "FAQ", "fAQ");

        layout.add(component1, component2, component3, component4, component5);

        add(headLineLibrary,firstQuote,secondQuote, layout);

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

