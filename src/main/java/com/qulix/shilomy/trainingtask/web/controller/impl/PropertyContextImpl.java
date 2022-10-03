package com.qulix.shilomy.trainingtask.web.controller.impl;

import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class PropertyContextImpl implements PropertyContext {
    private static final Logger LOGGER = Logger.getLogger(PropertyContextImpl.class.getName());
    private static final String PAGE_PROPERTIES_PATH = "src/main/resources/page.properties";
    private static final String CONFIG_PROPERTIES_PATH = "src/main/resources/database.properties";
    private static final String COMMAND_PROPERTIES_PATH = "src/main/resources/command.properties";
    private static final String SERVER_PROPERTIES_PATH = "src/main/resources/server.properties";
    private static final String PAGE_ATTRIBUTE = "page.";
    private static final String DB_ATTRIBUTE = "db.";
    private static final String COMMAND_ATTRIBUTE = "command/";

    private static final String SERVER_ATTRIBUTE = "server.";
    private static final String EMPTY_STRING = "";

    private final Map<String, String> propertyByKeys;

    private PropertyContextImpl() {
        propertyByKeys = new ConcurrentHashMap<>();
    }

    public static PropertyContextImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String get(String name) {
        return propertyByKeys.computeIfAbsent(name, this::receiveProperty);
    }

    private String receiveProperty(String name) {
        try (final FileInputStream stream = new FileInputStream(receiveFilePath(name))) {
            final Properties properties = new Properties();
            properties.load(stream);
            return properties.getProperty(name);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error while reading file", e);
        }
        return EMPTY_STRING;
    }

    private String receiveFilePath(String name) throws FileNotFoundException {
        if (name.startsWith(PAGE_ATTRIBUTE)) {
            return PAGE_PROPERTIES_PATH;
        } else if (name.startsWith(DB_ATTRIBUTE)) {
            return CONFIG_PROPERTIES_PATH;
        } else if (name.startsWith(COMMAND_ATTRIBUTE)) {
            return COMMAND_PROPERTIES_PATH;
        } else if (name.startsWith(SERVER_ATTRIBUTE)) {
            return SERVER_PROPERTIES_PATH;
        } else {
            throw new FileNotFoundException(format("No such file for: %s", name));
        }
    }

    private static class Holder {
        private static final PropertyContextImpl INSTANCE = new PropertyContextImpl();
    }
}
