package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class TeamPlannersPage extends BasePage {
    
    private By homeBreadCrumbLink = By.linkText("safe.openproject.com"); 
    private By show50PerPageButton = By.cssSelector("a[aria-label='Show 50 per page']");
    private By show100PerPageButton = By.cssSelector("a[aria-label='Show 100 per page']");
    private By show200PerPageButton = By.cssSelector("a[aria-label='Show 200 per page']");
    
    // ada 2 mode untuk column yaitu ascending dan descending dengan locator yang sama
    // Menggunakan CSS selector dengan href contains untuk lebih reliable
    private By columnName = By.cssSelector("a[href*='sort=name']");
    private By columnProject = By.cssSelector("a[href*='sort=project_name']");
    private By columnCreatedOn = By.cssSelector("a[href*='sort=created_at']");

    // row pertama pada tabel team planners
    private By nameRow = By.xpath("(//td[@class='name']/a)[1]");
    private By projectRow = By.xpath("(//td[@class='project_name']/a)[1]");
    
    public TeamPlannersPage(WebDriver driver) {
        super(driver);
    }
    
    // ╔════════════════════════════════════════════════════════╗
    // ║               TEAM PLANNERS ACTIONS                    ║
    // ╚════════════════════════════════════════════════════════╝
    
    public TeamPlannersPage clickHomeBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(homeBreadCrumbLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/"));
        Delay.waitDefault();
        return this;
    }
    
    public TeamPlannersPage clickShow50PerPage() {
        wait.until(ExpectedConditions.elementToBeClickable(show50PerPageButton)).click();
        wait.until(ExpectedConditions.urlContains("per_page=50"));
        Delay.waitDefault();
        return this;
    }
    
    public TeamPlannersPage clickShow100PerPage() {
        wait.until(ExpectedConditions.elementToBeClickable(show100PerPageButton)).click();
        wait.until(ExpectedConditions.urlContains("per_page=100"));
        Delay.waitDefault();
        return this;
    }
    
    public TeamPlannersPage clickShow200PerPage() {
        wait.until(ExpectedConditions.elementToBeClickable(show200PerPageButton)).click();
        wait.until(ExpectedConditions.urlContains("per_page=200"));
        Delay.waitDefault();
        return this;
    }
    
    public TeamPlannersPage clickColumnName() {
        wait.until(ExpectedConditions.elementToBeClickable(columnName)).click();
        Delay.waitDefault();
        return this;
    }
    
    public TeamPlannersPage clickColumnProject() {
        wait.until(ExpectedConditions.elementToBeClickable(columnProject)).click();
        Delay.waitDefault();
        return this;
    }
    
    public TeamPlannersPage clickColumnCreatedOn() {
        wait.until(ExpectedConditions.elementToBeClickable(columnCreatedOn)).click();
        Delay.waitDefault();
        return this;
    }
    
    public TeamPlannersPage clickNameRow() {
        wait.until(ExpectedConditions.elementToBeClickable(nameRow)).click();
        Delay.waitDefault();
        return this;
    }
    
    public TeamPlannersPage clickProjectRow() {
        wait.until(ExpectedConditions.elementToBeClickable(projectRow)).click();
        Delay.waitDefault();
        return this;
    }
}
