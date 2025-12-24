package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class NewsPage extends BasePage {
    private By homeBreadCrumbLink = By.linkText("safe.openproject.com");
    private By demoProjectLink = By.linkText("Demo project");
    private By clickTestSyntaxHighlightingLink = By.linkText("Test syntax highlighting");
    private By demoAdminLink = By.linkText("Demo admin");
    private By actualitesPageLink = By.linkText("Actualités - [Acteurspublics.fr] Réforme de la haute fonction publique : le cas de l’IGF interroge toujours");
    private By scrumProjectLink = By.linkText("Scrum project");
    private By welcomeToYourScrumDemoProjectLink = By.linkText("Welcome to your Scrum demo project");
    private By welcomeToYourDemoProjectLink = By.linkText("Welcome to your demo project");
    private By pagination50 = By.linkText("50");
    private By pagination100 = By.linkText("100");
    private By pagination200 = By.linkText("200");
    private By demoProjectLinkSecond = By.xpath("(//a[text()='Demo project'])[2]");
    private By demoProjectLinkThird = By.xpath("(//a[text()='Demo project'])[3]");
    private By demoAdminLinkSecond = By.xpath("(//a[text()='Demo admin'])[2]");

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║                   NEWS PAGE ACTIONS                    ║
    // ╚════════════════════════════════════════════════════════╝

    public NewsPage clickHomeBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(homeBreadCrumbLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickDemoProjectLink() {
        wait.until(ExpectedConditions.elementToBeClickable(demoProjectLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/projects/demo-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickTestSyntaxHighlightingLink() {
        wait.until(ExpectedConditions.elementToBeClickable(clickTestSyntaxHighlightingLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/news/4-test-syntax-highlighting"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickDemoAdminLink() {
        wait.until(ExpectedConditions.elementToBeClickable(demoAdminLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/users/10"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickActualitesPageLink() {
        wait.until(ExpectedConditions.elementToBeClickable(actualitesPageLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/news/3-actualites-acteurspublics-fr-reforme-de-la-haute-fonction-publique-le-cas-de-l-igf-interroge-toujours"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickScrumProjectLink() {
        wait.until(ExpectedConditions.elementToBeClickable(scrumProjectLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/projects/your-scrum-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickWelcomeToYourScrumDemoProjectLink() {
        wait.until(ExpectedConditions.elementToBeClickable(welcomeToYourScrumDemoProjectLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/news/2-welcome-to-your-scrum-demo-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickWelcomeToYourDemoProjectLink() {
        wait.until(ExpectedConditions.elementToBeClickable(welcomeToYourDemoProjectLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/news/1-welcome-to-your-demo-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickPagination50() {
        wait.until(ExpectedConditions.elementToBeClickable(pagination50)).click();
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickPagination100() {
        wait.until(ExpectedConditions.elementToBeClickable(pagination100)).click();
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickPagination200() {
        wait.until(ExpectedConditions.elementToBeClickable(pagination200)).click();
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickDemoProjectLinkSecond() {
        wait.until(ExpectedConditions.elementToBeClickable(demoProjectLinkSecond)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/projects/demo-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickDemoProjectLinkThird() {
        wait.until(ExpectedConditions.elementToBeClickable(demoProjectLinkThird)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/projects/demo-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickDemoAdminLinkSecond() {
        wait.until(ExpectedConditions.elementToBeClickable(demoAdminLinkSecond)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/users/10"));
        Delay.waitDefault();
        return this;
    }

}
