package com.qulix.shilomy.trainingtask.data.service.impl;

import com.qulix.shilomy.trainingtask.data.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.data.exception.DatabaseAccessException;
import com.qulix.shilomy.trainingtask.data.service.EntityService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Сервис для проекта
 */
public class ProjectServiceImpl implements EntityService<ProjectEntity> {
    /**
     * Единственный объект класса
     */
    private static ProjectServiceImpl instance;

    /**
     * Объект доступа к данным проекта
     */
    private final ProjectDao projectDao;

    private final Logger logger = Logger.getLogger(ProjectServiceImpl.class.getName());

    private ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * Получение объекта класса
     *
     * @return объект ProjectServiceImpl
     */
    public static synchronized ProjectServiceImpl getInstance(ProjectDao projectDao) {
        if (instance == null) {
            instance = new ProjectServiceImpl(projectDao);
        }
        return instance;
    }

    /**
     * Получение проекта по идентификатору.
     *
     * @param id идентификатор
     * @return проект
     */
    @Override
    public ProjectEntity get(Long id) {
        Optional<ProjectEntity> project = Optional.empty();
        try {
            project = projectDao.read(id);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
        return project.orElseThrow();
    }

    /**
     * Поиск всех проектов
     *
     * @return список проектов
     */
    @Override
    public List<ProjectEntity> findAll() {
        try {
            return projectDao.readAll();
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
        return Collections.emptyList();
    }

    /**
     * Добавление проекта
     *
     * @param projectEntity проект
     */
    @Override
    public void add(ProjectEntity projectEntity) {
        try {
            projectDao.create(projectEntity);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }

    /**
     * Обновление проекта
     *
     * @param projectEntity проект
     */
    @Override
    public void update(ProjectEntity projectEntity) {
        try {
            projectDao.update(projectEntity);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }

    /**
     * Удаление проекта по идентификатору.
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        try {
            projectDao.delete(id);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }
}
