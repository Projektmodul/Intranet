package com.example.application.backend.utils;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.entities.JobOfferEntity;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.server.StreamResource;
import sun.jvm.hotspot.debugger.DebuggerUtilities;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class GridJobOffer {
    private  String title;
    private String location;
    private String type;
    private String category;
    private DocumentEntity documentEntity;
    private GridDocument gridDocument;

    public GridJobOffer(GridDocument gridDocument, DocumentEntity documentEntity, String title, String location, String type, String category) {
        this.gridDocument = gridDocument;
        this.documentEntity = documentEntity;
        this.category = category;
        this.location = location;
        this.type = type;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public String getLocation() {
        return location;
    }
    public String getType() {
        return type;
    }
    public String getCategory() {
        return category;
    }

    public Anchor getDownloadLink() {
        gridDocument.getDownloadLink().setTarget("_blank");
        return gridDocument.getDownloadLink();
    }

    public static class JobOfferEntitiesConverter {
        public static Anchor createDownloadLink(String path, String filename) {

            return new Anchor(new StreamResource(filename, () -> {

                try {
                    System.out.println("PATH: " + path);
                    return new FileInputStream(path);
                } catch (Exception e) {
                    return new ByteArrayInputStream(new byte[]{});
                }
            }),  filename);
        }

        public static List<GridJobOffer> convertJobOfferEntities(List<JobOfferEntity> jobOfferEntities) {
            List<GridJobOffer> gridJobOffers = new ArrayList<>();
            for (JobOfferEntity jobOfferEntity : jobOfferEntities) {
                GridJobOffer gridJobOffer = new GridJobOffer(documentEntity.getFileName(),jobOfferEntity.getKeyword(),
                        createDownloadLink(jobOfferEntity.getPath(),jobOfferEntity.getFileName()),
                        jobOfferEntity.getCategory(),jobOfferEntity.getLocation(),
                                jobOfferEntity.getType(),jobOfferEntity.getTitle());
                gridJobOffers.add(gridJobOffer);
            }
            return gridJobOffers;
        }

    }

}
