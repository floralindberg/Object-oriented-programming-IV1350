package se.kth.iv1350.seminar4.source.startup;

import java.io.IOException;

import se.kth.iv1350.seminar4.source.controller.Controller;
import se.kth.iv1350.seminar4.source.view.View;

/**
 * Starts the whole system
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
		Controller contr = new Controller();
		View view = new View(contr);
		view.runFakeExecution();
	}
	
}

