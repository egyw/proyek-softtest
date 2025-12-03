package com.proyek_softtest.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.proyek_softtest.utils.Delay;

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
        Delay.waitDefault();
    }
    
    public void navigateBackAndWaitForUrl(String expectedUrlPart) {
        driver.navigate().back();
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        Delay.waitDefault();
    }
    
    public void refreshPage() {
        driver.navigate().refresh();
        waitForPageLoad();
        Delay.waitDefault();
    }
    
    public void waitForPageLoad() {
        wait.until(webDriver -> 
            ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")
                .equals("complete")
        );
    }
}
