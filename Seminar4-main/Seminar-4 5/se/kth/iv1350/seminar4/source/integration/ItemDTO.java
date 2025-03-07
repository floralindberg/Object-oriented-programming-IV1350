package se.kth.iv1350.seminar4.source.integration;

public class ItemDTO {

    private int codeOfItem;
    private String itemName;
    private int quantity;
    public double price;
    private double VAT;
    private String description;

    /*
     * Getter functions for getting specific information about an item
     */
    public int getCodeOfItem() {
    return codeOfItem;
    }

    public String getItemName() {
    return itemName;
    }

    public int getQuantity() {
    return quantity;
}

    public double getPrice() {
        return price;
    }

    public double getVAT() {
        return VAT;
    }

    public String getDescription() {
        return description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
    * constructor for ItemDTO
    * @param codeOfItem the specific code of an item, can be seen as the barcode
    * @param itemName the name of the product
    * @param quantity the quantity of the product
    * @param price the price of the product
    * @param VAT the VAT for the product
    * @param description is an item description that describes the product
    */

    public ItemDTO(int codeOfItem, String itemName, int quantity, double price, double VAT, String description) {
        this.codeOfItem = codeOfItem;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.VAT = VAT;
        this.description = description;
    }


    /**
     * Creates a string with the different attributes
     */
    @Override
    public String toString() {
        return
        "Item ID: " + codeOfItem  + "\n" +
        "Item name: " + itemName + "\n" +
        "Item cost: " + price + " SEK" + " \n" +
        "VAT: " + VAT + " %" + "\n" +
        "Item description: " + description;

    }
    
}

    
    




    

