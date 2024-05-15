package se.kth.iv1350.seminar3.source.startup;

import se.kth.iv1350.seminar3.source.controller.Controller;
import se.kth.iv1350.seminar3.source.view.View;

/**
 * Starts the whole system
 */
public class Main {
    
    public static void main(String[] args) {
		//Printer printer = new Printer();
		//ExternalSystemCreator createdSystem = new externalSystemCreator();
		Controller contr = new Controller();
		View view = new View(contr);
		view.runFakeExecution();
	}
	
}

