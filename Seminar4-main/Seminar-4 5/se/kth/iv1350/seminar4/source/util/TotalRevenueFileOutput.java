package se.kth.iv1350.seminar4.source.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.seminar4.source.integration.TotalRevenueObserver;

/**
 * Logs the total income to a file.
 */

public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private static final String LOG_FILE_NAME = "totalRevenue.txt";
    private static TotalRevenueFileOutput instance = new TotalRevenueFileOutput();
    private PrintWriter logFile;

    /**
     * Gets the instance of the TotalRevenueFileOutput.
     * @return The instance of the TotalRevenueFileOutput.
     */

    public static synchronized TotalRevenueFileOutput getTotalRevenueFileOutput() {
        if (instance == null) {
            instance = new TotalRevenueFileOutput();
        }
        return instance;
    }

    /**
     * Creates an instance of the TotalRevenueFileOutput.
     */

    private TotalRevenueFileOutput() {
        try {
            logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
        } catch (IOException ex) {
            System.out.println("Could not create logger.");
            ex.printStackTrace();
        }
    }

    /**
     * Updates the total income.
     * @param totalRevenue The total income.
     */

    @Override
    public void updateTotalRevenue(double totalRevenue) {
        logFile.println("Total Revenue: " + String.format("%.2f", totalRevenue));
    }
}