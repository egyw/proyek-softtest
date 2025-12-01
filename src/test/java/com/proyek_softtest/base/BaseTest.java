package com.proyek_softtest.base;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;
import com.proyek_softtest.config.TestConfig;
import com.proyek_softtest.utils.DriverManager;
import com.proyek_softtest.utils.ScreenshotUtil;

import java.io.ByteArrayInputStream;

/**
 * Base class for all test classes.
 * Handles WebDriver lifecycle and Allure reporting integration.
 */
public class BaseTest {
    protected static WebDriver driver;
    
    @BeforeAll
    public static void globalSetup() {
        DriverManager.initializeDriver();
        driver = DriverManager.getDriver();
        
        String baseUrl = TestConfig.getBaseUrl();
        driver.get(baseUrl);
    }
    
    @BeforeEach
    public void setupTestContext() {
        Allure.parameter("Application", "OpenProject");
        Allure.parameter("Browser", TestConfig.getBrowser());
        Allure.parameter("Base URL", TestConfig.getBaseUrl());
    }
    
    @AfterEach
    public void captureScreenshot() {
        byte[] screenshot = ScreenshotUtil.captureScreenshotAsBytes(driver);
        if (screenshot.length > 0) {
            Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        }
    }
    
    @AfterAll
    public static void globalTearDown() {
        DriverManager.quitDriver();
    }
}
