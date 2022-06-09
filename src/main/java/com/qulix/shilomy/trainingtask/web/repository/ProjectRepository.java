package com.qulix.shilomy.trainingtask.web.repository;

import com.qulix.shilomy.trainingtask.web.entity.ProjectEntity;

import java.util.List;

public interface ProjectRepository {
    void addProject(ProjectEntity project);
    void removeProject(ProjectEntity project);
    void updateProject(ProjectEntity project); // Think it as replace for set

    List<?> query(ProjectSpecification specification);
}
