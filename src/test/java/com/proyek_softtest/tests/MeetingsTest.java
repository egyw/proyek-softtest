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
        
        driver.get("https://safe.openproject.com/meetings");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   SIDEBAR TESTS                                ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("MT_T-001: Click Meetings Sidebar Button")
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
    @DisplayName("MT_T-002: Click Recurring Meetings Type")
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
    @DisplayName("MT_T-003: Click All Meetings Type")
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
    @DisplayName("MT_T-004: Toggle Meetings Series Dropdown")
    @Description("Verify toggling Meetings Series dropdown - close then open, check Sprint Review visible")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sidebar Navigation")
    public void test4_ToggleMeetingsSeriesDropdown() {
        Delay.waitFor(1000);

        meetingsPage.clickMeetingsSeriesDropdown();
        captureScreenshotWithTitle("Meetings Series Dropdown Closed");
        
        meetingsPage.clickMeetingsSeriesDropdown();
        captureScreenshotWithTitle("Meetings Series Dropdown Opened");
        
        assertTrue(meetingsPage.isSprintReviewButtonVisible(),
                   "Sprint Review button should be visible when dropdown is open");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   BREADCRUMB TESTS                             ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(5)
    @DisplayName("MT_T-005: Click Home Breadcrumb Link")
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
    @DisplayName("MT_T-006: Click Meetings Breadcrumb Link")
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
    @DisplayName("MT_T-007: Click Past Filter")
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
    @DisplayName("MT_T-008: Click Upcoming Filter")
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
    @DisplayName("MT_T-009: Toggle Filters Button")
    @Description("Verify toggling Filters button - close then open, check Apply button visible")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter Actions")
    public void test9_ToggleFiltersButton() {
        meetingsPage.clickFiltersButton();
        captureScreenshotWithTitle("Filters Opened");
        
        assertTrue(meetingsPage.isApplyFiltersButtonVisible(),
                   "Apply Filters button should be visible when filters panel is open");

        meetingsPage.clickFiltersButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Filters Closed");

        meetingsPage.clickFiltersButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Filters Opened Again");

        meetingsPage.clickCloseFormButton();
        captureScreenshotWithTitle("Filters Closed with Close Form Button");
    }

    @Test
    @Order(10)
    @DisplayName("MT_T-010: Open Filters and Select All Available Filters")
    @Description("Verify opening filters, selecting all filter options, and applying")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Filter Actions")
    public void test10_SelectAllFiltersAndApply() {
        meetingsPage.clickFiltersButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Filters Panel Opened");
        
        int selectedCount = meetingsPage.selectAllAvailableFilters();
        System.out.println("Selected filters: " + selectedCount);
        captureScreenshotWithTitle("All Filters Selected");
        
        meetingsPage.clickApplyFiltersButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Filters Applied");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("filters="),
                   "URL should contain filters parameter. Current URL: " + currentUrl);
    }

    @Test
    @Order(11)
    @DisplayName("MT_T-011: Select Filters, Toggle Switch, Select Project and Apply")
    @Description("Verify selecting filters, turning off meeting series switch, selecting Scrum project, and applying")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Filter Actions")
    public void test11_FiltersWithSwitchAndProjectSelect() {
        meetingsPage.clickFiltersButton();
        Delay.waitFor(500);
        
        meetingsPage.selectAllAvailableFilters();
        captureScreenshotWithTitle("Filters Selected");
        
        meetingsPage.clickMeetingSeriesSwitch();
        captureScreenshotWithTitle("Meeting Series Switch Toggled");
        
        meetingsPage.clickProjectNgSelect();
        captureScreenshotWithTitle("Project Dropdown Opened");
        
        meetingsPage.selectProjectFromDropdown("Scrum project");
        captureScreenshotWithTitle("Scrum Project Selected");
        
        meetingsPage.clickApplyFiltersButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Filters Applied with Project");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("filters="),
                   "URL should contain filters parameter. Current URL: " + currentUrl);
    }

    @Test
    @Order(12)
    @DisplayName("MT_T-012: Remove Default Filter and Apply")
    @Description("Verify removing the first default filter and applying")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filter Actions")
    public void test12_RemoveFilterAndApply() {
        meetingsPage.clickFiltersButton();
        Delay.waitFor(1100);
        captureScreenshotWithTitle("Filters Panel Opened");
        
        meetingsPage.clickRemoveFirstFilter();
        Delay.waitFor(500);
        captureScreenshotWithTitle("First Filter Removed");
        Delay.waitFor(1000);
        
        meetingsPage.clickApplyFiltersButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Filter Removal Applied");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings"),
                   "URL should contain /meetings. Current URL: " + currentUrl);
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   TABLE TESTS                                  ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(13)
    @DisplayName("MT_T-013: Click Table Title Link")
    @Description("Verify clicking meeting title link navigates to meeting details")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Actions")
    public void test13_ClickTableTitleLink() {
        driver.get("https://safe.openproject.com/meetings?filters=%5B%5D");
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meetings Table Loaded");
        
        meetingsPage.clickFirstTableTitleLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Title Link Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings/"),
                   "URL should contain /meetings/. Current URL: " + currentUrl);
        
        meetingsPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(14)
    @DisplayName("MT_T-014: Click Table Date Time Link")
    @Description("Verify clicking date/time link navigates to recurring meetings")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Actions")
    public void test14_ClickTableDateTimeLink() {
        driver.get("https://safe.openproject.com/meetings?filters=%5B%5D");
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meetings Table Loaded");
        
        meetingsPage.clickFirstTableDateTimeLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Date Time Link Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/recurring_meetings/"),
                   "URL should contain /recurring_meetings/. Current URL: " + currentUrl);
        
        meetingsPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(15)
    @DisplayName("MT_T-015: Click Table Project Link")
    @Description("Verify clicking project link navigates to project page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Actions")
    public void test15_ClickTableProjectLink() {
        driver.get("https://safe.openproject.com/meetings?filters=%5B%5D");
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meetings Table Loaded");
        
        meetingsPage.clickFirstTableProjectLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Project Link Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/projects/"),
                   "URL should contain /projects/. Current URL: " + currentUrl);
        
        meetingsPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(16)
    @DisplayName("MT_T-016: Click Actions Menu - View Meeting Series")
    @Description("Verify clicking actions menu and View Meeting Series link")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Actions")
    public void test16_ClickActionsMenuViewMeetingSeries() {
        driver.get("https://safe.openproject.com/meetings?filters=%5B%5D");
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meetings Table Loaded");
        
        meetingsPage.clickFirstActionsMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Actions Menu Opened");
        
        meetingsPage.clickViewMeetingSeriesLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("View Meeting Series Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/recurring_meetings/"),
                   "URL should contain /recurring_meetings/. Current URL: " + currentUrl);
        
        meetingsPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(17)
    @DisplayName("MT_T-017: Click Actions Menu - Download iCalendar Event")
    @Description("Verify clicking actions menu and Download iCalendar Event link")
    @Severity(SeverityLevel.NORMAL)
    @Story("Table Actions")
    public void test17_ClickActionsMenuDownloadICalendar() {
        driver.get("https://safe.openproject.com/meetings?filters=%5B%5D");
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meetings Table Loaded");
        
        meetingsPage.clickFirstActionsMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Actions Menu Opened");
        
        meetingsPage.clickDownloadICalendarLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Download iCalendar Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings"),
                   "Should still be on meetings page. Current URL: " + currentUrl);
    }
}
