package com.qulix.shilomy.trainingtask.web.service;

import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;

import java.util.List;

public interface TaskService extends EntityService<TaskEntity> {
    List<TaskEntity> findByProject(ProjectEntity project);
}
