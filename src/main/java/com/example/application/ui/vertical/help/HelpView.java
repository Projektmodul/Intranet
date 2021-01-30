package com.example.application.ui.vertical.help;

import com.example.application.backend.entities.LinkEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.example.application.ui.horizontal.library.FAQView;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

/**
 *  Help View shows ...
 *
 *  @author Jessica Reistel
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 26.01.2021
 */
@Route(value = "help", layout = MainView.class)
@PageTitle("Hilfe")
public class HelpView extends Div {

    private PageService pageService;
    private PageEntity pageEntity;
    private LinkService linkService;
    private H1 pageTitle;
    private Paragraph pageContent;
    private HorizontalLayout links;

    public HelpView(PageService pageService, LinkService linkService) {
        setId("help");
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");

        this.pageService = pageService;
        this.linkService = linkService;

        setContent();

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        add(breadcrumbs, pageTitle, pageContent, links);
    }

    private void setContent(){
        pageEntity = pageService.findPageById(25);
        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        LinkEntity linkEntity = linkService.findById(3);
        Anchor mailLink = new Anchor(linkEntity.getUrl(), linkEntity.getTitle());

        RouterLink faqLink = new RouterLink("Antworten auf häufig gestellte Fragen", FAQView.class);

        links = new HorizontalLayout();
        links.add(faqLink, mailLink);
    }
}
