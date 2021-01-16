package com.example.application.ui.horizontal.ourCompany;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Career View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "career", layout = MainView.class)
@PageTitle("Stellenangebote")
public class CareerView extends Div {

    public CareerView() {
        setClassName("pageContentPosition");
        addClassName("ourCompanyColorscheme");
        add(new Text("Stellenangebote"));
    }

}
