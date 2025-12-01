package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;

public class HomePage extends BasePage {
    
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
    
    // Sidebar Elements
    private By collapseSidebarButton = By.cssSelector(".Button.Button--iconOnly.Button--invisible.Button--medium");
    private By uncollapseSidebarButton = By.cssSelector(".Button.Button--iconOnly.Button--invisible.Button--small");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isSideMenuOverlayOpen(){
        try {
            return driver.findElement(closeSideMenuOverlayButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public HomePage openSideMenuOverlay() {
        wait.until(ExpectedConditions.elementToBeClickable(openSideMenuOverlayButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeSideMenuOverlayButton));
        return this;
    }

    public HomePage closeSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(closeSideMenuOverlayButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        return this;
    }

    public HomePage clickHomeInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(homeButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        return this;
    }

    public HomePage clickProjectsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(projectsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/projects"));
        return this;
    }

    public HomePage clickWorkPackagesInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(workPackagesButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/work_packages"));
        return this;
    }

    public HomePage clickGanttChartsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(ganttChartsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/gantt"));
        return this;
    }

    public HomePage clickTeamPlannersInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(teamPlannersButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/team_planners"));
        return this;
    }

    public HomePage clickBoardsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(boardsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/boards"));
        return this;
    }

    public HomePage clickMeetingsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(meetingsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/meetings"));
        return this;
    }

    public HomePage clickNewsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(newsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/news"));
        return this;
    }

    public HomePage clickTimeAndCostsInSideMenuOverlay(){
        wait.until(ExpectedConditions.elementToBeClickable(timeAndCostsButtonInSideMenuOverlay)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeSideMenuOverlayButton));
        wait.until(ExpectedConditions.urlContains("/cost_reports"));
        return this;
    }

    // ╔════════════════════════╗
    // ║    FUNCTION SIDEBAR    ║
    // ╚════════════════════════╝
    public HomePage collapseSidebar(){
        wait.until(ExpectedConditions.elementToBeClickable(collapseSidebarButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(uncollapseSidebarButton));
        return this;
    }

    public HomePage uncollapseSidebar(){
        wait.until(ExpectedConditions.elementToBeClickable(uncollapseSidebarButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(collapseSidebarButton));
        return this;
    }
}
