package com.qulix.shilomy.trainingtask.web.repository;

import com.qulix.shilomy.trainingtask.web.entity.TaskEntity;

import java.util.List;

public interface TaskRepository {
    void addTask(TaskEntity task);
    void removeTask(TaskEntity task);
    void updateTask(TaskEntity task);

    List<?> query(TaskSpecification specification);
}
