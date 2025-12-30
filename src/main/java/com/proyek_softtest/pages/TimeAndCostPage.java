package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class TimeAndCostPage extends BasePage {
    private By timeAndCostButtonInSidebar = By.xpath("//a[contains(@class,'main-menu--parent-node') and contains(@href,'cost_reports') and normalize-space()='Time and costs']");
    private By openProjectBreadcrumbLink = By.xpath("//a[@class='Link' and normalize-space()='safe.openproject.com']");
    private By timeAndCostBreadcrumbLink = By.xpath("//a[@class='Link' and normalize-space()='Time and costs']");
    private By filterCollapseable = By.xpath("//h3[normalize-space()='Filter']");
    private By groupByCollapseable = By.xpath("//h3[normalize-space()='Group by']");
    private By unitsCollapseable = By.xpath("//h3[normalize-space()='Units']");
    private By applyButton = By.id("query-icon-apply-button");
    private By clearButton = By.id("query-link-clear");
    
    // element dalam filter
    private By addFilterDropdown = By.id("add_filter_select");
    private By dateOperatorDropdown = By.id("operators[spent_on]");
    private By userFilterNgSelect = By.cssSelector("ng-select#user_id_select_1 input[role='combobox']");
    private By userFilterDropdownOptions = By.cssSelector("ng-dropdown-panel .ng-option");
    private By removeFilterIcon = By.cssSelector("#rm_box_spent_on a.filter_rem");
    private By noResultsMessage = By.cssSelector(".generic-table--no-results-title");

    // element dalam group by
    private By groupByColumnsDropdown = By.id("group-by--add-columns");
    private By groupByRowsDropdown = By.id("group-by--add-rows");
    private By selectedColumnsContainer = By.id("group-by--selected-columns");
    private By selectedRowsContainer = By.id("group-by--selected-rows");
    private By groupBySelectedElement = By.cssSelector(".group-by--selected-element");
    private By groupByRemoveButton = By.cssSelector("a.group-by--remove");

    // element dalam units
    private By laborRadioButton = By.id("unit_-1");
    private By cashValueRadioButton = By.id("unit_0");
    
    public TimeAndCostPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               SIDEBAR ACTIONS                          ║
    // ╚════════════════════════════════════════════════════════╝

    public TimeAndCostPage clickTimeAndCostButtonInSidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(timeAndCostButtonInSidebar)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               BREADCRUMB ACTIONS                       ║
    // ╚════════════════════════════════════════════════════════╝

    public TimeAndCostPage clickOpenProjectBreadcrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(openProjectBreadcrumbLink)).click();
        Delay.waitDefault();
        return this;
    }

    public TimeAndCostPage clickTimeAndCostBreadcrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(timeAndCostBreadcrumbLink)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               COLLAPSEABLE ACTIONS                     ║
    // ╚════════════════════════════════════════════════════════╝

    public TimeAndCostPage clickFilterCollapseable() {
        wait.until(ExpectedConditions.elementToBeClickable(filterCollapseable)).click();
        Delay.waitDefault();
        return this;
    }

    public TimeAndCostPage clickGroupByCollapseable() {
        wait.until(ExpectedConditions.elementToBeClickable(groupByCollapseable)).click();
        Delay.waitDefault();
        return this;
    }

    public TimeAndCostPage clickUnitsCollapseable() {
        wait.until(ExpectedConditions.elementToBeClickable(unitsCollapseable)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               VISIBILITY CHECKS                        ║
    // ╚════════════════════════════════════════════════════════╝

    public boolean isAddFilterDropdownVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(addFilterDropdown)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGroupByColumnsVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(groupByColumnsDropdown)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLaborRadioButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(laborRadioButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public TimeAndCostPage clickLaborRadioButton() {
        wait.until(ExpectedConditions.elementToBeClickable(laborRadioButton)).click();
        Delay.waitDefault();
        return this;
    }

    public TimeAndCostPage clickCashValueRadioButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cashValueRadioButton)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               FILTER ACTIONS                           ║
    // ╚════════════════════════════════════════════════════════╝

    public TimeAndCostPage selectFilterByValue(String value) {
        org.openqa.selenium.WebElement select = wait.until(ExpectedConditions.elementToBeClickable(addFilterDropdown));
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(select);
        dropdown.selectByValue(value);
        Delay.waitDefault();
        return this;
    }

    public int selectAllAvailableFilters() {
        int selectedCount = 0;
        String[] filterValues = {
            "responsible_id", "activity_id", "assigned_to_id", "author_id", 
            "budget_id", "category_id", "created_on", "spent_on", "due_date", 
            "logged_by_id", "overridden_costs", "priority_id", "project_id", 
            "start_date", "status_id", "subject", "type_id", "updated_on", 
            "user_id", "version_id", "work_package_id",
            "custom_field6", "custom_field4", "custom_field1", "custom_field5", 
            "custom_field7", "custom_field2", "custom_field3"
        };
        
        for (String value : filterValues) {
            try {
                org.openqa.selenium.WebElement select = wait.until(ExpectedConditions.elementToBeClickable(addFilterDropdown));
                
                // Check if option exists and is enabled (not disabled)
                org.openqa.selenium.WebElement option = select.findElement(By.cssSelector("option[value='" + value + "']"));
                String disabledAttr = option.getAttribute("disabled");
                
                // If disabled attribute is null or empty, the option is available
                if (disabledAttr == null || disabledAttr.isEmpty()) {
                    // Click dropdown first to open it (for visual effect)
                    select.click();
                    Delay.waitFor(200);
                    
                    // Now select the option
                    org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(select);
                    dropdown.selectByValue(value);
                    selectedCount++;
                    System.out.println("Selected filter: " + value);
                    Delay.waitFor(300);
                } else {
                    System.out.println("Filter already disabled: " + value);
                }
            } catch (Exception e) {
                // Option might not exist or other error
                System.out.println("Could not select filter: " + value + " - " + e.getMessage());
            }
        }
        return selectedCount;
    }

    public int getAvailableFilterCount() {
        org.openqa.selenium.WebElement select = wait.until(ExpectedConditions.presenceOfElementLocated(addFilterDropdown));
        java.util.List<org.openqa.selenium.WebElement> options = select.findElements(By.cssSelector("option:not([disabled]):not([value=''])"));
        return options.size();
    }

    public TimeAndCostPage selectDateOperator(String value) {
        org.openqa.selenium.WebElement select = wait.until(ExpectedConditions.elementToBeClickable(dateOperatorDropdown));
        select.click();
        Delay.waitFor(200);
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(select);
        dropdown.selectByValue(value);
        Delay.waitDefault();
        return this;
    }

    public TimeAndCostPage clickUserFilterNgSelect() {
        wait.until(ExpectedConditions.elementToBeClickable(userFilterNgSelect)).click();
        Delay.waitDefault();
        return this;
    }

    public TimeAndCostPage selectUserFromDropdown(String userName) {
        // Wait for dropdown options to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(userFilterDropdownOptions));
        Delay.waitFor(300);
        
        // Find and click the option with matching text
        java.util.List<org.openqa.selenium.WebElement> options = driver.findElements(userFilterDropdownOptions);
        for (org.openqa.selenium.WebElement option : options) {
            if (option.getText().trim().contains(userName)) {
                option.click();
                Delay.waitDefault();
                return this;
            }
        }
        throw new RuntimeException("User not found in dropdown: " + userName);
    }

    public TimeAndCostPage clickRemoveFirstFilter() {
        java.util.List<org.openqa.selenium.WebElement> removeButtons = driver.findElements(removeFilterIcon);
        if (!removeButtons.isEmpty()) {
            removeButtons.get(0).click();
            Delay.waitDefault();
        }
        return this;
    }

    public TimeAndCostPage clickApplyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(applyButton)).click();
        Delay.waitDefault();
        return this;
    }

    public boolean isNoResultsDisplayed() {
        try {
            org.openqa.selenium.WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsMessage));
            return element.getText().contains("There is currently nothing to display");
        } catch (Exception e) {
            return false;
        }
    }

    public TimeAndCostPage clickClearButton() {
        wait.until(ExpectedConditions.elementToBeClickable(clearButton)).click();
        Delay.waitDefault();
        return this;
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║               GROUP BY ACTIONS                         ║
    // ╚════════════════════════════════════════════════════════╝

    public TimeAndCostPage selectGroupByColumn(String value) {
        org.openqa.selenium.WebElement select = wait.until(ExpectedConditions.elementToBeClickable(groupByColumnsDropdown));
        select.click();
        Delay.waitFor(200);
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(select);
        dropdown.selectByValue(value);
        Delay.waitDefault();
        return this;
    }

    public TimeAndCostPage selectGroupByRow(String value) {
        org.openqa.selenium.WebElement select = wait.until(ExpectedConditions.elementToBeClickable(groupByRowsDropdown));
        select.click();
        Delay.waitFor(200);
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(select);
        dropdown.selectByValue(value);
        Delay.waitDefault();
        return this;
    }

    public java.util.List<org.openqa.selenium.WebElement> getGroupByColumnElements() {
        org.openqa.selenium.WebElement container = wait.until(ExpectedConditions.presenceOfElementLocated(selectedColumnsContainer));
        return container.findElements(groupBySelectedElement);
    }

    public java.util.List<org.openqa.selenium.WebElement> getGroupByRowElements() {
        org.openqa.selenium.WebElement container = wait.until(ExpectedConditions.presenceOfElementLocated(selectedRowsContainer));
        return container.findElements(groupBySelectedElement);
    }

    public TimeAndCostPage dragGroupByColumnToPosition(int fromIndex, int toIndex) {
        java.util.List<org.openqa.selenium.WebElement> elements = getGroupByColumnElements();
        if (fromIndex < elements.size() && toIndex < elements.size()) {
            org.openqa.selenium.WebElement source = elements.get(fromIndex);
            org.openqa.selenium.WebElement target = elements.get(toIndex);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.dragAndDrop(source, target).perform();
            Delay.waitDefault();
        }
        return this;
    }

    public TimeAndCostPage dragGroupByRowToPosition(int fromIndex, int toIndex) {
        java.util.List<org.openqa.selenium.WebElement> elements = getGroupByRowElements();
        if (fromIndex < elements.size() && toIndex < elements.size()) {
            org.openqa.selenium.WebElement source = elements.get(fromIndex);
            org.openqa.selenium.WebElement target = elements.get(toIndex);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.dragAndDrop(source, target).perform();
            Delay.waitDefault();
        }
        return this;
    }

    public TimeAndCostPage removeGroupByColumnByIndex(int index) {
        org.openqa.selenium.WebElement container = wait.until(ExpectedConditions.presenceOfElementLocated(selectedColumnsContainer));
        java.util.List<org.openqa.selenium.WebElement> removeButtons = container.findElements(groupByRemoveButton);
        if (index < removeButtons.size()) {
            removeButtons.get(index).click();
            Delay.waitDefault();
        }
        return this;
    }

    public TimeAndCostPage removeGroupByRowByIndex(int index) {
        org.openqa.selenium.WebElement container = wait.until(ExpectedConditions.presenceOfElementLocated(selectedRowsContainer));
        java.util.List<org.openqa.selenium.WebElement> removeButtons = container.findElements(groupByRemoveButton);
        if (index < removeButtons.size()) {
            removeButtons.get(index).click();
            Delay.waitDefault();
        }
        return this;
    }
}
