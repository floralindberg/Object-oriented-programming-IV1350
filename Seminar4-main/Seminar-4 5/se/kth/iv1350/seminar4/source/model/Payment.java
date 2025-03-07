package se.kth.iv1350.seminar4.source.model;


public class Payment {
    private double totalAmount;
    private double paidAmount;

/**
 * Constructor for payment
 * @param totalAmount is the total amount with VAT
 * @param paidAmount is the paid amount by the customer
 */
    public Payment (double totalAmount, double paidAmount) {
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
    }

    /**
     * Getter function for gettin the change for the customer
     * @param totalAmount is the total amount with VAT
     * @param paidAmount is the paid amount by the customer
     * @return the change for the customer
     */
    public double getChange(double totalAmount, double paidAmount) {
        return paidAmount - totalAmount;
    }
    
    /**
     * Method for adding the total amount in the register to update the accounting system
     * @param totalAmount is the total amount with VAT
     * @param paidAmount is the paid amount by the customer
     * @param totalAmountInRegister the total amount existing in the register
     * @return the total amount in the register
     */
    public double addToAccounting(double totalAmount, double paidAmount, double totalAmountInRegister) {

        totalAmountInRegister = (totalAmountInRegister + paidAmount) - getChange(totalAmount,paidAmount);
    
        return totalAmountInRegister;
        
    }
}