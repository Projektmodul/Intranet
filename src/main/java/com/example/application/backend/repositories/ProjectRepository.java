package com.example.application.backend.repositories;


import com.example.application.backend.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DESCRIPTION
 *
 * @author  Monika Martius and Laura Neuendorf
 * @version 1.0
 * @since   26.01.2021
 */

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
    ProjectEntity findByProjectId(int projectId);
}
