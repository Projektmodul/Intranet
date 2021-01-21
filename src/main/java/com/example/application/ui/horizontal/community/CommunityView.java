/*created @ Litharshiga Sivarasa */

package com.example.application.ui.horizontal.community;

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
 *  Community View shows ...
 *
 *  @author Litahshi Sivarasa
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 17.01.2021
 */
@Route(value = "community", layout = MainView.class)
@PageTitle("Community")
public class CommunityView extends Div {

    private PageService pageService;
    private H1 pageTitle;
    private H2 pageText;
    private PageEntity pageEntity;

    public CommunityView(PageService pageService) {
        setId("community");
        setClassName("pageContentPosition");
        addClassName("communityColorscheme");

        pageEntity = pageService.findPageById(11);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new H2(pageEntity.getContent());

        Label secondQuote = new Label("ganz Zentral");
        secondQuote.setClassName("secondQuote");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component component1 = createComponent(new Icon(VaadinIcon.PENCIL), "#F0D12C", "Blog", "blog");

        Component component2 = createComponent(new Icon(VaadinIcon.CLIPBOARD_TEXT), "#F0D12C", "Schwarzes Brett", "noticeBoard");

        Component component3 = createComponent(new Icon(VaadinIcon.HANDS_UP), "#F0D12C", "Ideenmanagement", "ideasManagement");

        layout.add(component1, component2, component3);

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


