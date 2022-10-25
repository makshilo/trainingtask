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
    T get(Long id);

    List<T> findAll();

    T add(T t);

    T update(T t);

    boolean delete(Long id);
}
