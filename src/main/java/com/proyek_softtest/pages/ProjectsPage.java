package com.proyek_softtest.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class ProjectsPage extends BasePage {
    // sidebar
    private By projectsButtonInSidebar = By.cssSelector("a.main-menu--parent-node[href*='/projects']");
    private By searchBarInSidebar = By.cssSelector("input[data-test-selector='op-submenu--search-input']");
    private By noItemsFoundInSidebar = By.cssSelector("span[data-test-selector='op-submenu--search-no-results']");
    private By activeProjectsButtonInSidebar = By.cssSelector("a.op-submenu--item-action.selected[href*='query_id=active']");
    private By projectStatusToggle = By.xpath("//button[contains(@class,'op-submenu--title') and normalize-space()='Project status']");
    private By onTrackFilter = By.xpath("//a[@data-test-selector='op-submenu--item-action' and .//span[normalize-space()='On track']]");
    private By offTrackFilter = By.xpath("//a[@data-test-selector='op-submenu--item-action' and .//span[normalize-space()='Off track']]");
    private By atRiskFilter = By.xpath("//a[@data-test-selector='op-submenu--item-action' and .//span[normalize-space()='At risk']]");

    // main content
    private By homeBreadCrumbLink = By.linkText("safe.openproject.com");
    private By projectsBreadCrumbLink = By.cssSelector("a.Link[href='/projects']");

    // more menu button - TARGET DESKTOP VERSION ONLY (d-sm-flex = visible on desktop)
    private By moreMenuButton = By.cssSelector("button[data-test-selector='project-more-dropdown-menu'].d-sm-flex");

    // untuk button button ini mereka punya 2 versi (mobile & desktop) dengan locator yang sama
    // openAsGantt unik karena tabindex berubah 0 saat menu terbuka
    private By openAsGantt = By.xpath("//a[@id='projects-index-open-as-gantt' and @tabindex='0']");

    // yang lain tabindex tetap tidak berubah (-1)
    private By overallActivity = By.cssSelector("a[href='/activities']");
    private By exportButton = By.cssSelector("a[href='/projects/export_list_modal']");
    private By configureView = By.cssSelector("a[href='/project_queries/configure_view_modal']");
    private By zenMode = By.cssSelector("button[data-controller='projects-zen-mode']");

    // extra for export function
    private By exportDialogHeader = By.id("op-project-list-export-dialog-title");
    private By exportDialogCloseButton = By.cssSelector("button[data-close-dialog-id='op-project-list-export-dialog']");
    private By exportXlsButton = By.xpath("//a[contains(@href,'.xls') and .//span[normalize-space()='XLS']]");
    private By exportCsvButton = By.xpath("//a[contains(@href,'.csv') and .//span[normalize-space()='CSV']]");
    private By exportPdfButton = By.xpath("//a[contains(@href,'.pdf') and .//span[normalize-space()='PDF']]");
    private By exportCompletedHeader = By.xpath("//h2[normalize-space()='Export completed']");
    private By downloadLink = By.linkText("click here");
    private By closeExportButton = By.cssSelector("button[data-close-dialog-id='job-status-modal-dialog']");

    // extra for configure view function
    private By configureViewDialogHeader = By.id("op-project-list-configure-dialog-title");
    private By configureViewDialogCloseButton = By.cssSelector("button[data-close-dialog-id='op-project-list-configure-dialog'].close-button");
    // private By configureViewCombobox = By.cssSelector("ng-select.op-draggable-autocomplete--input");
    private By comboboxInput = By.cssSelector("ng-select.op-draggable-autocomplete--input input[role='combobox']");
    private By comboboxDropdownPanel = By.cssSelector(".ng-dropdown-panel");
    private By comboboxOptions = By.cssSelector(".ng-dropdown-panel .ng-option");
    private By comboboxNoItemsFound = By.cssSelector(".ng-dropdown-panel .ng-option-disabled");
    // private By selectedColumnsContainer = By.cssSelector(".op-draggable-autocomplete--selected");
    private By selectedColumnItems = By.cssSelector(".op-draggable-autocomplete--item");
    private By selectedColumnItemTexts = By.cssSelector(".op-draggable-autocomplete--item-text");
    private By cancelButtonInConfigureView = By.xpath("//button[@data-close-dialog-id='op-project-list-configure-dialog']//span[normalize-space()='Cancel']");
    private By applyButtonInConfigureView = By.cssSelector("button[data-test-selector='op-project-list-configure-dialog-submit']");
    private By columnItemRemoveButton = By.cssSelector("a.op-draggable-autocomplete--remove-item.icon-remove");
    private By columnsTab = By.id("tab-selects--columns");
    private By sortTab = By.id("tab-selects--sorting");
    private By sortFieldSelect = By.id("sort_field");
    private By sortAscendingButton = By.cssSelector("button[data-direction='asc']");
    private By sortDescendingButton = By.cssSelector("button[data-direction='desc']");
    private By allSortFieldSelects = By.cssSelector("select#sort_field");

    private By projectSearchbar = By.id("name_and_identifier");
    private By searchbarClearButton = By.id("project-filters-form-clear-button");
    private By noResultsMessage = By.xpath("//td[normalize-space()='There is currently nothing to display.']");
    private By filtersButton = By.cssSelector("button[data-filter--filters-form-target='filterFormToggle']");
    private By filterValueContainer = By.cssSelector("label.advanced-filters--filter-value[data-filter-name='active']");
    private By activeFilterSwitch = By.cssSelector("label[data-filter-name='active'] span.spot-switch--fake");
    private By filterCloseIcon = By.cssSelector("a.advanced-filters--close.icon-close");
    private By removeActiveFilter = By.cssSelector("a.filter_rem[data-filter--filters-form-filter-name-param='active']");
    private By addFilterSelect = By.id("add_filter_select");
    private By publicFilterContainer = By.cssSelector("label.advanced-filters--filter-value[data-filter-name='public']");
    private By publicFilterSwitch = By.cssSelector("label[data-filter-name='public'] span.spot-switch--fake");
    private By removePublicFilter = By.cssSelector("a.filter_rem[data-filter--filters-form-filter-name-param='public']");

    // Table header Name column locators
    private By nameColumnHeaderButton = By.id("menu-name-button");
    private By nameSortDescending = By.cssSelector("[data-test-selector='name-sort-desc']");
    private By nameSortAscending = By.cssSelector("[data-test-selector='name-sort-asc']");
    private By nameFilterBy = By.cssSelector("[data-test-selector='name-filter-by']");
    private By nameMoveColLeft = By.cssSelector("[data-test-selector='name-move-col-left']");
    private By nameMoveColRight = By.cssSelector("[data-test-selector='name-move-col-right']");
    private By nameAddColumn = By.cssSelector("[data-test-selector='name-add-column']");
    private By nameRemoveColumn = By.cssSelector("[data-test-selector='name-remove-column']");
    private By filterProjectInput = By.cssSelector("input[role='combobox'][aria-label='Search']");
    private By removeIdFilter = By.cssSelector("a.filter_rem[data-filter--filters-form-filter-name-param='id']");

    // Table row favorite and menu locators
    private By favoriteStarIcon = By.cssSelector("a[href*='favorite.html']");
    private By rowMenuButton = By.cssSelector("td.buttons button[aria-haspopup='true']");
    private By addToFavoritesMenuItem = By.cssSelector("a[aria-label='Add to favorites']");

    // pagination
    private By pagination50 = By.linkText("50");
    private By pagination100 = By.linkText("100");
    private By pagination200 = By.linkText("200");

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               SIDEBAR ACTIONS                          ║
    // ╚════════════════════════════════════════════════════════╝

    public ProjectsPage clickProjectsButtonInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsButtonInSidebar)).click();
        wait.until(ExpectedConditions.urlContains("/projects"));
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage searchInSidebar(String searchText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBarInSidebar));
        driver.findElement(searchBarInSidebar).clear();
        driver.findElement(searchBarInSidebar).sendKeys(searchText);
        Delay.waitDefault();
        return this;
    }

    public boolean isNoItemsFoundInSidebar() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(noItemsFoundInSidebar)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectsPage clickActiveProjectsButtonInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(activeProjectsButtonInSidebar)).click();
        wait.until(ExpectedConditions.urlContains("query_id=active"));
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickOnTrackFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(onTrackFilter)).click();
        wait.until(ExpectedConditions.urlContains("query_id=on_track"));
        Delay.waitDefault();
        return this;
    }

    public boolean isOnTrackFilterDisplayed() {
        try {
            return driver.findElement(onTrackFilter).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOffTrackFilterDisplayed() {
        try {
            return driver.findElement(offTrackFilter).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAtRiskFilterDisplayed() {
        try {
            return driver.findElement(atRiskFilter).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectsPage clickProjectStatusToggle() {
        wait.until(ExpectedConditions.elementToBeClickable(projectStatusToggle)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickOffTrackFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(offTrackFilter)).click();
        wait.until(ExpectedConditions.urlContains("query_id=off_track"));
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickAtRiskFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(atRiskFilter)).click();
        wait.until(ExpectedConditions.urlContains("query_id=at_risk"));
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               BREADCRUMB ACTIONS                       ║
    // ╚════════════════════════════════════════════════════════╝

    public ProjectsPage clickHomeBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(homeBreadCrumbLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/"));
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickProjectsBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsBreadCrumbLink)).click();
        wait.until(ExpectedConditions.urlContains("/projects"));
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               MORE MENU ACTIONS                        ║
    // ╚════════════════════════════════════════════════════════╝

    public ProjectsPage clickMoreMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(moreMenuButton)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isOpenAsGanttDisplayed() {
        try {
            // Element selalu ada di DOM, tapi tabindex berubah:
            // tabindex="-1" = menu tertutup (tidak interactable)
            // tabindex="0" = menu terbuka (visible dan interactable)
            WebElement element = driver.findElement(openAsGantt);
            String tabIndex = element.getAttribute("tabindex");
            return "0".equals(tabIndex);
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectsPage clickOpenAsGantt() {
        wait.until(ExpectedConditions.elementToBeClickable(openAsGantt)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickOverallActivity() {
        // Karena ada 2 element (mobile & desktop), kita harus ambil yang VISIBLE
        // ElementToBeClickable akan ambil yang pertama (mobile yang hidden), jadi kita manual filter
        wait.until(driver -> {
            return driver.findElements(overallActivity).stream()
                    .anyMatch(el -> el.isDisplayed());
        });

        WebElement visibleElement = driver.findElements(overallActivity).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Overall Activity button not visible"));

        visibleElement.click();
        wait.until(ExpectedConditions.urlContains("/activities"));
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickExportButton() {
        // Sama seperti clickOverallActivity, filter yang visible
        wait.until(driver -> {
            return driver.findElements(exportButton).stream()
                    .anyMatch(el -> el.isDisplayed());
        });

        WebElement visibleElement = driver.findElements(exportButton).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Export button not visible"));

        visibleElement.click();

        // Wait untuk export dialog muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(exportDialogHeader));
        Delay.waitDefault();
        return this;
    }

    public String getExportDialogHeaderText() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(exportDialogHeader));
        return header.getText();
    }

    public ProjectsPage clickExportDialogCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(exportDialogCloseButton)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickExportXlsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(exportXlsButton)).click();

        // Wait untuk export completed header muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(exportCompletedHeader));
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickExportCsvButton() {
        wait.until(ExpectedConditions.elementToBeClickable(exportCsvButton)).click();

        // Wait untuk export completed header muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(exportCompletedHeader));
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickExportPdfButton() {
        wait.until(ExpectedConditions.elementToBeClickable(exportPdfButton)).click();

        // Wait untuk export completed header muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(exportCompletedHeader));
        Delay.waitDefault();
        return this;
    }

    public boolean isExportCompletedHeaderDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(exportCompletedHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectsPage clickDownloadLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(downloadLink));

        // Get type attribute untuk verify
        String linkType = link.getAttribute("type");
        System.out.println("DEBUG: Download link type = " + linkType);

        link.click();

        Delay.waitFor(3000);

        return this;
    }

    public String getDownloadLinkType() {
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(downloadLink));
        return link.getAttribute("type");
    }

    public ProjectsPage clickCloseExportButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeExportButton)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickZenMode() {
        // Sama seperti clickOverallActivity, filter yang visible
        wait.until(driver -> {
            return driver.findElements(zenMode).stream()
                    .anyMatch(el -> el.isDisplayed());
        });

        WebElement visibleElement = driver.findElements(zenMode).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Zen Mode button not visible"));

        visibleElement.click();
        Delay.waitDefault();
        return this;
    }

    public boolean isFullscreen() {
        return (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.fullscreenElement !== null");
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║           CONFIGURE VIEW ACTIONS                       ║
    // ╚════════════════════════════════════════════════════════╝

    public ProjectsPage clickConfigureView() {
        // Filter yang visible
        wait.until(driver -> {
            return driver.findElements(configureView).stream()
                    .anyMatch(el -> el.isDisplayed());
        });

        WebElement visibleElement = driver.findElements(configureView).stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Configure View button not visible"));

        visibleElement.click();

        // Wait untuk configure view dialog muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(configureViewDialogHeader));
        Delay.waitDefault();
        return this;
    }

    public boolean isConfigureViewDialogHeaderDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(configureViewDialogHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectsPage clickConfigureViewDialogCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(configureViewDialogCloseButton)).click();
        Delay.waitDefault();
        return this;
    }


    public ProjectsPage clickConfigureViewCombobox() {
        WebElement combobox = wait.until(ExpectedConditions.elementToBeClickable(comboboxInput));
        combobox.click();

        // Wait untuk dropdown muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(comboboxOptions));
        Delay.waitFor(500);
        return this;
    }

    public boolean isComboboxDropdownVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(comboboxDropdownPanel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getComboboxOptions() {
        // Wait untuk options muncul
        wait.until(ExpectedConditions.presenceOfElementLocated(comboboxOptions));

        // Get all ng-option elements
        List<WebElement> allOptions = driver.findElements(comboboxOptions);

        // Filter hanya yang visible dan tidak disabled
        return allOptions.stream()
                .filter(opt -> {
                    try {
                        // Exclude yang disabled (ng-option-disabled)
                        String classAttr = opt.getAttribute("class");
                        if (classAttr != null && classAttr.contains("ng-option-disabled")) {
                            return false;
                        }
                        // Hanya yang visible
                        return opt.isDisplayed();
                    } catch (Exception e) {
                        return false;
                    }
                })
                .toList();
    }

    public List<String> getSelectedColumnNames() {
        List<WebElement> columnTexts = driver.findElements(selectedColumnItemTexts);
        return columnTexts.stream()
                .map(el -> el.getText().trim())
                .toList();
    }

    public boolean isColumnSelected(String columnName) {
        List<String> selectedColumns = getSelectedColumnNames();
        return selectedColumns.contains(columnName);
    }

    public boolean isOptionAvailableInDropdown(String optionText) {
        try {
            List<WebElement> options = getComboboxOptions();
            return options.stream()
                    .anyMatch(opt -> opt.getText().trim().equals(optionText));
        } catch (Exception e) {
            return false;
        }
    }

    public void printComboboxOptionsDebug() {
        List<WebElement> allOptions = driver.findElements(comboboxOptions);
        System.out.println("\n=== DEBUG: All ng-option elements ===");
        for (int i = 0; i < allOptions.size(); i++) {
            WebElement opt = allOptions.get(i);
            String text = opt.getText().trim();
            String className = opt.getAttribute("class");
            boolean isDisplayed = opt.isDisplayed();
            System.out.println("[" + i + "] Text: '" + text + "' | Class: " + className + " | Displayed: " + isDisplayed);
        }
        System.out.println("=== END DEBUG ===");
    }

    public boolean isNoItemsFoundDisplayed() {
        try {
            WebElement noItems = wait.until(ExpectedConditions.visibilityOfElementLocated(comboboxNoItemsFound));
            return noItems.isDisplayed() && noItems.getText().trim().equals("No items found");
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectsPage selectComboboxOptionByIndex(int index) {
        List<WebElement> options = getComboboxOptions();
        if (index >= 0 && index < options.size()) {
            options.get(index).click();
            Delay.waitFor(100);
        } else {
            throw new IndexOutOfBoundsException("Combobox option index out of bounds: " + index);
        }
        return this;
    }

    public ProjectsPage selectComboboxOptionByText(String text) {
        List<WebElement> options = getComboboxOptions();
        WebElement option = options.stream()
                .filter(opt -> opt.getText().trim().equals(text))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Combobox option not found: " + text));

        option.click();
        Delay.waitFor(100);
        return this;
    }

    public ProjectsPage clickCancelButtonInConfigureView() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButtonInConfigureView)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickApplyButtonInConfigureView() {
        wait.until(ExpectedConditions.elementToBeClickable(applyButtonInConfigureView)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║                 DRAG AND DROP ACTIONS                  ║
    // ╚════════════════════════════════════════════════════════╝

    public List<WebElement> getSelectedColumnElements() {
        return driver.findElements(selectedColumnItems);
    }

    public WebElement getSelectedColumnElementByText(String columnName) {
        List<WebElement> columnElements = getSelectedColumnElements();
        return columnElements.stream()
                .filter(el -> {
                    try {
                        WebElement textElement = el.findElement(selectedColumnItemTexts);
                        return textElement.getText().trim().equals(columnName);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Selected column not found: " + columnName));
    }

    public ProjectsPage dragColumnToPosition(String sourceColumnName, int targetPosition) {
        // Get source element
        WebElement sourceElement = getSelectedColumnElementByText(sourceColumnName);

        // Get all column elements
        List<WebElement> allColumns = getSelectedColumnElements();

        // Validate target position
        if (targetPosition < 0 || targetPosition >= allColumns.size()) {
            throw new IndexOutOfBoundsException("Target position out of bounds: " + targetPosition);
        }

        // Get target element
        WebElement targetElement = allColumns.get(targetPosition);

        // Perform drag and drop using Actions
        Actions actions = new Actions(driver);
        actions.clickAndHold(sourceElement)
                .pause(500) // Pause untuk stabilitas
                .moveToElement(targetElement)
                .pause(500) // Pause sebelum release
                .release()
                .build()
                .perform();

        Delay.waitDefault(); // Wait untuk animation selesai
        return this;
    }

    public int getSelectedColumnPosition(String columnName) {
        List<WebElement> columnElements = getSelectedColumnElements();
        for (int i = 0; i < columnElements.size(); i++) {
            try {
                WebElement textElement = columnElements.get(i).findElement(selectedColumnItemTexts);
                if (textElement.getText().trim().equals(columnName)) {
                    return i;
                }
            } catch (Exception e) {
                // Continue
            }
        }
        return -1; // Not found
    }

    public ProjectsPage removeColumnByName(String columnName) {
        WebElement columnElement = getSelectedColumnElementByText(columnName);

        // Find remove button within this column element
        try {
            WebElement removeButton = columnElement.findElement(columnItemRemoveButton);
            removeButton.click();
            Delay.waitDefault(); // Wait untuk remove animation
            return this;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Remove button not found for column: " + columnName + ". This column might not be removable.");
        }
    }

    public ProjectsPage clickColumnsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(columnsTab)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickSortTab() {
        wait.until(ExpectedConditions.elementToBeClickable(sortTab)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isSortFieldDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(sortFieldSelect)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectsPage selectSortFieldByValue(String value) {
        WebElement select = wait.until(ExpectedConditions.elementToBeClickable(sortFieldSelect));
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(select);
        dropdown.selectByValue(value);
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage selectNthSortFieldByValue(int index, String value) {
        List<WebElement> allSelects = driver.findElements(allSortFieldSelects);
        if (index >= allSelects.size()) {
            throw new IndexOutOfBoundsException("Sort field index out of bounds: " + index);
        }
        WebElement select = allSelects.get(index);
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(select);
        dropdown.selectByValue(value);
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickSortAscending(int index) {
        List<WebElement> ascButtons = driver.findElements(sortAscendingButton);
        if (index >= ascButtons.size()) {
            throw new IndexOutOfBoundsException("Ascending button index out of bounds: " + index);
        }
        ascButtons.get(index).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickSortDescending(int index) {
        List<WebElement> descButtons = driver.findElements(sortDescendingButton);
        if (index >= descButtons.size()) {
            throw new IndexOutOfBoundsException("Descending button index out of bounds: " + index);
        }
        descButtons.get(index).click();
        Delay.waitDefault();
        return this;
    }

    public int getSortFieldCount() {
        return driver.findElements(allSortFieldSelects).size();
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║             SEARCHBAR ACTIONS                          ║
    // ╚════════════════════════════════════════════════════════╝

    public ProjectsPage typeInSearchbar(String text) {
        WebElement searchbox = wait.until(ExpectedConditions.elementToBeClickable(projectSearchbar));
        searchbox.clear();
        searchbox.sendKeys(text);
        searchbox.sendKeys(org.openqa.selenium.Keys.ENTER);
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickClearSearchbar() {
        wait.until(ExpectedConditions.elementToBeClickable(searchbarClearButton)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isNoResultsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectsPage clickProjectLinkByText(String linkText) {
        By projectLink = By.xpath("//a[normalize-space()='" + linkText + "']");
        wait.until(ExpectedConditions.elementToBeClickable(projectLink)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isProjectTitleDisplayed(String expectedTitle) {
        try {
            By titleSpan = By.xpath("//span[@class='ellipsis' and @title='" + expectedTitle + "']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(titleSpan)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               FILTER ACTIONS                           ║
    // ╚════════════════════════════════════════════════════════╝

    public ProjectsPage toggleFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(filtersButton)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage openFilter() {
        // Click filter button and wait for filter panel to appear
        wait.until(ExpectedConditions.elementToBeClickable(filtersButton)).click();
        // Wait for filter value container to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterValueContainer));
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage closeFilterByButton() {
        // Click filter button to close
        wait.until(ExpectedConditions.elementToBeClickable(filtersButton)).click();
        Delay.waitDefault();
        return this;
    }


    public ProjectsPage closeFilterByIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(filterCloseIcon)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isFilterValueContainerDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(filterValueContainer)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectsPage clickActiveFilterSwitch() {
        wait.until(ExpectedConditions.elementToBeClickable(activeFilterSwitch)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickRemoveActiveFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(removeActiveFilter)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isFilterPanelClosed() {
        try {
            // Filter panel is closed when aria-pressed=false
            WebElement button = driver.findElement(filtersButton);
            return "false".equals(button.getAttribute("aria-pressed"));
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectsPage addFilterByValue(String value) {
        WebElement select = wait.until(ExpectedConditions.elementToBeClickable(addFilterSelect));
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(select);
        dropdown.selectByValue(value);
        Delay.waitDefault();
        return this;
    }

    public boolean isPublicFilterDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(publicFilterContainer)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectsPage clickPublicFilterSwitch() {
        wait.until(ExpectedConditions.elementToBeClickable(publicFilterSwitch)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickRemovePublicFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(removePublicFilter)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║          TABLE HEADER ACTIONS (Name Column)            ║
    // ╚════════════════════════════════════════════════════════╝

    public ProjectsPage clickNameColumnHeader() {
        wait.until(ExpectedConditions.elementToBeClickable(nameColumnHeaderButton)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickNameSortDescending() {
        wait.until(ExpectedConditions.elementToBeClickable(nameSortDescending)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickNameSortAscending() {
        wait.until(ExpectedConditions.elementToBeClickable(nameSortAscending)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickNameFilterBy() {
        wait.until(ExpectedConditions.elementToBeClickable(nameFilterBy)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickNameMoveColLeft() {
        wait.until(ExpectedConditions.elementToBeClickable(nameMoveColLeft)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickNameMoveColRight() {
        wait.until(ExpectedConditions.elementToBeClickable(nameMoveColRight)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickNameAddColumn() {
        wait.until(ExpectedConditions.elementToBeClickable(nameAddColumn)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickNameRemoveColumn() {
        wait.until(ExpectedConditions.elementToBeClickable(nameRemoveColumn)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage selectProjectFromFilterDropdown(String projectName) {
        // Click on the input to open dropdown
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(filterProjectInput));
        input.click();
        Delay.waitDefault();
        
        // Select option by text
        By optionLocator = By.xpath("//div[@class='ng-option-label ellipsis' and @title='" + projectName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickRemoveIdFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(removeIdFilter)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isProjectLinkDisplayed(String projectName) {
        try {
            By projectLink = By.xpath("//a[normalize-space()='" + projectName + "']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(projectLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               TABLE ROW ACTIONS                        ║
    // ╚════════════════════════════════════════════════════════╝

    public ProjectsPage clickFirstFavoriteStarIcon() {
        // Wait for element to be present first
        wait.until(ExpectedConditions.presenceOfElementLocated(favoriteStarIcon));
        List<WebElement> stars = driver.findElements(favoriteStarIcon);
        if (!stars.isEmpty()) {
            WebElement star = stars.get(0);
            // Scroll to element and click with JavaScript to avoid interception
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", star);
            Delay.waitDefault();
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", star);
            Delay.waitDefault();
        }
        return this;
    }

    public ProjectsPage clickFirstRowMenuButton() {
        List<WebElement> buttons = driver.findElements(rowMenuButton);
        if (!buttons.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(buttons.get(0))).click();
            Delay.waitDefault();
        }
        return this;
    }

    public ProjectsPage clickAddToFavoritesMenuItem() {
        wait.until(ExpectedConditions.elementToBeClickable(addToFavoritesMenuItem)).click();
        Delay.waitDefault();
        return this;
    }


    // ╔════════════════════════════════════════════════════════╗
    // ║                  PAGINATION ACTIONS                    ║
    // ╚════════════════════════════════════════════════════════╝
    public ProjectsPage clickPagination50() {
        wait.until(ExpectedConditions.elementToBeClickable(pagination50)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickPagination100() {
        wait.until(ExpectedConditions.elementToBeClickable(pagination100)).click();
        Delay.waitDefault();
        return this;
    }

    public ProjectsPage clickPagination200() {
        wait.until(ExpectedConditions.elementToBeClickable(pagination200)).click();
        Delay.waitDefault();
        return this;
    }
}
