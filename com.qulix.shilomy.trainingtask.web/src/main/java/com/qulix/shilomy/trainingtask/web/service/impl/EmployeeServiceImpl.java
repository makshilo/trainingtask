package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.exception.EntityNotFoundException;
import com.qulix.shilomy.trainingtask.web.service.EntityService;

import java.util.List;
import java.util.logging.Logger;

public class EmployeeServiceImpl implements EntityService<EmployeeEntity> {
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

    /**
     * Метод получения сущности работника по идентификатору.
     * @param id идентификатор
     * @return сущность работника(EmployeeEntity)
     */
    public EmployeeEntity get(Long id) {
        return employeeDao.read(id).orElseThrow();
    }

    /**
     * Метод поиска всех сущностей работника.
     * @return список найденных сущностей
     */
    @Override
    public List<EmployeeEntity> findAll() {
        return employeeDao.readAll();
    }

    /**
     * Метод добавления сущности работника.
     * @param employeeEntity сущность работника для добавления
     * @return добавленная сущность
     */
    @Override
    public EmployeeEntity add(EmployeeEntity employeeEntity) {
        try {
            return employeeDao.create(employeeEntity).orElseThrow();
        } catch (InterruptedException e) {
            LOGGER.severe("Error while adding employee: " + e.getMessage());
        }
        return employeeEntity;
    }

    /**
     * Метод обновления сущности работника.
     * @param employeeEntity сущность которя содержит параметры для обновления
     * @return обновлённая сущность
     */
    @Override
    public EmployeeEntity update(EmployeeEntity employeeEntity) {
        try {
            EmployeeEntity employee = employeeDao.update(employeeEntity).orElseThrow();
            return get(employee.getId());
        } catch (InterruptedException e) {
            LOGGER.severe("Error while updating employee: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            LOGGER.severe("Employee not found " + e.getMessage());
        }
        return employeeEntity;
    }

    /**
     * Метод удаления сущности по идентификатору.
     * @param id идентификатор
     * @return результат
     */
    @Override
    public boolean delete(Long id) {
        return employeeDao.delete(id);
    }
}