package com.example.application.backend.repositories;

import com.example.application.backend.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
    ImageEntity findByImageId(int imageId);
}
