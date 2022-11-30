package com.qulix.shilomy.trainingtask.web.service;

import com.qulix.shilomy.trainingtask.web.entity.Entity;

import java.util.List;

/**
 * Интерфейс сервиса сущностей,
 * описывает базовые методы для сущности
 * <li>получение</li>
 * <li>поиск всех сущностей</li>
 * <li>добавление</li>
 * <li>обновление</li>
 * <li>удаление</li>
 * @param <T>
 */
public interface EntityService<T extends Entity> {
    /**
     * Получение сущности по идентификатору
     * @param id идентификатор
     * @return сущность
     */
    T get(Long id);

    /**
     * Поиск всех сущностей заданного класса
     * @return список сущностей класса T
     */
    List<T> findAll();

    /**
     * Добавление сущности заданного класса
     *
     * @param t экземпляр модели
     */
    void add(T t);

    /**
     * Обновление сущности заданного класса
     *
     * @param t экземпляр модели
     */
    void update(T t);

    /**
     * Удаление сущности по идентификатору
     *
     * @param id идентификатор
     */
    void delete(Long id);
}
