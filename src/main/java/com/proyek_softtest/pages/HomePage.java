package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.utils.Delay;

public class HomePage extends BasicSidebarPage {

    private By safeOpenProjectLink = By.cssSelector("a.Link[href='/'][target='_top']");

    // Welcome to OpenProject! panel elements - dynamic locators
    private By welcomePanelLinks = By.cssSelector("a.op-uc-link[target='_blank']");
    
    // Welcome to OpenProject! panel elements - static locators (commented for reference)
    // private By projectPortofolioManagementLink = By.cssSelector("a.op-uc-link[href='https://www.openproject.org/collaboration-software-features/project-portfolio-management/'][target='_blank']");
    // private By projectPlanningAndSchedulingLink = By.cssSelector("a.op-uc-link[href='https://www.openproject.org/collaboration-software-features/project-planning-scheduling/'][target='_blank']");
    // private By taskManagementAndIssueTrackingLink = By.cssSelector("a.op-uc-link[href='https://www.openproject.org/collaboration-software-features/task-management/'][target='_blank']");
    // private By agileboardsLink = By.cssSelector("a.op-uc-link[href='https://www.openproject.org/collaboration-software-features/agile-project-management/'][target='_blank']");
    // private By requirementsManagementAndReleasePlanningLink = By.cssSelector("a.op-uc-link[href='https://www.openproject.org/collaboration-software-features/product-development/'][target='_blank']");
    // private By timeAndCostTrackingLink = By.cssSelector("a.op-uc-link[href='https://www.openproject.org/collaboration-software-features/time-tracking/'][target='_blank']");
    // private By teamCollaborationAndDocumentLink = By.cssSelector("a.op-uc-link[href='https://www.openproject.org/collaboration-software-features/team-collaboration/'][target='_blank']");
    
    private By changeWelcomeTextForAdminLink = By.cssSelector("a.op-uc-link[href='https://safe.openproject.com/admin/settings/general'][target='_top']");
    
    // projects panel elements
    private By blueTeamLink = By.xpath("//a[contains(@href, '/projects/blue-team') and normalize-space(text())='Blue team']");
    private By redTeamLink = By.xpath("//a[contains(@href, '/projects/red-team') and normalize-space(text())='Red team']");
    private By otherProjectsLink = By.xpath("//a[contains(@href, '/projects/other-projects')]");
    private By viewAllProjectsButton = By.cssSelector("a.button.-highlight-inverted[href='/projects'][title='View all projects']"); 

    // new features panel elements
    private By newFeaturesPanelLink = By.xpath("//a[@class='openproject--static-link' and contains(@href, 'release-notes') and normalize-space(text())='Learn more about all new features']");

    // news panel elements
    private By newsTitle1_DemoProjectTestSyntaxLink = By.cssSelector("a.Link.text-bold[href='/news/4-test-syntax-highlighting']");
    private By newsTitle2_DemoProjectActualitesLink = By.cssSelector("a.Link.text-bold[href='/news/3-actualites-acteurspublics-fr-reforme-de-la-haute-fonction-publique-le-cas-de-l-igf-interroge-toujours']");
    private By newsTitle3_ScrumProjectWelcomeLink = By.cssSelector("a.Link.text-bold[href='/news/2-welcome-to-your-scrum-demo-project']");
    private By demoAdminProfileLink = By.cssSelector("a[href='/users/10'][title='User Demo admin']");

    // openproject community elements
    private By userGuidesLink = By.cssSelector("a.openproject--static-link[href*='user-guide'][target='_blank']");
    private By shortcutsLink = By.cssSelector("a.openproject--static-link[href*='keyboard-shortcuts-access-keys'][target='_blank']");
    private By communityForumLink = By.cssSelector("a.openproject--static-link[href*='community.openproject.org/projects/openproject/forums'][target='_blank']");
    private By enterpriseSupportLink = By.cssSelector("a.openproject--static-link[href*='enterprise-guide/support'][target='_blank']");
    private By openProjectWebsiteLink = By.cssSelector("a[href*='openproject.org?go_to_locale'][title='OpenProject website'][target='_blank']");
    private By securityAlertsLink = By.cssSelector("a[href*='security-and-privacy'][title='Security alerts'][target='_blank']");
    private By newsletterLink = By.cssSelector("a[href*='newsletter'][title='Newsletter'][target='_blank']");
    private By openProjectBlogLink = By.cssSelector("a.openproject--static-link[href*='/blog'][target='_blank']");
    private By releaseNotesLink = By.cssSelector("a.openproject--static-link[href*='release-notes'][target='_blank']");
    private By reportaBugLink = By.cssSelector("a.openproject--static-link[href*='report-a-bug'][target='_blank']");
    private By developmentRoadmapLink = By.cssSelector("a.openproject--static-link[href*='/roadmap'][target='_blank']");
    private By addAndEditTranslationsLink = By.cssSelector("a.openproject--static-link[href*='translate-openproject'][target='_blank']");
    private By apiDocumentationLink = By.cssSelector("a.openproject--static-link[href*='/api'][target='_blank']");
    
