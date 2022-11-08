package com.qulix.shilomy.trainingtask.web.db;

import com.qulix.shilomy.trainingtask.web.exception.EntityExtractionFailedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Функциональный интерфейс который получает
 * сущность из результирующего множества
 * @param <T>
 */
@FunctionalInterface
public interface ResultSetExtractor<T> {

    /**
     * Метод получения сущности из результирующего множества
     * @param resultSet результирующее множество
     * @return сущность
     * @throws EntityExtractionFailedException ошибка получения сущности
     */
    T extract(ResultSet resultSet) throws EntityExtractionFailedException;

    /**
     * Метод получения всех сущностей из результирующего множества
     * @param resultSet результирующее множество
     * @return сущность
     * @throws EntityExtractionFailedException ошибка получения сущности
     */
    default List<T> extractAll(ResultSet resultSet) throws EntityExtractionFailedException, SQLException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            final T entity = extract(resultSet);
            entities.add(entity);
        }
        return entities;
    }
}
