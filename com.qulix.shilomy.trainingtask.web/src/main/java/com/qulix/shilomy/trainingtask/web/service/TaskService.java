package com.qulix.shilomy.trainingtask.web.service;

import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;

import java.util.List;

/**
 * Интерфейс сервиса для работы с сущностями задач.
 */
public interface TaskService extends EntityService<TaskEntity> {
    /**
     * Описание метода поиска сущностей задач принадлежащих проекту.
     * @param project сущность проекта
     * @return список задач
     */
    List<TaskEntity> findByProject(ProjectEntity project);
}
