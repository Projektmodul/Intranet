package com.example.application.backend.repositories;

import com.example.application.backend.entities.JobOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOfferRepository extends JpaRepository<JobOfferEntity, Integer> {
}
