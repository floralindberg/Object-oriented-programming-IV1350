package se.kth.iv1350.seminar4.tests.controller;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import se.kth.iv1350.seminar4.source.controller.Controller;
import se.kth.iv1350.seminar4.source.integration.ExternalAccountingSystem;
import se.kth.iv1350.seminar4.source.integration.ExternalInventorySystem;
import se.kth.iv1350.seminar4.source.integration.InventoryFailureException;
import se.kth.iv1350.seminar4.source.integration.Item;
import se.kth.iv1350.seminar4.source.integration.ItemNotFoundInInventoryException;
import se.kth.iv1350.seminar4.source.model.NotEligibleForDiscountException;
import se.kth.iv1350.seminar4.source.model.Payment;
import se.kth.iv1350.seminar4.source.model.Sale;

public class ContrTest {
    private Sale sale;
    private int codeOfItem1;
    private int codeOfItem2;
    private Controller contr;
    private ExternalInventorySystem externalInventorySystem;

    @BeforeEach
        public void setUp() {
            this.contr = new Controller();
            this.contr.startSale();
            this.sale = this.contr.getSale();
            this.codeOfItem1 = 111;
            this.codeOfItem2 = 222;

        }

    @After
    public void tearDown() {
        contr = null;
        this.sale = null;
    }

    @Before
        public void settingUp() {
            this.contr = new Controller();
            this.contr.startSale();
            this.sale = this.contr.getSale();
            this.codeOfItem1 = 111;
            this.codeOfItem2 = 222;
            externalInventorySystem = ExternalInventorySystem.getInstance();
        }

        @Test
        public void testAddItem() throws ItemNotFoundInInventoryException {

            contr.enterItemIdentifier(codeOfItem1);
            
            assertEquals(1, sale.getAllItems().size(), "Unexpected quantity of items in sale");

            contr.enterItemIdentifier(codeOfItem2);
    
            assertEquals(2, sale.getAllItems().size(), "Unexpected quantity of items in sale");
    
            contr.enterItemIdentifier(codeOfItem1);
    
            assertEquals(2, sale.getAllItems().size(), "Unexpected quantity of items in sale");
            assertEquals(2, sale.getAllItems().get(0).getQuantity(), "Unexpected quantity of the first item in the sale");
        }

        @Test
        public void testItemNotFoundInInventoryException() throws ItemNotFoundInInventoryException {

            assertDoesNotThrow(() -> {
                contr.enterItemIdentifier(111);
            });

            assertThrows(ItemNotFoundInInventoryException.class, () -> {
                contr.enterItemIdentifier(444);
            });

        }

        @Test
        public void testInventoryFailureException() throws InventoryFailureException {

            assertDoesNotThrow(() -> {
                contr.enterItemIdentifier(111);
            });

            contr.clearInventory();

            assertThrows(InventoryFailureException.class, () -> {
                contr.enterItemIdentifier(111);
            });
        }

        @Test public void notEligibleForDiscountException() throws NotEligibleForDiscountException {

            assertDoesNotThrow(() -> {
                contr.checkDiscount(19241201);
            });

            assertThrows(NotEligibleForDiscountException.class, () -> {
                contr.checkDiscount(20011020);
            });

        }

        
        
        @Test
        public void testPay() throws ItemNotFoundInInventoryException {
            double totalAmount = 100;
            double paidAmount = 100;
            ExternalAccountingSystem externalAccountingSystem = new ExternalAccountingSystem();
            ExternalInventorySystem externalInventorySystem = ExternalInventorySystem.getInstance();

            Payment payment = new Payment(totalAmount,paidAmount);
            double amountInRegister = payment.addToAccounting(totalAmount, paidAmount, externalAccountingSystem.getTotalAmountInRegister());
            assertEquals(200, amountInRegister,"Unexpected amount in register after payment");

            totalAmount = 70;
            paidAmount = 100;

            payment = new Payment(totalAmount,paidAmount);
            amountInRegister = payment.addToAccounting(totalAmount, paidAmount, externalAccountingSystem.getTotalAmountInRegister());
            assertEquals(170, amountInRegister,"Unexpected amount in register after payment");

            contr.enterItemIdentifier(111);

            externalInventorySystem.decreaseInventoryQuantity(sale.getAllItems());

            Item item = externalInventorySystem.getTheItemFromInventory(111);
            assertEquals(99, item.getQuantity(), "Unexpected quantity of a specific item after sale");
        
	}

        @Test
        public void testEndSale() throws ItemNotFoundInInventoryException{
        this.externalInventorySystem = ExternalInventorySystem.getInstance();
        double expectedTotalPriceVAT = 49.0;
        Item item1 = externalInventorySystem.getItemCopyFromInventory(111);
        Item item2 = externalInventorySystem.getItemCopyFromInventory(222);

        sale.registerAllItems(item1);
        sale.registerAllItems(item2);

        double actualTotalPriceVAT = contr.endSale();


        assertEquals(expectedTotalPriceVAT, actualTotalPriceVAT,01);

        }

        @Test
        public void testGetChange(){
            double totalAmount = 200;
            double paidAmount = 300;
            double changeCheck;
            double change = 100;

            Payment payment = new Payment(totalAmount, paidAmount);
        
                changeCheck = payment.getChange(totalAmount, paidAmount);

                assertEquals(change, changeCheck, "Unexpected change");

            }
        }
    
