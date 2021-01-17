package com.example.application.ui.horizontal.ourCompany;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  News View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "news", layout = MainView.class)
@PageTitle("Nachrichten")
public class NewsView extends Div {

    public NewsView() {
        setId("news");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");
        add(new Text("Nachrichten"));
    }

}
