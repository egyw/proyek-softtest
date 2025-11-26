package com.proyek_softtest.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration manager untuk membaca properties file
 */
public class TestConfig {
    private static Properties properties;
    private static final String CONFIG_FILE = "test-config.properties";
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = TestConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Unable to find " + CONFIG_FILE);
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getBaseUrl() {
        return properties.getProperty("base.url", "https://safe.openproject.com/");
    }
    
    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }
    
    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }
    
    public static boolean isMaximize() {
        return Boolean.parseBoolean(properties.getProperty("maximize", "true"));
    }
    
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait", "10"));
    }
    
    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait", "15"));
    }
    
    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout", "30"));
    }
    
    public static boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(properties.getProperty("screenshot.on.failure", "true"));
    }
    
    public static String getScreenshotFolder() {
        return properties.getProperty("screenshot.folder", "test-output/screenshots");
    }
    
    public static String getReportFolder() {
        return properties.getProperty("report.folder", "test-output/reports");
    }
    
    public static String getReportName() {
        return properties.getProperty("report.name", "Test Execution Report");
    }
    
    public static int getShortDelay() {
        return Integer.parseInt(properties.getProperty("short.delay", "1000"));
    }
    
    public static int getMediumDelay() {
        return Integer.parseInt(properties.getProperty("medium.delay", "2000"));
    }
    
    public static int getLongDelay() {
        return Integer.parseInt(properties.getProperty("long.delay", "3000"));
    }
}
