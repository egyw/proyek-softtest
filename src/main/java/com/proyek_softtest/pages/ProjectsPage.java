package com.proyek_softtest.pages;

import org.openqa.selenium.By;

public class ProjectsPage {
    // sidebar
    
    private By projectsButtonInSidebar = By.cssSelector("a.main-menu--parent-node[href*='/projects']");
    private By searchBarInSidebar = By.cssSelector("input[data-test-selector='op-submenu--search-input']");
    private By projectStatusToggle = By.xpath("//button[normalize-space()='Project status']");
    private By onTrackFilter = By.xpath("//a[@data-test-selector='op-submenu--item-action' and .//span[normalize-space()='On track']]");
    private By offTrackFilter = By.xpath("//a[@data-test-selector='op-submenu--item-action' and .//span[normalize-space()='Off track']]");
    private By atRiskFilter = By.xpath("//a[@data-test-selector='op-submenu--item-action' and .//span[normalize-space()='At risk']]");

    // main content
    private By homeBreadCrumbLink = By.linkText("safe.openproject.com");
    private By projectsBreadCrumbLink = By.cssSelector("a.Link[href='/projects']");

    // ini untuk verifikasi header content pada halaman projects
    private By headerContent = By.cssSelector("h2[data-test-selector='project-query-name']");

    // more menu button 
    private By moreMenuButton = By.cssSelector("button[data-test-selector='project-more-dropdown-menu']");
    private By openAsGantt = By.id("projects-index-open-as-gantt");
    private By overallActivity = By.cssSelector("a[href='/activities']");
    private By exportButton = By.cssSelector("a[href='/projects/export_list_modal']");
    private By configureView = By.cssSelector("a[href='/project_queries/configure_view_modal']");
    private By zenMode = By.cssSelector("button[data-controller='projects-zen-mode']");

    private By projectSearchbar = By.id("name_and_identifier");
    private By filtersButtonOpen =By.cssSelector("button[data-filter--filters-form-target='filterFormToggle'][aria-pressed='true']");
    private By filtersButtonClosed =By.cssSelector("button[data-filter--filters-form-target='filterFormToggle'][aria-pressed='false']");
}
