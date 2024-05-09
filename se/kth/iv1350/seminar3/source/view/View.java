package se.kth.iv1350.seminar3.source.view;

import se.kth.iv1350.seminar3.source.controller.Controller;


public class View {

	private Controller contr;
/**
 * Constructor for view
 * @param contr is representing the controller class
 */
	public View (Controller contr) {
		this.contr = contr;
	}

    /**
     * A fake execution that calls all operations in the controller to be able to test the program
     */
    public void runFakeExecution() {
        contr.startSale();
        System.out.println("A new sale has been started.");

        System.out.println("add 1 item with ID 111");
        System.out.println(contr.enterItemIdentifier(111) + "\n");
        System.out.println(contr.showTotalPriceAndVAT());



        System.out.println("add 1 item with ID 111");
        System.out.println(contr.enterItemIdentifier(111) + "\n");
        System.out.println(contr.showTotalPriceAndVAT());


        System.out.println("add 1 item with ID 333");
        System.out.println(contr.enterItemIdentifier(333) + "\n");
        System.out.println(contr.showTotalPriceAndVAT());
        
        
        double totalPrice = contr.endSale();
        System.out.println("End Sale: ");
        System.out.println("Total cost ( incl VAT ): "+ String.format("%.2f",totalPrice) + " SEK");
        System.out.println("Customer pays: "+ 100 + " SEK");
        contr.pay(totalPrice,100);
        System.out.println("Told external inventory system to decrease inventory quantity");
        //contr.printReceipt(100);
        System.out.println("Change to give the customer:" + String.format("%.2f", contr.getChange(totalPrice, 100)) + " SEK");
        
    }

}
