package com.qulix.shilomy.trainingtask.data.dao;

import com.qulix.shilomy.trainingtask.data.entity.Entity;
import com.qulix.shilomy.trainingtask.data.exception.DatabaseAccessException;

import java.util.List;
import java.util.Optional;


/**
 * Интерфейс дао сущностей,
 * описывает базовые методы модели
 *
 * @param <T>
 */
public interface EntityDao<T extends Entity> {
    /**
     * Создание модели
     *
     * @param entity модель
     */
    void create(T entity) throws DatabaseAccessException;

    /**
     * Поиск по идентификатору.
     *
     * @param id идентификатор
     * @return сущность
     */
    Optional<T> read(Long id) throws DatabaseAccessException;

    /**
     * Поиска всех моделей
     *
     * @return список моделей
     */
    List<T> readAll() throws DatabaseAccessException;

    /**
     * Обновление модели
     *
     * @param entity модель
     */
    void update(T entity) throws DatabaseAccessException;

    /**
     * Удаление модели
     *
     * @param id идентификатор
     */
    void delete(Long id) throws DatabaseAccessException;
}
