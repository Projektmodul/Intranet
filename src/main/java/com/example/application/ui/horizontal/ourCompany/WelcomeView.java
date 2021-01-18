package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.ourCompany.WelcomeViewService;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Welcome View shows ...
 *
 *  @author Sabrine Gamdou, Jessica Reistel, Laura Neuendorf
 *  @version 2.0
 *  @since 15.12.2020
 *  @lastUpdated 18.01.2021
 */
@Route(value = "welcome", layout = MainView.class)
@PageTitle("Willkommen")
public class WelcomeView extends Div {

    private  WelcomeViewService welcomeViewService;

    private H1 pageTitle;
    private Paragraph pageContent;
    
    public WelcomeView(WelcomeViewService welcomeViewService) {
        this.welcomeViewService = welcomeViewService;

        setId("welcome");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        setData();

    }

    private void setData(){
        PageEntity pageEntity = welcomeViewService.findPageById(1);

        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Paragraph(pageEntity.getContent());
        pageContent.getElement().setProperty("innerHTML", pageEntity.getContent());

        this.add(pageTitle,pageContent);
    }

}
