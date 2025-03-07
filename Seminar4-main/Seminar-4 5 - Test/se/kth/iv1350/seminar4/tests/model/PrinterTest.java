package se.kth.iv1350.seminar4.tests.model;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import se.kth.iv1350.seminar4.source.controller.Controller;
import se.kth.iv1350.seminar4.source.integration.Printer;

public class PrinterTest {
    private Controller contr;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Printer printer;

    @BeforeEach
        public void setUp() {
            this.contr = new Controller();
            this.contr.startSale();
        }

    @After
    public void tearDown() {
        contr = null;
    }

    @Before
        public void settingUp() {
            this.contr = new Controller();
            this.contr.startSale();
            System.setOut(new PrintStream(outputStreamCaptor));
        }


    @Test
    public void testPrintReceipt() {
            
            String time = "2024-05-02 00:00:00";
            String info = "Apple 1 x 50.00: 50.00 SEK\n\nTotal : 50.00 SEK\nVAT : 12.50 SEK\n\nCash : 100.00 SEK\nChange : 50.00 SEK\n";
            double amountInRegister = 150.0;
    
            this.printer = Printer.getInstance();
    
            printer.print(time, info, amountInRegister);
            String printedOutput = outputStreamCaptor.toString();
    

            String expectedOutput = "Time of sale:2024-05-02 00:00:00\n" +
                                    "Apple 1 x 50.00: 50.00 SEK\n\n" +
                                    "Total : 50.00 SEK\nVAT : 12.50 SEK\n\n" +
                                    "Cash : 100.00 SEK\nChange : 50.00 SEK\n" +
                                    "\nAmount in Register:150,00\n";
    

            assertEquals(expectedOutput, printedOutput, "Unexpected output");
        }
}