    //  quick links / shortcut elements
    private By userGuidesShortcut = By.cssSelector("a.homescreen--links--item[href*='user-guide'][aria_label='User guides'][target='_blank']");
    private By glossaryShortcut = By.cssSelector("a.homescreen--links--item[href*='glossary'][aria_label='Glossary'][target='_blank']");
    private By shortcutsShortcut = By.cssSelector("a.homescreen--links--item[href*='keyboard-shortcuts-access-keys'][aria_label='Shortcuts'][target='_blank']");
    private By communityForumShortcut = By.cssSelector("a.homescreen--links--item[href*='community.openproject.org/projects/openproject/forums'][aria_label='Community forum'][target='_blank']");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║     WELCOME TO OPENPROJECT! PANEL FUNCTIONS            ║
    // ╚════════════════════════════════════════════════════════╝

    public HomePage clickSafeOpenProjectLink() {
        wait.until(ExpectedConditions.elementToBeClickable(safeOpenProjectLink)).click();
        Delay.waitDefault();
        return this;
    }

    // Dynamic approach - scalable for any number of links
    public int getWelcomePanelLinksCount() {
        return driver.findElements(welcomePanelLinks).size();
    }

    public HomePage clickWelcomePanelLinkByIndex(int index) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(welcomePanelLinks));
        driver.findElements(welcomePanelLinks).get(index).click();
        Delay.waitDefault();
        return this;
    }

    public String getWelcomePanelLinkText(int index) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(welcomePanelLinks));
        return driver.findElements(welcomePanelLinks).get(index).getText();
    }

    // Static approach methods (commented for reference)
    // public HomePage clickProjectPortofolioManagementLink() {
    //     wait.until(ExpectedConditions.elementToBeClickable(projectPortofolioManagementLink)).click();
    //     Delay.waitDefault();
    //     return this;
    // }
    
    // public HomePage clickProjectPlanningAndSchedulingLink() {
    //     wait.until(ExpectedConditions.elementToBeClickable(projectPlanningAndSchedulingLink)).click();
    //     Delay.waitDefault();
    //     return this;
    // }
    
    // public HomePage clickTaskManagementAndIssueTrackingLink() {
    //     wait.until(ExpectedConditions.elementToBeClickable(taskManagementAndIssueTrackingLink)).click();
    //     Delay.waitDefault();
    //     return this;
    // }
    
    // public HomePage clickAgileboardsLink() {
    //     wait.until(ExpectedConditions.elementToBeClickable(agileboardsLink)).click();
    //     Delay.waitDefault();
    //     return this;
    // }
    
    // public HomePage clickRequirementsManagementAndReleasePlanningLink() {
    //     wait.until(ExpectedConditions.elementToBeClickable(requirementsManagementAndReleasePlanningLink)).click();
    //     Delay.waitDefault();
    //     return this;
    // }
    
    // public HomePage clickTimeAndCostTrackingLink() {
    //     wait.until(ExpectedConditions.elementToBeClickable(timeAndCostTrackingLink)).click();
    //     Delay.waitDefault();
    //     return this;
    // }
    
    // public HomePage clickTeamCollaborationAndDocumentLink() {
    //     wait.until(ExpectedConditions.elementToBeClickable(teamCollaborationAndDocumentLink)).click();
    //     Delay.waitDefault();
    //     return this;
    // }

    public HomePage clickChangeWelcomeTextForAdminLink() {
        wait.until(ExpectedConditions.elementToBeClickable(changeWelcomeTextForAdminLink)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║            PROJECTS PANEL FUNCTIONS                    ║
    // ╚════════════════════════════════════════════════════════╝

    public HomePage clickBlueTeamLink() {
        wait.until(ExpectedConditions.elementToBeClickable(blueTeamLink)).click();
        wait.until(ExpectedConditions.urlContains("/projects/blue-team"));
        Delay.waitDefault();
        return this;
    }

    public HomePage clickRedTeamLink() {
        wait.until(ExpectedConditions.elementToBeClickable(redTeamLink)).click();
        wait.until(ExpectedConditions.urlContains("/projects/red-team"));
        Delay.waitDefault();
        return this;
    }

    public HomePage clickOtherProjectsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(otherProjectsLink)).click();
        wait.until(ExpectedConditions.urlContains("/projects/other-projects"));
        Delay.waitDefault();
        return this;
    }

    public HomePage clickViewAllProjectsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(viewAllProjectsButton)).click();
        wait.until(ExpectedConditions.urlContains("/projects"));
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║          NEW FEATURES PANEL FUNCTIONS                  ║
    // ╚════════════════════════════════════════════════════════╝

    public HomePage clickNewFeaturesPanelLink() {
        wait.until(ExpectedConditions.elementToBeClickable(newFeaturesPanelLink)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║              NEWS PANEL FUNCTIONS                      ║
    // ╚════════════════════════════════════════════════════════╝

    public HomePage clickNewsTitle1_DemoProjectTestSyntax() {
        wait.until(ExpectedConditions.elementToBeClickable(newsTitle1_DemoProjectTestSyntaxLink)).click();
        wait.until(ExpectedConditions.urlContains("/news/4-test-syntax-highlighting"));
        Delay.waitDefault();
        return this;
    }

    public HomePage clickNewsTitle2_DemoProjectActualites() {
        wait.until(ExpectedConditions.elementToBeClickable(newsTitle2_DemoProjectActualitesLink)).click();
        wait.until(ExpectedConditions.urlContains("/news/3-actualites"));
        Delay.waitDefault();
        return this;
    }

    public HomePage clickNewsTitle3_ScrumProjectWelcome() {
        wait.until(ExpectedConditions.elementToBeClickable(newsTitle3_ScrumProjectWelcomeLink)).click();
        wait.until(ExpectedConditions.urlContains("/news/2-welcome-to-your-scrum-demo-project"));
        Delay.waitDefault();
        return this;
    }

    public HomePage clickDemoAdminProfileLink() {
        wait.until(ExpectedConditions.elementToBeClickable(demoAdminProfileLink)).click();
        wait.until(ExpectedConditions.urlContains("/users/10"));
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║       OPENPROJECT COMMUNITY PANEL FUNCTIONS            ║
    // ╚════════════════════════════════════════════════════════╝

    public HomePage clickUserGuidesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(userGuidesLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickShortcutsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(shortcutsLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickCommunityForumLink() {
        wait.until(ExpectedConditions.elementToBeClickable(communityForumLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickEnterpriseSupportLink() {
        wait.until(ExpectedConditions.elementToBeClickable(enterpriseSupportLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickOpenProjectWebsiteLink() {
        wait.until(ExpectedConditions.elementToBeClickable(openProjectWebsiteLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickSecurityAlertsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(securityAlertsLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickNewsletterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(newsletterLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickOpenProjectBlogLink() {
        wait.until(ExpectedConditions.elementToBeClickable(openProjectBlogLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickReleaseNotesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(releaseNotesLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickReportaBugLink() {
        wait.until(ExpectedConditions.elementToBeClickable(reportaBugLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickDevelopmentRoadmapLink() {
        wait.until(ExpectedConditions.elementToBeClickable(developmentRoadmapLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickAddAndEditTranslationsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(addAndEditTranslationsLink)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickApiDocumentationLink() {
        wait.until(ExpectedConditions.elementToBeClickable(apiDocumentationLink)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║          QUICK LINKS / SHORTCUTS FUNCTIONS             ║
    // ╚════════════════════════════════════════════════════════╝

    public HomePage clickUserGuidesShortcut() {
        wait.until(ExpectedConditions.elementToBeClickable(userGuidesShortcut)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickGlossaryShortcut() {
        wait.until(ExpectedConditions.elementToBeClickable(glossaryShortcut)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickShortcutsShortcut() {
        wait.until(ExpectedConditions.elementToBeClickable(shortcutsShortcut)).click();
        Delay.waitDefault();
        return this;
    }

    public HomePage clickCommunityForumShortcut() {
        wait.until(ExpectedConditions.elementToBeClickable(communityForumShortcut)).click();
        Delay.waitDefault();
        return this;
    }
    
}
