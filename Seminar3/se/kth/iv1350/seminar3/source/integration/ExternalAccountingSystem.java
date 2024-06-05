package se.kth.iv1350.seminar3.source.integration;

public class ExternalAccountingSystem {
    public double totalAmountInRegister;

    /**
     * Sets the starting amount in the cash register.
     */
    public ExternalAccountingSystem() {
        this.totalAmountInRegister = 100;
    }

    public void updateAccountingSystem(double amount) {
        this.totalAmountInRegister = amount;
        System.out.println("Updated accountingSystem");
    }

    /**
     * Getter function for getting the totel amount in register
     * @return the total amount in the register.
     */
    public double getTotalAmountInRegister(){
        
        return this.totalAmountInRegister;
    }
}