package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;

import java.util.List;

/**
 * Класс сервис реализующий методы сервиса для сущности проекта
 */
public class ProjectServiceImpl implements EntityService<ProjectEntity> {
    private static ProjectServiceImpl instance;

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
        return projectDao.read(id);
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
     */
    @Override
    public void add(ProjectEntity projectEntity) {
        projectDao.create(projectEntity);
    }

    /**
     * Метод обновления сущности проекта.
     * @param projectEntity сущность которя содержит параметры для обновления
     */
    @Override
    public void update(ProjectEntity projectEntity) {
        projectDao.update(projectEntity);
    }

    /**
     * Метод удаления сущности по идентификатору.
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        projectDao.delete(id);
    }
}
