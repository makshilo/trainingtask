package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.CommonDao;
import com.qulix.shilomy.trainingtask.web.dao.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.sql.Types.INTEGER;

/**
 * Класс реализация объекта доступа к данным для сущности задачи.
 */
public class MethodTaskDao extends CommonDao<TaskEntity> implements TaskDao {
    private static MethodTaskDao instance;

    private static final String TASK_TABLE_NAME = "trainingtaskdb.task_list";
    private static final String TASK_NAME_COLUMN = "task_name";
    private static final String TASK_PROJECT_ID_COLUMN = "task_project_id";
    private static final String TASK_WORK_COLUMN = "task_work";
    private static final String TASK_START_DATE_COLUMN = "task_start_date";
    private static final String TASK_END_DATE_COLUMN = "task_end_date";
    private static final String TASK_STATUS_COLUMN = "task_status";
    private static final String TASK_EXECUTOR_ID_COLUMN = "task_executor_id";
    private static final String TASK_ID = "task_id";
    private static final String TASK_UNIQUE_HASH = "task_unique_hash";


    private static final Logger LOGGER = Logger.getLogger(MethodTaskDao.class.getName());

    private static final List<String> FIELDS = Arrays.asList(
            TASK_ID,
            TASK_NAME_COLUMN,
            TASK_PROJECT_ID_COLUMN,
            TASK_WORK_COLUMN,
            TASK_START_DATE_COLUMN,
            TASK_END_DATE_COLUMN,
            TASK_STATUS_COLUMN,
            TASK_EXECUTOR_ID_COLUMN,
            TASK_UNIQUE_HASH);

    /**
     * Защищённый конструктор по умолчанию.
     */
    protected MethodTaskDao() {
        super(LOGGER);
    }

    /**
     * Метод получения объекта класса.
     * @return объект MethodTaskDao
     */
    public static synchronized MethodTaskDao getInstance() {
        if (instance == null) {
            instance = new MethodTaskDao();
        }
        return instance;
    }

    /**
     * Метод получения имени таблицы.
     * @return TASK_TABLE_NAME
     */
    @Override
    protected String getTableName() {
        return TASK_TABLE_NAME;
    }

    /**
     * Метод получения имени поля идентификатора.
     * @return TASK_ID
     */
    @Override
    protected String getIdFieldName() {
        return TASK_ID;
    }

    /**
     * Метод получения уникального поля.
     * @return TASK_UNIQUE_HASH
     */
    @Override
    protected String getUniqueFieldName() {
        return TASK_UNIQUE_HASH;
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
    protected void fillEntity(PreparedStatement statement, TaskEntity entity) throws SQLException {
        fillFields(statement, entity);
    }

    /**
     * Метод обновления сущности.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    @Override
    protected void updateEntity(PreparedStatement statement, TaskEntity entity) throws SQLException {
        fillFields(statement, entity);
        statement.setLong(1, entity.getId());
        statement.setLong(10, entity.getId());
    }

    /**
     * Метод заполнения уникального поля.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    @Override
    protected void fillUniqueField(PreparedStatement statement, TaskEntity entity) throws SQLException {
        statement.setString(1, composeHashCode(entity));
    }

    /**
     * Метод заполнения результрирующего множества.
     * @param resultSet результирующее множество
     * @return сущность
     * @throws SQLException ошибка базы данных
     */
    @Override
    protected TaskEntity extractResultSet(ResultSet resultSet) throws SQLException {
        return new TaskEntity(
                TaskStatus.of(resultSet.getString(TASK_STATUS_COLUMN)),
                resultSet.getString(TASK_NAME_COLUMN),
                resultSet.getLong(TASK_PROJECT_ID_COLUMN),
                resultSet.getString(TASK_WORK_COLUMN),
                resultSet.getDate(TASK_START_DATE_COLUMN),
                resultSet.getDate(TASK_END_DATE_COLUMN),
                resultSet.getLong(TASK_EXECUTOR_ID_COLUMN),
                resultSet.getLong(TASK_ID));
    }

    /**
     * Метод получения задачи по проекту.
     * @param project проект который содержит задачу.
     * @return задачи принадлежащие проекту
     */
    @Override
    public List<TaskEntity> receiveTaskByProject(ProjectEntity project) {
        return receiveEntitiesByParam(TASK_PROJECT_ID_COLUMN, project.getId());
    }

    /**
     * Метод заполнения полей.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    private void fillFields(PreparedStatement statement, TaskEntity entity) throws SQLException {
        statement.setNull(1, INTEGER);
        statement.setString(2, entity.getName());
        statement.setLong(3, entity.getProjectId());
        statement.setString(4, entity.getWork());
        statement.setDate(5, entity.getStartDate());
        statement.setDate(6, entity.getEndDate());
        statement.setString(7, entity.getStatus().name());
        statement.setLong(8, entity.getExecutorId());
        statement.setString(9, composeHashCode(entity));
    }

    /**
     * Метод создания уникальной строки для работника
     * @param taskEntity сущность работника
     * @return уникальная строка
     */
    private String composeHashCode(TaskEntity taskEntity) {
        return taskEntity.getName() + taskEntity.getExecutorId() + taskEntity.getStartDate() + taskEntity.getEndDate();
    }
}
