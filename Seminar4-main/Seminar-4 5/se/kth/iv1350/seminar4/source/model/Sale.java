package se.kth.iv1350.seminar4.source.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import se.kth.iv1350.seminar4.source.integration.Item;

/**
 * This class represents a sale transaction. It maintains a list of all items included in the sale
 * and generates a receipt for the sale. Each sale is timestamped with the time of creation.
 */

public class Sale {
    private ArrayList<Item> listOfAllItems;
    private Receipt receipt;


    /**
     *Constructor for Sale.
     * Creates a new instance of the time of the sale
     */
    public Sale () {
        this.listOfAllItems = new ArrayList<>();
        this.receipt = new Receipt(this);

    }

    /**
     * A function to get the current time of a sale
    *
    * @return the current time in java.time.LocalTime format
    */
    public String getTimeOfSale() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return currentTime.format(formatter);
    }

/**
  * Method for registering all of the items in the sale
  * @param item sends in all of the items being puchased
  */
    public void registerAllItems(Item item) {

        boolean check = false;

        for ( Item items : listOfAllItems){
            if(items.getCodeOfItem() == item.getCodeOfItem()){

                items.setQuantity(items.getQuantity() + 1);
                check = true;
            }
        }
        
        if(check == false){
            
            this.listOfAllItems.add(item);
        }
    }
    
    /**
     * Getter function to get a specific item from the list of all items
     * @param i index to find the item in the arraylist
     * @return retunrs the specific item
     */
    public Item getItemFromList(int i){
        return listOfAllItems.get(i);

    }


    /**
     * Method for calculating the total price of the sale with VAT
     * @return returns the total price with VAT
     */
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Item item : listOfAllItems) {
                totalPrice += item.getPrice()*item.getQuantity();
        }
        return totalPrice;
    }
    
    /**
     * Method that calculates the VAT of one single item
     * @param item the item that we want the price with VAT of
     * @return the price of the item with VAT
     */
    public double calculateAmountOfVATOneItem(Item item){

        double priceWithoutVAT  = item.getPrice()/((item.getVAT() / 100)+1);
        double priceVAT = item.getPrice()-priceWithoutVAT;
        return priceVAT;
    }

    /**
     * Calculates only the total VAT of the sale
     * @return the total VAT of the sale
     */
    public double calculateTotalAmountVAT(){
        double totalVAT = 0.0;
        for (Item item : listOfAllItems) {
                totalVAT += calculateAmountOfVATOneItem(item)*item.getQuantity();
        }
        return totalVAT;

    }
    
    /**
   * Getter function to get the receipt
   * @return the receipt
   */
    public Receipt getReceipt() {
    
        return this.receipt;
    }

    /**
     * Getter function to get the whole list of all items bought
     * but with increased quantity instead of duplicated items
     * @return the list of all the items bought
     */
    public ArrayList<Item> getAllItems() {
        return this.listOfAllItems;
    }

    /**
     * Method to get the total price and the total VAT of the sale
     * @return the total price and the total VAT of the sale
     */

    public String getTotalPriceAndVAT(){

        StringBuilder saleBuilder = new StringBuilder();

        saleBuilder.append("Total cost (incl VAT):").append(String.format("%.2f", calculateTotalPrice())).append(" SEK").append("\n").append("Total VAT:").append(String.format("%.2f", calculateTotalAmountVAT())).append(" SEK").append("\n");
        return saleBuilder.toString();
    }

    /**
     * Method to calculate the amount of items bought
     * @return the amount of items bought
     */

    public int calculateAmountOfItems(){
        int amountOfItems = 0;
        for (Item item : listOfAllItems) {
            amountOfItems += item.getQuantity();
        }
        return amountOfItems;
    }

    /**
     * Method to create a discount DTO
     * @return the discount DTO
     */

    public DiscountDTO getDiscountDTO(){

        return new DiscountDTO(calculateTotalPrice(), calculateAmountOfItems());

    }

    /**
     * Method to apply discounts to the sale
     * @param personalID the personal ID of the customer
     * @param discountDTO the discount DTO
     * @return the price after the discount
     */

    public double applyDiscounts(int personalID, DiscountDTO discountDTO) {

        double totalPrice = calculateTotalPrice();

        Discount d = new CompositeDiscount(Arrays.asList(new AgeBasedDiscount(personalID), new ItemBasedDiscount()));
        double discount = d.calculateDiscount(discountDTO);
        double priceAfterDiscount = totalPrice - discount;

        return priceAfterDiscount;
    
    }

    /**
     * Method to check if the customer is eligible for a discount
     * @return the total price of the sale with the discount
     */

    public double checkIfEligibleForDiscount(int personalID) throws NotEligibleForDiscountException {

        if(personalID <= 19590101){
            return applyDiscounts(personalID, getDiscountDTO());
        }
        else {
        throw new NotEligibleForDiscountException(personalID);
    }
}

}