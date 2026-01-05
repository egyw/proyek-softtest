package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.GanttChartPage;
import com.proyek_softtest.pages.WorkPackagesPage;
import com.proyek_softtest.utils.Delay;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
    public void verifyAllProjectsTabOpen() {
        workPackagesPage.clickWorkPackagesSideBar();
        workPackagesPage.clickAllProjectsTab();

        Delay.waitFor(500);

        Assertions.assertTrue(workPackagesPage.isAllProjectsTabOpen(),
                "Gagal: Tab All Projects seharusnya terbuka (aria-expanded='true'), tapi ternyata tertutup.");

        workPackagesPage.clickAllProjectsTab();
    }

    @Test
    @Order(3)
    @DisplayName("WP_T_003: Searching all projects in All Projects Search Bar")
    public void testSearchProject() {
        String[] projects = {
                "Other projects",
                "Demo project",
                "Scrum project",
                "SAFe - Solution Train 1",
                "ART-1 Engineering",
                "Blue team",
                "Red team",
                "ART-2 Design"
        };

        workPackagesPage.clickWorkPackagesSideBar();
        workPackagesPage.clickAllProjectsTab();

        Assertions.assertTrue(workPackagesPage.isAllProjectsTabOpen(), "Gagal membuka tab All Projects");

        for (String project : projects) {
            System.out.println("Sedang mencari project: " + project);

            boolean isFound = workPackagesPage.searchAndVerifyProject(project);

            Assertions.assertTrue(isFound, "Gagal: Project '" + project + "' tidak ditemukan dalam hasil pencarian.");
        }
    }

    @Test
    @Order(4)
    @DisplayName("WP_T_004: Searching non existent project in All Projects Search Bar")
    public void testNonExistentProject() {
        workPackagesPage.clickWorkPackagesSideBar();
        workPackagesPage.clickAllProjectsTab();

        Assertions.assertTrue(workPackagesPage.isAllProjectsTabOpen(), "Gagal membuka tab All Projects");

        String randomProjectName = "abc";
        workPackagesPage.typeSearchProject(randomProjectName);

        boolean isEmpty = workPackagesPage.isProjectListEmpty();

        Assertions.assertTrue(isEmpty,
                "Error: Seharusnya tidak ada project yang muncul untuk keyword '" + randomProjectName
                        + "', tapi ternyata ada hasil.");
    }

    @Test
    @Order(5)
    @DisplayName("WP_T_005: Click projects in All Projects Search Bar")
    public void testClickProject() {
        String[] projects = {
                "Other projects",
                "Demo project",
                "Scrum project",
                "SAFe - Solution Train 1",
                "ART-1 Engineering",
                "Blue team",
                "Red team",
                "ART-2 Design"
        };

    }
}
