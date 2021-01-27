/*created @ Litharshiga Sivarasa */

package com.example.application.ui.horizontal.community;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.componentfactory.Breadcrumb;
import com.vaadin.componentfactory.Breadcrumbs;
import com.example.application.ui.auxiliary.OverviewComponents;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Community View shows ...
 *
 *  @author Litahshi Sivarasa, Vanessa Skowronsky
 *  @version 4.0
 *  @since 15.12.2020
 *  @lastUpdated 27.01.2021 by Jessica Reistel
 */
@Route(value = "community", layout = MainView.class)
@PageTitle("Community")
public class CommunityView extends Div {

    private PageService pageService;
    private H1 pageTitle;
    private H2 firstQuote;
    private H2 secondQuote;
    private HorizontalLayout layout;

    public CommunityView(PageService pageService) {
        this.pageService = pageService;

        setId("community");
        setClassName("pageContentPosition");
        addClassName("communityColorscheme");

        Breadcrumbs breadcrumbs = new Breadcrumbs();
        breadcrumbs.add(new Breadcrumb("Home"), new Breadcrumb(pageEntity.getTitle()));

        setContent();

        add(breadcrumbs,pageTitle, firstQuote, secondQuote, layout);
    }

    private void setContent(){
        PageEntity pageEntity = pageService.findPageById(19);

        pageTitle = new H1(pageEntity.getTitle());
        firstQuote = new H2(pageEntity.getContent());
        secondQuote = new H2("ganz Zentral");
        firstQuote.setClassName("firstQuote");
        secondQuote.setClassName("secondQuote");

        layout = new HorizontalLayout();
        //layout.setPadding(true);
        layout.addClassName("justifyContentCenter");

        Component componentBlog = OverviewComponents.createLinkedComponent(new Icon(VaadinIcon.PENCIL), "#F0D12C", "Blog", "https://blog.bsag.de/");

        Component componentNoticeBoard = OverviewComponents.createComponent(new Icon(VaadinIcon.CLIPBOARD_TEXT), "#F0D12C", "Schwarzes Brett", "noticeBoard");

        Component componentIdeaManagement = OverviewComponents.createComponent(new Icon(VaadinIcon.HANDS_UP), "#F0D12C", "Ideenmanagement", "ideasManagement");

        layout.add(componentBlog, componentNoticeBoard, componentIdeaManagement);

    }

}


