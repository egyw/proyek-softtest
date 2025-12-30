package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.TimeAndCostPage;
import com.proyek_softtest.utils.Delay;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Time and Cost Module")
@Feature("Time and Cost Page")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TimeAndCostTest extends BaseTest {
    private TimeAndCostPage timeAndCostPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting Time and Cost Test...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        timeAndCostPage = new TimeAndCostPage(driver);
        
        // Navigate to Time and Cost page
        driver.get("https://safe.openproject.com/cost_reports");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   SIDEBAR TESTS                                ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("Test 1: Click Time and Cost Button in Sidebar")
    @Description("Verify clicking Time and Cost button in sidebar navigates to cost reports page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sidebar Navigation")
    public void test1_ClickTimeAndCostButtonInSidebar() {
        // Click Time and Cost button in sidebar
        timeAndCostPage.clickTimeAndCostButtonInSidebar();
        Delay.waitFor(1000);
        
        // Assert URL is correct
        String currentUrl = timeAndCostPage.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/cost_reports"),
                   "URL should be https://safe.openproject.com/cost_reports. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Time and Cost Page from Sidebar");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   BREADCRUMB TESTS                             ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(2)
    @DisplayName("Test 2: Click OpenProject Breadcrumb Link")
    @Description("Verify clicking OpenProject breadcrumb link navigates to home and back")
    @Severity(SeverityLevel.NORMAL)
    @Story("Breadcrumb Navigation")
    public void test2_ClickOpenProjectBreadcrumbLink() {
        // Click OpenProject breadcrumb link
        timeAndCostPage.clickOpenProjectBreadcrumbLink();
        
        // Assert URL is home page
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/"),
                   "URL should be home page. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Home Page from Breadcrumb");
        
        // Navigate back
        timeAndCostPage.navigateBack();
        captureScreenshotWithTitle("Back to Time and Cost Page");
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Click Time and Cost Breadcrumb Link")
    @Description("Verify clicking Time and Cost breadcrumb link stays on cost reports page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Breadcrumb Navigation")
    public void test3_ClickTimeAndCostBreadcrumbLink() {
        // Click Time and Cost breadcrumb link
        timeAndCostPage.clickTimeAndCostBreadcrumbLink();
        Delay.waitFor(1000);

        // Assert URL is still cost reports
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/cost_reports"),
                   "URL should be https://safe.openproject.com/cost_reports. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Time and Cost Page from Breadcrumb");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   COLLAPSEABLE TESTS                           ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(4)
    @DisplayName("Test 4: Toggle Filter Collapseable")
    @Description("Verify clicking Filter collapseable twice (close then open) and check add filter dropdown visibility")
    @Severity(SeverityLevel.NORMAL)
    @Story("Collapseable Sections")
    public void test4_ToggleFilterCollapseable() {
        // First click - close Filter section
        timeAndCostPage.clickFilterCollapseable();
        captureScreenshotWithTitle("Filter Section Closed");
        Delay.waitFor(500);

        // Second click - open Filter section
        timeAndCostPage.clickFilterCollapseable();
        captureScreenshotWithTitle("Filter Section Opened");

        // Assert add filter dropdown is visible
        assertTrue(timeAndCostPage.isAddFilterDropdownVisible(),
                   "Add filter dropdown should be visible when Filter is expanded");
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Toggle Group By Collapseable")
    @Description("Verify clicking Group By collapseable twice (close then open) and check group by columns visibility")
    @Severity(SeverityLevel.NORMAL)
    @Story("Collapseable Sections")
    public void test5_ToggleGroupByCollapseable() {
        // First click - close Group By section
        timeAndCostPage.clickGroupByCollapseable();
        captureScreenshotWithTitle("Group By Section Closed");
        Delay.waitFor(500);

        // Second click - open Group By section
        timeAndCostPage.clickGroupByCollapseable();
        captureScreenshotWithTitle("Group By Section Opened");
        
        // Assert group by columns is visible
        assertTrue(timeAndCostPage.isGroupByColumnsVisible(),
                   "Group by columns should be visible when Group By is expanded");
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Toggle Units Collapseable")
    @Description("Verify clicking Units collapseable twice (close then open) and check labor radio button visibility")
    @Severity(SeverityLevel.NORMAL)
    @Story("Collapseable Sections")
    public void test6_ToggleUnitsCollapseable() {
        // First click - close Units section
        timeAndCostPage.clickUnitsCollapseable();
        captureScreenshotWithTitle("Units Section Closed");
        Delay.waitFor(500);
        
        // Second click - open Units section
        timeAndCostPage.clickUnitsCollapseable();
        captureScreenshotWithTitle("Units Section Opened");
        
        // Assert labor radio button is visible
        assertTrue(timeAndCostPage.isLaborRadioButtonVisible(),
                   "Labor radio button should be visible when Units is expanded");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   FILTER TESTS                                 ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(7)
    @DisplayName("Test 7: Select All Available Filters")
    @Description("Verify selecting all available filter options from add filter dropdown")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Filter Actions")
    public void test7_SelectAllAvailableFilters() {
        // Get initial count of available filters
        int initialCount = timeAndCostPage.getAvailableFilterCount();
        System.out.println("Initial available filters: " + initialCount);
        captureScreenshotWithTitle("Before Selecting Filters");
        
        // Select all available filters
        int selectedCount = timeAndCostPage.selectAllAvailableFilters();
        System.out.println("Selected filters: " + selectedCount);
        captureScreenshotWithTitle("After Selecting All Filters");
        
        // Assert at least some filters were selected
        assertTrue(selectedCount > 0, 
                   "At least some filters should have been selected. Selected: " + selectedCount);
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Add User Filter, Set Date to Today, Select User, Remove Date, Apply")
    @Description("Verify adding User filter, selecting Demo admin, removing Date filter, and applying shows no results")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Filter Actions")
    public void test8_UserFilterWithDateRemoval() {
        // Step 1: Add User filter from dropdown
        timeAndCostPage.selectFilterByValue("user_id");
        captureScreenshotWithTitle("User Filter Added");
        
        // Step 2: Set Date filter to "today"
        timeAndCostPage.selectDateOperator("t");
        captureScreenshotWithTitle("Date Set to Today");
        
        // Step 3: Click User ng-select and select Demo admin
        timeAndCostPage.clickUserFilterNgSelect();
        captureScreenshotWithTitle("User Dropdown Opened");
        
        timeAndCostPage.selectUserFromDropdown("Demo admin");
        captureScreenshotWithTitle("Demo admin Selected");
        
        // Step 4: Remove the first filter (Date/spent_on)
        timeAndCostPage.clickRemoveFirstFilter();
        captureScreenshotWithTitle("Date Filter Removed");
        
        // Step 5: Click Apply button
        timeAndCostPage.clickApplyButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Filter Applied");
        
        // Step 6: Assert no results displayed
        assertTrue(timeAndCostPage.isNoResultsDisplayed(),
                   "Should display 'There is currently nothing to display' message");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   GROUP BY TESTS                               ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(9)
    @DisplayName("Test 9: Group By Columns and Rows - Add, Drag, Remove")
    @Description("Verify adding group by columns/rows, drag to reorder, remove item, and apply")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Group By Actions")
    public void test9_GroupByColumnsAndRows() {
        // Step 1: Select 2 Group By Columns (budget_id, custom_field6)
        timeAndCostPage.selectGroupByColumn("budget_id");
        captureScreenshotWithTitle("Column Added - Budget");
        
        timeAndCostPage.selectGroupByColumn("custom_field6");
        captureScreenshotWithTitle("Column Added - Academie");
        
        // Step 2: Select 2 Group By Rows (assigned_to_id, user_id)
        timeAndCostPage.selectGroupByRow("assigned_to_id");
        captureScreenshotWithTitle("Row Added - Assignee");
        
        timeAndCostPage.selectGroupByRow("user_id");
        captureScreenshotWithTitle("Row Added - User");
        
        // Step 3: Drag Column item 3 (index 2) to position 1 (index 0)
        timeAndCostPage.dragGroupByColumnToPosition(2, 0);
        captureScreenshotWithTitle("Column Dragged - Position Changed");
        
        // Step 4: Drag Row item 3 (index 2) to position 1 (index 0)
        timeAndCostPage.dragGroupByRowToPosition(2, 0);
        captureScreenshotWithTitle("Row Dragged - Position Changed");
        
        // Step 5: Remove Column item at index 1 (second item)
        timeAndCostPage.removeGroupByColumnByIndex(1);
        captureScreenshotWithTitle("Column Item Removed");
        
        // Step 6: Remove Row item at index 1 (second item)
        timeAndCostPage.removeGroupByRowByIndex(1);
        captureScreenshotWithTitle("Row Item Removed");
        
        // Step 7: Click Apply button
        timeAndCostPage.clickApplyButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Group By Applied");
        
        assertTrue(timeAndCostPage.isNoResultsDisplayed(),
                   "Should display 'There is currently nothing to display' message");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   UNITS TESTS                                  ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(10)
    @DisplayName("Test 10: Units Filter - Toggle Radio Buttons and Apply")
    @Description("Verify clicking Cash Value, then Labor, then Cash Value radio buttons, and apply")
    @Severity(SeverityLevel.NORMAL)
    @Story("Units Actions")
    public void test10_UnitsToggleRadioButtons() {
        // Step 1: Click Cash Value radio button
        timeAndCostPage.clickCashValueRadioButton();
        captureScreenshotWithTitle("Cash Value Selected");
        
        // Step 2: Click Labor radio button
        timeAndCostPage.clickLaborRadioButton();
        captureScreenshotWithTitle("Labor Selected");
        
        // Step 3: Click Cash Value radio button again
        timeAndCostPage.clickCashValueRadioButton();
        captureScreenshotWithTitle("Cash Value Selected Again");
        
        // Step 4: Click Apply button
        timeAndCostPage.clickApplyButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Units Applied");
        
        assertTrue(timeAndCostPage.isNoResultsDisplayed(),
                   "Should display 'There is currently nothing to display' message");
    }

    @Test
    @Order(11)
    @DisplayName("Test 11: Clear Filter Button")
    @Description("Verify clicking clear button resets filters")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter Actions")
    public void test11_ClearFilterButton() {
        // Click Clear button
        timeAndCostPage.clickClearButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Filters Cleared");
        
        // Assert - verify page shows no results after clear
        assertTrue(timeAndCostPage.isNoResultsDisplayed(),
                   "Should display 'There is currently nothing to display' message after clear");
    }
}
