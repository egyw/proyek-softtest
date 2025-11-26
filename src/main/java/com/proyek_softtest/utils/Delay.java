package com.proyek_softtest.utils;

/**
 * Utility class untuk delay/wait dalam testing
 */
public class Delay {
    
    /**
     * Menunggu dalam milidetik
     * @param milliseconds waktu tunggu dalam milidetik
     */
    public static void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }
    
    /**
     * Menunggu dalam detik
     * @param seconds waktu tunggu dalam detik
     */
    public static void waitForSeconds(int seconds) {
        waitFor(seconds * 1000L);
    }
}
