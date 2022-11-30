package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для модели задачи
 */
public class TaskServiceImpl implements EntityService<TaskEntity> {

    // Объект одиночка
    private static TaskServiceImpl instance;

    // Объект дао для модели задачи
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
     * Получение задачи по идентификатору.
     * @param id идентификатор
     * @return модель задачи
     */
    @Override
    public TaskEntity get(Long id) {
        return taskDao.read(id);
    }

    /**
     * Поиск всех задач
     * @return список задач
     */
    @Override
    public List<TaskEntity> findAll() {
        return taskDao.readAll();
    }

    /**
     * Добавление задачи.
     *
     * @param taskEntity модель задачи
     */
    @Override
    public void add(TaskEntity taskEntity) {
        taskDao.create(taskEntity);
    }

    /**
     * Обновление задачи.
     *
     * @param taskEntity модель задачи
     */
    @Override
    public void update(TaskEntity taskEntity) {
        taskDao.update(taskEntity);
    }

    /**
     * Удаление задачи по идентификатору.
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        taskDao.delete(id);
    }

    /**
     * Поиск задач по проекту
     * @param projectEntity модель проекта
     * @return список задач
     */
    public List<TaskEntity> findByProject(ProjectEntity projectEntity) {
        return findAll().stream().filter(task -> task.getProjectId().equals(projectEntity.getId())).collect(Collectors.toList());
    }
}
