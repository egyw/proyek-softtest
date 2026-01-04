package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys; // Import ini yang tadi kurang
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


    public GanttChartsMilestonesPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: NAVIGATION                      ║
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

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: INCLUDE PROJECTS                ║
    // ╚════════════════════════════════════════════════════════╝

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

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: BASELINE                        ║
    // ╚════════════════════════════════════════════════════════╝

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

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: ADVANCED FILTER                 ║
    // ╚════════════════════════════════════════════════════════╝

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
        input.sendKeys(Keys.ENTER); // Memerlukan import org.openqa.selenium.Keys
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

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: TOOLBAR BUTTONS                 ║
    // ╚════════════════════════════════════════════════════════╝

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

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: CONFIGURE VIEW                  ║
    // ╚════════════════════════════════════════════════════════╝

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

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: GROUP BY CONFIG                 ║
    // ╚════════════════════════════════════════════════════════╝

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
}