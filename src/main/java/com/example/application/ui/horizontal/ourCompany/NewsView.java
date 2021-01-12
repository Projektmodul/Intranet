package com.example.application.ui.horizontal.ourCompany;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "news", layout = MainView.class)
@PageTitle("Nachrichten")
public class NewsView extends Div {

    public NewsView() {
        setId("ourCompanyView");
        setClassName("pageContentPosition");
        add(new Text("Nachrichten"));
    }

}
