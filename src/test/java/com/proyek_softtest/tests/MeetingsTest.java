package com.proyek_softtest.tests;

import com.proyek_softtest.base.BaseTest;
import com.proyek_softtest.pages.BasicSidebarPage;
import com.proyek_softtest.pages.MeetingsPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Meetings Page")
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
    @DisplayName("Test: Full Workflow (Filter -> Past -> Recurring -> Disable Toggle)")
    public void testFullMeetingWorkflow() {
        // 1. All Meetings: Set Filter Invited User & Apply
        meetingsPage.selectFilterIfNotFound("Invited user", "invited_user_id");
        meetingsPage.setInvitedUserToIsNotEmpty();
        meetingsPage.clickApply();

        // 2. All Meetings: Klik Tab Past
        meetingsPage.clickPast();

        // 3. Navigasi ke Recurring Meetings
        meetingsPage.goToRecurringMeetings();
        assertTrue(driver.getCurrentUrl().contains("recurring"), "Gagal navigasi ke halaman Recurring.");

        // 4. Halaman Recurring: Klik Tab Past
        meetingsPage.clickPast();

        // 5. Halaman Recurring: Matikan toggle 'Part of a meeting series'
        meetingsPage.turnOffMeetingSeriesToggle();

        // 6. Terakhir: Klik tombol Apply
        meetingsPage.clickApply();
        
        System.out.println("SUCCESS: Skenario Filter All -> Past -> Recurring -> Toggle OFF -> Apply Selesai.");
    }
}