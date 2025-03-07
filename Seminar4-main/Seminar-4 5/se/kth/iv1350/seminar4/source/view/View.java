package se.kth.iv1350.seminar4.source.view;

import java.io.IOException;

import se.kth.iv1350.seminar4.source.controller.Controller;
import se.kth.iv1350.seminar4.source.integration.InventoryFailureException;
import se.kth.iv1350.seminar4.source.integration.ItemNotFoundInInventoryException;
import se.kth.iv1350.seminar4.source.util.*;
import se.kth.iv1350.seminar4.source.model.NotEligibleForDiscountException;



public class View {

	private Controller contr;
    private TotalRevenueView totalRevenueView;
    private TotalRevenueFileOutput totalRevenueFileOutput;
    private Logger logger;

 /**
  * Creates a new instance of the View class.
  * Initializes the logger, total revenue view, and total revenue file output.
  * Adds observers to the controller for total revenue updates.
  * @param contr The controller instance used in view 
  * @throws IOException If an I/O error occurs while initializing the logger or the file output. 
  */

	public View (Controller contr) throws IOException {
		this.contr = contr;
        this.logger = new Logger();
        this.totalRevenueView = new TotalRevenueView();
        this.totalRevenueFileOutput = TotalRevenueFileOutput.getTotalRevenueFileOutput();
        
        contr.addTotalRevenueObserver(totalRevenueView);
        contr.addTotalRevenueObserver(totalRevenueFileOutput);
        contr.addTotalRevenueObservers();

	}

