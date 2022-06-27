package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.exception.EntityNotFoundException;
import com.qulix.shilomy.trainingtask.web.exception.EntityUpdateException;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;

import java.util.List;
import java.util.logging.Logger;

public class EmployeeServiceImpl implements EmployeeService {
    private static EmployeeServiceImpl instance;

    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class.getName());

    private final EmployeeDao employeeDao;

    private EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public static synchronized EmployeeServiceImpl getInstance(EmployeeDao employeeDao) {
        if (instance == null) {
            instance = new EmployeeServiceImpl(employeeDao);
        }
        return instance;
    }

    @Override
    public EmployeeEntity get(Long id) {
        return employeeDao.read(id).orElse(null);
    }

    @Override
    public List<EmployeeEntity> findAll() {
        return employeeDao.readAll();
    }

    @Override
    public EmployeeEntity add(EmployeeEntity employeeEntity) {
        try {
            return employeeDao.create(employeeEntity);
        } catch (EntityUpdateException | InterruptedException e) {
            LOGGER.severe("Error while adding employee: " + e.getMessage());
        }
        return null;
    }

    @Override
    public EmployeeEntity update(EmployeeEntity employeeEntity) {
        try {
            EmployeeEntity employee = employeeDao.update(employeeEntity);
            return get(employee.getId());
        } catch (EntityUpdateException | InterruptedException e) {
            LOGGER.severe("Error while updating employee: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            LOGGER.severe("Employee not found " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return employeeDao.delete(id);
    }
}