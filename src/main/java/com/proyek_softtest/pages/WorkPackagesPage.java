package com.proyek_softtest.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class WorkPackagesPage extends BasePage {

    private By workPackagesSidebar = By.cssSelector("a[title='Work packages']");
    private By AllProjectsTab = By.id("projects-menu");
    private By projectSearchInput = By.cssSelector("input[placeholder='Search projects...']");

    private By projectListItems = By.cssSelector(".spot-list--item-title span");

    public WorkPackagesPage(WebDriver driver) {
        super(driver);
    }

    public WorkPackagesPage clickWorkPackagesSideBar() {
        wait.until(ExpectedConditions.elementToBeClickable(workPackagesSidebar)).click();
        wait.until(ExpectedConditions.urlContains("work"));
        Delay.waitFor(500);
        return this;
    }

    public WorkPackagesPage clickAllProjectsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(AllProjectsTab)).click();
        return this;
    }

    public boolean isAllProjectsTabOpen() {
        try {
            WebElement tabButton = driver.findElement(AllProjectsTab);
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(projectSearchInput));
            return searchInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean searchAndVerifyProject(String projectName) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(projectSearchInput));
        searchField.click();
        searchField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        searchField.sendKeys(Keys.DELETE);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }

        searchField.sendKeys(projectName);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        List<WebElement> results = driver.findElements(projectListItems);

        for (WebElement result : results) {
            if (result.getText().trim().equalsIgnoreCase(projectName)) {
                return true;
            }
        }
        return false;
    }

    public void typeSearchProject(String keyword) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(projectSearchInput));

        searchField.click();

        searchField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        searchField.sendKeys(Keys.DELETE);
        Delay.waitFor(200);

        searchField.sendKeys(keyword);

        Delay.waitFor(1000);
    }

    public boolean isProjectListEmpty() {
        List<WebElement> results = driver.findElements(projectListItems);
        return results.isEmpty();
    }

    public void clickProjectResult(String projectName) {
        List<WebElement> results = driver.findElements(projectListItems);

        boolean isClicked = false;
        for (WebElement result : results) {

            if (result.getText().trim().equalsIgnoreCase(projectName)) {

                wait.until(ExpectedConditions.elementToBeClickable(result));

                result.click();
                isClicked = true;
                break;
            }
        }

        if (!isClicked) {
            throw new RuntimeException("Project '" + projectName + "' tidak ditemukan atau tidak bisa diklik!");
        }

        Delay.waitFor(1000);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

}
