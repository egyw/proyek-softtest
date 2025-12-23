package com.proyek_softtest.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.config.TestData;
import com.proyek_softtest.pages.BasicSidebarPage;

import io.qameta.allure.*;

@Epic("Basic Sidebar Page")
@Feature("Basic Sidebar and Projects Selection Functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasicSidebarTest extends BaseTest {
    
    private BasicSidebarPage basicSidebarPage;
    
    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting BasicSidebarTest...");
    }
    
    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        basicSidebarPage = new BasicSidebarPage(driver);
    }
    
    // ╔════════════════════════════════════════════════════════════╗
    // ║                    HELPER METHODS                          ║
    // ╚════════════════════════════════════════════════════════════╝
    
    private void verifyProjectsSelectionNavigation(Runnable clickAction, String expectedUrl, String projectName) {
        basicSidebarPage.openProjectsSelection();
        assertTrue(basicSidebarPage.isProjectsSelectionOpen(), "Projects selection should be open");

        clickAction.run();
        assertTrue(basicSidebarPage.getCurrentUrl().contains(expectedUrl),"Should navigate to " + projectName + " project page");
        captureScreenshotWithTitle("Navigate to " + projectName + " Project");

        basicSidebarPage.navigateBack();
        assertTrue(basicSidebarPage.isProjectsSelectionOpen(), "Projects selection should be open after navigating back");

        basicSidebarPage.closeProjectsSelection();
        assertTrue(basicSidebarPage.isProjectsSelectionClosed(), "Projects selection should be closed");
    }

    // untuk sidebar dengan kemungkinan redirect ke 1 url saja
    private void verifySidebarNavigation(Runnable clickAction, String expectedUrl, String menuName) {
        clickAction.run();
        assertTrue(basicSidebarPage.getCurrentUrl().contains(expectedUrl), "Should navigate to " + menuName + " page");
        captureScreenshotWithTitle("Navigate to " + menuName + " Page");

        basicSidebarPage.navigateBack();
        assertTrue(basicSidebarPage.getCurrentUrl().contains("/"), "Should be back at home page after navigating back");
    }

    // untuk sidebar dengan kemungkinan redirect ke beberapa url
    private void verifySidebarNavigation(Runnable clickAction, String menuName, String... expectedUrls) {
        clickAction.run();
        String currentUrl = basicSidebarPage.getCurrentUrl();
        boolean urlMatched = false;
        for (String url : expectedUrls) {
            if (currentUrl.contains(url)) {
                urlMatched = true;
                break;
            }
        }
        assertTrue(urlMatched, "Should navigate to " + menuName + " page (one of: " + String.join(", ", expectedUrls) + ")");
        captureScreenshotWithTitle("Navigate to " + menuName + " Page");
        
        basicSidebarPage.navigateBack();
        assertTrue(basicSidebarPage.getCurrentUrl().contains("/"), "Should be back at home page after navigating back");
    }

    // ╔════════════════════════════════════════════════════════════╗
    // ║                      TEST CASES                            ║
    // ╚════════════════════════════════════════════════════════════╝
    
    @Test
    @Order(1)
    @DisplayName("Test 1: Collapse dan uncollapse sidebar")
    @Description("Verify that sidebar can be collapsed and uncollapsed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sidebar Functionality")
    public void test1_CollapseUncollapseSidebar() {
        basicSidebarPage.collapseSidebar();
        assertTrue(basicSidebarPage.isSidebarCollapsed(), "Sidebar should be collapsed");
        captureScreenshotWithTitle("Sidebar Collapsed");

        basicSidebarPage.uncollapseSidebar();
        assertFalse(basicSidebarPage.isSidebarCollapsed(), "Sidebar should be uncollapsed");
        captureScreenshotWithTitle("Sidebar Uncollapsed");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Buka dan Tutup Projects Selection di sidebar")
    @Description("Verify that projects selection in sidebar can be opened and closed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Selection Functionality")
    public void test2_OpenCloseProjectsSelection() {
        basicSidebarPage.openProjectsSelection();
        assertTrue(basicSidebarPage.isProjectsSelectionOpen(), "Projects selection should be open");
        captureScreenshotWithTitle("Projects Selection Open");

        basicSidebarPage.closeProjectsSelection();
        assertTrue(basicSidebarPage.isProjectsSelectionClosed(), "Projects selection should be closed");
        captureScreenshotWithTitle("Projects Selection Closed");
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Search projects di projects selection")
    @Description("Verify search functionality in projects selection with valid and invalid queries")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Selection Search")
    public void test3_SearchProjectsSelection() {
        basicSidebarPage.openProjectsSelection();
        assertTrue(basicSidebarPage.isProjectsSelectionOpen(), "Projects selection should be open");
        
        // test invalid search
        String invalidKeyword = TestData.getSidebarInvalidSearchKeyword();
        basicSidebarPage.searchInProjectsSelection(invalidKeyword);
        assertTrue(basicSidebarPage.isNoResultsMessageDisplayed(), "No results message should be displayed for '" + invalidKeyword + "'");
        captureScreenshotWithTitle("No Results for Invalid Search: " + invalidKeyword);

        // test whole words
        String fullnameKeyword = TestData.getSidebarValidSearchFullname();
        String expectedResult = TestData.getSidebarExpectedSearchResult();
        basicSidebarPage.searchInProjectsSelection(fullnameKeyword);
        assertTrue(basicSidebarPage.isSearchResultContains(expectedResult) || basicSidebarPage.isSearchResultContains(expectedResult.toLowerCase()), 
                "Search results should contain '" + expectedResult + "' when searching '" + fullnameKeyword + "'");
        captureScreenshotWithTitle("Search Results for Fullname: " + fullnameKeyword);

        // test partial words
        String partialKeyword = TestData.getSidebarValidSearchPartial();
        basicSidebarPage.searchInProjectsSelection(partialKeyword);
        assertTrue(basicSidebarPage.isSearchResultContains(expectedResult) || basicSidebarPage.isSearchResultContains(expectedResult.toLowerCase()),
                "Search results should contain '" + expectedResult + "' when searching '" + partialKeyword + "'");
        captureScreenshotWithTitle("Search Results for Partial: " + partialKeyword);

        basicSidebarPage.clickScrumProjectSelection();
        assertTrue(basicSidebarPage.getCurrentUrl().contains("/projects/your-scrum-project"),
                "Should navigate to Scrum Project page");
        captureScreenshotWithTitle("Navigate to Scrum Project");

        basicSidebarPage.navigateBack();
        assertTrue(basicSidebarPage.isProjectsSelectionOpen(), "Projects selection should be open after navigating back");
        
        basicSidebarPage.closeProjectsSelection();
        assertTrue(basicSidebarPage.isProjectsSelectionClosed(), "Projects selection should be closed");
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Navigasi Other Projects di projects selection")
    @Description("Verify navigation to Other Projects through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test4_NavigateToOtherProjects() {
        verifyProjectsSelectionNavigation(
            () -> basicSidebarPage.clickOtherProjectsSelection(),
            "/projects/other-projects",
            "Other Projects"
        );
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Navigasi Demo Project di projects selection")
    @Description("Verify navigation to Demo Project through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test5_NavigateToDemoProject() {
        verifyProjectsSelectionNavigation(
            () -> basicSidebarPage.clickDemoProjectSelection(),
            "/projects/demo-project",
            "Demo Project"
        );
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Navigasi Scrum Project di projects selection")
    @Description("Verify navigation to Scrum Project through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test6_NavigateToScrumProject() {
        verifyProjectsSelectionNavigation(
            () -> basicSidebarPage.clickScrumProjectSelection(),
            "/projects/your-scrum-project",
            "Scrum Project"
        );
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Navigasi SAFe Solution Train 1 di projects selection")
    @Description("Verify navigation to SAFe Solution Train 1 through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test7_NavigateToSafeSolutionTrain1() {
        verifyProjectsSelectionNavigation(
            () -> basicSidebarPage.clickSafeSolutionTrain1Selection(),
            "/projects/safe-solution-train-1",
            "SAFe Solution Train 1"
        );
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Navigasi ART 1 Engineering di projects selection")
    @Description("Verify navigation to ART 1 Engineering through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test8_NavigateToArt1Engineering() {
        verifyProjectsSelectionNavigation(
            () -> basicSidebarPage.clickArt1EngineeringSelection(),
            "/projects/art-0-test-release-train",
            "ART 1 Engineering"
        );
    }

    @Test
    @Order(9)
    @DisplayName("Test 9: Navigasi Blue Team di projects selection")
    @Description("Verify navigation to Blue Team through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test9_NavigateToBlueTeam() {
        verifyProjectsSelectionNavigation(
            () -> basicSidebarPage.clickBlueTeamSelection(),
            "/projects/blue-team",
            "Blue Team"
        );
    }

    @Test
    @Order(10)
    @DisplayName("Test 10: Navigasi Red Team di projects selection")
    @Description("Verify navigation to Red Team through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test10_NavigateToRedTeam() {
        verifyProjectsSelectionNavigation(
            () -> basicSidebarPage.clickRedTeamSelection(),
            "/projects/red-team",
            "Red Team"
        );
    }

    @Test
    @Order(11)
    @DisplayName("Test 11: Navigasi ART 2 Design di projects selection")
    @Description("Verify navigation to ART 2 Design through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test11_NavigateToArt2Design() {
        verifyProjectsSelectionNavigation(
            () -> basicSidebarPage.clickArt2DesignSelection(),
            "/projects/art-2-design",
            "ART 2 Design"
        );
    }

    @Test
    @Order(12)
    @DisplayName("Test 12: Navigasi Project Lists di projects selection")
    @Description("Verify navigation to Project Lists through projects selection")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Selection Navigation")
    public void test12_NavigateToProjectLists() {
        verifyProjectsSelectionNavigation(
                () -> basicSidebarPage.clickProjectListsSelection(),
                "/projects",
                "Project Lists");
    }

    @Test
    @Order(13)
    @DisplayName("Test 13: Navigasi Home di sidebar")
    @Description("Verify navigation to Home through sidebar (refresh)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test13_NavigateToHomeInSidebar() {
        basicSidebarPage.clickHomeInSidebar();
        assertTrue(basicSidebarPage.getCurrentUrl().equals("https://safe.openproject.com/"), "Should navigate to home page (refresh)");
        captureScreenshotWithTitle("Navigate to Home Page");
    }

    @Test
    @Order(14)
    @DisplayName("Test 14: Navigasi My Page di sidebar")
    @Description("Verify navigation to My Page through sidebar (redirects to login if not authenticated)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test14_NavigateToMyPageInSidebar() {
        verifySidebarNavigation(
            () -> basicSidebarPage.clickMyPageInSidebar(),
            "My Page",
            "/my/page", "/login"
        );
    }

    @Test
    @Order(15)
    @DisplayName("Test 15: Navigasi Projects di sidebar")
    @Description("Verify navigation to Projects through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test15_NavigateToProjectsInSidebar() {
        verifySidebarNavigation(
            () -> basicSidebarPage.clickProjectsInSidebar(),
            "/projects",
            "Projects"
        );
    }

    @Test
    @Order(16)
    @DisplayName("Test 16: Navigasi Work Packages di sidebar")
    @Description("Verify navigation to Work Packages through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test16_NavigateToWorkPackagesInSidebar() {
        verifySidebarNavigation(
            () -> basicSidebarPage.clickWorkPackagesInSidebar(),
            "/work_packages",
            "Work Packages"
        );
    }

    @Test
    @Order(17)
    @DisplayName("Test 17: Navigasi Gantt Charts di sidebar")
    @Description("Verify navigation to Gantt Charts through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test17_NavigateToGanttChartsInSidebar() {
        verifySidebarNavigation(
            () -> basicSidebarPage.clickGanttChartsInSidebar(),
            "/gantt",
            "Gantt Charts"
        );
    }

    @Test
    @Order(18)
    @DisplayName("Test 18: Navigasi Team Planners di sidebar")
    @Description("Verify navigation to Team Planners through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test18_NavigateToTeamPlannersInSidebar() {
        verifySidebarNavigation(
            () -> basicSidebarPage.clickTeamPlannersInSidebar(),
            "/team_planners",
            "Team Planners"
        );
    }

    @Test
    @Order(19)
    @DisplayName("Test 19: Navigasi Boards di sidebar")
    @Description("Verify navigation to Boards through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test19_NavigateToBoardsInSidebar() {
        verifySidebarNavigation(
            () -> basicSidebarPage.clickBoardsInSidebar(),
            "/boards",
            "Boards"
        );
    }

    @Test
    @Order(20)
    @DisplayName("Test 20: Navigasi Meetings di sidebar")
    @Description("Verify navigation to Meetings through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test20_NavigateToMeetingsInSidebar() {
        verifySidebarNavigation(
            () -> basicSidebarPage.clickMeetingsInSidebar(),
            "/meetings",
            "Meetings"
        );
    }

    @Test
    @Order(21)
    @DisplayName("Test 21: Navigasi News di sidebar")
    @Description("Verify navigation to News through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test21_NavigateToNewsInSidebar() {
        verifySidebarNavigation(
            () -> basicSidebarPage.clickNewsInSidebar(),
            "/news",
            "News"
        );
    }

    @Test
    @Order(22)
    @DisplayName("Test 22: Navigasi Time and Costs di sidebar")
    @Description("Verify navigation to Time and Costs through sidebar")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test22_NavigateToTimeAndCostsInSidebar() {
        verifySidebarNavigation(
            () -> basicSidebarPage.clickTimeAndCostsInSidebar(),
            "/cost_reports",
            "Time and Costs"
        );
    }
}
