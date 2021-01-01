package com.example.application.ui.horizontal.ourCompany;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "ourCompany", layout = ContentHolder.class)
@PageTitle("Unser Unternehmen")
public class OurCompanyView extends Div {

    public OurCompanyView() {
        setId("ourCompany-view");
        setClassName("pageContentPosition");
        add(new Text("Übersicht Unser Unternehmen"));
    }
}
