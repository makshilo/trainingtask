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
    /**
     * Описание метода создания сущности.
     * @param entity сущность для создания
     * @return объект сущности
     * @throws EntityUpdateException ошибка обновления сущности.
     * @throws InterruptedException ошибка прерывания действия.
     */
    T create(T entity) throws EntityUpdateException, InterruptedException;

    /**
     * Описание метода поиска по идентификатору.
     * @param id идентификатор
     * @return найденная сущность
     */
    Optional<T> read(Long id);

    /**
     * Описание метода поиска всех сущностей.
     * @return список найденных сущностей.
     */
    List<T> readAll();

    /**
     * Описание метода обновления сущности.
     * @param entity сущность для обновления.
     * @return обновлённая сущность
     * @throws EntityUpdateException ошибка обновления сущности.
     * @throws InterruptedException ошибка прерывания действия.
     * @throws EntityNotFoundException ошибка поиска сущности.
     */
    T update(T entity) throws EntityUpdateException, InterruptedException, EntityNotFoundException;

    /**
     * Описание метода удаления сущности.
     * @param id идентификатор
     * @return результат удаления
     */
    boolean delete(Long id);
}
