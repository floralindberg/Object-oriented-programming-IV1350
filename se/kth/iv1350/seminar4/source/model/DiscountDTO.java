package se.kth.iv1350.seminar4.source.model;

/**
 * A class that holds the information about a discount.
 */

public class DiscountDTO {

    private double totalPrice;
    private int numberOfItems;

    /**
     * Getters for discount data transfer object.
     * @param totalPrice The total price of the sale.
     * @param numberOfItems The number of items in the sale.
     */
    
    public double getTotalPrice(){
        return totalPrice;
        }
        
        public int getNumberOfItems(){
            return numberOfItems;
        }

        /**
         * Creates an instance of the DiscountDTO class.
         * @param totalPrice The total price of the sale.
         * @param numberOfItems The number of items in the sale.
         */

        public DiscountDTO(double totalPrice, int numberOfItems){
            this.totalPrice = totalPrice;
            this.numberOfItems = numberOfItems;
        }










    
}
