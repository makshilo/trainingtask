package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.CommonDao;
import com.qulix.shilomy.trainingtask.web.dao.EntityDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.sql.Types.INTEGER;

/**
 * Класс реализация объекта доступа к данным для сущности сотрудника.
 */
public class EmployeeDao extends CommonDao<EmployeeEntity> implements EntityDao<EmployeeEntity> {
    private static EmployeeDao instance;
    private static final String EMPLOYEE_TABLE_NAME = "trainingtaskdb.employee_list";
    private static final String EMPLOYEE_ID_COLUMN = "id";
    private static final String EMPLOYEE_FIRST_NAME_COLUMN = "first_name";
    private static final String EMPLOYEE_LAST_NAME_COLUMN = "last_name";
    private static final String EMPLOYEE_PATRONYMIC_COLUMN = "patronymic";
    private static final String EMPLOYEE_POSITION_COLUMN = "employee_position";
    private static final String EMPLOYEE_UNIQUE_HASH = "unique_hash";
    private static final Logger LOGGER = Logger.getLogger(EmployeeDao.class.getName());

    private static final List<String> FIELDS = Arrays.asList(
            EMPLOYEE_ID_COLUMN,
            EMPLOYEE_FIRST_NAME_COLUMN,
            EMPLOYEE_LAST_NAME_COLUMN,
            EMPLOYEE_PATRONYMIC_COLUMN,
            EMPLOYEE_POSITION_COLUMN,
            EMPLOYEE_UNIQUE_HASH
    );

    /**
     * Защищённый конструктор по умолчанию.
     */
    protected EmployeeDao() {
        super(LOGGER);
    }

    /**
     * Метод получения объекта класса.
     * @return объект MethodEmployeeDao
     */
    public static synchronized EmployeeDao getInstance() {
        if (instance == null) {
            instance = new EmployeeDao();
        }
        return instance;
    }

    /**
     * Метод получения имени таблицы.
     * @return EMPLOYEE_TABLE_NAME
     */
    @Override
    protected String getTableName() {
        return EMPLOYEE_TABLE_NAME;
    }

    /**
     * Метод получения имени поля идентификатора.
     * @return EMPLOYEE_ID_COLUMN
     */
    @Override
    protected String getIdFieldName() {
        return EMPLOYEE_ID_COLUMN;
    }

    /**
     * Метод получения уникального поля.
     * @return EMPLOYEE_UNIQUE_HASH
     */
    @Override
    protected String getUniqueFieldName() {
        return EMPLOYEE_UNIQUE_HASH;
    }

    /**
     * Метод получения списка полей.
     * @return FIELDS
     */
    @Override
    protected List<String> getFields() {
        return FIELDS;
    }

    /**
     * Метод заполнения работника.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    @Override
    protected void fillEntity(PreparedStatement statement, EmployeeEntity entity) throws SQLException {
        fillFields(statement, entity);
    }

    /**
     * Метод обновления сущности.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    @Override
    protected void updateEntity(PreparedStatement statement, EmployeeEntity entity) throws SQLException {
        fillFields(statement, entity);
        statement.setLong(1, entity.getId());
        statement.setLong(7, entity.getId());
    }

    /**
     * Метод заполнения уникального поля.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    @Override
    protected void fillUniqueField(PreparedStatement statement, EmployeeEntity entity) throws SQLException {
        statement.setString(1, composeHashCode(entity));
    }

    /**
     * Метод заполнения результрирующего множества.
     * @param resultSet результирующее множество
     * @return сущность
     * @throws SQLException ошибка базы данных
     */
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

    /**
     * Метод заполнения полей.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    private void fillFields(PreparedStatement statement, EmployeeEntity entity) throws SQLException {
        statement.setNull(1, INTEGER);
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getPatronymic());
        statement.setString(5, entity.getPosition());
        statement.setString(6, composeHashCode(entity));
    }

    /**
     * Метод создания уникальной строки для работника
     * @param employee сущность работника
     * @return уникальная строка
     */
    private String composeHashCode(EmployeeEntity employee) {
        return employee.getFirstName() + employee.getLastName() + employee.getPatronymic() + employee.getPosition();
    }
}
