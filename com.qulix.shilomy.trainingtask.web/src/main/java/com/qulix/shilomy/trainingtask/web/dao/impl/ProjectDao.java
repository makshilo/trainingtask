package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.EntityDao;
import com.qulix.shilomy.trainingtask.web.db.ConnectionService;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.exception.DatabaseAccessException;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.sql.Types.INTEGER;

/**
 * Объект доступа к данным проекта
 */
public class ProjectDao implements EntityDao<ProjectEntity> {
    /**
     * Единственный объект класса
     */
    private static ProjectDao instance;

    /**
     * Сервис подключений
     */
    protected ConnectionService connectionService = ConnectionService.getInstance();

    private static final String TABLE_NAME = "trainingtaskdb.projects";
    private static final List<String> COLUMNS = Arrays.asList("project_name", "description");
    private static final List<String> fields = COLUMNS.stream().map(column -> column + "=?").collect(Collectors.toList());
    private static final String placeholders = String.join(", ", Collections.nCopies(COLUMNS.size() + 1, "?"));
    private static final String SQL_INSERT = String.format("INSERT INTO %s VALUES(%s)", TABLE_NAME, placeholders);
    private static final String SQL_SELECT_ALL = String.format("SELECT * FROM %s", TABLE_NAME);
    private static final String SQL_SELECT_BY_ID = String.format("SELECT * FROM %s WHERE id=?", TABLE_NAME);
    private static final String SQL_UPDATE = String.format("UPDATE %s SET %s WHERE id=?", TABLE_NAME, String.join(", ", fields));
    private static final String SQL_DELETE_BY_ID = String.format("DELETE FROM %s WHERE id=?", TABLE_NAME);

    public ProjectDao() {

    }

    /**
     * Получение объекта класса
     *
     * @return объект ProjectDao
     */
    public static synchronized ProjectDao getInstance() {
        if (instance == null) {
            instance = new ProjectDao();
        }
        return instance;
    }

    /**
     * Добавление проекта
     *
     * @param entity проект
     */
    @Override
    public void create(ProjectEntity entity) throws DatabaseAccessException {
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setNull(3, INTEGER);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, creating project record", e);
        }
    }

    /**
     * Поиск проекта
     *
     * @param id идентификатор
     * @return проект
     */
    @Override
    public Optional<ProjectEntity> read(Long id) throws DatabaseAccessException {
        Optional<ProjectEntity> entity = Optional.empty();
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = Optional.of(new ProjectEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        id));
            }
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, searching project record", e);
        }
        return entity;
    }

    /**
     * Поиск всех проектов
     *
     * @return список проектов
     */
    @Override
    public List<ProjectEntity> readAll() throws DatabaseAccessException {
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
            throw new DatabaseAccessException("Database exception occurred, searching all project records", e);
        }
        return entities;
    }

    /**
     * Обновление проекта
     *
     * @param entity проект
     */
    @Override
    public void update(ProjectEntity entity) throws DatabaseAccessException {
        try (Connection connection = connectionService.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setLong(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseAccessException("Database exception occurred, updating project record", e);
        }
    }

    /**
     * Удаление проекта по идентификатору
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
            throw new DatabaseAccessException("Database exception occurred, deleting project record", e);
        }
    }
}