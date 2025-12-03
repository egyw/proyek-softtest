package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class NavbarPage extends BasePage {
    
    // Side Menu Overlay Elements
    private By openSideMenuOverlayButton = By.cssSelector(".Button.Button--iconOnly.Button--invisible.Button--medium.op-app-header--primer-button");
    private By closeSideMenuOverlayButton = By.cssSelector(".close-button.Overlay-closeButton");
    private By homeButtonInSideMenuOverlay = By.xpath("//a[contains(@class,'ActionListContent--visual16') and @href='/']");
    private By projectsButtonInSideMenuOverlay = By.xpath("//a[contains(@class,'ActionListContent--visual16') and @href='/projects']");
    private By workPackagesButtonInSideMenuOverlay = By.xpath("//a[contains(@class,'ActionListContent--visual16') and @href='/work_packages']");
    private By ganttChartsButtonInSideMenuOverlay = By.xpath("//a[contains(@class,'ActionListContent--visual16') and @href='/gantt']");
    private By teamPlannersButtonInSideMenuOverlay = By.xpath("//a[contains(@class,'ActionListContent--visual16') and @href='/team_planners']");
    private By boardsButtonInSideMenuOverlay = By.xpath("//a[contains(@class,'ActionListContent--visual16') and @href='/boards']");
    private By meetingsButtonInSideMenuOverlay = By.xpath("//a[contains(@class,'ActionListContent--visual16') and @href='/meetings']");
    private By newsButtonInSideMenuOverlay = By.xpath("//a[contains(@class,'ActionListContent--visual16') and @href='/news']");
    private By timeAndCostsButtonInSideMenuOverlay = By.xpath("//a[contains(@class,'ActionListContent--visual16') and @href='/cost_reports']");

    // Navbar Elements
    private By openProjectLogo = By.cssSelector("a.op-logo--link[href='https://safe.openproject.com/']");
    private By searchBar = By.cssSelector("input.global-search--input[name='global-search--input']");
    private By searchDropdownPanel = By.cssSelector("ng-dropdown-panel.ng-dropdown-panel.ng-select-bottom");
    private By noResultsMessage = By.xpath("//div[@class='ng-option disabled' and contains(text(), 'No items found')]");
    private By viewAllResultsButton = By.cssSelector("button.global-search--view-all-button");
    private By helpButton = By.cssSelector("button#op-app-header--help-menu-button");
    private By signInButton = By.xpath("//span[@class='Button-label' and text()='Sign in']");

    public NavbarPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               NAVBAR ACTIONS                           ║
    // ╚════════════════════════════════════════════════════════╝
    
    public NavbarPage clickOpenProjectLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(openProjectLogo)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/"));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage typeInSearchBar(String searchText) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBar)).click();
        driver.findElement(searchBar).clear();
        driver.findElement(searchBar).sendKeys(searchText);
        Delay.waitDefault();
        return this;
    }

    public boolean isSearchDropdownDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(searchDropdownPanel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoResultsMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public NavbarPage clickViewAllResultsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewAllResultsButton)).click();
        wait.until(ExpectedConditions.urlContains("/search?"));
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║          SIDE MENU OVERLAY ACTIONS                     ║
    // ╚════════════════════════════════════════════════════════╝
    
    public boolean isSideMenuOverlayOpen(){
        try {
            return driver.findElement(closeSideMenuOverlayButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public NavbarPage openSideMenuOverlay() {
        wait.until(ExpectedConditions.elementToBeClickable(openSideMenuOverlayButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeSideMenuOverlayButton));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage closeSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(closeSideMenuOverlayButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickHomeInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(homeButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickProjectsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(projectsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/projects"));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickWorkPackagesInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(workPackagesButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/work_packages"));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickGanttChartsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(ganttChartsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/gantt"));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickTeamPlannersInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(teamPlannersButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/team_planners"));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickBoardsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(boardsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/boards"));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickMeetingsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(meetingsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/meetings"));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickNewsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(newsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/news"));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickTimeAndCostsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(timeAndCostsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/cost_reports"));
        Delay.waitDefault();
        return this;
    }

    
}
