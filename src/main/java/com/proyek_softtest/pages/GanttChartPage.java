package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    // --- Configure View (UPDATED LOCATOR) ---
    
    // 1. Tombol Titik Tiga (More actions) - ID UNIK
    private By moreActionsButton = By.id("work-packages-settings-button");

    // 2. Menu Item 'Configure view' - PERBAIKAN: Menggunakan aria-label
    // Locator ini jauh lebih spesifik dan langsung menargetkan tombol yang bisa diklik
    private By configureViewMenuItem = By.cssSelector("button[aria-label='Configure view']");

    // 3. Tombol X pada kolom ID
    private By removeIdColumnButton = By.xpath("//span[contains(@class, 'op-draggable-autocomplete--item-text') and contains(text(), 'ID')]/following-sibling::a");

    // 4. Input 'Search a column'
    private By addColumnInput = By.cssSelector("ng-select.op-draggable-autocomplete--input input");

    // 5. Opsi Dropdown (Dynamic)
    private String dropdownOptionXpath = "//div[contains(@class, 'ng-option') and contains(., '%s')]";

    // 6. Tombol Apply di Modal Configure View
    private By modalApplyButton = By.xpath("//div[contains(@class, 'spot-action-bar')]//button[contains(text(), 'Apply')]");


    public GanttChartPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: NAVIGATION                      ║
    // ╚════════════════════════════════════════════════════════╝

    public GanttChartPage clickGanttChartsSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(ganttChartsSidebarLink)).click();
        wait.until(ExpectedConditions.urlContains("gantt"));
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: INCLUDE PROJECTS                ║
    // ╚════════════════════════════════════════════════════════╝

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

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: BASELINE                        ║
    // ╚════════════════════════════════════════════════════════╝

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

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: ADVANCED FILTERS                ║
    // ╚════════════════════════════════════════════════════════╝

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

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: TOOLBAR BUTTONS                 ║
    // ╚════════════════════════════════════════════════════════╝

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

    // ╔════════════════════════════════════════════════════════╗
    // ║               ACTIONS: CONFIGURE VIEW                  ║
    // ╚════════════════════════════════════════════════════════╝

    /**
     * Klik tombol titik tiga (More actions)
     */
    public GanttChartPage clickMoreActionsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(moreActionsButton)).click();
        System.out.println("Clicked More Actions");
        Delay.waitFor(1000); // Beri waktu untuk menu render
        return this;
    }

    /**
     * Pilih 'Configure view'
     */
    public GanttChartPage clickConfigureView() {
        // Menggunakan locator CSS Selector yang sangat spesifik
        wait.until(ExpectedConditions.elementToBeClickable(configureViewMenuItem)).click();
        System.out.println("Clicked Configure View");
        // Tunggu modal muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(addColumnInput));
        Delay.waitFor(1000);
        return this;
    }

    /**
     * Hapus kolom ID
     */
    public GanttChartPage removeIdColumn() {
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

    /**
     * Tambah kolom 'Author'
     */
    public GanttChartPage addColumn(String columnName) {
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

    /**
     * Klik tombol Apply di modal Configure View
     */
    public GanttChartPage clickModalApply() {
        wait.until(ExpectedConditions.elementToBeClickable(modalApplyButton)).click();
        System.out.println("Clicked Modal Apply");
        Delay.waitFor(2000); // Tunggu reload tabel
        return this;
    }
}