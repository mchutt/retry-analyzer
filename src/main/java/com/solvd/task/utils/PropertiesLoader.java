package com.solvd.task.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);
    private static final String APP_PROPERTIES = "app.properties";
    private static final String USER_PROPERTIES = "user.properties";
    private static final Properties appProperties = new Properties();
    private static final Properties userProperties = new Properties();

    public enum PropertySource {
        APP,
        USER
    }

    static {
        loadProperty(appProperties, APP_PROPERTIES);
        loadProperty(userProperties, USER_PROPERTIES);
    }

    public static Properties getProperties(PropertySource source) {
        return source == PropertySource.APP ? appProperties : userProperties;
    }

    public static String getProperty(PropertySource source, String key) {
        return getProperty(source, key, null);
    }

    public static String getProperty(PropertySource source, String key, String defaultValue) {
        Properties properties = getProperties(source);
        String property = properties.getProperty(key, defaultValue);
        if (property == null || property.equalsIgnoreCase(defaultValue)) {
            LOGGER.warn("Property with name: '{}' not found OR its value is equals to the default one. Properties file '{}'. Setting the default value as: {}", key, source.name(), defaultValue);
        }
        return property;
    }


    // helper methods
    private static void loadProperty(Properties properties, String fileName) {
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(fileName));
            LOGGER.info("Properties loaded from file '{}'", fileName);
        } catch (IOException e) {
            LOGGER.warn("Could not load properties from '{}'. {}", fileName, e);
        }
    }
}
