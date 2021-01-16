package com.example.application.ui.vertical.help;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Help View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "help", layout = MainView.class)
@PageTitle("Hilfe")
public class HelpView extends Div {

    public HelpView() {
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");
        add(new Text("Content placeholder"));

    }

}
