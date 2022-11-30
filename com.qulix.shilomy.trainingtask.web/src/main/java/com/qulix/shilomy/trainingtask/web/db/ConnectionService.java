package com.qulix.shilomy.trainingtask.web.db;

import com.qulix.shilomy.trainingtask.web.exception.ConnectionServiceInitializationFailed;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Сервис выдачи соединений в базу данных
 */
public class ConnectionService {
    public static final String DB_URL_PROPERTY_NAME = "dbUrl";
    public static final String DB_USER_PROPERTY_NAME = "dbUser";
    public static final String DB_PASSWORD_PROPERTY_NAME = "dbPassword";

    private final String APP_CONFIG_PATH = "../gradle.properties";
    private final Properties appProperties = new Properties();

    private static final Logger LOGGER = Logger.getLogger(ConnectionService.class.getName());

    private static ConnectionService instance;

    /**
     * Конструктор с регистрацией драйвера
     * @throws ConnectionServiceInitializationFailed ошибка инициализации сервиса подключений
     * @throws IOException ошибка чтения файла с параметрами
     */
    private ConnectionService() throws ConnectionServiceInitializationFailed, IOException {
        appProperties.load(new FileInputStream(APP_CONFIG_PATH));
        registerDrivers();
    }

    public static synchronized ConnectionService getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionService();
            } catch (ConnectionServiceInitializationFailed | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    /**
     * Получение соединения
     * @return Connection
     * @throws SQLException если при открытии соединения,
     * возникают ошибки со стороны базы данных
     */
    public synchronized Connection getConnection() throws SQLException {
        LOGGER.finest("creating connection");
        return DriverManager.getConnection(
                appProperties.getProperty(DB_URL_PROPERTY_NAME),
                appProperties.getProperty(DB_USER_PROPERTY_NAME),
                appProperties.getProperty(DB_PASSWORD_PROPERTY_NAME));
    }

    /**
     * Регистрация сервера базы данных
     * @throws ConnectionServiceInitializationFailed ошибка при регистрации драйвера
     */
    public void registerDrivers() throws ConnectionServiceInitializationFailed {
        LOGGER.finest("driver registration");
        try {
            DriverManager.registerDriver(DriverManager.getDriver(appProperties.getProperty(DB_URL_PROPERTY_NAME)));
        } catch (SQLException e) {
            throw new ConnectionServiceInitializationFailed("Failed to register driver", e);
        }
    }
}
