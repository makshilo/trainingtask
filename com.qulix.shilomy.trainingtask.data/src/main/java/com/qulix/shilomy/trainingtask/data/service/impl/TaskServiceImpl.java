package com.qulix.shilomy.trainingtask.data.service.impl;

import com.qulix.shilomy.trainingtask.data.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.data.exception.DatabaseAccessException;
import com.qulix.shilomy.trainingtask.data.service.EntityService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Сервис для задачи
 */
public class TaskServiceImpl implements EntityService<TaskEntity> {
    /**
     * Единственный объект класса
     */
    private static TaskServiceImpl instance;

    /**
     * Объект доступа к данным задачи
     */
    private final TaskDao taskDao;

    private final Logger logger = Logger.getLogger(TaskServiceImpl.class.getName());

    private TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    /**
     * Получение объекта класса
     *
     * @return объект TaskServiceImpl
     */
    public static synchronized TaskServiceImpl getInstance(TaskDao taskDao) {
        if (instance == null) {
            instance = new TaskServiceImpl(taskDao);
        }
        return instance;
    }

    /**
     * Получение задачи по идентификатору.
     *
     * @param id идентификатор
     * @return модель задачи
     */
    @Override
    public TaskEntity get(Long id) {
        Optional<TaskEntity> task = Optional.empty();
        try {
            task = taskDao.read(id);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
        return task.orElseThrow();
    }

    /**
     * Поиск всех задач
     *
     * @return список задач
     */
    @Override
    public List<TaskEntity> findAll() {
        try {
            return taskDao.readAll();
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
        return Collections.emptyList();
    }

    /**
     * Добавление задачи.
     *
     * @param taskEntity модель задачи
     */
    @Override
    public void add(TaskEntity taskEntity) {
        try {
            taskDao.create(taskEntity);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }

    /**
     * Обновление задачи.
     *
     * @param taskEntity модель задачи
     */
    @Override
    public void update(TaskEntity taskEntity) {
        try {
            taskDao.update(taskEntity);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }

    /**
     * Удаление задачи по идентификатору.
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        try {
            taskDao.delete(id);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }

    /**
     * Поиск задач по проекту
     *
     * @param projectEntity модель проекта
     * @return список задач
     */
    public List<TaskEntity> findByProject(ProjectEntity projectEntity) {
        return findAll().stream().filter(task -> task.getProjectId().equals(projectEntity.getId())).collect(Collectors.toList());
    }
}
