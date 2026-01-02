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
    
    // Row-level more menu button (different from header more menu)
    private By rowMoreMenuButton = By.cssSelector("button[data-test-selector='more-button']");
    private By rowDownloadICalendarLink = By.cssSelector("a[href*='/download_ics?occurrence_id']");
    
    private By editTemplateButton = By.xpath("//a[.//span[normalize-space()='Edit template']]");
    
    private By showMoreLink = By.xpath("//span[normalize-space()='Show more']");
    

    // UNTUK PAST
    private By firstItemInTable = By.xpath("//a[normalize-space()='12/20/2025 10:00 AM']");
    
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

    // ╔════════════════════════════════════════════════════════╗
    // ║               AGENDA BOX ACTIONS                       ║
    // ╚════════════════════════════════════════════════════════╝

    public RecurringMeetingsPage clickColumnDateAndTimeLink() {
        wait.until(driver -> {
            return driver.findElements(columnDateAndTimeLink).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(columnDateAndTimeLink).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Date/Time link not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    public RecurringMeetingsPage clickRowMoreMenuButton() {
        wait.until(driver -> {
            return driver.findElements(rowMoreMenuButton).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(rowMoreMenuButton).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Row more menu button not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    public boolean isRowDownloadICalendarLinkVisible() {
        try {
            return driver.findElements(rowDownloadICalendarLink).stream()
                    .anyMatch(el -> el.isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    public RecurringMeetingsPage clickRowDownloadICalendarLink() {
        wait.until(driver -> {
            return driver.findElements(rowDownloadICalendarLink).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(rowDownloadICalendarLink).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Row download iCalendar link not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    public RecurringMeetingsPage clickEditTemplateButton() {
        wait.until(driver -> {
            return driver.findElements(editTemplateButton).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(editTemplateButton).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Edit Template button not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    public RecurringMeetingsPage clickShowMoreLink() {
        wait.until(driver -> {
            return driver.findElements(showMoreLink).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(showMoreLink).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Show More link not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               PAST TAB ACTIONS                         ║
    // ╚════════════════════════════════════════════════════════╝

    public RecurringMeetingsPage clickFirstItemInPastTable() {
        wait.until(driver -> {
            return driver.findElements(firstItemInTable).stream()
                    .anyMatch(el -> el.isDisplayed());
        });
        org.openqa.selenium.WebElement visibleElement = driver.findElements(firstItemInTable).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("First item in past table not visible"));
        visibleElement.click();
        Delay.waitDefault();
        return this;
    }
}
