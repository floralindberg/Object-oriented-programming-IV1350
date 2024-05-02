package se.kth.iv1350.seminar3.source.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import se.kth.iv1350.seminar3.source.integration.Item;

/**
 * This class represents a sale transaction. It maintains a list of all items included in the sale
 * and generates a receipt for the sale. Each sale is timestamped with the time of creation.
 */

public class Sale {
    private ArrayList<Item> singleItemInfo;
    private ArrayList<ArrayList<Item>> listOfAllItems;
    private Receipt receipt;


    /**
     *Constructor for Sale.
     * Creates a new instance of the time of the sale
     */
    public Sale () {
        this.singleItemInfo = new ArrayList<>();
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

        ArrayList<Item> singleItemInfo = new ArrayList<>(); 
        singleItemInfo.add(item);
        this.listOfAllItems.add(singleItemInfo);
    }
    
    /**
     * Getter function to get a specific item from the list of all items
     * @param i index to find the item in the arraylist
     * @return retunrs the specific item
     */
    public Item getItemFromList(int i){
        return listOfAllItems.get(i).get(0);

    }


    /**
     * Method for calculating the total price of the sale with VAT
     * @return returns the total price with VAT
     */
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (ArrayList<Item> singleItemInfo : listOfAllItems) {
            for (Item item : singleItemInfo) {
                totalPrice += item.getPrice();
            }
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
        for (ArrayList<Item> singleItemInfo : listOfAllItems) {
            for (Item item : singleItemInfo) {
                totalVAT += calculateAmountOfVATOneItem(item);
            }
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
    public ArrayList<ArrayList<Item>> getAllItems() {
        return this.listOfAllItems;
    }


    public ArrayList<ArrayList<Item>> getReceiptArray() {
        ArrayList<ArrayList<Item>> newItemsList = new ArrayList<>();

        for (ArrayList<Item> items : listOfAllItems) {
            boolean itemExists = false;

            
            for (ArrayList<Item> uniqueItem : newItemsList) {
                Item newItem = uniqueItem.get(0);
                Item currentItem = items.get(0);


                if (newItem.getCodeOfItem() == currentItem.getCodeOfItem()) {
                    newItem.setQuantity(newItem.getQuantity()+ 1);
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                newItemsList.add(new ArrayList<>(items));
            }
        }

        return newItemsList;
    }
}