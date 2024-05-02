package se.kth.iv1350.seminar3.source.integration;

import java.util.ArrayList;



    public class ExternalInventorySystem {

        public ArrayList<Item> fakeInventorySystem;
    
        /**
         * Method for being able to store and add items in the inventory system.
         * Is also used for being able to run the fake execution.
         */
        public ExternalInventorySystem() {
            
            fakeInventorySystem = new ArrayList<>();
    

            ItemDTO itemOne = new ItemDTO(111, "Big Wheel Oatmeal", 100, 29, 6, "BigWheel Oatmeal 500 g , whole grain oats ,\n" + "high fiber , gluten free");
            ItemDTO itemTwo = new ItemDTO(222, "Green Apple", 100, 20, 2, "Grönt äpple - Granny Smith");
            ItemDTO itemThree = new ItemDTO(333, "YouGoGo Blueberry", 100, 14, 5, "YouGoGo Blueberry 240 g , low sugar youghurt ,\n" + "blueberry flavour");
    
            fakeInventorySystem.add(new Item(itemOne));
            fakeInventorySystem.add(new Item(itemTwo));
            fakeInventorySystem.add(new Item(itemThree));
        }
    
        /**
         * Checks to see if the item exists in the inventory system
         * @param codeOfItem is the sent in information to see if there is a match in the inventory
         * @return if it is true or false that the item exists.
         */
        public boolean verifyItemByCode(int codeOfItem) {

            for (Item item : fakeInventorySystem) {
                if (item.getCodeOfItem() == codeOfItem) {
                    return true;
                }
            }
            return false;
            
        }

        /**
         * Creates a copy with all information about the item 
         * @param codeOfItem is the information sent in to search in the inventory system
         * @return The copy of the item if found in inventory system
         */
        public Item getItemCopyFromInventory(int codeOfItem) {
            for (Item item : fakeInventorySystem) {
                if (item.getCodeOfItem() == codeOfItem) {
                    Item itemCopy = new Item(item.getItemDTO(item));
                    itemCopy.setQuantity(1);
                    return itemCopy;
                }
            }
            return null;
        }

        /**
         * Getter function for getting the item from the inventory
         * @param codeOfItem the sent in info to search with in inventory system
         * @return the specific item if found in inventory
         */
        public Item getTheItemFromInventory(int codeOfItem) {
            for (Item item : fakeInventorySystem) {
                if (item.getCodeOfItem() == codeOfItem) {
                    
                    return item;
                }
            }
            return null;
        }



        /**
         * Method to update the inventory system when items are bought.
         * @param listOfAllItems The items bought
         */
        public void decreaseInventoryQuantity(ArrayList<ArrayList<Item>> listOfAllItems){

            for (ArrayList<Item> itemList : listOfAllItems){
                for (Item item : fakeInventorySystem){

                    if(itemList.get(0).getCodeOfItem() == item.getCodeOfItem()){
                        item.setQuantity(item.getQuantity() - 1);
                    }
                
                }

            }
        }
    }
    

