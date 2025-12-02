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
    
    // ╔════════════════════════════════════════════════════════════╗
    // ║                    HELPER METHODS                          ║
    // ╚════════════════════════════════════════════════════════════╝
    private void verifySidebarOverlayMenuNavigation(Runnable clickAction, String expectedUrl, String menuName) {
        homePage.openSideMenuOverlay();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open");
        
        clickAction.run();
        assertTrue(homePage.getCurrentUrl().contains(expectedUrl), 
            "Should navigate to " + menuName + " page");
        
        homePage.navigateBack();
        assertTrue(homePage.isSideMenuOverlayOpen(), 
            "Side menu overlay should be open after navigating back");
        
        homePage.closeSideMenuOverlay();
        assertFalse(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be closed");
    }
    
    private void verifyProjectsSelectionNavigation(Runnable clickAction, String expectedUrl, String projectName) {
        homePage.openProjectsSelection();
        assertTrue(homePage.isProjectsSelectionOpen(), "Projects selection should be open");

        clickAction.run();
        assertTrue(homePage.getCurrentUrl().contains(expectedUrl), 
            "Should navigate to " + projectName + " project page");
        
        homePage.navigateBack();
        assertTrue(homePage.isProjectsSelectionOpen(), 
            "Projects selection should be open after navigating back");
        
        homePage.closeProjectsSelection();
        assertFalse(homePage.isProjectsSelectionOpen(), "Projects selection should be closed");
    }

    // ╔════════════════════════════════════════════════════════════╗
    // ║                      TEST CASES                            ║
    // ╚════════════════════════════════════════════════════════════╝
    
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
    @Story("Sidebar Menu Overlay Navigation")
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
    @Story("Sidebar Menu Overlay Navigation")
    public void test03_NavigateToProjects() {
        verifySidebarOverlayMenuNavigation(
            () -> homePage.clickProjectsInSideMenuOverlay(),
            "/projects",
            "Projects"
        );
    }
    
    @Test
    @Order(4)
    @DisplayName("Test 4: Navigasi menu Work Packages di side menu overlay")
    @Description("Verify navigation to Work Packages page through side menu")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sidebar Menu Overlay Navigation")
    public void test04_NavigateToWorkPackages() {
        verifySidebarOverlayMenuNavigation(
            () -> homePage.clickWorkPackagesInSideMenuOverlay(),
            "/work_packages",
            "Work Packages"
        );
    }
    
    @Test
    @Order(5)
    @DisplayName("Test 5: Navigasi menu Gantt Charts di side menu overlay")
    @Description("Verify navigation to Gantt Charts page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Menu Overlay Navigation")
    public void test05_NavigateToGanttCharts() {
        verifySidebarOverlayMenuNavigation(
            () -> homePage.clickGanttChartsInSideMenuOverlay(),
            "/gantt",
            "Gantt Charts"
        );
    }
    
    @Test
    @Order(6)
    @DisplayName("Test 6: Navigasi menu Team Planners di side menu overlay")
    @Description("Verify navigation to Team Planners page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Menu Overlay Navigation")
    public void test06_NavigateToTeamPlanners() {
        verifySidebarOverlayMenuNavigation(
            () -> homePage.clickTeamPlannersInSideMenuOverlay(),
            "/team_planners",
            "Team Planners"
        );
    }
    
    @Test
    @Order(7)
    @DisplayName("Test 7: Navigasi menu Boards di side menu overlay")
    @Description("Verify navigation to Boards page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Menu Overlay Navigation")
    public void test07_NavigateToBoards() {
        verifySidebarOverlayMenuNavigation(
            () -> homePage.clickBoardsInSideMenuOverlay(),
            "/boards",
            "Boards"
        );
    }
    
    @Test
    @Order(8)
    @DisplayName("Test 8: Navigasi menu Meetings di side menu overlay")
    @Description("Verify navigation to Meetings page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Menu Overlay Navigation")
    public void test08_NavigateToMeetings() {
        verifySidebarOverlayMenuNavigation(
            () -> homePage.clickMeetingsInSideMenuOverlay(),
            "/meetings",
            "Meetings"
        );
    }
    
    @Test
    @Order(9)
    @DisplayName("Test 9: Navigasi menu News di side menu overlay")
    @Description("Verify navigation to News page through side menu")
    @Severity(SeverityLevel.MINOR)
    @Story("Sidebar Menu Overlay Navigation")
    public void test09_NavigateToNews() {
        verifySidebarOverlayMenuNavigation(
            () -> homePage.clickNewsInSideMenuOverlay(),
            "/news",
            "News"
        );
    }
    
    @Test
    @Order(10)
    @DisplayName("Test 10: Navigasi menu Time and Costs di side menu overlay")
    @Description("Verify navigation to Time and Costs page through side menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Menu Overlay Navigation")
    public void test10_NavigateToTimeAndCosts() {
        verifySidebarOverlayMenuNavigation(
            () -> homePage.clickTimeAndCostsInSideMenuOverlay(),
            "/cost_reports",
            "Time and Costs"
        );
    }

    @Test
    @Order(11)
    @DisplayName("Test 11: Collapse dan uncollapse sidebar")
    @Description("Verify that sidebar can be collapsed and uncollapsed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sidebar Functionality")
    public void test11_CollapseUncollapseSidebar() {
        homePage.collapseSidebar();
        assertTrue(homePage.isSidebarCollapsed(), "Sidebar should be collapsed");
        
        homePage.uncollapseSidebar();
        assertFalse(homePage.isSidebarCollapsed(), "Sidebar should be uncollapsed");
    }

    @Test
    @Order(12)
    @DisplayName("Buka dan Tutup Projects Selection di sidebar")
    @Description("Verify that projects selection in sidebar can be opened and closed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Selection Functionality")
    public void test12_OpenCloseProjectsSelection() {
        homePage.openProjectsSelection();
        assertTrue(homePage.isProjectsSelectionOpen(), "Projects selection should be open");

        homePage.closeProjectsSelection();
        assertFalse(homePage.isProjectsSelectionOpen(), "Projects selection should be closed");
    }

    @Test
    @Order(13)
    @DisplayName("Test 13: Navigasi Other Projects di projects selection")
    @Description("Verify navigation to Other Projects through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test13_NavigateToOtherProjects() {
        verifyProjectsSelectionNavigation(
            () -> homePage.clickOtherProjectsSelection(),
            "/projects/other-projects",
            "Other Projects"
        );
    }
}