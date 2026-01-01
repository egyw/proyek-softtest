package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class RecurringMeetingsPage extends BasePage {
    // More menu button - using data-test-selector for unique targeting
    private By moreMenuButton = By.cssSelector("button[data-test-selector='recurring-meeting-action-menu']");
    
    // Menu items inside more menu
    private By downloadICalendarLink = By.cssSelector("a[href*='/download_ics']");

    // Segmented control tabs
    private By upcomingTab = By.cssSelector("a[title='Upcoming meetings']");
    private By pastTab = By.cssSelector("a[title='Past meetings']");

    // agenda opened box
    private By columnDateAndTimeLink = By.xpath("//a[normalize-space()='01/03/2026 10:00 AM']");
    
    public RecurringMeetingsPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               MORE MENU ACTIONS                        ║
    // ╚════════════════════════════════════════════════════════╝

    public RecurringMeetingsPage clickMoreMenuButton() {
        // Filter for visible element only
        wait.until(driver -> {
            return driver.findElements(moreMenuButton).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(moreMenuButton).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("More menu button not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    public boolean isDownloadICalendarLinkVisible() {
        try {
            return driver.findElements(downloadICalendarLink).stream()
                    .anyMatch(el -> el.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    public RecurringMeetingsPage clickDownloadICalendarLink() {
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

    // ╔════════════════════════════════════════════════════════╗
    // ║               TAB ACTIONS                              ║
    // ╚════════════════════════════════════════════════════════╝

    public RecurringMeetingsPage clickUpcomingTab() {
        wait.until(driver -> {
            return driver.findElements(upcomingTab).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(upcomingTab).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Upcoming tab not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    public RecurringMeetingsPage clickPastTab() {
        wait.until(driver -> {
            return driver.findElements(pastTab).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(pastTab).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Past tab not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }
}
