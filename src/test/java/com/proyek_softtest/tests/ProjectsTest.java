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

        // Navigate to projects page before each test
        driver.get("https://safe.openproject.com/projects");
    }

    // ╔════════════════════════════════════════════════════════════╗
    // ║                      TEST CASES                            ║
    // ╚════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("Test 1: Click Projects Button in Sidebar")
    @Description("Verify clicking projects button in sidebar navigates to projects page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Sidebar Navigation")
    public void test1_ClickProjectsButtonInSidebar() {
        // Click projects button in sidebar
        projectsPage.clickProjectsButtonInSidebar();

        // Assert URL contains /projects
        assertTrue(projectsPage.getCurrentUrl().contains("/projects"),
                "URL should contain /projects");
        captureScreenshotWithTitle("Navigate to Projects Page");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Search Active Projects and Click")
    @Description("Verify searching 'active' then 'projects' filters correctly and clicking active projects navigates to correct URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Sidebar Search")
    public void test2_SearchActiveProjectsAndClick() {
        // Search "active" first
        projectsPage.searchInSidebar("active");
        captureScreenshotWithTitle("Search Active");

        // Then add " projects"
        projectsPage.searchInSidebar("active projects");
        captureScreenshotWithTitle("Search Active Projects");

        // Assert project status filters (ontrack/offtrack/at risk) are NOT displayed
        assertFalse(projectsPage.isOnTrackFilterDisplayed(), "OnTrack filter should not be displayed");
        assertFalse(projectsPage.isOffTrackFilterDisplayed(), "OffTrack filter should not be displayed");
        assertFalse(projectsPage.isAtRiskFilterDisplayed(), "AtRisk filter should not be displayed");

        // Click active projects button
        projectsPage.clickActiveProjectsButtonInSidebar();

        // Assert URL
        assertEquals("https://safe.openproject.com/projects?query_id=active",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/projects?query_id=active");
        captureScreenshotWithTitle("Active Projects Page");
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Search On Track Status and Click")
    @Description("Verify searching 'ontrack' filters correctly and clicking ontrack navigates to correct URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Sidebar Search")
    public void test3_SearchOnTrackStatusAndClick() {
        // Search "ontrack"
        projectsPage.searchInSidebar("on track");
        captureScreenshotWithTitle("Search On Track");

        // Click ontrack filter
        projectsPage.clickOnTrackFilter();

        // Assert URL
        assertEquals("https://safe.openproject.com/projects?query_id=on_track",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/projects?query_id=on_track");
        captureScreenshotWithTitle("On Track Projects Page");
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Search Non-Existent Item")
    @Description("Verify searching for non-existent item 'ABC' shows no items found message")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Sidebar Search")
    public void test4_SearchNonExistentItem() {
        // Search "ABC" (non-existent)
        projectsPage.searchInSidebar("ABC");
        captureScreenshotWithTitle("Search ABC");

        // Assert no items found message is displayed
        assertTrue(projectsPage.isNoItemsFoundInSidebar(),
                "No items found message should be displayed");
        captureScreenshotWithTitle("No Items Found");
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Toggle Project Status Filters")
    @Description("Verify toggling project status shows and hides filters (OnTrack, OffTrack, AtRisk)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Sidebar Toggle")
    public void test5_ToggleProjectStatusFilters() {
        // Click toggle to collapse (hide filters)
        projectsPage.clickProjectStatusToggle();
        captureScreenshotWithTitle("Project Status Collapsed");

        // Assert filters are NOT displayed
        assertFalse(projectsPage.isOnTrackFilterDisplayed(), "OnTrack filter should not be displayed");
        assertFalse(projectsPage.isOffTrackFilterDisplayed(), "OffTrack filter should not be displayed");
        assertFalse(projectsPage.isAtRiskFilterDisplayed(), "AtRisk filter should not be displayed");

        // Click toggle to expand (show filters)
        projectsPage.clickProjectStatusToggle();
        captureScreenshotWithTitle("Project Status Expanded");

        // Assert filters ARE displayed
        assertTrue(projectsPage.isOnTrackFilterDisplayed(), "OnTrack filter should be displayed");
        assertTrue(projectsPage.isOffTrackFilterDisplayed(), "OffTrack filter should be displayed");
        assertTrue(projectsPage.isAtRiskFilterDisplayed(), "AtRisk filter should be displayed");
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Click On Track Filter")
    @Description("Verify clicking On Track filter navigates to correct URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Filter Navigation")
    public void test6_ClickOnTrackFilter() {
        // reset url to projects page
        driver.get("https://safe.openproject.com/projects");

        // Click on track filter
        projectsPage.clickOnTrackFilter();

        // Assert URL
        assertEquals("https://safe.openproject.com/projects?query_id=on_track",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/projects?query_id=on_track");
        captureScreenshotWithTitle("On Track Filter Page");
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Click Off Track Filter")
    @Description("Verify clicking Off Track filter navigates to correct URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Filter Navigation")
    public void test7_ClickOffTrackFilter() {
        // Click off track filter
        projectsPage.clickOffTrackFilter();

        // Assert URL
        assertEquals("https://safe.openproject.com/projects?query_id=off_track",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/projects?query_id=off_track");
        captureScreenshotWithTitle("Off Track Filter Page");
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Click At Risk Filter")
    @Description("Verify clicking At Risk filter navigates to correct URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Filter Navigation")
    public void test8_ClickAtRiskFilter() {
        // Click at risk filter
        projectsPage.clickAtRiskFilter();

        // Assert URL
        assertEquals("https://safe.openproject.com/projects?query_id=at_risk",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/projects?query_id=at_risk");
        captureScreenshotWithTitle("At Risk Filter Page");
    }

    @Test
    @Order(9)
    @DisplayName("Test 9: Click Home Breadcrumb Link")
    @Description("Verify clicking home breadcrumb link navigates to home page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Breadcrumb Navigation")
    public void test9_ClickHomeBreadCrumbLink() {
        // Click home breadcrumb link
        projectsPage.clickHomeBreadCrumbLink();

        // Assert URL
        assertEquals("https://safe.openproject.com/",
                projectsPage.getCurrentUrl(),
                "URL should be https://safe.openproject.com/");
        captureScreenshotWithTitle("Navigate to Home Page");

        projectsPage.navigateBack();
    }

    @Test
    @Order(10)
    @DisplayName("Test 10: Click Projects Breadcrumb Link")
    @Description("Verify clicking projects breadcrumb link navigates to projects page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Breadcrumb Navigation")
    public void test10_ClickProjectsBreadCrumbLink() {
        // Click projects breadcrumb link
        projectsPage.clickProjectsBreadCrumbLink();

        // Assert URL contains /projects
        assertTrue(projectsPage.getCurrentUrl().contains("/projects"),
                "URL should contain /projects");
        captureScreenshotWithTitle("Navigate to Projects Page");
    }

    @Test
    @Order(11)
    @DisplayName("Test 11: Open and Close More Menu")
    @Description("Verify opening more menu shows options and closing hides them")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects More Menu")
    public void test11_OpenAndCloseMoreMenu() {
        // Open more menu
        projectsPage.clickMoreMenuButton();
        // Assert menu items are displayed
        assertTrue(projectsPage.isOpenAsGanttDisplayed(),
                "Open as Gantt option should be displayed");
        captureScreenshotWithTitle("More Menu Opened");

        // Close more menu (click again)
        projectsPage.clickMoreMenuButton();
        captureScreenshotWithTitle("More Menu Closed");

    }

    @Test
    @Order(12)
    @DisplayName("Test 12: Click Open As Gantt from More Menu")
    @Description("Verify clicking Open as Gantt opens new window with gantt URL")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects More Menu")
    public void test12_ClickOpenAsGantt() {
        // Store original window handle
        String originalWindow = driver.getWindowHandle();

        // Open more menu
        projectsPage.clickMoreMenuButton();

        // Click Open as Gantt
        projectsPage.clickOpenAsGantt();

        // Wait for new window and switch to it
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Assert URL contains gantt
        assertTrue(projectsPage.getCurrentUrl().contains("gantt"),
                "URL should contain gantt");
        captureScreenshotWithTitle("Gantt View in New Window");

        // Close new window and switch back to original
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    @Order(13)
    @DisplayName("Test 13: Click Overall Activity from More Menu")
    @Description("Verify clicking Overall Activity navigates to activities page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects More Menu")
    public void test13_ClickOverallActivity() {
        // Open more menu
        projectsPage.clickMoreMenuButton();

        // Click Overall Activity
        projectsPage.clickOverallActivity();

        // Assert URL contains activities
        assertTrue(projectsPage.getCurrentUrl().contains("/activities"),
                "URL should contain /activities");
        captureScreenshotWithTitle("Overall Activity Page");

        // Navigate back to projects page
        projectsPage.navigateBack();
        assertTrue(projectsPage.getCurrentUrl().contains("/projects"),
                "Should be back at projects page");
    }

    @Test
    @Order(14)
    @DisplayName("Test 14: Open Export Dialog and Close")
    @Description("Verify clicking Export opens dialog with 'Export' header and can be closed")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Export")
    public void test14_OpenExportDialogAndClose() {
        // Open more menu
        projectsPage.clickMoreMenuButton();
        captureScreenshotWithTitle("More Menu Opened");

        // Click Export button
        projectsPage.clickExportButton();
        captureScreenshotWithTitle("Export Dialog Opened");

        // Assert dialog header text is "Export"
        String headerText = projectsPage.getExportDialogHeaderText();
        assertEquals("Export", headerText,
                "Export dialog header should be 'Export'");

        // Close dialog
        projectsPage.clickExportDialogCloseButton();
        captureScreenshotWithTitle("Export Dialog Closed");
    }

    @Test
    @Order(15)
    @DisplayName("Test 15: Export to XLS")
    @Description("Verify exporting to XLS format completes successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Export")
    public void test15_ExportToXls() {
        // Open more menu
        projectsPage.clickMoreMenuButton();

        // Click Export button
        projectsPage.clickExportButton();
        captureScreenshotWithTitle("Export Dialog Opened");

        // Click Export XLS button (akan trigger auto download)
        projectsPage.clickExportXlsButton();

        // Assert export completed header is displayed
        assertTrue(projectsPage.isExportCompletedHeaderDisplayed(),
                "Export completed header should be displayed");
        captureScreenshotWithTitle("Export Completed");

        // Get download link type and assert it's Excel type
        String linkType = projectsPage.getDownloadLinkType();
        assertEquals("application/vnd.ms-excel", linkType,
                "Download link should be Excel type");

        // Click download link untuk trigger download manual
        projectsPage.clickDownloadLink();

        // Click close export button
        projectsPage.clickCloseExportButton();
        captureScreenshotWithTitle("Export Dialog Closed After Download");
    }

    @Test
    @Order(16)
    @DisplayName("Test 16: Export to CSV")
    @Description("Verify exporting to CSV format completes successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Export")
    public void test16_ExportToCsv() {
        // Open more menu
        projectsPage.clickMoreMenuButton();

        // Click Export button
        projectsPage.clickExportButton();
        captureScreenshotWithTitle("Export Dialog Opened for CSV");

        // Click Export CSV button
        projectsPage.clickExportCsvButton();

        // Assert export completed header is displayed
        assertTrue(projectsPage.isExportCompletedHeaderDisplayed(),
                "Export completed header should be displayed");
        captureScreenshotWithTitle("CSV Export Completed");

        // Get download link type and assert it's CSV type
        String linkType = projectsPage.getDownloadLinkType();
        assertEquals("text/csv", linkType,
                "Download link should be CSV type");

        // Click download link untuk trigger download
        projectsPage.clickDownloadLink();

        // Click close export button
        projectsPage.clickCloseExportButton();
        captureScreenshotWithTitle("Export Dialog Closed After CSV Download");
    }

    @Test
    @Order(17)
    @DisplayName("Test 17: Export to PDF")
    @Description("Verify exporting to PDF format completes successfully with new tab handling")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Export")
    public void test17_ExportToPdf() {
        // Open more menu
        projectsPage.clickMoreMenuButton();

        // Click Export button
        projectsPage.clickExportButton();
        captureScreenshotWithTitle("Export Dialog Opened for PDF");

        // Store original window handle
        String originalWindow = driver.getWindowHandle();

        // Click Export PDF button (akan otomatis buka tab baru)
        projectsPage.clickExportPdfButton();

        // Assert export completed header is displayed
        assertTrue(projectsPage.isExportCompletedHeaderDisplayed(),
                "Export completed header should be displayed");
        captureScreenshotWithTitle("PDF Export Completed");

        // Switch ke tab baru yang otomatis terbuka
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                captureScreenshotWithTitle("First PDF Tab Opened Automatically");
                Delay.waitFor(2000);
                // Close tab baru ini
                driver.close();
                break;
            }
        }

        // Switch kembali ke original window
        driver.switchTo().window(originalWindow);
        captureScreenshotWithTitle("Back to Original Window");

        // Get download link type and assert it's PDF type
        String linkType = projectsPage.getDownloadLinkType();
        assertEquals("application/pdf", linkType,
                "Download link should be PDF type");

        // Click download link lagi (akan buka tab baru lagi)
        projectsPage.clickDownloadLink();

        // Switch ke tab baru yang kedua
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                captureScreenshotWithTitle("Second PDF Tab from Download Link");
                // Close tab baru ini juga
                driver.close();
                break;
            }
        }

        // Switch kembali ke original window
        driver.switchTo().window(originalWindow);

        // Click close export button
        projectsPage.clickCloseExportButton();
        captureScreenshotWithTitle("Export Dialog Closed After PDF Download");
    }

    @Test
    @Order(18)
    @DisplayName("Test 18: Open and Close Configure View Dialog")
    @Description("Verify configure view dialog can be opened and closed successfully")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Configure View")
    public void test18_OpenAndCloseConfigureViewDialog() {
        // Open more menu
        projectsPage.clickMoreMenuButton();

        // Click Configure View button
        projectsPage.clickConfigureView();

        // Assert configure view dialog header is displayed
        assertTrue(projectsPage.isConfigureViewDialogHeaderDisplayed(),
                "Configure view dialog header should be displayed");
        captureScreenshotWithTitle("Configure View Dialog Opened");

        // Click close button
        projectsPage.clickConfigureViewDialogCloseButton();
        captureScreenshotWithTitle("Configure View Dialog Closed");
    }

    @Test
    @Order(19)
    @DisplayName("Test 19: Switch Between Columns and Sort Tabs")
    @Description("Verify switching between Columns tab and Sort tab in Configure View dialog")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Configure View")
    public void test19_SwitchBetweenColumnsAndSortTabs() {
        // Open more menu
        projectsPage.clickMoreMenuButton();

        // Click Configure View button
        projectsPage.clickConfigureView();
        captureScreenshotWithTitle("Configure View Dialog Opened - Default Columns Tab");

        // Click Sort tab
        projectsPage.clickSortTab();

        // Assert sort field select is displayed
        assertTrue(projectsPage.isSortFieldDisplayed(),
                "Sort field select should be displayed when Sort tab is active");
        captureScreenshotWithTitle("Sort Tab Clicked");

        // Click Columns tab to go back
        projectsPage.clickColumnsTab();
        captureScreenshotWithTitle("Columns Tab Clicked");

        // Click Cancel button
        projectsPage.clickCancelButtonInConfigureView();
    }

    @Test
    @Order(20)
    @DisplayName("Test 20: Select First Combobox Item and Cancel")
    @Description("Verify selecting first combobox item and canceling works correctly")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Configure View")
    public void test20_SelectFirstComboboxItemAndCancel() {
        // Open more menu
        projectsPage.clickMoreMenuButton();

        // Click Configure View button
        projectsPage.clickConfigureView();
        captureScreenshotWithTitle("Configure View Dialog Opened for Combobox Test");

        // Click combobox to open dropdown
        projectsPage.clickConfigureViewCombobox();
        captureScreenshotWithTitle("Combobox Dropdown Opened");

        // Assert dropdown options are visible
        assertTrue(projectsPage.isComboboxDropdownVisible(),
                "Combobox dropdown should be visible after clicking combobox");

        // Get first option text before selecting
        var options = projectsPage.getComboboxOptions();
        String firstOptionText = options.get(0).getText().trim();
        System.out.println("Selecting first option: " + firstOptionText);

        // Select first item (index 0)
        projectsPage.selectComboboxOptionByIndex(0);
        captureScreenshotWithTitle("First Combobox Item Selected");

        // Assert that the selected item appears in the selected columns list
        assertTrue(projectsPage.isColumnSelected(firstOptionText),
                "Selected column '" + firstOptionText + "' should appear in the columns list");

        // Get all selected columns and print them
        var selectedColumns = projectsPage.getSelectedColumnNames();
        System.out.println("Current selected columns: " + selectedColumns);

        // Open combobox again to check if selected item is removed from dropdown
        projectsPage.clickConfigureViewCombobox();
        captureScreenshotWithTitle("Combobox Reopened After Selection");

        // Assert that the selected item is no longer in the dropdown options
        assertFalse(projectsPage.isOptionAvailableInDropdown(firstOptionText),
                "Selected column '" + firstOptionText + "' should not appear in dropdown options anymore");

        System.out.println("Verified: Selected item '" + firstOptionText + "' is removed from dropdown options");

        // Click Cancel button
        projectsPage.clickCancelButtonInConfigureView();
        captureScreenshotWithTitle("Configure View Dialog Canceled");
    }

    @Test
    @Order(21)
    @DisplayName("Test 21: Drag and Drop Selected Columns")
    @Description("Verify dragging 'Public' to first position and 'SAFe solution train' to second position")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Configure View")
    public void test21_DragAndDropSelectedColumns() {
        // Open more menu
        projectsPage.clickMoreMenuButton();

        // Click Configure View button
        projectsPage.clickConfigureView();

        // Print current column order before drag
        var columnsBefore = projectsPage.getSelectedColumnNames();
        System.out.println("Columns before drag: " + columnsBefore);
        captureScreenshotWithTitle("Initial Column Order");

        // Drag "Public" to position 0 (first position, replacing Favorite)
        projectsPage.dragColumnToPosition("Public", 0);
        // Verify "Public" is now at position 0
        int publicPosition = projectsPage.getSelectedColumnPosition("Public");
        assertEquals(0, publicPosition, "Public should be at position 0 after drag");
        captureScreenshotWithTitle("Public Dragged to First Position");
        System.out.println("Verified: 'Public' is now at position 0");

        // Drag "SAFe solution train" to position 1 (second position, replacing Name)
        projectsPage.dragColumnToPosition("SAFe solution train", 1);
        // Verify "SAFe solution train" is now at position 1
        int safePosition = projectsPage.getSelectedColumnPosition("SAFe solution train");
        assertEquals(1, safePosition, "SAFe solution train should be at position 1 after drag");
        captureScreenshotWithTitle("SAFe solution train Dragged to Second Position");
        System.out.println("Verified: 'SAFe solution train' is now at position 1");

        // Print final column order
        var columnsAfter = projectsPage.getSelectedColumnNames();
        System.out.println("Columns after drag: " + columnsAfter);

        // Click Apply button to save changes
        projectsPage.clickApplyButtonInConfigureView();
        captureScreenshotWithTitle("Configure View Applied After Drag and Drop");
    }

    @Test
    @Order(22)
    @DisplayName("Test 22: Remove All Columns Except Name")
    @Description("Verify removing all columns except Name (which cannot be removed) and applying changes")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Configure View")
    public void test22_RemoveAllColumnsExceptName() {
        // Open more menu
        projectsPage.clickMoreMenuButton();

        // Click Configure View button
        projectsPage.clickConfigureView();

        // Get all selected columns before removal
        var columnsBefore = projectsPage.getSelectedColumnNames();
        System.out.println("Columns before removal: " + columnsBefore);

        // Remove all columns except "Name"
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

        // Get remaining columns after removal
        var columnsAfter = projectsPage.getSelectedColumnNames();
        System.out.println("Columns after removal: " + columnsAfter);

        // Assert only "Name" remains
        assertEquals(1, columnsAfter.size(), "Only one column should remain");
        assertEquals("Name", columnsAfter.get(0), "The remaining column should be 'Name'");
        captureScreenshotWithTitle("All Columns Removed Except Name");

        // Click Apply button to save changes
        projectsPage.clickApplyButtonInConfigureView();
        captureScreenshotWithTitle("Configure View Applied After Removing Columns");
    }

    @Test
    @Order(23)
    @DisplayName("Test 23: Select All Combobox Items and Apply")
    @Description("Verify selecting each combobox item one by one and applying works correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Configure View")
    public void test23_SelectAllComboboxItemsAndApply() {
        // Open more menu
        projectsPage.clickMoreMenuButton();

        // Click Configure View button
        projectsPage.clickConfigureView();

        // Click combobox to open dropdown
        projectsPage.clickConfigureViewCombobox();

        // DEBUG: Print all options details
        projectsPage.printComboboxOptionsDebug();

        // Loop sampai tidak ada options lagi (bukan fixed count)
        int selectedCount = 0;
        int maxAttempts = 20; // Safety limit untuk avoid infinite loop

        while (selectedCount < maxAttempts) {
            // Get current available options
            var options = projectsPage.getComboboxOptions();

            // Jika tidak ada options lagi, break
            if (options.isEmpty()) {
                System.out.println("No more options available");
                break;
            }

            // Ambil option pertama (index 0)
            String optionText = options.get(0).getText().trim();
            selectedCount++;
            System.out.println("Selecting option " + selectedCount + ": " + optionText);

            // Click the option (selalu index 0)
            projectsPage.selectComboboxOptionByIndex(0);
            captureScreenshotWithTitle("Selected Option " + selectedCount + ": " + optionText);

            // Buka combobox lagi untuk pilih berikutnya
            projectsPage.clickConfigureViewCombobox();
            Delay.waitFor(100); // Extra wait untuk virtual scrolling update
        }

        System.out.println("Total options selected: " + selectedCount);

        // Setelah semua item dipilih, buka combobox lagi untuk verify tidak ada item
        // tersisa
        projectsPage.clickConfigureViewCombobox();
        captureScreenshotWithTitle("Combobox After All Items Selected");

        // Assert "No items found" message is displayed
        assertTrue(projectsPage.isNoItemsFoundDisplayed(),
                "No items found message should be displayed after all items are selected");

        System.out.println("Verified: All items selected, no more options available in dropdown");

        // Click Apply button
        projectsPage.clickApplyButtonInConfigureView();
        captureScreenshotWithTitle("Configure View Applied After Selecting All Items");
    }

    @Test
    @Order(24)
    @DisplayName("Test 24: Sort by Name Descending and Cancel")
    @Description("Verify selecting sort field and canceling does not apply the sort")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Configure View")
    public void test24_SortByNameDescAndCancel() {
        // Open more menu
        projectsPage.clickMoreMenuButton();
        
        // Click Configure View button
        projectsPage.clickConfigureView();
        
        // Click Sort tab
        projectsPage.clickSortTab();
        
        // Select Name from sort field dropdown (value="name")
        projectsPage.selectSortFieldByValue("name");
        captureScreenshotWithTitle("Name Selected in Sort Field");
        
        // Click Descending button (index 0 for first sort row)
        projectsPage.clickSortDescending(0);
        captureScreenshotWithTitle("Sort Descending Clicked");
        
        // Click Cancel button - should NOT apply the sort
        projectsPage.clickCancelButtonInConfigureView();
        
        // Assert URL does NOT contain sortBy (cancel should not apply)
        assertFalse(projectsPage.getCurrentUrl().contains("sortBy"), 
                    "URL should not contain sortBy after cancel");
        captureScreenshotWithTitle("Sort Canceled - URL Unchanged");
    }

    @Test
    @Order(25)
    @DisplayName("Test 25: Sort by Name and Status Descending and Apply")
    @Description("Verify selecting multiple sort fields (Name desc, Status desc) and applying")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Configure View")
    public void test25_SortByNameAndStatusDescAndApply() {
        // Open more menu
        projectsPage.clickMoreMenuButton();
        
        // Click Configure View button
        projectsPage.clickConfigureView();
        
        // Click Sort tab
        projectsPage.clickSortTab();
        
        // Select Name from first sort field (value="name")
        projectsPage.selectNthSortFieldByValue(0, "name");
        captureScreenshotWithTitle("First Sort: Name Selected");
        
        // Click Descending for first sort field
        projectsPage.clickSortDescending(0);
        
        // Assert second sort field appeared
        assertTrue(projectsPage.getSortFieldCount() >= 2, 
                   "Second sort field should appear after selecting first");
        
        // Select Status from second sort field (value="project_status")
        projectsPage.selectNthSortFieldByValue(1, "project_status");
        captureScreenshotWithTitle("Second Sort: Status Selected");
        
        // Click Descending for second sort field
        projectsPage.clickSortDescending(1);
        
        // Click Apply button
        projectsPage.clickApplyButtonInConfigureView();
        captureScreenshotWithTitle("Sort Applied");
        
        // Assert URL contains sortBy with name and project_status
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
    @DisplayName("Test 26: Reset Sort by Selecting Empty Value and Apply")
    @Description("Verify selecting empty value '-' for both sort fields and applying clears the sort")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Configure View")
    public void test26_ResetSortBySelectingEmptyAndApply() {
        // Open more menu
        projectsPage.clickMoreMenuButton();
        
        // Click Configure View button
        projectsPage.clickConfigureView();
        
        // Click Sort tab
        projectsPage.clickSortTab();
        
        // Select empty value "-" for first sort field (index 0)
        projectsPage.selectNthSortFieldByValue(0, "");
        captureScreenshotWithTitle("First Sort Field Reset to Empty");
        
        // Click Apply button
        projectsPage.clickApplyButtonInConfigureView();
        captureScreenshotWithTitle("Sort Reset Applied");
        
        // Assert URL contains sortBy=%5B%5D (empty array)
        String currentUrl = projectsPage.getCurrentUrl();
        assertTrue(currentUrl.contains("sortBy=%5B%5D") || currentUrl.contains("sortBy=[]"), 
                   "URL should contain empty sortBy array after reset");
        System.out.println("Final URL after reset: " + currentUrl);
    }

    @Test
    @Order(27)
    @DisplayName("Test 27: Toggle Zen Mode On and Off")
    @Description("Verify clicking zen mode toggles fullscreen on and then off")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects More Menu")
    public void test27_ToggleZenModeOnAndOff() {
        // Open more menu
        projectsPage.clickMoreMenuButton();
        
        // Click Zen Mode to enable fullscreen
        projectsPage.clickZenMode();
        
        // Assert fullscreen is now active
        assertTrue(projectsPage.isFullscreen(), "Should be in fullscreen mode after clicking Zen Mode");
        captureScreenshotWithTitle("Zen Mode Enabled - Fullscreen");
        
        // Open more menu again (menu closes after clicking zen mode)
        projectsPage.clickMoreMenuButton();
        
        // Click Zen Mode again to disable fullscreen
        projectsPage.clickZenMode();
        
        // Assert fullscreen is now disabled
        assertFalse(projectsPage.isFullscreen(), "Should NOT be in fullscreen mode after clicking Zen Mode again");
        captureScreenshotWithTitle("Zen Mode Disabled - Normal View");
    }

    @Test
    @Order(28)
    @DisplayName("Test 28: Search Project Not Found")
    @Description("Verify searching for non-existent project shows no results message")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Searchbar")
    public void test28_SearchProjectNotFound() {
        // Get search keyword from test-data.properties
        String searchKeyword = TestData.getProjectsNoResultsSearch();
        
        Delay.waitDefault();

        // Type in searchbar
        projectsPage.typeInSearchbar(searchKeyword);
        
        // Assert no results message is displayed
        assertTrue(projectsPage.isNoResultsDisplayed(), 
                   "No results message should be displayed for search: " + searchKeyword);
        captureScreenshotWithTitle("Search Not Found - " + searchKeyword);
        
        // Click clear button to reset searchbar
        projectsPage.clickClearSearchbar();
        captureScreenshotWithTitle("Searchbar Cleared");
    }

    @Test
    @Order(29)
    @DisplayName("Test 29: Search Project Found and Navigate")
    @Description("Verify searching for existing project shows result and navigating to project works")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Searchbar")
    public void test29_SearchProjectFoundAndNavigate() {
        // Get test data from test-data.properties
        String searchKeyword = TestData.getProjectsValidSearchName();
        String expectedLinkText = TestData.getProjectsExpectedLinkText();
        
        // Type in searchbar
        projectsPage.typeInSearchbar(searchKeyword);
        captureScreenshotWithTitle("Search Results for - " + searchKeyword);
        
        Delay.waitDefault();
        
        // Click project link
        projectsPage.clickProjectLinkByText(expectedLinkText);
        
        // Assert project title is displayed on project page
        assertTrue(projectsPage.isProjectTitleDisplayed(expectedLinkText), 
                   "Project title '" + expectedLinkText + "' should be displayed");
        captureScreenshotWithTitle("Project Page - " + expectedLinkText);
        
        // Navigate back to projects list
        driver.navigate().back();
        Delay.waitDefault();
        
        // Click clear button to reset searchbar
        projectsPage.clickClearSearchbar();
        captureScreenshotWithTitle("Searchbar Cleared After Navigation");
    }

    @Test
    @Order(30)
    @DisplayName("Test 30: Open and Close Filter Panel")
    @Description("Verify opening and closing filter panel using both button and icon")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Filter")
    public void test30_OpenAndCloseFilterPanel() {
        // Open filter panel
        projectsPage.openFilter();
        
        // Assert filter value container is displayed
        assertTrue(projectsPage.isFilterValueContainerDisplayed(), 
                   "Filter value container should be visible when filter is open");
        captureScreenshotWithTitle("Filter Panel Opened");
        
        // Close filter using the main button
        projectsPage.closeFilterByButton();
        captureScreenshotWithTitle("Filter Closed by Button");
        
        // Open filter again
        projectsPage.openFilter();
        
        // Close filter using the close icon
        projectsPage.closeFilterByIcon();

        captureScreenshotWithTitle("Filter Closed by Icon");
    }

    @Test
    @Order(31)
    @DisplayName("Test 31: Toggle Active Filter and Remove")
    @Description("Verify toggling active filter switch shows no results and removing filter resets view")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Filter")
    public void test31_ToggleActiveFilterAndRemove() {
        // Open filter panel
        projectsPage.openFilter();
        
        // Click active filter switch to disable it
        projectsPage.clickActiveFilterSwitch();
        
        // Assert no results is displayed when active filter is disabled
        assertTrue(projectsPage.isNoResultsDisplayed(), 
                   "No results message should be displayed when Active filter is disabled");
        captureScreenshotWithTitle("Active Filter Disabled - No Results");
        
        // Click active filter switch again to enable it
        projectsPage.clickActiveFilterSwitch();
        captureScreenshotWithTitle("Active Filter Enabled Again");
        
        // Remove the active filter using X button
        projectsPage.clickRemoveActiveFilter();
        captureScreenshotWithTitle("Active Filter Removed");
        
        // Close filter panel
        projectsPage.closeFilterByButton();
        captureScreenshotWithTitle("Filter Panel Closed After Remove");
    }

    @Test
    @Order(32)
    @DisplayName("Test 32: Add Public Filter from Dropdown")
    @Description("Verify adding public filter from add filter dropdown, toggle it, and remove it")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Filter")
    public void test32_AddPublicFilterFromDropdown() {
        // Open filter panel
        projectsPage.openFilter();
        
        // Add public filter from dropdown (value="public")
        projectsPage.addFilterByValue("public");
        
        // Assert public filter is displayed
        assertTrue(projectsPage.isPublicFilterDisplayed(), 
                   "Public filter should be displayed after adding");
        captureScreenshotWithTitle("Public Filter Added");
        
        // Toggle public filter switch
        projectsPage.clickPublicFilterSwitch();
        captureScreenshotWithTitle("Public Filter Toggled");
        
        // Remove public filter
        projectsPage.clickRemovePublicFilter();
        captureScreenshotWithTitle("Public Filter Removed");
        
        // Close filter panel
        projectsPage.closeFilterByButton();
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║              TABLE HEADER NAME COLUMN TESTS                    ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(33)
    @DisplayName("Test 33: Name Column - Sort Descending")
    @Description("Verify clicking Sort Descending from Name column header menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Table Header")
    public void test33_NameColumnSortDescending() {
        // Click Name column header
        projectsPage.clickNameColumnHeader();
        captureScreenshotWithTitle("Name Column Menu Opened");
        
        // Click Sort Descending
        projectsPage.clickNameSortDescending();
        
        // Wait for page to reload with new URL
        Delay.waitDefault();
        
        // Assert URL contains sortBy with name desc
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("sortBy"), "URL should contain sortBy parameter. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Sorted by Name Descending");
    }

    @Test
    @Order(34)
    @DisplayName("Test 34: Name Column - Sort Ascending")
    @Description("Verify clicking Sort Ascending from Name column header menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Table Header")
    public void test34_NameColumnSortAscending() {
        // Click Name column header
        projectsPage.clickNameColumnHeader();
        
        // Click Sort Ascending
        projectsPage.clickNameSortAscending();
        
        Delay.waitDefault();

        // Assert URL contains sortBy
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("sortBy"), "URL should contain sortBy parameter");
        captureScreenshotWithTitle("Sorted by Name Ascending");
    }

    @Test
    @Order(35)
    @DisplayName("Test 35: Name Column - Filter By")
    @Description("Verify clicking Filter By from Name column header, select project, and remove filter")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Table Header")
    public void test35_NameColumnFilterBy() {
        // Click Name column header
        projectsPage.clickNameColumnHeader();
        
        // Click Filter By
        projectsPage.clickNameFilterBy();
        captureScreenshotWithTitle("Filter By Opened");
        
        // Select project from dropdown (from test-data.properties)
        String projectName = TestData.getTableHeaderFilterByProject();
        projectsPage.selectProjectFromFilterDropdown(projectName);
        captureScreenshotWithTitle("Project Selected - " + projectName);
        
        // Assert project link is displayed
        assertTrue(projectsPage.isProjectLinkDisplayed(projectName), 
                   "Project link '" + projectName + "' should be displayed");
        
        // Remove the filter
        projectsPage.clickRemoveIdFilter();
        captureScreenshotWithTitle("Filter Removed");
        
        // Close filter panel
        projectsPage.closeFilterByIcon();
    }

    @Test
    @Order(36)
    @DisplayName("Test 36: Name Column - Move Column Left")
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
    @DisplayName("Test 37: Name Column - Move Column Right")
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
    @DisplayName("Test 38: Name Column - Add Column (Opens Configure View)")
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
    @DisplayName("Test 39: Name Column - Remove Column")
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
    @DisplayName("Test 40: Click Favorite Star Icon")
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
    @DisplayName("Test 41: Click Row Menu Add to Favorites")
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
    @DisplayName("Test 42: Click pagination 100")
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
    @DisplayName("Test 43: Click pagination 200")
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
    @DisplayName("Test 44: Click pagination 50")
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
