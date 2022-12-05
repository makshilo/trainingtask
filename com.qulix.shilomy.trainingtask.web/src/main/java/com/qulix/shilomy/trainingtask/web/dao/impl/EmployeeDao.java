package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.EntityDao;
import com.qulix.shilomy.trainingtask.web.db.ConnectionService;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.exception.DatabaseAccessException;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.sql.Types.INTEGER;

/**
 * Объект доступа к данным для сотрудника
 */
public class EmployeeDao implements EntityDao<EmployeeEntity> {

    // Объект одиночка
    private static EmployeeDao instance;

    // Объект сервиса подключений
    protected ConnectionService connectionService = ConnectionService.getInstance();

    private static final String TABLE_NAME = "trainingtaskdb.employee_list";
    private static final List<String> COLUMNS = Arrays.asList("last_name", "first_name", "patronymic", "employee_position");
    private static final List<String> fields = COLUMNS.stream().map(column -> column + "=?").collect(Collectors.toList());
    private static final String placeholders = String.join(", ", Collections.nCopies(COLUMNS.size()+1, "?"));
    private static final String SQL_INSERT = String.format("INSERT INTO %s VALUES(%s)", TABLE_NAME, placeholders);
    private static final String SQL_SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);
    private static final String SQL_SELECT_BY_ID = String.format("SELECT * FROM %s WHERE id=?", TABLE_NAME);
    private static final String SQL_UPDATE = String.format("UPDATE %s SET %s WHERE id=?", TABLE_NAME, String.join(", ", fields));
    private static final String SQL_DELETE_BY_ID = String.format("DELETE FROM %s WHERE id=?", TABLE_NAME);

    protected EmployeeDao() {

    }

    public static synchronized EmployeeDao getInstance() {
        if (instance == null) {
            instance = new EmployeeDao();
        }
        return instance;
    }

    /**
     * Создание работника
     * @param entity работник с данными
     */
    @Override
    public void create(EmployeeEntity entity) throws DatabaseAccessException {
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, entity.getLastName());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getPatronymic());
            statement.setString(4, entity.getPosition());
            statement.setNull(5, INTEGER);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, creating employee record", e);
        }
    }

    /**
     * Поиск работника
     *
     * @param id идентификатор
     * @return найденный работник
     */
    @Override
    public Optional<EmployeeEntity> read(Long id) throws DatabaseAccessException {
        Optional<EmployeeEntity> entity = Optional.empty();
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = Optional.of(new EmployeeEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        id));
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, searching employee record", e);
        }
        return entity;
    }

    /**
     * Поиск всех работников
     * @return список найденных работников
     */
    @Override
    public List<EmployeeEntity> readAll() throws DatabaseAccessException {
        List<EmployeeEntity> entities = new ArrayList<>();
        try (Connection connection = connectionService.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                entities.add(new EmployeeEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getLong(5))
                );
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, searching all employee records", e);
        }
        return entities;
    }

    /**
     * Обновление работника
     * @param entity работник с данными
     */
    @Override
    public void update(EmployeeEntity entity) throws DatabaseAccessException {
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, entity.getLastName());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getPatronymic());
            statement.setString(4, entity.getPosition());
            statement.setLong(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, updating employee record", e);
        }
    }

    /**
     * Удаление работника по идентификатору
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) throws DatabaseAccessException {
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, deleting employee record", e);
        }
    }
}
