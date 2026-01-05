package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class WorkPackagesPage extends BasePage {

    private By workPackagesSidebar = By.cssSelector("a[title='Work packages']");
    private By AllProjectsTab = By.id("projects-menu");

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
        clickWorkPackagesSideBar();
        wait.until(ExpectedConditions.elementToBeClickable(AllProjectsTab)).click();
        return this;
    }

    public WorkPackagesPage closeAllProjectsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(AllProjectsTab)).click();
        return this;
    }
}
