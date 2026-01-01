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
}
