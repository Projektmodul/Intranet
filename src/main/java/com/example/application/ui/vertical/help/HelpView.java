package com.example.application.ui.vertical.help;

import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.InitData;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.html.Div;
import com.example.application.ui.horizontal.library.FAQView;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

/**
 *  Help View shows a description of the intranet, a link to the FAQ and an email address for questions
 *
 *  @author Jessica Reistel
 *  @version 4.0
 *  @since 15.12.2020
 *  @lastUpdated 07.02.2021 by Jessica Reistel
 */
@Route(value = "help", layout = MainView.class)
@PageTitle("Hilfe")
public class HelpView extends Div {

    public HelpView(PageService pageService, LinkService linkService) {
        setId("help");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Hilfe"));

        RouterLink faqLink = new RouterLink("Antworten auf häufig gestellte Fragen", FAQView.class);

        InitData initHelp = new InitData(pageService, linkService);

        HorizontalLayout horizontalLayout = new HorizontalLayout(initHelp.setLinkData(3), faqLink);

        add(breadcrumbs,initHelp.setData(25) , horizontalLayout);
    }
}
