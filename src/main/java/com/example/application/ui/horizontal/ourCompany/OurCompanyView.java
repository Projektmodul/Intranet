package com.example.application.ui.horizontal.ourCompany;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "ourCompany", layout = MainView.class)
@PageTitle("Unser Unternehmen")
@RouteAlias(value = "", layout = MainView.class)
public class OurCompanyView extends Div {

    public OurCompanyView() {
        setId("ourCompany-view");
        setClassName("pageContentPosition");
        add(new Text(""));
    }
}
