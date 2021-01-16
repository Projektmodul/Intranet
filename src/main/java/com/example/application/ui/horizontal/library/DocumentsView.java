package com.example.application.ui.horizontal.library;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.ui.ContentHolder;
import com.example.application.ui.MainView;
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
 * Documents View shows a grid-view for all documents.
 *
 * @author  Sabrine Gamdou, Anastasiya Jackwerth
 * @version 1.0
 * @since   12.01.2021
 * @lastUpdated 17.01.2021
 */

@Route(value = "documents", layout = MainView.class)
@PageTitle("Unterlagen")
public class DocumentsView extends Div {

    private List<DocumentEntity> documentsList;

    private TreeGrid<DocumentEntity> documentsGrid;
    private VerticalLayout pageContentLayout;
    private H1 pageTitle;

    public DocumentsView() {
        setClassName("pageContentPosition");
        addClassName("libraryColorscheme");

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

    public void initializeTreeGrid(){
        documentsGrid = new TreeGrid<>();

        documentsGrid.setItems(documentsList);

        documentsGrid.addHierarchyColumn(DocumentEntity::getKeyword).setHeader("Abteilung");
        documentsGrid.addColumn(DocumentEntity::getFileName).setHeader("Dateiname");

        documentsGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        pageContentLayout.add(documentsGrid);
    }

    public void initializePageContent(){
        pageTitle = new H1("Unterlagen");
        pageContentLayout = new VerticalLayout(pageTitle);

        this.add(pageContentLayout);
    }

}
