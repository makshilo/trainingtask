package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.db.ConnectionService;
import com.qulix.shilomy.trainingtask.web.db.ResultSetExtractor;
import com.qulix.shilomy.trainingtask.web.db.StatementPreparator;
import com.qulix.shilomy.trainingtask.web.entity.Entity;
import com.qulix.shilomy.trainingtask.web.exception.EntityExtractionFailedException;
import com.qulix.shilomy.trainingtask.web.exception.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.lang.String.join;

/**
 * Абстрактный класс, который задаёт базовые для всех классов доступа к данным методы.
 * @param <T> объект класса реализующий интерфейс сущности
 */
public abstract class CommonDao<T extends Entity> implements EntityDao<T> {

    private static final String SELECT_ALL_FROM = "select %s from %s";
    private static final String INSERT_INTO = "insert into %s (%s) values(%s)";
    private static final String WHERE_FIELD = "where %s = ?";
    private static final String UPDATE_SET = "update %s set %s";
    private static final String DELETE_FROM = "delete from %s";
    private static final String COMMA = ", ";
    private static final String SPACE = " ";
    private static final String PREPARE_STATEMENT_PARAM = "?";
    private static final String UPDATE_PARAM = "=?";

    private final Logger logger;

    ConnectionService connectionService;
    private final String selectByUFExpression;
    private final String selectByIdExpression;
    private final String selectAllExpression;
    private final String insertExpression;
    private final String updateExpression;
    private final String deleteExpression;

    /**
     * Защищённый конструктор, который заполняет строки выражений для базы данных,
     * а также получает объект сервиса подключений.
     */
    protected CommonDao(Logger logger) {
        this.logger = logger;
        connectionService = ConnectionService.getInstance();
        this.selectAllExpression = format(SELECT_ALL_FROM, join(COMMA, getFields()), getTableName());
        this.selectByIdExpression = this.selectAllExpression + SPACE + format(WHERE_FIELD, getIdFieldName());
        this.insertExpression = format(INSERT_INTO, getTableName(), join(COMMA, getFields()), join(COMMA, insertParams()));
        this.selectByUFExpression = this.selectAllExpression + SPACE + format(WHERE_FIELD, getUniqueFieldName());
        this.deleteExpression = format(DELETE_FROM, getTableName()) + SPACE + format(WHERE_FIELD, getIdFieldName());
        this.updateExpression = format(UPDATE_SET, getTableName(), join(COMMA, updateParams())) + SPACE + format(WHERE_FIELD, getIdFieldName());
    }

    /**
     * Метод вставки параметров.
     * @return список параметров
     */
    private List<String> insertParams() {
        final List<String> parameters = new ArrayList<>();
        getFields().forEach(param -> parameters.add(PREPARE_STATEMENT_PARAM));
        return parameters;
    }

    /**
     * Метод добавления символа обновления для параметров сущности
     * @return список параметров для обновления
     */
    private List<String> updateParams() {
        final List<String> parameters = new ArrayList<>();
        getFields().forEach(param -> parameters.add(param + UPDATE_PARAM));
        return parameters;
    }

