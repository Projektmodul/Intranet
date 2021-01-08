package com.example.application.ui.horizontal.community;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "ideasManagement", layout = ContentHolder.class)
@PageTitle("Ideenmanagement")
public class IdeasManagementView extends Div {

    public IdeasManagementView() {
        setId("communityView");
        setClassName("pageContentPosition");
        add(new Text("Ideenmanagement"));
    }

}
