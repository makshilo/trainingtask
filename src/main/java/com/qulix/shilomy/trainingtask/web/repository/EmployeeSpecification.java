package com.qulix.shilomy.trainingtask.web.repository;

import com.qulix.shilomy.trainingtask.web.entity.EmployeeEntity;

public interface EmployeeSpecification {
    boolean specified(EmployeeEntity employee);
}
