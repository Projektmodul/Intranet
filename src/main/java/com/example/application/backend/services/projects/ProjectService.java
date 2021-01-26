package com.example.application.backend.services.projects;

import com.example.application.backend.entities.ProjectEntity;
import com.example.application.backend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
