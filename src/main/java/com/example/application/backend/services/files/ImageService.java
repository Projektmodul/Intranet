package com.example.application.backend.services.files;

import com.example.application.backend.entities.DocumentEntity;
import com.example.application.backend.entities.ImageEntity;
import com.example.application.backend.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    public ImageRepository getImageRepository(){
        return imageRepository;
    }

    public void save(ImageEntity imageEntity){
        getImageRepository().saveAndFlush(imageEntity);
    }

    public ImageEntity findImageById(int imageId) {
        return getImageRepository().findByImageId(imageId);
    }

    public void delete (ImageEntity imageEntity){
        getImageRepository().delete(imageEntity);
    }
}
