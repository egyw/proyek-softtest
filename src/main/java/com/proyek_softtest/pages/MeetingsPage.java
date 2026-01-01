package com.proyek_softtest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import com.proyek_softtest.base.BasePage;
import com.proyek_softtest.utils.Delay;
import java.util.List;

public class MeetingsPage extends BasePage {

    // Locator Utama (Berdasarkan Inspect Element Terbaru)
    private By filtersToggleButton = By.xpath("//button[contains(., 'Filters')]");
    private By addFilterSelect = By.id("add_filter_select");
    private By applyButton = By.cssSelector("input[type='submit'][value='Apply']");
    
    // Tab Navigation
    private By pastTab = By.xpath("//a[@title='Past meetings']");
    private By upcomingTab = By.xpath("//a[@title='Upcoming meetings']");

    // SIDEBAR: Sesuai Inspect Element (op-submenu--item-action)
    // Kita gunakan data-test-selector atau kombinasi class + text agar tidak tertukar dengan All Meetings
    private By recurringMeetingsLink = By.xpath("//a[contains(@class, 'op-submenu--item-action') and contains(., 'Recurring meetings')]");

    // Toggle "Part of a meeting series" (Berdasarkan Screenshot 2)
    private By toggleWrapper = By.cssSelector("opce-spot-switch[data-name='v-type']");
    private By toggleFakeSpan = By.cssSelector("opce-spot-switch[data-name='v-type'] .spot-switch--fake");

    // Filter Logic
    private By invitedUserOperator = By.xpath("//li[@data-filter-name='invited_user_id']//select[@id='operator']");

    public MeetingsPage(WebDriver driver) {
        super(driver);
    }

    public void openFiltersPanel() {
        if (!isElementDisplayed(addFilterSelect)) {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(filtersToggleButton));
            jsClick(btn);
            wait.until(ExpectedConditions.visibilityOfElementLocated(addFilterSelect));
        }
    }

    public void selectFilterIfNotFound(String filterName, String filterDataName) {
        openFiltersPanel();
        By activeFilterLocator = By.xpath("//li[@data-filter-name='" + filterDataName + "']");
        if (!isElementDisplayed(activeFilterLocator)) {
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(addFilterSelect));
            new Select(dropdown).selectByVisibleText(filterName);
            Delay.waitDefault();
        }
    }

    public void setInvitedUserToIsNotEmpty() {
        try {
            WebElement selectEl = wait.until(ExpectedConditions.visibilityOfElementLocated(invitedUserOperator));
            new Select(selectEl).selectByVisibleText("is not empty");
        } catch (Exception e) {
            jsClick(driver.findElement(By.xpath("//li[@data-filter-name='invited_user_id']//option[text()='is not empty']")));
        }
    }

    public void clickApply() {
        System.out.println("DEBUG: Klik Apply...");
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(applyButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
        Delay.waitDefault();
        jsClick(btn);
        Delay.waitDefault();
    }

    public void clickPast() {
        System.out.println("DEBUG: Klik tab Past...");
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(pastTab));
        jsClick(el);
        // Menunggu URL berubah (upcoming=false)
        wait.until(ExpectedConditions.urlContains("upcoming=false"));
        Delay.waitDefault();
    }

    public void goToRecurringMeetings() {
        System.out.println("DEBUG: Navigasi ke Recurring Meetings...");
        try {
            // Karena elemen berada di dalam <turbo-frame>, pastikan kita menunggu visibilitasnya
            // Menggunakan locator yang sudah diperbaiki (op-submenu--item-action)
            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(recurringMeetingsLink));
            jsClick(link);
            
            // Verifikasi bahwa URL sudah mengandung /recurring
            wait.until(ExpectedConditions.urlContains("/recurring"));
            System.out.println("DEBUG: Berhasil masuk ke halaman Recurring.");
        } catch (Exception e) {
            System.err.println("ERROR: Gagal klik sidebar. Mencoba klik berdasarkan data-test-selector...");
            // Fallback menggunakan data-test-selector dari inspect element
            WebElement fallback = driver.findElement(By.cssSelector("a[data-test-selector='op-submenu--item-action']"));
            jsClick(fallback);
        }
    }

    public void turnOffMeetingSeriesToggle() {
        System.out.println("DEBUG: Mengecek status toggle 'Part of a meeting series'...");
        try {
            WebElement wrapper = wait.until(ExpectedConditions.presenceOfElementLocated(toggleWrapper));
            
            // Berdasarkan screenshot inspect, atribut 'checked' ada pada opce-spot-switch
            String isChecked = wrapper.getAttribute("checked");
            
            if ("true".equals(isChecked)) {
                System.out.println("DEBUG: Status ON. Mematikan (OFF)...");
                jsClick(driver.findElement(toggleFakeSpan));
            } else {
                System.out.println("DEBUG: Status sudah OFF.");
            }
        } catch (Exception e) {
            System.err.println("DEBUG: Toggle tidak ditemukan.");
        }
        Delay.waitDefault();
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private boolean isElementDisplayed(By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}