    /**
     * Метод который добавляет сущность в базу, а затем возвращает по уникальному полю.
     *
     * @param t сущность для создания
     * @return созданная сущность
     * @throws InterruptedException ошибка прерывания действия
     */
    @Override
    public Optional<T> create(T t) throws InterruptedException {
        try {
            update(insertExpression, st -> fillEntity(st, t));
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "take connection interrupted", e);
            Thread.currentThread().interrupt();
            throw e;
        }
        return search(selectByUFExpression, ps -> fillUniqueField(ps, t), this::extractResultCheckingException);
    }

    /**
     * Метод поиска по идентификатору.
     * @param id идентификатор
     * @return найденная сущность
     */
    @Override
    public synchronized Optional<T> read(Long id) {
        try {
            return search(selectByIdExpression, ps -> ps.setLong(1, id), this::extractResultCheckingException);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "take connection interrupted", e);
            return Optional.empty();
        }
    }

    /**
     * Метод поиска всех сущностей.
     * @return список найденных сущностей
     */
    @Override
    public List<T> readAll() {
        try {
            return searchList(selectAllExpression, st -> {
            }, this::extractResultCheckingException);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "take connection interrupted", e);
            Thread.currentThread().interrupt();
        }
        return Collections.emptyList();
    }

    /**
     * Метод который обновляет все поля сущности.
     *
     * @param t сущность для обновления.
     * @return обновлённая сущность
     * @throws InterruptedException    ошибка прерывания действия
     * @throws EntityNotFoundException ошибка поиска сущности
     */
    @Override
    public synchronized Optional<T> update(T t) throws InterruptedException, EntityNotFoundException {
        try {
            update(updateExpression, ps -> updateEntity(ps, t));
            return search(selectByUFExpression, st -> fillUniqueField(st, t), this::extractResultCheckingException);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "take connection interrupted");
            throw e;
        }
    }

    /**
     * Метод удаления сущности.
     * @param id идентификатор
     * @return результат удаления
     */
    @Override
    public boolean delete(Long id) {
        try {
            final int updatedLines = update(deleteExpression, ps -> ps.setLong(1, id));
            return updatedLines > 0;
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "take connection interrupted");
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /**
     * Метод который выполняет поиск непосредственно в базе,
     * метод получает соединение,
     * записывает в утверждение строку запроса,
     * выполняет запрос, закрывает подключение,
     * @param sql строка с запросом в базу данных
     * @param statementPreparation интерфейс подготовки утверждения
     * @param extractor интерфейс работы с результирующим множеством
     * @return результирующее множество или Optional.empty() если извлечение не произведено
     * @param <R> тип сущности
     * @throws InterruptedException ошибка прерывания действия
     */
    private <R> Optional<R> search(String sql, StatementPreparator statementPreparation,
                         ResultSetExtractor<R> extractor) throws InterruptedException {
        try (Connection conn = connectionService.getConnection();
             final PreparedStatement statement = conn.prepareStatement(sql)) {
            statementPreparation.accept(statement);
            final ResultSet resultSet = statement.executeQuery();
            conn.close();
            return resultSet.next()
                    ? Optional.of(extractor.extract(resultSet))
                    : Optional.empty();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception occurred", e);
        } catch (EntityExtractionFailedException e) {
            logger.log(Level.SEVERE, "could not extract entity", e);
        }
        return Optional.empty();
    }

    /**
     * Метод который выполняет поиск непосредственно в базе,
     * метод получает соединение,
     * записывает в утверждение строку запроса,
     * выполняет запрос, закрывает подключение,
     * @param sql строка с запросом в базу данных
     * @param statementPreparation интерфейс подготовки утверждения
     * @param extractor интерфейс работы с результирующим множеством
     * @return результирующее множество в виде списка или Optional.empty() если извлечение не произведено
     * @param <R> тип сущности
     * @throws InterruptedException ошибка прерывания действия
     */
    private <R> List<R> searchList(String sql, StatementPreparator statementPreparation,
                                   ResultSetExtractor<R> extractor) throws InterruptedException {
        try (Connection conn = connectionService.getConnection();
             final PreparedStatement statement = conn.prepareStatement(sql)) {
            statementPreparation.accept(statement);
            final ResultSet resultSet = statement.executeQuery();
            conn.close();
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception occurred", e);
        } catch (EntityExtractionFailedException e) {
            logger.log(Level.SEVERE, "could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Метод который выполняет непосредственно обновление сущности в базе данных.
     * метод получает соединение,
     * записывает в утверждение строку запроса,
     * выполняет обновление, закрывает подключение,
     * @param sql строка с запросом в базу данных
     * @param statementPreparation интерфейс подготовки утверждения
     * @return результат обновления
     * @throws InterruptedException ошибка прерывания действия
     */
    private int update(String sql, StatementPreparator statementPreparation) throws InterruptedException {
        try (Connection conn = connectionService.getConnection();
             final PreparedStatement statement = conn.prepareStatement(sql)) {
            statementPreparation.accept(statement);
            int result = statement.executeUpdate();
            conn.close();
            return result;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "sql exception occurred", e);
        }
        return 0;
    }

    /**
     * Метод который достаёт данные из результирующего множества.
     * @param resultSet результирующее множество
     * @return данные из результирующего множества
     * @throws EntityExtractionFailedException ошибка получения сущности
     */
    private T extractResultCheckingException(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return extractResultSet(resultSet);
        } catch (SQLException e) {
            logger.severe("sql exception occurred extraction user from resultSet");
            throw new EntityExtractionFailedException("failed to extract entity", e);
        }
    }

    /**
     * Метод получения сущности по параметру.
     * @param fieldName название поля
     * @param param параметр сущности
     * @return список найденных сущностей
     */
    protected List<T> receiveEntitiesByParam(String fieldName, Object param) {
        final String selectByField = format(
                SELECT_ALL_FROM, join(COMMA, getFields()), getTableName()) + SPACE + format(WHERE_FIELD, fieldName
        );
        try {
            return searchList(selectByField, st -> st.setObject(1, param), this::extractResultCheckingException);
        } catch (InterruptedException e) {
            logger.warning("take connection interrupted");
            Thread.currentThread().interrupt();
        }
        return Collections.emptyList();
    }

    /**
     * Метод получения имени таблицы.
     */
    protected abstract String getTableName();

    /**
     * Метод получения имени поля идентификатора.
     */
    protected abstract String getIdFieldName();

    /**
     * Метод получения уникального поля.
     */
    protected abstract String getUniqueFieldName();

    /**
     * Метод получения списка полей.
     */
    protected abstract List<String> getFields();

    /**
     * Метод заполнения сущности.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    protected abstract void fillEntity(PreparedStatement statement, T entity) throws SQLException;

    /**
     * Метод обновления сущности.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    protected abstract void updateEntity(PreparedStatement statement, T entity) throws SQLException;

    /**
     * Метод заполнения уникального поля.
     * @param statement утверждение
     * @param entity сущность
     * @throws SQLException ошибка базы данных
     */
    protected abstract void fillUniqueField(PreparedStatement statement, T entity) throws SQLException;

    /**
     * Метод заполнения результрирующего множества.
     * @param resultSet результирующее множество
     * @return сущность
     * @throws SQLException ошибка базы данных
     */
    protected abstract T extractResultSet(ResultSet resultSet) throws SQLException;
}
