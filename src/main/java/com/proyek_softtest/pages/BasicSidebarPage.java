package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class BasicSidebarPage extends BasePage {
    // Sidebar Elements
    private By collapseSidebarButton = By.id("menu-toggle--collapse-button");
    private By uncollapseSidebarButton = By.id("menu-toggle--expand-button"); 

    // Sidebar - focused on Projects Selection Elements
    private By projectsSelectionButton = By.id("projects-menu");
    private By projectsSelectionModalOpened = By.cssSelector(".spot-drop-modal_opened");
    private By projectsSelectionModalClosed = By.cssSelector(".op-project-list-modal.spot-drop-modal:not(.spot-drop-modal_opened)");
    private By searchBarInProjectsSelection = By.cssSelector("input.spot-text-field--input[placeholder='Search projects...']");
    private By noResultsMessage = By.cssSelector(".op-project-list-modal--no-results");
    private By otherProjectsSelectionButton = By.xpath("//a[@data-project-id='6' and contains(@href, '/projects/other-projects')]");
    private By demoProjectButton = By.xpath("//a[@data-project-id='1' and contains(@href, '/projects/demo-project')]");
    private By scrumProjectButton = By.xpath("//a[@data-project-id='2' and contains(@href, '/projects/your-scrum-project')]");
    private By safeSolutionTrain1Button = By.xpath("//a[@data-project-id='5' and contains(@href, '/projects/safe-solution-train-1')]");
    private By art1EngineeringButton = By.xpath("//a[@data-project-id='3' and contains(@href, '/projects/art-0-test-release-train')]");
    private By blueTeamButton = By.xpath("//a[@data-project-id='8' and contains(@href, '/projects/blue-team')]");
    private By redTeamButton = By.xpath("//a[@data-project-id='7' and contains(@href, '/projects/red-team')]");
    private By art2DesignButton = By.xpath("//a[@data-project-id='4' and contains(@href, '/projects/art-2-design')]");
    private By projectListsButton = By.cssSelector("a.button.spot-action-bar--action[href='/projects']");

    // Sidebar Navigation Elements
    private By homeButton = By.cssSelector("a.home-menu-item.op-menu--item-action[href='https://safe.openproject.com/']");
    private By myPageButton = By.cssSelector("a.my-page-menu-item.op-menu--item-action[href='https://safe.openproject.com/my/page']");
    private By projectsButton = By.cssSelector("a.projects-menu-item.op-menu--item-action[href='https://safe.openproject.com/projects']");
    private By workPackagesButton = By.cssSelector("a.work-packages-menu-item.op-menu--item-action[href='https://safe.openproject.com/work_packages']");
    private By ganttChartsButton = By.cssSelector("a.gantt-menu-item.op-menu--item-action[href='https://safe.openproject.com/gantt']");
    private By teamPlannersButton = By.cssSelector("a.team-planners-menu-item.op-menu--item-action[href='https://safe.openproject.com/team_planners']");
    private By boardsButton = By.cssSelector("a.boards-menu-item.op-menu--item-action[href='https://safe.openproject.com/boards']");
    private By meetingsButton = By.cssSelector("a.meetings-menu-item.op-menu--item-action[href='https://safe.openproject.com/meetings']");
    private By newsButton = By.cssSelector("a.news-menu-item.op-menu--item-action[href='https://safe.openproject.com/news']");
    private By timeAndCostsButton = By.cssSelector("a.cost-reports-global-menu-item.op-menu--item-action[href='https://safe.openproject.com/cost_reports']");

    public BasicSidebarPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════╗
    // ║    FUNCTION SIDEBAR    ║
    // ╚════════════════════════╝
    public boolean isSidebarCollapsed(){
        try {
            return driver.findElement(uncollapseSidebarButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public BasicSidebarPage collapseSidebar(){
        wait.until(ExpectedConditions.elementToBeClickable(collapseSidebarButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(uncollapseSidebarButton));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage uncollapseSidebar(){
        wait.until(ExpectedConditions.elementToBeClickable(uncollapseSidebarButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(collapseSidebarButton));
        Delay.waitDefault();
        return this;
    }

    // ╔══════════════════════════════════════════════╗
    // ║    FUNCTION SIDEBAR - FOCUSED ON PROJECTS    ║
    // ╚══════════════════════════════════════════════╝
    public boolean isProjectsSelectionOpen() {
        try {
            return driver.findElement(projectsSelectionModalOpened).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProjectsSelectionClosed() {
        try {
            return driver.findElement(projectsSelectionModalClosed).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public BasicSidebarPage openProjectsSelection(){
        driver.findElement(projectsSelectionButton).click();
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage closeProjectsSelection() {
        driver.findElement(projectsSelectionButton).click();
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage searchInProjectsSelection(String searchText){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBarInProjectsSelection));
        driver.findElement(searchBarInProjectsSelection).clear();
        driver.findElement(searchBarInProjectsSelection).sendKeys(searchText);
        Delay.waitDefault();
        return this;
    }

    public boolean isNoResultsMessageDisplayed(){
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchResultContains(String expectedText) {
        try {
            Delay.waitDefault();
            return driver.getPageSource().contains(expectedText);
        } catch (Exception e) {
            return false;
        }
    }
    
    public BasicSidebarPage clickOtherProjectsSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(otherProjectsSelectionButton)).click();
        wait.until(ExpectedConditions.urlContains("/projects/other-projects"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickDemoProjectSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(demoProjectButton)).click();
        wait.until(ExpectedConditions.urlContains("/projects/demo-project"));
        Delay.waitDefault();
        return this;
    }
    
    public BasicSidebarPage clickScrumProjectSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(scrumProjectButton)).click();
        wait.until(ExpectedConditions.urlContains("/projects/your-scrum-project"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickSafeSolutionTrain1Selection() {
        wait.until(ExpectedConditions.elementToBeClickable(safeSolutionTrain1Button)).click();
        wait.until(ExpectedConditions.urlContains("/projects/safe-solution-train-1"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickArt1EngineeringSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(art1EngineeringButton)).click();
        wait.until(ExpectedConditions.urlContains("/projects/art-0-test-release-train"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickBlueTeamSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(blueTeamButton)).click();
        wait.until(ExpectedConditions.urlContains("/projects/blue-team"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickRedTeamSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(redTeamButton)).click();
        wait.until(ExpectedConditions.urlContains("/projects/red-team"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickArt2DesignSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(art2DesignButton)).click();
        wait.until(ExpectedConditions.urlContains("/projects/art-2-design"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickProjectListsSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(projectListsButton)).click();
        wait.until(ExpectedConditions.urlContains("/projects"));
        Delay.waitDefault();
        return this;
    }

    // Sidebar Navigation Methods
    public BasicSidebarPage clickHomeInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(homeButton)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickMyPageInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(myPageButton)).click();
        wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("/my/page"),
            ExpectedConditions.urlContains("/login")
        ));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickProjectsInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsButton)).click();
        wait.until(ExpectedConditions.urlContains("/projects"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickWorkPackagesInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(workPackagesButton)).click();
        wait.until(ExpectedConditions.urlContains("/work_packages"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickGanttChartsInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(ganttChartsButton)).click();
        wait.until(ExpectedConditions.urlContains("/gantt"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickTeamPlannersInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(teamPlannersButton)).click();
        wait.until(ExpectedConditions.urlContains("/team_planners"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickBoardsInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(boardsButton)).click();
        wait.until(ExpectedConditions.urlContains("/boards"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickMeetingsInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(meetingsButton)).click();
        wait.until(ExpectedConditions.urlContains("/meetings"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickNewsInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(newsButton)).click();
        wait.until(ExpectedConditions.urlContains("/news"));
        Delay.waitDefault();
        return this;
    }

    public BasicSidebarPage clickTimeAndCostsInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(timeAndCostsButton)).click();
        wait.until(ExpectedConditions.urlContains("/cost_reports"));
        Delay.waitDefault();
        return this;
    }
}
