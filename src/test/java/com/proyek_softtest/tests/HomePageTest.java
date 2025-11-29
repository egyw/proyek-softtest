package com.proyek_softtest.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.*;
import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.HomePage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

@Epic("OpenProject Web Testing")
@Feature("Homepage Navigation")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest extends BaseTest {
    
    private static HomePage homePage;
    
    @BeforeAll
    public static void setUpHomePage() {
        homePage = new HomePage(driver);
        System.out.println("HomePage initialized\n");
    }
    
    @Test
    @Order(1)
    @DisplayName("Test 1: Buka dan tutup side menu overlay")
    @Description("Verify that side menu overlay can be opened and closed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Side Menu Overlay Functionality")
    public void test01_OpenCloseSideMenuOverlay() {
        logInfo("Opening side menu overlay");
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");
        logPass("Side menu overlay opened successfully");
        
        logInfo("Closing side menu overlay");
        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
        logPass("Side menu overlay closed successfully");
    }
    
    @Test
    @Order(2)
    @DisplayName("Test 2: Navigasi menu Home di side menu overlay")
    @Description("Verify navigation to Home page through side menu")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Side Menu Overlay Preparation")
    public void test02_NavigateToHome() {
        logInfo("Opening side menu overlay for navigation");
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");
        logPass("Side menu overlay opened successfully");

        logInfo("Testing navigation to Home menu");
        homePage.clickHomeInSideMenuOverlay();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.endsWith("/"), "Should navigate to Home page");
        logPass("Successfully navigated to Home menu");
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Navigasi menu Projects di side menu overlay")
    @Description("Verify navigation to Projects page through side menu")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Menu Navigation")
    public void test03_NavigateToProjects() {
        logInfo("Opening side menu overlay for navigation");
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");
        logPass("Side menu overlay opened successfully");

        logInfo("Testing navigation to Projects menu");
        homePage.clickProjectsInSideMenuOverlay();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/projects"), "Should navigate to Projects page");
        logPass("Successfully navigated to Projects menu");
    }
    
    @Test
    @Order(4)
    @DisplayName("Test 4: Navigasi menu Work Packages di side menu overlay")
    @Description("Verify navigation to Work Packages page through side menu")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Menu Navigation")
    public void test04_NavigateToWorkPackages() {
        logInfo("Testing navigation to Work Packages menu");
        homePage.clickWorkPackagesInSideMenuOverlay();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/work_packages"), "Should navigate to Work Packages page");
        logPass("Successfully navigated to Work Packages menu");
    }
    
    @Test
    @Order(5)
    @DisplayName("Test 5: Navigasi menu Gantt Charts di side menu overlay")
    @Description("Verify navigation to Gantt Charts page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Menu Navigation")
    public void test05_NavigateToGanttCharts() {
        logInfo("Testing navigation to Gantt Charts menu");    
        homePage.clickGanntChartsInSideMenuOverlay();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/gantt"), "Should navigate to Gantt Charts page");
        logPass("Successfully navigated to Gantt Charts menu"); 
    }
    
    @Test
    @Order(6)
    @DisplayName("Test 6: Navigasi menu Team Planners di side menu overlay")
    @Description("Verify navigation to Team Planners page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Menu Navigation")
    public void test06_NavigateToTeamPlanners() {
        logInfo("Testing navigation to Team Planners menu");
        homePage.clickTeamPlannersInSideMenuOverlay();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/team_planners"), "Should navigate to Team Planners page");
        logPass("Successfully navigated to Team Planners menu");        
    }
    
    @Test
    @Order(7)
    @DisplayName("Test 7: Navigasi menu Boards di side menu overlay")
    @Description("Verify navigation to Boards page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Menu Navigation")
    public void test07_NavigateToBoards() {
        logInfo("Testing navigation to Boards menu");
        homePage.clickBoardsInSideMenuOverlay();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/boards"), "Should navigate to Boards page");
        logPass("Successfully navigated to Boards menu");
    }
    
    @Test
    @Order(8)
    @DisplayName("Test 8: Navigasi menu Meetings di side menu overlay")
    @Description("Verify navigation to Meetings page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Menu Navigation")
    public void test08_NavigateToMeetings() {
        logInfo("Testing navigation to Meetings menu");
        homePage.clickMeetingsInSideMenuOverlay();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings"), "Should navigate to Meetings page");
        logPass("Successfully navigated to Meetings menu");
    }
    
    @Test
    @Order(9)
    @DisplayName("Test 9: Navigasi menu News di side menu overlay")
    @Description("Verify navigation to News page through side menu")
    @Severity(SeverityLevel.MINOR)
    @Story("Menu Navigation")
    public void test09_NavigateToNews() {
        logInfo("Testing navigation to News menu");
        homePage.clickNewsInSideMenuOverlay();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/news"), "Should navigate to News page");
        logPass("Successfully navigated to News menu");
    }
    
    @Test
    @Order(10)
    @DisplayName("Test 10: Navigasi menu Time and Costs di side menu overlay")
    @Description("Verify navigation to Time and Costs page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Menu Navigation")
    public void test10_NavigateToTimeAndCosts() {
        logInfo("Testing navigation to Time and Costs menu");
        homePage.clickTimeAndCostsInSideMenuOverlay();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/cost_reports"), "Should navigate to Time and Costs page");
        logPass("Successfully navigated to Time and Costs menu");
        
        logInfo("Closing sidemenu overlay after navigation tests");
        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
        logPass("Side menu overlay closed successfully after navigation tests");
    }
}
