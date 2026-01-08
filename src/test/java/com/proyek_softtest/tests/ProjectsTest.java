package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.ProjectsPage;
import com.proyek_softtest.utils.Delay;
import com.proyek_softtest.config.TestData;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Projects Page")
@Feature("Projects Page Functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectsTest extends BaseTest {

    private ProjectsPage projectsPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting ProjectsTest...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        projectsPage = new ProjectsPage(driver);

        driver.get("https://safe.openproject.com/projects");
    }

    // ╔════════════════════════════════════════════════════════════╗
    // ║                      TEST CASES                            ║
    // ╚════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("PJ_T-001: Click Projects Button in Sidebar")
    @Description("Verify clicking projects button in sidebar navigates to projects page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Sidebar Navigation")
    public void test1_ClickProjectsButtonInSidebar() {
        projectsPage.clickProjectsButtonInSidebar();

        assertTrue(projectsPage.getCurrentUrl().contains("/projects"),
                "URL should contain /projects");
        captureScreenshotWithTitle("Navigate to Projects Page");
    }

    @Test
    @Order(2)
    @DisplayName("PJ_T-002: Search Active Projects and Click")
    @Description("Verify searching 'active' then 'projects' filters correctly and clicking active projects navigates to correct URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Sidebar Search")
    public void test2_SearchActiveProjectsAndClick() {
        projectsPage.searchInSidebar("active");
        captureScreenshotWithTitle("Search Active");

        projectsPage.searchInSidebar("active projects");
        captureScreenshotWithTitle("Search Active Projects");

        assertFalse(projectsPage.isOnTrackFilterDisplayed(), "OnTrack filter should not be displayed");
        assertFalse(projectsPage.isOffTrackFilterDisplayed(), "OffTrack filter should not be displayed");
        assertFalse(projectsPage.isAtRiskFilterDisplayed(), "AtRisk filter should not be displayed");

        projectsPage.clickActiveProjectsButtonInSidebar();

        assertEquals("https://safe.openproject.com/projects?query_id=active",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/projects?query_id=active");
        captureScreenshotWithTitle("Active Projects Page");
    }

    @Test
    @Order(3)
    @DisplayName("PJ_T-003: Search On Track Status and Click")
    @Description("Verify searching 'ontrack' filters correctly and clicking ontrack navigates to correct URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Sidebar Search")
    public void test3_SearchOnTrackStatusAndClick() {
        projectsPage.searchInSidebar("on track");
        captureScreenshotWithTitle("Search On Track");

        projectsPage.clickOnTrackFilter();

        assertEquals("https://safe.openproject.com/projects?query_id=on_track",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/projects?query_id=on_track");
        captureScreenshotWithTitle("On Track Projects Page");
    }

    @Test
    @Order(4)
    @DisplayName("PJ_T-004: Search Non-Existent Item")
    @Description("Verify searching for non-existent item 'ABC' shows no items found message")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Sidebar Search")
    public void test4_SearchNonExistentItem() {
        projectsPage.searchInSidebar("ABC");
        captureScreenshotWithTitle("Search ABC");

        assertTrue(projectsPage.isNoItemsFoundInSidebar(),
                "No items found message should be displayed");
        captureScreenshotWithTitle("No Items Found");
    }

    @Test
    @Order(5)
    @DisplayName("PJ_T-005: Toggle Project Status Filters")
    @Description("Verify toggling project status shows and hides filters (OnTrack, OffTrack, AtRisk)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Sidebar Toggle")
    public void test5_ToggleProjectStatusFilters() {
        projectsPage.clickProjectStatusToggle();
        captureScreenshotWithTitle("Project Status Collapsed");

        assertFalse(projectsPage.isOnTrackFilterDisplayed(), "OnTrack filter should not be displayed");
        assertFalse(projectsPage.isOffTrackFilterDisplayed(), "OffTrack filter should not be displayed");
        assertFalse(projectsPage.isAtRiskFilterDisplayed(), "AtRisk filter should not be displayed");

        projectsPage.clickProjectStatusToggle();
        captureScreenshotWithTitle("Project Status Expanded");

        assertTrue(projectsPage.isOnTrackFilterDisplayed(), "OnTrack filter should be displayed");
        assertTrue(projectsPage.isOffTrackFilterDisplayed(), "OffTrack filter should be displayed");
        assertTrue(projectsPage.isAtRiskFilterDisplayed(), "AtRisk filter should be displayed");
    }

    @Test
    @Order(6)
    @DisplayName("PJ_T-006: Click On Track Filter")
    @Description("Verify clicking On Track filter navigates to correct URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Filter Navigation")
    public void test6_ClickOnTrackFilter() {
        driver.get("https://safe.openproject.com/projects");

        projectsPage.clickOnTrackFilter();

        assertEquals("https://safe.openproject.com/projects?query_id=on_track",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/projects?query_id=on_track");
        captureScreenshotWithTitle("On Track Filter Page");
    }

    @Test
    @Order(7)
    @DisplayName("PJ_T-007: Click Off Track Filter")
    @Description("Verify clicking Off Track filter navigates to correct URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Filter Navigation")
    public void test7_ClickOffTrackFilter() {
        projectsPage.clickOffTrackFilter();

        assertEquals("https://safe.openproject.com/projects?query_id=off_track",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/projects?query_id=off_track");
        captureScreenshotWithTitle("Off Track Filter Page");
    }

    @Test
    @Order(8)
    @DisplayName("PJ_T-008: Click At Risk Filter")
    @Description("Verify clicking At Risk filter navigates to correct URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Filter Navigation")
    public void test8_ClickAtRiskFilter() {
        projectsPage.clickAtRiskFilter();

        assertEquals("https://safe.openproject.com/projects?query_id=at_risk",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/projects?query_id=at_risk");
        captureScreenshotWithTitle("At Risk Filter Page");
    }

    @Test
    @Order(9)
    @DisplayName("PJ_T-009: Click Home Breadcrumb Link")
    @Description("Verify clicking home breadcrumb link navigates to home page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Breadcrumb Navigation")
    public void test9_ClickHomeBreadCrumbLink() {
        projectsPage.clickHomeBreadCrumbLink();

        assertEquals("https://safe.openproject.com/",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/");
        captureScreenshotWithTitle("Navigate to Home Page");

        projectsPage.navigateBack();
    }

    @Test
    @Order(10)
    @DisplayName("PJ_T-010: Click Projects Breadcrumb Link")
    @Description("Verify clicking projects breadcrumb link navigates to projects page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Breadcrumb Navigation")
    public void test10_ClickProjectsBreadCrumbLink() {
        projectsPage.clickProjectsBreadCrumbLink();

        assertTrue(projectsPage.getCurrentUrl().contains("/projects"),
                "URL should contain /projects");
        captureScreenshotWithTitle("Navigate to Projects Page");
    }

    @Test
    @Order(11)
    @DisplayName("PJ_T-011: Open and Close More Menu")
    @Description("Verify opening more menu shows options and closing hides them")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects More Menu")
    public void test11_OpenAndCloseMoreMenu() {
        projectsPage.clickMoreMenuButton();
        assertTrue(projectsPage.isOpenAsGanttDisplayed(),
                "Open as Gantt option should be displayed");
        captureScreenshotWithTitle("More Menu Opened");

        projectsPage.clickMoreMenuButton();
        captureScreenshotWithTitle("More Menu Closed");

    }

    @Test
    @Order(12)
    @DisplayName("PJ_T-012: Click Open As Gantt from More Menu")
    @Description("Verify clicking Open as Gantt opens new window with gantt URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects More Menu")
    public void test12_ClickOpenAsGantt() {
        String originalWindow = driver.getWindowHandle();

        projectsPage.clickMoreMenuButton();

        projectsPage.clickOpenAsGantt();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        assertTrue(projectsPage.getCurrentUrl().contains("gantt"),
                "URL should contain gantt");
        captureScreenshotWithTitle("Gantt View in New Window");

        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    @Order(13)
    @DisplayName("PJ_T-013: Click Overall Activity from More Menu")
    @Description("Verify clicking Overall Activity navigates to activities page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects More Menu")
    public void test13_ClickOverallActivity() {
        projectsPage.clickMoreMenuButton();

        projectsPage.clickOverallActivity();

        assertTrue(projectsPage.getCurrentUrl().contains("/activities"),
                "URL should contain /activities");
        captureScreenshotWithTitle("Overall Activity Page");

        projectsPage.navigateBack();
        assertTrue(projectsPage.getCurrentUrl().contains("/projects"),
                "Should be back at projects page");
    }

    @Test
    @Order(14)
    @DisplayName("PJ_T-014: Open Export Dialog and Close")
    @Description("Verify clicking Export opens dialog with 'Export' header and can be closed")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Export")
    public void test14_OpenExportDialogAndClose() {
        projectsPage.clickMoreMenuButton();
        captureScreenshotWithTitle("More Menu Opened");

        projectsPage.clickExportButton();
        captureScreenshotWithTitle("Export Dialog Opened");

        String headerText = projectsPage.getExportDialogHeaderText();
        assertEquals("Export", headerText,
                "Export dialog header should be 'Export'");

        projectsPage.clickExportDialogCloseButton();
        captureScreenshotWithTitle("Export Dialog Closed");
    }

    @Test
    @Order(15)
    @DisplayName("PJ_T-015: Export to XLS")
    @Description("Verify exporting to XLS format completes successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Export")
    public void test15_ExportToXls() {
        projectsPage.clickMoreMenuButton();

        projectsPage.clickExportButton();
        captureScreenshotWithTitle("Export Dialog Opened");

        projectsPage.clickExportXlsButton();

        assertTrue(projectsPage.isExportCompletedHeaderDisplayed(),
                "Export completed header should be displayed");
        captureScreenshotWithTitle("Export Completed");

        String linkType = projectsPage.getDownloadLinkType();
        assertEquals("application/vnd.ms-excel", linkType,
                "Download link should be Excel type");

        projectsPage.clickDownloadLink();

        projectsPage.clickCloseExportButton();
        captureScreenshotWithTitle("Export Dialog Closed After Download");
    }

    @Test
    @Order(16)
    @DisplayName("PJ_T-016: Export to CSV")
    @Description("Verify exporting to CSV format completes successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Export")
    public void test16_ExportToCsv() {
        projectsPage.clickMoreMenuButton();

        projectsPage.clickExportButton();
        captureScreenshotWithTitle("Export Dialog Opened for CSV");

        projectsPage.clickExportCsvButton();

        assertTrue(projectsPage.isExportCompletedHeaderDisplayed(),
                "Export completed header should be displayed");
        captureScreenshotWithTitle("CSV Export Completed");

        String linkType = projectsPage.getDownloadLinkType();
        assertEquals("text/csv", linkType,
                "Download link should be CSV type");

        projectsPage.clickDownloadLink();

        projectsPage.clickCloseExportButton();
        captureScreenshotWithTitle("Export Dialog Closed After CSV Download");
    }

    @Test
    @Order(17)
    @DisplayName("PJ_T-017: Export to PDF")
    @Description("Verify exporting to PDF format completes successfully with new tab handling")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Export")
    public void test17_ExportToPdf() {
        projectsPage.clickMoreMenuButton();

        projectsPage.clickExportButton();
        captureScreenshotWithTitle("Export Dialog Opened for PDF");

        String originalWindow = driver.getWindowHandle();

        projectsPage.clickExportPdfButton();

        assertTrue(projectsPage.isExportCompletedHeaderDisplayed(),
                "Export completed header should be displayed");
        captureScreenshotWithTitle("PDF Export Completed");

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                captureScreenshotWithTitle("First PDF Tab Opened Automatically");
                Delay.waitFor(2000);
                driver.close();
                break;
            }
        }

        driver.switchTo().window(originalWindow);
        captureScreenshotWithTitle("Back to Original Window");

        String linkType = projectsPage.getDownloadLinkType();
        assertEquals("application/pdf", linkType,
                "Download link should be PDF type");

        projectsPage.clickDownloadLink();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                captureScreenshotWithTitle("Second PDF Tab from Download Link");
                driver.close();
                break;
            }
        }

        driver.switchTo().window(originalWindow);

        projectsPage.clickCloseExportButton();
        captureScreenshotWithTitle("Export Dialog Closed After PDF Download");
    }

    @Test
    @Order(18)
    @DisplayName("PJ_T-018: Open and Close Configure View Dialog")
    @Description("Verify configure view dialog can be opened and closed successfully")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Configure View")
    public void test18_OpenAndCloseConfigureViewDialog() {
        projectsPage.clickMoreMenuButton();

        projectsPage.clickConfigureView();
        assertTrue(projectsPage.isConfigureViewDialogHeaderDisplayed(),
                "Configure view dialog header should be displayed");
        captureScreenshotWithTitle("Configure View Dialog Opened");

        projectsPage.clickConfigureViewDialogCloseButton();
        captureScreenshotWithTitle("Configure View Dialog Closed");
    }

    @Test
    @Order(19)
    @DisplayName("PJ_T-019: Switch Between Columns and Sort Tabs")
    @Description("Verify switching between Columns tab and Sort tab in Configure View dialog")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Configure View")
    public void test19_SwitchBetweenColumnsAndSortTabs() {
        projectsPage.clickMoreMenuButton();

        projectsPage.clickConfigureView();
        captureScreenshotWithTitle("Configure View Dialog Opened - Default Columns Tab");

        projectsPage.clickSortTab();

        assertTrue(projectsPage.isSortFieldDisplayed(),
                "Sort field select should be displayed when Sort tab is active");
        captureScreenshotWithTitle("Sort Tab Clicked");

        projectsPage.clickColumnsTab();
        captureScreenshotWithTitle("Columns Tab Clicked");

        projectsPage.clickCancelButtonInConfigureView();
    }

    @Test
    @Order(20)
    @DisplayName("PJ_T-020: Select First Combobox Item and Cancel")
    @Description("Verify selecting first combobox item and canceling works correctly")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Configure View")
    public void test20_SelectFirstComboboxItemAndCancel() {
        projectsPage.clickMoreMenuButton();

        projectsPage.clickConfigureView();
        captureScreenshotWithTitle("Configure View Dialog Opened for Combobox Test");

        projectsPage.clickConfigureViewCombobox();
        captureScreenshotWithTitle("Combobox Dropdown Opened");

        assertTrue(projectsPage.isComboboxDropdownVisible(),
                "Combobox dropdown should be visible after clicking combobox");

        var options = projectsPage.getComboboxOptions();
        String firstOptionText = options.get(0).getText().trim();
        System.out.println("Selecting first option: " + firstOptionText);

        projectsPage.selectComboboxOptionByIndex(0);
        captureScreenshotWithTitle("First Combobox Item Selected");

        assertTrue(projectsPage.isColumnSelected(firstOptionText),
                "Selected column '" + firstOptionText + "' should appear in the columns list");

        var selectedColumns = projectsPage.getSelectedColumnNames();
        System.out.println("Current selected columns: " + selectedColumns);

        projectsPage.clickConfigureViewCombobox();
        captureScreenshotWithTitle("Combobox Reopened After Selection");

        assertFalse(projectsPage.isOptionAvailableInDropdown(firstOptionText),
                "Selected column '" + firstOptionText + "' should not appear in dropdown options anymore");

        System.out.println("Verified: Selected item '" + firstOptionText + "' is removed from dropdown options");

        projectsPage.clickCancelButtonInConfigureView();
        captureScreenshotWithTitle("Configure View Dialog Canceled");
    }

    @Test
    @Order(21)
    @DisplayName("PJ_T-021: Drag and Drop Selected Columns")
    @Description("Verify dragging 'Public' to first position and 'SAFe solution train' to second position")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Configure View")
    public void test21_DragAndDropSelectedColumns() {
        projectsPage.clickMoreMenuButton();

        projectsPage.clickConfigureView();
        var columnsBefore = projectsPage.getSelectedColumnNames();
        System.out.println("Columns before drag: " + columnsBefore);
        captureScreenshotWithTitle("Initial Column Order");

        projectsPage.dragColumnToPosition("Public", 0);
        int publicPosition = projectsPage.getSelectedColumnPosition("Public");
        assertEquals(0, publicPosition, "Public should be at position 0 after drag");
        captureScreenshotWithTitle("Public Dragged to First Position");
        System.out.println("Verified: 'Public' is now at position 0");

        projectsPage.dragColumnToPosition("SAFe solution train", 1);
        int safePosition = projectsPage.getSelectedColumnPosition("SAFe solution train");
        assertEquals(1, safePosition, "SAFe solution train should be at position 1 after drag");
        captureScreenshotWithTitle("SAFe solution train Dragged to Second Position");
        System.out.println("Verified: 'SAFe solution train' is now at position 1");

        var columnsAfter = projectsPage.getSelectedColumnNames();
        System.out.println("Columns after drag: " + columnsAfter);

        projectsPage.clickApplyButtonInConfigureView();
        captureScreenshotWithTitle("Configure View Applied After Drag and Drop");
    }

    @Test
    @Order(22)
    @DisplayName("PJ_T-022: Remove All Columns Except Name")
    @Description("Verify removing all columns except Name (which cannot be removed) and applying changes")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Configure View")
    public void test22_RemoveAllColumnsExceptName() {
        projectsPage.clickMoreMenuButton();

        projectsPage.clickConfigureView();

        var columnsBefore = projectsPage.getSelectedColumnNames();
        System.out.println("Columns before removal: " + columnsBefore);

        for (String columnName : columnsBefore) {
            if (!columnName.equals("Name")) {
                try {
                    projectsPage.removeColumnByName(columnName);
                    System.out.println("Removed column: " + columnName);
                } catch (Exception e) {
                    System.out.println("Could not remove column: " + columnName + " (might not have remove button)");
                }
            }
        }

        var columnsAfter = projectsPage.getSelectedColumnNames();
        System.out.println("Columns after removal: " + columnsAfter);

        assertEquals(1, columnsAfter.size(), "Only one column should remain");
        assertEquals("Name", columnsAfter.get(0), "The remaining column should be 'Name'");
        captureScreenshotWithTitle("All Columns Removed Except Name");

        projectsPage.clickApplyButtonInConfigureView();
        captureScreenshotWithTitle("Configure View Applied After Removing Columns");
    }

    @Test
    @Order(23)
    @DisplayName("PJ_T-023: Select All Combobox Items and Apply")
    @Description("Verify selecting each combobox item one by one and applying works correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Configure View")
    public void test23_SelectAllComboboxItemsAndApply() {
        projectsPage.clickMoreMenuButton();

        projectsPage.clickConfigureView();

        projectsPage.clickConfigureViewCombobox();

        projectsPage.printComboboxOptionsDebug();

        int selectedCount = 0;
        int maxAttempts = 20; 

        while (selectedCount < maxAttempts) {
            var options = projectsPage.getComboboxOptions();

            if (options.isEmpty()) {
                System.out.println("No more options available");
                break;
            }

            String optionText = options.get(0).getText().trim();
            selectedCount++;
            System.out.println("Selecting option " + selectedCount + ": " + optionText);

            projectsPage.selectComboboxOptionByIndex(0);
            captureScreenshotWithTitle("Selected Option " + selectedCount + ": " + optionText);

            projectsPage.clickConfigureViewCombobox();
            Delay.waitFor(100); 
        }

        System.out.println("Total options selected: " + selectedCount);

        projectsPage.clickConfigureViewCombobox();
        captureScreenshotWithTitle("Combobox After All Items Selected");

        assertTrue(projectsPage.isNoItemsFoundDisplayed(),
                "No items found message should be displayed after all items are selected");

        System.out.println("Verified: All items selected, no more options available in dropdown");

        projectsPage.clickApplyButtonInConfigureView();
        captureScreenshotWithTitle("Configure View Applied After Selecting All Items");
    }

    @Test
    @Order(24)
    @DisplayName("PJ_T-024: Sort by Name Descending and Cancel")
    @Description("Verify selecting sort field and canceling does not apply the sort")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Configure View")
    public void test24_SortByNameDescAndCancel() {
        projectsPage.clickMoreMenuButton();
        
        projectsPage.clickConfigureView();
        
        projectsPage.clickSortTab();
        
        projectsPage.selectSortFieldByValue("name");
        captureScreenshotWithTitle("Name Selected in Sort Field");
        
        projectsPage.clickSortDescending(0);
        captureScreenshotWithTitle("Sort Descending Clicked");
        
        projectsPage.clickCancelButtonInConfigureView();
        
        assertFalse(projectsPage.getCurrentUrl().contains("sortBy"), 
                    "URL should not contain sortBy after cancel");
        captureScreenshotWithTitle("Sort Canceled - URL Unchanged");
    }

    @Test
    @Order(25)
    @DisplayName("PJ_T-025: Sort by Name and Status Descending and Apply")
    @Description("Verify selecting multiple sort fields (Name desc, Status desc) and applying")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Configure View")
    public void test25_SortByNameAndStatusDescAndApply() {
        projectsPage.clickMoreMenuButton();
        
        projectsPage.clickConfigureView();
        
        projectsPage.clickSortTab();
        
        projectsPage.selectNthSortFieldByValue(0, "name");
        captureScreenshotWithTitle("First Sort: Name Selected");
        
        projectsPage.clickSortDescending(0);
        
        assertTrue(projectsPage.getSortFieldCount() >= 2, 
                   "Second sort field should appear after selecting first");
        
        projectsPage.selectNthSortFieldByValue(1, "project_status");
        captureScreenshotWithTitle("Second Sort: Status Selected");
        
        projectsPage.clickSortDescending(1);
        
        projectsPage.clickApplyButtonInConfigureView();
        captureScreenshotWithTitle("Sort Applied");
        
        String currentUrl = projectsPage.getCurrentUrl();
        assertTrue(currentUrl.contains("sortBy"), 
                   "URL should contain sortBy after apply");
        assertTrue(currentUrl.contains("name"), 
                   "URL should contain 'name' in sortBy");
        assertTrue(currentUrl.contains("project_status"), 
                   "URL should contain 'project_status' in sortBy");
        System.out.println("Final URL: " + currentUrl);
    }

    @Test
    @Order(26)
    @DisplayName("PJ_T-026: Reset Sort by Selecting Empty Value and Apply")
    @Description("Verify selecting empty value '-' for both sort fields and applying clears the sort")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Configure View")
    public void test26_ResetSortBySelectingEmptyAndApply() {
        projectsPage.clickMoreMenuButton();
        
        projectsPage.clickConfigureView();
        
        projectsPage.clickSortTab();
        
        projectsPage.selectNthSortFieldByValue(0, "");
        captureScreenshotWithTitle("First Sort Field Reset to Empty");
        
        projectsPage.clickApplyButtonInConfigureView();
        captureScreenshotWithTitle("Sort Reset Applied");
        
        String currentUrl = projectsPage.getCurrentUrl();
        assertTrue(currentUrl.contains("sortBy=%5B%5D") || currentUrl.contains("sortBy=[]"), 
                   "URL should contain empty sortBy array after reset");
        System.out.println("Final URL after reset: " + currentUrl);
    }

    @Test
    @Order(27)
    @DisplayName("PJ_T-027: Toggle Zen Mode On and Off")
    @Description("Verify clicking zen mode toggles fullscreen on and then off")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects More Menu")
    public void test27_ToggleZenModeOnAndOff() {
        projectsPage.clickMoreMenuButton();
        
        projectsPage.clickZenMode();
        
        assertTrue(projectsPage.isFullscreen(), "Should be in fullscreen mode after clicking Zen Mode");
        captureScreenshotWithTitle("Zen Mode Enabled - Fullscreen");
        
        projectsPage.clickMoreMenuButton();
        
        projectsPage.clickZenMode();
        
        assertFalse(projectsPage.isFullscreen(), "Should NOT be in fullscreen mode after clicking Zen Mode again");
        captureScreenshotWithTitle("Zen Mode Disabled - Normal View");
    }

    @Test
    @Order(28)
    @DisplayName("PJ_T-028: Search Project Not Found")
    @Description("Verify searching for non-existent project shows no results message")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Searchbar")
    public void test28_SearchProjectNotFound() {
        String searchKeyword = TestData.getProjectsNoResultsSearch();
        
        Delay.waitFor(2000);

        projectsPage.typeInSearchbar(searchKeyword);
        projectsPage.typeInSearchbar(searchKeyword);
        
        assertTrue(projectsPage.isNoResultsDisplayed(), 
                   "No results message should be displayed for search: " + searchKeyword);
        captureScreenshotWithTitle("Search Not Found - " + searchKeyword);
        
        projectsPage.clickClearSearchbar();
        captureScreenshotWithTitle("Searchbar Cleared");
    }

    @Test
    @Order(29)
    @DisplayName("PJ_T-029: Search Project Found and Navigate")
    @Description("Verify searching for existing project shows result and navigating to project works")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Searchbar")
    public void test29_SearchProjectFoundAndNavigate() {
        String searchKeyword = TestData.getProjectsValidSearchName();
        String expectedLinkText = TestData.getProjectsExpectedLinkText();
        
        projectsPage.typeInSearchbar(searchKeyword);
        captureScreenshotWithTitle("Search Results for - " + searchKeyword);
        
        Delay.waitDefault();
        
        projectsPage.clickProjectLinkByText(expectedLinkText);
        
        assertTrue(projectsPage.isProjectTitleDisplayed(expectedLinkText), 
                   "Project title '" + expectedLinkText + "' should be displayed");
        captureScreenshotWithTitle("Project Page - " + expectedLinkText);
        
        driver.navigate().back();
        Delay.waitDefault();
        
        projectsPage.clickClearSearchbar();
        captureScreenshotWithTitle("Searchbar Cleared After Navigation");
    }

    @Test
    @Order(30)
    @DisplayName("PJ_T-030: Open and Close Filter Panel")
    @Description("Verify opening and closing filter panel using both button and icon")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Filter")
    public void test30_OpenAndCloseFilterPanel() {
        projectsPage.openFilter();
        
        assertTrue(projectsPage.isFilterValueContainerDisplayed(), 
                   "Filter value container should be visible when filter is open");
        captureScreenshotWithTitle("Filter Panel Opened");
        
        projectsPage.closeFilterByButton();
        captureScreenshotWithTitle("Filter Closed by Button");
        
        projectsPage.openFilter();
        
        projectsPage.closeFilterByIcon();

        captureScreenshotWithTitle("Filter Closed by Icon");
    }

    @Test
    @Order(31)
    @DisplayName("PJ_T-031: Toggle Active Filter and Remove")
    @Description("Verify toggling active filter switch shows no results and removing filter resets view")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Filter")
    public void test31_ToggleActiveFilterAndRemove() {
        projectsPage.openFilter();
        
        projectsPage.clickActiveFilterSwitch();
        
        assertTrue(projectsPage.isNoResultsDisplayed(), 
                   "No results message should be displayed when Active filter is disabled");
        captureScreenshotWithTitle("Active Filter Disabled - No Results");
        
        projectsPage.clickActiveFilterSwitch();
        captureScreenshotWithTitle("Active Filter Enabled Again");
        
        projectsPage.clickRemoveActiveFilter();
        captureScreenshotWithTitle("Active Filter Removed");
        
        projectsPage.closeFilterByButton();
        captureScreenshotWithTitle("Filter Panel Closed After Remove");
    }

    @Test
    @Order(32)
    @DisplayName("PJ_T-032: Add Public Filter from Dropdown")
    @Description("Verify adding public filter from add filter dropdown, toggle it, and remove it")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Filter")
    public void test32_AddPublicFilterFromDropdown() {
        projectsPage.openFilter();
        
        projectsPage.addFilterByValue("public");
        
        assertTrue(projectsPage.isPublicFilterDisplayed(), 
                   "Public filter should be displayed after adding");
        captureScreenshotWithTitle("Public Filter Added");
        
        projectsPage.clickPublicFilterSwitch();
        captureScreenshotWithTitle("Public Filter Toggled");
        
        projectsPage.clickRemovePublicFilter();
        captureScreenshotWithTitle("Public Filter Removed");
        
        projectsPage.closeFilterByButton();
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║              TABLE HEADER NAME COLUMN TESTS                    ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(33)
    @DisplayName("PJ_T-033: Name Column - Sort Descending")
    @Description("Verify clicking Sort Descending from Name column header menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Table Header")
    public void test33_NameColumnSortDescending() {
        projectsPage.clickNameColumnHeader();
        captureScreenshotWithTitle("Name Column Menu Opened");
        
        projectsPage.clickNameSortDescending();
        
        Delay.waitDefault();
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("sortBy"), "URL should contain sortBy parameter. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Sorted by Name Descending");
    }

    @Test
    @Order(34)
    @DisplayName("PJ_T-034: Name Column - Sort Ascending")
    @Description("Verify clicking Sort Ascending from Name column header menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Table Header")
    public void test34_NameColumnSortAscending() {
        projectsPage.clickNameColumnHeader();
        
        projectsPage.clickNameSortAscending();
        
        Delay.waitDefault();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("sortBy"), "URL should contain sortBy parameter");
        captureScreenshotWithTitle("Sorted by Name Ascending");
    }

    @Test
    @Order(35)
    @DisplayName("PJ_T-035: Name Column - Filter By")
    @Description("Verify clicking Filter By from Name column header, select project, and remove filter")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Table Header")
    public void test35_NameColumnFilterBy() {
        projectsPage.clickNameColumnHeader();
        
        projectsPage.clickNameFilterBy();
        captureScreenshotWithTitle("Filter By Opened");
        
        String projectName = TestData.getTableHeaderFilterByProject();
        projectsPage.selectProjectFromFilterDropdown(projectName);
        captureScreenshotWithTitle("Project Selected - " + projectName);
        
        assertTrue(projectsPage.isProjectLinkDisplayed(projectName), 
                   "Project link '" + projectName + "' should be displayed");
        
        projectsPage.clickRemoveIdFilter();
        captureScreenshotWithTitle("Filter Removed");
        
        projectsPage.closeFilterByIcon();
    }

    @Test
    @Order(36)
    @DisplayName("PJ_T-036: Name Column - Move Column Left")
    @Description("Verify clicking Move Column Left from Name column header menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Table Header")
    public void test36_NameColumnMoveLeft() {
        // Click Name column header
        projectsPage.clickNameColumnHeader();
        captureScreenshotWithTitle("Name Column Menu Opened");
        
        // Click Move Column Left
        projectsPage.clickNameMoveColLeft();
        
        Delay.waitDefault();
        
        // Assert URL contains columns parameter
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("columns"), "URL should contain columns parameter");
        captureScreenshotWithTitle("Column Moved Left");
    }

    @Test
    @Order(37)
    @DisplayName("PJ_T-037: Name Column - Move Column Right")
    @Description("Verify clicking Move Column Right from Name column header menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Table Header")
    public void test37_NameColumnMoveRight() {
        // Click Name column header
        projectsPage.clickNameColumnHeader();
        
        // Click Move Column Right
        projectsPage.clickNameMoveColRight();
        
        Delay.waitDefault();
        
        // Assert URL contains columns parameter
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("columns"), "URL should contain columns parameter");
        captureScreenshotWithTitle("Column Moved Right");
    }

    @Test
    @Order(38)
    @DisplayName("PJ_T-038: Name Column - Add Column (Opens Configure View)")
    @Description("Verify clicking Add Column from Name column header opens Configure View dialog")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Table Header")
    public void test38_NameColumnAddColumn() {
        // Click Name column header
        projectsPage.clickNameColumnHeader();
        
        // Click Add Column
        projectsPage.clickNameAddColumn();
        captureScreenshotWithTitle("Configure View Opened from Add Column");
        
        // Assert Configure View dialog is displayed
        assertTrue(projectsPage.isConfigureViewDialogHeaderDisplayed(), 
                   "Configure View dialog should be displayed");
        
        // Cancel Configure View
        projectsPage.clickCancelButtonInConfigureView();
        captureScreenshotWithTitle("Configure View Cancelled");
    }

    @Test
    @Order(39)
    @DisplayName("PJ_T-039: Name Column - Remove Column")
    @Description("Verify clicking Remove Column from Name column header menu")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Table Header")
    public void test39_NameColumnRemoveColumn() {
        // Click Name column header
        projectsPage.clickNameColumnHeader();
        captureScreenshotWithTitle("Name Column Menu Opened");
        
        // Click Remove Column
        projectsPage.clickNameRemoveColumn();
        
        Delay.waitDefault();
        
        // Assert URL contains columns parameter (without name)
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("columns"), "URL should contain columns parameter");
        captureScreenshotWithTitle("Name Column Removed");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   TABLE ROW ACTIONS TESTS                      ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(40)
    @DisplayName("PJ_T-040: Click Favorite Star Icon")
    @Description("Verify clicking favorite star icon redirects to login with favorite URL")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Table Row")
    public void test40_ClickFavoriteStarIcon() {
        // Click first favorite star icon
        projectsPage.clickFirstFavoriteStarIcon();
        
        // Wait for redirect
        Delay.waitFor(1000);
        
        // Assert URL contains "favorite" (redirects to login page with back_url containing favorite)
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("favorite"), 
                   "URL should contain 'favorite'. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Favorite Star Clicked - Login Redirect");
        
        // Navigate back to projects page
        projectsPage.navigateBack();
        Delay.waitDefault();
    }

    @Test
    @Order(41)
    @DisplayName("PJ_T-041: Click Row Menu Add to Favorites")
    @Description("Verify clicking row menu and Add to Favorites redirects to login with favorite URL")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Table Row")
    public void test41_ClickRowMenuAddToFavorites() {
        // Click first row menu button (kebab menu)
        projectsPage.clickFirstRowMenuButton();
        captureScreenshotWithTitle("Row Menu Opened");
        
        // Click Add to Favorites menu item
        projectsPage.clickAddToFavoritesMenuItem();
        
        // Wait for redirect
        Delay.waitFor(1000);
        
        // Assert URL contains "favorite"
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("favorite"), 
                   "URL should contain 'favorite'. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Row Menu Add to Favorites - Login Redirect");
        
        // Navigate back to projects page
        projectsPage.navigateBack();
        Delay.waitDefault();
    }

    @Test
    @Order(42)
    @DisplayName("PJ_T-042: Click pagination 100")
    @Description("Verify clicking pagination 100 works correctly")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Pagination Functionality")
    public void test42_ClickPagination100() {
        projectsPage.clickPagination100();
        assertEquals("https://safe.openproject.com/projects?page=1&per_page=100", projectsPage.getCurrentUrl(),
                "Should navigate to page with 100 items per page");
        captureScreenshotWithTitle("Pagination 100 Clicked");
    }

    @Test
    @Order(43)
    @DisplayName("PJ_T-043: Click pagination 200")
    @Description("Verify clicking pagination 200 works correctly")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Pagination Functionality")
    public void test43_ClickPagination200() {
        projectsPage.clickPagination200();
        assertEquals("https://safe.openproject.com/projects?page=1&per_page=200", projectsPage.getCurrentUrl(),
                "Should navigate to page with 200 items per page");
        captureScreenshotWithTitle("Pagination 200 Clicked");
    }

    @Test
    @Order(44)
    @DisplayName("PJ_T-044: Click pagination 50")
    @Description("Verify clicking pagination 50 works correctly")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Pagination Functionality")
    public void test44_ClickPagination50() {
        projectsPage.clickPagination50();
        assertEquals("https://safe.openproject.com/projects?page=1&per_page=50", projectsPage.getCurrentUrl(),
                "Should navigate to page with 50 items per page");
        captureScreenshotWithTitle("Pagination 50 Clicked");
    }
}
