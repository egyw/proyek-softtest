package com.proyek_softtest.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.proyek_softtest.config.TestConfig;

import java.time.Duration;

/**
 * Driver Manager untuk manage WebDriver instances
 * Singleton pattern untuk ensure satu instance per thread
 */
public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    /**
     * Initialize WebDriver berdasarkan konfigurasi
     */
    public static void initializeDriver() {
        if (driver.get() != null) {
            return; // Driver sudah diinisialisasi
        }
        
        String browser = TestConfig.getBrowser().toLowerCase();
        WebDriver webDriver;
        
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                
                if (TestConfig.isHeadless()) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-extensions");
                
                if (TestConfig.isMaximize()) {
                    chromeOptions.addArguments("--start-maximized");
                }
                
                webDriver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                
                if (TestConfig.isHeadless()) {
                    firefoxOptions.addArguments("--headless");
                }
                
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
                
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                
                if (TestConfig.isHeadless()) {
                    edgeOptions.addArguments("--headless");
                }
                
                webDriver = new EdgeDriver(edgeOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Browser tidak didukung: " + browser);
        }
        
        // Set timeouts
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestConfig.getImplicitWait()));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestConfig.getPageLoadTimeout()));
        
        // Maximize window jika tidak headless dan belum di-set di options
        if (!TestConfig.isHeadless() && TestConfig.isMaximize() && !browser.equals("chrome")) {
            webDriver.manage().window().maximize();
        }
        
        driver.set(webDriver);
    }
    
    /**
     * Get WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }
    
    /**
     * Quit WebDriver dan remove dari ThreadLocal
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
