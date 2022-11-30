package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.entity.Entity;

import java.util.List;

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
    void create(T entity);

    /**
     * Поиск по идентификатору.
     * @param id идентификатор
     * @return найденная сущность
     */
    T read(Long id);

    /**
     * Поиска всех моделей заданного типа
     * @return список найденных моделей.
     */
    List<T> readAll();

    /**
     * Обновления модели
     *
     * @param entity модель для обновления.
     */
    void update(T entity);

    /**
     * Удаления модели
     * @param id идентификатор
     */
    void delete(Long id);
}
