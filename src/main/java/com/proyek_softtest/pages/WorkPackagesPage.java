package com.proyek_softtest.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    private By projectListsButton = By.cssSelector("a.spot-action-bar--action[href='/projects']");
    private By mainMenuBackButton = By.cssSelector("a[aria-label='Go back one menu level']");
    private By workPackagesToggler = By.cssSelector("[data-test-selector='main-menu-toggler--work_packages']");
    private By collapseSidebarButton = By.id("menu-toggle--collapse-button");
    private By expandSidebarButton = By.id("menu-toggle--expand-button");
    private By workPackagesButton = By
            .xpath("//a[contains(@class, 'main-menu--parent-node') and contains(text(), 'Work packages')]");
    private By subMenuSearchInput = By.cssSelector("input[data-test-selector='op-submenu--search-input']");
    private By subMenuResultItems = By.cssSelector(".op-submenu--item-title");

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

    public void navigateBackToWorkPackages() {
        String targetUrl = "https://safe.openproject.com/work_packages";

        driver.get(targetUrl);
        Delay.waitFor(1000);
    }

    public void clickProjectLists() {
        wait.until(ExpectedConditions.elementToBeClickable(projectListsButton)).click();
    }

    public void clickMainMenuBackButton() {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(mainMenuBackButton));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void clickWorkPackagesToggler() {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(workPackagesToggler));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    public void collapseSidebar() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(collapseSidebarButton));
            wait.until(ExpectedConditions.elementToBeClickable(collapseSidebarButton)).click();
        } catch (Exception e) {
            System.out.println("Sidebar mungkin sudah tertutup (Tombol collapse tidak ketemu).");
        }
    }

    public void expandSidebar() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(expandSidebarButton));
            wait.until(ExpectedConditions.elementToBeClickable(expandSidebarButton)).click();
        } catch (Exception e) {
            System.out.println("Sidebar mungkin sudah terbuka (Tombol expand tidak ketemu).");
        }
    }

    public void clickWorkPackagesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(workPackagesButton)).click();
    }

    public boolean searchInSubMenu(String keyword) {

        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(subMenuSearchInput));

        searchField.click();
        searchField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        searchField.sendKeys(Keys.DELETE);

        searchField.sendKeys(keyword);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        List<WebElement> results = driver.findElements(subMenuResultItems);

        for (WebElement result : results) {

            if (result.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return true; // Ditemukan
            }
        }

        List<WebElement> headers = driver.findElements(By.cssSelector(".op-submenu--group-title"));
        for (WebElement header : headers) {
            if (header.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

}
