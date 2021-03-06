package com.example.application.backend.utils.images;

import com.example.application.backend.entities.ImageEntity;
import com.example.application.backend.services.files.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/**
 * This is class that takes care of deleting images on the
 * database and on the server.
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 1.0
 * @since   21.12.2020
 * @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */
public class ImageDeletionManager {

    private ImageService imageService;

    private ImageEntity imageEntity;

    public ImageDeletionManager(ImageEntity imageEntity, ImageService imageService){
        this.imageService = imageService;
        this.imageEntity = imageEntity;
    }

    public void delete(){
        deleteFromServer();
        deleteFromDatabase();
    }

    public void deleteFromServer(){
       deleteFile();
    }

    public void deleteFromDatabase(){
        try{
            this.imageService.delete(imageEntity);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public boolean deleteFile(){
        try{
            return Files.deleteIfExists(
                    Paths.get(imageEntity.getPath()));
        }
        catch(NoSuchFileException e)
        {
            e.printStackTrace();
            return false;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}