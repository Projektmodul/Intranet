package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.ui.ContentHolder;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a grid-view for all documents.
 *
 * @author  Sabrine Gamdou, Anastasiya Jackwerth
 * @version 1.0
 * @since   14.12.2020
 * @lastUpdated 12.01.2021
 */

@Route(value = "documents", layout = ContentHolder.class)
@PageTitle("Unterlagen")
public class DocumentsView extends Div {

    public DocumentsView() {
        setClassName("pageContentPosition");

        /*
        * Temporary Dummy-Data, will be deleted after Back-End is implemented
        * ------------------------------------------------------------------------
        * */

        DocumentEntity documentManagement = new DocumentEntity();
        documentManagement.setFileName("Verwaltung-Dokument");
        documentManagement.setKeyword("Verwaltung");

        DocumentEntity documentManagement2 = new DocumentEntity();
        documentManagement2.setFileName("Verwaltung-Dokument");
        documentManagement2.setKeyword("Verwaltung");

        DocumentEntity documentDrivingService = new DocumentEntity();
        documentDrivingService.setFileName("Fahrdienst-Dokument");
        documentDrivingService.setKeyword("Fahrdienst");

        DocumentEntity documentDrivingService2 = new DocumentEntity();
        documentDrivingService2.setFileName("Fahrdienst-Dokument");
        documentDrivingService2.setKeyword("Fahrdienst");

        DocumentEntity documentWorkshop = new DocumentEntity();
        documentWorkshop.setFileName("Werkstatt-Dokument");
        documentWorkshop.setKeyword("Werkstatt");

        DocumentEntity documentWorkshop2 = new DocumentEntity();
        documentWorkshop2.setFileName("Werkstatt-Dokument");
        documentWorkshop2.setKeyword("Werkstatt");

        DocumentEntity documentOther = new DocumentEntity();
        documentOther.setFileName("Sonstige-Dokument");
        documentOther.setKeyword("Sonstige");

        DocumentEntity documentOther2 = new DocumentEntity();
        documentOther2.setFileName("Sonstige-Dokument");
        documentOther2.setKeyword("Sonstige");

        documentsList = new ArrayList<>();
        documentsList.add(documentManagement);
        documentsList.add(documentDrivingService);
        documentsList.add(documentDrivingService2);
        documentsList.add(documentManagement2);
        documentsList.add(documentWorkshop);
        documentsList.add(documentWorkshop2);
        documentsList.add(documentOther);
        documentsList.add(documentOther2);
        /*----------------------------------------------------------------------*/

        initializePageContent();
        initializeTreeGrid();

        this.getStyle().set("width","100%");
    }

}
