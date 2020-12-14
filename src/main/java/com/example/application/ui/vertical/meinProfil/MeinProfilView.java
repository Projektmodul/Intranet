package com.example.application.ui.vertical.meinProfil;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.ui.MainView;

@Route(value = "MeinProfil", layout = MainView.class)
@PageTitle("Mein Profil")
public class MeinProfilView extends Div {

    public MeinProfilView() {
        setId("meinProfil-view");
        add(new Text("Content placeholder"));
    }

}
