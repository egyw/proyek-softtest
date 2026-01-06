package com.proyek_softtest.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class WorkPackagesPage extends BasePage {

    private By workPackagesSidebar = By.cssSelector("a[title='Work packages']");
    private By AllProjectsTab = By.id("projects-menu");
    private By projectSearchInput = By.cssSelector("input[placeholder='Search projects...']");
    private By projectListItems = By.cssSelector(".spot-list--item-title span");
    private By projectListsButton = By.cssSelector("a.spot-action-bar--action[href='/projects']");
    private By mainMenuBackButton = By.cssSelector("a[aria-label='Go back one menu level']");
    private By workPackagesToggler = By.cssSelector("[data-test-selector='main-menu-toggler--work_packages']");
    private By collapseSidebarButton = By.id("menu-toggle--collapse-button");
    private By expandSidebarButton = By.id("menu-toggle--expand-button");
    private By workPackagesButton = By
            .xpath("//a[contains(@class, 'main-menu--parent-node') and contains(text(), 'Work packages')]");
    private By subMenuSearchInput = By.cssSelector("input[data-test-selector='op-submenu--search-input']");
    private By subMenuResultItems = By.cssSelector(".op-submenu--item-title");
    private By toolbarContainer = By.cssSelector("ul.toolbar-items");
    private By toolbarButtons = By.cssSelector("ul.toolbar-items button");

    public WorkPackagesPage(WebDriver driver) {
        super(driver);
    }

    public WorkPackagesPage clickWorkPackagesSideBar() {
        wait.until(ExpectedConditions.elementToBeClickable(workPackagesSidebar)).click();
        wait.until(ExpectedConditions.urlContains("work"));
        Delay.waitFor(500);
        return this;
    }

    public WorkPackagesPage clickAllProjectsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(AllProjectsTab)).click();
        return this;
    }

    public boolean isAllProjectsTabOpen() {
        try {
            WebElement tabButton = driver.findElement(AllProjectsTab);
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(projectSearchInput));
            return searchInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean searchAndVerifyProject(String projectName) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(projectSearchInput));
        searchField.click();
        searchField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        searchField.sendKeys(Keys.DELETE);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }

        searchField.sendKeys(projectName);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        List<WebElement> results = driver.findElements(projectListItems);

        for (WebElement result : results) {
            if (result.getText().trim().equalsIgnoreCase(projectName)) {
                return true;
            }
        }
        return false;
    }

    public void typeSearchProject(String keyword) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(projectSearchInput));

        searchField.click();

        searchField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        searchField.sendKeys(Keys.DELETE);
        Delay.waitFor(200);

        searchField.sendKeys(keyword);

        Delay.waitFor(1000);
    }

    public boolean isProjectListEmpty() {
        List<WebElement> results = driver.findElements(projectListItems);
        return results.isEmpty();
    }

    public void clickProjectResult(String projectName) {
        List<WebElement> results = driver.findElements(projectListItems);

        boolean isClicked = false;
        for (WebElement result : results) {

            if (result.getText().trim().equalsIgnoreCase(projectName)) {

                wait.until(ExpectedConditions.elementToBeClickable(result));

                result.click();
                isClicked = true;
                break;
            }
        }

        if (!isClicked) {
            throw new RuntimeException("Project '" + projectName + "' tidak ditemukan atau tidak bisa diklik!");
        }

        Delay.waitFor(1000);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void navigateBackToWorkPackages() {
        String targetUrl = "https://safe.openproject.com/work_packages";

        driver.get(targetUrl);
        Delay.waitFor(1000);
    }

    public void clickProjectLists() {
        wait.until(ExpectedConditions.elementToBeClickable(projectListsButton)).click();
    }

    public void clickMainMenuBackButton() {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(mainMenuBackButton));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void clickWorkPackagesToggler() {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(workPackagesToggler));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    public void collapseSidebar() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(collapseSidebarButton));
            wait.until(ExpectedConditions.elementToBeClickable(collapseSidebarButton)).click();
        } catch (Exception e) {
            System.out.println("Sidebar mungkin sudah tertutup (Tombol collapse tidak ketemu).");
        }
    }

    public void expandSidebar() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(expandSidebarButton));
            wait.until(ExpectedConditions.elementToBeClickable(expandSidebarButton)).click();
        } catch (Exception e) {
            System.out.println("Sidebar mungkin sudah terbuka (Tombol expand tidak ketemu).");
        }
    }

    public void clickWorkPackagesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(workPackagesButton)).click();
    }

    public boolean searchInSubMenu(String keyword) {

        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(subMenuSearchInput));

        searchField.click();
        searchField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        searchField.sendKeys(Keys.DELETE);

        searchField.sendKeys(keyword);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        List<WebElement> results = driver.findElements(subMenuResultItems);

        for (WebElement result : results) {

            if (result.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return true; // Ditemukan
            }
        }

        List<WebElement> headers = driver.findElements(By.cssSelector(".op-submenu--group-title"));
        for (WebElement header : headers) {
            if (header.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    private By getSubmenuButton(String menuName) {

        return By.xpath("//button[contains(@class, 'op-submenu--title') and contains(., '" + menuName + "')]");
    }

    private By getSubmenuList(String menuName) {

        return By.xpath("//button[contains(@class, 'op-submenu--title') and contains(., '" + menuName
                + "')]/following-sibling::ul");
    }

    private boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubmenuOpen(String menuName) {
        return isElementVisible(getSubmenuList(menuName));
    }

    public void expandSubmenu(String menuName) {
        By buttonLocator = getSubmenuButton(menuName);
        By listLocator = getSubmenuList(menuName);

        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonLocator));

        if (!isElementVisible(listLocator)) {
            btn.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(listLocator));
        }
    }

    public void collapseSubmenu(String menuName) {
        By buttonLocator = getSubmenuButton(menuName);
        By listLocator = getSubmenuList(menuName);

        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonLocator));

        if (isElementVisible(listLocator)) {
            btn.click();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(listLocator));
        }
    }

    public void clickSubmenuItem(String itemName) {

        String xpath = "//a[contains(@class, 'op-submenu--item-action')]//span[contains(normalize-space(.), '"
                + itemName + "')]";

        try {
            WebElement item = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            item.click();
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengklik sub-menu item: '" + itemName
                    + "'. Pastikan menu induknya (Favorite/Default) sudah terbuka.");
        }
    }

    public void clickBreadcrumb(String breadcrumbName) {
        String dynamicXpath = String.format(
                "//li[@data-test-selector='op-breadcrumbs--item']/a[contains(normalize-space(.), '%s')]",
                breadcrumbName);

        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));

            link.click();

            Delay.waitFor(1000);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Gagal klik Breadcrumb: '" + breadcrumbName + "'. Elemen tidak ditemukan atau tidak bisa diklik.",
                    e);
        }
    }

    public void toggleAllToolbarButtons() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(toolbarContainer));

        int buttonCount = driver.findElements(toolbarButtons).size();
        System.out.println("Ditemukan " + buttonCount + " tombol di toolbar.");

        for (int i = 0; i < buttonCount; i++) {

            List<WebElement> buttons = driver.findElements(toolbarButtons);
            WebElement btn = buttons.get(i);

            String btnName = btn.getAttribute("title");
            if (btnName == null || btnName.isEmpty()) {
                btnName = btn.getAttribute("aria-label");
            }
            if (btnName == null || btnName.isEmpty()) {
                btnName = "Button Index " + i;
            }

            System.out.println("--- Testing Button: " + btnName + " ---");

            if (btn.isEnabled() && btn.isDisplayed()) {

                try {
                    wait.until(ExpectedConditions.elementToBeClickable(btn));
                    btn.click();
                    System.out.println("   -> Klik 1 (Open)");
                    Delay.waitFor(1000);

                    btn.click();
                    System.out.println("   -> Klik 2 (Close)");
                    Delay.waitFor(1000);

                } catch (Exception e) {
                    System.out.println("   -> Gagal klik tombol ini: " + e.getMessage());
                }

            } else {
                System.out.println("   -> SKIP: Tombol disabled atau tersembunyi.");
            }
        }
    }

}
