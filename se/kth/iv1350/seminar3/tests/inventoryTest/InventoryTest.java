package se.kth.iv1350.seminar3.tests.inventoryTest;

import se.kth.iv1350.seminar3.source.integration.ExternalInventorySystem;
import se.kth.iv1350.seminar3.source.integration.Item;
import se.kth.iv1350.seminar3.source.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class InventoryTest {
    private ExternalInventorySystem externalInventorySystem;
    private ArrayList<ArrayList<Item>> listOfAllItems;
    private Item item;

    @BeforeEach
    public void setUp() {
        externalInventorySystem = new ExternalInventorySystem();
        listOfAllItems = new ArrayList<>();
    }

    @Test
    public void testVerifyItemByCode() {
        assertTrue(externalInventorySystem.verifyItemByCode(111),"Inventory doesn't contain item with code 111");
        assertFalse(externalInventorySystem.verifyItemByCode(999),"Inventory contains item with code 999");
    }

    @Test
    public void testGetItemCopyFromInventory() {
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
    
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(new ItemDTO(111, "Big Wheel Oatmeal", 100, 29, 6, "BigWheel Oatmeal 500 g")));
        items.add(new Item(new ItemDTO(222, "Green Apple", 100, 20, 2, "Grönt äpple")));
        listOfAllItems.add(items);

        externalInventorySystem.decreaseInventoryQuantity(listOfAllItems);
        item = externalInventorySystem.getTheItemFromInventory(111);
        assertEquals(99, item.getQuantity(),"Inventory did not decrease the quantity");
    }
}