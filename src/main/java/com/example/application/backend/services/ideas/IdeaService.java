package com.example.application.backend.services.ideas;

import com.example.application.backend.entities.IdeaEntity;
import com.example.application.backend.repositories.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * DESCRIPTION
 *
 * @author  Jessica Reistel and Litharshiga Sivarasa
 * @version 1.0
 * @since   25.01.2021
 * @lastUpdated 25.01.2021
 */
@Service
public class IdeaService {
    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository){
        this.ideaRepository = ideaRepository;
    }

    public IdeaRepository getIdeaRepository() {
        return ideaRepository;
    }

    public IdeaEntity findById(int ideaId){
        return getIdeaRepository().findByIdeaId(ideaId);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int findMaxId() {
        String sql = "SELECT max(idea_id) from ideas";
        try {
            return jdbcTemplate.queryForObject(sql,Integer.class);
        } catch (Exception e) {
            return 0;
        }
    }
}
