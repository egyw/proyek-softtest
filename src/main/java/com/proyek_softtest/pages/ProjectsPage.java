package com.proyek_softtest.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private By configureViewCombobox = By.cssSelector("ng-select.op-draggable-autocomplete--input");
    private By comboboxInput = By.cssSelector("ng-select.op-draggable-autocomplete--input input[role='combobox']");
    private By comboboxDropdownPanel = By.cssSelector(".ng-dropdown-panel");
    private By comboboxOptions = By.cssSelector(".ng-dropdown-panel .ng-option");
    private By comboboxNoItemsFound = By.cssSelector(".ng-dropdown-panel .ng-option-disabled");
    private By selectedColumnsContainer = By.cssSelector(".op-draggable-autocomplete--selected");
    private By selectedColumnItems = By.cssSelector(".op-draggable-autocomplete--item");
    private By selectedColumnItemTexts = By.cssSelector(".op-draggable-autocomplete--item-text");
    private By cancelButtonInConfigureView = By.xpath("//button[@data-close-dialog-id='op-project-list-configure-dialog']//span[normalize-space()='Cancel']");
    private By applyButtonInConfigureView = By.cssSelector("button[data-test-selector='op-project-list-configure-dialog-submit']");

    private By projectSearchbar = By.id("name_and_identifier");
    private By filtersButtonOpen =By.cssSelector("button[data-filter--filters-form-target='filterFormToggle'][aria-pressed='true']");
    private By filtersButtonClosed =By.cssSelector("button[data-filter--filters-form-target='filterFormToggle'][aria-pressed='false']");

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
}
