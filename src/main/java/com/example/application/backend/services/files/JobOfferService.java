package com.example.application.backend.services.files;

import com.example.application.backend.entities.JobOfferEntity;
import com.example.application.backend.repositories.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * A service class for the JobOfferEntities
 *
 * @author  Litharshiga Sivarasa
 * @version 2.0
 * @since 31.01.2021
 * @lastUpdated 01.02.2021 by Sabrine Gamdou, Anastasiya Jackwerth
 */
@Service
public class JobOfferService {

    private JobOfferRepository jobOfferRepository;

    @Autowired
    JobOfferService(JobOfferRepository jobOfferRepository){
        this.jobOfferRepository = jobOfferRepository;
    }

    public JobOfferRepository getJobOfferRepository(){return jobOfferRepository; }

    public JobOfferEntity findById(int jobOfferId) {
        return getJobOfferRepository().findByJobOfferId(jobOfferId);
    }

    public void save(JobOfferEntity jobOfferEntity){
        getJobOfferRepository().saveAndFlush(jobOfferEntity);
    }

}
