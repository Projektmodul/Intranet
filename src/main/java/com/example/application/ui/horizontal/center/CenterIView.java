package com.example.application.ui.horizontal.center;

import com.example.application.backend.entities.LinkEntity;
import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.InitData;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
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
 *  Center I View shows ...
 *
 *  @author Monika Martius, Laura Neuendorf
 *  @version 5.0
 *  @since 15.12.2020
 *  @lastUpdated 01.02.2021 Laura Neuendorf
 */
@Route(value = "centerI", layout = MainView.class)
@PageTitle("CenterI")
public class CenterIView extends Div {
    private PageService pageService;
    private PageEntity pageEntity;
    private LinkService linkService;
    private LinkEntity linkEntity;

    public CenterIView(PageService pageService, LinkService linkService) {
        setId("centerI");
        setClassName("pageContentPosition");
        addClassName("centerColorscheme");

        this.pageService = pageService;
        this.linkService = linkService;

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Centers"), new Breadcrumb("Center I"));

        InitData initCenterI = new InitData(pageService, linkService);
        this.add(breadcrumbs, initCenterI.setData(10), initCenterI.setLinkData(5));

        setPicture();
    }

    public void setPicture(){
        Image organizationChart = new Image("images/organigramm-centerI.png", "OrganigrammCenterI");
        organizationChart.setId("organizationChart");
        this.add(organizationChart);
    }
}
