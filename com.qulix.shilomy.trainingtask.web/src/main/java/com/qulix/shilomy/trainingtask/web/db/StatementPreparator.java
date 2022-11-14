package com.qulix.shilomy.trainingtask.web.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Функциональный интерфейс,
 * подготавливающий запрос в базу данныйх
 */
@FunctionalInterface
public interface StatementPreparator {

    /**
     * Метод принимающий подготовленное выражение
     * @param preparedStatement подготовленное выражение
     * @throws SQLException ошибка базы данных
     */
    void accept(PreparedStatement preparedStatement) throws SQLException;
}
