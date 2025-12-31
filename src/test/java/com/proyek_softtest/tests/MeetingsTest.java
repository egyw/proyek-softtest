package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.BasicSidebarPage;
import com.proyek_softtest.pages.MeetingsPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Meetings Page")
@Feature("Filter Test")
public class MeetingsTest extends BaseTest {

    private BasicSidebarPage sidebarPage;
    private MeetingsPage meetingsPage;

    @BeforeEach
    public void setup() {
        sidebarPage = new BasicSidebarPage(driver);
        meetingsPage = new MeetingsPage(driver);
        driver.get("https://safe.openproject.com/");
        sidebarPage.clickMeetingsInSidebar();
    }

    @Test
    @DisplayName("Test: Manual Flow - Select 'is empty' & Add Project Filter")
    public void testFillFilterAndAddProject() {
        // Step 1: Ubah operator menjadi 'is empty' dengan klik dropdown operator yang benar
        meetingsPage.changeInvitedUserOperatorToIsEmpty();
        
        // Step 2: Tambah filter Project
        meetingsPage.addProjectFilter();
        
        // Step 3: Apply
        meetingsPage.clickApply();
        
        // Validasi
        String currentUrl = driver.getCurrentUrl();
        System.out.println("DEBUG: URL Final -> " + currentUrl);
        assertTrue(currentUrl.contains("filters") || currentUrl.contains("operators"), 
                   "URL tidak berubah, filter gagal.");
    }
}