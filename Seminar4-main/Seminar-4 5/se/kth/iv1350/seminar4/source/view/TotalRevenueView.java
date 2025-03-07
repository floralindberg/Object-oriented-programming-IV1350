package se.kth.iv1350.seminar4.source.view;

import se.kth.iv1350.seminar4.source.integration.TotalRevenueObserver;

/**
 * A simple view that displays the total income.
 */

public class TotalRevenueView implements TotalRevenueObserver {

    @Override
    public void updateTotalRevenue(double totalRevenue) {
        System.out.println("Total Revenue: " + String.format("%.2f", totalRevenue));
    }
}
