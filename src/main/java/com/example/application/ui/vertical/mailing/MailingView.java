package com.example.application.ui.vertical.mailing;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Mailing", layout = MainView.class)
@PageTitle("Mailing")
public class MailingView extends Div {

    public MailingView() {
        setId("mailing-view");
        add(new Text("Content placeholder"));
    }

}
