package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.BoardsPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Boards Page")
@Feature("Boards Functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardsTest extends BaseTest {

    private BoardsPage boardsPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting BoardsTest...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        boardsPage = new BoardsPage(driver);
        
        // Navigate to boards page before each test
        driver.get("https://safe.openproject.com/boards");
    }

    // ╔════════════════════════════════════════════════════════════╗
    // ║                      TEST CASES                            ║
    // ╚════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("Test 1: Click Home Breadcrumb Link and Navigate Back")
    @Description("Verify clicking home breadcrumb link navigates to home page and can navigate back")
    @Severity(SeverityLevel.NORMAL)
    @Story("Boards Navigation")
    public void test1_ClickHomeBreadcrumbLinkAndNavigateBack() {
        // Click home breadcrumb link
        boardsPage.clickHomeBreadCrumbLink();
        
        // Assert navigates to home page
        assertEquals("https://safe.openproject.com/", boardsPage.getCurrentUrl(), 
                     "Should navigate to home page");
        captureScreenshotWithTitle("Navigate to Home");

        // Navigate back
        boardsPage.navigateBack();
        
        // Assert back to boards page
        assertTrue(boardsPage.getCurrentUrl().contains("/boards"), 
                   "Should be back at boards page");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Sort Table Columns")
    @Description("Verify sorting by Name, Project, and Created on columns (ascending and descending)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Boards Sorting")
    public void test2_SortTableColumns() {
        // Test Name column - first click (descending)
        boardsPage.clickColumnName();
        String currentUrl = boardsPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=name(%3A|:)desc.*"), 
                   "URL should contain sort=name:desc or sort=name%3Adesc");
        captureScreenshotWithTitle("Sort by Name Descending");

        // Test Name column - second click (ascending)
        boardsPage.clickColumnName();
        currentUrl = boardsPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=name[^%]*(%2C|,).*") || 
                   currentUrl.matches(".*sort=name$.*"), 
                   "URL should contain sort=name without desc");
        captureScreenshotWithTitle("Sort by Name Ascending");

        // Reset to clean state before testing Project column
        driver.get("https://safe.openproject.com/boards");
        
        // Test Project column - first click (ascending)
        boardsPage.clickColumnProject();
        currentUrl = boardsPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=project_name[^%]*(%2C|,).*") || 
                   currentUrl.matches(".*sort=project_name$.*"), 
                   "URL should contain sort=project_name without desc");
        captureScreenshotWithTitle("Sort by Project Ascending");

        // Test Project column - second click (descending)
        boardsPage.clickColumnProject();
        currentUrl = boardsPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=project_name(%3A|:)desc.*"), 
                   "URL should contain sort=project_name:desc or sort=project_name%3Adesc");
        captureScreenshotWithTitle("Sort by Project Descending");
        
        // Reset to clean state before testing Created on column
        driver.get("https://safe.openproject.com/boards");
        
        // Test Created on column - first click (ascending)
        boardsPage.clickColumnCreatedOn();
        currentUrl = boardsPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=created_at[^%]*(%2C|,).*") || 
                   currentUrl.matches(".*sort=created_at$.*"), 
                   "URL should contain sort=created_at without desc");
        captureScreenshotWithTitle("Sort by Created On Ascending");

        // Test Created on column - second click (descending)
        boardsPage.clickColumnCreatedOn();
        currentUrl = boardsPage.getCurrentUrl();
        assertTrue(currentUrl.matches(".*sort=created_at(%3A|:)desc.*"), 
                   "URL should contain sort=created_at:desc or sort=created_at%3Adesc");
        assertTrue(currentUrl.contains("desc"), "URL should contain desc for second click");
        captureScreenshotWithTitle("Sort by Created On Descending");
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Click Name and Project Row Links")
    @Description("Verify clicking first row name and project links navigate correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Boards Row Links")
    public void test3_ClickNameAndProjectRowLinks() {
        // Test Name row link
        boardsPage.clickNameRow();
        String currentUrl = boardsPage.getCurrentUrl();
        assertTrue(currentUrl.contains("/projects") && currentUrl.contains("/boards"), 
                   "URL should contain /projects and /boards");
        captureScreenshotWithTitle("Click Name Row Link");

        // Navigate back
        boardsPage.navigateBack();
        assertTrue(boardsPage.getCurrentUrl().contains("/boards"), 
                   "Should be back at boards page");
        
        // Test Project row link
        boardsPage.clickProjectRow();
        assertTrue(boardsPage.getCurrentUrl().contains("/projects"), 
                   "URL should contain /projects");
        captureScreenshotWithTitle("Click Project Row Link");
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Change Items Per Page")
    @Description("Verify changing items per page to 100, 200, and 50")
    @Severity(SeverityLevel.NORMAL)
    @Story("Boards Pagination")
    public void test4_ChangeItemsPerPage() {
        // Click 100 per page
        boardsPage.clickShow100PerPage();
        assertTrue(boardsPage.getCurrentUrl().contains("per_page=100"), 
                   "URL should contain per_page=100");
        captureScreenshotWithTitle("Show 100 Per Page");

        // Click 200 per page
        boardsPage.clickShow200PerPage();
        assertTrue(boardsPage.getCurrentUrl().contains("per_page=200"), 
                   "URL should contain per_page=200");
        captureScreenshotWithTitle("Show 200 Per Page");
        
        // Click 50 per page
        boardsPage.clickShow50PerPage();
        assertTrue(boardsPage.getCurrentUrl().contains("per_page=50"), 
                   "URL should contain per_page=50");
        captureScreenshotWithTitle("Show 50 Per Page");
    }
}
