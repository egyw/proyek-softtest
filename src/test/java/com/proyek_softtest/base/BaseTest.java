package com.proyek_softtest.base;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.proyek_softtest.config.TestConfig;
import com.proyek_softtest.utils.DriverManager;
import com.proyek_softtest.utils.ReportManager;
import com.proyek_softtest.utils.ScreenshotUtil;

/**
 * Base Test class - semua test class harus extend class ini
 * Menyediakan common setup/teardown dan utilities
 */
public class BaseTest {
    protected static WebDriver driver;
    
    @BeforeAll
    public static void globalSetup() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║   OpenProject Automation Test Suite - Starting    ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        // Initialize Report
        ReportManager.initReport();
        
        // Initialize Driver - HANYA SEKALI
        DriverManager.initializeDriver();
        driver = DriverManager.getDriver();
        
        // Navigate to base URL - HANYA SEKALI
        String baseUrl = TestConfig.getBaseUrl();
        System.out.println("🌐 Opening website: " + baseUrl);
        driver.get(baseUrl);
        System.out.println("✅ Browser opened successfully\n");
    }
    
    @BeforeEach
    public void setUp(TestInfo testInfo) {
        System.out.println("▶ Starting Test: " + testInfo.getDisplayName());
        
        // Create test in report
        ReportManager.createTest(
            testInfo.getDisplayName(),
            testInfo.getTestClass().get().getSimpleName()
        );
        
        ReportManager.getTest().log(Status.INFO, "Test started");
        ReportManager.getTest().log(Status.INFO, "Browser: " + TestConfig.getBrowser());
    }
    
    @AfterEach
    public void tearDown(TestInfo testInfo) {
        try {
            // Check if test failed
            if (testInfo.getTags().contains("failed")) {
                String screenshotPath = ScreenshotUtil.captureScreenshot(
                    driver, 
                    testInfo.getDisplayName()
                );
                
                if (screenshotPath != null) {
                    ReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
                }
                
                ReportManager.getTest().log(Status.FAIL, "Test failed - screenshot captured");
            } else {
                ReportManager.getTest().log(Status.PASS, "Test completed successfully");
            }
        } catch (Exception e) {
            System.err.println("Error in tearDown: " + e.getMessage());
        }
        
        System.out.println("✓ Test completed: " + testInfo.getDisplayName() + "\n");
    }
    
    @AfterAll
    public static void globalTearDown() {
        System.out.println("========================================");
        System.out.println("  ALL TESTS COMPLETED - CLOSING BROWSER");
        System.out.println("========================================");
        
        // Quit Driver - HANYA SEKALI
        DriverManager.quitDriver();
        
        // Flush Report
        ReportManager.flushReport();
        
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║   OpenProject Automation Test Suite - Completed   ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
    
    /**
     * Helper method untuk log info ke report
     */
    protected void logInfo(String message) {
        System.out.println("ℹ " + message);
        ReportManager.getTest().log(Status.INFO, message);
    }
    
    /**
     * Helper method untuk log pass ke report
     */
    protected void logPass(String message) {
        System.out.println("✓ " + message);
        ReportManager.getTest().log(Status.PASS, message);
    }
    
    /**
     * Helper method untuk log warning ke report
     */
    protected void logWarning(String message) {
        System.out.println("⚠ " + message);
        ReportManager.getTest().log(Status.WARNING, message);
    }
}
