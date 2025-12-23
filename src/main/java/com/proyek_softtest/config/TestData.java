package com.proyek_softtest.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Test data manager for loading test credentials and test data from properties file.
 * This class provides centralized access to all test data used across test cases.
 */
public class TestData {
    private static volatile TestData instance;
    private final Properties properties;
    private static final String DATA_FILE = "test-data.properties";
    
    private TestData() {
        properties = new Properties();
        loadProperties();
    }
    
    private static TestData getInstance() {
        if (instance == null) {
            synchronized (TestData.class) {
                if (instance == null) {
                    instance = new TestData();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(DATA_FILE)) {
            if (input == null) {
                throw new RuntimeException("Test data file '" + DATA_FILE + "' not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data file: " + DATA_FILE, e);
        }
    }
    
    private static String getProperty(String key, String defaultValue) {
        return getInstance().properties.getProperty(key, defaultValue);
    }
    
    // ╔══════════════════════════════════════════╗
    // ║       SIGN IN CREDENTIALS                ║
    // ╚══════════════════════════════════════════╝
    
    public static String getValidUsername() {
        return getProperty("signin.valid.username", "");
    }
    
    public static String getValidPassword() {
        return getProperty("signin.valid.password", "");
    }
    
    public static String getInvalidUsername() {
        return getProperty("signin.invalid.username", "");
    }
    
    public static String getInvalidPassword() {
        return getProperty("signin.invalid.password", "");
    }
    
    // ╔══════════════════════════════════════════╗
    // ║       LOST PASSWORD DATA                  ║
    // ╚══════════════════════════════════════════╝
    
    public static String getLostPasswordEmail() {
        return getProperty("lostpassword.email", "test@example.com");
    }
    
    // ╔══════════════════════════════════════════╗
    // ║       SEARCH TEST DATA                   ║
    // ╚══════════════════════════════════════════╝
    
    // BasicSidebarTest - Projects Selection Search
    public static String getSidebarInvalidSearchKeyword() {
        return getProperty("search.sidebar.invalid.keyword", "abc");
    }
    
    public static String getSidebarValidSearchFullname() {
        return getProperty("search.sidebar.valid.fullname", "scrum project");
    }
    
    public static String getSidebarValidSearchPartial() {
        return getProperty("search.sidebar.valid.partial", "rum");
    }
    
    public static String getSidebarExpectedSearchResult() {
        return getProperty("search.sidebar.expected.result", "Scrum");
    }
    
    // NavbarPageTest - Navbar Search Bar
    public static String getNavbarNoResultsSearch() {
        return getProperty("search.navbar.no.results", "abc");
    }
    
    public static String getNavbarIdSearch() {
        return getProperty("search.navbar.id", "70");
    }
    
    public static String getNavbarCategorySearch() {
        return getProperty("search.navbar.category", "art-2 design");
    }
    
    public static String getNavbarCategoryProject() {
        return getProperty("search.navbar.category.project", "ART-2 Design");
    }
    
    public static String getNavbarTitleSearch() {
        return getProperty("search.navbar.title", "SSL certificate");
    }
    
    public static String getNavbarTitleSubject() {
        return getProperty("search.navbar.title.subject", "SSL certificate");
    }
}
