package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.GanttChartsMilestonesPage;
import com.proyek_softtest.utils.Delay;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

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
        driver.get("https://safe.openproject.com/"); 
        Delay.waitFor(2000); 
    }

    @Test
    @Order(1)
    @DisplayName("GCM_T-001: Filter Milestones by 'Blue team'")
    @Severity(SeverityLevel.CRITICAL)
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
    @Severity(SeverityLevel.CRITICAL)
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
    @Severity(SeverityLevel.CRITICAL)
    public void testMilestonesAdvancedFilter() {
        milestonesPage.clickGanttChartsSidebar();
        milestonesPage.clickMilestonesSidebarLink();
        milestonesPage.clickFilterToolbarButton();
        milestonesPage.enterFilterText("description");
        milestonesPage.changeTypeOperator("is not");
        milestonesPage.addTypeFilterValue("Epic");
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("description"));
    }

    @Test
    @Order(4)
    @DisplayName("GCM_T-004: Toolbar Actions: Zoom & Zen Mode")
    @Severity(SeverityLevel.NORMAL)
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
    @Description("Flow: More Actions -> Group by -> Select 'Group by' -> Select 'Author' -> Check 'Display Sums' -> Apply")
    @Severity(SeverityLevel.CRITICAL)
    public void testMilestonesGroupBy() {
        milestonesPage.clickGanttChartsSidebar();
        milestonesPage.clickMilestonesSidebarLink();
        captureScreenshotWithTitle("5_0_Milestones_Opened");

        milestonesPage.clickMoreActionsButton();
        captureScreenshotWithTitle("5_1_MoreActions_Opened");

        milestonesPage.clickGroupByMenuItem();
        captureScreenshotWithTitle("5_2_GroupBy_Modal_Opened");

        milestonesPage.selectGroupByRadioButton();
        captureScreenshotWithTitle("5_3_RadioButton_Selected");

        milestonesPage.selectGroupByCriteria("Author");
        captureScreenshotWithTitle("5_4_Author_Selected");

        milestonesPage.checkDisplaySums();
        captureScreenshotWithTitle("5_5_DisplaySums_Checked");

        milestonesPage.clickModalApply();
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after Group By: " + currentUrl);
        
        assertTrue(currentUrl.contains("author"), 
                   "URL should contain 'author' indicating grouping is active. Actual: " + currentUrl);
        
        captureScreenshotWithTitle("5_6_GroupBy_Applied");
    }
}