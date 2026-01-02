package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.RecurringMeetingsPage;
import com.proyek_softtest.utils.Delay;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Meetings Module")
@Feature("Recurring Meetings Page")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecurringMeetingsTest extends BaseTest {
    private RecurringMeetingsPage recurringMeetingsPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting Recurring Meetings Test...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        recurringMeetingsPage = new RecurringMeetingsPage(driver);
        
        // Navigate to Recurring Meetings page
        driver.get("https://safe.openproject.com/projects/art-0-test-release-train/recurring_meetings/1");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   MORE MENU TESTS                              ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("Test 1: Toggle More Menu Button")
    @Description("Verify opening and closing more menu button, assert Download iCalendar visible when open")
    @Severity(SeverityLevel.NORMAL)
    @Story("More Menu Actions")
    public void test1_ToggleMoreMenuButton() {
        // First click - open menu
        recurringMeetingsPage.clickMoreMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("More Menu Opened");
        
        // Assert Download iCalendar is visible when menu is open
        assertTrue(recurringMeetingsPage.isDownloadICalendarLinkVisible(),
                   "Download iCalendar link should be visible when menu is open");
        
        // Second click - close menu
        recurringMeetingsPage.clickMoreMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("More Menu Closed");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Click Download iCalendar Event")
    @Description("Verify clicking Download iCalendar event link")
    @Severity(SeverityLevel.NORMAL)
    @Story("More Menu Actions")
    public void test2_ClickDownloadICalendar() {
        // Open menu
        recurringMeetingsPage.clickMoreMenuButton();
        Delay.waitFor(500);
        
        // Click download iCalendar
        recurringMeetingsPage.clickDownloadICalendarLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Download iCalendar Clicked");
        
        // Assert URL unchanged (still on same page)
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/recurring_meetings/"),
                   "URL should still contain /recurring_meetings/. Current URL: " + currentUrl);
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   TAB SWITCH TESTS                             ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(3)
    @DisplayName("Test 3: Switch Between Past and Upcoming Tabs")
    @Description("Verify switching between Past and Upcoming tabs")
    @Severity(SeverityLevel.NORMAL)
    @Story("Tab Navigation")
    public void test3_SwitchBetweenTabs() {
        // Click Past tab
        recurringMeetingsPage.clickPastTab();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Past Tab Selected");
        
        // Assert URL contains direction=past
        String pastUrl = driver.getCurrentUrl();
        assertTrue(pastUrl.contains("direction=past"),
                   "URL should contain direction=past. Current URL: " + pastUrl);
        
        // Click Upcoming tab
        recurringMeetingsPage.clickUpcomingTab();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Upcoming Tab Selected");
        
        // Assert URL is base recurring meetings URL (no direction param or does not contain direction=past)
        String upcomingUrl = driver.getCurrentUrl();
        assertFalse(upcomingUrl.contains("direction=past"),
                    "URL should not contain direction=past. Current URL: " + upcomingUrl);
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   AGENDA BOX TESTS                             ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(4)
    @DisplayName("Test 4: Click Date/Time Link in Agenda Box")
    @Description("Verify clicking date/time link navigates to meeting detail page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Agenda Box Actions")
    public void test4_ClickDateTimeLink() {
        recurringMeetingsPage.clickColumnDateAndTimeLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meeting Detail Page");
        
        // Assert URL is meeting detail page
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/projects/art-0-test-release-train/meetings/25"),
                   "URL should be meeting detail page. Current URL: " + currentUrl);
        
        // Navigate back
        driver.navigate().back();
        Delay.waitFor(500);
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Toggle Row More Menu Button")
    @Description("Verify opening and closing row-level more menu button")
    @Severity(SeverityLevel.NORMAL)
    @Story("Agenda Box Actions")
    public void test5_ToggleRowMoreMenuButton() {
        // First click - open menu
        recurringMeetingsPage.clickRowMoreMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Row More Menu Opened");
        
        // Assert Download iCalendar is visible when menu is open
        assertTrue(recurringMeetingsPage.isRowDownloadICalendarLinkVisible(),
                   "Row download iCalendar link should be visible when menu is open");
        
        // Second click - close menu
        recurringMeetingsPage.clickRowMoreMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Row More Menu Closed");
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Click Row Download iCalendar")
    @Description("Verify clicking download iCalendar from row-level more menu")
    @Severity(SeverityLevel.NORMAL)
    @Story("Agenda Box Actions")
    public void test6_ClickRowDownloadICalendar() {
        // Open row menu
        recurringMeetingsPage.clickRowMoreMenuButton();
        Delay.waitFor(500);
        
        // Click download iCalendar
        recurringMeetingsPage.clickRowDownloadICalendarLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Row Download iCalendar Clicked");
        
        // Assert URL unchanged (still on same page)
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/recurring_meetings/"),
                   "URL should still contain /recurring_meetings/. Current URL: " + currentUrl);
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Click Edit Template Button")
    @Description("Verify clicking Edit Template navigates to template edit page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Template Actions")
    public void test7_ClickEditTemplateButton() {
        recurringMeetingsPage.clickEditTemplateButton();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Edit Template Page");
        
        // Assert URL is template edit page
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/projects/art-0-test-release-train/meetings/3"),
                   "URL should be template edit page. Current URL: " + currentUrl);
        
        // Navigate back
        driver.navigate().back();
        Delay.waitFor(500);
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Click Show More Link")
    @Description("Verify clicking Show More link loads more meetings")
    @Severity(SeverityLevel.NORMAL)
    @Story("Pagination Actions")
    public void test8_ClickShowMoreLink() {
        recurringMeetingsPage.clickShowMoreLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Show More Loaded");
        
        // Assert URL contains limit parameter
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("limit="),
                   "URL should contain limit= parameter. Current URL: " + currentUrl);
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   PAST TAB TESTS                               ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(9)
    @DisplayName("Test 9: Past Tab - Click First Item in Table")
    @Description("Verify clicking first item in past table navigates to meeting detail")
    @Severity(SeverityLevel.NORMAL)
    @Story("Past Tab Actions")
    public void test9_PastTabClickFirstItem() {
        // Click past tab first
        recurringMeetingsPage.clickPastTab();
        Delay.waitFor(1000);
        
        // Click first item in table
        recurringMeetingsPage.clickFirstItemInPastTable();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Past Meeting Detail Page");
        
        // Assert URL contains meetings
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings/"),
                   "URL should contain /meetings/. Current URL: " + currentUrl);
        
        // Navigate back
        driver.navigate().back();
        Delay.waitFor(500);
    }

    @Test
    @Order(10)
    @DisplayName("Test 10: Past Tab - Toggle Row Action Button")
    @Description("Verify opening and closing row action button in past tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Past Tab Actions")
    public void test10_PastTabToggleRowActionButton() {
        // Click past tab first
        recurringMeetingsPage.clickPastTab();
        Delay.waitFor(1000);
        
        // Open row action menu
        recurringMeetingsPage.clickRowMoreMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Past Row More Menu Opened");
        
        // Assert Download iCalendar is visible
        assertTrue(recurringMeetingsPage.isRowDownloadICalendarLinkVisible(),
                   "Row download iCalendar link should be visible when menu is open");
        
        // Close row action menu
        recurringMeetingsPage.clickRowMoreMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Past Row More Menu Closed");
    }

    @Test
    @Order(11)
    @DisplayName("Test 11: Past Tab - Click Row Download iCalendar")
    @Description("Verify clicking download iCalendar from row action in past tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Past Tab Actions")
    public void test11_PastTabClickRowDownloadICalendar() {
        // Click past tab first
        recurringMeetingsPage.clickPastTab();
        Delay.waitFor(1000);
        
        // Open row action menu
        recurringMeetingsPage.clickRowMoreMenuButton();
        Delay.waitFor(500);
        
        // Click download iCalendar
        recurringMeetingsPage.clickRowDownloadICalendarLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Past Download iCalendar Clicked");
        
        // Assert URL unchanged
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/recurring_meetings/"),
                   "URL should still contain /recurring_meetings/. Current URL: " + currentUrl);
    }

    @Test
    @Order(12)
    @DisplayName("Test 12: Past Tab - Click Show More Link")
    @Description("Verify clicking Show More link in past tab")
    @Severity(SeverityLevel.NORMAL)
    @Story("Past Tab Actions")
    public void test12_PastTabClickShowMoreLink() {
        // Click past tab first
        recurringMeetingsPage.clickPastTab();
        Delay.waitFor(1000);
        
        // Click show more link
        recurringMeetingsPage.clickShowMoreLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Past Show More Loaded");
        
        // Assert URL contains limit parameter
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("limit="),
                   "URL should contain limit= parameter. Current URL: " + currentUrl);
    }
}
