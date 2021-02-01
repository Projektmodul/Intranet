package com.example.application.backend.utils;

import com.example.application.backend.entities.JobOfferEntity;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * GridJobOffer class is a POJO (plain old java object) that has title, location, type, category and an
 * anchor downloadLink as attributes.
 *
 * @author Sabrine Gamdou, Anastasiya Jackwerth, Litharshiga Sivarasa
 * @version 2.0
 * @since 01.02.2021
 * @lastUpdated 01.02.2021 by Sabrine Gamdou, Anastasiya Jackwerth
 */
public class GridJobOffer {
    private  String title;
    private String location;
    private String type;
    private String category;
    private Anchor downloadLink;

    public GridJobOffer(Anchor downloadLink, String title, String location, String type, String category) {
        this.downloadLink = downloadLink;
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
        downloadLink.setTarget("_blank");
        return downloadLink;
    }

    /**
     * JobOfferEntitiesConverter is an embedded static helper class with a static method to convert a jobOfferList to a
     * gridJobOfferList and a static method to create an anchor link fromm a location.
     *
     * @author Sabrine Gamdou, Anastasiya Jackwerth, Litharshiga Sivarasa
     * @version 2.0
     * @since 24.01.2021
     * @lastUpdated 01.02.2021 by Sabrine Gamdou, Anastasiya Jackwerth
     */
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
                GridJobOffer gridJobOffer = new GridJobOffer(
                        createDownloadLink(jobOfferEntity.getDocument().getPath(), jobOfferEntity.getDocument().getFileName()),
                        jobOfferEntity.getTitle(), jobOfferEntity.getLocation(), jobOfferEntity.getType(),
                        jobOfferEntity.getCategory());
                gridJobOffers.add(gridJobOffer);
            }
            return gridJobOffers;
        }

    }

}
