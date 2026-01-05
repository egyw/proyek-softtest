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
    @Severity(SeverityLevel.TRIVIAL)
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
    @Severity(SeverityLevel.TRIVIAL)
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
    @Severity(SeverityLevel.TRIVIAL)
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
    @Severity(SeverityLevel.TRIVIAL)
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

        for (String project : projects) {
            System.out.println("=== Iterasi Project: " + project + " ===");

            workPackagesPage.navigateBackToWorkPackages();

            workPackagesPage.clickAllProjectsTab();
            Assertions.assertTrue(workPackagesPage.isAllProjectsTabOpen(), "Menu project gagal terbuka");

            workPackagesPage.typeSearchProject(project);

            workPackagesPage.clickProjectResult(project);

            String currentTitle = workPackagesPage.getPageTitle();
            boolean isTitleCorrect = currentTitle.toLowerCase().contains(project.toLowerCase());

            Assertions.assertTrue(isTitleCorrect,
                    "Gagal Navigasi: Title '" + currentTitle + "' tidak sesuai dengan project '" + project + "'");

            System.out.println("Berhasil masuk ke halaman: " + currentTitle);
        }

    }

    @Test
    @Order(6)
    @DisplayName("WP_T_006: Click project lists button in All Projects")
    @Severity(SeverityLevel.TRIVIAL)
    public void clickProjectListsButton() {
        workPackagesPage.clickWorkPackagesSideBar();
        workPackagesPage.clickAllProjectsTab();

        Assertions.assertTrue(workPackagesPage.isAllProjectsTabOpen(), "Menu gagal terbuka");

        workPackagesPage.clickProjectLists();
        Delay.waitFor(1000);
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.endsWith("/projects"),
                "Gagal: Seharusnya pindah ke halaman /projects, tapi sekarang di: " + currentUrl);
        System.out.println("Berhasil pindah ke halaman Project Lists.");
        workPackagesPage.navigateBackToWorkPackages();
    }

    @Test
    @Order(7)
    @DisplayName("WP_T_007: Test Menu Navigation (Toggle and Back)")
    @Severity(SeverityLevel.TRIVIAL)
    public void testMenuNavigation() {
        workPackagesPage.clickWorkPackagesSideBar();
        workPackagesPage.clickMainMenuBackButton();
        Delay.waitFor(500);

        try {
            workPackagesPage.clickWorkPackagesToggler();
            System.out.println("Berhasil klik tombol Back.");
        } catch (Exception e) {
            System.out.println("Tombol back tidak muncul (mungkin sudah di root menu).");
        }
    }

    @Test
    @Order(8)
    @DisplayName("WP_T_008: Test Click Collapse Button")
    @Severity(SeverityLevel.TRIVIAL)
    public void testSidebarToggle() {
        workPackagesPage.clickWorkPackagesSideBar();

        workPackagesPage.collapseSidebar();
        Delay.waitFor(1000);

        workPackagesPage.expandSidebar();
        Delay.waitFor(1000);

        System.out.println("Berhasil melakukan toggle sidebar.");
    }

    @Test
    @Order(9)
    @DisplayName("WP_T_009: Test Click Work packages button")
    @Severity(SeverityLevel.MINOR)
    public void testWorkPackagesButton() {
        workPackagesPage.clickWorkPackagesSideBar();

        workPackagesPage.clickWorkPackagesButton();

        Delay.waitFor(1000);

        String expectedUrl = "https://safe.openproject.com/work_packages";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.endsWith("/")) {
            actualUrl = actualUrl.substring(0, actualUrl.length() - 1);
        }

        Assertions.assertEquals(expectedUrl, actualUrl,
                "Gagal: URL setelah klik tidak sesuai. \nExpected: " + expectedUrl + "\nActual: " + actualUrl);

        System.out.println("Sukses: URL valid -> " + actualUrl);
    }

    @Test
    @Order(10)
    @DisplayName("WP_T_010: Test Click Work packages button")
    @Severity(SeverityLevel.TRIVIAL)
    public void testSearchBarMain() {
        String[] searchItems = {
                "High-level project view",
                "Solution Train",
                "All open",
                "Latest activity",
                "Recently created",
                "Overdue",
        };

        workPackagesPage.clickWorkPackagesSideBar();

        for (String item : searchItems) {
            System.out.println("Mencari item sub-menu: " + item);

            boolean isFound = workPackagesPage.searchInSubMenu(item);

            Assertions.assertTrue(isFound,
                    "Gagal: Item '" + item + "' tidak ditemukan di pencarian sub-menu.");
        }
    }
}
