package se.kth.iv1350.seminar4.source.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the external accounting system.
 */

public class ExternalAccountingSystem {
    private final double beginningAmountInRegister = 100;
    private double totalAmountInRegister;
    private List<TotalRevenueObserver> totalRevenueObservers = new ArrayList<>();

    /**
     * Register receives the beginning amount in the register.
     */

    public ExternalAccountingSystem() {
        this.totalAmountInRegister = beginningAmountInRegister;
    }

    /**
     * Updates the accounting system with the total price of the sale.
     * @param amount The amount of the sale.
     */

    public void updateAccountingSystem(double totalPrice) {
        this.totalAmountInRegister = totalPrice;
        notifyObservers();
    }

    /**
     * Adds observers to the list of observers.
     * @param observers The list of observers.
     */

    public void addTotalRevenueObserversToAccounting(List<TotalRevenueObserver> observers) {
        totalRevenueObservers.addAll(observers);
    }

    /**
     * Notifies the observers.
     */

    private void notifyObservers() {
        for (TotalRevenueObserver obs : totalRevenueObservers) {
            obs.updateTotalRevenue(totalAmountInRegister - beginningAmountInRegister);
        }
    }

    /**
     * Gets the total amount in the register.
     * @return The total amount in the register.
     */

    public double getTotalAmountInRegister(){
        return this.totalAmountInRegister;
    }
}