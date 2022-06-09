package com.qulix.shilomy.trainingtask.web.repository.impl;

import com.qulix.shilomy.trainingtask.web.entity.TaskEntity;
import com.qulix.shilomy.trainingtask.web.repository.TaskRepository;
import com.qulix.shilomy.trainingtask.web.repository.TaskSpecification;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    private final List<TaskEntity> taskList;

    public TaskRepositoryImpl(List<TaskEntity> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void addTask(TaskEntity task) {
        taskList.add(task);
    }

    @Override
    public void removeTask(TaskEntity task) {
        taskList.remove(task);
    }

    @Override
    public void updateTask(TaskEntity task) {
        int index = taskList.indexOf(task);
        if (index == -1) {
            throw new RuntimeException("Task not found");
        }
        taskList.set(index, task);
    }

    @Override
    public List<TaskEntity> query(TaskSpecification specification) {
        List<TaskEntity> suitableTasks = new ArrayList<>();
        for (TaskEntity task : taskList) {
            if (specification.specified(task)) {
                suitableTasks.add(task);
            }
        }
        return suitableTasks;
    }
}
