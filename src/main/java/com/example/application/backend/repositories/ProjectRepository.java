package com.example.application.backend.repositories;


import com.example.application.backend.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * This interface is a repository for the entity project.
 * Each entity has a repository to manage its data exchange.
 * The repository takes over the DAO functionality.
 * @author  Monika Martius and Laura Neuendorf
 * @version 1.0
 * @since   26.01.2021
 */

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
    ProjectEntity findByProjectId(int projectId);
}
