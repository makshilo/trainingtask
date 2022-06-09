package com.qulix.shilomy.trainingtask.web.repository;

import com.qulix.shilomy.trainingtask.web.entity.ProjectEntity;

public interface ProjectSpecification {
    boolean specified(ProjectEntity project);
}
