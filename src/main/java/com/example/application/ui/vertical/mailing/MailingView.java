package com.example.application.ui.vertical.mailing;

import com.example.application.ui.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *  Mailing View shows ...
 *
 *  @author
 *  @version 1.0
 *  @since 15.12.2020
 *  @lastUpdated
 */
@Route(value = "mailing", layout = MainView.class)
@PageTitle("Mailing")
public class MailingView extends Div {

    public MailingView() {
        setClassName("pageContentPosition");
        addClassName("homeColorscheme");
        add(new Text("Content placeholder"));
        add(new Text("Huhu :)"));
    }

}
