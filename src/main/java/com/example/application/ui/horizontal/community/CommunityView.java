package com.example.application.ui.horizontal.community;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "community", layout = MainView.class)
@PageTitle("Community")
public class CommunityView extends Div {

    public CommunityView() {
        setId("communityView");
        setClassName("pageContentPosition");
        add(new Text("Übersicht Community"));
    }

}
