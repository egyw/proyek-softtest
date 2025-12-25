package com.proyek_softtest.utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Helper class for Robot operations (native OS interactions)
 */
public class RobotHelper {
    
    /**
     * Close file dialog or any window using ESC key
     * Useful for closing native OS dialogs that Selenium cannot handle
     */
    public static void closeDialogWithEsc() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            Delay.waitFor(500); // Wait untuk dialog close
        } catch (Exception e) {
            System.err.println("Failed to close dialog with ESC: " + e.getMessage());
        }
    }
    
    /**
     * Close file dialog with ESC and optional custom delay
     * 
     * @param delayAfterClose - delay in milliseconds after pressing ESC
     */
    public static void closeDialogWithEsc(int delayAfterClose) {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            Delay.waitFor(delayAfterClose);
        } catch (Exception e) {
            System.err.println("Failed to close dialog with ESC: " + e.getMessage());
        }
    }
    
    /**
     * Press ENTER key (useful for confirming dialogs)
     */
    public static void pressEnter() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Delay.waitFor(500);
        } catch (Exception e) {
            System.err.println("Failed to press ENTER: " + e.getMessage());
        }
    }
    
    /**
     * Press any key
     * 
     * @param keyCode - KeyEvent constant (e.g., KeyEvent.VK_ESCAPE, KeyEvent.VK_ENTER)
     */
    public static void pressKey(int keyCode) {
        try {
            Robot robot = new Robot();
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            Delay.waitFor(500);
        } catch (Exception e) {
            System.err.println("Failed to press key " + keyCode + ": " + e.getMessage());
        }
    }
}
