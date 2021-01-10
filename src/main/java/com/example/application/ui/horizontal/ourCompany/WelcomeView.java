package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.ourCompany.WelcomeViewService;
import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "welcome", layout = ContentHolder.class)
@PageTitle("Willkommen")
public class WelcomeView extends Div {

    private  WelcomeViewService welcomeViewService;

    private H1 pageTitle;
    private Span pageContent;
    
    public WelcomeView(WelcomeViewService welcomeViewService) {
        this.welcomeViewService = welcomeViewService;

        setId("welcome-view");
        setClassName("pageContentPosition");

        setData();

    }

    private void setData(){
        PageEntity pageEntity = welcomeViewService.findPageById(1);

        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Span(pageEntity.getContent());

        this.add(pageTitle,pageContent);
    }

}
