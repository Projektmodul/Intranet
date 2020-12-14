package com.example.application.ui.vertical.kalendar;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Kalendar", layout = MainView.class)
@PageTitle("Kalendar")
public class KalendarView extends Div {

    public KalendarView() {
        setId("kalendar-view");
        add(new Text("Content placeholder"));
    }

}
