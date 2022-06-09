package com.qulix.shilomy.trainingtask.web.repository;

import com.qulix.shilomy.trainingtask.web.entity.TaskEntity;

public interface TaskSpecification {
    boolean specified(TaskEntity task);
}
