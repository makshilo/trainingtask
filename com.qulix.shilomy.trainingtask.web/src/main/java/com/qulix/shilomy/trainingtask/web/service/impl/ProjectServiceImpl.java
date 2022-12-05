package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.exception.DatabaseAccessException;
import com.qulix.shilomy.trainingtask.web.service.EntityService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Сервис для модели проекта
 */
public class ProjectServiceImpl implements EntityService<ProjectEntity> {

    // Объект одиночка
    private static ProjectServiceImpl instance;

    // Объект дао для модели проекта
    private final ProjectDao projectDao;

    private final Logger logger = Logger.getLogger(ProjectServiceImpl.class.getName());

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
     * @return список найденных проектов
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
     * @param projectEntity модель проекта
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
     * @param projectEntity модель проекта
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
