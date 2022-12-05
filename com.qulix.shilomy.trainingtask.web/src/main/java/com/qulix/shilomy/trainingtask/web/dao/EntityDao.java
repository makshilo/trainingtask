package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.entity.Entity;
import com.qulix.shilomy.trainingtask.web.exception.DatabaseAccessException;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс дао сущностей,
 * описывает базовые методы для модели
 * <li>создание</li>
 * <li>получение</li>
 * <li>получение всех</li>
 * <li>обновление</li>
 * <li>удаление</li>
 * @param <T>
 */
public interface EntityDao<T extends Entity> {
    /**
     * Создание модели
     *
     * @param entity модель для создания
     */
    void create(T entity) throws DatabaseAccessException;

    /**
     * Поиск по идентификатору.
     *
     * @param id идентификатор
     * @return найденная сущность
     */
    Optional<T> read(Long id) throws DatabaseAccessException;

    /**
     * Поиска всех моделей заданного типа
     * @return список найденных моделей.
     */
    List<T> readAll() throws DatabaseAccessException;

    /**
     * Обновления модели
     *
     * @param entity модель для обновления.
     */
    void update(T entity) throws DatabaseAccessException;

    /**
     * Удаления модели
     * @param id идентификатор
     */
    void delete(Long id) throws DatabaseAccessException;
}
