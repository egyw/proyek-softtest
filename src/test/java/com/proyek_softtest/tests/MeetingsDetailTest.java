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
        
        driver.get("https://safe.openproject.com/projects/art-0-test-release-train/meetings/25");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   BREADCRUMB TESTS                             ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("MD_T-001: Click Home Breadcrumb Link")
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
        
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(2)
    @DisplayName("MD_T-002: Click ART-1 Engineering Breadcrumb Link")
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
        
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(3)
    @DisplayName("MD_T-003: Click Meetings Breadcrumb Link")
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
        
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(4)
    @DisplayName("MD_T-004: Click Sprint Review Breadcrumb Link")
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
        
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   MORE MENU TESTS                              ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(5)
    @DisplayName("MD_T-005: Toggle More Menu Button")
    @Description("Verify opening and closing more menu button")
    @Severity(SeverityLevel.NORMAL)
    @Story("More Menu Actions")
    public void test5_ToggleMoreMenuButton() {
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("More Menu Opened");
        
        assertTrue(meetingsDetailPage.isCopyAsOneTimeLinkVisible(),
                   "Copy as one-time link should be visible when menu is open");
        
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("More Menu Closed");
    }

    @Test
    @Order(6)
    @DisplayName("MD_T-006: Click Copy As One-Time")
    @Description("Verify clicking Copy as one-time link")
    @Severity(SeverityLevel.NORMAL)
    @Story("More Menu Actions")
    public void test6_ClickCopyAsOneTime() {
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        
        meetingsDetailPage.clickCopyAsOneTimeLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Copy As One-Time Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings/"),
                   "URL should still contain /meetings/. Current URL: " + currentUrl);
    }

    @Test
    @Order(7)
    @DisplayName("MD_T-007: Click Download iCalendar Event")
    @Description("Verify clicking Download iCalendar Event link")
    @Severity(SeverityLevel.NORMAL)
    @Story("More Menu Actions")
    public void test7_ClickDownloadICalendar() {
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        
        meetingsDetailPage.clickDownloadICalendarLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Download iCalendar Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings/"),
                   "URL should still contain /meetings/. Current URL: " + currentUrl);
    }

    @Test
    @Order(8)
    @DisplayName("MD_T-008: Export PDF - Close Dialog with Cancel and X Button")
    @Description("Verify opening Export PDF dialog and closing with Cancel and X button")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test8_ExportPdfCloseDialog() {
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Export PDF Dialog Opened");
        
        meetingsDetailPage.clickCancelExportPdf();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Export PDF Closed with Cancel");
        
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        meetingsDetailPage.clickCloseExportPdfDialog();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Export PDF Closed with X Button");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   EXPORT PDF CHECKBOX TESTS                    ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(9)
    @DisplayName("MD_T-009: Export PDF - Download with Default Checkbox (Participants)")
    @Description("Verify downloading PDF with default checkbox checked (Participants)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test9_ExportPdfDefaultCheckbox() {
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Export PDF Dialog - Default State");
        
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);

        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with Participants");
    }

    @Test
    @Order(10)
    @DisplayName("MD_T-010: Export PDF - Check Attachments and Download")
    @Description("Verify downloading PDF with Participants and Attachments checked")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test10_ExportPdfWithAttachments() {
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        meetingsDetailPage.clickIncludeAttachmentsCheckbox();
        captureScreenshotWithTitle("Attachments Checkbox Checked");
        
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);

        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with Attachments");
    }

    @Test
    @Order(11)
    @DisplayName("MD_T-011: Export PDF - Check Attachments and Backlog")
    @Description("Verify downloading PDF with Participants, Attachments, and Backlog checked")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test11_ExportPdfWithBacklog() {
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        meetingsDetailPage.clickIncludeAttachmentsCheckbox();
        meetingsDetailPage.clickIncludeBacklogCheckbox();
        captureScreenshotWithTitle("Attachments and Backlog Checked");
        
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);

        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with Backlog");
    }

    @Test
    @Order(12)
    @DisplayName("MD_T-012: Export PDF - Check Attachments, Backlog and Outcomes")
    @Description("Verify downloading PDF with Participants, Attachments, Backlog, and Outcomes checked")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test12_ExportPdfWithOutcomes() {
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        meetingsDetailPage.clickIncludeAttachmentsCheckbox();
        meetingsDetailPage.clickIncludeBacklogCheckbox();
        meetingsDetailPage.clickIncludeOutcomesCheckbox();
        captureScreenshotWithTitle("All Checkboxes Checked Except Footer");
        
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);

        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with Outcomes");
    }

    @Test
    @Order(13)
    @DisplayName("MD_T-013: Export PDF - All Checkboxes and Custom Footer Text")
    @Description("Verify downloading PDF with all checkboxes and custom footer text from properties")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Export PDF Actions")
    public void test13_ExportPdfAllCheckboxesAndFooter() {
        String footerText = testData.getProperty("meetings.detail.export.footer.text", "TestFooterText");
        
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        meetingsDetailPage.clickIncludeAttachmentsCheckbox();
        meetingsDetailPage.clickIncludeBacklogCheckbox();
        meetingsDetailPage.clickIncludeOutcomesCheckbox();
        
        meetingsDetailPage.clearAndTypeFooterText(footerText);
        captureScreenshotWithTitle("All Checkboxes and Footer Text Set");
        
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);

        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with All Options and Footer");
    }

    @Test
    @Order(14)
    @DisplayName("MD_T-014: Export PDF - Download with Manual Link")
    @Description("Verify downloading PDF using manual 'click here' link after initial download")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test14_ExportPdfManualDownloadLink() {
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        captureScreenshotWithTitle("Export Complete Popup Visible");
        
        Delay.waitFor(2000);
        
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        meetingsDetailPage.clickDownloadPdfManualLink();
        Delay.waitFor(2000);
        captureScreenshotWithTitle("Manual Download Link Clicked");
        
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("PDF Downloaded with Manual Link");
    }

    @Test
    @Order(15)
    @DisplayName("MD_T-015: Export PDF - Test Both Close Popup Methods")
    @Description("Verify closing export popup with both icon and button methods")
    @Severity(SeverityLevel.NORMAL)
    @Story("Export PDF Actions")
    public void test15_ExportPdfBothCloseMethods() {
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        
        Delay.waitFor(2000);
        
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        meetingsDetailPage.clickCloseExportCompletePopUpByIcon();
        captureScreenshotWithTitle("Popup Closed with Icon");
        
        meetingsDetailPage.clickMoreMenuButton();
        Delay.waitFor(500);
        meetingsDetailPage.clickExportPdfLink();
        Delay.waitFor(1000);
        
        meetingsDetailPage.clickDownloadExportPdf();
        Delay.waitFor(2000);
        
        assertTrue(meetingsDetailPage.isExportCompleteHeaderVisible(),
                   "Export Complete header should be visible");
        
        Delay.waitFor(2000);
        
        meetingsDetailPage.closeNewTabAndSwitchBack();
        
        meetingsDetailPage.clickCloseExportCompletePopUpByButton();
        captureScreenshotWithTitle("Popup Closed with Button");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   INTRO BOX TESTS                              ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(16)
    @DisplayName("MD_T-016: Click Demo Admin User Link in Intro Box")
    @Description("Verify clicking Demo Admin link in intro box navigates to user profile")
    @Severity(SeverityLevel.NORMAL)
    @Story("Intro Box Actions")
    public void test16_ClickDemoAdminUserLinkInIntroBox() {
        meetingsDetailPage.clickDemoAdminUserLinkInIntroBox();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Demo Admin Profile Page");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/users/10"),
                   "URL should be https://safe.openproject.com/users/10. Current URL: " + currentUrl);
        
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    @Test
    @Order(17)
    @DisplayName("MD_T-017: Toggle Agenda Items Actions Button")
    @Description("Verify opening and closing agenda items actions dropdown")
    @Severity(SeverityLevel.NORMAL)
    @Story("Intro Box Actions")
    public void test17_ToggleAgendaItemsActionsButton() {
        meetingsDetailPage.clickAgendaItemsActionsButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Agenda Items Actions Opened");
        
        assertTrue(meetingsDetailPage.isCopyLinkToClipboardVisible(),
                   "Copy link to clipboard should be visible when dropdown is open");
        
        meetingsDetailPage.clickAgendaItemsActionsButton();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Agenda Items Actions Closed");
    }

    @Test
    @Order(18)
    @DisplayName("MD_T-018: Click Copy Link to Clipboard")
    @Description("Verify clicking Copy link to clipboard in agenda items dropdown")
    @Severity(SeverityLevel.NORMAL)
    @Story("Intro Box Actions")
    public void test18_ClickCopyLinkToClipboard() {
        meetingsDetailPage.clickAgendaItemsActionsButton();
        Delay.waitFor(500);
        
        meetingsDetailPage.clickCopyLinkToClipboard();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Copy Link Clicked");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/meetings/25"),
                   "URL should still be on meeting detail. Current URL: " + currentUrl);
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   SERIES BACKLOG TESTS                         ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(19)
    @DisplayName("MD_T-019: Toggle Series Backlog Collapsible")
    @Description("Verify opening and closing series backlog collapsible section")
    @Severity(SeverityLevel.NORMAL)
    @Story("Series Backlog Actions")
    public void test19_ToggleSeriesBacklogCollapsible() {
        meetingsDetailPage.clickSeriesBacklogCollapsibleTrigger();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Series Backlog Collapsed");
        
        meetingsDetailPage.clickSeriesBacklogCollapsibleTrigger();
        Delay.waitFor(500);
        captureScreenshotWithTitle("Series Backlog Expanded");
        
        assertTrue(meetingsDetailPage.isSeriesBacklogDescriptionVisible(),
                   "Series backlog description should be visible when expanded");
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   MEETING DETAILS TESTS                        ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(20)
    @DisplayName("MD_T-020: Click Sprint Review Link in Meeting Details")
    @Description("Verify clicking Sprint Review link navigates to recurring meetings")
    @Severity(SeverityLevel.NORMAL)
    @Story("Meeting Details Actions")
    public void test20_ClickSprintReviewLink() {
        meetingsDetailPage.clickSprintReviewLink();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Sprint Review Page");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/recurring_meetings/"),
                   "URL should contain /recurring_meetings/. Current URL: " + currentUrl);
        
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }

    // ╔════════════════════════════════════════════════════════════════╗
    // ║                   PARTICIPANTS TESTS                           ║
    // ╚════════════════════════════════════════════════════════════════╝

    @Test
    @Order(21)
    @DisplayName("MD_T-021: Click Demo Admin User in Participants")
    @Description("Verify clicking Demo Admin in participants navigates to user profile")
    @Severity(SeverityLevel.NORMAL)
    @Story("Participants Actions")
    public void test21_ClickDemoAdminUserInParticipants() {
        meetingsDetailPage.clickDemoAdminUserInParticipants();
        Delay.waitFor(1000);
        captureScreenshotWithTitle("Demo Admin Profile from Participants");
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://safe.openproject.com/users/10"),
                   "URL should be https://safe.openproject.com/users/10. Current URL: " + currentUrl);
        
        meetingsDetailPage.navigateBack();
        Delay.waitFor(500);
    }
}
