package com.example.application.ui.horizontal.ourCompany;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "career", layout = MainView.class)
@PageTitle("Stellenangebote")
public class CareerView extends Div {

    public CareerView() {
        setId("career-view");
        setClassName("pageContentPosition");
        add(new Text("Stellenangebote"));
    }

}
