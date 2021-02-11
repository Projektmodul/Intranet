package com.example.application.backend.utils.images;

import com.example.application.backend.entities.ImageEntity;
import com.example.application.backend.services.files.ImageService;

import java.io.*;


/**
 * This is class that takes care of creating the image on the
 * database and on the server.
 *
 * @author  Anastasiya Jackwerth, Sabrine Gamdou
 * @version 1.0
 * @since   21.12.2020
 * @lastUpdated 19.01.2021 from Anastasiya Jackwerth, Sabrine Gamdou
 */

public class ImageCreationManager {

    private ImageService imageService;
    private InputStream inputStream;
    private ImageEntity imageEntity;

    private String mimeType;

    public ImageCreationManager(InputStream inputStream, ImageEntity imageEntity,
                                ImageService imageService){

        this.inputStream = inputStream;
        this.imageEntity = imageEntity;

        this.imageService = imageService;
    }

    public void save(){
        saveToDatabase();
        saveToServer();
    }

    /**
     * This method saves the file content(byte[]) on to the filesystem
     */
    public void saveToServer(){
        byte[] fileContent = new byte[]{};
        try{
            fileContent= getArrayFromInputStream(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
        try(BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(
                imageEntity.getPath()))){
            fileOutputStream.write(fileContent);
            fileOutputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveToDatabase(){
        this.imageService.save(imageEntity);
    }

    /**
     * This method transforms the inputStream into a byte array, so it can be later converted to a file
     * @param inputStream is where the file content is saved
     * @return byte[] is the file content in a form of a byte[]
     * @throws IOException
     */
    public byte[] getArrayFromInputStream(InputStream inputStream) throws IOException {
        byte[] bytes;
        byte[] buffer = new byte[1073741824];
        try(BufferedInputStream is = new BufferedInputStream(inputStream)){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int length;
            while ((length = is.read(buffer)) > -1 ) {
                bos.write(buffer, 0, length);
            }
            bos.flush();
            bytes = bos.toByteArray();
        }
        return bytes;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
