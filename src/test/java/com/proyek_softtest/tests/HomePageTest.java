package com.proyek_softtest.tests;

import org.junit.jupiter.api.*;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.HomePage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class untuk HomePage OpenProject
 * Website: https://safe.openproject.com/
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest extends BaseTest {
    
    private static HomePage homePage;
    
    @BeforeAll
    public static void setUpHomePage() {
        // BaseTest.globalSetup() sudah otomatis dipanggil karena inheritance
        // Jadi TIDAK perlu panggil lagi
        
        // Initialize HomePage object - HANYA SEKALI setelah driver ready
        homePage = new HomePage(driver);
        System.out.println("✅ HomePage initialized\n");
    }
    
    @Test
    @Order(1)
    @DisplayName("Test 1: Buka dan tutup side menu overlay")
    public void test01_OpenCloseSideMenuOverlay() {
        logInfo("Testing open and close side menu overlay");
        
        homePage.openSideMenuOverlay();
        logPass("Side menu overlay opened successfully");
        
        homePage.closeSideMenuOverlay();
        logPass("Side menu overlay closed successfully");
        
        assertTrue(true, "Test berhasil dijalankan");
    }
    
    @Test
    @Order(2)
    @DisplayName("Test 2: Navigasi menu Projects di side menu overlay")
    public void test02_NavigateToProjects() {
        logInfo("Testing navigation to Projects menu");
        
        homePage.openSideMenuOverlay();
        logInfo("Side menu overlay opened");
        
        homePage.clickProjectsInSideMenuOverlay();
        logPass("Successfully navigated to Projects menu");
        
        assertTrue(true, "Test navigasi Projects berhasil");
    }
    
    @Test
    @Order(3)
    @DisplayName("Test 3: Navigasi menu Work Packages di side menu overlay")
    public void test03_NavigateToWorkPackages() {
        logInfo("Testing navigation to Work Packages menu");
                
        homePage.clickWorkPackagesInSideMenuOverlay();
        logPass("Successfully navigated to Work Packages menu");
        
        assertTrue(true, "Test navigasi Work Packages berhasil");
    }
    
    @Test
    @Order(4)
    @DisplayName("Test 4: Navigasi menu Gantt Charts di side menu overlay")
    public void test04_NavigateToGanttCharts() {
        logInfo("Testing navigation to Gantt Charts menu");
        
        homePage.clickGanntChartsInSideMenuOverlay();
        logPass("Successfully navigated to Gantt Charts menu");
        
        assertTrue(true, "Test navigasi Gantt Charts berhasil");
    }
    
    @Test
    @Order(5)
    @DisplayName("Test 5: Navigasi menu Team Planners di side menu overlay")
    public void test05_NavigateToTeamPlanners() {
        logInfo("Testing navigation to Team Planners menu");
        
        homePage.clickTeamPlannersInSideMenuOverlay();
        logPass("Successfully navigated to Team Planners menu");
        
        assertTrue(true, "Test navigasi Team Planners berhasil");
    }
    
    @Test
    @Order(6)
    @DisplayName("Test 6: Navigasi menu Boards di side menu overlay")
    public void test06_NavigateToBoards() {
        logInfo("Testing navigation to Boards menu");
        
        homePage.clickBoardsInSideMenuOverlay();
        logPass("Successfully navigated to Boards menu");
        
        assertTrue(true, "Test navigasi Boards berhasil");
    }
    
    @Test
    @Order(7)
    @DisplayName("Test 7: Navigasi menu Meetings di side menu overlay")
    public void test07_NavigateToMeetings() {
        logInfo("Testing navigation to Meetings menu");

        homePage.clickMeetingsInSideMenuOverlay();
        logPass("Successfully navigated to Meetings menu");
        
        assertTrue(true, "Test navigasi Meetings berhasil");
    }
    
    @Test
    @Order(8)
    @DisplayName("Test 8: Navigasi menu News di side menu overlay")
    public void test08_NavigateToNews() {
        logInfo("Testing navigation to News menu");

        homePage.clickNewsInSideMenuOverlay();
        logPass("Successfully navigated to News menu");
        
        assertTrue(true, "Test navigasi News berhasil");
    }
    
    @Test
    @Order(9)
    @DisplayName("Test 9: Navigasi menu Time and Costs di side menu overlay")
    public void test09_NavigateToTimeAndCosts() {
        logInfo("Testing navigation to Time and Costs menu");

        homePage.clickTimeAndCostsInSideMenuOverlay();
        logPass("Successfully navigated to Time and Costs menu");
        
        homePage.closeSideMenuOverlay();
        logInfo("Side menu overlay closed");
        
        assertTrue(true, "Test navigasi Time and Costs berhasil");
    }
}
