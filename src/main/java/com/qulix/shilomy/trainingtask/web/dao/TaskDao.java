package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;

import java.util.List;

public interface TaskDao extends EntityDao<TaskEntity>{

    List<TaskEntity> receiveTaskByProject(ProjectEntity project);
}