package com.example.application.ui.horizontal.center;

import com.example.application.ui.ContentHolder;
import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "centerI", layout = ContentHolder.class)
@PageTitle("CenterI")
public class CenterIView extends Div {

    public CenterIView() {
        setClassName("pageContentPosition");
        setId("contentViewBlue");
        add(new Text("Center I"));

    }

}
