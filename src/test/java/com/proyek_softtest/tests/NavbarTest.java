package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.config.TestData;
import com.proyek_softtest.pages.NavbarPage;
import com.proyek_softtest.utils.Delay;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Navbar Page")
@Feature("Navbar Functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NavbarTest extends BaseTest {

    private NavbarPage navbarPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting NavbarPageTest...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        navbarPage = new NavbarPage(driver);
    }

    // ╔════════════════════════════════════════════════════════════╗
    // ║                    HELPER METHODS                          ║
    // ╚════════════════════════════════════════════════════════════╝
    private void verifySidebarOverlayMenuNavigation(Runnable clickAction, String expectedUrl, String menuName) {
        navbarPage.openSideMenuOverlay();
        assertTrue(navbarPage.isSideMenuOverlayOpen(), "Side menu overlay should be open");
        
        clickAction.run();
        assertTrue(navbarPage.getCurrentUrl().contains(expectedUrl), "Should navigate to " + menuName + " page");
        captureScreenshotWithTitle("Navigate to " + menuName + " Page");

        navbarPage.navigateBack();
        assertTrue(navbarPage.isSideMenuOverlayOpen(), "Side menu overlay should be open after navigating back");
        
        navbarPage.closeSideMenuOverlay();
        assertFalse(navbarPage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }

    private void verifyHelpMenuLinkNavigation(Runnable clickAction, String expectedUrlPart, String linkName) {
        navbarPage.clickHelpButton();
        assertTrue(navbarPage.isHelpMenuDisplayed(), "Help menu should be displayed");
        
        String originalWindow = driver.getWindowHandle();
        
        clickAction.run();
        
        navbarPage.waitForNumberOfWindowsToBe(2);
        
        String newWindow = null;
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                newWindow = windowHandle;
                driver.switchTo().window(newWindow);
                break;
            }
        }
        
        navbarPage.waitForPageLoad();
        
        assertTrue(navbarPage.getCurrentUrl().contains(expectedUrlPart), 
            "Should navigate to " + linkName + " page with URL containing: " + expectedUrlPart);

        captureScreenshotWithTitle("Navigate to " + linkName + " Page");
        
        try {
            driver.close();
        } catch (Exception e) {
            System.out.println("Warning: Failed to close window normally for Help Menu - " + linkName + ", forcing close");
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
    @DisplayName("NB_T-001: Buka dan Tutup Side Menu Overlay")
    @Description("Verify that side menu overlay can be opened and closed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Side Menu Overlay Toggle")
    public void test1_OpenCloseSideMenuOverlay() {
        navbarPage.openSideMenuOverlay();
        assertTrue(navbarPage.isSideMenuOverlayOpen(), "Side menu overlay should be open");
        captureScreenshotWithTitle("Open Side Menu Overlay");

        navbarPage.closeSideMenuOverlay();
        assertFalse(navbarPage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }

    @Test
    @Order(2)
    @DisplayName("NB_T-002: Navigasi Home di side menu overlay")
    @Description("Verify navigation to Home through side menu overlay (refresh)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Side Menu Overlay Navigation")
    public void test2_NavigateToHome() {
        navbarPage.openSideMenuOverlay();
        assertTrue(navbarPage.isSideMenuOverlayOpen(), "Side menu overlay should be open");
        
        navbarPage.clickHomeInSideMenuOverlay();
        assertTrue(navbarPage.getCurrentUrl().endsWith("/"), "Should navigate to home page (refresh)");
        captureScreenshotWithTitle("Navigate to Home Page");
    }

    @Test
    @Order(3)
    @DisplayName("NB_T-003: Navigasi Projects di side menu overlay")
    @Description("Verify navigation to Projects through side menu overlay")
    @Severity(SeverityLevel.NORMAL)
    @Story("Side Menu Overlay Navigation")
    public void test3_NavigateToProjects() {
        verifySidebarOverlayMenuNavigation(
            () -> navbarPage.clickProjectsInSideMenuOverlay(),
            "/projects",
            "Projects"
        );
    }

    @Test
    @Order(4)
    @DisplayName("NB_T-004: Navigasi Work Packages di side menu overlay")
    @Description("Verify navigation to Work Packages through side menu overlay")
    @Severity(SeverityLevel.NORMAL)
    @Story("Side Menu Overlay Navigation")
    public void test4_NavigateToWorkPackages() {
        verifySidebarOverlayMenuNavigation(
            () -> navbarPage.clickWorkPackagesInSideMenuOverlay(),
            "/work_packages",
            "Work Packages"
        );
    }

    @Test
    @Order(5)
    @DisplayName("NB_T-005: Navigasi Gantt Charts di side menu overlay")
    @Description("Verify navigation to Gantt Charts through side menu overlay")
    @Severity(SeverityLevel.NORMAL)
    @Story("Side Menu Overlay Navigation")
    public void test5_NavigateToGanttCharts() {
        verifySidebarOverlayMenuNavigation(
            () -> navbarPage.clickGanttChartsInSideMenuOverlay(),
            "/gantt",
            "Gantt Charts"
        );
    }

    @Test
    @Order(6)
    @DisplayName("NB_T-006: Navigasi Team Planners di side menu overlay")
    @Description("Verify navigation to Team Planners through side menu overlay")
    @Severity(SeverityLevel.NORMAL)
    @Story("Side Menu Overlay Navigation")
    public void test6_NavigateToTeamPlanners() {
        verifySidebarOverlayMenuNavigation(
            () -> navbarPage.clickTeamPlannersInSideMenuOverlay(),
            "/team_planners",
            "Team Planners"
        );
    }

    @Test
    @Order(7)
    @DisplayName("NB_T-007: Navigasi Boards di side menu overlay")
    @Description("Verify navigation to Boards through side menu overlay")
    @Severity(SeverityLevel.NORMAL)
    @Story("Side Menu Overlay Navigation")
    public void test7_NavigateToBoards() {
        verifySidebarOverlayMenuNavigation(
            () -> navbarPage.clickBoardsInSideMenuOverlay(),
            "/boards",
            "Boards"
        );
    }

    @Test
    @Order(8)
    @DisplayName("NB_T-008: Navigasi Meetings di side menu overlay")
    @Description("Verify navigation to Meetings through side menu overlay")
    @Severity(SeverityLevel.NORMAL)
    @Story("Side Menu Overlay Navigation")
    public void test8_NavigateToMeetings() {
        verifySidebarOverlayMenuNavigation(
            () -> navbarPage.clickMeetingsInSideMenuOverlay(),
            "/meetings",
            "Meetings"
        );
    }

    @Test
    @Order(9)
    @DisplayName("NB_T-009: Navigasi News di side menu overlay")
    @Description("Verify navigation to News through side menu overlay")
    @Severity(SeverityLevel.NORMAL)
    @Story("Side Menu Overlay Navigation")
    public void test9_NavigateToNews() {
        verifySidebarOverlayMenuNavigation(
            () -> navbarPage.clickNewsInSideMenuOverlay(),
            "/news",
            "News"
        );
    }

    @Test
    @Order(10)
    @DisplayName("NB_T-010: Navigasi Time and Costs di side menu overlay")
    @Description("Verify navigation to Time and Costs through side menu overlay")
    @Severity(SeverityLevel.NORMAL)
    @Story("Side Menu Overlay Navigation")
    public void test10_NavigateToTimeAndCosts() {
        verifySidebarOverlayMenuNavigation(
            () -> navbarPage.clickTimeAndCostsInSideMenuOverlay(),
            "/cost_reports",
            "Time and Costs"
        );
    }

    @Test
    @Order(11)
    @DisplayName("NB_T-011: Klik OpenProject Logo")
    @Description("Verify clicking OpenProject logo navigates to home page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Navigation")
    public void test11_ClickOpenProjectLogo() {
        navbarPage.clickOpenProjectLogo();
        assertTrue(navbarPage.getCurrentUrl().equals("https://safe.openproject.com/"), "Should navigate to home page when clicking logo");
        captureScreenshotWithTitle("Navigate to Home Page via Logo");
    }

    @Test
    @Order(12)
    @DisplayName("NB_T-012: Search dengan query 'abc'")
    @Description("Verify searching with 'abc' and clicking 'In all projects'")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Navbar Search")
    public void test12_SearchNoResults() {
        String searchQuery = TestData.getNavbarNoResultsSearch();
        navbarPage.typeInSearchBar(searchQuery);
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed");
        captureScreenshotWithTitle("Search Dropdown with Query '" + searchQuery + "'");

        navbarPage.clickInAllProjects();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=" + searchQuery), "Should navigate to search results page with query '" + searchQuery + "'");
        captureScreenshotWithTitle("Search Results Page with Query '" + searchQuery + "'");
    }

    @Test
    @Order(13)
    @DisplayName("NB_T-013: Search berdasarkan ID 70")
    @Description("Verify searching work package by ID 70")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Navbar Search")
    public void test13_SearchById() {
        String searchId = TestData.getNavbarIdSearch();
        navbarPage.typeInSearchBar(searchId);
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed");
        captureScreenshotWithTitle("Search Dropdown with Query '" + searchId + "'");

        navbarPage.clickInAllProjects();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=" + searchId), "Should navigate to search results page with query '" + searchId + "'");
        captureScreenshotWithTitle("Search Results Page with Query '" + searchId + "'");

        navbarPage.navigateBack();
        navbarPage.clickSearchBar();
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed after navigate back");
        
        navbarPage.clickSearchResultByWorkPackageId("70");
        assertTrue(navbarPage.getCurrentUrl().contains("/work_packages/70"), "Should navigate to work package 70");
        captureScreenshotWithTitle("Navigate to Work Package 70");
    }

    @Test
    @Order(14)
    @DisplayName("NB_T-014: Search berdasarkan kategori 'art-2 design'")
    @Description("Verify searching by category 'art-2 design'")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Search")
    public void test14_SearchByCategory() {
        String categorySearch = TestData.getNavbarCategorySearch();
        String expectedProject = TestData.getNavbarCategoryProject();
        
        navbarPage.typeInSearchBar(categorySearch);
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed");
        captureScreenshotWithTitle("Search Dropdown with Query '" + categorySearch + "'");

        navbarPage.clickInAllProjects();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=art-2"),   "Should navigate to search results page with query '" + categorySearch + "'");
        captureScreenshotWithTitle("Search Results Page with Query '" + categorySearch + "'");

        navbarPage.navigateBack();
        Delay.waitFor(1000);
        
        navbarPage.typeInSearchBar(categorySearch);
        Delay.waitDefault();
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed after navigate back");
        
        navbarPage.clickSearchResultByProject(expectedProject);
        assertTrue(navbarPage.getCurrentUrl().contains("/work_packages/"), "Should navigate to a work package");
        captureScreenshotWithTitle("Navigate to Work Package from Category Search");
    }

    @Test
    @Order(15)
    @DisplayName("NB_T-015: Search berdasarkan judul 'SSL certificate'")
    @Description("Verify searching by title 'SSL certificate'")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Search")
    public void test15_SearchByTitle() {
        String titleSearch = TestData.getNavbarTitleSearch();
        String expectedSubject = TestData.getNavbarTitleSubject();
        
        Delay.waitFor(1000);

        navbarPage.typeInSearchBar(titleSearch);
        Delay.waitFor(1000);
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed");
        captureScreenshotWithTitle("Search Dropdown with Query '" + titleSearch + "'");

        navbarPage.clickInAllProjects();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=SSL+certificate"), 
            "Should navigate to search results page with query '" + titleSearch + "'");
        captureScreenshotWithTitle("Search Results Page with Query '" + titleSearch + "'");

        navbarPage.navigateBack();
        navbarPage.clickSearchBar();
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed after navigate back");
        
        navbarPage.clickSearchResultBySubject(expectedSubject);
        assertTrue(navbarPage.getCurrentUrl().contains("/work_packages/"), "Should navigate to a work package");
        captureScreenshotWithTitle("Navigate to Work Package from Title Search");
    }

    @Test
    @Order(16)
    @DisplayName("NB_T-016: Klik Help Button untuk membuka dan menutup help menu")
    @Description("Verify clicking Help button to open and close help menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test16_ClickHelpButton() {
        navbarPage.clickHelpButton();
        assertTrue(navbarPage.isHelpMenuDisplayed(), "Help menu should be displayed after clicking help button");
        captureScreenshotWithTitle("Help Menu Displayed");

        navbarPage.clickHelpButton();
        assertFalse(navbarPage.isHelpMenuDisplayed(), "Help menu should be closed after clicking help button again");
    }

    @Test
    @Order(17)
    @DisplayName("NB_T-017: Klik Getting Started Link dan tutup dengan tombol X")
    @Description("Verify clicking Getting Started link opens popup and close with button 1 (X button)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test17_ClickGettingStartedLinkAndCloseWithButton1() {
        navbarPage.clickHelpButton();
        assertTrue(navbarPage.isHelpMenuDisplayed(), "Help menu should be displayed");
        
        navbarPage.clickGettingStartedLink();
        assertTrue(navbarPage.isGettingStartedPopUpVideoDisplayed(), "Getting started popup video should be displayed");
        captureScreenshotWithTitle("Getting Started Popup Displayed");
        
        navbarPage.closeGettingStartedPopUpWithButton1();
        
        navbarPage.clickHelpButton();
        assertTrue(navbarPage.isHelpMenuDisplayed(), "Help menu should be displayed again");
        
        navbarPage.clickGettingStartedLink();
        assertTrue(navbarPage.isGettingStartedPopUpVideoDisplayed(), "Getting started popup video should be displayed again");
        captureScreenshotWithTitle("Getting Started Popup Displayed Again");
        
        navbarPage.closeGettingStartedPopUpWithButton2();
    }

    @Test
    @Order(18)
    @DisplayName("NB_T-018: Help Menu - Upgrade to Enterprise Edition Link")
    @Description("Verify clicking Upgrade to Enterprise Edition link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test18_UpgradeToEnterpriseLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickUpgradeToEnterpriseLink(),
            "openproject.org/enterprise-edition",
            "Upgrade to Enterprise Edition"
        );
    }

    @Test
    @Order(19)
    @DisplayName("NB_T-019: Help Menu - User Guides Link")
    @Description("Verify clicking User Guides link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test19_UserGuidesLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickUserGuidesLink(),
            "openproject.org/docs/user-guide",
            "User Guides"
        );
    }

    @Test
    @Order(20)
    @DisplayName("NB_T-020: Help Menu - Videos Link")
    @Description("Verify clicking Videos link opens YouTube page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test20_VideosLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickVideosLink(),
            "youtube.com",
            "Videos"
        );
    }

    @Test
    @Order(21)
    @DisplayName("NB_T-021: Help Menu - Shortcuts Link")
    @Description("Verify clicking Shortcuts link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test21_ShortcutsLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickShortcutsLink(),
            "keyboard-shortcuts",
            "Shortcuts"
        );
    }

    @Test
    @Order(22)
    @DisplayName("NB_T-022: Help Menu - Community Forum Link")
    @Description("Verify clicking Community Forum link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test22_CommunityForumLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickCommunityForumLink(),
            "community.openproject.org",
            "Community Forum"
        );
    }

    @Test
    @Order(23)
    @DisplayName("NB_T-023: Help Menu - Enterprise Support Link")
    @Description("Verify clicking Enterprise Support link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test23_EnterpriseSupportLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickEnterpriseSupportLink(),
            "enterprise-guide/support",
            "Enterprise Support"
        );
    }

    @Test
    @Order(24)
    @DisplayName("NB_T-024: Help Menu - Data Privacy and Security Policy Link")
    @Description("Verify clicking Data Privacy and Security Policy link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test24_DataPrivacyAndSecurityLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickDataPrivacyAndSecurityLink(),
            "legal/privacy",
            "Data Privacy and Security"
        );
    }

    @Test
    @Order(25)
    @DisplayName("NB_T-025: Help Menu - Digital Accessibility Link")
    @Description("Verify clicking Digital Accessibility link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test25_DigitalAccessibilityLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickDigitalAccessibilityLink(),
            "erklaerung-zur-digitalen-barrierefreiheit",
            "Digital Accessibility"
        );
    }

    @Test
    @Order(26)
    @DisplayName("NB_T-026: Help Menu - OpenProject Website Link")
    @Description("Verify clicking OpenProject Website link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test26_OpenProjectWebsiteLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickOpenProjectWebsiteLink(),
            "openproject.org",
            "OpenProject Website"
        );
    }

    @Test
    @Order(27)
    @DisplayName("NB_T-027: Help Menu - Security Alerts Link")
    @Description("Verify clicking Security Alerts link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test27_SecurityLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickSecurityLink(),
            "security-and-privacy",
            "Security Alerts"
        );
    }

    @Test
    @Order(28)
    @DisplayName("NB_T-028: Help Menu - Newsletter Link")
    @Description("Verify clicking Newsletter link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test28_NewsletterLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickNewsletterLink(),
            "newsletter",
            "Newsletter"
        );
    }

    @Test
    @Order(29)
    @DisplayName("NB_T-029: Help Menu - OpenProject Blog Link")
    @Description("Verify clicking OpenProject Blog link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test29_OpenProjectBlogLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickOpenProjectBlogLink(),
            "blog",
            "OpenProject Blog"
        );
    }

    @Test
    @Order(30)
    @DisplayName("NB_T-030: Help Menu - Release Notes Link")
    @Description("Verify clicking Release Notes link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test30_ReleaseNotesLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickReleaseNotesLink(),
            "release-notes",
            "Release Notes"
        );
    }

    @Test
    @Order(31)
    @DisplayName("NB_T-031: Help Menu - Report a Bug Link")
    @Description("Verify clicking Report a Bug link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test31_ReportaBugLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickReportaBugLink(),
            "report-a-bug",
            "Report a Bug"
        );
    }

    @Test
    @Order(32)
    @DisplayName("NB_T-032: Help Menu - Development Roadmap Link")
    @Description("Verify clicking Development Roadmap link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test32_DevelopmentLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickDevelopmentLink(),
            "roadmap",
            "Development Roadmap"
        );
    }

    @Test
    @Order(33)
    @DisplayName("NB_T-033: Help Menu - Add and Edit Translations Link")
    @Description("Verify clicking Add and Edit Translations link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test33_AddAndEditLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickAddAndEditLink(),
            "translate-openproject",
            "Add and Edit Translations"
        );
    }

    @Test
    @Order(34)
    @DisplayName("NB_T-034: Help Menu - API Documentation Link")
    @Description("Verify clicking API Documentation link opens correct page in new tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test34_ApiDocumentationLink() {
        verifyHelpMenuLinkNavigation(
            () -> navbarPage.clickApiDocumentationLink(),
            "docs/api",
            "API Documentation"
        );
    }

    // ╔════════════════════════════════════════════════════════════╗
    // ║                    SIGN IN TESTS                           ║
    // ╚════════════════════════════════════════════════════════════╝

    @Test
    @Order(35)
    @DisplayName("NB_T-035: Open and Close Sign In Side Panel")
    @Description("verify sign in side panel can be opened and closed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sign In Side Panel")
    public void testOpenAndCloseSignInSidePanel() {
        navbarPage.clickSignInButton();
        
        assertTrue(navbarPage.isSignInSidePanelDisplayed(), "Sign in side panel should be displayed after clicking sign in button");
        captureScreenshotWithTitle("Sign In Side Panel Displayed");

        navbarPage.closeSignInSidePanel();
    }

    @Test
    @Order(36)
    @DisplayName("NB_T-036: Sign In with Google Redirect")
    @Description("verify clicking sign in with Google button redirects to Google OAuth page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sign In with Google")
    public void testSignInWithGoogleRedirect() {
        navbarPage.clickSignInButton();
        assertTrue(navbarPage.isSignInSidePanelDisplayed(), "Sign in side panel should be displayed");
        
        navbarPage.clickSignInWithGoogle();
        
        assertTrue(navbarPage.getCurrentUrl().contains("accounts.google.com") || 
                   navbarPage.getCurrentUrl().contains("oauth"), 
                   "Should redirect to Google OAuth page");
        captureScreenshotWithTitle("Google OAuth Redirect");

        navbarPage.navigateBack();

        navbarPage.closeSignInSidePanel();
    }

    @Test
    @Order(37)
    @DisplayName("NB_T-037: Sign In with Invalid Credentials")
    @Description("verify signing in with invalid credentials shows login form")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sign In with Credentials")
    public void testSignInWithInvalidCredentials() {
        navbarPage.clickSignInButton();
        assertTrue(navbarPage.isSignInSidePanelDisplayed(), "Sign in side panel should be displayed");
        
        navbarPage.enterUsername(TestData.getInvalidUsername());
        navbarPage.enterPassword(TestData.getInvalidPassword());
        captureScreenshotWithTitle("Invalid Credentials Entered");

        navbarPage.clickSignInSubmitButton();
        
        assertTrue(navbarPage.isLoginFormDisplayed(), "Login form should be displayed after failed login attempt");
        captureScreenshotWithTitle("Login Form Displayed After Failed Attempt");
    }

    @Test
    @Order(38)
    @DisplayName("NB_T-038: Navigate to Forgot Password Page")
    @Description("verify clicking forgot password link navigates to lost password page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Forgot Password")
    public void testNavigateToForgotPassword() {
        navbarPage.clickSignInButton();
        assertTrue(navbarPage.isSignInSidePanelDisplayed(), "Sign in side panel should be displayed");
        
        navbarPage.clickForgotPasswordLink();
        
        assertTrue(navbarPage.getCurrentUrl().contains("/account/lost_password"), 
                   "Should navigate to lost password page");
        captureScreenshotWithTitle("Lost Password Page Displayed");
    }
}
