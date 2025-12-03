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
        assertTrue(homePage.getCurrentUrl().contains(expectedUrl), "Should navigate to " + menuName + " page");
        
        homePage.navigateBack();
        assertTrue(homePage.isSideMenuOverlayOpen(), "Side menu overlay should be open after navigating back");
        
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
        assertTrue(homePage.isProjectsSelectionOpen(), "Projects selection should be open after navigating back");

        homePage.closeProjectsSelection();
        assertTrue(homePage.isProjectsSelectionClosed(), "Projects selection should be closed");
    }

    // untuk sidebar dengan kemungkinan redirect ke 1 url saja
    private void verifySidebarNavigation(Runnable clickAction, String expectedUrl, String menuName) {
        clickAction.run();
        assertTrue(homePage.getCurrentUrl().contains(expectedUrl), "Should navigate to " + menuName + " page");

        homePage.navigateBack();
        assertTrue(homePage.getCurrentUrl().contains("/"), "Should be back at home page after navigating back");
    }

    // untuk sidebar dengan kemungkinan redirect ke beberapa url
    private void verifySidebarNavigation(Runnable clickAction, String menuName, String... expectedUrls) {
        clickAction.run();
        String currentUrl = homePage.getCurrentUrl();
        boolean urlMatched = false;
        for (String url : expectedUrls) {
            if (currentUrl.contains(url)) {
                urlMatched = true;
                break;
            }
        }
        assertTrue(urlMatched, "Should navigate to " + menuName + " page (one of: " + String.join(", ", expectedUrls) + ")");
        
        homePage.navigateBack();
        assertTrue(homePage.getCurrentUrl().contains("/"), "Should be back at home page after navigating back");
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
        System.out.println(homePage.isProjectsSelectionClosed());

        assertTrue(homePage.isProjectsSelectionClosed(), "Projects selection should be closed");
    }

    @Test
    @Order(13)
    @DisplayName("Test 13: Search projects di projects selection")
    @Description("Verify search functionality in projects selection with valid and invalid queries")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Selection Search")
    public void test13_SearchProjectsSelection() {
        homePage.openProjectsSelection();
        assertTrue(homePage.isProjectsSelectionOpen(), "Projects selection should be open");
        
        // test invalid search
        homePage.searchInProjectsSelection("abc");
        assertTrue(homePage.isNoResultsMessageDisplayed(), "No results message should be displayed for 'abc'");
            
        // test whole words
        homePage.searchInProjectsSelection("scrum project");
        assertTrue(homePage.isSearchResultContains("Scrum") || homePage.isSearchResultContains("scrum"), "Search results should contain 'Scrum' when searching 'scrum project'");
        
        // test partial words
        homePage.searchInProjectsSelection("rum");
        assertTrue(homePage.isSearchResultContains("Scrum") || homePage.isSearchResultContains("scrum"),
                "Search results should contain 'Scrum' when searching 'rum'");
        
        homePage.clickScrumProjectSelection();
        assertTrue(homePage.getCurrentUrl().contains("/projects/your-scrum-project"),
                "Should navigate to Scrum Project page");
        
        homePage.navigateBack();
        assertTrue(homePage.isProjectsSelectionOpen(), "Projects selection should be open after navigating back");
        
        homePage.closeProjectsSelection();
        assertTrue(homePage.isProjectsSelectionClosed(), "Projects selection should be closed");
    }

    @Test
    @Order(14)
    @DisplayName("Test 14: Navigasi Other Projects di projects selection")
    @Description("Verify navigation to Other Projects through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test14_NavigateToOtherProjects() {
        verifyProjectsSelectionNavigation(
            () -> homePage.clickOtherProjectsSelection(),
            "/projects/other-projects",
            "Other Projects"
        );
    }

    @Test
    @Order(15)
    @DisplayName("Test 15: Navigasi Demo Project di projects selection")
    @Description("Verify navigation to Demo Project through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test15_NavigateToDemoProject() {
        verifyProjectsSelectionNavigation(
            () -> homePage.clickDemoProjectSelection(),
            "/projects/demo-project",
            "Demo Project"
        );
    }

    @Test
    @Order(16)
    @DisplayName("Test 16: Navigasi Scrum Project di projects selection")
    @Description("Verify navigation to Scrum Project through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test16_NavigateToScrumProject() {
        verifyProjectsSelectionNavigation(
            () -> homePage.clickScrumProjectSelection(),
            "/projects/your-scrum-project",
            "Scrum Project"
        );
    }

    @Test
    @Order(17)
    @DisplayName("Test 17: Navigasi SAFe Solution Train 1 di projects selection")
    @Description("Verify navigation to SAFe Solution Train 1 through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test17_NavigateToSafeSolutionTrain1() {
        verifyProjectsSelectionNavigation(
            () -> homePage.clickSafeSolutionTrain1Selection(),
            "/projects/safe-solution-train-1",
            "SAFe Solution Train 1"
        );
    }

    @Test
    @Order(18)
    @DisplayName("Test 18: Navigasi ART 1 Engineering di projects selection")
    @Description("Verify navigation to ART 1 Engineering through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test18_NavigateToArt1Engineering() {
        verifyProjectsSelectionNavigation(
            () -> homePage.clickArt1EngineeringSelection(),
            "/projects/art-0-test-release-train",
            "ART 1 Engineering"
        );
    }

    @Test
    @Order(19)
    @DisplayName("Test 19: Navigasi Blue Team di projects selection")
    @Description("Verify navigation to Blue Team through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test19_NavigateToBlueTeam() {
        verifyProjectsSelectionNavigation(
            () -> homePage.clickBlueTeamSelection(),
            "/projects/blue-team",
            "Blue Team"
        );
    }

    @Test
    @Order(20)
    @DisplayName("Test 20: Navigasi Red Team di projects selection")
    @Description("Verify navigation to Red Team through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test20_NavigateToRedTeam() {
        verifyProjectsSelectionNavigation(
            () -> homePage.clickRedTeamSelection(),
            "/projects/red-team",
            "Red Team"
        );
    }

    @Test
    @Order(21)
    @DisplayName("Test 21: Navigasi ART 2 Design di projects selection")
    @Description("Verify navigation to ART 2 Design through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test21_NavigateToArt2Design() {
        verifyProjectsSelectionNavigation(
            () -> homePage.clickArt2DesignSelection(),
            "/projects/art-2-design",
            "ART 2 Design"
        );
    }

    @Test
    @Order(22)
    @DisplayName("Test 22: Navigasi Project Lists di projects selection")
    @Description("Verify navigation to Project Lists through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test22_NavigateToProjectLists() {
        verifyProjectsSelectionNavigation(
                () -> homePage.clickProjectListsSelection(),
                "/projects",
                "Project Lists");
    }

    @Test
    @Order(23)
    @DisplayName("Test 23: Navigasi Home di sidebar")
    @Description("Verify navigation to Home through sidebar (refresh)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test23_NavigateToHomeInSidebar() {
        homePage.clickHomeInSidebar();
        assertTrue(homePage.getCurrentUrl().equals("https://safe.openproject.com/"), "Should navigate to home page (refresh)");
    }

    @Test
    @Order(24)
    @DisplayName("Test 24: Navigasi My Page di sidebar")
    @Description("Verify navigation to My Page through sidebar (redirects to login if not authenticated)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test24_NavigateToMyPageInSidebar() {
        verifySidebarNavigation(
            () -> homePage.clickMyPageInSidebar(),
            "My Page",
            "/my/page", "/login"
        );
    }

    @Test
    @Order(25)
    @DisplayName("Test 25: Navigasi Projects di sidebar")
    @Description("Verify navigation to Projects through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test25_NavigateToProjectsInSidebar() {
        verifySidebarNavigation(
            () -> homePage.clickProjectsInSidebar(),
            "/projects",
            "Projects"
        );
    }

    @Test
    @Order(26)
    @DisplayName("Test 26: Navigasi Work Packages di sidebar")
    @Description("Verify navigation to Work Packages through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test26_NavigateToWorkPackagesInSidebar() {
        verifySidebarNavigation(
            () -> homePage.clickWorkPackagesInSidebar(),
            "/work_packages",
            "Work Packages"
        );
    }

    @Test
    @Order(27)
    @DisplayName("Test 27: Navigasi Gantt Charts di sidebar")
    @Description("Verify navigation to Gantt Charts through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test27_NavigateToGanttChartsInSidebar() {
        verifySidebarNavigation(
            () -> homePage.clickGanttChartsInSidebar(),
            "/gantt",
            "Gantt Charts"
        );
    }

    @Test
    @Order(28)
    @DisplayName("Test 28: Navigasi Team Planners di sidebar")
    @Description("Verify navigation to Team Planners through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test28_NavigateToTeamPlannersInSidebar() {
        verifySidebarNavigation(
            () -> homePage.clickTeamPlannersInSidebar(),
            "/team_planners",
            "Team Planners"
        );
    }

    @Test
    @Order(29)
    @DisplayName("Test 29: Navigasi Boards di sidebar")
    @Description("Verify navigation to Boards through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test29_NavigateToBoardsInSidebar() {
        verifySidebarNavigation(
            () -> homePage.clickBoardsInSidebar(),
            "/boards",
            "Boards"
        );
    }

    @Test
    @Order(30)
    @DisplayName("Test 30: Navigasi Meetings di sidebar")
    @Description("Verify navigation to Meetings through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test30_NavigateToMeetingsInSidebar() {
        verifySidebarNavigation(
            () -> homePage.clickMeetingsInSidebar(),
            "/meetings",
            "Meetings"
        );
    }

    @Test
    @Order(31)
    @DisplayName("Test 31: Navigasi News di sidebar")
    @Description("Verify navigation to News through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test31_NavigateToNewsInSidebar() {
        verifySidebarNavigation(
            () -> homePage.clickNewsInSidebar(),
            "/news",
            "News"
        );
    }

    @Test
    @Order(32)
    @DisplayName("Test 32: Navigasi Time and Costs di sidebar")
    @Description("Verify navigation to Time and Costs through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test32_NavigateToTimeAndCostsInSidebar() {
        verifySidebarNavigation(
            () -> homePage.clickTimeAndCostsInSidebar(),
            "/cost_reports",
            "Time and Costs"
        );
    }
    
    
}