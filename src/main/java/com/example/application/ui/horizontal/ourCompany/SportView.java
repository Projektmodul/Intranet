package com.example.application.ui.horizontal.ourCompany;

import com.example.application.backend.entities.PageEntity;
import com.example.application.backend.services.pages.PageService;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  FAQ View shows ...
 *
 *  @author Monika Martius
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated 22.01.2021
 */
@Route(value = "sport", layout = MainView.class)
@PageTitle("Sport&Freizeit")
public class SportView extends Div {
    private PageService pageService;
    private H1 pageTitle;
    private H2 pageText;
    private PageEntity pageEntity;

    public SportView(PageService pageService) {
        setId("sport");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");

        pageEntity = pageService.findPageById(7);
        pageTitle = new H1(pageEntity.getTitle());
        pageText = new H2(pageEntity.getContent());

        add(pageTitle,pageText);
    }
}
