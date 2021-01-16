package com.example.application.ui.horizontal.ourCompany;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Sport View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "sport", layout = MainView.class)
@PageTitle("Sport&Freizeit")
public class SportView extends Div {

    public SportView() {
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");
        add(new Text("Sport & Freizeit"));
    }

}
