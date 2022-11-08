package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;

import java.util.List;

/**
 * Интерфейс описывющий реализацию Дао классов для сущности проекта.
 */
public interface ProjectDao extends EntityDao<ProjectEntity> {
    /**
     * Описание метода получение проекта по его имени
     * @param name имя проекта
     * @return сущность проекта
     */
    List<ProjectEntity> receiveProjectByName(String name);
}
