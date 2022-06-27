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

    @Override
    public TaskEntity get(Long id) {
        return taskDao.read(id).orElse(null);
    }

    @Override
    public List<TaskEntity> findAll() {
        return taskDao.readAll();
    }

    @Override
    public TaskEntity add(TaskEntity taskEntity) {
        try {
            return taskDao.create(taskEntity);
        } catch (InterruptedException | EntityUpdateException e) {
            LOGGER.severe("Error while adding task: " + e.getMessage());
        }
        return null;
    }

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

    @Override
    public boolean delete(Long id) {
        return taskDao.delete(id);
    }

    @Override
    public List<TaskEntity> findByProject(ProjectEntity projectEntity) {
        return taskDao.receiveTaskByProject(projectEntity)
                .stream()
                .map(pr->get(pr.getId()))
                .collect(Collectors.toList());
    }
}
