package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.entity.Entity;
import com.qulix.shilomy.trainingtask.web.exception.EntityNotFoundException;
import com.qulix.shilomy.trainingtask.web.exception.EntityUpdateException;

import java.util.List;
import java.util.Optional;

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

    T create(T entity) throws EntityUpdateException, InterruptedException;

    Optional<T> read(Long id);

    List<T> readAll();

    T update(T entity) throws EntityUpdateException, InterruptedException, EntityNotFoundException;

    boolean delete(Long id);
}
