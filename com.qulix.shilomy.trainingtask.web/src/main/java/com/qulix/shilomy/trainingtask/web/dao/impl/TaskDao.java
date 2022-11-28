package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.EntityDao;
import com.qulix.shilomy.trainingtask.web.db.ConnectionService;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;

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
 * Реализация объекта доступа к данным для задачи.
 */
public class TaskDao implements EntityDao<TaskEntity> {
    private static TaskDao instance;
    protected ConnectionService connectionService = ConnectionService.getInstance();
    private final Logger logger = Logger.getLogger(TaskDao.class.getName());
    private static final String TABLE_NAME = "trainingtaskdb.task_list";
    private static final List<String> COLUMNS = Arrays.asList("status", "task_name", "project", "task_work", "start_date", "end_date", "executor");
    private static final List<String> fields = COLUMNS.stream().map(column -> column + "=?").collect(Collectors.toList());
    private static final String placeholders = String.join(", ", Collections.nCopies(COLUMNS.size()+1, "?"));
    private static final String SQL_INSERT = String.format("INSERT INTO %s VALUES(%s)", TABLE_NAME, placeholders);
    private static final String SQL_SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);
    private static final String SQL_SELECT_BY_ID = String.format("SELECT * FROM %s WHERE id=?", TABLE_NAME);
    private static final String SQL_UPDATE = String.format("UPDATE %s SET %s WHERE id=?", TABLE_NAME, String.join(", ", fields));
    private static final String SQL_DELETE_BY_ID = String.format("DELETE FROM %s WHERE id=?", TABLE_NAME);

    protected TaskDao() {
        
    }

    public static synchronized TaskDao getInstance() {
        if (instance == null) {
            instance = new TaskDao();
        }
        return instance;
    }

    /**
     * Создание задачи
     * @param entity сущность для создания
     */
    @Override
    public void create(TaskEntity entity) {
        logger.log(Level.INFO, "creating task");
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
            logger.log(Level.SEVERE, "sql exception while creating task", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Поиск задачи
     * @param id идентификатор
     * @return найденная задача
     */
    @Override
    public TaskEntity read(Long id) {
        logger.log(Level.INFO, "searching task");
        TaskEntity entity = null;
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = new TaskEntity(
                        TaskStatus.of(resultSet.getString(1)),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getDate(6),
                        resultSet.getLong(7),
                        id);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception while searching task", e);
            throw new RuntimeException(e);
        }
        return entity;
    }

    /**
     * Поиск всех задач
     * @return список найденных задач
     */
    @Override
    public List<TaskEntity> readAll() {
        logger.log(Level.INFO, "searching all tasks");
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
            logger.log(Level.SEVERE, "sql exception while searching all tasks", e);
            throw new RuntimeException(e);
        }
        return entities;
    }

    /**
     * Обновление задачи
     * @param entity задача для обновления
     */
    @Override
    public void update(TaskEntity entity) {
        logger.log(Level.INFO, "updating task");
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
            logger.log(Level.SEVERE, "sql exception while updating task", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление задачи по идентификатору
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        logger.log(Level.INFO, "deleting task");
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception while deleting task", e);
            throw new RuntimeException(e);
        }
    }
}
