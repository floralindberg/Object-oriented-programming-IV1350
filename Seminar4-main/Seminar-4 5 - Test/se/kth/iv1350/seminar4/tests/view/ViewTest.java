package se.kth.iv1350.seminar4.tests.view;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import se.kth.iv1350.seminar4.source.controller.Controller;
import se.kth.iv1350.seminar4.source.view.View;

public class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() throws IOException{
        Controller contr = new Controller();
        instanceToTest = new View(contr);

        printoutBuffer = new ByteArrayOutputStream(0);
        PrintStream inMemSysOut = new PrintStream(printoutBuffer); 
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
        
    }

    @After
    public void tearDown() {
        instanceToTest = null;

        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Before
    public void settingUp() throws IOException {
        Controller contr = new Controller();
        instanceToTest = new View(contr);

        printoutBuffer = new ByteArrayOutputStream(0);
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut); }


    @Test
    public void testRunFakeExecution() {
        
        instanceToTest.runFakeExecution();
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        assertTrue(printout.contains(expectedOutput), "UI did not start correctly. ");
    }
    
}