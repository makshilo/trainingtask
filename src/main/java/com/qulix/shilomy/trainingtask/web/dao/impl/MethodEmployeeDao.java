package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.CommonDao;
import com.qulix.shilomy.trainingtask.web.dao.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.sql.Types.INTEGER;
import static java.sql.Types.NULL;

public class MethodEmployeeDao extends CommonDao<EmployeeEntity> implements EmployeeDao {
    private static MethodEmployeeDao instance;
    private static final String EMPLOYEE_TABLE_NAME = "trainingtaskdb.employee_list";
    private static final String EMPLOYEE_ID_COLUMN = "employee_id";
    private static final String EMPLOYEE_FIRST_NAME_COLUMN = "employee_first_name";
    private static final String EMPLOYEE_LAST_NAME_COLUMN = "employee_last_name";
    private static final String EMPLOYEE_PATRONYMIC_COLUMN = "employee_patronymic";
    private static final String EMPLOYEE_POSITION_COLUMN = "employee_position";
    private static final String EMPLOYEE_UNIQUE_HASH = "employee_unique_hash";
    private static final Logger LOGGER = Logger.getLogger(MethodEmployeeDao.class.getName());

    private static final List<String> FIELDS = Arrays.asList(
            EMPLOYEE_ID_COLUMN,
            EMPLOYEE_FIRST_NAME_COLUMN,
            EMPLOYEE_LAST_NAME_COLUMN,
            EMPLOYEE_PATRONYMIC_COLUMN,
            EMPLOYEE_POSITION_COLUMN,
            EMPLOYEE_UNIQUE_HASH
    );

    protected MethodEmployeeDao() {
        super(LOGGER);
    }

    public static synchronized MethodEmployeeDao getInstance() {
        if (instance == null) {
            instance = new MethodEmployeeDao();
        }
        return instance;
    }

    @Override
    protected String getTableName() {
        return EMPLOYEE_TABLE_NAME;
    }

    @Override
    protected String getIdFieldName() {
        return EMPLOYEE_ID_COLUMN;
    }

    @Override
    protected String getUniqueFieldName() {
        return EMPLOYEE_UNIQUE_HASH;
    }

    @Override
    protected List<String> getFields() {
        return FIELDS;
    }

    @Override
    protected void fillEntity(PreparedStatement statement, EmployeeEntity entity) throws SQLException {
        fillFields(statement, entity);
    }

    @Override
    protected void updateEntity(PreparedStatement statement, EmployeeEntity entity) throws SQLException {
        fillFields(statement, entity);
        statement.setLong(1, entity.getId());
        statement.setLong(7, entity.getId());
    }

    @Override
    protected void fillUniqueField(PreparedStatement statement, EmployeeEntity entity) throws SQLException {
        statement.setString(1, composeHashCode(entity));
    }

    @Override
    protected EmployeeEntity extractResultSet(ResultSet resultSet) throws SQLException {
        return new EmployeeEntity(
                resultSet.getString(EMPLOYEE_FIRST_NAME_COLUMN),
                resultSet.getString(EMPLOYEE_LAST_NAME_COLUMN),
                resultSet.getString(EMPLOYEE_PATRONYMIC_COLUMN),
                resultSet.getString(EMPLOYEE_POSITION_COLUMN),
                resultSet.getLong(EMPLOYEE_ID_COLUMN)
        );
    }

    private void fillFields(PreparedStatement statement, EmployeeEntity entity) throws SQLException {
        statement.setNull(1, INTEGER);
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getPatronymic());
        statement.setString(5, entity.getPosition());
        statement.setString(6, composeHashCode(entity));
    }

    private String composeHashCode(EmployeeEntity employee) {
        return employee.getFirstName() + employee.getLastName() + employee.getPatronymic() + employee.getPosition();
    }
}
