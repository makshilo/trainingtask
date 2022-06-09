package com.qulix.shilomy.trainingtask.web.repository;

import com.qulix.shilomy.trainingtask.web.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository {
    void addEmployee(EmployeeEntity employee);
    void removeEmployee(EmployeeEntity employee);
    void updateEmployee(EmployeeEntity employee); // Think it as replace for set

    List<?> query(EmployeeSpecification specification);
}
