package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;

import java.util.List;

/**
 * Сервис для модели сотрудника
 */
public class EmployeeServiceImpl implements EntityService<EmployeeEntity> {

    // Объект одиночка
    private static EmployeeServiceImpl instance;

    // Объект дао для модели сотрудников
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
     * Получение сотрудника по идентификатору
     * @param id идентификатор
     * @return модель сотрудника
     */
    public EmployeeEntity get(Long id) {
        return employeeDao.read(id);
    }

    /**
     * Поиск всех сотрудников
     * @return список найденных сотрудников
     */
    @Override
    public List<EmployeeEntity> findAll() {
        return employeeDao.readAll();
    }

    /**
     * Добавление сотрудника
     *
     * @param employeeEntity модель сотрудника
     */
    @Override
    public void add(EmployeeEntity employeeEntity) {
        employeeDao.create(employeeEntity);
    }

    /**
     * Обновление сотрудника.
     *
     * @param employeeEntity модель сотрудника
     */
    @Override
    public void update(EmployeeEntity employeeEntity) {
        employeeDao.update(employeeEntity);
    }

    /**
     * Метод удаления сотрудника по идентификатору.
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }
}