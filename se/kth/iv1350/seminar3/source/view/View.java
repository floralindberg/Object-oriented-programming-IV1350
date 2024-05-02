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
        contr.enterItemIdentifier(111);
        System.out.println("add 1 item with ID 111");
        contr.showItemAddedToSale(111);
        contr.enterItemIdentifier(111);
        System.out.println("add 1 item with ID 111");
        contr.showItemAddedToSale(111);
        contr.enterItemIdentifier(333);
        System.out.println("add 1 item with ID 333");
        contr.showItemAddedToSale(333);
        double totalPrice = contr.endSale();
        System.out.println("Total cost ( incl VAT ):"+ String.format("%.2f",totalPrice));
        System.out.println("Customer pays:"+ 100);
        contr.pay(totalPrice,100);
        System.out.println("Told external inventory system to decrease inventory quantity");
        //contr.printReceipt(100);
        System.out.println("Change to give the customer:" + String.format("%.2f", contr.getChange(totalPrice, 100)));
        
    }

}