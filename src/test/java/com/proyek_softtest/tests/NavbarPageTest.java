package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.NavbarPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Navbar Page")
@Feature("Navbar Functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NavbarPageTest extends BaseTest {

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
    @DisplayName("Test 1: Buka dan Tutup Side Menu Overlay")
    @Description("Verify that side menu overlay can be opened and closed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Side Menu Overlay Toggle")
    public void test1_OpenCloseSideMenuOverlay() {
        navbarPage.openSideMenuOverlay();
        assertTrue(navbarPage.isSideMenuOverlayOpen(), "Side menu overlay should be open");

        navbarPage.closeSideMenuOverlay();
        assertFalse(navbarPage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Navigasi Home di side menu overlay")
    @Description("Verify navigation to Home through side menu overlay (refresh)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Side Menu Overlay Navigation")
    public void test2_NavigateToHome() {
        navbarPage.openSideMenuOverlay();
        assertTrue(navbarPage.isSideMenuOverlayOpen(), "Side menu overlay should be open");

        navbarPage.clickHomeInSideMenuOverlay();
        assertTrue(navbarPage.getCurrentUrl().endsWith("/"), "Should navigate to home page (refresh)");
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Navigasi Projects di side menu overlay")
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
    @DisplayName("Test 4: Navigasi Work Packages di side menu overlay")
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
    @DisplayName("Test 5: Navigasi Gantt Charts di side menu overlay")
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
    @DisplayName("Test 6: Navigasi Team Planners di side menu overlay")
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
    @DisplayName("Test 7: Navigasi Boards di side menu overlay")
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
    @DisplayName("Test 8: Navigasi Meetings di side menu overlay")
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
    @DisplayName("Test 9: Navigasi News di side menu overlay")
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
    @DisplayName("Test 10: Navigasi Time and Costs di side menu overlay")
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
    @DisplayName("Test 11: Klik OpenProject Logo")
    @Description("Verify clicking OpenProject logo navigates to home page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Navigation")
    public void test11_ClickOpenProjectLogo() {
        navbarPage.clickOpenProjectLogo();
        assertTrue(navbarPage.getCurrentUrl().equals("https://safe.openproject.com/"), "Should navigate to home page when clicking logo");
    }

    @Test
    @Order(12)
    @DisplayName("Test 12: Search dengan query 'abc'")
    @Description("Verify searching with 'abc' and clicking 'In all projects'")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Navbar Search")
    public void test12_SearchNoResults() {
        navbarPage.typeInSearchBar("abc");
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed");
        
        navbarPage.clickInAllProjects();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=abc"), "Should navigate to search results page with query 'abc'");
    }

    @Test
    @Order(13)
    @DisplayName("Test 13: Search berdasarkan ID 70")
    @Description("Verify searching work package by ID 70")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Navbar Search")
    public void test13_SearchById() {
        navbarPage.typeInSearchBar("70");
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed");
        
        navbarPage.clickInAllProjects();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=70"), "Should navigate to search results page with query '70'");
        
        navbarPage.navigateBack();
        navbarPage.clickSearchBar();
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed after navigate back");
        
        navbarPage.clickSearchResultByWorkPackageId("70");
        assertTrue(navbarPage.getCurrentUrl().contains("/work_packages/70"), "Should navigate to work package 70");
    }

    @Test
    @Order(14)
    @DisplayName("Test 14: Search berdasarkan kategori 'art-2 design'")
    @Description("Verify searching by category 'art-2 design'")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Search")
    public void test14_SearchByCategory() {
        navbarPage.typeInSearchBar("art-2 design");
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed");
        
        navbarPage.clickInAllProjects();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=art-2"),   "Should navigate to search results page with query 'art-2 design'");
        
        navbarPage.navigateBack();
        navbarPage.clickSearchBar();
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed after navigate back");
        
        navbarPage.clickSearchResultByProject("ART-2 Design");
        assertTrue(navbarPage.getCurrentUrl().contains("/work_packages/"), "Should navigate to a work package");
    }

    @Test
    @Order(15)
    @DisplayName("Test 15: Search berdasarkan judul 'SSL certificate'")
    @Description("Verify searching by title 'SSL certificate'")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Search")
    public void test15_SearchByTitle() {
        navbarPage.typeInSearchBar("SSL certificate");
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed");
        
        navbarPage.clickInAllProjects();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=SSL+certificate"), 
            "Should navigate to search results page with query 'SSL certificate'");
        
        navbarPage.navigateBack();
        navbarPage.clickSearchBar();
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed after navigate back");
        
        navbarPage.clickSearchResultBySubject("SSL certificate");
        assertTrue(navbarPage.getCurrentUrl().contains("/work_packages/"), "Should navigate to a work package");
    }

    @Test
    @Order(16)
    @DisplayName("Test 16: Klik Help Button untuk membuka dan menutup help menu")
    @Description("Verify clicking Help button to open and close help menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test16_ClickHelpButton() {
        navbarPage.clickHelpButton();
        assertTrue(navbarPage.isHelpMenuDisplayed(), "Help menu should be displayed after clicking help button");
        
        navbarPage.clickHelpButton();
        assertFalse(navbarPage.isHelpMenuDisplayed(), "Help menu should be closed after clicking help button again");
    }

    @Test
    @Order(17)
    @DisplayName("Test 17: Klik Getting Started Link dan tutup dengan tombol X")
    @Description("Verify clicking Getting Started link opens popup and close with button 1 (X button)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navbar Help Menu")
    public void test17_ClickGettingStartedLinkAndCloseWithButton1() {
        // Test pertama - buka dan tutup dengan button 1
        navbarPage.clickHelpButton();
        assertTrue(navbarPage.isHelpMenuDisplayed(), "Help menu should be displayed");
        
        navbarPage.clickGettingStartedLink();
        assertTrue(navbarPage.isGettingStartedPopUpVideoDisplayed(), "Getting started popup video should be displayed");
        
        navbarPage.closeGettingStartedPopUpWithButton1();
        
        // Test kedua - buka lagi dan tutup dengan button 2
        navbarPage.clickHelpButton();
        assertTrue(navbarPage.isHelpMenuDisplayed(), "Help menu should be displayed again");
        
        navbarPage.clickGettingStartedLink();
        assertTrue(navbarPage.isGettingStartedPopUpVideoDisplayed(), "Getting started popup video should be displayed again");
        
        navbarPage.closeGettingStartedPopUpWithButton2();
    }

    @Test
    @Order(18)
    @DisplayName("Test 18: Help Menu - Upgrade to Enterprise Edition Link")
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
    @DisplayName("Test 19: Help Menu - User Guides Link")
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
    @DisplayName("Test 20: Help Menu - Videos Link")
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
    @DisplayName("Test 21: Help Menu - Shortcuts Link")
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
    @DisplayName("Test 22: Help Menu - Community Forum Link")
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
    @DisplayName("Test 23: Help Menu - Enterprise Support Link")
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
    @DisplayName("Test 24: Help Menu - Data Privacy and Security Policy Link")
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
    @DisplayName("Test 25: Help Menu - Digital Accessibility Link")
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
    @DisplayName("Test 26: Help Menu - OpenProject Website Link")
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
    @DisplayName("Test 27: Help Menu - Security Alerts Link")
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
    @DisplayName("Test 28: Help Menu - Newsletter Link")
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
    @DisplayName("Test 29: Help Menu - OpenProject Blog Link")
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
    @DisplayName("Test 30: Help Menu - Release Notes Link")
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
    @DisplayName("Test 31: Help Menu - Report a Bug Link")
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
    @DisplayName("Test 32: Help Menu - Development Roadmap Link")
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
    @DisplayName("Test 33: Help Menu - Add and Edit Translations Link")
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
    @DisplayName("Test 34: Help Menu - API Documentation Link")
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
}
