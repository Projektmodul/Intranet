package com.example.application.ui.horizontal.projekte;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "Projekte", layout = MainView.class)
@PageTitle("Projekte")
public class ProjekteView extends Div {

    public ProjekteView() {
        setId("projekte-view");
        add(new Text("Content placeholder"));
    }

}
