package se.kth.iv1350.seminar4.source.model;

/**
 * A class that calculates a discount based on the number of items.
 */

public class ItemBasedDiscount implements Discount {

    /**
     * Calculates the discount based on the number of items.
     * @param discountDTO The discount data transfer object.
     * @return The discount based on the number of items.
     */
    
    @Override
    public double calculateDiscount(DiscountDTO discountDTO) {

        double totalPrice = discountDTO.getTotalPrice();
        int numberOfItems = discountDTO.getNumberOfItems();

        double discount = (0.005 * numberOfItems);
        return totalPrice * discount;
    }
}