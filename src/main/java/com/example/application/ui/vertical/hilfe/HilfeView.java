package com.example.application.ui.vertical.hilfe;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Hilfe", layout = MainView.class)
@PageTitle("Hilfe")
public class HilfeView extends Div {

    public HilfeView() {
        setId("hilfe-view");
        add(new Text("Content placeholder"));
    }

}
