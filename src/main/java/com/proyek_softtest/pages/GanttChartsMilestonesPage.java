package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class GanttChartsMilestonesPage extends BasePage {

    // ╔════════════════════════════════════════════════════════╗
    // ║               LOCATORS                                 ║
    // ╚════════════════════════════════════════════════════════╝

    // --- Sidebar Links ---
    private By ganttChartsSidebarLink = By.cssSelector("a[title='Gantt charts']");
    private By milestonesSidebarLink = By.xpath("//a[.//span[contains(text(), 'Milestones')]]");

    // --- Filter: Include Projects ---
    private By includeProjectsButton = By.cssSelector("button[title='Include projects']");
    private By projectListContainer = By.cssSelector("ul.op-project-include-list");
    private By clearSelectionButton = By.xpath("//button[contains(text(), 'Clear selection')]");
    private By applyFilterButton = By.cssSelector("button.spot-action-bar--action.-primary");

    // --- Filter: Baseline ---
    private By baselineToolbarButton = By.xpath("//button[@title='Baseline']");
    private By baselineSelectElement = By.cssSelector("select.op-baseline--filter");
    private By baselineClearButton = By.xpath("//div[contains(@class, 'spot-action-bar')]//button[normalize-space()='Clear']");
    private By baselineApplyButton = By.xpath("//div[contains(@class, 'spot-action-bar')]//button[normalize-space()='Apply']");

    // --- Filter: Advanced Filters ---
    private By filterToolbarButton = By.id("work-packages-filter-toggle-button");
    private By filterByTextInput = By.cssSelector("input#filter-by-text-input");
    private By typeOperatorSelect = By.id("operators-type");
    private By typeValueContainer = By.cssSelector("#div-values-type ng-select");
    private String dropdownOptionXpath = "//div[contains(@class, 'ng-option') and contains(., '%s')]";

    // --- Toolbar Buttons ---
    private By zoomInButton = By.id("work-packages-timeline-zoom-in-button");
    private By zoomOutButton = By.id("work-packages-timeline-zoom-out-button");
    private By zenModeButton = By.id("work-packages-zen-mode-toggle-button");

    // --- Configure View (General) ---
    private By moreActionsButton = By.id("work-packages-settings-button");
    private By configureViewMenuItem = By.xpath("//li[contains(@class, 'menu-item')]//*[contains(text(), 'Configure view')]");
    private By removeIdColumnButton = By.xpath("//span[contains(@class, 'op-draggable-autocomplete--item-text') and contains(text(), 'ID')]/following-sibling::a");
    private By addColumnInput = By.cssSelector("ng-select.op-draggable-autocomplete--input input");
    private By modalApplyButton = By.xpath("//div[contains(@class, 'spot-action-bar')]//button[contains(text(), 'Apply')]");

    // --- Configure View: Group By ---
    private By groupByMenuItem = By.xpath("//li[contains(., 'Group by')]");
    private By groupByRadioLabel = By.xpath("//label[.//input[@value='grouped']]");
    private By groupBySelectElement = By.id("selected_grouping");
    private By displaySumsLabel = By.xpath("//label[contains(., 'Display Sums')]");

    // --- Info Pane & Details Toolbar ---
    private By firstTableItem = By.cssSelector(".wp-table--row:first-of-type");
    private By infoButton = By.id("work-packages-details-view-button");
    private By detailsPaneContainer = By.cssSelector(".work-packages--details-content");
    private By detailsMoreActionsButton = By.cssSelector(".work-packages--details-toolbar-container button[title='More']");
    private By copyLinkToClipboardItem = By.xpath("//span[contains(text(), 'Copy link to clipboard')]");
    private By successToast = By.cssSelector(".op-toast--content");

    // --- Activity Tab ---
    private By activityTab = By.cssSelector("li[data-qa-tab-id='activity']");
    private By activityTabLink = By.cssSelector("li[data-qa-tab-id='activity'] a");
    private By activityFilterDropdown = By.cssSelector("[data-test-selector='op-wp-journals-filter-menu']");
    private By showCommentsOnlyOption = By.xpath("//span[contains(text(), 'Show comments only')]");
    private By activitySortDropdown = By.cssSelector("[data-test-selector='op-wp-journals-sorting-menu']");
    private By newestOnTopOption = By.xpath("//span[contains(text(), 'Newest on top')]");

    // --- Files, Relations, Meetings Tabs ---
    private By filesTab = By.cssSelector("li[data-qa-tab-id='files']");
    private By filesTabLink = By.cssSelector("li[data-qa-tab-id='files'] a");
    private By relationsTab = By.cssSelector("li[data-qa-tab-id='relations']");
    private By relationsTabLink = By.cssSelector("li[data-qa-tab-id='relations'] a");
    private By meetingsTab = By.cssSelector("li[data-qa-tab-id='meetings']");
    private By meetingsTabLink = By.cssSelector("li[data-qa-tab-id='meetings'] a");
    private By meetingsPastTab = By.xpath("//a[contains(@class, 'tabnav-tab') and contains(., 'Past')]");
    private By meetingsUpcomingTab = By.xpath("//a[contains(@class, 'tabnav-tab') and contains(., 'Upcoming')]");

    // --- [UPDATED] Details View Actions (Zen Mode, Back, Close) ---
    
    // Zen Mode / Full Screen di Panel Detail (Sesuai Screenshot 1)
    private By detailsFullScreenButton = By.cssSelector("button.spot-link.work-packages--details-fullscreen-icon");
    
    // Tombol Close (X) di Panel Detail (Sesuai Screenshot 2)
    private By detailsCloseButton = By.cssSelector("button.spot-link.work-packages--details-close-icon");
    
    // Tombol Back di Single View (Sesuai Screenshot 3)
    private By backButton = By.cssSelector("button.op-back-button");


    public GanttChartsMilestonesPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               METHODS                                  ║
    // ╚════════════════════════════════════════════════════════╝
    
    public GanttChartsMilestonesPage clickGanttChartsSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(ganttChartsSidebarLink)).click();
        wait.until(ExpectedConditions.urlContains("gantt"));
        Delay.waitDefault();
        return this;
    }

    public GanttChartsMilestonesPage clickMilestonesSidebarLink() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(milestonesSidebarLink));
        element.click();
        Delay.waitFor(2000); 
        return this;
    }

    // --- Filter Methods ---
    public GanttChartsMilestonesPage clickIncludeProjectsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(includeProjectsButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(projectListContainer));
        Delay.waitFor(500); 
        return this;
    }

    public GanttChartsMilestonesPage selectProjectCheckbox(String projectName) {
        String xpathLocator = "//ul[contains(@class, 'op-project-include-list')]//li[contains(., '" + projectName + "')]";
        WebElement projectItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathLocator)));
        projectItem.click();
        System.out.println("Selected project: " + projectName);
        Delay.waitFor(500);
        return this;
    }

    public GanttChartsMilestonesPage clickClearSelection() {
        WebElement clearBtn = wait.until(ExpectedConditions.elementToBeClickable(clearSelectionButton));
        clearBtn.click();
        System.out.println("Clicked 'Clear selection'");
        Delay.waitFor(500);
        return this;
    }

    public GanttChartsMilestonesPage clickApplyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(applyFilterButton)).click();
        System.out.println("Clicked Apply Button");
        Delay.waitFor(2000); 
        return this;
    }

    public GanttChartsMilestonesPage clickBaselineButton() {
        wait.until(ExpectedConditions.elementToBeClickable(baselineToolbarButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(baselineSelectElement));
        Delay.waitFor(500);
        return this;
    }

    public GanttChartsMilestonesPage selectBaselineOption(String optionText) {
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(baselineSelectElement));
        Select dropdown = new Select(selectElement);
        dropdown.selectByVisibleText(optionText);
        System.out.println("Selected Baseline option: " + optionText);
        Delay.waitFor(1000); 
        return this;
    }

    public GanttChartsMilestonesPage clickBaselineClear() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(baselineClearButton));
        btn.click();
        System.out.println("Clicked Baseline 'Clear'");
        Delay.waitFor(500);
        return this;
    }

    public GanttChartsMilestonesPage clickBaselineApply() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(baselineApplyButton));
        btn.click();
        System.out.println("Clicked Baseline 'Apply'");
        Delay.waitFor(2000); 
        return this;
    }

    // --- Advanced Filter ---
    public GanttChartsMilestonesPage clickFilterToolbarButton() {
        wait.until(ExpectedConditions.elementToBeClickable(filterToolbarButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterByTextInput));
        System.out.println("Clicked Filter Toolbar Button");
        Delay.waitFor(500);
        return this;
    }

    public GanttChartsMilestonesPage enterFilterText(String text) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(filterByTextInput));
        input.clear();
        input.sendKeys(text);
        input.sendKeys(Keys.ENTER); 
        System.out.println("Entered Filter Text: " + text);
        Delay.waitFor(1000); 
        return this;
    }

    public GanttChartsMilestonesPage changeTypeOperator(String operatorText) {
        WebElement selectElem = wait.until(ExpectedConditions.elementToBeClickable(typeOperatorSelect));
        Select dropdown = new Select(selectElem);
        dropdown.selectByVisibleText(operatorText);
        System.out.println("Changed Type Operator to: " + operatorText);
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartsMilestonesPage addTypeFilterValue(String typeName) {
        WebElement container = wait.until(ExpectedConditions.elementToBeClickable(typeValueContainer));
        container.click();
        Delay.waitFor(500);

        String specificOptionXpath = String.format(dropdownOptionXpath, typeName);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(specificOptionXpath)));
        option.click();
        
        System.out.println("Added Type Value: " + typeName);
        Delay.waitFor(1000);
        return this;
    }

    // --- Toolbar ---
    public GanttChartsMilestonesPage clickZoomIn() {
        wait.until(ExpectedConditions.elementToBeClickable(zoomInButton)).click();
        System.out.println("Clicked Zoom In");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartsMilestonesPage clickZoomOut() {
        wait.until(ExpectedConditions.elementToBeClickable(zoomOutButton)).click();
        System.out.println("Clicked Zoom Out");
        Delay.waitFor(1000); 
        return this;
    }

    public GanttChartsMilestonesPage clickZenModeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(zenModeButton)).click();
        System.out.println("Clicked Zen Mode Toggle");
        Delay.waitFor(1000); 
        return this;
    }

    // --- Configure View ---
    public GanttChartsMilestonesPage clickMoreActionsButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(moreActionsButton));
        btn.click();
        System.out.println("Clicked More Actions");
        Delay.waitFor(1000); 
        return this;
    }

    public GanttChartsMilestonesPage clickConfigureView() {
        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(configureViewMenuItem));
        menu.click();
        System.out.println("Clicked Configure View");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addColumnInput));
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartsMilestonesPage removeIdColumn() {
        try {
            WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(removeIdColumnButton));
            removeBtn.click();
            System.out.println("Removed ID Column");
            Delay.waitFor(500);
        } catch (Exception e) {
            System.out.println("Warning: ID Column not found or already removed.");
        }
        return this;
    }

    public GanttChartsMilestonesPage addColumn(String columnName) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(addColumnInput));
        input.click();
        Delay.waitFor(300);

        String specificOptionXpath = String.format(dropdownOptionXpath, columnName);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(specificOptionXpath)));
        option.click();
        System.out.println("Added column: " + columnName);
        Delay.waitFor(500);
        return this;
    }

    public GanttChartsMilestonesPage clickModalApply() {
        wait.until(ExpectedConditions.elementToBeClickable(modalApplyButton)).click();
        System.out.println("Clicked Modal Apply");
        Delay.waitFor(2000); 
        return this;
    }

    // --- Group By ---
    public GanttChartsMilestonesPage clickGroupByMenuItem() {
        wait.until(ExpectedConditions.elementToBeClickable(groupByMenuItem)).click();
        System.out.println("Clicked 'Group by' Menu Item");
        wait.until(ExpectedConditions.visibilityOfElementLocated(groupByRadioLabel));
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartsMilestonesPage selectGroupByRadioButton() {
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(groupByRadioLabel));
        label.click();
        System.out.println("Selected 'Group by' Radio Button");
        Delay.waitFor(500);
        return this;
    }

    public GanttChartsMilestonesPage selectGroupByCriteria(String criteria) {
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(groupBySelectElement));
        Select dropdown = new Select(selectElement);
        dropdown.selectByVisibleText(criteria);
        System.out.println("Selected Group By criteria: " + criteria);
        Delay.waitFor(500);
        return this;
    }

    public GanttChartsMilestonesPage checkDisplaySums() {
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(displaySumsLabel));
        label.click();
        System.out.println("Checked 'Display Sums'");
        Delay.waitFor(500);
        return this;
    }

    // --- Info Pane & Details ---
    public GanttChartsMilestonesPage selectFirstMilestoneItem() {
        wait.until(ExpectedConditions.elementToBeClickable(firstTableItem)).click();
        System.out.println("Selected First Milestone Item");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartsMilestonesPage clickInfoButton() {
        wait.until(ExpectedConditions.elementToBeClickable(infoButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(detailsPaneContainer));
        System.out.println("Clicked Info (i) Button");
        Delay.waitFor(1000); 
        return this;
    }

    public GanttChartsMilestonesPage clickDetailsPaneMoreActions() {
        wait.until(ExpectedConditions.elementToBeClickable(detailsMoreActionsButton)).click();
        System.out.println("Clicked 'More Actions' in Details Pane");
        Delay.waitFor(500); 
        return this;
    }

    public GanttChartsMilestonesPage clickCopyLinkToClipboard() {
        wait.until(ExpectedConditions.elementToBeClickable(copyLinkToClipboardItem)).click();
        System.out.println("Clicked 'Copy link to clipboard'");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
            System.out.println("Success toast appeared!");
        } catch (Exception e) {}
        Delay.waitFor(1000);
        return this;
    }

    // --- Activity Tab ---
    public GanttChartsMilestonesPage clickActivityTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(activityTab));
        wait.until(ExpectedConditions.elementToBeClickable(activityTabLink)).click();
        System.out.println("Clicked Activity Tab");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartsMilestonesPage filterActivityCommentsOnly() {
        WebElement filterBtn = wait.until(ExpectedConditions.elementToBeClickable(activityFilterDropdown));
        filterBtn.click();
        System.out.println("Clicked Activity Filter Dropdown");
        Delay.waitFor(500);
        wait.until(ExpectedConditions.elementToBeClickable(showCommentsOnlyOption)).click();
        System.out.println("Selected 'Show comments only'");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartsMilestonesPage sortActivityNewestOnTop() {
        WebElement sortBtn = wait.until(ExpectedConditions.elementToBeClickable(activitySortDropdown));
        sortBtn.click();
        System.out.println("Clicked Activity Sort Dropdown");
        Delay.waitFor(500);
        wait.until(ExpectedConditions.elementToBeClickable(newestOnTopOption)).click();
        System.out.println("Selected 'Newest on top'");
        Delay.waitFor(1000);
        return this;
    }

    // --- Files, Relations, Meetings Tabs ---
    public GanttChartsMilestonesPage clickFilesTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(filesTab));
        wait.until(ExpectedConditions.elementToBeClickable(filesTabLink)).click();
        System.out.println("Clicked Files Tab");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartsMilestonesPage clickRelationsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(relationsTab));
        wait.until(ExpectedConditions.elementToBeClickable(relationsTabLink)).click();
        System.out.println("Clicked Relations Tab");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartsMilestonesPage clickMeetingsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(meetingsTab));
        wait.until(ExpectedConditions.elementToBeClickable(meetingsTabLink)).click();
        System.out.println("Clicked Meetings Tab");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartsMilestonesPage clickMeetingsPastTab() {
        wait.until(ExpectedConditions.elementToBeClickable(meetingsPastTab)).click();
        System.out.println("Clicked Meetings 'Past' Tab");
        Delay.waitFor(1000);
        return this;
    }

    public GanttChartsMilestonesPage clickMeetingsUpcomingTab() {
        wait.until(ExpectedConditions.elementToBeClickable(meetingsUpcomingTab)).click();
        System.out.println("Clicked Meetings 'Upcoming' Tab");
        Delay.waitFor(1000);
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║          [UPDATED] ZEN MODE, BACK, CLOSE               ║
    // ╚════════════════════════════════════════════════════════╝

    /**
     * Klik tombol Full Screen/Zen Mode di Panel Detail (Navigasi ke Single View)
     */
    public GanttChartsMilestonesPage clickDetailsFullScreen() {
        WebElement fsBtn = wait.until(ExpectedConditions.elementToBeClickable(detailsFullScreenButton));
        fsBtn.click();
        System.out.println("Clicked Details Full Screen (Navigated to Single View)");
        Delay.waitFor(1500); // Tunggu navigasi halaman
        return this;
    }

    /**
     * Klik tombol Back di halaman Single View
     */
    public GanttChartsMilestonesPage clickBackButton() {
        WebElement backBtn = wait.until(ExpectedConditions.elementToBeClickable(backButton));
        backBtn.click();
        System.out.println("Clicked Back Button (Return to Gantt)");
        // Tunggu sampai halaman Gantt (tombol Include Projects) muncul kembali
        wait.until(ExpectedConditions.visibilityOfElementLocated(includeProjectsButton));
        Delay.waitFor(1000);
        return this;
    }

    /**
     * Klik tombol Close (X) di Panel Detail
     */
    public GanttChartsMilestonesPage clickCloseDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(detailsCloseButton)).click();
        System.out.println("Clicked Close Details View (X)");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(detailsPaneContainer));
        } catch (Exception e) {}
        Delay.waitFor(1000);
        return this;
    }
}