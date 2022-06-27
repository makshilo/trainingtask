package com.qulix.shilomy.trainingtask.web.db;

import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.exception.CouldNotInitialiseConnectionService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionService {

    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";

    private static final Logger LOGGER = Logger.getLogger(ConnectionService.class.getName());

    private static ConnectionService instance;

    private final static PropertyContext propertyContext = PropertyContext.getInstance();

    private ConnectionService() throws CouldNotInitialiseConnectionService {
        registerDrivers();
    }

    public static synchronized ConnectionService getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionService();
            } catch (CouldNotInitialiseConnectionService e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException {
        LOGGER.finest("creating connection");
        return DriverManager.getConnection(
                propertyContext.get(DB_URL),
                propertyContext.get(DB_USER),
                propertyContext.get(DB_PASSWORD));
    }

    public void registerDrivers() throws CouldNotInitialiseConnectionService {
        LOGGER.finest("driver registration");
        try {
            DriverManager.registerDriver(DriverManager.getDriver(propertyContext.get(DB_URL)));
        } catch (SQLException e) {
            throw new CouldNotInitialiseConnectionService("Failed to register driver", e);
        }
    }
}
