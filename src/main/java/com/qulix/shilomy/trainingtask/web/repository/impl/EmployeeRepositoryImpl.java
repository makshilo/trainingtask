package com.qulix.shilomy.trainingtask.web.repository.impl;

import com.qulix.shilomy.trainingtask.web.entity.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.repository.EmployeeRepository;
import com.qulix.shilomy.trainingtask.web.repository.EmployeeSpecification;


import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final List<EmployeeEntity> employeeList;

    public EmployeeRepositoryImpl(List<EmployeeEntity> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public void addEmployee(EmployeeEntity employee) {
        employeeList.add(employee);
    }

    @Override
    public void removeEmployee(EmployeeEntity employee) {
        employeeList.remove(employee);
    }

    @Override
    public void updateEmployee(EmployeeEntity employee) {
        int index = employeeList.indexOf(employee);
        if (index == -1) {
            throw new RuntimeException("Employee not found");
        }
        employeeList.set(index, employee);
    }

    @Override
    public List<EmployeeEntity> query(EmployeeSpecification specification) {
        List<EmployeeEntity> suitableEmployee = new ArrayList<>();
        for (EmployeeEntity employee : employeeList) {
            if (specification.specified(employee)) {
                suitableEmployee.add(employee);
            }
        }
        return suitableEmployee;
    }
}