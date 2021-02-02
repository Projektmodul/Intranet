package com.example.application.ui.horizontal.center;

import com.example.application.backend.services.links.LinkService;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.example.application.ui.auxiliary.InitData;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


/**
 *  Center i View is a dummy page that shows a specific center
 *
 *  @author Monika Martius, Laura Neuendorf
 *  @version 5.0
 *  @since 15.12.2020
 *  @lastUpdated 01.02.2021 Laura Neuendorf
 */
@Route(value = "centerI", layout = MainView.class)
@PageTitle("CenterI")
public class CenterIView extends Div {

    public CenterIView(PageService pageService, LinkService linkService) {
        setId("centerI");
        setClassName("pageContentPosition");
        addClassName("centerColorscheme");


        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb("Centers"), new Breadcrumb("Center I"));

        InitData initCenterI = new InitData(pageService, linkService);
        this.add(breadcrumbs, initCenterI.setData(10), initCenterI.setLinkData(5));

        setPicture();
    }

    /**
     * This method puts an organization chart at the bottom of the page
     */
    public void setPicture(){
        Image organizationChart = new Image("images/organigramm-centerI.png", "OrganigrammCenterI");
        organizationChart.setId("organizationChart");
        this.add(organizationChart);
    }
}
