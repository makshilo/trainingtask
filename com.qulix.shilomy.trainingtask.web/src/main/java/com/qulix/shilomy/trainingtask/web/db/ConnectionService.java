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
 * Класс, который предназначен для выдачи соединений с базой данных
 */
public class ConnectionService {
    public static final String DB_URL_PROPERTY_NAME = "dbUrl";
    public static final String DB_USER_PROPERTY_NAME = "dbUser";
    public static final String DB_PASSWORD_PROPERTY_NAME = "dbPassword";

    private final String APP_CONFIG_PATH = "../gradle.properties";
    private final Properties appProperties;

    private static final Logger LOGGER = Logger.getLogger(ConnectionService.class.getName());

    private static ConnectionService instance;

    /**
     * Приватный конструктор, который получает файл с параметрами и рагистрирует драйвер базы данных.
     * @throws ConnectionServiceInitializationFailed ошибка инициализации сервиса подключений
     * @throws IOException ошибка чтения файла с параметрами
     */
    private ConnectionService() throws ConnectionServiceInitializationFailed, IOException {
        appProperties = new Properties();
        appProperties.load(new FileInputStream(APP_CONFIG_PATH));
        registerDrivers();
    }

    /**
     * Метод получения единственного объекта этого класса
     * @return объект ConnectionService
     */
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
     * Метод который создаёт соединение
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
     * Метод регистрации драйвера базы данных
     * @throws ConnectionServiceInitializationFailed если ошибка возникает при регистрации драйвера
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
