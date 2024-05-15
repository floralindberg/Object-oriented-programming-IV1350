package se.kth.iv1350.seminar4.source.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Logger {

    
    public static final String LOG_FILE_NAME = "purchase_errors.txt";
    private PrintWriter printwriter;

    public Logger () throws IOException {
        printwriter = new PrintWriter (new FileWriter(LOG_FILE_NAME), true);

    }

    public void logExceptionsforSale (Exception exception) {

        printwriter.println(timeOfException());
        printwriter.print("Exception was thrown: ");
        printwriter.println(exception.getMessage());
        printwriter.println();
    }


    public String timeOfException () {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
    
}
