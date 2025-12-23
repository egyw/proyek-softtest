package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class LostPasswordPage extends BasePage {
    
    private By homeBreadCrumbLink = By.linkText("safe.openproject.com"); 
    private By emailInput = By.id("mail");
    private By submitButton = By.xpath("//button[normalize-space()='Submit']");
    private By successBanner = By.cssSelector("div.Banner.flash-success");

    public LostPasswordPage(WebDriver driver) {
        super(driver);
    }
    
    // ╔════════════════════════════════════════════════════════╗
    // ║               LOST PASSWORD ACTIONS                    ║
    // ╚════════════════════════════════════════════════════════╝
    
    public LostPasswordPage clickHomeBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(homeBreadCrumbLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/"));
        Delay.waitDefault();
        return this;
    }
    
    public LostPasswordPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).clear();
        driver.findElement(emailInput).sendKeys(email);
        Delay.waitDefault();
        return this;
    }
    
    public LostPasswordPage clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isSuccessBannerDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successBanner)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
