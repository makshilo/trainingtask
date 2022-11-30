package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;

import java.util.List;

/**
 * Сервис для модели проекта
 */
public class ProjectServiceImpl implements EntityService<ProjectEntity> {

    // Объект одиночка
    private static ProjectServiceImpl instance;

    // Объект дао для модели проекта
    private final ProjectDao projectDao;

    private ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public static synchronized ProjectServiceImpl getInstance(ProjectDao projectDao) {
        if (instance == null) {
            instance = new ProjectServiceImpl(projectDao);
        }
        return instance;
    }

    /**
     * Получение проекта по идентификатору.
     * @param id идентификатор
     * @return модель проекта
     */
    @Override
    public ProjectEntity get(Long id) {
        return projectDao.read(id);
    }

    /**
     * Поиск всех проектов
     * @return список найденных проектов
     */
    @Override
    public List<ProjectEntity> findAll() {
        return projectDao.readAll();
    }

    /**
     * Добавление проекта
     * @param projectEntity модель проекта
     */
    @Override
    public void add(ProjectEntity projectEntity) {
        projectDao.create(projectEntity);
    }

    /**
     * Обновление проекта
     * @param projectEntity модель проекта
     */
    @Override
    public void update(ProjectEntity projectEntity) {
        projectDao.update(projectEntity);
    }

    /**
     * Удаление проекта по идентификатору.
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        projectDao.delete(id);
    }
}
