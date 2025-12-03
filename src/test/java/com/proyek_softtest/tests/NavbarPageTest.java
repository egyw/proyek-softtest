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
        assertTrue(navbarPage.getCurrentUrl().equals("https://safe.openproject.com/"), 
            "Should navigate to home page when clicking logo");
    }

    @Test
    @Order(12)
    @DisplayName("Test 12: Search dengan query tidak ditemukan")
    @Description("Verify searching with 'abc' shows no results message and View all results button works")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Navbar Search")
    public void test12_SearchNoResults() {
        navbarPage.typeInSearchBar("abc");
        
        assertTrue(navbarPage.isSearchDropdownDisplayed(), "Search dropdown should be displayed");
        assertTrue(navbarPage.isNoResultsMessageDisplayed(), "No results message should be displayed for 'abc'");
        
        navbarPage.clickViewAllResultsButton();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=abc&scope=all&filter=work_packages"), 
            "Should navigate to search results page with query 'abc'");
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
        
        navbarPage.clickViewAllResultsButton();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=70"), 
            "Should navigate to search results page with query '70'");
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
        
        navbarPage.clickViewAllResultsButton();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=art-2+design") || 
                   navbarPage.getCurrentUrl().contains("/search?q=art-2%20design"), 
            "Should navigate to search results page with query 'art-2 design'");
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
        
        navbarPage.clickViewAllResultsButton();
        assertTrue(navbarPage.getCurrentUrl().contains("/search?q=SSL+certificate") || 
                   navbarPage.getCurrentUrl().contains("/search?q=SSL%20certificate"), 
            "Should navigate to search results page with query 'SSL certificate'");
    }
}
