package se.kth.iv1350.seminar4.source.integration;

/**
 * Represents an item in the inventory.
 */

public class Item {
    
    private int codeOfItem;
    private String itemName;
    private int quantity;
    public double price;
    private double VAT;
    private String description;


/**
 * Constructor for item and creates instances of item
 * @param itemDTO is the information used in method
 */
public Item (ItemDTO itemDTO){
    this.codeOfItem = itemDTO.getCodeOfItem();
    this.itemName = itemDTO.getItemName();
    this.quantity = itemDTO.getQuantity();
    this.price = itemDTO.getPrice();
    this.VAT = itemDTO.getVAT();
    this.description = itemDTO.getDescription();
}

/**
 * Getter functions for the attributes of an item
 * @return the different attributes
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

/**
 * Methods for an items quantity
 */
public void incrementQuantity() {
    this.price++;
}

public void setQuantity(int quantity) {
    this.quantity = quantity;
}


/**
 * Getter function for ItemDTO
 * Can get itemDTO from a specific item
 * @param item parameter to get right itemDTO
 * @return all information about item (itemDTO)
 */
public ItemDTO getItemDTO(Item item){
        this.codeOfItem = item.getCodeOfItem();
        this.itemName = item.getItemName();
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
        this.VAT = item.getVAT();
        this.description = item.getDescription();

        ItemDTO itemDTO = new ItemDTO(
        this.codeOfItem,
        this.itemName,
        this.quantity,
        this.price,
        this.VAT,
        this.description
    );
    return itemDTO;
}
}