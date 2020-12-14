package com.example.application.ui.horizontal.centers;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Center1", layout = MainView.class)
@PageTitle("Center1")
public class Center1View extends Div {

    public Center1View() {
        setId("center1-view");
        add(new Text("Center mit mehr Text als sonst1"));
    }

}
