package com.spribe;

import java.util.Properties;
import java.io.IOException;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("config.properties is not loaded", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getConnectionTimeout() {
        return properties.getProperty("connection.timeout");
    }
}
