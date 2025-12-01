package com.proyek_softtest.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void navigateBack() {
        driver.navigate().back();
        waitForPageLoad();
    }
    
    public void navigateBackAndWaitForUrl(String expectedUrlPart) {
        driver.navigate().back();
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
    }
    
    public void refreshPage() {
        driver.navigate().refresh();
        waitForPageLoad();
    }
    
    public void waitForPageLoad() {
        wait.until(webDriver -> 
            ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")
                .equals("complete")
        );
    }
}
