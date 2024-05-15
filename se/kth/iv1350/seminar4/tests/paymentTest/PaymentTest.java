package se.kth.iv1350.seminar4.tests.paymentTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.seminar4.source.integration.ExternalAccountingSystem;
import se.kth.iv1350.seminar4.source.model.Payment;

public class PaymentTest {
    private Payment payment;
    private ExternalAccountingSystem externalAccountingSystem;
    private double paidAmount;
    private double totalAmount;

    @Before
    public void setUp() {
        totalAmount = 100.0;
        paidAmount = 150.0;
        payment = new Payment(totalAmount, paidAmount);
        
        externalAccountingSystem = new ExternalAccountingSystem();
    }

    @Test
    public void testGetChange() {
        
        double expectedChange = 50.0;
        double actualChange = payment.getChange(totalAmount,paidAmount);
        assertEquals(expectedChange, actualChange, 0.001);

        totalAmount = 200.0;
        paidAmount = 2002.0;
        expectedChange = 1802.0;
        actualChange = payment.getChange(totalAmount,paidAmount);
        assertEquals(expectedChange, actualChange, 0.001);
    }
    
    @Test
    public void testAddToAccounting() {
        
        double expectedTotalInRegister = 200.0;

        
        double actualTotalInRegister = payment.addToAccounting(totalAmount,paidAmount, externalAccountingSystem.getTotalAmountInRegister());
        assertEquals(expectedTotalInRegister, actualTotalInRegister,0);
    }
}
