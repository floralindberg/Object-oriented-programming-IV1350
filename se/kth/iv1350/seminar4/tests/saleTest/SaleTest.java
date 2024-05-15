package se.kth.iv1350.seminar4.tests.saleTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.seminar4.source.controller.Controller;
import se.kth.iv1350.seminar4.source.integration.Item;
import se.kth.iv1350.seminar4.source.integration.ItemDTO;
import se.kth.iv1350.seminar4.source.model.Sale;

public class SaleTest {
    private Sale sale;
    private int codeOfItem1;
    private int codeOfItem2;
    private Controller contr;

    @BeforeEach
    public void setUp() {
        this.contr = new Controller();
        this.contr.startSale();
        this.sale = this.contr.getSale();
        this.codeOfItem1 = 111;
        this.codeOfItem2 = 222;
    }

    @AfterEach
    public void tearDown() {
        contr = null;
        this.sale = null;
    }

    @Test
    public void testRegisterAllItems() {
        contr.enterItemIdentifier(codeOfItem1);
        contr.enterItemIdentifier(codeOfItem2);
        assertEquals(2, sale.getAllItems().size(), "Unexpected number of items in sale after registering two items");
    }

    @Test
    public void testGetItemFromList() {
        contr.enterItemIdentifier(codeOfItem1);
        Item item = sale.getItemFromList(0);
        assertEquals(codeOfItem1, item.getCodeOfItem(), "Unexpected item code returned");
    }

    @Test
    public void testCalculateTotalPrice() {
        contr.enterItemIdentifier(codeOfItem1);
        contr.enterItemIdentifier(codeOfItem2);
        double totalPrice = sale.calculateTotalPrice();
        assertEquals(49, totalPrice, "Unexpected total price with VAT");
    }

    @Test
    public void testCalculateVATOneItem() {
        Item item = new Item(new ItemDTO(codeOfItem1, "Item 1", 1, 10.0, 25.0, "Description 1"));
        double priceVAT = sale.calculateAmountOfVATOneItem(item);
        assertEquals(2, priceVAT, "Unexpected amount of VAT for one item");
    }

    @Test
    public void testCalculateTotalVAT() {
        contr.enterItemIdentifier(codeOfItem1);
        contr.enterItemIdentifier(codeOfItem2);
        double totalVAT = sale.calculateTotalAmountVAT();
        assertEquals(2.03, totalVAT, 0.01, "Unexpected total VAT");
    }

    @Test
    public void testGetReceipt() {
        assertNotNull(sale.getReceipt(), "Receipt object is null");
    }

    @Test
    public void testGetAllItems() {
        contr.enterItemIdentifier(codeOfItem1);
        contr.enterItemIdentifier(codeOfItem2);
        assertEquals(2, sale.getAllItems().size(), "Unexpected number of items in sale");
    }

    @Test
    public void testGetReceiptArray() {
        contr.enterItemIdentifier(codeOfItem1);
        contr.enterItemIdentifier(codeOfItem2);
        assertEquals(2, sale.getAllItems().size(), "Unexpected number of items in receipt array");
    }
}
