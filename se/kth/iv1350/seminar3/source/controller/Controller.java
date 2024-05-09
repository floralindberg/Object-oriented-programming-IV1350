package se.kth.iv1350.seminar3.source.controller;

import se.kth.iv1350.seminar3.source.integration.ExternalAccountingSystem;
import se.kth.iv1350.seminar3.source.integration.ExternalInventorySystem;
import se.kth.iv1350.seminar3.source.integration.ExternalSystemCreator;
import se.kth.iv1350.seminar3.source.integration.Item;
import se.kth.iv1350.seminar3.source.integration.Printer;
import se.kth.iv1350.seminar3.source.model.Payment;
import se.kth.iv1350.seminar3.source.model.Sale;
import se.kth.iv1350.seminar3.source.integration.ItemDTO;

/**
 * The main controller class that handles the flow of the application.
 */

public class Controller {
    private Sale sale;
    private ExternalSystemCreator externalSystemCreator;
	private Payment payment;
    private ExternalInventorySystem externalInventorySystem;
    private ExternalAccountingSystem externalAccountingSystem;
    private Item item;
    private Printer printer;

    /**
     * Constructor for the Controller class.
     * Initializes the external systems and the printer.
     */

    public Controller() {
		this.externalSystemCreator = new ExternalSystemCreator();
        this.externalInventorySystem = externalSystemCreator.getExternalInventorySystem();
        this.externalAccountingSystem = externalSystemCreator.getExternalAccountingSystem();
        this.printer = new Printer();

	}

    /**
     * Starts a new sale.
     * This method have to be called before anything is registered in the sale.
     */
    public void startSale() {
        this.sale = new Sale();
    }

    /*
    * Function to get the current sale
    * @return the current sale
    */
    public Sale getSale() {
        return this.sale;
    }

    /**
     * Checks if the entered item exists in the inventory. 
     * If it does, the item information can be found.
     * @param codeOfItem the information sent in to check if item exists i inventory. 
     */
    public ItemDTO enterItemIdentifier(int codeOfItem){
		boolean checkIfItemExists = externalInventorySystem.verifyItemByCode(codeOfItem);

        Item item = externalInventorySystem.getItemCopyFromInventory(codeOfItem);

        if (checkIfItemExists){
            sale.registerAllItems(item);
        }

        return item.getItemDTO(item);
	}

    /**
     * Gets all the information about an item and shows the item information
     * @param codeOfItem the sent in information about the product to find whole information.
     */
    public String showTotalPriceAndVAT(){

        return sale.getTotalPriceAndVAT();
    
    }

    
    /**
     * Starting a new payment.
     * @param totalAmount is the total amount with VAT that customer needs to pay.
     * @param paidAmount is the amount paid by customer.
     * Also calls for the method that is supposed to update the inventory system with the bought items.
     */
	public void pay(double totalAmount, double paidAmount) {
		this.payment = new Payment(totalAmount,paidAmount);
        double amountInRegister = payment.addToAccounting(totalAmount, paidAmount, externalAccountingSystem.getTotalAmountInRegister());
        externalInventorySystem.decreaseInventoryQuantity(sale.getAllItems());
        externalAccountingSystem.updateAccountingSystem(amountInRegister);
        printReceipt(paidAmount, amountInRegister);
        
	}

    /**
     * Method that calls for the receipt to be printed.
     * @param paidAmount is sent to printer with other information about the purchase.
     */
    public void printReceipt(double paidAmount, double amountInRegister){
        sale.getReceipt();
        printer.print(sale.getTimeOfSale(),sale.getReceipt().receiptInfo(externalAccountingSystem.getTotalAmountInRegister(), paidAmount), amountInRegister);
    }

    /**
     * Ends the sale by cashier when all items are scanned.
     * @return the total price with VAT.
     */
	public double endSale(){
        //externalInventorySystem.updateExternalInventorySystem(item, codeOfItem);
        return sale.calculateTotalPrice();
	}

    /**
     * Getter function for the change
     * @param totalAmount is the total amount with VAT
     * @param paidAmount is the amount paid by customer
     * @return the calculated amount of change back to the customer
     */
    public double getChange(double totalAmount, double paidAmount){
        return payment.getChange(totalAmount, paidAmount);
    }
}