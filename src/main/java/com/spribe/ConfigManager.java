package com.spribe;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException exception) {
            throw new RuntimeException("config.properties is not loaded", exception);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value;
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getThreadCount() {
        String threadCount = System.getProperty("thread.count");
        if (threadCount != null && !threadCount.isEmpty()) {
            return threadCount;
        }
        return getProperty("thread.count");
    }

    public static int getConnectionTimeout() {
        return Integer.parseInt(getProperty("http.connection.timeout"));
    }

    public static int getSocketTimeout() {
        return Integer.parseInt(getProperty("http.socket.timeout"));
    }
}
