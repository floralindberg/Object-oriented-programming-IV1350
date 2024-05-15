package se.kth.iv1350.seminar4.source.integration;

/**
 * An interface for classes that wants to observe the total income.
 */

public interface TotalIncomeObserver {
    void updateTotalIncome(double totalIncome);
}