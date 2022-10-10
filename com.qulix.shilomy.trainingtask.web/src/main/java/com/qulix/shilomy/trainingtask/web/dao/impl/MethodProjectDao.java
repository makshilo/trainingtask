package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.CommonDao;
import com.qulix.shilomy.trainingtask.web.dao.ProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.sql.Types.INTEGER;

public class MethodProjectDao extends CommonDao<ProjectEntity> implements ProjectDao {

    private static final String PROJECT_TABLE_NAME = "trainingtaskdb.project_list";
    private static final String PROJECT_NAME_COLUMN = "project_name";
    private static final String PROJECT_DESCRIPTION = "project_description";
    private static final String PROJECT_ID_COLUMN = "project_id";

    private static final String PROJECT_UNIQUE_HASH = "project_unique_hash";

    private static final Logger LOGGER = Logger.getLogger(MethodProjectDao.class.getName());

    private static final List<String> FIELDS = Arrays.asList(
            PROJECT_ID_COLUMN,
            PROJECT_NAME_COLUMN,
            PROJECT_DESCRIPTION,
            PROJECT_UNIQUE_HASH);

    private static MethodProjectDao instance;

    protected MethodProjectDao() {
        super(LOGGER);
    }

    public static synchronized MethodProjectDao getInstance() {
        if (instance == null) {
            instance = new MethodProjectDao();
        }
        return instance;
    }

    @Override
    protected String getTableName() {
        return PROJECT_TABLE_NAME;
    }

    @Override
    protected String getIdFieldName() {
        return PROJECT_ID_COLUMN;
    }

    @Override
    protected String getUniqueFieldName() {
        return PROJECT_UNIQUE_HASH;
    }

    @Override
    protected List<String> getFields() {
        return FIELDS;
    }

    @Override
    protected void fillEntity(PreparedStatement statement, ProjectEntity entity) throws SQLException {
        fillFields(statement, entity);
    }

    @Override
    protected void updateEntity(PreparedStatement statement, ProjectEntity entity) throws SQLException {
        fillFields(statement, entity);
        statement.setLong(1, entity.getId());
        statement.setLong(5, entity.getId());
    }

    @Override
    protected void fillUniqueField(PreparedStatement statement, ProjectEntity entity) throws SQLException {
        statement.setString(1, composeHashCode(entity));
    }

    @Override
    public List<ProjectEntity> receiveProjectByName(String name) {
        return receiveEntitiesByParam(PROJECT_NAME_COLUMN,name);
    }

    @Override
    protected ProjectEntity extractResultSet(ResultSet resultSet) throws SQLException {
        return new ProjectEntity(
                resultSet.getString(PROJECT_NAME_COLUMN),
                resultSet.getString(PROJECT_DESCRIPTION),
                resultSet.getLong(PROJECT_ID_COLUMN));
    }

    private void fillFields(PreparedStatement statement, ProjectEntity entity) throws SQLException {
        statement.setNull(1, INTEGER);
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getDescription());
        statement.setString(4, composeHashCode(entity));
    }

    private String composeHashCode(ProjectEntity projectEntity){return projectEntity.getName() + projectEntity.getDescription();}
}