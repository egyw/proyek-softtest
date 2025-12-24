package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;

public class NewsPage extends BasePage {
    private By homeBreadCrumbLink = By.linkText("safe.openproject.com");

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    // ╔════════════════════════════════════════════════════════╗
    // ║ NEWS PAGE ACTIONS                                      ║
    // ╚════════════════════════════════════════════════════════╝

    public NewsPage clickHomeBreadCrumbLink() {
        wait.until(ExpectedConditions.elementToBeClickable(homeBreadCrumbLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickOpenProjectLink() {
        By openProjectLink = By.linkText("safe.openproject.com");
        wait.until(ExpectedConditions.elementToBeClickable(openProjectLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickDemoProjectLink() {
        By demoProjectLink = By.linkText("Demo project");
        wait.until(ExpectedConditions.elementToBeClickable(demoProjectLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/projects/demo-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickTestSyntaxHighlightingLink() {
        By clickTestSyntaxHighlightingLink = By.linkText("Test syntax highlighting");
        wait.until(ExpectedConditions.elementToBeClickable(clickTestSyntaxHighlightingLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/news/4-test-syntax-highlighting"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickDemoAdminLink() {
        By demoAdminLink = By.linkText("Demo admin");
        wait.until(ExpectedConditions.elementToBeClickable(demoAdminLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/users/10"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickActualitesPageLink() {
        By actualitesPageLink = By.linkText(
                "Actualités - [Acteurspublics.fr] Réforme de la haute fonction publique : le cas de l’IGF interroge toujours");
        wait.until(ExpectedConditions.elementToBeClickable(actualitesPageLink)).click();
        wait.until(ExpectedConditions.urlToBe(
                "https://safe.openproject.com/news/3-actualites-acteurspublics-fr-reforme-de-la-haute-fonction-publique-le-cas-de-l-igf-interroge-toujours"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickScrumProjectLink() {
        By scrumProjectLink = By.linkText("Scrum project");
        wait.until(ExpectedConditions.elementToBeClickable(scrumProjectLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/projects/your-scrum-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickWelcomeToYourScrumDemoProjectLink() {
        By welcomeToYourScrumDemoProjectLink = By.linkText("Welcome to your Scrum demo project");
        wait.until(ExpectedConditions.elementToBeClickable(welcomeToYourScrumDemoProjectLink)).click();
        wait.until(
                ExpectedConditions.urlToBe("https://safe.openproject.com/news/2-welcome-to-your-scrum-demo-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickWelcomeToYourDemoProjectLink() {
        By welcomeToYourDemoProjectLink = By.linkText("Welcome to your demo project");
        wait.until(ExpectedConditions.elementToBeClickable(welcomeToYourDemoProjectLink)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/news/1-welcome-to-your-demo-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickPagination50() {
        By pagination50 = By.linkText("50");
        wait.until(ExpectedConditions.elementToBeClickable(pagination50)).click();
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickPagination100() {
        By pagination100 = By.linkText("100");
        wait.until(ExpectedConditions.elementToBeClickable(pagination100)).click();
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickPagination200() {
        By pagination200 = By.linkText("200");
        wait.until(ExpectedConditions.elementToBeClickable(pagination200)).click();
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickDemoProjectLinkSecond() {
        By demoProjectLinkSecond = By.xpath("(//a[text()='Demo project'])[2]");
        wait.until(ExpectedConditions.elementToBeClickable(demoProjectLinkSecond)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/projects/demo-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickDemoPorjectLinkThird() {
        By demoProjectLinkThird = By.xpath("(//a[text()='Demo project'])[3]");
        wait.until(ExpectedConditions.elementToBeClickable(demoProjectLinkThird)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/projects/demo-project"));
        Delay.waitDefault();
        return this;
    }

    public NewsPage clickDemoAdminLinkSecond() {
        By demoAdminLinkSecond = By.xpath("(//a[text()='Demo admin'])[2]");
        wait.until(ExpectedConditions.elementToBeClickable(demoAdminLinkSecond)).click();
        wait.until(ExpectedConditions.urlToBe("https://safe.openproject.com/users/10"));
        Delay.waitDefault();
        return this;
    }

}
