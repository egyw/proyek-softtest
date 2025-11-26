package com.proyek_softtest.base;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import com.proyek_softtest.config.TestConfig;
import com.proyek_softtest.utils.DriverManager;
import com.proyek_softtest.utils.ScreenshotUtil;

import java.io.ByteArrayInputStream;

/**
 * Base Test class - semua test class harus extend class ini
 * Menyediakan common setup/teardown dan utilities
 */
public class BaseTest {
    protected static WebDriver driver;
    
    @BeforeAll
    public static void globalSetup() {
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║   OpenProject Automation Test - Starting    ║");
        System.out.println("╚═════════════════════════════════════════════╝\n");
        
        // Initialize Driver - HANYA SEKALI
        DriverManager.initializeDriver();
        driver = DriverManager.getDriver();
        
        // Navigate to base URL - HANYA SEKALI
        String baseUrl = TestConfig.getBaseUrl();
        System.out.println("🌐 Opening website: " + baseUrl);
        driver.get(baseUrl);
        System.out.println("✅ Browser opened successfully\n");
        
        // Set Allure environment info
        Allure.parameter("Application", "OpenProject");
        Allure.parameter("Environment", "Test");
        Allure.parameter("Browser", TestConfig.getBrowser());
        Allure.parameter("Base URL", baseUrl);
    }
    
    @BeforeEach
    public void setUp(TestInfo testInfo) {
        System.out.println("▶ Starting Test: " + testInfo.getDisplayName());
        Allure.step("Starting Test: " + testInfo.getDisplayName());
    }
    
    @AfterEach
    public void tearDown(TestInfo testInfo) {
        System.out.println("✓ Test completed: " + testInfo.getDisplayName() + "\n");
    }
    
    @AfterAll
    public static void globalTearDown() {
        System.out.println("========================================");
        System.out.println("  ALL TESTS COMPLETED - CLOSING BROWSER");
        System.out.println("========================================");
        
        // Quit Driver - HANYA SEKALI
        DriverManager.quitDriver();
        
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║   OpenProject Automation Test Suite - Completed   ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
    
    /**
     * Helper method untuk log info ke Allure report
     */
    @Step("{message}")
    protected void logInfo(String message) {
        System.out.println("ℹ " + message);
        Allure.step(message);
    }
    
    /**
     * Helper method untuk log pass ke Allure report
     */
    @Step("✓ {message}")
    protected void logPass(String message) {
        System.out.println("✓ " + message);
    }
    
    /**
     * Helper method untuk log warning ke Allure report
     */
    @Step("⚠ {message}")
    protected void logWarning(String message) {
        System.out.println("⚠ " + message);
    }
    
    /**
     * Helper method untuk attach screenshot ke Allure
     */
    protected void attachScreenshot(String name) {
        try {
            byte[] screenshot = ScreenshotUtil.captureScreenshotAsBytes(driver);
            if (screenshot != null) {
                Allure.addAttachment(name, "image/png", new ByteArrayInputStream(screenshot), ".png");
            }
        } catch (Exception e) {
            System.err.println("Failed to attach screenshot: " + e.getMessage());
        }
    }
}