    /**
     * A fake execution that calls all operations in the controller to be able to test the program
     */
    public void runFakeExecution() {
        contr.startSale();
        System.out.println("A new sale has been started.");

        System.out.println("add 1 item with ID 111");
        try{
            System.out.println(contr.enterItemIdentifier(111) + "\n");
        } catch (ItemNotFoundInInventoryException e) {
            writeToLog(e);
        } catch (InventoryFailureException exc) {
            writingtolog(exc);
        }
        System.out.println(contr.showTotalPriceAndVAT());



        System.out.println("add 1 item with ID 111");
        try{
            System.out.println(contr.enterItemIdentifier(111) + "\n");
        } catch (ItemNotFoundInInventoryException e) {
            writeToLog(e);
        } catch (InventoryFailureException exc) {
            writingtolog(exc);
        }
        System.out.println(contr.showTotalPriceAndVAT());


        System.out.println("add 1 item with ID 333");
        try {
        System.out.println(contr.enterItemIdentifier(333) + "\n");
        
        } catch (ItemNotFoundInInventoryException e) {
            writeToLog(e);
        } catch (InventoryFailureException exc) {
            writingtolog(exc);
        }
        System.out.println(contr.showTotalPriceAndVAT());

        double totalPrice = contr.endSale();
        
        System.out.println("Customer wants discount");
        try {
            totalPrice = contr.checkDiscount(19201110);
            System.err.println("Discount has been applied");

        } catch (NotEligibleForDiscountException disExc) {
            
            System.out.print(disExc.getMessage() + "\n");
            
}
        System.out.println("End Sale: ");
        System.out.println("Total cost ( incl VAT ): "+ String.format("%.2f",totalPrice) + " SEK");
        System.out.println("Customer pays: "+ 100 + " SEK");
        contr.pay(totalPrice,100);
        System.out.println("Updated accountingSystem");
        System.out.println("Told external inventory system to decrease inventory quantity");
        
        System.out.println("Change to give the customer:" + String.format("%.2f", contr.getChange(totalPrice, 100)) + " SEK");

        System.out.println(" ");

        contr.startSale();
        System.out.println("A new sale has been started.");

        System.out.println("add 1 item with ID 222");
        try {
            System.out.println(contr.enterItemIdentifier(123) + "\n");
        } catch (ItemNotFoundInInventoryException e) {
            writeToLog(e);
        } catch (InventoryFailureException exc) {
            writingtolog(exc);
        }
            System.out.println(contr.showTotalPriceAndVAT());



            System.out.println("add 1 item with ID 222");
            try {
                System.out.println(contr.enterItemIdentifier(222) + "\n");
            } catch (ItemNotFoundInInventoryException e) {
                writeToLog(e);
            } catch (InventoryFailureException exc) {
                writingtolog(exc);
            }
                System.out.println(contr.showTotalPriceAndVAT());


                System.out.println("add 1 item with ID 111");
                try{
                    System.out.println(contr.enterItemIdentifier(121) + "\n");
                } catch (ItemNotFoundInInventoryException e) {
                    writeToLog(e);
                } catch (InventoryFailureException exc) {
                    writingtolog(exc);
                }
                System.out.println(contr.showTotalPriceAndVAT());
        
        
        totalPrice = contr.endSale();
        
        System.out.println("Customer wants discount");
        try {
            totalPrice = contr.checkDiscount(19721110);
            System.err.println("Discount has been applied");
            
        } catch (NotEligibleForDiscountException disExc) {
        
            System.out.print(disExc.getMessage() + "\n");
            
}
        System.out.println("End Sale: ");
        System.out.println("Total cost ( incl VAT ): "+ String.format("%.2f",totalPrice) + " SEK");
        System.out.println("Customer pays: "+ 100 + " SEK");
        contr.pay(totalPrice,100);
        System.out.println("Updated accountingSystem");
        System.out.println("Told external inventory system to decrease inventory quantity");
        
        System.out.println("Change to give the customer:" + String.format("%.2f", contr.getChange(totalPrice, 100)) + " SEK");

        System.out.println(" ");

        /**
         * Clearing the inventory to check exception if inventory system is "offline".
         */

        contr.startSale();
        contr.clearInventory();

        System.out.println("A new sale has been started.");

        System.out.println("add 1 item with ID 111");
        try{
            System.out.println(contr.enterItemIdentifier(111) + "\n");
        } catch (ItemNotFoundInInventoryException e) {
            writeToLog(e);
        } catch (InventoryFailureException exc) {
            writingtolog(exc);
        }
        System.out.println(contr.showTotalPriceAndVAT());


        System.out.println("add 1 item with ID 111");
        try{
            System.out.println(contr.enterItemIdentifier(111) + "\n");
        } catch (ItemNotFoundInInventoryException e) {
            writeToLog(e);
        } catch (InventoryFailureException exc) {
            writingtolog(exc);
        }
        System.out.println(contr.showTotalPriceAndVAT());


        System.out.println("add 1 item with ID 333");
        try {
        System.out.println(contr.enterItemIdentifier(333) + "\n");
        
        } catch (ItemNotFoundInInventoryException e) {
            writeToLog(e);
        } catch (InventoryFailureException exc) {
            writingtolog(exc);
        }
        System.out.println(contr.showTotalPriceAndVAT());

        totalPrice = contr.endSale();
        
        System.out.println("End Sale: ");
        System.out.println("Total cost ( incl VAT ): "+ String.format("%.2f",totalPrice) + " SEK");
        System.out.println("Customer pays: "+ 100 + " SEK");
        contr.pay(totalPrice,100);
        System.out.println("Updated accountingSystem");
        System.out.println("Told external inventory system to decrease inventory quantity");
        
        System.out.println("Change to give the customer:" + String.format("%.2f", contr.getChange(totalPrice, 100)) + " SEK");

        System.out.println(" ");
    }

    private void writeToLog (Exception e) {
        System.out.printf("ItemNotFoundInInventoryException: %s%n",e.getMessage());
        logger.logExceptionsforSale(e);
    }

    private void writingtolog (Exception exc) {
        System.out.printf("InventoryFailureException: %s%n",exc.getMessage());
        logger.logExceptionsforSale(exc);
    }

}
