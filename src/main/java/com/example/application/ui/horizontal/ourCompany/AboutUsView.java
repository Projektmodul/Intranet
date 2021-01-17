package com.example.application.ui.horizontal.ourCompany;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  AboutUs View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "aboutUs", layout = MainView.class)
@PageTitle("Über Uns")
public class AboutUsView extends Div {

    public AboutUsView() {
        setId("aboutUs");
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");
        add(new Text("Über Uns"));
    }

}
