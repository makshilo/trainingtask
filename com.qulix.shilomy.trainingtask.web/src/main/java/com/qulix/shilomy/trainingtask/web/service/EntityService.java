package com.qulix.shilomy.trainingtask.web.service;

import com.qulix.shilomy.trainingtask.web.entity.Entity;

import java.util.List;

/**
 * Общий интерфейс сервиса сущностей,
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
     * Описание метода получения сущности по идентификатору
     * @param id идентификатор
     * @return сущность
     */
    T get(Long id);

    /**
     * Описание метода поиска всех сущностей заданного класса
     * @return список сущностей класса T
     */
    List<T> findAll();

    /**
     * Описание метода добавления сущности заданного класса
     * @param t экземпляр сущности с данными для добавления
     * @return добавленная сущность
     */
    T add(T t);

    /**
     * Описание метода обновления сущности заданного класса
     * @param t экземпляр сущности с данными для лбновления
     * @return обновлённая сущность
     */
    T update(T t);

    /**
     * Описание метода удаления сущности по идентификатору
     * @param id идентификатор
     * @return результат удаления
     */
    boolean delete(Long id);
}
