package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.GanttChartPage;
import com.proyek_softtest.pages.WorkPackagesPage;
import com.proyek_softtest.utils.Delay;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Gantt Charts Module")
@Feature("Full Gantt Chart Features")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WorkPackagesTest extends BaseTest {

    private WorkPackagesPage workPackagesPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting Work Packages Tests...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        workPackagesPage = new WorkPackagesPage(driver);
        driver.get("https://safe.openproject.com/");
        Delay.waitFor(2000);
    }

    @Test
    @Order(1)
    @DisplayName("WP_T_001: Click Work packages sidebar and navigate to work packages page")
    @Severity(SeverityLevel.TRIVIAL)
    public void clickWorkPackagesSideBar() {
        workPackagesPage.clickWorkPackagesSideBar();
        Delay.waitFor(1000);

        String currentUrl = driver.getCurrentUrl();

        Assertions.assertTrue(currentUrl.contains("work_packages"),
                "Gagal: URL tidak mengandung 'work_packages'. URL saat ini: " + currentUrl);
    }

    @Test
    @Order(2)
    @DisplayName("WP_T_002: Click All Projects Tab")
    public void clickAllProjectsTab() {
        workPackagesPage.clickAllProjectsTab();
        workPackagesPage.closeAllProjectsTab();
    }
}
