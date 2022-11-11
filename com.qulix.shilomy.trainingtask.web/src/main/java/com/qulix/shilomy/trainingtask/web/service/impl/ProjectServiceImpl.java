package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.exception.EntityNotFoundException;
import com.qulix.shilomy.trainingtask.web.service.EntityService;

import java.util.List;
import java.util.logging.Logger;

public class ProjectServiceImpl implements EntityService<ProjectEntity> {
    private static ProjectServiceImpl instance;

    private static final  Logger LOGGER = Logger.getLogger(ProjectServiceImpl.class.getName());

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
     * Метод получения сущности проекта по идентификатору.
     * @param id идентификатор
     * @return сущность работника(EmployeeEntity)
     */
    @Override
    public ProjectEntity get(Long id) {
        return projectDao.read(id).orElseThrow();
    }

    /**
     * Метод поиска всех сущностей проекта.
     * @return список найденных сущностей
     */
    @Override
    public List<ProjectEntity> findAll() {
        return projectDao.readAll();
    }

    /**
     * Метод добавления сущности проекта.
     * @param projectEntity сущность проекта для добавления
     * @return добавленная сущность
     */
    @Override
    public ProjectEntity add(ProjectEntity projectEntity) {
        try {
            return projectDao.create(projectEntity).orElseThrow();
        } catch (InterruptedException e) {
            LOGGER.severe("Error while adding project: " + e.getMessage());
        }
        return projectEntity;
    }

    /**
     * Метод обновления сущности проекта.
     * @param projectEntity сущность которя содержит параметры для обновления
     * @return обновлённая сущность
     */
    @Override
    public ProjectEntity update(ProjectEntity projectEntity) {
        try {
            ProjectEntity project = projectDao.update(projectEntity).orElseThrow();
            return get(project.getId());
        } catch (InterruptedException e) {
            LOGGER.severe("Error while updating project: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            LOGGER.severe("Project not found " + e.getMessage());
        }
        return projectEntity;
    }

    /**
     * Метод удаления сущности по идентификатору.
     * @param id идентификатор
     * @return результат
     */
    @Override
    public boolean delete(Long id) {
        return projectDao.delete(id);
    }
}
