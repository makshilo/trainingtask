package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;

import java.util.List;

/**
 * Класс сервис реализующий методы сервиса для сущности работника
 */
public class EmployeeServiceImpl implements EntityService<EmployeeEntity> {
    private static EmployeeServiceImpl instance;

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
        return employeeDao.read(id);
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
     *
     * @param employeeEntity сущность работника для добавления
     */
    @Override
    public void add(EmployeeEntity employeeEntity) {
        employeeDao.create(employeeEntity);
    }

    /**
     * Метод обновления сущности работника.
     *
     * @param employeeEntity сущность которя содержит параметры для обновления
     */
    @Override
    public void update(EmployeeEntity employeeEntity) {
        employeeDao.update(employeeEntity);
    }

    /**
     * Метод удаления сущности по идентификатору.
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }
}