package com.proyek_softtest.tests;

import org.junit.jupiter.api.*;

import io.qameta.allure.*;
import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.HomePage;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Home Page")
@Feature("Home Page Functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest extends BaseTest {
    
    private HomePage homePage;
    
    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting HomePageTest...");
    }
    
    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        homePage = new HomePage(driver);
    }

    // ╔════════════════════════════════════════════════════════════╗
    // ║                    HELPER METHODS                          ║
    // ╚════════════════════════════════════════════════════════════╝
    
    private void verifyLinkNavigationInNewTab(Runnable clickAction, String expectedUrlPart, String linkName) {
        String originalWindow = driver.getWindowHandle();
        
        clickAction.run();
        
        homePage.waitForNumberOfWindowsToBe(2);
        
        String newWindow = null;
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                newWindow = windowHandle;
                driver.switchTo().window(newWindow);
                break;
            }
        }
        
        homePage.waitForPageLoad();
        
        assertTrue(homePage.getCurrentUrl().contains(expectedUrlPart), 
            "Should navigate to " + linkName + " page with URL containing: " + expectedUrlPart);
        captureScreenshotWithTitle("Navigate to " + linkName + " Page");

        try {
            driver.close();
        } catch (Exception e) {
            System.out.println("Warning: Failed to close window normally, forcing close for: " + linkName);
            try {
                driver.switchTo().window(originalWindow);
            } catch (Exception ex) {
                // Ignore if already switched
            }
        }
        
        driver.switchTo().window(originalWindow);
    }

    // ╔════════════════════════════════════════════════════════════╗
    // ║                         TESTS                              ║
    // ╚════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("Test 1: Safe OpenProject Link")
    @Description("Verify clicking safe OpenProject link navigates to home page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Welcome Panel Navigation")
    public void test01_SafeOpenProjectLink() {
        homePage.clickSafeOpenProjectLink();
        assertTrue(homePage.getCurrentUrl().contains("safe.openproject.com"), 
            "Should stay on safe.openproject.com");
        captureScreenshotWithTitle("Safe OpenProject Link Navigation");
    }

    // Dynamic approach - tests all links regardless of count
    @Test
    @Order(2)
    @DisplayName("Test 2: All Welcome Panel Links Are Clickable")
    @Description("Verify all welcome panel links open correct pages in new tabs")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Welcome Panel Navigation")
    public void test02_AllWelcomePanelLinksAreClickable() {
        int linksCount = homePage.getWelcomePanelLinksCount();
        assertTrue(linksCount > 0, "Welcome panel should have at least one link");
        
        for (int i = 0; i < linksCount; i++) {
            String linkText = homePage.getWelcomePanelLinkText(i);
            int currentIndex = i; // For lambda
            
            verifyLinkNavigationInNewTab(
                () -> homePage.clickWelcomePanelLinkByIndex(currentIndex),
                "openproject.org",
                "Welcome Panel Link " + (currentIndex + 1) + ": " + linkText
            );
        }
    }

    // Static approach tests (commented for reference)
    // @Test
    // @Order(2)
    // @DisplayName("Test 2: Project Portfolio Management Link")
    // @Description("Verify clicking Project Portfolio Management link opens correct page in new tab")
    // @Severity(SeverityLevel.NORMAL)
    // @Story("Welcome Panel Navigation")
    // public void test02_ProjectPortfolioManagementLink() {
    //     verifyLinkNavigationInNewTab(
    //         () -> homePage.clickProjectPortofolioManagementLink(),
    //         "project-portfolio-management",
    //         "Project Portfolio Management"
    //     );
    // }
    
    // @Test
    // @Order(3)
    // @DisplayName("Test 3: Project Planning and Scheduling Link")
    // @Description("Verify clicking Project Planning and Scheduling link opens correct page in new tab")
    // @Severity(SeverityLevel.NORMAL)
    // @Story("Welcome Panel Navigation")
    // public void test03_ProjectPlanningAndSchedulingLink() {
    //     verifyLinkNavigationInNewTab(
    //         () -> homePage.clickProjectPlanningAndSchedulingLink(),
    //         "project-planning-scheduling",
    //         "Project Planning and Scheduling"
    //     );
    // }
    
    // @Test
    // @Order(4)
    // @DisplayName("Test 4: Task Management and Issue Tracking Link")
    // @Description("Verify clicking Task Management and Issue Tracking link opens correct page in new tab")
    // @Severity(SeverityLevel.NORMAL)
    // @Story("Welcome Panel Navigation")
    // public void test04_TaskManagementAndIssueTrackingLink() {
    //     verifyLinkNavigationInNewTab(
    //         () -> homePage.clickTaskManagementAndIssueTrackingLink(),
    //         "task-management",
    //         "Task Management and Issue Tracking"
    //     );
    // }
    
    // @Test
    // @Order(5)
    // @DisplayName("Test 5: Agile Boards Link")
    // @Description("Verify clicking Agile Boards link opens correct page in new tab")
    // @Severity(SeverityLevel.NORMAL)
    // @Story("Welcome Panel Navigation")
    // public void test05_AgileboardsLink() {
    //     verifyLinkNavigationInNewTab(
    //         () -> homePage.clickAgileboardsLink(),
    //         "agile-project-management",
    //         "Agile Boards"
    //     );
    // }
    
    // @Test
    // @Order(6)
    // @DisplayName("Test 6: Requirements Management and Release Planning Link")
    // @Description("Verify clicking Requirements Management and Release Planning link opens correct page in new tab")
    // @Severity(SeverityLevel.NORMAL)
    // @Story("Welcome Panel Navigation")
    // public void test06_RequirementsManagementAndReleasePlanningLink() {
    //     verifyLinkNavigationInNewTab(
    //         () -> homePage.clickRequirementsManagementAndReleasePlanningLink(),
    //         "product-development",
    //         "Requirements Management and Release Planning"
    //     );
    // }
    
    // @Test
    // @Order(7)
    // @DisplayName("Test 7: Time and Cost Tracking Link")
    // @Description("Verify clicking Time and Cost Tracking link opens correct page in new tab")
    // @Severity(SeverityLevel.NORMAL)
    // @Story("Welcome Panel Navigation")
    // public void test07_TimeAndCostTrackingLink() {
    //     verifyLinkNavigationInNewTab(
    //         () -> homePage.clickTimeAndCostTrackingLink(),
    //         "time-tracking",
    //         "Time and Cost Tracking"
    //     );
    // }
    
    // @Test
    // @Order(8)
    // @DisplayName("Test 8: Team Collaboration and Document Link")
    // @Description("Verify clicking Team Collaboration and Document link opens correct page in new tab")
    // @Severity(SeverityLevel.NORMAL)
    // @Story("Welcome Panel Navigation")
    // public void test08_TeamCollaborationAndDocumentLink() {
    //     verifyLinkNavigationInNewTab(
    //         () -> homePage.clickTeamCollaborationAndDocumentLink(),
    //         "team-collaboration",
    //         "Team Collaboration and Document"
    //     );
    // }

    @Test
    @Order(3)
    @DisplayName("Test 3: Change Welcome Text for Admin Link")
    @Description("Verify clicking Change Welcome Text for Admin link opens admin settings page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Welcome Panel Navigation")
    public void test03_ChangeWelcomeTextForAdminLink() {
        homePage.clickChangeWelcomeTextForAdminLink();
        assertTrue(homePage.getCurrentUrl().contains("/login"), 
            "Should navigate to login page since not logged in as admin");

        captureScreenshotWithTitle("Navigate to Login Page for Admin");
        homePage.navigateBack();
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Blue Team Project Link")
    @Description("Verify clicking Blue Team link navigates to Blue Team project page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Panel Navigation")
    public void test04_BlueTeamLink() {
        homePage.clickBlueTeamLink();
        assertTrue(homePage.getCurrentUrl().contains("/projects/blue-team"), 
            "Should navigate to Blue Team project page");
        captureScreenshotWithTitle("Navigate to Blue Team Project");
        homePage.navigateBack();
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Red Team Project Link")
    @Description("Verify clicking Red Team link navigates to Red Team project page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Panel Navigation")
    public void test05_RedTeamLink() {
        homePage.clickRedTeamLink();
        assertTrue(homePage.getCurrentUrl().contains("/projects/red-team"), 
            "Should navigate to Red Team project page");
        captureScreenshotWithTitle("Navigate to Red Team Project");
        homePage.navigateBack();
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Other Projects Link")
    @Description("Verify clicking Other Projects link navigates to Other Projects page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Panel Navigation")
    public void test06_OtherProjectsLink() {
        homePage.clickOtherProjectsLink();
        assertTrue(homePage.getCurrentUrl().contains("/projects/other-projects"), 
            "Should navigate to Other Projects page");
        captureScreenshotWithTitle("Navigate to Other Projects");
        homePage.navigateBack();
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: View All Projects Button")
    @Description("Verify clicking View All Projects button navigates to all projects page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Panel Navigation")
    public void test07_ViewAllProjectsButton() {
        homePage.clickViewAllProjectsButton();
        assertTrue(homePage.getCurrentUrl().contains("/projects"), 
            "Should navigate to all projects page");
        captureScreenshotWithTitle("Navigate to All Projects Page");
        homePage.navigateBack();
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: New Features Panel Link")
    @Description("Verify clicking Learn more about all new features link opens release notes page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("New Features Panel Navigation")
    public void test08_NewFeaturesPanelLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickNewFeaturesPanelLink(),
            "release-notes",
            "New Features Panel"
        );
    }

    @Test
    @Order(9)
    @DisplayName("Test 9: News Title 1 - Test Syntax Highlighting")
    @Description("Verify clicking 'Test syntax highlighting' news title navigates to news detail page")
    @Severity(SeverityLevel.NORMAL)
    @Story("News Panel Navigation")
    public void test09_NewsTitle1_DemoProjectTestSyntax() {
        homePage.clickNewsTitle1_DemoProjectTestSyntax();
        assertTrue(homePage.getCurrentUrl().contains("/news/4-test-syntax-highlighting"), 
            "Should navigate to Test syntax highlighting news page");
        homePage.navigateBack();
    }

    @Test
    @Order(10)
    @DisplayName("Test 10: News Title 2 - Actualités Réforme Fonction Publique")
    @Description("Verify clicking 'Actualités - Réforme de la haute fonction publique' news title navigates to news detail page")
    @Severity(SeverityLevel.NORMAL)
    @Story("News Panel Navigation")
    public void test10_NewsTitle2_DemoProjectActualites() {
        homePage.clickNewsTitle2_DemoProjectActualites();
        assertTrue(homePage.getCurrentUrl().contains("/news/3-actualites"), 
            "Should navigate to Actualités news page");
        homePage.navigateBack();
    }

    @Test
    @Order(11)
    @DisplayName("Test 11: News Title 3 - Welcome to Scrum Demo Project")
    @Description("Verify clicking 'Welcome to your Scrum demo project' news title navigates to news detail page")
    @Severity(SeverityLevel.NORMAL)
    @Story("News Panel Navigation")
    public void test11_NewsTitle3_ScrumProjectWelcome() {
        homePage.clickNewsTitle3_ScrumProjectWelcome();
        assertTrue(homePage.getCurrentUrl().contains("/news/2-welcome-to-your-scrum-demo-project"), 
            "Should navigate to Welcome Scrum demo project news page");
        homePage.navigateBack();
    }

    @Test
    @Order(12)
    @DisplayName("Test 12: Demo Admin Profile Link in News Panel")
    @Description("Verify clicking Demo admin profile link navigates to user profile page")
    @Severity(SeverityLevel.NORMAL)
    @Story("News Panel Navigation")
    public void test12_DemoAdminProfileLink() {
        homePage.clickDemoAdminProfileLink();
        assertTrue(homePage.getCurrentUrl().contains("/users/10"), 
            "Should navigate to Demo admin user profile page");
        homePage.navigateBack();
    }

    @Test
    @Order(13)
    @DisplayName("Test 13: User Guides Link")
    @Description("Verify clicking User guides link opens documentation in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test13_UserGuidesLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickUserGuidesLink(),
            "user-guide",
            "User Guides"
        );
    }

    @Test
    @Order(14)
    @DisplayName("Test 14: Shortcuts Link")
    @Description("Verify clicking Shortcuts link opens keyboard shortcuts documentation in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test14_ShortcutsLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickShortcutsLink(),
            "keyboard-shortcuts",
            "Shortcuts"
        );
    }

    @Test
    @Order(15)
    @DisplayName("Test 15: Community Forum Link")
    @Description("Verify clicking Community forum link opens forum in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test15_CommunityForumLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickCommunityForumLink(),
            "community.openproject.org",
            "Community Forum"
        );
    }

    @Test
    @Order(16)
    @DisplayName("Test 16: Enterprise Support Link")
    @Description("Verify clicking Enterprise support link opens support documentation in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test16_EnterpriseSupportLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickEnterpriseSupportLink(),
            "enterprise-guide/support",
            "Enterprise Support"
        );
    }

    @Test
    @Order(17)
    @DisplayName("Test 17: OpenProject Website Link")
    @Description("Verify clicking OpenProject website link opens main website in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test17_OpenProjectWebsiteLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickOpenProjectWebsiteLink(),
            "openproject.org",
            "OpenProject Website"
        );
    }

    @Test
    @Order(18)
    @DisplayName("Test 18: Security Alerts Link")
    @Description("Verify clicking Security alerts link opens security page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test18_SecurityAlertsLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickSecurityAlertsLink(),
            "security-and-privacy",
            "Security Alerts"
        );
    }

    @Test
    @Order(19)
    @DisplayName("Test 19: Newsletter Link")
    @Description("Verify clicking Newsletter link opens newsletter subscription page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test19_NewsletterLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickNewsletterLink(),
            "newsletter",
            "Newsletter"
        );
    }

    @Test
    @Order(20)
    @DisplayName("Test 20: OpenProject Blog Link")
    @Description("Verify clicking OpenProject blog link opens blog in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test20_OpenProjectBlogLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickOpenProjectBlogLink(),
            "/blog",
            "OpenProject Blog"
        );
    }

    @Test
    @Order(21)
    @DisplayName("Test 21: Release Notes Link")
    @Description("Verify clicking Release notes link opens release notes documentation in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test21_ReleaseNotesLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickReleaseNotesLink(),
            "release-notes",
            "Release Notes"
        );
    }

    @Test
    @Order(22)
    @DisplayName("Test 22: Report a Bug Link")
    @Description("Verify clicking Report a bug link opens bug reporting documentation in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test22_ReportaBugLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickReportaBugLink(),
            "report-a-bug",
            "Report a Bug"
        );
    }

    @Test
    @Order(23)
    @DisplayName("Test 23: Development Roadmap Link")
    @Description("Verify clicking Development roadmap link opens roadmap page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test23_DevelopmentRoadmapLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickDevelopmentRoadmapLink(),
            "/roadmap",
            "Development Roadmap"
        );
    }

    @Test
    @Order(24)
    @DisplayName("Test 24: Add and Edit Translations Link")
    @Description("Verify clicking Add and edit translations link opens translation guide in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test24_AddAndEditTranslationsLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickAddAndEditTranslationsLink(),
            "translate-openproject",
            "Add and Edit Translations"
        );
    }

    @Test
    @Order(25)
    @DisplayName("Test 25: API Documentation Link")
    @Description("Verify clicking API documentation link opens API docs in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("OpenProject Community Panel Navigation")
    public void test25_ApiDocumentationLink() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickApiDocumentationLink(),
            "/api",
            "API Documentation"
        );
    }

    @Test
    @Order(26)
    @DisplayName("Test 26: User Guides Shortcut Link")
    @Description("Verify clicking User guides shortcut link opens documentation in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Quick Links / Shortcuts Navigation")
    public void test26_UserGuidesShortcut() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickUserGuidesShortcut(),
            "user-guide",
            "User Guides Shortcut"
        );
    }

    @Test
    @Order(27)
    @DisplayName("Test 27: Glossary Shortcut Link")
    @Description("Verify clicking Glossary shortcut link opens glossary documentation in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Quick Links / Shortcuts Navigation")
    public void test27_GlossaryShortcut() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickGlossaryShortcut(),
            "glossary",
            "Glossary Shortcut"
        );
    }

    @Test
    @Order(28)
    @DisplayName("Test 28: Shortcuts Shortcut Link")
    @Description("Verify clicking Shortcuts shortcut link opens keyboard shortcuts documentation in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Quick Links / Shortcuts Navigation")
    public void test28_ShortcutsShortcut() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickShortcutsShortcut(),
            "keyboard-shortcuts",
            "Shortcuts Shortcut"
        );
    }

    @Test
    @Order(29)
    @DisplayName("Test 29: Community Forum Shortcut Link")
    @Description("Verify clicking Community forum shortcut link opens forum in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Quick Links / Shortcuts Navigation")
    public void test29_CommunityForumShortcut() {
        verifyLinkNavigationInNewTab(
            () -> homePage.clickCommunityForumShortcut(),
            "community.openproject.org",
            "Community Forum Shortcut"
        );
    }
    
}