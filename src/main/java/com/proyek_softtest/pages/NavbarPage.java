package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    // Logo elements
    private By openProjectLogo = By.cssSelector("a.op-logo--link[href='https://safe.openproject.com/']");

    // searchbar elements
    private By searchBar = By.cssSelector("input.global-search--input[name='global-search--input']");
    private By searchDropdownPanel = By.cssSelector("ng-dropdown-panel.ng-dropdown-panel.ng-select-bottom");
    private By searchInAllProjects = By.cssSelector("div.ng-option[aria-posinset='1']");
    private By workPackageResult; // Defined dynamically in methods

    // help menu elements
    private By helpButton = By.cssSelector("button#op-app-header--help-menu-button");
    private By helpDropdownPanel = By.cssSelector("ul#op-app-header--help-menu-list");

    private By gettingStartedLink = By.cssSelector("a[href='/onboarding_video_dialog']"); // membuka popup (tidak melempar ke halaman lain)
    private By closeGettingStartedPopUpButton1 = By.cssSelector("button[data-close-dialog-id='onboarding-video-dialog'][aria-label='Close']");
    private By closeGettingStartedPopUpButton2 = By.xpath("//button[@data-close-dialog-id='onboarding-video-dialog']//span[@class='Button-label' and text()='Close']");
    private By gettingStartedPopUpVideo = By.cssSelector("div.onboarding--video video");

    private By upgradeToEnterpriseLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Upgrade to Enterprise edition']/parent::a");
    private By userGuidesLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='User guides']/parent::a");
    private By videosLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Videos']/parent::a");
    private By shortcutsLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Shortcuts']/parent::a");
    private By communityForumLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Community forum']/parent::a");
    private By enterpriseSupportLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Enterprise support']/parent::a");
    private By dataPrivacyAndSecurityLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Data privacy and security policy']/parent::a");
    private By digitalAccessibilityLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Digital accessibility (DE)']/parent::a");
    private By openProjectWebsiteLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='OpenProject website']/parent::a");
    private By securityLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Security alerts']/parent::a");
    private By newsletterLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Newsletter']/parent::a");
    private By openProjectBlogLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='OpenProject blog']/parent::a");
    private By releaseNotesLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Release notes']/parent::a");
    private By reportaBugLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Report a bug']/parent::a");
    private By developmentLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Development roadmap']/parent::a");
    private By addAndEditLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='Add and edit translations']/parent::a");
    private By apiDocumentationLink = By.xpath("//span[@class='ActionListItem-label' and normalize-space(text())='API documentation']/parent::a");

    // sign in elements
    private By signInButton = By.xpath("//span[@class='Button-label' and text()='Sign in']");
    private By signInSidePanel = By.cssSelector("dialog.Overlay.Overlay--placement-right[aria-modal='true'][open]");
    private By closeSignInSidePanelButton = By.cssSelector("button.close-button.Overlay-closeButton[aria-label='Close']"); 
    private By usernameInputField = By.id("username-pulldown");
    private By passwordInputField = By.id("password-pulldown");
    private By signInSubmitButton = By.id("login-pulldown");
    private By signInWithGoogleButton = By.cssSelector("a.auth-provider.auth-provider-google[href='/auth/google']");
    
    public NavbarPage(WebDriver driver) {
        super(driver);
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

    // ╔════════════════════════════════════════════════════════╗
    // ║               SEARCHBAR ACTIONS                        ║
    // ╚════════════════════════════════════════════════════════╝
    
    public NavbarPage clickOpenProjectLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(openProjectLogo)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/"));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickSearchBar() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBar)).click();
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

    public NavbarPage clickInAllProjects() {
        wait.until(ExpectedConditions.elementToBeClickable(searchInAllProjects)).click();
        wait.until(ExpectedConditions.urlContains("/search?"));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickSearchResultByWorkPackageId(String id) {
        workPackageResult = By.xpath("//a[@class='global-search--option' and @href='/work_packages/" + id + "']");
        wait.until(ExpectedConditions.elementToBeClickable(workPackageResult)).click();
        wait.until(ExpectedConditions.urlContains("/work_packages/" + id));
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickSearchResultBySubject(String subject) {
        workPackageResult = By.xpath("//span[@class='global-search--wp-subject' and contains(text(), '" + subject + "')]/ancestor::a[@class='global-search--option']");
        wait.until(ExpectedConditions.elementToBeClickable(workPackageResult)).click();
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickSearchResultByProject(String project) {
        workPackageResult = By.xpath("//span[@class='global-search--wp-project' and contains(@title, '" + project + "')]/ancestor::a[@class='global-search--option']");
        wait.until(ExpectedConditions.elementToBeClickable(workPackageResult)).click();
        Delay.waitDefault();
        return this;
    }


    // ╔═════════════════════════════╗
    // ║        HELP FUNCTIONS       ║
    // ╚═════════════════════════════╝

    public boolean isHelpMenuDisplayed() {
        try {
            return driver.findElement(helpDropdownPanel).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    } 

    public NavbarPage clickHelpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(helpButton)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isGettingStartedPopUpVideoDisplayed() {
        try {
            return driver.findElement(gettingStartedPopUpVideo).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public NavbarPage clickGettingStartedLink() {
        wait.until(ExpectedConditions.elementToBeClickable(gettingStartedLink)).click();
        Delay.waitForSeconds(3);
        return this;
    }

    public NavbarPage closeGettingStartedPopUpWithButton1() {
        wait.until(ExpectedConditions.elementToBeClickable(closeGettingStartedPopUpButton1)).click();
        Delay.waitDefault();
        return this;
    }
    
    public NavbarPage closeGettingStartedPopUpWithButton2() {
        wait.until(ExpectedConditions.elementToBeClickable(closeGettingStartedPopUpButton2)).click();
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickUpgradeToEnterpriseLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(upgradeToEnterpriseLink));
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(upgradeToEnterpriseLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickUserGuidesLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(userGuidesLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(userGuidesLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickVideosLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(videosLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(videosLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickShortcutsLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(shortcutsLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(shortcutsLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickCommunityForumLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(communityForumLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(communityForumLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickEnterpriseSupportLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(enterpriseSupportLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(enterpriseSupportLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickDataPrivacyAndSecurityLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(dataPrivacyAndSecurityLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dataPrivacyAndSecurityLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickDigitalAccessibilityLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(digitalAccessibilityLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(digitalAccessibilityLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }   

    public NavbarPage clickOpenProjectWebsiteLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(openProjectWebsiteLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(openProjectWebsiteLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickSecurityLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(securityLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(securityLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickNewsletterLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(newsletterLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(newsletterLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickOpenProjectBlogLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(openProjectBlogLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(openProjectBlogLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickReleaseNotesLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(releaseNotesLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(releaseNotesLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickReportaBugLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(reportaBugLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(reportaBugLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickDevelopmentLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(developmentLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(developmentLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickAddAndEditLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(addAndEditLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addAndEditLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }

    public NavbarPage clickApiDocumentationLink() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(apiDocumentationLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        Delay.waitDefault();
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(apiDocumentationLink)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        
        Delay.waitDefault();
        return this;
    }
}
