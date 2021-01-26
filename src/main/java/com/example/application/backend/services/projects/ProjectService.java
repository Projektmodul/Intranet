package com.example.application.backend.services.projects;

import com.example.application.backend.entities.ProjectEntity;
import com.example.application.backend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DESCRIPTION
 *
 * @author  Monika Martius and Laura Neuendorf
 * @version 1.0
 * @since   26.01.2021
 */

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public ProjectEntity findById(int projectId){
        return getProjectRepository().findByProjectId(projectId);
    }

    public ProjectRepository getProjectRepository(){
        return projectRepository;
    }
}
