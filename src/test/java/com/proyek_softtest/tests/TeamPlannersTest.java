package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.TeamPlannersPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Team Planners Page")
@Feature("Team Planners Functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeamPlannersTest extends BaseTest {

    private TeamPlannersPage teamPlannersPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting TeamPlannersTest...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        teamPlannersPage = new TeamPlannersPage(driver);
        
        // Navigate to team planners page before each test
        driver.get("https://safe.openproject.com/team_planners");
    }

    // ╔════════════════════════════════════════════════════════════╗
    // ║                      TEST CASES                            ║
    // ╚════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("Test 1: Click Home Breadcrumb Link and Navigate Back")
    @Description("Verify clicking home breadcrumb link navigates to home page and can navigate back")
    @Severity(SeverityLevel.NORMAL)
    @Story("Team Planners Navigation")
    public void test1_ClickHomeBreadcrumbLinkAndNavigateBack() {
        // Click home breadcrumb link
        teamPlannersPage.clickHomeBreadCrumbLink();
        
        // Assert navigates to home page
        assertEquals("https://safe.openproject.com/", teamPlannersPage.getCurrentUrl(), 
                     "Should navigate to home page");
        captureScreenshotWithTitle("Navigate to Home");

        // Navigate back
        teamPlannersPage.navigateBack();
        
        // Assert back to team planners page
        assertTrue(teamPlannersPage.getCurrentUrl().contains("/team_planners"), 
                   "Should be back at team planners page");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Change Items Per Page")
    @Description("Verify changing items per page to 100, 200, and 50")
    @Severity(SeverityLevel.NORMAL)
    @Story("Team Planners Pagination")
    public void test2_ChangeItemsPerPage() {
        // Click 100 per page
        teamPlannersPage.clickShow100PerPage();
        assertTrue(teamPlannersPage.getCurrentUrl().contains("per_page=100"), 
                   "URL should contain per_page=100");
        captureScreenshotWithTitle("Show 100 Per Page");

        // Click 200 per page
        teamPlannersPage.clickShow200PerPage();
        assertTrue(teamPlannersPage.getCurrentUrl().contains("per_page=200"), 
                   "URL should contain per_page=200");
        captureScreenshotWithTitle("Show 200 Per Page");
        
        // Click 50 per page
        teamPlannersPage.clickShow50PerPage();
        assertTrue(teamPlannersPage.getCurrentUrl().contains("per_page=50"), 
                   "URL should contain per_page=50");
        captureScreenshotWithTitle("Show 50 Per Page");
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Sort Table Columns")
    @Description("Verify sorting by Name, Project, and Created on columns (ascending and descending)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Team Planners Sorting")
    public void test3_SortTableColumns() {
        // Test Name column - first click (descending)
        teamPlannersPage.clickColumnName();
        String currentUrl = teamPlannersPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=name(%3A|:)desc.*"), 
                   "URL should contain sort=name:desc or sort=name%3Adesc");
        captureScreenshotWithTitle("Sort by Name Descending");

        // Test Name column - second click (ascending)
        teamPlannersPage.clickColumnName();
        currentUrl = teamPlannersPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=name[^%]*(%2C|,).*") || 
                   currentUrl.matches(".*sort=name$.*"), 
                   "URL should contain sort=name without desc");
        captureScreenshotWithTitle("Sort by Name Ascending");

        // Reset to clean state before testing Project column
        driver.get("https://safe.openproject.com/team_planners");
        
        // Test Project column - first click (ascending)
        teamPlannersPage.clickColumnProject();
        currentUrl = teamPlannersPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=project_name[^%]*(%2C|,).*") || 
                   currentUrl.matches(".*sort=project_name$.*"), 
                   "URL should contain sort=project_name without desc");
        captureScreenshotWithTitle("Sort by Project Ascending");

        // Test Project column - second click (descending)
        teamPlannersPage.clickColumnProject();
        currentUrl = teamPlannersPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=project_name(%3A|:)desc.*"), 
                   "URL should contain sort=project_name:desc or sort=project_name%3Adesc");
        captureScreenshotWithTitle("Sort by Project Descending");
        
        // Reset to clean state before testing Created on column
        driver.get("https://safe.openproject.com/team_planners");
        
        // Test Created on column - first click (ascending)
        teamPlannersPage.clickColumnCreatedOn();
        currentUrl = teamPlannersPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=created_at[^%]*(%2C|,).*") || 
                   currentUrl.matches(".*sort=created_at$.*"), 
                   "URL should contain sort=created_at without desc");
        captureScreenshotWithTitle("Sort by Created On Ascending");

        // Test Created on column - second click (descending)
        teamPlannersPage.clickColumnCreatedOn();
        currentUrl = teamPlannersPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=created_at(%3A|:)desc.*"), 
                   "URL should contain sort=created_at:desc or sort=created_at%3Adesc");
        assertTrue(currentUrl.contains("desc"), "URL should contain desc for second click");
        captureScreenshotWithTitle("Sort by Created On Descending");
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Click Name and Project Row Links")
    @Description("Verify clicking first row name and project links navigate correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Team Planners Row Links")
    public void test4_ClickNameAndProjectRowLinks() {
        // Test Name row link
        teamPlannersPage.clickNameRow();
        String currentUrl = teamPlannersPage.getCurrentUrl();
        assertTrue(currentUrl.contains("/projects") && currentUrl.contains("/team_planners"), 
                   "URL should contain /projects and /team_planners");
        captureScreenshotWithTitle("Click Name Row Link");

        // Navigate back
        teamPlannersPage.navigateBack();
        assertTrue(teamPlannersPage.getCurrentUrl().contains("/team_planners"), 
                   "Should be back at team planners page");
        
        // Test Project row link
        teamPlannersPage.clickProjectRow();
        assertTrue(teamPlannersPage.getCurrentUrl().contains("/projects"), 
                   "URL should contain /projects");
        captureScreenshotWithTitle("Click Project Row Link");
    }
}
