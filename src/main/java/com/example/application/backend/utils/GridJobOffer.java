package com.example.application.backend.utils;

import com.example.application.backend.entities.JobOfferEntity;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class GridJobOffer {
    private String filename;
    private String keyword;
    private Anchor downloadLink;
    private  String title;
    private String location;
    private String type;
    private String category;

    public GridJobOffer(String filename, String keyword, Anchor downloadLink, String title, String location, String type, String category) {
        this.filename = filename;
        this.keyword = keyword;
        this.downloadLink = downloadLink;
        this.category = category;
        this.location = location;
        this.type =  type;
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
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
    public String getFilename(){return filename;}

    public Anchor getDownloadLink() {
        downloadLink.setTarget("_blank");
        return downloadLink;
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
            }), filename);
        }

        public static List<GridJobOffer> convertJobOfferEntities(List<JobOfferEntity> jobOfferEntities) {
            List<GridJobOffer> gridJobOffers = new ArrayList<>();
            for (JobOfferEntity jobOfferEntity : jobOfferEntities) {
                GridJobOffer gridJobOffer = new GridJobOffer(jobOfferEntity.getFileName(), jobOfferEntity.getKeyword(),
                        createDownloadLink(jobOfferEntity.getPath(),jobOfferEntity.getFileName()),
                        jobOfferEntity.getCategory(),jobOfferEntity.getLocation(),
                                jobOfferEntity.getType(),jobOfferEntity.getTitle());
                gridJobOffers.add(gridJobOffer);
            }
            return gridJobOffers;
        }

    }

}
