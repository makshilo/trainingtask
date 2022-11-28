package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.EntityDao;
import com.qulix.shilomy.trainingtask.web.db.ConnectionService;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.sql.Types.INTEGER;

/**
 * Реализация объекта доступа к данным для сотрудника.
 */
public class EmployeeDao implements EntityDao<EmployeeEntity> {
    private static EmployeeDao instance;
    protected ConnectionService connectionService = ConnectionService.getInstance();
    private final Logger logger = Logger.getLogger(TaskDao.class.getName());
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
    public void create(EmployeeEntity entity) {
        logger.log(Level.INFO, "creating employee");
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, entity.getLastName());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getPatronymic());
            statement.setString(4, entity.getPosition());
            statement.setNull(5, INTEGER);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception while creating employee", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Поиск работника
     * @param id идентификатор
     * @return найденный работник
     */
    @Override
    public EmployeeEntity read(Long id) {
        logger.log(Level.INFO, "searching employee");
        EmployeeEntity entity = null;
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = new EmployeeEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        id);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception while searching employee", e);
            throw new RuntimeException(e);
        }
        return entity;
    }

    /**
     * Поиск всех работников
     * @return список найденных работников
     */
    @Override
    public List<EmployeeEntity> readAll() {
        logger.log(Level.INFO, "searching all employees");
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
            logger.log(Level.SEVERE, "sql exception while searching all employees", e);
            throw new RuntimeException(e);
        }
        return entities;
    }

    /**
     * Обновление работника
     * @param entity работник с данными
     */
    @Override
    public void update(EmployeeEntity entity) {
        logger.log(Level.INFO, "updating employee");
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, entity.getLastName());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getPatronymic());
            statement.setString(4, entity.getPosition());
            statement.setLong(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception while updating employee", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление работника по идентификатору
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        logger.log(Level.INFO, "deleting employee");
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception while deleting employee", e);
            throw new RuntimeException(e);
        }
    }
}
