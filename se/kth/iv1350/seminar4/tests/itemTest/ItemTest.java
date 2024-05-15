package se.kth.iv1350.seminar4.tests.itemTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.seminar4.source.integration.Item;
import se.kth.iv1350.seminar4.source.integration.ItemDTO;

public class ItemTest {

    @Test
    public void testGetItemDTO() {
        
        int codeOfItem = 123;
        String itemName = "Test Item";
        int quantity = 10;
        double price = 50.0;
        double VAT = 25.0;
        String description = "This is a test item.";

        ItemDTO expectedItemDTO = new ItemDTO(codeOfItem, itemName, quantity, price, VAT, description);
        Item item = new Item(expectedItemDTO);

        
        ItemDTO actualItemDTO = item.getItemDTO(item);


        assertEquals(expectedItemDTO.getCodeOfItem(), actualItemDTO.getCodeOfItem());
        assertEquals(expectedItemDTO.getItemName(), actualItemDTO.getItemName());
        assertEquals(expectedItemDTO.getQuantity(), actualItemDTO.getQuantity());
        assertEquals(expectedItemDTO.getPrice(), actualItemDTO.getPrice());
        assertEquals(expectedItemDTO.getVAT(), actualItemDTO.getVAT());
        assertEquals(expectedItemDTO.getDescription(), actualItemDTO.getDescription());

        
        item.setQuantity(2);
        actualItemDTO = item.getItemDTO(item);

        
        assertNotSame(expectedItemDTO, actualItemDTO);
        
        assertEquals(expectedItemDTO.getCodeOfItem(), actualItemDTO.getCodeOfItem());
        assertEquals(expectedItemDTO.getItemName(), actualItemDTO.getItemName());
        assertNotEquals(expectedItemDTO.getQuantity(), actualItemDTO.getQuantity());
        assertEquals(expectedItemDTO.getPrice(), actualItemDTO.getPrice());
        assertEquals(expectedItemDTO.getVAT(), actualItemDTO.getVAT());
        assertEquals(expectedItemDTO.getDescription(), actualItemDTO.getDescription());
    }
}