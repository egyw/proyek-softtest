package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.MeetingsPage;
import com.proyek_softtest.utils.Delay;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Meetings Module")
@Feature("Meetings Page")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeetingsTest extends BaseTest {
    private MeetingsPage meetingsPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting Meetings Test...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        meetingsPage = new MeetingsPage(driver);
        
        // Navigate to Meetings page
        driver.get("https://safe.openproject.com/meetings");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   SIDEBAR TESTS                                ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("Test 1: Click Meetings Sidebar Button")
    @Description("Verify clicking Meetings sidebar button stays on meetings page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sidebar Navigation")
    public void test1_ClickMeetingsSidebarButton() {
        meetingsPage.clickMeetingsSidebarButton();
        Delay.waitFor(1000);
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/meetings"),
                   "URL should be https://safe.openproject.com/meetings. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Meetings Sidebar Clicked");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Click Recurring Meetings Type")
    @Description("Verify clicking Recurring Meetings type navigates to correct URL")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test2_ClickRecurringMeetingsType() {
        meetingsPage.clickRecurringMeetingsType();
        Delay.waitFor(1000);
        
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://safe.openproject.com/meetings?filters=%5B%7B%22type%22%3A%7B%22operator%22%3A%22%3D%22%2C%22values%22%3A%5B%22t%22%5D%7D%7D%5D&sort=start_time";
        assertTrue(currentUrl.equals(expectedUrl),
                   "URL should match expected. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Recurring Meetings Type Clicked");
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Click All Meetings Type")
    @Description("Verify clicking All Meetings type navigates to correct URL")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test3_ClickAllMeetingsType() {
        meetingsPage.clickAllMeetingsType();
        Delay.waitFor(1000);
        
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://safe.openproject.com/meetings?filters=%5B%7B%22invited_user_id%22%3A%7B%22operator%22%3A%22%2A%22%2C%22values%22%3A%5B%5D%7D%7D%5D";
        assertTrue(currentUrl.equals(expectedUrl),
                   "URL should match expected. Current URL: " + currentUrl);
        captureScreenshotWithTitle("All Meetings Type Clicked");
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Toggle Meetings Series Dropdown")
    @Description("Verify toggling Meetings Series dropdown - close then open, check Sprint Review visible")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test4_ToggleMeetingsSeriesDropdown() {
        Delay.waitFor(1000);

        // First click - close dropdown
        meetingsPage.clickMeetingsSeriesDropdown();
        captureScreenshotWithTitle("Meetings Series Dropdown Closed");
        
        // Second click - open dropdown
        meetingsPage.clickMeetingsSeriesDropdown();
        captureScreenshotWithTitle("Meetings Series Dropdown Opened");
        
        // Assert Sprint Review button is visible
        assertTrue(meetingsPage.isSprintReviewButtonVisible(),
                   "Sprint Review button should be visible when dropdown is open");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   BREADCRUMB TESTS                             ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(5)
    @DisplayName("Test 5: Click Home Breadcrumb Link")
    @Description("Verify clicking Home breadcrumb link navigates to home page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Breadcrumb Navigation")
    public void test5_ClickHomeBreadCrumbLink() {
        meetingsPage.clickHomeBreadCrumbLink();
        Delay.waitFor(1000);
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/"),
                   "URL should be home page. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Home Breadcrumb Clicked");

        meetingsPage.navigateBack();
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Click Meetings Breadcrumb Link")
    @Description("Verify clicking Meetings breadcrumb link stays on meetings page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Breadcrumb Navigation")
    public void test6_ClickMeetingsBreadCrumbLink() {
        meetingsPage.clickMeetingsBreadCrumbLink();
        Delay.waitFor(1000);
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings"),
                   "URL should contain /meetings. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Meetings Breadcrumb Clicked");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   FILTER TESTS                                 ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(7)
    @DisplayName("Test 7: Click Past Filter")
    @Description("Verify clicking Past filter navigates to correct URL")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter Actions")
    public void test7_ClickPastFilter() {
        meetingsPage.clickPastFilterButton();
        Delay.waitFor(1000);
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("upcoming=false"),
                   "URL should contain upcoming=false. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Past Filter Clicked");
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Click Upcoming Filter")
    @Description("Verify clicking Upcoming filter navigates to correct URL")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter Actions")
    public void test8_ClickUpcomingFilter() {
        meetingsPage.clickUpcomingFilterButton();
        Delay.waitFor(1000);
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("upcoming=true"),
                   "URL should contain upcoming=true. Current URL: " + currentUrl);
        captureScreenshotWithTitle("Upcoming Filter Clicked");
    }

    @Test
    @Order(9)
    @DisplayName("Test 9: Toggle Filters Button")
    @Description("Verify toggling Filters button - close then open, check Apply button visible")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter Actions")
    public void test9_ToggleFiltersButton() {
        // First click - open filters (default is closed)
        meetingsPage.clickFiltersButton();
        captureScreenshotWithTitle("Filters Opened");
        
        // Assert Apply button is visible
        assertTrue(meetingsPage.isApplyFiltersButtonVisible(),
                   "Apply Filters button should be visible when filters panel is open");

        // Second click - close filters using Filters button
        meetingsPage.clickFiltersButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Filters Closed");

        // Third - open filters again
        meetingsPage.clickFiltersButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Filters Opened Again");

        // Close using Close Form button (X icon)
        meetingsPage.clickCloseFormButton();
        captureScreenshotWithTitle("Filters Closed with Close Form Button");
    }

    @Test
    @Order(10)
    @DisplayName("Test 10: Open Filters and Select All Available Filters")
    @Description("Verify opening filters, selecting all filter options, and applying")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Filter Actions")
    public void test10_SelectAllFiltersAndApply() {
        // Open filters panel
        meetingsPage.clickFiltersButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Filters Panel Opened");
        
        // Select all available filters
        int selectedCount = meetingsPage.selectAllAvailableFilters();
        System.out.println("Selected filters: " + selectedCount);
        captureScreenshotWithTitle("All Filters Selected");
        
        // Click Apply
        meetingsPage.clickApplyFiltersButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Filters Applied");
        
        // Assert URL contains filters
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("filters="),
                   "URL should contain filters parameter. Current URL: " + currentUrl);
    }

    @Test
    @Order(11)
    @DisplayName("Test 11: Select Filters, Toggle Switch, Select Project and Apply")
    @Description("Verify selecting filters, turning off meeting series switch, selecting Scrum project, and applying")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Filter Actions")
    public void test11_FiltersWithSwitchAndProjectSelect() {
        // Open filters panel
        meetingsPage.clickFiltersButton();
        Delay.waitFor(500);
        
        // Select all available filters
        meetingsPage.selectAllAvailableFilters();
        captureScreenshotWithTitle("Filters Selected");
        
        // Click Meeting Series switch to turn it off
        meetingsPage.clickMeetingSeriesSwitch();
        captureScreenshotWithTitle("Meeting Series Switch Toggled");
        
        // Click Project ng-select and select Scrum project
        meetingsPage.clickProjectNgSelect();
        captureScreenshotWithTitle("Project Dropdown Opened");
        
        meetingsPage.selectProjectFromDropdown("Scrum project");
        captureScreenshotWithTitle("Scrum Project Selected");
        
        // Click Apply
        meetingsPage.clickApplyFiltersButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Filters Applied with Project");
        
        // Assert URL contains filters
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("filters="),
                   "URL should contain filters parameter. Current URL: " + currentUrl);
    }

    @Test
    @Order(12)
    @DisplayName("Test 12: Remove Default Filter and Apply")
    @Description("Verify removing the first default filter and applying")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter Actions")
    public void test12_RemoveFilterAndApply() {
        // Open filters panel
        meetingsPage.clickFiltersButton();
        Delay.waitFor(1100);
        captureScreenshotWithTitle("Filters Panel Opened");
        
        // Remove the first filter
        meetingsPage.clickRemoveFirstFilter();
        Delay.waitFor(500);
        captureScreenshotWithTitle("First Filter Removed");
        Delay.waitFor(1000);
        
        // Click Apply
        meetingsPage.clickApplyFiltersButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Filter Removal Applied");
        
        // Assert URL still contains meetings
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings"),
                   "URL should contain /meetings. Current URL: " + currentUrl);
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   TABLE TESTS                                  ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(13)
    @DisplayName("Test 13: Click Table Title Link")
    @Description("Verify clicking meeting title link navigates to meeting details")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Actions")
    public void test13_ClickTableTitleLink() {
        // Navigate to meetings with empty filter to show table
        driver.get("https://safe.openproject.com/meetings?filters=%5B%5D");
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meetings Table Loaded");
        
        // Click first title link
        meetingsPage.clickFirstTableTitleLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Title Link Clicked");
        
        // Assert URL contains meetings detail
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings/"),
                   "URL should contain /meetings/. Current URL: " + currentUrl);
        
        // Navigate back
        meetingsPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(14)
    @DisplayName("Test 14: Click Table Date Time Link")
    @Description("Verify clicking date/time link navigates to recurring meetings")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Actions")
    public void test14_ClickTableDateTimeLink() {
        // Navigate to meetings with empty filter to show table
        driver.get("https://safe.openproject.com/meetings?filters=%5B%5D");
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meetings Table Loaded");
        
        // Click first date/time link
        meetingsPage.clickFirstTableDateTimeLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Date Time Link Clicked");
        
        // Assert URL contains recurring_meetings
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/recurring_meetings/"),
                   "URL should contain /recurring_meetings/. Current URL: " + currentUrl);
        
        // Navigate back
        meetingsPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(15)
    @DisplayName("Test 15: Click Table Project Link")
    @Description("Verify clicking project link navigates to project page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Actions")
    public void test15_ClickTableProjectLink() {
        // Navigate to meetings with empty filter to show table
        driver.get("https://safe.openproject.com/meetings?filters=%5B%5D");
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meetings Table Loaded");
        
        // Click first project link
        meetingsPage.clickFirstTableProjectLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Project Link Clicked");
        
        // Assert URL contains projects
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/projects/"),
                   "URL should contain /projects/. Current URL: " + currentUrl);
        
        // Navigate back
        meetingsPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(16)
    @DisplayName("Test 16: Click Actions Menu - View Meeting Series")
    @Description("Verify clicking actions menu and View Meeting Series link")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Actions")
    public void test16_ClickActionsMenuViewMeetingSeries() {
        // Navigate to meetings with empty filter to show table
        driver.get("https://safe.openproject.com/meetings?filters=%5B%5D");
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meetings Table Loaded");
        
        // Click actions menu button
        meetingsPage.clickFirstActionsMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Actions Menu Opened");
        
        // Click View Meeting Series
        meetingsPage.clickViewMeetingSeriesLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("View Meeting Series Clicked");
        
        // Assert URL contains recurring_meetings
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/recurring_meetings/"),
                   "URL should contain /recurring_meetings/. Current URL: " + currentUrl);
        
        // Navigate back
        meetingsPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(17)
    @DisplayName("Test 17: Click Actions Menu - Download iCalendar Event")
    @Description("Verify clicking actions menu and Download iCalendar Event link")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Actions")
    public void test17_ClickActionsMenuDownloadICalendar() {
        // Navigate to meetings with empty filter to show table
        driver.get("https://safe.openproject.com/meetings?filters=%5B%5D");
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meetings Table Loaded");
        
        // Click actions menu button
        meetingsPage.clickFirstActionsMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Actions Menu Opened");
        
        // Click Download iCalendar Event
        meetingsPage.clickDownloadICalendarLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Download iCalendar Clicked");
        
        // Assert - just verify no error (download should trigger)
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings"),
                   "Should still be on meetings page. Current URL: " + currentUrl);
    }
}
