package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class GanttChartPage extends BasePage {

    // ╔════════════════════════════════════════════════════════╗
    // ║               LOCATORS                                 ║
    // ╚════════════════════════════════════════════════════════╝

    // --- Sidebar & Navigation ---
    private By ganttChartsSidebarLink = By.cssSelector("a[title='Gantt charts']");

    // --- Filter: Include Projects ---
    private By includeProjectsButton = By.cssSelector("button[title='Include projects']");
    private By projectListContainer = By.cssSelector("ul.op-project-include-list");
    private By applyFilterButton = By.cssSelector("button.spot-action-bar--action.-primary");
    private By clearSelectionButton = By.xpath("//button[contains(text(), 'Clear selection')]");

    // --- Filter: Baseline ---
    private By baselineToolbarButton = By.xpath("//button[@title='Baseline']");
    private By baselineSelectElement = By.cssSelector("select.op-baseline--filter");
    private By baselineClearButton = By.xpath("//op-baseline//button[contains(text(), 'Clear')]");
    private By baselineApplyButton = By.xpath("//op-baseline//button[contains(text(), 'Apply')]");

    // --- Filter: Advanced Filters ---
    private By filterToolbarButton = By.id("work-packages-filter-toggle-button");
    private By filterByTextInput = By.cssSelector("wp-filter-by-text-input input");
    private By statusFilterSelect = By.id("operators-status");
    private By addFilterInput = By.xpath("//div[contains(@class, 'advanced-filters--add-filter')]//ng-select//input");
    private String addFilterOptionXpath = "//div[contains(@class, 'ng-option')]//span[contains(text(), '%s')]";

    // --- Toolbar Buttons ---
    private By zoomInButton = By.cssSelector("button[title='Zoom in']");
    private By zoomOutButton = By.id("work-packages-timeline-zoom-out-button");
    private By zenModeButton = By.id("work-packages-zen-mode-toggle-button");

    // --- Configure View ---
    private By moreActionsButton = By.id("work-packages-settings-button");
    private By configureViewMenuItem = By.xpath("//span[contains(text(), 'Configure view')]");
    private By removeIdColumnButton = By.xpath("//span[contains(@class, 'op-draggable-autocomplete--item-text') and contains(text(), 'ID')]/following-sibling::a");
    private By addColumnInput = By.cssSelector("ng-select.op-draggable-autocomplete--input input");
    private String dropdownOptionXpath = "//div[contains(@class, 'ng-option') and contains(., '%s')]";
    private By modalApplyButton = By.xpath("//div[contains(@class, 'spot-action-bar')]//button[contains(text(), 'Apply')]");

    // --- Info Pane & Details Toolbar ---
    private By infoButton = By.id("work-packages-details-view-button");
    private By detailsPaneContainer = By.cssSelector(".work-packages--details-content");
    private By detailsMoreActionsButton = By.cssSelector("wp-details-toolbar button[title='More']");
    private By copyLinkToClipboardItem = By.xpath("//span[contains(text(), 'Copy link to clipboard')]");
    private By successToast = By.cssSelector(".op-toast--content");

    // --- Work Package Table ---
    private By firstWorkPackageRow = By.cssSelector(".wp-table--row:first-of-type");

    // --- Activity Tab ---
    private By activityTab = By.cssSelector("li[data-qa-tab-id='activity']");
    private By activityTabLink = By.cssSelector("li[data-qa-tab-id='activity'] a");
    private By activityFilterButton = By.cssSelector("[data-test-selector='op-wp-journals-filter-menu']");
    private By showChangesOnlyOption = By.xpath("//span[contains(text(), 'Show changes only')]");
    private By activitySortButton = By.cssSelector("[data-test-selector='op-wp-journals-sorting-menu']");
    private By newestOnTopOption = By.xpath("//span[contains(text(), 'Newest on top')]");

    // --- Files Tab & Subject ---
    private By filesTab = By.cssSelector("li[data-qa-tab-id='files']");
    private By filesTabLink = By.cssSelector("li[data-qa-tab-id='files'] a");
    private By subjectHeaderTitle = By.cssSelector("span.inline-edit--display-field.subject");

    // --- Relations & Child View Locators ---
    private By relationsTab = By.cssSelector("li[data-qa-tab-id='relations']");
    private By relationsTabLink = By.cssSelector("li[data-qa-tab-id='relations'] a");
    private By relationItemLink = By.xpath("//div[contains(@class, 'relation-row--subject')]//a");
    private By childViewZenModeButton = By.id("work-packages-zen-mode-toggle-button");
    private By childViewMoreActionsButton = By.xpath("//div[@id='toolbar']//button[@title='More']");
    private By descriptionFieldReadOnly = By.cssSelector("span.inline-edit--display-field.description.-read-only");
    private By backButton = By.cssSelector("button[data-test-selector='op-back-button']");

    // --- Meetings Tab & Details Actions ---
    private By meetingsTab = By.cssSelector("li[data-qa-tab-id='meetings']");
    private By meetingsTabLink = By.cssSelector("li[data-qa-tab-id='meetings'] a");
    private By meetingsPastTab = By.xpath("//a[contains(@class, 'tabnav-tab') and contains(., 'Past')]");
    private By meetingsUpcomingTab = By.xpath("//a[contains(@class, 'tabnav-tab') and contains(., 'Upcoming')]");
    private By detailsFullScreenButton = By.cssSelector("button.work-packages--details-fullscreen-icon");
    private By detailsCloseButton = By.cssSelector("button.work-packages--details-close-icon");

    // --- [BARU] Pagination & Per Page Locators ---
    
    // 1. Pagination Buttons (Next & Previous)
    // Sesuai screenshot, menggunakan class spesifik untuk next dan prev
    private By paginationNextButton = By.cssSelector("button.op-pagination--item-link_next");
    private By paginationPreviousButton = By.cssSelector("button.op-pagination--item-link_prev");

    // 2. Per Page Buttons (100 & 200)
    // Mencari button yang berisi text '100' atau '200' di dalam navigasi pagination options
    private By perPage100Button = By.xpath("//button[contains(@class, 'op-pagination--item-link') and contains(text(), '100')]");
    private By perPage200Button = By.xpath("//button[contains(@class, 'op-pagination--item-link') and contains(text(), '200')]");


    public GanttChartPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               METHODS                                  ║
    // ╚════════════════════════════════════════════════════════╝
    
    public GanttChartPage clickGanttChartsSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(ganttChartsSidebarLink)).click();
        wait.until(ExpectedConditions.urlContains("gantt"));
        Delay.waitDefault();
        return this;
    }

    public GanttChartPage clickIncludeProjectsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(includeProjectsButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(projectListContainer));
        Delay.waitFor(500); 
        return this;
    }

    public GanttChartPage selectProjectCheckbox(String projectName) {
        String xpathLocator = "//ul[contains(@class, 'op-project-include-list')]//li[contains(., '" + projectName + "')]";
        WebElement projectItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathLocator)));
        projectItem.click();
        Delay.waitFor(500);
        return this;
    }

    public GanttChartPage clickClearSelection() {
        WebElement clearBtn = wait.until(ExpectedConditions.elementToBeClickable(clearSelectionButton));
        clearBtn.click();
        Delay.waitFor(500);
        return this;
    }

    public GanttChartPage clickApplyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(applyFilterButton)).click();
        Delay.waitFor(1500);
        return this;
    }

    public GanttChartPage clickBaselineButton() {
        wait.until(ExpectedConditions.elementToBeClickable(baselineToolbarButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(baselineSelectElement));
        Delay.waitFor(500);
        return this;
    }

    public GanttChartPage selectBaselineOption(String optionText) {
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(baselineSelectElement));
        Select dropdown = new Select(selectElement);
        dropdown.selectByVisibleText(optionText);
        Delay.waitFor(500);
        return this;
    }

    public GanttChartPage clickBaselineClear() {
        wait.until(ExpectedConditions.elementToBeClickable(baselineClearButton)).click();
        Delay.waitFor(500);
        return this;
    }

    public GanttChartPage clickBaselineApply() {
        wait.until(ExpectedConditions.elementToBeClickable(baselineApplyButton)).click();
        Delay.waitFor(1500);
        return this;
    }

    public GanttChartPage clickFilterToolbarButton() {
        wait.until(ExpectedConditions.elementToBeClickable(filterToolbarButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterByTextInput));
        Delay.waitFor(500);
        return this;
    }

    public GanttChartPage enterFilterText(String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(filterByTextInput));
        input.clear();
        input.sendKeys(text);
        input.sendKeys(Keys.ENTER);
        Delay.waitFor(1000); 
        return this;
    }

    public GanttChartPage selectStatusFilter(String statusValue) {
        WebElement selectElem = wait.until(ExpectedConditions.elementToBeClickable(statusFilterSelect));
        Select dropdown = new Select(selectElem);
        dropdown.selectByVisibleText(statusValue);
        Delay.waitFor(1000); 
        return this;
    }

    public GanttChartPage addFilter(String filterName) {
        WebElement addInput = wait.until(ExpectedConditions.elementToBeClickable(addFilterInput));
        addInput.click();
        Delay.waitFor(300);

        String specificOptionXpath = String.format(addFilterOptionXpath, filterName);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(specificOptionXpath)));
        option.click();
        Delay.waitFor(1000); 
        return this;
    }

    public GanttChartPage clickZoomIn() {
        wait.until(ExpectedConditions.elementToBeClickable(zoomInButton)).click();
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickZoomOut() {
        wait.until(ExpectedConditions.elementToBeClickable(zoomOutButton)).click();
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickZenModeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(zenModeButton)).click();
        Delay.waitFor(1000);
        return this;
    }

    // --- Configure View ---
    public GanttChartPage clickMoreActionsButton() {
        Delay.waitFor(1000);
        wait.until(ExpectedConditions.elementToBeClickable(moreActionsButton)).click();
        System.out.println("Clicked More Actions");
        Delay.waitFor(1500);
        return this;
    }

    public GanttChartPage clickConfigureView() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(configureViewMenuItem));
        wait.until(ExpectedConditions.elementToBeClickable(configureViewMenuItem)).click();
        System.out.println("Clicked Configure View");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addColumnInput));
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage removeIdColumn() {
        try {
            WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(removeIdColumnButton));
            removeBtn.click();
            Delay.waitFor(500);
        } catch (Exception e) {
            System.out.println("Warning: ID Column not found or already removed.");
        }
        return this;
    }

    public GanttChartPage addColumn(String columnName) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(addColumnInput));
        input.click();
        Delay.waitFor(300);

        String specificOptionXpath = String.format(dropdownOptionXpath, columnName);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(specificOptionXpath)));
        option.click();
        Delay.waitFor(500);
        return this;
    }

    public GanttChartPage clickModalApply() {
        wait.until(ExpectedConditions.elementToBeClickable(modalApplyButton)).click();
        Delay.waitFor(2000); 
        return this;
    }

    // --- Info Pane Actions ---
    public GanttChartPage selectFirstWorkPackage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstWorkPackageRow));
        driver.findElement(firstWorkPackageRow).click();
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage openInfoPane() {
        wait.until(ExpectedConditions.elementToBeClickable(infoButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(detailsPaneContainer));
        Delay.waitFor(1000); 
        return this;
    }

    public GanttChartPage clickDetailsPaneMoreActions() {
        wait.until(ExpectedConditions.elementToBeClickable(detailsMoreActionsButton)).click();
        Delay.waitFor(500); 
        return this;
    }

    public GanttChartPage clickCopyLinkToClipboard() {
        wait.until(ExpectedConditions.elementToBeClickable(copyLinkToClipboardItem)).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
        } catch (Exception e) {}
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickActivityTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(activityTab));
        wait.until(ExpectedConditions.elementToBeClickable(activityTabLink)).click();
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage filterActivityToShowChangesOnly() {
        WebElement filterBtn = wait.until(ExpectedConditions.elementToBeClickable(activityFilterButton));
        filterBtn.click();
        Delay.waitFor(500);
        wait.until(ExpectedConditions.elementToBeClickable(showChangesOnlyOption)).click();
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage sortActivityNewestOnTop() {
        WebElement sortBtn = wait.until(ExpectedConditions.elementToBeClickable(activitySortButton));
        sortBtn.click();
        Delay.waitFor(500);
        wait.until(ExpectedConditions.elementToBeClickable(newestOnTopOption)).click();
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickFilesTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(filesTab));
        wait.until(ExpectedConditions.elementToBeClickable(filesTabLink)).click();
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickSubjectHeader() {
        WebElement subject = wait.until(ExpectedConditions.elementToBeClickable(subjectHeaderTitle));
        subject.click();
        Delay.waitFor(500);
        return this;
    }

    // --- Relations & Child View Actions ---

    public GanttChartPage clickRelationsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(relationsTab));
        wait.until(ExpectedConditions.elementToBeClickable(relationsTabLink)).click();
        System.out.println("Clicked Relations Tab");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickRelationItem() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(relationItemLink));
        link.click();
        System.out.println("Clicked Relation Item Link");
        wait.until(ExpectedConditions.visibilityOfElementLocated(backButton));
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage toggleZenModeInChildView() {
        WebElement zenBtn = wait.until(ExpectedConditions.elementToBeClickable(childViewZenModeButton));
        zenBtn.click();
        System.out.println("Toggled Zen Mode ON");
        Delay.waitFor(1000);
        zenBtn.click();
        System.out.println("Toggled Zen Mode OFF");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickMoreActionsInChildView() {
        wait.until(ExpectedConditions.elementToBeClickable(childViewMoreActionsButton)).click();
        System.out.println("Clicked More Actions (Child View)");
        Delay.waitFor(500);
        return this;
    }

    public GanttChartPage hoverDescriptionReadOnly() {
        WebElement descField = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionFieldReadOnly));
        Actions action = new Actions(driver);
        action.moveToElement(descField).perform();
        System.out.println("Hovered over Read-Only Description");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickBackButton() {
        wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
        System.out.println("Clicked Back Button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(includeProjectsButton));
        Delay.waitFor(1000);
        return this;
    }

    // --- Meetings Tab & Details Actions ---

    public GanttChartPage clickMeetingsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(meetingsTab));
        wait.until(ExpectedConditions.elementToBeClickable(meetingsTabLink)).click();
        System.out.println("Clicked Meetings Tab");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickMeetingsPastTab() {
        wait.until(ExpectedConditions.elementToBeClickable(meetingsPastTab)).click();
        System.out.println("Clicked Meetings 'Past' Tab");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickMeetingsUpcomingTab() {
        wait.until(ExpectedConditions.elementToBeClickable(meetingsUpcomingTab)).click();
        System.out.println("Clicked Meetings 'Upcoming' Tab");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickDetailsFullScreen() {
        WebElement fsBtn = wait.until(ExpectedConditions.elementToBeClickable(detailsFullScreenButton));
        fsBtn.click();
        System.out.println("Clicked Details Full Screen (Navigated to Single View)");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartPage clickCloseDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(detailsCloseButton)).click();
        System.out.println("Clicked Close Details View (X)");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(detailsPaneContainer));
        } catch (Exception e) {}
        Delay.waitFor(1000);
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║          [BARU] ACTIONS: PAGINATION & PER PAGE         ║
    // ╚════════════════════════════════════════════════════════╝

    /**
     * Klik tombol Next Page (>)
     */
    public GanttChartPage clickNextPage() {
        wait.until(ExpectedConditions.elementToBeClickable(paginationNextButton)).click();
        System.out.println("Clicked Next Page");
        // Tunggu loading (biasanya indikator baris/tabel reload)
        Delay.waitFor(2000); 
        return this;
    }

    /**
     * Klik tombol Previous Page (<)
     */
    public GanttChartPage clickPreviousPage() {
        wait.until(ExpectedConditions.elementToBeClickable(paginationPreviousButton)).click();
        System.out.println("Clicked Previous Page");
        Delay.waitFor(2000);
        return this;
    }

    /**
     * Klik opsi 100 items per page
     */
    public GanttChartPage clickPerPage100() {
        // Scroll ke bawah agar pagination visible jika perlu
        WebElement btn100 = wait.until(ExpectedConditions.elementToBeClickable(perPage100Button));
        Actions actions = new Actions(driver);
        actions.moveToElement(btn100).perform();
        
        btn100.click();
        System.out.println("Clicked '100' Items Per Page");
        Delay.waitFor(2000); // Tunggu reload tabel
        return this;
    }

    /**
     * Klik opsi 200 items per page
     */
    public GanttChartPage clickPerPage200() {
        WebElement btn200 = wait.until(ExpectedConditions.elementToBeClickable(perPage200Button));
        btn200.click();
        System.out.println("Clicked '200' Items Per Page");
        Delay.waitFor(2000); // Tunggu reload tabel
        return this;
    }
}