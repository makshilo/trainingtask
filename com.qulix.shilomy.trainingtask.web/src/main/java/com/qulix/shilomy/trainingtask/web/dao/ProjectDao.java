package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;

import java.util.List;

public interface ProjectDao extends EntityDao<ProjectEntity> {
    List<ProjectEntity> receiveProjectByName(String name);
}
