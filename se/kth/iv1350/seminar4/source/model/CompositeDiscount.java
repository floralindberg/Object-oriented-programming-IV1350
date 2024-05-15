package se.kth.iv1350.seminar4.source.model;

import java.util.Arrays;
import java.util.List;

/**
 * A class that represents a composite discount.
 */

public class CompositeDiscount implements Discount {

    private List<Discount> listOfDiscounts;

    /**
     * Creates a new instance of a composite discount.
     * @param listOfDiscounts The list of discounts that are to be applied.
     */

    public CompositeDiscount(List<Discount> listOfDiscounts) {
        this.listOfDiscounts = listOfDiscounts;
    }

    /**
     * Calculates the discount based on the discount data transfer object.
     * @param discountDTO The discount data transfer object.
     * @return The discount based on the discount data transfer object.
     */

    @Override
    public double calculateDiscount(DiscountDTO discountDTO) {

        double discountEligible = 0;
        for (Discount discount : listOfDiscounts) {
            
            discountEligible += discount.calculateDiscount(discountDTO);
        }
        return discountEligible;
    }

    /**
     * Applies the discount to the total price.
     * @param personalID The personal ID of the customer.
     * @param discountDTO The discount data transfer object.
     * @return The total price after the discount has been applied.
     */
    
    public double applyDiscount(double personalID, DiscountDTO discountDTO){

        double totalPrice = discountDTO.getTotalPrice();
        
        Discount d = new CompositeDiscount(Arrays.asList(new AgeBasedDiscount(personalID), new ItemBasedDiscount()));
        double discount = d.calculateDiscount(discountDTO);
        double priceAfterDiscount = totalPrice - discount;
        return priceAfterDiscount;
}

}

