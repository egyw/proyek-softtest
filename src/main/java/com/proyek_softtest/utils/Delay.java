package com.proyek_softtest.utils;

import com.proyek_softtest.config.TestConfig;

/**
 * Utility class untuk delay/wait dalam testing
 * Delay hanya akan dijalankan jika delay.enabled=true di test-config.properties
 */
public class Delay {
    public static void waitFor(long milliseconds) {
        if (!TestConfig.isDelayEnabled()) {
            return; // Skip delay jika disabled di config
        }
        
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }
    
    public static void waitForSeconds(int seconds) {
        waitFor(seconds * 1000L);
    }
    
    public static void waitDefault() {
        waitFor(TestConfig.getDefaultDelayMs());
    }
    
    public static boolean isEnabled() {
        return TestConfig.isDelayEnabled();
    }
}
