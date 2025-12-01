package com.proyek_softtest.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.proyek_softtest.config.TestConfig;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.time.Duration;

/**
 * WebDriver factory with ThreadLocal pattern for parallel test execution.
 * Supports Chrome and Edge browsers.
 */
public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    private DriverManager() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    public static void initializeDriver() {
        if (driver.get() != null) {
            return;
        }
        
        String browser = TestConfig.getBrowser().toLowerCase();
        WebDriver webDriver;
        
        switch (browser) {
            case "chrome":
                webDriver = createChromeDriver();
                break;
                
            case "edge":
                webDriver = createEdgeDriver();
                break;
                
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser + 
                    ". Supported browsers: chrome, edge");
        }
        
        configureTimeouts(webDriver);
        configureWindow(webDriver, browser);
        
        driver.set(webDriver);
    }
    
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        if (TestConfig.isHeadless()) {
            options.addArguments("--headless=new");
        }
        
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        return new ChromeDriver(options);
    }
    
    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        
        if (TestConfig.isHeadless()) {
            options.addArguments("--headless=new");
        }
        
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        return new EdgeDriver(options);
    }
    
    private static void configureTimeouts(WebDriver webDriver) {
        webDriver.manage().timeouts()
            .implicitlyWait(Duration.ofSeconds(TestConfig.getImplicitWait()));
        webDriver.manage().timeouts()
            .pageLoadTimeout(Duration.ofSeconds(TestConfig.getPageLoadTimeout()));
    }
    
    private static void configureWindow(WebDriver webDriver, String browser) {
        if (!TestConfig.isHeadless()) {
            if (TestConfig.useSecondaryMonitor()) {
                moveToSecondaryMonitor(webDriver);
            } else if (TestConfig.isMaximize()) {
                webDriver.manage().window().maximize();
            }
        }
    }
    
    private static void moveToSecondaryMonitor(WebDriver webDriver) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] screens = ge.getScreenDevices();
            
            System.out.println("=== MONITOR DETECTION ===");
            System.out.println("Total monitors detected: " + screens.length);
            
            if (screens.length > 1) {
                // Get second monitor bounds
                Rectangle bounds = screens[1].getDefaultConfiguration().getBounds();
                System.out.println("Secondary monitor bounds: " + bounds);
                webDriver.manage().window().setPosition(new Point(bounds.x, bounds.y));
                webDriver.manage().window().setSize(new Dimension(bounds.width, bounds.height));
                System.out.println("Browser moved to SECONDARY monitor");
                System.out.println("To disable: Set 'use.secondary.monitor=false' in test-config.properties");
            } else {
                // Fallback: Only one monitor detected, maximize on primary
                webDriver.manage().window().maximize();
                System.out.println("Only ONE monitor detected, using PRIMARY monitor");
            }
            System.out.println("=========================");
        } catch (Exception e) {
            // Fallback on error
            webDriver.manage().window().maximize();
            System.err.println("Failed to move to secondary monitor: " + e.getMessage());
        }
    }
    
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }
    
    public static void quitDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
            } finally {
                driver.remove();
            }
        }
    }
}
