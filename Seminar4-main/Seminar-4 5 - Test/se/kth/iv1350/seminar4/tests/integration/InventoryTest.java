package se.kth.iv1350.seminar4.tests.integration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.seminar4.source.integration.ExternalInventorySystem;
import se.kth.iv1350.seminar4.source.integration.Item;
import se.kth.iv1350.seminar4.source.integration.ItemDTO;
import se.kth.iv1350.seminar4.source.integration.ItemNotFoundInInventoryException;

import java.util.ArrayList;

public class InventoryTest {
    private ExternalInventorySystem externalInventorySystem;
    private ArrayList<Item> listOfAllItems;
    private Item item;

    @BeforeEach
    public void setUp() {
        externalInventorySystem = ExternalInventorySystem.getInstance();
        externalInventorySystem.resetInvetory();
        listOfAllItems = new ArrayList<>();
    }

    @Test
    public void testVerifyItemByCode() {
        assertTrue(externalInventorySystem.verifyItemByCode(111),"Inventory doesn't contain item with code 111");
        assertFalse(externalInventorySystem.verifyItemByCode(999),"Inventory contains item with code 999");
    }

    @Test
    public void testGetItemCopyFromInventory() throws ItemNotFoundInInventoryException{
        Item item = externalInventorySystem.getItemCopyFromInventory(111);
        assertNotNull(item,"The item wasn't created");
        assertEquals(111, item.getCodeOfItem(), "Wrong item was created");

        assertEquals(1, item.getQuantity(), "Item got the wrong quantity");
    }

    @Test
    public void testGetTheItemFromInventory(){

        Item item = externalInventorySystem.getTheItemFromInventory(111);
        assertNotNull(item, "Inventory doesn't contain item with code 111");
        assertEquals(111, item.getCodeOfItem(), "Wrong item from inventory");

        assertEquals(100, item.getQuantity(), "Wrong item from inventory");

        Item wrongItem = externalInventorySystem.getTheItemFromInventory(112);

        assertNull(wrongItem, "Wrong item from inventory");

    }

    @Test
    public void testDecreaseInventoryQuantity() {
    
        Item item1 = new Item(new ItemDTO(111, "Big Wheel Oatmeal", 100, 29, 6, "BigWheel Oatmeal 500 g"));
        Item item2 = new Item(new ItemDTO(222, "Green Apple", 100, 20, 2, "Grönt äpple"));
        listOfAllItems.add(item1);
        listOfAllItems.add(item2);

        externalInventorySystem.decreaseInventoryQuantity(listOfAllItems);
        item = externalInventorySystem.getTheItemFromInventory(111);
        assertEquals(99, item.getQuantity(),"Inventory did not decrease the quantity");
    }
}