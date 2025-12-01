package com.proyek_softtest.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static volatile TestConfig instance;
    private final Properties properties;
    private static final String CONFIG_FILE = "test-config.properties";
    
    private TestConfig() {
        properties = new Properties();
        loadProperties();
    }
    
    private static TestConfig getInstance() {
        if (instance == null) {
            synchronized (TestConfig.class) {
                if (instance == null) {
                    instance = new TestConfig();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Configuration file '" + CONFIG_FILE + "' not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + CONFIG_FILE, e);
        }
    }
    
    private static String getProperty(String key, String defaultValue) {
        return getInstance().properties.getProperty(key, defaultValue);
    }
    
    // ╔══════════════════════════════════════════╗
    // ║         BASE CONFIGURATION               ║
    // ╚══════════════════════════════════════════╝

    public static String getBaseUrl() {
        return getProperty("base.url", "https://safe.openproject.com/");
    }
    
    // ╔══════════════════════════════════════════╗
    // ║      BROWSER CONFIGURATION               ║
    // ╚══════════════════════════════════════════╝

    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }
    
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }
    
    public static boolean isMaximize() {
        return Boolean.parseBoolean(getProperty("maximize", "true"));
    }
    
    // ╔══════════════════════════════════════════╗
    // ║       TIMEOUT CONFIGURATION              ║
    // ╚══════════════════════════════════════════╝

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "15"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout", "30"));
    }
}
