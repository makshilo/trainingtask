package com.qulix.shilomy.trainingtask.web.repository.impl;

import com.qulix.shilomy.trainingtask.web.entity.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.repository.ProjectRepository;
import com.qulix.shilomy.trainingtask.web.repository.ProjectSpecification;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {
    private final List<ProjectEntity> projectList;

    public ProjectRepositoryImpl(List<ProjectEntity> projectList) {
        this.projectList = projectList;
    }

    @Override
    public void addProject(ProjectEntity project) {
        projectList.add(project);
    }

    @Override
    public void removeProject(ProjectEntity project) {
        projectList.remove(project);
    }

    @Override
    public void updateProject(ProjectEntity project) {
        int index = projectList.indexOf(project);
        if (index == -1) {
            throw new RuntimeException("Project not found");
        }
        projectList.set(index, project);
    }

    @Override
    public List<ProjectEntity> query(ProjectSpecification specification) {
        List<ProjectEntity> suitableProjects = new ArrayList<>();
        for (ProjectEntity project : projectList) {
            if (specification.specified(project)) {
                suitableProjects.add(project);
            }
        }
        return suitableProjects;
    }
}