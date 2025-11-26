package com.proyek_softtest.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import com.proyek_softtest.config.TestConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility untuk capture screenshots
 */
public class ScreenshotUtil {
    
    /**
     * Capture screenshot dan simpan ke file
     * @param driver WebDriver instance
     * @param testName nama test untuk filename
     * @return path ke screenshot file
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        if (!TestConfig.isScreenshotOnFailure()) {
            return null;
        }
        
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        
        // Create screenshot folder if not exists
        File screenshotFolder = new File(TestConfig.getScreenshotFolder());
        if (!screenshotFolder.exists()) {
            screenshotFolder.mkdirs();
        }
        
        String screenshotPath = TestConfig.getScreenshotFolder() + File.separator + screenshotName;
        
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
            
            System.out.println("📸 Screenshot captured: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Capture screenshot dengan nama custom
     */
    public static String captureScreenshot(WebDriver driver, String testName, String customName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testName + "_" + customName + "_" + timestamp + ".png";
        
        File screenshotFolder = new File(TestConfig.getScreenshotFolder());
        if (!screenshotFolder.exists()) {
            screenshotFolder.mkdirs();
        }
        
        String screenshotPath = TestConfig.getScreenshotFolder() + File.separator + screenshotName;
        
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
            
            return screenshotPath;
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Capture screenshot as byte array for Allure
     * @param driver WebDriver instance
     * @return byte array of screenshot
     */
    public static byte[] captureScreenshotAsBytes(WebDriver driver) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            return ts.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot as bytes: " + e.getMessage());
            return null;
        }
    }
}
