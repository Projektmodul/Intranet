package com.example.application.backend.repositories;

import com.example.application.backend.entities.JobOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a repository for the JobOfferEntity.
 * @author Sabrine Gamdou
 * @version 2.0
 * @since 05.01.2021
 * @lastUpdatet 31.01.2021 by Litharshiga Sivarasa
 */
public interface JobOfferRepository extends JpaRepository<JobOfferEntity, Integer> {
    JobOfferEntity findByJobOfferId(int jobOfferId);

}
