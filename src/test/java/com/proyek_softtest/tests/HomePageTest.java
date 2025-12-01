package com.proyek_softtest.tests;

import org.junit.jupiter.api.*;

import io.qameta.allure.*;
import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.HomePage;

import static org.junit.jupiter.api.Assertions.*;

@Epic("OpenProject Web Testing")
@Feature("Homepage Navigation")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest extends BaseTest {
    
    private static HomePage homePage;
    
    @BeforeAll
    public static void setUpHomePage() {
        homePage = new HomePage(driver);
    }
    
    @Test
    @Order(1)
    @DisplayName("Test 1: Buka dan tutup side menu overlay")
    @Description("Verify that side menu overlay can be opened and closed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Side Menu Overlay Functionality")
    public void test01_OpenCloseSideMenuOverlay() {
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");
        
        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }
    
    @Test
    @Order(2)
    @DisplayName("Test 2: Navigasi menu Home di side menu overlay")
    @Description("Verify navigation to Home page through side menu")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Side Menu Overlay Preparation")
    public void test02_NavigateToHome() {
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");

        homePage.clickHomeInSideMenuOverlay();
        assertTrue(homePage.getCurrentUrl().endsWith("/"), "Should navigate to Home page");
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Navigasi menu Projects di side menu overlay")
    @Description("Verify navigation to Projects page through side menu")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Menu Navigation")
    public void test03_NavigateToProjects() {
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");

        homePage.clickProjectsInSideMenuOverlay();
        assertTrue(homePage.getCurrentUrl().contains("/projects"), "Should navigate to Projects page");

        homePage.navigateBack();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open after navigating back");

        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }
    
    @Test
    @Order(4)
    @DisplayName("Test 4: Navigasi menu Work Packages di side menu overlay")
    @Description("Verify navigation to Work Packages page through side menu")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Menu Navigation")
    public void test04_NavigateToWorkPackages() {
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");

        homePage.clickWorkPackagesInSideMenuOverlay();
        assertTrue(homePage.getCurrentUrl().contains("/work_packages"), "Should navigate to Work Packages page");

        homePage.navigateBack();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open after navigating back");

        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }
    
    @Test
    @Order(5)
    @DisplayName("Test 5: Navigasi menu Gantt Charts di side menu overlay")
    @Description("Verify navigation to Gantt Charts page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Menu Navigation")
    public void test05_NavigateToGanttCharts() {
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");

        homePage.clickGanttChartsInSideMenuOverlay();
        assertTrue(homePage.getCurrentUrl().contains("/gantt"), "Should navigate to Gantt Charts page");

        homePage.navigateBack();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open after navigating back");

        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }
    
    @Test
    @Order(6)
    @DisplayName("Test 6: Navigasi menu Team Planners di side menu overlay")
    @Description("Verify navigation to Team Planners page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Menu Navigation")
    public void test06_NavigateToTeamPlanners() {
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");

        homePage.clickTeamPlannersInSideMenuOverlay();
        assertTrue(homePage.getCurrentUrl().contains("/team_planners"), "Should navigate to Team Planners page");

        homePage.navigateBack();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open after navigating back");

        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }
    
    @Test
    @Order(7)
    @DisplayName("Test 7: Navigasi menu Boards di side menu overlay")
    @Description("Verify navigation to Boards page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Menu Navigation")
    public void test07_NavigateToBoards() {
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");

        homePage.clickBoardsInSideMenuOverlay();
        assertTrue(homePage.getCurrentUrl().contains("/boards"), "Should navigate to Boards page");

        homePage.navigateBack();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open after navigating back");

        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }
    
    @Test
    @Order(8)
    @DisplayName("Test 8: Navigasi menu Meetings di side menu overlay")
    @Description("Verify navigation to Meetings page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Menu Navigation")
    public void test08_NavigateToMeetings() {
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");

        homePage.clickMeetingsInSideMenuOverlay();
        assertTrue(homePage.getCurrentUrl().contains("/meetings"), "Should navigate to Meetings page");

        homePage.navigateBack();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open after navigating back");

        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }
    
    @Test
    @Order(9)
    @DisplayName("Test 9: Navigasi menu News di side menu overlay")
    @Description("Verify navigation to News page through side menu")
    @Severity(SeverityLevel.MINOR)
    @Story("Menu Navigation")
    public void test09_NavigateToNews() {
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");

        homePage.clickNewsInSideMenuOverlay();
        assertTrue(homePage.getCurrentUrl().contains("/news"), "Should navigate to News page");

        homePage.navigateBack();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open after navigating back");

        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }
    
    @Test
    @Order(10)
    @DisplayName("Test 10: Navigasi menu Time and Costs di side menu overlay")
    @Description("Verify navigation to Time and Costs page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Menu Navigation")
    public void test10_NavigateToTimeAndCosts() {
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");
        
        homePage.clickTimeAndCostsInSideMenuOverlay();
        assertTrue(homePage.getCurrentUrl().contains("/cost_reports"), "Should navigate to Time and Costs page");

        homePage.navigateBack();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open after navigating back");
        
        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }
}
