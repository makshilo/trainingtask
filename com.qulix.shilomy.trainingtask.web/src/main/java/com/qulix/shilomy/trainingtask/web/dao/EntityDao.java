package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.entity.Entity;

import java.util.List;

/**
 * Общий интерфейс дао сущностей,
 * описывает базовые методы для сущности
 * <li>создание</li>
 * <li>получение</li>
 * <li>получение всех</li>
 * <li>обновление</li>
 * <li>удаление</li>
 * @param <T>
 */
public interface EntityDao<T extends Entity> {
    /**
     * Описание метода создания сущности.
     *
     * @param entity сущность для создания
     */
    void create(T entity);

    /**
     * Описание метода поиска по идентификатору.
     * @param id идентификатор
     * @return найденная сущность
     */
    T read(Long id);

    /**
     * Описание метода поиска всех сущностей.
     * @return список найденных сущностей.
     */
    List<T> readAll();

    /**
     * Описание метода обновления сущности.
     *
     * @param entity сущность для обновления.
     */
    void update(T entity);

    /**
     * Описание метода удаления сущности.
     * @param id идентификатор
     */
    void delete(Long id);
}
