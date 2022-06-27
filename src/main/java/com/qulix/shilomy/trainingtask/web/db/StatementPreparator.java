package com.qulix.shilomy.trainingtask.web.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementPreparator {

    void accept(PreparedStatement preparedStatement) throws SQLException;
}
