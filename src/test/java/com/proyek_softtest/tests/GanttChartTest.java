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
}