package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.EntityDao;
import com.qulix.shilomy.trainingtask.web.db.ConnectionService;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.exception.DatabaseAccessException;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.sql.Types.INTEGER;

/**
 * Объекта доступа к данным задачи
 */
public class TaskDao implements EntityDao<TaskEntity> {

    /**
     * Единственный объект класса
     */
    private static TaskDao instance;

    /**
     * Сервис подключений
     */
    protected ConnectionService connectionService = ConnectionService.getInstance();

    private static final String TABLE_NAME = "trainingtaskdb.tasks";
    private static final List<String> COLUMNS = Arrays.asList("status", "task_name", "project", "task_work", "start_date", "end_date", "executor");
    private static final List<String> fields = COLUMNS.stream().map(column -> column + "=?").collect(Collectors.toList());
    private static final String placeholders = String.join(", ", Collections.nCopies(COLUMNS.size() + 1, "?"));
    private static final String SQL_INSERT = String.format("INSERT INTO %s VALUES(%s)", TABLE_NAME, placeholders);
    private static final String SQL_SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);
    private static final String SQL_SELECT_BY_ID = String.format("SELECT * FROM %s WHERE id=?", TABLE_NAME);
    private static final String SQL_UPDATE = String.format("UPDATE %s SET %s WHERE id=?", TABLE_NAME, String.join(", ", fields));
    private static final String SQL_DELETE_BY_ID = String.format("DELETE FROM %s WHERE id=?", TABLE_NAME);

    protected TaskDao() {

    }

    /**
     * Получение объекта класса
     *
     * @return объект TaskDao
     */
    public static synchronized TaskDao getInstance() {
        if (instance == null) {
            instance = new TaskDao();
        }
        return instance;
    }

    /**
     * Создание задачи
     *
     * @param entity задача
     */
    @Override
    public void create(TaskEntity entity) throws DatabaseAccessException {
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, entity.getStatus().name());
            statement.setString(2, entity.getName());
            statement.setLong(3, entity.getProjectId());
            statement.setString(4, entity.getWork());
            statement.setDate(5, entity.getStartDate());
            statement.setDate(6, entity.getEndDate());
            statement.setLong(7, entity.getExecutorId());
            statement.setNull(8, INTEGER);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, creating task record", e);
        }
    }

    /**
     * Поиск задачи
     *
     * @param id идентификатор
     * @return задача
     */
    @Override
    public Optional<TaskEntity> read(Long id) throws DatabaseAccessException {
        Optional<TaskEntity> entity = Optional.empty();
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = Optional.of(new TaskEntity(
                        TaskStatus.of(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getDate(6),
                        resultSet.getLong(7),
                        id));
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, searching task record", e);
        }
        return entity;
    }

    /**
     * Поиск всех задач
     *
     * @return список задач
     */
    @Override
    public List<TaskEntity> readAll() throws DatabaseAccessException {
        List<TaskEntity> entities = new ArrayList<>();
        try (Connection connection = connectionService.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                entities.add(new TaskEntity(
                        TaskStatus.of(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getDate(6),
                        resultSet.getLong(7),
                        resultSet.getLong(8))
                );
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, searching all task records", e);
        }
        return entities;
    }

    /**
     * Обновление задачи
     *
     * @param entity задача
     */
    @Override
    public void update(TaskEntity entity) throws DatabaseAccessException {
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, entity.getStatus().name());
            statement.setString(2, entity.getName());
            statement.setLong(3, entity.getProjectId());
            statement.setString(4, entity.getWork());
            statement.setDate(5, entity.getStartDate());
            statement.setDate(6, entity.getEndDate());
            statement.setLong(7, entity.getExecutorId());
            statement.setLong(8, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, searching task record", e);
        }
    }

    /**
     * Удаление задачи по идентификатору
     *
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) throws DatabaseAccessException {
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, deleting task record", e);
        }
    }
}
