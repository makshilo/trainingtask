package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс сервис реализующий методы сервиса для сущности задачи
 */
public class TaskServiceImpl implements EntityService<TaskEntity> {
    private static TaskServiceImpl instance;

    private final TaskDao taskDao;
    private TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public static synchronized TaskServiceImpl getInstance(TaskDao taskDao) {
        if (instance == null) {
            instance = new TaskServiceImpl(taskDao);
        }
        return instance;
    }

    /**
     * Метод получения сущности задачи по идентификатору.
     * @param id идентификатор
     * @return сущность работника(EmployeeEntity)
     */
    @Override
    public TaskEntity get(Long id) {
        return taskDao.read(id);
    }

    /**
     * Метод поиска всех сущностей задач.
     * @return список найденных сущностей
     */
    @Override
    public List<TaskEntity> findAll() {
        return taskDao.readAll();
    }

    /**
     * Метод добавления сущности задачи.
     *
     * @param taskEntity сущность задачи для добавления
     */
    @Override
    public void add(TaskEntity taskEntity) {
        taskDao.create(taskEntity);
    }

    /**
     * Метод обновления сущности задачи.
     *
     * @param taskEntity сущность которя содержит параметры для обновления.
     */
    @Override
    public void update(TaskEntity taskEntity) {
        taskDao.update(taskEntity);
    }

    /**
     * Метод удаления сущности по идентификатору.
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        taskDao.delete(id);
    }

    /**
     * Метод поиска сущностей задач принадлежащих проекту.
     * @param projectEntity сущность проекта
     * @return список задач
     */
    public List<TaskEntity> findByProject(ProjectEntity projectEntity) {
        return findAll().stream().filter(task -> task.getProjectId().equals(projectEntity.getId())).collect(Collectors.toList());
    }
}
