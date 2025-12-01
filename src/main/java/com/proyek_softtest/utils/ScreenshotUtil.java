package com.proyek_softtest.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Screenshot utility for Allure reporting integration.
 */
public class ScreenshotUtil {
    
    private ScreenshotUtil() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    public static byte[] captureScreenshotAsBytes(WebDriver driver) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            return screenshot.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }
}
