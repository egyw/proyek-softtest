package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.ProjectsPage;
import com.proyek_softtest.utils.Delay;

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
    @DisplayName("Test 19: Select First Combobox Item and Cancel")
    @Description("Verify selecting first combobox item and canceling works correctly")
    @Severity(SeverityLevel.NORMAL)
    @Story("Projects Configure View")
    public void test19_SelectFirstComboboxItemAndCancel() {
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
    @Order(20)
    @DisplayName("Test 20: Select All Combobox Items and Apply")
    @Description("Verify selecting each combobox item one by one and applying works correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Projects Configure View")
    public void test20_SelectAllComboboxItemsAndApply() {
        // Open more menu
        projectsPage.clickMoreMenuButton();
        
        // Click Configure View button
        projectsPage.clickConfigureView();
        captureScreenshotWithTitle("Configure View Dialog Opened for All Items Test");
        
        // Click combobox to open dropdown
        projectsPage.clickConfigureViewCombobox();
        captureScreenshotWithTitle("Combobox Dropdown Opened");
        
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
        
        // Setelah semua item dipilih, buka combobox lagi untuk verify tidak ada item tersisa
        projectsPage.clickConfigureViewCombobox();
        captureScreenshotWithTitle("Combobox After All Items Selected");
        
        // Assert "No items found" message is displayed
        assertTrue(projectsPage.isNoItemsFoundDisplayed(), 
                   "No items found message should be displayed after all items are selected");
        
        System.out.println("Verified: All items selected, no more options available in dropdown");
        
        // Click Apply button
        projectsPage.clickApplyButtonInConfigureView();
        captureScreenshotWithTitle("Configure View Applied After Selecting All Items");
        Delay.waitFor(10000);
    }
}
