package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class MeetingsPage extends BasePage {
    // sidebar
    private By meetingsSidebarButton = By.cssSelector("a.main-menu--parent-node.ellipsis[href*='meetings']");
    private By recurringMeetingsType = By.cssSelector("a[data-test-selector='op-submenu--item-action'] span.op-submenu--item-title");
    private By allMeetingsType = By.xpath("//span[normalize-space()='All meetings']");
    private By meetingsSeriesDropdown = By.xpath("//button[normalize-space()='Meeting series' or contains(normalize-space(),'Meeting series')]");
    private By sprintReviewButtonInsideDropdown = By.xpath("//a[contains(@class,'op-submenu--item-action')]//span[normalize-space()='Sprint Review']");

    // main content
    private By meetingsBreadCrumbLink = By.linkText("Meetings");
    private By homeBreadCrumbLink = By.linkText("safe.openproject.com");
    private By upComingFilterButton = By.xpath("//a[.//span[@class='Button-label' and normalize-space()='Upcoming']]");
    private By pastFilterButton = By.xpath("//a[.//span[@class='Button-label' and normalize-space()='Past']]");
    private By filtersButton = By.xpath("//button[.//span[normalize-space()='Filters']]");
    
    // element extra untuk filters
    private By applyFiltersButton = By.xpath("//input[@type='submit' and @value='Apply']");
    private By addFilterDropdown = By.id("add_filter_select");
    private By meetingSeriesSwitch = By.cssSelector("opce-spot-switch[data-name='\"v-type\"'] input[type='checkbox']");
    private By projectNgSelect = By.cssSelector("ng-select#project_id_value input[role='combobox']");
    private By projectDropdownOptions = By.cssSelector("ng-dropdown-panel .ng-option");
    private By removeFilterButton = By.xpath("//a[@class='filter_rem' and @data-filter--filters-form-filter-name-param='invited_user_id']");
    private By closeFormButton = By.cssSelector("a.advanced-filters--close[title='Close form']");

    // table items
    private By tableTitleLink = By.cssSelector("a.Link.text-bold[href*='/meetings/']");
    private By tableProjectLink = By.cssSelector("a[href^='/projects/']:not(.Link)");
    private By tableDateTimeLink = By.cssSelector("a.Link[href*='/recurring_meetings/']");
    private By actionsMenuButton = By.cssSelector("button[data-test-selector='more-button']");
    private By viewMeetingSeriesLink = By.xpath("//span[contains(@class,'ActionListItem-label') and contains(text(),'View meeting series')]/ancestor::a");
    private By downloadICalendarLink = By.xpath("//span[contains(@class,'ActionListItem-label') and contains(text(),'Download iCalendar event')]/ancestor::a");

    public MeetingsPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               SIDEBAR ACTIONS                          ║
    // ╚════════════════════════════════════════════════════════╝

    public MeetingsPage clickMeetingsSidebarButton() {
        wait.until(ExpectedConditions.elementToBeClickable(meetingsSidebarButton)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickRecurringMeetingsType() {
        wait.until(ExpectedConditions.elementToBeClickable(recurringMeetingsType)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickAllMeetingsType() {
        wait.until(ExpectedConditions.elementToBeClickable(allMeetingsType)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickMeetingsSeriesDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(meetingsSeriesDropdown)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isSprintReviewButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(sprintReviewButtonInsideDropdown)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               BREADCRUMB ACTIONS                       ║
    // ╚════════════════════════════════════════════════════════╝

    public MeetingsPage clickHomeBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(homeBreadCrumbLink)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickMeetingsBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(meetingsBreadCrumbLink)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               FILTER ACTIONS                           ║
    // ╚════════════════════════════════════════════════════════╝

    public MeetingsPage clickPastFilterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(pastFilterButton)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickUpcomingFilterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(upComingFilterButton)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickFiltersButton() {
        wait.until(ExpectedConditions.elementToBeClickable(filtersButton)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isApplyFiltersButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(applyFiltersButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public MeetingsPage clickApplyFiltersButton() {
        wait.until(ExpectedConditions.elementToBeClickable(applyFiltersButton)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickCloseFormButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeFormButton)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage selectFilterByValue(String value) {
        org.openqa.selenium.WebElement select = wait.until(ExpectedConditions.elementToBeClickable(addFilterDropdown));
        select.click();
        Delay.waitFor(200);
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(select);
        dropdown.selectByValue(value);
        Delay.waitDefault();
        return this;
    }

    public int selectAllAvailableFilters() {
        int selectedCount = 0;
        String[] filterValues = {"attended_user_id", "author_id", "invited_user_id", "type", "project_id"};
        
        for (String value : filterValues) {
            try {
                org.openqa.selenium.WebElement select = wait.until(ExpectedConditions.elementToBeClickable(addFilterDropdown));
                org.openqa.selenium.WebElement option = select.findElement(By.cssSelector("option[value='" + value + "']"));
                String disabledAttr = option.getAttribute("disabled");
                
                if (disabledAttr == null || disabledAttr.isEmpty()) {
                    select.click();
                    Delay.waitFor(200);
                    org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(select);
                    dropdown.selectByValue(value);
                    selectedCount++;
                    System.out.println("Selected filter: " + value);
                    Delay.waitFor(300);
                }
            } catch (Exception e) {
                System.out.println("Could not select filter: " + value + " - " + e.getMessage());
            }
        }
        return selectedCount;
    }


    public MeetingsPage clickMeetingSeriesSwitch() {
        org.openqa.selenium.WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(meetingSeriesSwitch));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Delay.waitFor(200);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickProjectNgSelect() {
        wait.until(ExpectedConditions.elementToBeClickable(projectNgSelect)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage selectProjectFromDropdown(String projectName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(projectDropdownOptions));
        Delay.waitFor(300);
        
        java.util.List<org.openqa.selenium.WebElement> options = driver.findElements(projectDropdownOptions);
        for (org.openqa.selenium.WebElement option : options) {
            if (option.getText().trim().contains(projectName)) {
                option.click();
                Delay.waitDefault();
                return this;
            }
        }
        throw new RuntimeException("Project not found in dropdown: " + projectName);
    }

    public MeetingsPage clickRemoveFirstFilter() {
        org.openqa.selenium.WebElement link = wait.until(ExpectedConditions.elementToBeClickable(removeFilterButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               TABLE ACTIONS                            ║
    // ╚════════════════════════════════════════════════════════╝

    public MeetingsPage clickFirstTableTitleLink() {
        wait.until(ExpectedConditions.elementToBeClickable(tableTitleLink)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickFirstTableProjectLink() {
        wait.until(ExpectedConditions.elementToBeClickable(tableProjectLink)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickFirstTableDateTimeLink() {
        wait.until(ExpectedConditions.elementToBeClickable(tableDateTimeLink)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickFirstActionsMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(actionsMenuButton)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickViewMeetingSeriesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(viewMeetingSeriesLink)).click();
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickDownloadICalendarLink() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadICalendarLink)).click();
        Delay.waitDefault();
        return this;
    }
}
