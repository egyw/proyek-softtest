package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.NewsPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("News Page")
@Feature("News Page Functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NewsTest extends BaseTest {

    private NewsPage newsPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting NewsPageTest...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        newsPage = new NewsPage(driver);

        // Navigate to news page before each test
        driver.get("https://safe.openproject.com/news");
    }
    // ╔════════════════════════════════════════════════════════════╗
    // ║                        TEST CASES                          ║
    // ╚════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("Test 1: Click Home Breadcrumb Link and Navigate Back")
    @Description("Verify clicking home breadcrumb link navigates to home page and can navigate back")
    @Severity(SeverityLevel.NORMAL)
    @Story("News Page Navigation")
    public void test1_ClickHomeBreadcrumbLinkAndNavigateBack() {
        // Click home breadcrumb link
        newsPage.clickHomeBreadCrumbLink();
        // Assert navigates to home page
        assertEquals("https://safe.openproject.com/", newsPage.getCurrentUrl(),
                "Should navigate to home page");

        // Manual screenshot after first assertion
        captureScreenshotWithTitle("Navigate to Home");

        // Navigate back
        newsPage.navigateBack();

        // Assert back to news page
        assertTrue(newsPage.getCurrentUrl().contains("/news"),
                "Should be back at news page");

    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Click Link Demo Project and Navigate to Demo Project Page")
    @Description("Verify clicking Link Demo Project and navigates to demo project page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Demo Project Link Navigation")
    public void test2_ClickLinkDemoProject() {
        newsPage.clickDemoProjectLink();
        assertEquals("https://safe.openproject.com/projects/demo-project", newsPage.getCurrentUrl(),
                "Should navigate to demo project page");

        captureScreenshotWithTitle("Navigate to demo project");

        newsPage.navigateBack();
        assertTrue(newsPage.getCurrentUrl().contains("/news"), "Should be back at news page");

    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Click Link Test Syntax Highlighting and Navigate to Test Syntax Highlighting Page")
    @Description("Verify clicking Link Test Syntax Highlighting and navigates to test syntax highlighting page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Test Syntax Highlighting Link Navigation")
    public void test3_ClickLinkTestSyntaxHighlighting() {
        newsPage.clickTestSyntaxHighlightingLink();
        assertEquals("https://safe.openproject.com/news/4-test-syntax-highlighting", newsPage.getCurrentUrl(),
                "Should navigate to test syntax highlighting page");

        captureScreenshotWithTitle("Navigate to test syntax highlighting");

        newsPage.navigateBack();
        assertTrue(newsPage.getCurrentUrl().contains("/news"), "Should be back at news page");
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Click Link Demo Admin and Navigate to Demo Admin Page")
    @Description("Verify clicking Link Demo Admin and navigates to demo admin page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Demo Admin Link Navigation")
    public void test4_ClickLinkDemoAdmin() {
        newsPage.clickDemoAdminLink();
        assertEquals("https://safe.openproject.com/users/10", newsPage.getCurrentUrl(),
                "Should navigate to demo admin page");

        captureScreenshotWithTitle("Navigate to demo admin");

        newsPage.navigateBack();
        assertTrue(newsPage.getCurrentUrl().contains("/news"), "Should be back at news page");
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Click the second Demo project link")
    @Description("Verify clicking the second Demo project link works correctly")
    @Severity(SeverityLevel.MINOR)
    @Story("Demo Project Link Navigation")
    public void test5_ClickDemoProjectLinkSecond() {
        newsPage.clickDemoProjectLinkSecond();
        assertEquals("https://safe.openproject.com/projects/demo-project", newsPage.getCurrentUrl(),
                "Should navigate to demo project page");
        captureScreenshotWithTitle("Second Demo Project Link Clicked");
        newsPage.navigateBack();
        assertTrue(newsPage.getCurrentUrl().contains("/news"), "Should be back at news page");
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Click Link Actualites and Navigate to Actualites Page")
    @Description("Verify clicking Link Actualites and navigates to actualites page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Actualites Link Navigation")
    public void test6_ClickLinkActualites() {
        newsPage.clickActualitesPageLink();
        assertEquals(
                "https://safe.openproject.com/news/3-actualites-acteurspublics-fr-reforme-de-la-haute-fonction-publique-le-cas-de-l-igf-interroge-toujours",
                newsPage.getCurrentUrl(),
                "Should navigate to actualites page");

        captureScreenshotWithTitle("Navigate to actualites page");

        newsPage.navigateBack();
        assertTrue(newsPage.getCurrentUrl().contains("/news"), "Should be back at news page");
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Click the second Demo admin link")
    @Description("Verify clicking the second Demo admin link works correctly")
    @Severity(SeverityLevel.MINOR)
    @Story("Demo Admin Link Navigation")
    public void test7_ClickDemoAdminLinkSecond() {
        newsPage.clickDemoAdminLinkSecond();
        assertEquals("https://safe.openproject.com/users/10", newsPage.getCurrentUrl(),
                "Should navigate to demo admin page");
        captureScreenshotWithTitle("Second Demo Admin Link Clicked");
        newsPage.navigateBack();
        assertTrue(newsPage.getCurrentUrl().contains("/news"), "Should be back at news page");
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Click Link Scrum Project and Navigate to Scrum Project Page")
    @Description("Verify clicking Link Scrum Project and navigates to scrum project page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Scrum Project Link Navigation")
    public void test8_ClickLinkScrumProject() {
        newsPage.clickScrumProjectLink();
        assertEquals("https://safe.openproject.com/projects/your-scrum-project", newsPage.getCurrentUrl(),
                "Should navigate to scrum project page");
        captureScreenshotWithTitle("Navigate to scrum project page");
        newsPage.navigateBack();
        assertTrue(newsPage.getCurrentUrl().contains("/news"), "Should be back at news page");
    }

    @Test
    @Order(9)
    @DisplayName("Test 9: Click Link Welcome to your Scrum demo project and Navigate to welcome to your scrum demo project page")
    @Description("Verify clicking Link Welcome to your Scrum demo project and navigates to welcome to your scrum demo project page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Welcome to your Scrum demo project Link Navigation")
    public void test9_ClickLinkWelcomeToYourScrumDemoProject() {
        newsPage.clickWelcomeToYourScrumDemoProjectLink();
        assertEquals("https://safe.openproject.com/news/2-welcome-to-your-scrum-demo-project",
                newsPage.getCurrentUrl(),
                "Should navigate to welcome to your scrum demo project page");
        captureScreenshotWithTitle("Navigate to welcome to your scrum demo project page");
        newsPage.navigateBack();
        assertTrue(newsPage.getCurrentUrl().contains("/news"), "Should be back at news page");
    }

    @Test
    @Order(10)
    @DisplayName("Test 10: Click the third Demo project link")
    @Description("Verify clicking the third Demo project link works correctly")
    @Severity(SeverityLevel.MINOR)
    @Story("Demo Project Link Navigation")
    public void test10_ClickDemoProjectLinkThird() {
        newsPage.clickDemoProjectLinkThird();
        assertEquals("https://safe.openproject.com/projects/demo-project", newsPage.getCurrentUrl(),
                "Should navigate to demo project page");
        captureScreenshotWithTitle("Third Demo Project Link Clicked");
        newsPage.navigateBack();
        assertTrue(newsPage.getCurrentUrl().contains("/news"), "Should be back at news page");
    }

    @Test
    @Order(11)
    @DisplayName("Test 11: Click link welcome to your demo project and navigate to welcome to your demo project page")
    @Description("Verify clicking link welcome to your demo project and navigates to welcome to your demo project page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Welcome to your demo project Link Navigation")
    public void test11_ClickLinkWelcomeToYourDemoProject() {
        newsPage.clickWelcomeToYourDemoProjectLink();
        assertEquals("https://safe.openproject.com/news/1-welcome-to-your-demo-project",
                newsPage.getCurrentUrl(),
                "Should navigate to welcome to your demo project page");
        captureScreenshotWithTitle("Navigate to welcome to your demo project page");
        newsPage.navigateBack();
        assertTrue(newsPage.getCurrentUrl().contains("/news"), "Should be back at news page");
    }

    @Test
    @Order(12)
    @DisplayName("Test 12: Click pagination 100")
    @Description("Verify clicking pagination 100 works correctly")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Pagination Functionality")
    public void test12_ClickPagination100() {
        newsPage.clickPagination100();
        assertEquals("https://safe.openproject.com/news?page=1&per_page=100", newsPage.getCurrentUrl(),
                "Should navigate to page with 100 items per page");
        captureScreenshotWithTitle("Pagination 100 Clicked");
    }

    @Test
    @Order(13)
    @DisplayName("Test 13: Click pagination 200")
    @Description("Verify clicking pagination 200 works correctly")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Pagination Functionality")
    public void test13_ClickPagination200() {
        newsPage.clickPagination200();
        assertEquals("https://safe.openproject.com/news?page=1&per_page=200", newsPage.getCurrentUrl(),
                "Should navigate to page with 200 items per page");
        captureScreenshotWithTitle("Pagination 200 Clicked");
    }

    @Test
    @Order(14)
    @DisplayName("Test 14: Click pagination 50")
    @Description("Verify clicking pagination 50 works correctly")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Pagination Functionality")
    public void test14_ClickPagination50() {
        // newsPage.clickPagination100();
        newsPage.clickPagination50();
        assertEquals("https://safe.openproject.com/news?page=1&per_page=50", newsPage.getCurrentUrl(),
                "Should navigate to page with 50 items per page");
        captureScreenshotWithTitle("Pagination 50 Clicked");
    }
}
