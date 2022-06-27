package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectDao extends EntityDao<ProjectEntity> {
    List<ProjectEntity> receiveProjectByName(String name);
}
