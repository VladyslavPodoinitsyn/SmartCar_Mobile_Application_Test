package com.smartcarmobile.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = new FileInputStream("configuration.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file!", e);
        }
    }

    public static String getProperty(String property) {
        String value = properties.getProperty(property);
        if (value == null) {
        } else {
        }
        return value;
    }
}
