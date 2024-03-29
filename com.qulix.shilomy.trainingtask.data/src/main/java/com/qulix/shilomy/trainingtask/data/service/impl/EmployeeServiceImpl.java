package com.qulix.shilomy.trainingtask.data.service.impl;

import com.qulix.shilomy.trainingtask.data.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.exception.DatabaseAccessException;
import com.qulix.shilomy.trainingtask.data.service.EntityService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Сервис для сотрудника
 */
public class EmployeeServiceImpl implements EntityService<EmployeeEntity> {
    /**
     * Единственный объект класса
     */
    private static EmployeeServiceImpl instance;

    /**
     * Объект доступа к данным сотрудника
     */
    private final EmployeeDao employeeDao;

    private final Logger logger = Logger.getLogger(EmployeeServiceImpl.class.getName());

    private EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * Получение объекта класса
     *
     * @return объект EmployeeServiceImpl
     */
    public static synchronized EmployeeServiceImpl getInstance(EmployeeDao employeeDao) {
        if (instance == null) {
            instance = new EmployeeServiceImpl(employeeDao);
        }
        return instance;
    }

    /**
     * Получение сотрудника по идентификатору
     *
     * @param id идентификатор
     * @return модель сотрудника
     */
    public EmployeeEntity get(Long id) {
        Optional<EmployeeEntity> employee = Optional.empty();
        try {
            employee = employeeDao.read(id);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
        return employee.orElseThrow();
    }

    /**
     * Поиск всех сотрудников
     *
     * @return список сотрудников
     */
    @Override
    public List<EmployeeEntity> findAll() {
        try {
            return employeeDao.readAll();
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
        return Collections.emptyList();
    }

    /**
     * Добавление сотрудника
     *
     * @param employeeEntity сотрудник
     */
    @Override
    public void add(EmployeeEntity employeeEntity) {
        try {
            employeeDao.create(employeeEntity);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }

    /**
     * Обновление сотрудника
     *
     * @param employeeEntity сотрудник
     */
    @Override
    public void update(EmployeeEntity employeeEntity) {
        try {
            employeeDao.update(employeeEntity);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }

    /**
     * Удаление сотрудника по идентификатору.
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        try {
            employeeDao.delete(id);
        } catch (DatabaseAccessException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }
}