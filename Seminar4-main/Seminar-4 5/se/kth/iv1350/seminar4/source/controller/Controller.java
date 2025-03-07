package se.kth.iv1350.seminar4.source.controller;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.seminar4.source.integration.ExternalAccountingSystem;
import se.kth.iv1350.seminar4.source.integration.ExternalInventorySystem;
import se.kth.iv1350.seminar4.source.integration.ExternalSystemCreator;
import se.kth.iv1350.seminar4.source.integration.InventoryFailureException;
import se.kth.iv1350.seminar4.source.integration.Item;
import se.kth.iv1350.seminar4.source.integration.ItemDTO;
import se.kth.iv1350.seminar4.source.integration.ItemNotFoundInInventoryException;
import se.kth.iv1350.seminar4.source.integration.Printer;
import se.kth.iv1350.seminar4.source.integration.TotalRevenueObserver;
import se.kth.iv1350.seminar4.source.model.NotEligibleForDiscountException;
import se.kth.iv1350.seminar4.source.model.Payment;
import se.kth.iv1350.seminar4.source.model.Sale;

/**
 * The main controller class that handles the flow of the application.
 */

public class Controller {
    private Sale sale;
    private ExternalSystemCreator externalSystemCreator;
	private Payment payment;
    private ExternalInventorySystem externalInventorySystem;
    private ExternalAccountingSystem externalAccountingSystem;
    private Printer printer;
    private List<TotalRevenueObserver> totalRevenueObservers = new ArrayList<>();

    /**
     * Constructor for the Controller class.
     * Initializes the external systems and the printer.
     */

    public Controller() {
		this.externalSystemCreator = new ExternalSystemCreator();
        this.externalInventorySystem = externalSystemCreator.getExternalInventorySystem();
        this.externalAccountingSystem = externalSystemCreator.getExternalAccountingSystem();
        this.printer = Printer.getInstance();
	}

    /**
     * Adds an observer to the list of observers.
     * @param observer the observer to be added to the list.
     */

    public void addTotalRevenueObserver(TotalRevenueObserver observer) {
        totalRevenueObservers.add(observer);
    }

    /**
     * Adds observer to the list of observers.
     */

    public void addTotalRevenueObservers() {
        externalAccountingSystem.addTotalRevenueObserversToAccounting(totalRevenueObservers);
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
      * @return The item with all of its attributes that has the same code of item.
      * @throws ItemNotFoundInInventoryException If the item with the entered code of item doesnt exist in the inventory
      * @throws InventoryFailureException If the inventory system is not reachable / empty. 
      */
    public ItemDTO enterItemIdentifier(int codeOfItem) throws ItemNotFoundInInventoryException, InventoryFailureException {
        if (externalInventorySystem.fakeInventorySystem.isEmpty() || codeOfItem == 123) {
            throw new InventoryFailureException();
        }
        else if (externalInventorySystem.fakeInventorySystem.isEmpty()) {
            throw new ItemNotFoundInInventoryException(codeOfItem);
    }
    
        else {
        Item item = externalInventorySystem.getItemCopyFromInventory(codeOfItem);
        
        sale.registerAllItems(item);
    
        return item.getItemDTO(item);
        }
    }


    /**
     * Shows the total price with VAT.
     * @return the total price with VAT.
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
    private void printReceipt(double paidAmount, double amountInRegister){
    
        printer.print(sale.getTimeOfSale(),sale.getReceipt().receiptInfo(externalAccountingSystem.getTotalAmountInRegister(), paidAmount), amountInRegister);
    }

    /**
     * Ends the sale by cashier when all items are scanned.
     * @return the total price with VAT.
     */
	public double endSale(){
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

   /**
     * Method to check if the customer is eligible for discount.
     * @param personalID the personal ID of the customer.
     * @return the total price with discount.
     * @return the total price without discount.
      * @throws NotEligibleForDiscountException if the customer is not eligable for discount.
      */

    public double checkDiscount(int personalID) throws NotEligibleForDiscountException {

        return sale.checkIfEligibleForDiscount(personalID);

    }

    /**
     * Method to clear the inventory.
     */
    
    public void clearInventory () {
        externalInventorySystem.clearInventory();
    }
}