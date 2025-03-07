package se.kth.iv1350.seminar4.tests.integration;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.seminar4.source.integration.ExternalAccountingSystem;

public class AccountingTest {
    private ExternalAccountingSystem accountingSystem;

    @BeforeEach
    public void setUp() {
        accountingSystem = new ExternalAccountingSystem();
    }

    @AfterEach
    public void tearDown() {
        accountingSystem = null;
    }

    @Test
    public void testUpdateAccountingSystem() {
        double amount = 200.0;
        accountingSystem.updateAccountingSystem(amount);
        assertEquals(amount, accountingSystem.getTotalAmountInRegister(), "Unexpected total amount in register after updated");
    }

    @Test
    public void testGetTotalAmountInRegister() {
        assertEquals(100.0, accountingSystem.getTotalAmountInRegister(), "Unexpected total amount in register");
    }
}