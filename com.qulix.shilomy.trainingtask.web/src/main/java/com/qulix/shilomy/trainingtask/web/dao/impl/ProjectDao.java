package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.CommonDao;
import com.qulix.shilomy.trainingtask.web.dao.EntityDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.sql.Types.INTEGER;

/**
 * Класс реализация объекта доступа к данным для сущности проекта.
 */
public class ProjectDao extends CommonDao<ProjectEntity> implements EntityDao<ProjectEntity> {
    private static ProjectDao instance;
    private static final String PROJECT_TABLE_NAME = "trainingtaskdb.project_list";
    private static final String PROJECT_NAME_COLUMN = "project_name";
    private static final String PROJECT_DESCRIPTION = "description";
    private static final String PROJECT_ID_COLUMN = "id";

    private static final String PROJECT_UNIQUE_HASH = "unique_hash";

    private static final Logger LOGGER = Logger.getLogger(ProjectDao.class.getName());

    private static final List<String> FIELDS = Arrays.asList(
            PROJECT_ID_COLUMN,
            PROJECT_NAME_COLUMN,
            PROJECT_DESCRIPTION,
            PROJECT_UNIQUE_HASH);

    /**
     * Защищённый конструктор по умолчанию.
     */
    protected ProjectDao() {
        super(LOGGER);
    }

    /**
     * Метод получения объекта класса.
     * @return объект MethodProjectDao
     */
    public static synchronized ProjectDao getInstance() {
        if (instance == null) {
            instance = new ProjectDao();
        }
        return instance;
    }

    /**
     * Метод получения имени таблицы.
     * @return PROJECT_TABLE_NAME
     */
    @Override
    protected String getTableName() {
        return PROJECT_TABLE_NAME;
    }

    /**
     * Метод получения имени поля идентификатора.
     * @return PROJECT_ID_COLUMN
     */
    @Override
    protected String getIdFieldName() {
        return PROJECT_ID_COLUMN;
    }

    /**
     * Метод получения уникального поля.
     * @return PROJECT_UNIQUE_HASH
     */
    @Override
    protected String getUniqueFieldName() {
        return PROJECT_UNIQUE_HASH;
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
     * Метод заполнения сущности.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    @Override
    protected void fillEntity(PreparedStatement statement, ProjectEntity entity) throws SQLException {
        fillFields(statement, entity);
    }

    /**
     * Метод обновления сущности.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    @Override
    protected void updateEntity(PreparedStatement statement, ProjectEntity entity) throws SQLException {
        fillFields(statement, entity);
        statement.setLong(1, entity.getId());
        statement.setLong(5, entity.getId());
    }

    /**
     * Метод заполнения уникального поля.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    @Override
    protected void fillUniqueField(PreparedStatement statement, ProjectEntity entity) throws SQLException {
        statement.setString(1, composeHashCode(entity));
    }

    /**
     * Метод заполнения результрирующего множества.
     * @param resultSet результирующее множество
     * @return сущность
     * @throws SQLException ошибка базы данных
     */
    @Override
    protected ProjectEntity extractResultSet(ResultSet resultSet) throws SQLException {
        return new ProjectEntity(
                resultSet.getString(PROJECT_NAME_COLUMN),
                resultSet.getString(PROJECT_DESCRIPTION),
                resultSet.getLong(PROJECT_ID_COLUMN));
    }

    /**
     * Метод заполнения полей.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    private void fillFields(PreparedStatement statement, ProjectEntity entity) throws SQLException {
        statement.setNull(1, INTEGER);
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getDescription());
        statement.setString(4, composeHashCode(entity));
    }

    /**
     * Метод создания уникальной строки для работника
     * @param projectEntity сущность работника
     * @return уникальная строка
     */
    private String composeHashCode(ProjectEntity projectEntity){return projectEntity.getName() + projectEntity.getDescription();}
}