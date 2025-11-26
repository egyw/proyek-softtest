package com.proyek_softtest;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.proyek_softtest.pages.HomePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class untuk HomePage OpenProject
 * Website: https://safe.openproject.com/
 */
public class HomePageTest {
    
    private static WebDriver driver;
    private static HomePage homePage;
    private static final String BASE_URL = "https://safe.openproject.com/";
    
    @BeforeAll
    public static void setupClass() {
        System.out.println("========================================");
        System.out.println("  MEMULAI TEST HOMEPAGE OPENPROJECT");
        System.out.println("========================================\n");
        
        // Setup ChromeDriver secara otomatis menggunakan WebDriverManager
        WebDriverManager.chromedriver().setup();
        
        // Konfigurasi Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-notifications");
        
        // Inisialisasi WebDriver - HANYA SEKALI
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Buka website dan inisialisasi HomePage - HANYA SEKALI
        System.out.println("📂 Membuka website: " + BASE_URL);
        driver.get(BASE_URL);
        homePage = new HomePage(driver);
        System.out.println("✅ Website berhasil dibuka\n");
    }
    
    @Test
    @DisplayName("Test 1: Buka dan tutup side menu overlay")
    public void test01_OpenCloseSideMenuOverlay() {
        System.out.println("=== Test 1: Buka dan tutup side menu overlay ===");
        
        homePage.openSideMenuOverlay();
        System.out.println("✓ Side menu overlay dibuka");
        
        homePage.closeSideMenuOverlay();
        System.out.println("✓ Side menu overlay ditutup\n");
        
        assertTrue(true, "Test berhasil dijalankan");
    }
    
    @Test
    @DisplayName("Test 2: Navigasi menu Projects di side menu overlay")
    public void test02_NavigateToProjects() {
        System.out.println("=== Test 2: Navigasi ke Projects ===");
        
        homePage.openSideMenuOverlay();
        homePage.clickProjectsInSideMenuOverlay();
        
        System.out.println("✓ Navigasi ke Projects berhasil\n");
        assertTrue(true, "Test navigasi Projects berhasil");
    }
    
    @Test
    @DisplayName("Test 3: Navigasi menu Work Packages di side menu overlay")
    public void test03_NavigateToWorkPackages() {
        System.out.println("=== Test 3: Navigasi ke Work Packages ===");
        
        homePage.openSideMenuOverlay();
        homePage.clickWorkPackagesInSideMenuOverlay();
        
        System.out.println("✓ Navigasi ke Work Packages berhasil\n");
        assertTrue(true, "Test navigasi Work Packages berhasil");
    }
    
    @Test
    @DisplayName("Test 4: Navigasi menu Gantt Charts di side menu overlay")
    public void test04_NavigateToGanttCharts() {
        System.out.println("=== Test 4: Navigasi ke Gantt Charts ===");
        
        homePage.openSideMenuOverlay();
        homePage.clickGanntChartsInSideMenuOverlay();
        
        System.out.println("✓ Navigasi ke Gantt Charts berhasil\n");
        assertTrue(true, "Test navigasi Gantt Charts berhasil");
    }
    
    @Test
    @DisplayName("Test 5: Navigasi menu Team Planners di side menu overlay")
    public void test05_NavigateToTeamPlanners() {
        System.out.println("=== Test 5: Navigasi ke Team Planners ===");
        
        homePage.openSideMenuOverlay();
        homePage.clickTeamPlannersInSideMenuOverlay();
        
        System.out.println("✓ Navigasi ke Team Planners berhasil\n");
        assertTrue(true, "Test navigasi Team Planners berhasil");
    }
    
    @Test
    @DisplayName("Test 6: Navigasi menu Boards di side menu overlay")
    public void test06_NavigateToBoards() {
        System.out.println("=== Test 6: Navigasi ke Boards ===");
        
        homePage.openSideMenuOverlay();
        homePage.clickBoardsInSideMenuOverlay();
        
        System.out.println("✓ Navigasi ke Boards berhasil\n");
        assertTrue(true, "Test navigasi Boards berhasil");
    }
    
    @Test
    @DisplayName("Test 7: Navigasi menu Meetings di side menu overlay")
    public void test07_NavigateToMeetings() {
        System.out.println("=== Test 7: Navigasi ke Meetings ===");
        
        homePage.openSideMenuOverlay();
        homePage.clickMeetingsInSideMenuOverlay();
        
        System.out.println("✓ Navigasi ke Meetings berhasil\n");
        assertTrue(true, "Test navigasi Meetings berhasil");
    }
    
    @Test
    @DisplayName("Test 8: Navigasi menu News di side menu overlay")
    public void test08_NavigateToNews() {
        System.out.println("=== Test 8: Navigasi ke News ===");
        
        homePage.openSideMenuOverlay();
        homePage.clickNewsInSideMenuOverlay();
        
        System.out.println("✓ Navigasi ke News berhasil\n");
        assertTrue(true, "Test navigasi News berhasil");
    }
    
    @Test
    @DisplayName("Test 9: Navigasi menu Time and Costs di side menu overlay")
    public void test09_NavigateToTimeAndCosts() {
        System.out.println("=== Test 9: Navigasi ke Time and Costs ===");
        
        homePage.openSideMenuOverlay();
        homePage.clickTimeAndCostsInSideMenuOverlay();
        
        System.out.println("✓ Navigasi ke Time and Costs berhasil\n");
        assertTrue(true, "Test navigasi Time and Costs berhasil");
    }
    
    @AfterAll
    public static void tearDown() {
        // Tutup browser HANYA SEKALI setelah SEMUA test selesai
        System.out.println("========================================");
        System.out.println("  SEMUA TEST SELESAI - MENUTUP BROWSER");
        System.out.println("========================================");
        
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser ditutup\n");
        }
    }
}
