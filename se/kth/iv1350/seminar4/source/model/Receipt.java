package se.kth.iv1350.seminar4.source.model;

import se.kth.iv1350.seminar4.source.integration.Item;


public class Receipt {
    private String startReceipt;
    private String endReceipt;
    private Sale sale;
    
    /**
     * Creates a new instance of a receipt.
     * @param sale The sale that the receipt is for.
     */

    public Receipt (Sale sale) {

        this.startReceipt = "------------- Begin receipt ---------------";
        this.endReceipt = "------------- End receipt ---------------";
        this.sale = sale;
    }

    /**
     * Creates a receipt for the sale.
     * @param amountInRegister The amount in the register.
     * @param paidAmount The amount paid by the customer.
     * @return The receipt as a string.
     */

    public String receiptInfo(double amountInRegister, double paidAmount) {

        StringBuilder receiptBuilder = new StringBuilder();
    
        receiptBuilder.append(this.startReceipt).append("\n").append("\n");
        
        for (Item item : sale.getAllItems()) {
            
            int quantity = item.getQuantity();
            double pricePerItem = item.getPrice();
            double totalPrice = quantity * item.getPrice();
    
            receiptBuilder.append(item.getItemName()).append(" ").append(quantity).append(" ").append("x ").append(String.format("%.2f", pricePerItem)).append(" SEK:").append(" ").append(String.format("%.2f", totalPrice)).append(" SEK").append("\n").append("\n");
    }
    
    double totalAmount = sale.calculateTotalPrice();
    double totalVAT = sale.calculateTotalAmountVAT();
    
    receiptBuilder.append("\n").append("Total : ").append(String.format("%.2f", totalAmount)).append(" SEK").append("\n").append("VAT : ").append(String.format("%.2f", totalVAT)).append(" SEK").append("\n\n");
    
    receiptBuilder.append(this.endReceipt);
    
    return receiptBuilder.toString();
    }
}
