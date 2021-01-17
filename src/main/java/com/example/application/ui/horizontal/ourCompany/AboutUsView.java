package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.ourCompany.AboutUsViewService;
import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "aboutUs", layout = MainView.class)
@PageTitle("Über Uns")
public class AboutUsView extends Div {

    private AboutUsViewService aboutUsViewService;

    private H1 pageTitle;
    private Span pageContent;

    public AboutUsView(AboutUsViewService aboutUsViewService) {
        this.aboutUsViewService = aboutUsViewService;

        setId("aboutUs-view");
        setClassName("pageContentPosition");

        setData();
    }

    private void setData(){
        PageEntity pageEntity = aboutUsViewService.findPageById(4);

        pageTitle = new H1(pageEntity.getTitle());
        pageContent = new Span(pageEntity.getContent());

        this.add(pageTitle, pageContent);
    }

}
