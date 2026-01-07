package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.GanttChartsMilestonesPage;
import com.proyek_softtest.utils.Delay;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Gantt Charts Module")
@Feature("Milestones Filtering")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GanttChartsMilestonesTest extends BaseTest {
    
    private GanttChartsMilestonesPage milestonesPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting Gantt Charts Milestones Test...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        milestonesPage = new GanttChartsMilestonesPage(driver);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        try {
            driver.get("https://safe.openproject.com/");
        } catch (org.openqa.selenium.TimeoutException e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.stop();");
        }
        Delay.waitFor(3000); 
    }

    // ... (Test Case 1 s.d 8 sama seperti sebelumnya) ...

    @Test
    @Order(1)
    @DisplayName("GCM_T-001: Filter Milestones by 'Blue team'")
    public void testMilestonesFilterBlueTeam() {
        milestonesPage.clickGanttChartsSidebar();
        milestonesPage.clickMilestonesSidebarLink();
        milestonesPage.clickIncludeProjectsButton();
        milestonesPage.selectProjectCheckbox("Blue team");
        milestonesPage.clickClearSelection();
        milestonesPage.selectProjectCheckbox("Blue team");
        milestonesPage.clickApplyButton();
        assertTrue(driver.getCurrentUrl().contains("safe.openproject.com"));
    }

    @Test
    @Order(2)
    @DisplayName("GCM_T-002: Filter Milestones by Baseline")
    public void testMilestonesBaselineFilter() {
        milestonesPage.clickGanttChartsSidebar();
        milestonesPage.clickMilestonesSidebarLink();
        milestonesPage.clickBaselineButton();
        milestonesPage.selectBaselineOption("last week");
        milestonesPage.clickBaselineClear();
        milestonesPage.selectBaselineOption("last working day");
        milestonesPage.clickBaselineApply();
        assertTrue(driver.getCurrentUrl().contains("lastWorkingDay"));
    }

    @Test
    @Order(3)
    @DisplayName("GCM_T-003: Advanced Filter: Text, Type Operator, Add Value")
    public void testMilestonesAdvancedFilter() {
        milestonesPage.clickGanttChartsSidebar();
        milestonesPage.clickMilestonesSidebarLink();
        milestonesPage.clickFilterToolbarButton();
        milestonesPage.enterFilterText("description");
        milestonesPage.changeTypeOperator("is not");
        milestonesPage.addTypeFilterValue("Epic");
        assertTrue(driver.getCurrentUrl().contains("description"));
    }

    @Test
    @Order(4)
    @DisplayName("GCM_T-004: Toolbar Actions: Zoom & Zen Mode")
    public void testMilestonesToolbarActions() {
        milestonesPage.clickGanttChartsSidebar();
        milestonesPage.clickMilestonesSidebarLink();
        milestonesPage.clickZoomIn();
        milestonesPage.clickZoomOut();
        milestonesPage.clickZenModeButton();
        milestonesPage.clickZenModeButton();
        assertTrue(driver.getCurrentUrl().contains("safe.openproject.com"));
    }

    @Test
    @Order(5)
    @DisplayName("GCM_T-005: Configure View: Group By Author & Sums")
    public void testMilestonesGroupBy() {
        milestonesPage.clickGanttChartsSidebar();
        milestonesPage.clickMilestonesSidebarLink();
        milestonesPage.clickMoreActionsButton();
        milestonesPage.clickGroupByMenuItem();
        milestonesPage.selectGroupByRadioButton();
        milestonesPage.selectGroupByCriteria("Author");
        milestonesPage.checkDisplaySums();
        milestonesPage.clickModalApply();
        assertTrue(driver.getCurrentUrl().contains("author"));
    }

    @Test
    @Order(6)
    @DisplayName("GCM_T-006: Open Info Pane & Copy Link")
    public void testOpenInfoAndCopyLink() {
        milestonesPage.clickGanttChartsSidebar();
        milestonesPage.clickMilestonesSidebarLink();
        Delay.waitFor(1000);
        milestonesPage.selectFirstMilestoneItem();
        milestonesPage.clickInfoButton();
        milestonesPage.clickDetailsPaneMoreActions();
        milestonesPage.clickCopyLinkToClipboard();
        assertTrue(driver.getCurrentUrl().contains("safe.openproject.com"));
    }

    @Test
    @Order(7)
    @DisplayName("GCM_T-007: Activity Tab - Filter Comments & Sort Newest Top")
    public void testActivityTabFeatures() {
        milestonesPage.clickGanttChartsSidebar();
        milestonesPage.clickMilestonesSidebarLink();
        Delay.waitFor(1000);
        milestonesPage.selectFirstMilestoneItem();
        milestonesPage.clickInfoButton();
        milestonesPage.clickActivityTab();
        milestonesPage.filterActivityCommentsOnly();
        milestonesPage.sortActivityNewestOnTop();
        assertTrue(driver.getCurrentUrl().contains("safe.openproject.com"));
    }

    @Test
    @Order(8)
    @DisplayName("GCM_T-008: Navigation through Files, Relations, and Meetings Tabs")
    public void testDetailsTabsNavigation() {
        milestonesPage.clickGanttChartsSidebar();
        milestonesPage.clickMilestonesSidebarLink();
        Delay.waitFor(1000);
        milestonesPage.selectFirstMilestoneItem();
        milestonesPage.clickInfoButton();
        milestonesPage.clickFilesTab();
        milestonesPage.clickRelationsTab();
        milestonesPage.clickMeetingsTab();
        milestonesPage.clickMeetingsPastTab();
        milestonesPage.clickMeetingsUpcomingTab();
        assertTrue(driver.getCurrentUrl().contains("safe.openproject.com"));
    }

    // --- [BARU] Test Case 9 ---
    @Test
    @Order(9)
    @DisplayName("GCM_T-009: Full Screen Navigation Loop (Info -> Zen -> Back -> Info -> Close)")
    @Description("Flow: Click 'i' -> Click Fullscreen -> Click Back -> Click 'i' -> Click Close (X)")
    @Severity(SeverityLevel.NORMAL)
    public void testZenModeAndCloseLoop() {
        milestonesPage.clickGanttChartsSidebar();
        milestonesPage.clickMilestonesSidebarLink();
        Delay.waitFor(1000);

        // 1. Pilih Item & Buka Info
        milestonesPage.selectFirstMilestoneItem();
        milestonesPage.clickInfoButton();

        // 2. Klik Full Screen (Navigasi ke halaman Single View)
        milestonesPage.clickDetailsFullScreen();

        // 3. Klik Back (Kembali ke Gantt Chart)
        milestonesPage.clickBackButton();

        // 4. Klik Info lagi (sesuai instruksi: 'tekan i lagi')
        milestonesPage.clickInfoButton();

        // 5. Klik Close (X)
        milestonesPage.clickCloseDetails();

        // Assert
        assertTrue(driver.getCurrentUrl().contains("gantt"));
    }
}