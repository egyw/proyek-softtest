package com.proyek_softtest.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.proyek_softtest.config.TestConfig;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Report Manager untuk Extent Reports
 */
public class ReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    /**
     * Initialize Extent Reports
     */
    public static void initReport() {
        if (extent != null) {
            return;
        }
        
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = TestConfig.getReportFolder() + File.separator + 
                           "TestReport_" + timestamp + ".html";
        
        // Create report folder if not exists
        File reportFolder = new File(TestConfig.getReportFolder());
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        }
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("OpenProject Automation Test Report");
        sparkReporter.config().setReportName(TestConfig.getReportName());
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "OpenProject");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("Browser", TestConfig.getBrowser());
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        
        System.out.println("📊 Report initialized: " + reportPath);
    }
    
    /**
     * Create test dalam report
     */
    public static void createTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
    }
    
    /**
     * Get current test
     */
    public static ExtentTest getTest() {
        return test.get();
    }
    
    /**
     * Flush report (write to file)
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println("✅ Report generated successfully");
        }
    }
}
