package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.EntityDao;
import com.qulix.shilomy.trainingtask.web.db.ConnectionService;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;

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
 * Объект доступа к данным для проекта
 */
public class ProjectDao implements EntityDao<ProjectEntity> {

    // Объект одиночка
    private static ProjectDao instance;

    // Объект сервиса подключений
    protected ConnectionService connectionService = ConnectionService.getInstance();

    private final Logger logger = Logger.getLogger(TaskDao.class.getName());
    private static final String TABLE_NAME = "trainingtaskdb.project_list";
    private static final List<String> COLUMNS = Arrays.asList("project_name", "description");
    private static final List<String> fields = COLUMNS.stream().map(column -> column + "=?").collect(Collectors.toList());
    private static final String placeholders = String.join(", ", Collections.nCopies(COLUMNS.size()+1, "?"));
    private static final String SQL_INSERT = String.format("INSERT INTO %s VALUES(%s)", TABLE_NAME, placeholders);
    private static final String SQL_SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);
    private static final String SQL_SELECT_BY_ID = String.format("SELECT * FROM %s WHERE id=?", TABLE_NAME);
    private static final String SQL_UPDATE = String.format("UPDATE %s SET %s WHERE id=?", TABLE_NAME, String.join(", ", fields));
    private static final String SQL_DELETE_BY_ID = String.format("DELETE FROM %s WHERE id=?", TABLE_NAME);

    public static synchronized ProjectDao getInstance() {
        if (instance == null) {
            instance = new ProjectDao();
        }
        return instance;
    }
    public ProjectDao() {

    }

    /**
     * Добавление проекта
     * @param entity проект с данными
     */
    @Override
    public void create(ProjectEntity entity) {
        logger.log(Level.INFO, "creating project");
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setNull(3, INTEGER);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception while creating project", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Поиск проекта
     * @param id идентификатор
     * @return найденный проект
     */
    @Override
    public ProjectEntity read(Long id) {
        logger.log(Level.INFO, "searching project");
        ProjectEntity entity = null;
        try (Connection connection = connectionService.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = new ProjectEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        id);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception while searching project", e);
            throw new RuntimeException(e);
        }
        return entity;
    }

    /**
     * Поиск всех проектов
     * @return список найденных проектов
     */
    @Override
    public List<ProjectEntity> readAll() {
        logger.log(Level.INFO, "searching all projects");
        List<ProjectEntity> entities = new ArrayList<>();
        try (Connection connection = connectionService.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                entities.add(new ProjectEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getLong(3)
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception while searching all projects", e);
            throw new RuntimeException(e);
        }
        return entities;
    }

    /**
     * Обновление проекта
     * @param entity проект с данными
     */
    @Override
    public void update(ProjectEntity entity) {
        logger.log(Level.INFO, "updating project");
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setLong(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception while updating project", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление проекта по идентификатору
     * @param id идентификатор
     */
    @Override
    public void delete(Long id) {
        logger.log(Level.INFO, "deleting project");
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception while deleting project", e);
            throw new RuntimeException(e);
        }
    }
}