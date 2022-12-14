package com.qulix.shilomy.trainingtask.web.service;

import com.qulix.shilomy.trainingtask.web.entity.Entity;

import java.util.List;

/**
 * Интерфейс сервиса сущностей,
 * описывает базовые методы для сущности
 *
 * @param <T>
 */
public interface EntityService<T extends Entity> {
    /**
     * Получение сущности по идентификатору
     *
     * @param id идентификатор
     * @return сущность
     */
    T get(Long id);

    /**
     * Поиск всех сущностей
     *
     * @return список сущностей
     */
    List<T> findAll();

    /**
     * Добавление сущности
     *
     * @param t сущность
     */
    void add(T t);

    /**
     * Обновление сущности
     *
     * @param t сущность
     */
    void update(T t);

    /**
     * Удаление сущности по идентификатору
     *
     * @param id идентификатор
     */
    void delete(Long id);
}
