/*created @ de Boer, Marieke Menna & Monika Martius */
package com.example.application.ui;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/main/mainView.css")
@PWA(name = "BSAG Intranet", shortName = "BSAG Intranet", enableInstallPrompt = false)
@JsModule(value="@vaadin/vaadin-icons/vaadin-icons.js")
@HtmlImport(value="frontend://bower_components/vaadin-icons/vaadin-icons.html")

public class MainView extends VerticalLayout implements RouterLayout {

    private VerticalLayout childWrapper = new VerticalLayout();

    public MainView() {

        setSizeFull();
        setId("mainView");

        //HEADER
        Header header = new Header();

        // WORKSPACE
        childWrapper.addClassName("workspace");
        childWrapper.setSizeFull();

        //SIDEBAR
        SideBar sidebar = new SideBar();

        // MAIN CONTAINER
        HorizontalLayout content = new HorizontalLayout();
        content.add(childWrapper,sidebar);
        content.setClassName("layout");

        VerticalLayout contentPosition = new VerticalLayout();
        contentPosition.add(content);
        contentPosition.setClassName("contentLayout");

        add(header,contentPosition);

    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        // The "content" is the the view you are navigating to
        // The code below sets the childWrapper to hold the view
        childWrapper.getElement().appendChild(content.getElement());
    }


}