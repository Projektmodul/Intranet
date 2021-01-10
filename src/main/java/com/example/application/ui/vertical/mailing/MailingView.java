package com.example.application.ui.vertical.mailing;

import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "mailing", layout = ContentHolder.class)
@PageTitle("Mailing")
public class MailingView extends Div {

    public MailingView() {
        setId("contentViewBlue");
        setClassName("pageContentPosition");
        add(new Text("Content placeholder"));
        add(new Text("Huhu :)"));
    }

}
