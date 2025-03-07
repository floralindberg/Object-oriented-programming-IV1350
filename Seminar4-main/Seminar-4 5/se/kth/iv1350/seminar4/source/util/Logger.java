package se.kth.iv1350.seminar4.source.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Logs exceptions to a file.
 */


public class Logger {

    /**
     * Creates a new instance of the Logger.
     * @throws IOException if the file cannot be created.
     */

    
    public static final String LOG_FILE_NAME = "purchase_errors.txt";
    private PrintWriter printwriter;

    public Logger () throws IOException {
        printwriter = new PrintWriter (new FileWriter(LOG_FILE_NAME), true);

    }

    /**
     * Logs an exception to the file.
     * @param exception The exception to be logged.
     */

    public void logExceptionsforSale (Exception exception) {

        printwriter.println(timeOfException());
        printwriter.print("Exception was thrown: ");
        printwriter.println(exception.getMessage());
        printwriter.println();
    }

    /**
     * Gets the time of the exception.
     * @return the time of the exception
     */

    public String timeOfException () {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
    
}
