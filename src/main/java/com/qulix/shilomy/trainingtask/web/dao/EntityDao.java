package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.entity.Entity;
import com.qulix.shilomy.trainingtask.web.exception.EntityNotFoundException;
import com.qulix.shilomy.trainingtask.web.exception.EntityUpdateException;

import java.util.List;
import java.util.Optional;

public interface EntityDao<T extends Entity> {

    T create(T entity) throws EntityUpdateException, InterruptedException;

    Optional<T> read(Long id);

    List<T> readAll();

    T update(T entity) throws EntityUpdateException, InterruptedException, EntityNotFoundException;

    boolean delete(Long id);
}
