package com.example.application.ui.horizontal.center;

import com.example.application.backend.entities.LinkEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;

/**
 *  FAQ View shows ...
 *
 *  @author Monika Martius, Jessica Reistel, Laura Neuendorf
 *  @version 4.0
 *  @since 15.12.2020
 *  @lastUpdated 29.01.2021 by Laura Neuendorf
 */
@Route(value = "centerI", layout = MainView.class)
@PageTitle("CenterI")
public class CenterIView extends Div {
    private PageService pageService;
    private H1 pageTitle;
    private PageEntity pageEntity;
    private LinkService linkService;
    private LinkEntity linkEntity;
    private Paragraph pageContent;
    private HorizontalLayout link;

    public CenterIView(PageService pageService, LinkService linkService) {
        setId("centerI");
        setClassName("pageContentPosition");
        addClassName("centerColorscheme");

        this.pageService = pageService;
        this.linkService = linkService;

        setData();
        setPicture();
    }

    /*
     * This method reads the data from the database and displays it on the corresponding page.
     */
    public void setData(){
        pageEntity = pageService.findPageById(10);
        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        linkEntity = linkService.findById(5);
        Anchor mailLink = new Anchor(linkEntity.getUrl(), linkEntity.getTitle());

        link = new HorizontalLayout();
        link.add(mailLink);

        this.add(pageTitle, pageContent, link);
    }

    public void setPicture(){
        Image organizationChart = new Image("images/organigramm-centerI.png", "OrganigrammCenterI");
        organizationChart.setId("organizationChart");
        this.add(organizationChart);
    }
}
