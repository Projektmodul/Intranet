package com.example.application.ui.horizontal.centers;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "Centers", layout = MainView.class)
@PageTitle("Centers")
public class CentersView extends Div {

    public CentersView() {
        setId("centers-view");
        add(new Text("Content placeholder"));
    }

}
