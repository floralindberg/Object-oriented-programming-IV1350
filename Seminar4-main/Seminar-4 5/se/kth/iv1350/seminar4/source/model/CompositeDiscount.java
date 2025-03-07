package se.kth.iv1350.seminar4.source.model;
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

}

