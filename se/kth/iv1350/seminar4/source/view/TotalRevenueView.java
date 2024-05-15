package se.kth.iv1350.seminar4.source.view;

import se.kth.iv1350.seminar4.source.integration.TotalIncomeObserver;

/**
 * A simple view that displays the total income.
 */

public class TotalRevenueView implements TotalIncomeObserver {

    private double totalIncome;

    /**
     * Creates an instance of the TotalRevenueView.
     */

    public TotalRevenueView() {
        totalIncome = 0;
    }

    /**
     * Updates the total income.
     * @param totalIncome The total income.
     */

    @Override
    public void updateTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
        printCurrentState();
    }

    /**
     * Prints the current state of the total income.
     */

    private void printCurrentState() {
        System.out.println("Total income: " + String.format("%.2f", totalIncome));
    }
}
