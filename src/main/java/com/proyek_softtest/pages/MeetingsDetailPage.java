package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class MeetingsDetailPage extends BasePage {
    // Breadcrumb links
    private By homeBreadCrumbLink = By.linkText("safe.openproject.com");
    private By art1EngineeringBreadCrumbLink = By.xpath("//a[@href='/projects/3' and contains(@class,'Link') and normalize-space()='ART-1 Engineering']");
    private By meetingsBreadCrumbLink = By.linkText("Meetings");
    private By sprintReviewBreadCrumbLink = By.linkText("Sprint Review");

    // More menu (3 dots) - using PageHeader-actions to target the correct one
    private By moreMenuButton = By.cssSelector("div.PageHeader-actions button.Button--iconOnly");
    
    // Menu items inside more menu - using href attributes for more reliable targeting
    private By copyAsOneTimeLink = By.cssSelector("a[href*='/meetings/25/copy']");
    private By downloadICalendarLink = By.cssSelector("a[href*='/download_ics']");
    private By exportPdfLink = By.cssSelector("a[href*='/generate_pdf_dialog']");
    private By historyLink = By.cssSelector("a[href*='/meetings/25/history']");

    // Export PDF dialog elements
    private By closeExportPdfDialog = By.cssSelector("button.Overlay-closeButton[data-close-dialog-id='op-meeting-export-pdf-dialog']");
    private By cancelExportPdfButton = By.xpath("//button[@data-close-dialog-id='op-meeting-export-pdf-dialog' and .//span[text()='Cancel']]");
    private By downloadExportPdfButton = By.xpath("//button[@form='op-meeting-pdf-export-dialog-form' and @type='submit']");
    
    // Checkboxes in export PDF dialog
    private By includeParticipantsCheckbox = By.id("pdf_include_participants");
    private By includeAttachmentsCheckbox = By.id("pdf_include_attachments");
    private By includeBacklogCheckbox = By.id("pdf_include_backlog");
    private By includeOutcomesCheckbox = By.id("pdf_include_outcomes");
    private By footerTextInput = By.id("pdf_footer_text");

    // export complete pop up
    private By exportCompleteHeader = By.xpath("//h2[normalize-space()='Export completed']");
    private By closeExportCompletePopUpByIcon = By.xpath("//button[@data-close-dialog-id='job-status-modal-dialog' and contains(@class,'Overlay-closeButton')]");
    private By closeExportCompletePopUpByButton = By.xpath("//button[@data-close-dialog-id='job-status-modal-dialog']//span[normalize-space()='Close']");
    private By downloadPdfManualLink = By.xpath("//a[normalize-space()='click here' and @data-job-status-polling-target='download']");

    // elements in intro box
    private By demoAdminUserLinkInIntroBox = By.xpath("//li[@id='meeting-agenda-item-34']//a[@class='op-principal--name' and @href='/users/10']");
    private By agendaItemsActionsButton = By.cssSelector("div.op-meeting-agenda-item--actions button.Button--iconOnly");
    private By copyLinkToClipboardItem = By.cssSelector("clipboard-copy.ActionListContent");

    // element di meeting details
    private By sprintReviewLink = By.xpath("//section[.//h4[normalize-space()='Meeting details']]//a[text()='Sprint Review']");

    // element di participants
    private By demoAdminUserInParticipants = By.xpath("//section[.//h4[text()='Participants']]//a[@class='op-principal--name' and text()='Demo admin']");

    // Series backlog collapsible
    private By seriesBacklogCollapsibleTrigger = By.cssSelector("collapsible-header div[data-target='collapsible-header.triggerElement']");
    private By seriesBacklogDescriptionText = By.cssSelector("collapsible-header span[data-targets='collapsible-header.collapsibleElements']");

    public MeetingsDetailPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               BREADCRUMB ACTIONS                       ║
    // ╚════════════════════════════════════════════════════════╝

    public MeetingsDetailPage clickHomeBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(homeBreadCrumbLink)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickArt1EngineeringBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(art1EngineeringBreadCrumbLink)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickMeetingsBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(meetingsBreadCrumbLink)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickSprintReviewBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(sprintReviewBreadCrumbLink)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               MORE MENU ACTIONS                        ║
    // ╚════════════════════════════════════════════════════════╝

    public MeetingsDetailPage clickMoreMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(moreMenuButton)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isCopyAsOneTimeLinkVisible() {
        try {
            return driver.findElements(copyAsOneTimeLink).stream()
                    .anyMatch(el -> el.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    public MeetingsDetailPage clickCopyAsOneTimeLink() {
        // Filter for visible element only
        wait.until(driver -> {
            return driver.findElements(copyAsOneTimeLink).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(copyAsOneTimeLink).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Copy as one-time link not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickDownloadICalendarLink() {
        // Filter for visible element only
        wait.until(driver -> {
            return driver.findElements(downloadICalendarLink).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(downloadICalendarLink).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Download iCalendar link not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickExportPdfLink() {
        // Filter for visible element only
        wait.until(driver -> {
            return driver.findElements(exportPdfLink).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(exportPdfLink).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Export PDF link not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickHistoryLink() {
        // Filter for visible element only
        wait.until(driver -> {
            return driver.findElements(historyLink).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(historyLink).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("History link not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               EXPORT PDF DIALOG ACTIONS                ║
    // ╚════════════════════════════════════════════════════════╝

    public MeetingsDetailPage clickCloseExportPdfDialog() {
        wait.until(ExpectedConditions.elementToBeClickable(closeExportPdfDialog)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickCancelExportPdf() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelExportPdfButton)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickDownloadExportPdf() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadExportPdfButton)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickIncludeParticipantsCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(includeParticipantsCheckbox)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickIncludeAttachmentsCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(includeAttachmentsCheckbox)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickIncludeBacklogCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(includeBacklogCheckbox)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickIncludeOutcomesCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(includeOutcomesCheckbox)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clearAndTypeFooterText(String text) {
        org.openqa.selenium.WebElement input = wait.until(ExpectedConditions.elementToBeClickable(footerTextInput));
        input.clear();
        input.sendKeys(text);
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               EXPORT COMPLETE POP UP ACTIONS           ║
    // ╚════════════════════════════════════════════════════════╝

    public boolean isExportCompleteHeaderVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(exportCompleteHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public MeetingsDetailPage clickCloseExportCompletePopUpByButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeExportCompletePopUpByButton)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickCloseExportCompletePopUpByIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(closeExportCompletePopUpByIcon)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage closeNewTabAndSwitchBack() {
        // Get all window handles
        java.util.Set<String> handles = driver.getWindowHandles();
        String originalWindow = driver.getWindowHandles().iterator().next();
        
        // If more than 1 tab, switch to new tab and close it
        if (handles.size() > 1) {
            for (String handle : handles) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                    Delay.waitFor(1000);
                    driver.close();
                    break;
                }
            }
            // Switch back to original window
            driver.switchTo().window(originalWindow);
        }
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickDownloadPdfManualLink() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadPdfManualLink)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               INTRO BOX ACTIONS                        ║
    // ╚════════════════════════════════════════════════════════╝

    public MeetingsDetailPage clickDemoAdminUserLinkInIntroBox() {
        wait.until(ExpectedConditions.elementToBeClickable(demoAdminUserLinkInIntroBox)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsDetailPage clickAgendaItemsActionsButton() {
        // Filter for visible element only
        wait.until(driver -> {
            return driver.findElements(agendaItemsActionsButton).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(agendaItemsActionsButton).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Agenda items actions button not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    public boolean isCopyLinkToClipboardVisible() {
        try {
            return driver.findElements(copyLinkToClipboardItem).stream()
                    .anyMatch(el -> el.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    public MeetingsDetailPage clickCopyLinkToClipboard() {
        // Filter for visible element only
        wait.until(driver -> {
            return driver.findElements(copyLinkToClipboardItem).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(copyLinkToClipboardItem).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Copy link to clipboard not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               PARTICIPANTS ACTIONS                     ║
    // ╚════════════════════════════════════════════════════════╝

    public MeetingsDetailPage clickDemoAdminUserInParticipants() {
        wait.until(ExpectedConditions.elementToBeClickable(demoAdminUserInParticipants)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               MEETING DETAILS ACTIONS                  ║
    // ╚════════════════════════════════════════════════════════╝

    public MeetingsDetailPage clickSprintReviewLink() {
        wait.until(ExpectedConditions.elementToBeClickable(sprintReviewLink)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               SERIES BACKLOG ACTIONS                   ║
    // ╚════════════════════════════════════════════════════════╝

    public MeetingsDetailPage clickSeriesBacklogCollapsibleTrigger() {
        wait.until(ExpectedConditions.elementToBeClickable(seriesBacklogCollapsibleTrigger)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isSeriesBacklogDescriptionVisible() {
        try {
            return driver.findElements(seriesBacklogDescriptionText).stream()
                    .anyMatch(el -> el.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }
}
