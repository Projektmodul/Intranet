package com.example.application.backend.services.files;

import com.example.application.backend.entities.JobOfferEntity;
import com.example.application.backend.repositories.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


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

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int findMaxId() {
        String sql = "SELECT max(job_offer_id) from job_offers";
        try {
            return jdbcTemplate.queryForObject(sql,Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }
}
