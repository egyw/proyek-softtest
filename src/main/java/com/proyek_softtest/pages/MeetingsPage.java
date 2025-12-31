package com.proyek_softtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;
import java.util.List;

public class MeetingsPage extends BasePage {

    // 1. Tombol Toolbar Filter
    private By filtersToggleButton = By.xpath("//button[@title='Filter' or contains(., 'Filter')]");
    
    // 2. Label 'Invited user'
    private By invitedUserLabel = By.xpath("//label[contains(text(), 'Invited user')]");

    // 3. Dropdown Operator (STRATEGI BARU: Common Ancestor / Row Wrapper)
    // Penjelasan: Cari elemen pembungkus (ancestor) yang punya class 'filter' DAN memiliki label 'Invited user'.
    // Lalu ambil ng-select PERTAMA di dalamnya. Ini pasti Operator.
    private By invitedUserOperatorDropdown = By.xpath("(//*[contains(@class, 'filter')][.//label[contains(text(), 'Invited user')]]//ng-select)[1]");

    // 4. Panel Opsi Dropdown (Muncul setelah diklik)
    private By dropdownPanel = By.className("ng-dropdown-panel");
    
    // 5. Opsi "is empty"
    private By optionIsEmpty = By.xpath("//div[contains(@class, 'ng-option')][contains(., 'is empty')]");

    // 6. Dropdown Add Filter (Please select)
    private By addFilterDropdown = By.xpath("//ng-select[contains(., 'Please select')]");
    
    // 7. Tombol Apply
    private By applyButton = By.cssSelector("button[class*='button--save'], button[class*='button--highlight']");

    public MeetingsPage(WebDriver driver) {
        super(driver);
    }

    // --- ACTIONS ---

    public MeetingsPage openFiltersPanel() {
        if (isElementVisible(invitedUserLabel)) {
            System.out.println("DEBUG: Panel SUDAH terbuka.");
            return this;
        }

        System.out.println("DEBUG: Mengklik tombol Filter...");
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(filtersToggleButton));
            btn.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(invitedUserLabel));
        } catch (Exception e) {
            WebElement btn = driver.findElement(filtersToggleButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        Delay.waitDefault(); 
        return this;
    }

    public MeetingsPage changeInvitedUserOperatorToIsEmpty() {
        openFiltersPanel();
        System.out.println("DEBUG: Mencari Dropdown Operator (Dropdown Pertama di baris 'Invited user')...");
        
        try {
            // 1. Temukan Dropdown Operator
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(invitedUserOperatorDropdown));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
            
            // 2. Klik Dropdown
            // Kita gunakan JS Click untuk memastikan elemen terklik meskipun ada overlay
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
            System.out.println("DEBUG: Dropdown Operator diklik. Menunggu panel opsi...");
            
            // 3. Tunggu Panel Muncul & Klik 'is empty'
            // Tunggu panel muncul
            wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownPanel));
            
            // Cari dan klik opsi
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionIsEmpty));
            option.click();
            
            System.out.println("DEBUG: Berhasil KLIK opsi 'is empty'.");
            
        } catch (Exception e) {
            System.out.println("ERROR: Gagal memilih operator. " + e.getMessage());
            // Fallback: Coba klik elemen berdasarkan teks "is (OR)" jika locator di atas gagal
            try {
                System.out.println("DEBUG: Mencoba locator fallback...");
                WebElement fallback = driver.findElement(By.xpath("//ng-select[contains(., 'is (OR)')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fallback);
                wait.until(ExpectedConditions.elementToBeClickable(optionIsEmpty)).click();
            } catch (Exception ex) {
                System.out.println("ERROR: Fallback juga gagal.");
                throw ex;
            }
        }
        
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage addProjectFilter() {
        System.out.println("DEBUG: Menambah Filter Project...");
        try {
            // 1. Temukan Dropdown Add Filter
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(addFilterDropdown));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
            
            // 2. Klik Dropdown
            dropdown.click(); 
            Delay.waitDefault();

            // 3. Pilih "Project" via Keyboard
            Actions actions = new Actions(driver);
            actions.sendKeys("Project");
            Delay.waitDefault();
            actions.sendKeys(Keys.ENTER).perform();
            
            System.out.println("DEBUG: Filter 'Project' ditambahkan.");

        } catch (Exception e) {
            System.out.println("ERROR: Gagal menambah filter Project. " + e.getMessage());
            throw e;
        }
        Delay.waitDefault();
        return this;
    }

    public MeetingsPage clickApply() {
        System.out.println("DEBUG: Klik Apply...");
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(applyButton));
            btn.click();
        } catch (Exception e) {
            WebElement btn = driver.findElement(By.xpath("//button[contains(text(), 'Apply')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        return this;
    }
    
    private boolean isElementVisible(By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}