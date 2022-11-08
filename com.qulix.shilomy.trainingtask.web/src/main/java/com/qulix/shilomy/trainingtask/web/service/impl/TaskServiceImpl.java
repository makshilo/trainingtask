package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.exception.EntityNotFoundException;
import com.qulix.shilomy.trainingtask.web.exception.EntityUpdateException;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService {
    private static TaskServiceImpl instance;

    private static final Logger LOGGER = Logger.getLogger(TaskServiceImpl.class.getName());

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
        return taskDao.read(id).orElse(null);
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
     * @param taskEntity сущность задачи для добавления
     * @return добавленная сущность
     */
    @Override
    public TaskEntity add(TaskEntity taskEntity) {
        try {
            return taskDao.create(taskEntity);
        } catch (InterruptedException | EntityUpdateException e) {
            LOGGER.severe("Error while adding task: " + e.getMessage());
        }
        return null;
    }

    /**
     * Метод обновления сущности задачи.
     * @param taskEntity сущность которя содержит параметры для обновления.
     * @return обновлённая сущность
     */
    @Override
    public TaskEntity update(TaskEntity taskEntity) {
        try {
            TaskEntity task = taskDao.update(taskEntity);
            return get(task.getId());
        } catch (InterruptedException | EntityUpdateException e) {
            LOGGER.severe("Error while updating task: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            LOGGER.severe("Task not found " + e.getMessage());
        }
        return null;
    }

    /**
     * Метод удаления сущности по идентификатору.
     * @param id идентификатор
     * @return результат
     */
    @Override
    public boolean delete(Long id) {
        return taskDao.delete(id);
    }

    /**
     * Метод поиска сущностей задач принадлежащих проекту.
     * @param projectEntity сущность проекта
     * @return список задач
     */
    @Override
    public List<TaskEntity> findByProject(ProjectEntity projectEntity) {
        return taskDao.receiveTaskByProject(projectEntity)
                .stream()
                .map(pr->get(pr.getId()))
                .collect(Collectors.toList());
    }
}
