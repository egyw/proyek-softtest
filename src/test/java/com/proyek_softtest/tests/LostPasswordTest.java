package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.config.TestData;
import com.proyek_softtest.pages.LostPasswordPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Lost Password Page")
@Feature("Lost Password Functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LostPasswordTest extends BaseTest {

    private LostPasswordPage lostPasswordPage;

    @BeforeAll
    public static void setupClass() {
        System.out.println("Starting LostPasswordTest...");
    }

    @BeforeEach
    @Override
    public void setupTestContext() {
        super.setupTestContext();
        lostPasswordPage = new LostPasswordPage(driver);
        
        driver.get("https://safe.openproject.com/account/lost_password");
    }

    // ╔════════════════════════════════════════════════════════════╗
    // ║                      TEST CASES                            ║
    // ╚════════════════════════════════════════════════════════════╝

    @Test
    @Order(1)
    @DisplayName("LP_T-001: Click Home Breadcrumb Link and Navigate Back")
    @Description("Verify clicking home breadcrumb link navigates to home page and can navigate back")
    @Severity(SeverityLevel.NORMAL)
    @Story("Lost Password Navigation")
    public void test1_ClickHomeBreadcrumbLinkAndNavigateBack() {
        lostPasswordPage.clickHomeBreadCrumbLink();
        
        assertEquals("https://safe.openproject.com/", lostPasswordPage.getCurrentUrl(), 
                     "Should navigate to home page");
        
        captureScreenshotWithTitle("Navigate to Home");
        
        lostPasswordPage.navigateBack();
        
        assertTrue(lostPasswordPage.getCurrentUrl().contains("/account/lost_password"), 
                   "Should be back at lost password page");
    }

    @Test
    @Order(2)
    @DisplayName("LP_T-002: Submit Email for Password Reset")
    @Description("Verify entering email and submitting password reset request")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Lost Password Email Submission")
    public void test2_SubmitEmailForPasswordReset() {
        String email = TestData.getLostPasswordEmail();
        lostPasswordPage.enterEmail(email);
        
        lostPasswordPage.clickSubmitButton();
        
        assertTrue(lostPasswordPage.isSuccessBannerDisplayed(), 
                   "Success banner should be displayed after submitting password reset request");

        captureScreenshotWithTitle("Password Reset Submission");
    }
}
