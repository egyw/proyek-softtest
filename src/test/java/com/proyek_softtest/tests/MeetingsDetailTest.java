package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.MeetingsDetailPage;
import com.proyek_softtest.utils.Delay;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Meetings Module")
@Feature("Meetings Detail Page")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeetingsDetailTest extends BaseTest {
    private MeetingsDetailPage meetingsDetailPage;
    private static Properties testData;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting Meetings Detail Test...");
        // Load test data properties
        testData = new Properties();
        try {
            testData.load(new FileInputStream("src/main/resources/test-data.properties"));
        } catch (Exception e) {
            System.out.println("Could not load test-data.properties: " + e.getMessage());
        }
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        meetingsDetailPage = new MeetingsDetailPage(driver);
        
        // Navigate to Meetings Detail page
        driver.get("https://safe.openproject.com/projects/art-0-test-release-train/meetings/25");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   BREADCRUMB TESTS                             ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("Test 1: Click Home Breadcrumb Link")
    @Description("Verify clicking Home breadcrumb navigates to home page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Breadcrumb Navigation")
    public void test1_ClickHomeBreadCrumbLink() {
        meetingsDetailPage.clickHomeBreadCrumbLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Home Breadcrumb Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/"),
                   "URL should be home page. Current URL: " + currentUrl);
        
        // Navigate back
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Click ART-1 Engineering Breadcrumb Link")
    @Description("Verify clicking ART-1 Engineering breadcrumb navigates to project page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Breadcrumb Navigation")
    public void test2_ClickArt1EngineeringBreadCrumbLink() {
        meetingsDetailPage.clickArt1EngineeringBreadCrumbLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("ART-1 Engineering Breadcrumb Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/projects/"),
                   "URL should contain /projects/. Current URL: " + currentUrl);
        
        // Navigate back
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Click Meetings Breadcrumb Link")
    @Description("Verify clicking Meetings breadcrumb navigates to meetings list")
    @Severity(SeverityLevel.NORMAL)
    @Story("Breadcrumb Navigation")
    public void test3_ClickMeetingsBreadCrumbLink() {
        meetingsDetailPage.clickMeetingsBreadCrumbLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Meetings Breadcrumb Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings"),
                   "URL should contain /meetings. Current URL: " + currentUrl);
        
        // Navigate back
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Click Sprint Review Breadcrumb Link")
    @Description("Verify clicking Sprint Review breadcrumb navigates to recurring meetings")
    @Severity(SeverityLevel.NORMAL)
    @Story("Breadcrumb Navigation")
    public void test4_ClickSprintReviewBreadCrumbLink() {
        meetingsDetailPage.clickSprintReviewBreadCrumbLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Sprint Review Breadcrumb Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/recurring_meetings/"),
                   "URL should contain /recurring_meetings/. Current URL: " + currentUrl);
        
        // Navigate back
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   MORE MENU TESTS                              ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(5)
    @DisplayName("Test 5: Toggle More Menu Button")
    @Description("Verify opening and closing more menu button")
    @Severity(SeverityLevel.NORMAL)
    @Story("More Menu Actions")
    public void test5_ToggleMoreMenuButton() {
        // First click - open menu
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("More Menu Opened");
        
        // Assert Copy as one-time link is visible when menu is open
        assertTrue(meetingsDetailPage.isCopyAsOneTimeLinkVisible(),
                   "Copy as one-time link should be visible when menu is open");
        
        // Second click - close menu
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("More Menu Closed");
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Click Copy As One-Time")
    @Description("Verify clicking Copy as one-time link")
    @Severity(SeverityLevel.NORMAL)
    @Story("More Menu Actions")
    public void test6_ClickCopyAsOneTime() {
        // Open menu
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        
        // Click copy as one-time
        meetingsDetailPage.clickCopyAsOneTimeLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Copy As One-Time Clicked");
        
        // Assert URL still on same page
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings/"),
                   "URL should still contain /meetings/. Current URL: " + currentUrl);
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Click Download iCalendar Event")
    @Description("Verify clicking Download iCalendar Event link")
    @Severity(SeverityLevel.NORMAL)
    @Story("More Menu Actions")
    public void test7_ClickDownloadICalendar() {
        // Open menu
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        
        // Click download iCalendar
        meetingsDetailPage.clickDownloadICalendarLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Download iCalendar Clicked");
        
        // Assert no error
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings/"),
                   "URL should still contain /meetings/. Current URL: " + currentUrl);
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Export PDF - Close Dialog with Cancel and X Button")
    @Description("Verify opening Export PDF dialog and closing with Cancel and X button")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test8_ExportPdfCloseDialog() {
        // Open menu and click Export PDF
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Export PDF Dialog Opened");
        
        // Close with Cancel button
        meetingsDetailPage.clickCancelExportPdf();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Export PDF Closed with Cancel");
        
        // Open menu and Export PDF again
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        // Close with X button
        meetingsDetailPage.clickCloseExportPdfDialog();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Export PDF Closed with X Button");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   EXPORT PDF CHECKBOX TESTS                    ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(9)
    @DisplayName("Test 9: Export PDF - Download with Default Checkbox (Participants)")
    @Description("Verify downloading PDF with default checkbox checked (Participants)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test9_ExportPdfDefaultCheckbox() {
        // Open menu and click Export PDF
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Export PDF Dialog - Default State");
        
        // Click Download (first checkbox already checked)
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        // Assert Export Complete header is visible
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);

        // Close new PDF tab and switch back to original
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        // Close the export complete popup
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with Participants");
    }

    @Test
    @Order(10)
    @DisplayName("Test 10: Export PDF - Check Attachments and Download")
    @Description("Verify downloading PDF with Participants and Attachments checked")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test10_ExportPdfWithAttachments() {
        // Open menu and click Export PDF
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        // Check Attachments checkbox
        meetingsDetailPage.clickIncludeAttachmentsCheckbox();
        captureScreenshotWithTitle("Attachments Checkbox Checked");
        
        // Click Download
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        // Assert Export Complete header is visible
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);

        // Close new PDF tab and switch back to original
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        // Close the export complete popup
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with Attachments");
    }

    @Test
    @Order(11)
    @DisplayName("Test 11: Export PDF - Check Attachments and Backlog")
    @Description("Verify downloading PDF with Participants, Attachments, and Backlog checked")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test11_ExportPdfWithBacklog() {
        // Open menu and click Export PDF
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        // Check Attachments and Backlog checkboxes
        meetingsDetailPage.clickIncludeAttachmentsCheckbox();
        meetingsDetailPage.clickIncludeBacklogCheckbox();
        captureScreenshotWithTitle("Attachments and Backlog Checked");
        
        // Click Download
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        // Assert Export Complete header is visible
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);

        // Close new PDF tab and switch back to original
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        // Close the export complete popup
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with Backlog");
    }

    @Test
    @Order(12)
    @DisplayName("Test 12: Export PDF - Check Attachments, Backlog and Outcomes")
    @Description("Verify downloading PDF with Participants, Attachments, Backlog, and Outcomes checked")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test12_ExportPdfWithOutcomes() {
        // Open menu and click Export PDF
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        // Check Attachments, Backlog, and Outcomes checkboxes
        meetingsDetailPage.clickIncludeAttachmentsCheckbox();
        meetingsDetailPage.clickIncludeBacklogCheckbox();
        meetingsDetailPage.clickIncludeOutcomesCheckbox();
        captureScreenshotWithTitle("All Checkboxes Checked Except Footer");
        
        // Click Download
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        // Assert Export Complete header is visible
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);

        // Close new PDF tab and switch back to original
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        // Close the export complete popup
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with Outcomes");
    }

    @Test
    @Order(13)
    @DisplayName("Test 13: Export PDF - All Checkboxes and Custom Footer Text")
    @Description("Verify downloading PDF with all checkboxes and custom footer text from properties")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Export PDF Actions")
    public void test13_ExportPdfAllCheckboxesAndFooter() {
        // Get footer text from properties
        String footerText = testData.getProperty("meetings.detail.export.footer.text", "TestFooterText");
        
        // Open menu and click Export PDF
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        // Check all checkboxes
        meetingsDetailPage.clickIncludeAttachmentsCheckbox();
        meetingsDetailPage.clickIncludeBacklogCheckbox();
        meetingsDetailPage.clickIncludeOutcomesCheckbox();
        
        // Change footer text
        meetingsDetailPage.clearAndTypeFooterText(footerText);
        captureScreenshotWithTitle("All Checkboxes and Footer Text Set");
        
        // Click Download
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        // Assert Export Complete header is visible
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);

        // Close new PDF tab and switch back to original
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        // Close the export complete popup
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with All Options and Footer");
    }

    @Test
    @Order(14)
    @DisplayName("Test 14: Export PDF - Download with Manual Link")
    @Description("Verify downloading PDF using manual 'click here' link after initial download")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test14_ExportPdfManualDownloadLink() {
        // Open menu and click Export PDF
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        // Click Download
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        // Assert Export Complete header is visible
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);
        
        // Close new PDF tab and switch back to original
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        // Click manual download link ("click here")
        meetingsDetailPage.clickDownloadPdfManualLink();
        Delay.waitFor(2000);
        captureScreenshotWithTitle("Manual Download Link Clicked");
        
        // Close new PDF tab opened by manual link and switch back
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        // Close the export complete popup
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with Manual Link");
    }

    @Test
    @Order(15)
    @DisplayName("Test 15: Export PDF - Test Both Close Popup Methods")
    @Description("Verify closing export popup with both icon and button methods")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test15_ExportPdfBothCloseMethods() {
        // ===== First: Close with Icon =====
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        // Click Download
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        // Assert Export Complete header is visible
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        
        Delay.waitFor(2000);
        
        // Close new PDF tab and switch back to original
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        // Close with X icon
        meetingsDetailPage.clickCloseExportCompletePopUpByIcon();
        captureScreenshotWithTitle("Popup Closed with Icon");
        
        // ===== Second: Close with Button =====
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        // Click Download
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        // Assert Export Complete header is visible
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        
        Delay.waitFor(2000);
        
        // Close new PDF tab and switch back to original
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        // Close with Button
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("Popup Closed with Button");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   INTRO BOX TESTS                              ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(16)
    @DisplayName("Test 16: Click Demo Admin User Link in Intro Box")
    @Description("Verify clicking Demo Admin link in intro box navigates to user profile")
    @Severity(SeverityLevel.NORMAL)
    @Story("Intro Box Actions")
    public void test16_ClickDemoAdminUserLinkInIntroBox() {
        meetingsDetailPage.clickDemoAdminUserLinkInIntroBox();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Demo Admin Profile Page");
        
        // Assert URL is user profile
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/users/10"),
                   "URL should be https://safe.openproject.com/users/10. Current URL: " + currentUrl);
        
        // Navigate back
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(17)
    @DisplayName("Test 17: Toggle Agenda Items Actions Button")
    @Description("Verify opening and closing agenda items actions dropdown")
    @Severity(SeverityLevel.NORMAL)
    @Story("Intro Box Actions")
    public void test17_ToggleAgendaItemsActionsButton() {
        // First click - open dropdown
        meetingsDetailPage.clickAgendaItemsActionsButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Agenda Items Actions Opened");
        
        // Assert Copy link to clipboard is visible
        assertTrue(meetingsDetailPage.isCopyLinkToClipboardVisible(),
                   "Copy link to clipboard should be visible when dropdown is open");
        
        // Second click - close dropdown
        meetingsDetailPage.clickAgendaItemsActionsButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Agenda Items Actions Closed");
    }

    @Test
    @Order(18)
    @DisplayName("Test 18: Click Copy Link to Clipboard")
    @Description("Verify clicking Copy link to clipboard in agenda items dropdown")
    @Severity(SeverityLevel.NORMAL)
    @Story("Intro Box Actions")
    public void test18_ClickCopyLinkToClipboard() {
        // Open dropdown
        meetingsDetailPage.clickAgendaItemsActionsButton();
        Delay.waitFor(500);
        
        // Click copy link to clipboard
        meetingsDetailPage.clickCopyLinkToClipboard();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Copy Link Clicked");
        
        // Assert URL unchanged (still on same page)
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings/25"),
                   "URL should still be on meeting detail. Current URL: " + currentUrl);
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   SERIES BACKLOG TESTS                         ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(19)
    @DisplayName("Test 19: Toggle Series Backlog Collapsible")
    @Description("Verify opening and closing series backlog collapsible section")
    @Severity(SeverityLevel.NORMAL)
    @Story("Series Backlog Actions")
    public void test19_ToggleSeriesBacklogCollapsible() {
        // First click - close the collapsible (it starts open)
        meetingsDetailPage.clickSeriesBacklogCollapsibleTrigger();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Series Backlog Collapsed");
        
        // Second click - open the collapsible
        meetingsDetailPage.clickSeriesBacklogCollapsibleTrigger();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Series Backlog Expanded");
        
        // Assert description text is visible when open
        assertTrue(meetingsDetailPage.isSeriesBacklogDescriptionVisible(),
                   "Series backlog description should be visible when expanded");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   MEETING DETAILS TESTS                        ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(20)
    @DisplayName("Test 20: Click Sprint Review Link in Meeting Details")
    @Description("Verify clicking Sprint Review link navigates to recurring meetings")
    @Severity(SeverityLevel.NORMAL)
    @Story("Meeting Details Actions")
    public void test20_ClickSprintReviewLink() {
        meetingsDetailPage.clickSprintReviewLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Sprint Review Page");
        
        // Assert URL contains recurring_meetings
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/recurring_meetings/"),
                   "URL should contain /recurring_meetings/. Current URL: " + currentUrl);
        
        // Navigate back
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   PARTICIPANTS TESTS                           ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(21)
    @DisplayName("Test 21: Click Demo Admin User in Participants")
    @Description("Verify clicking Demo Admin in participants navigates to user profile")
    @Severity(SeverityLevel.NORMAL)
    @Story("Participants Actions")
    public void test21_ClickDemoAdminUserInParticipants() {
        meetingsDetailPage.clickDemoAdminUserInParticipants();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Demo Admin Profile from Participants");
        
        // Assert URL is user profile
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/users/10"),
                   "URL should be https://safe.openproject.com/users/10. Current URL: " + currentUrl);
        
        // Navigate back
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }
}
