package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.GanttChartPage;
import com.proyek_softtest.utils.Delay;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Gantt Charts Module")
@Feature("Full Gantt Chart Features")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GanttChartTest extends BaseTest {
    
    private GanttChartPage ganttChartPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting Gantt Chart Tests...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        ganttChartPage = new GanttChartPage(driver);
        driver.get("https://safe.openproject.com/"); 
        Delay.waitFor(2000); 
    }

    // ... (Test Case 1 s.d 11 sudah ada) ...

    @Test
    @Order(1)
    @DisplayName("GC_T-001: Filter by 'Demo Project'")
    @Severity(SeverityLevel.CRITICAL)
    public void testFilterByDemoProject() {
        ganttChartPage.clickGanttChartsSidebar();
        ganttChartPage.clickIncludeProjectsButton();
        ganttChartPage.selectProjectCheckbox("Demo project");
        ganttChartPage.clickClearSelection();
        ganttChartPage.selectProjectCheckbox("Demo project");
        ganttChartPage.clickApplyButton();
        assertTrue(driver.getCurrentUrl().contains("safe.openproject.com"));
    }

    @Test
    @Order(2)
    @DisplayName("GC_T-002: Filter by Baseline")
    @Severity(SeverityLevel.CRITICAL)
    public void testFilterByBaseline() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);
        ganttChartPage.clickBaselineButton();
        ganttChartPage.selectBaselineOption("yesterday");
        ganttChartPage.clickBaselineClear();
        ganttChartPage.selectBaselineOption("yesterday");
        ganttChartPage.clickBaselineApply();
        assertTrue(driver.getCurrentUrl().contains("oneDayAgo"));
    }

    @Test
    @Order(3)
    @DisplayName("GC_T-003: Advanced Filters")
    @Severity(SeverityLevel.CRITICAL)
    public void testFilterSearchStatusAndAdd() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);
        ganttChartPage.clickFilterToolbarButton();
        ganttChartPage.enterFilterText("subject");
        ganttChartPage.selectStatusFilter("closed");
        ganttChartPage.addFilter("Assignee");
        assertTrue(driver.getCurrentUrl().contains("safe.openproject.com"));
    }

    @Test
    @Order(4)
    @DisplayName("GC_T-004: Toolbar Actions")
    @Severity(SeverityLevel.NORMAL)
    public void testToolbarZoomAndZenMode() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);
        ganttChartPage.clickZoomIn();
        ganttChartPage.clickZoomOut();
        ganttChartPage.clickZenModeButton();
        ganttChartPage.clickZenModeButton();
        assertTrue(driver.getCurrentUrl().contains("gantt"));
    }

    @Test
    @Order(5)
    @DisplayName("GC_T-005: Configure View: Remove ID & Add Author")
    @Severity(SeverityLevel.CRITICAL)
    public void testConfigureView() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);

        ganttChartPage.clickMoreActionsButton();
        ganttChartPage.clickConfigureView();
        ganttChartPage.removeIdColumn();
        ganttChartPage.addColumn("Author");
        ganttChartPage.clickModalApply();
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("safe.openproject.com"));
    }

    @Test
    @Order(6)
    @DisplayName("GC_T-006: Open Info Pane & Copy Link")
    @Severity(SeverityLevel.NORMAL)
    public void testOpenInfoAndCopyLink() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);
        
        ganttChartPage.selectFirstWorkPackage();
        ganttChartPage.openInfoPane();
        ganttChartPage.clickDetailsPaneMoreActions();
        ganttChartPage.clickCopyLinkToClipboard();
        assertTrue(driver.getCurrentUrl().contains("safe.openproject.com"));
    }

    @Test
    @Order(7)
    @DisplayName("GC_T-007: Activity Tab - Filter & Sort")
    @Severity(SeverityLevel.NORMAL)
    public void testActivityTabFeatures() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);

        ganttChartPage.selectFirstWorkPackage();
        ganttChartPage.openInfoPane();
        ganttChartPage.clickActivityTab();
        ganttChartPage.filterActivityToShowChangesOnly();
        ganttChartPage.sortActivityNewestOnTop();

        assertTrue(driver.getCurrentUrl().contains("safe.openproject.com"));
    }

    @Test
    @Order(8)
    @DisplayName("GC_T-008: Files Tab & Subject Click")
    @Severity(SeverityLevel.NORMAL)
    public void testFilesTabAndSubjectClick() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);
        ganttChartPage.selectFirstWorkPackage();
        ganttChartPage.openInfoPane();
        ganttChartPage.clickFilesTab();
        ganttChartPage.clickSubjectHeader();
        assertTrue(driver.getCurrentUrl().contains("safe.openproject.com"));
    }

    @Test
    @Order(9)
    @DisplayName("GC_T-009: Relations Tab, Child View & Zen Mode")
    @Severity(SeverityLevel.CRITICAL)
    public void testRelationsTabAndChildNavigation() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);

        ganttChartPage.selectFirstWorkPackage();
        ganttChartPage.openInfoPane();
        ganttChartPage.clickRelationsTab();
        ganttChartPage.clickRelationItem(); 

        ganttChartPage.toggleZenModeInChildView();
        ganttChartPage.clickMoreActionsInChildView();
        ganttChartPage.clickCopyLinkToClipboard();
        ganttChartPage.hoverDescriptionReadOnly();

        ganttChartPage.clickBackButton();

        assertTrue(driver.getCurrentUrl().contains("gantt"));
    }

    @Test
    @Order(10)
    @DisplayName("GC_T-010: Meetings Tab (Past & Upcoming)")
    @Severity(SeverityLevel.NORMAL)
    public void testMeetingsTab() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);

        ganttChartPage.selectFirstWorkPackage();
        ganttChartPage.openInfoPane();
        ganttChartPage.clickMeetingsTab();
        ganttChartPage.clickMeetingsPastTab();
        ganttChartPage.clickMeetingsUpcomingTab();

        assertTrue(driver.getCurrentUrl().contains("safe.openproject.com"));
    }

    @Test
    @Order(11)
    @DisplayName("GC_T-011: Details View - Zen Mode & Close Button")
    @Severity(SeverityLevel.NORMAL)
    public void testDetailsZenModeAndClose() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);

        ganttChartPage.selectFirstWorkPackage();
        ganttChartPage.openInfoPane();
        ganttChartPage.clickDetailsFullScreen();
        ganttChartPage.clickBackButton();
        ganttChartPage.clickCloseDetails();

        assertTrue(driver.getCurrentUrl().contains("gantt"));
    }

    // --- [BARU] Test Cases Pagination ---

    @Test
    @Order(12)
    @DisplayName("GC_T-012: Pagination - Next & Previous")
    @Severity(SeverityLevel.NORMAL)
    public void testPagination() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);

        // Scroll ke bawah agar pagination terlihat (optional tapi recommended)
        // 1. Klik Next
        ganttChartPage.clickNextPage();

        // 2. Klik Previous
        ganttChartPage.clickPreviousPage();

        assertTrue(driver.getCurrentUrl().contains("gantt"));
    }

    @Test
    @Order(13)
    @DisplayName("GC_T-013: Items Per Page - 100 & 200")
    @Severity(SeverityLevel.NORMAL)
    public void testItemsPerPage() {
        ganttChartPage.clickGanttChartsSidebar();
        Delay.waitFor(1000);

        // 1. Klik '100'
        ganttChartPage.clickPerPage100();

        // 2. Klik '200'
        ganttChartPage.clickPerPage200();

        assertTrue(driver.getCurrentUrl().contains("gantt"));
    }
}