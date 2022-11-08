package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;

import java.util.List;

/**
 * Интерфейс описывющий реализацию Дао классов для сущности задачи.
 */
public interface TaskDao extends EntityDao<TaskEntity>{
    /**
     * Описание метода получения задачи по проекту.
     * @param project проект который содержит задачу.
     * @return сущность задачи.
     */
    List<TaskEntity> receiveTaskByProject(ProjectEntity project);
}