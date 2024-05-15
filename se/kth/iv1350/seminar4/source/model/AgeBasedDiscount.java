package se.kth.iv1350.seminar4.source.model;

/**
 * This class calculates the discount based on the age of the customer.
 */

public class AgeBasedDiscount implements Discount {
    private double personalID;

    /**
     * Creates an instance of the AgeBasedDiscount class.
     * @param personalID The personal ID of the customer.
     */

    public AgeBasedDiscount(double personalID) {
        this.personalID = personalID;
    }

    /**
     * Calculates the discount based on the age of the customer.
     * @param discountDTO The discount data transfer object.
     * @return The discount based on the age of the customer.
     */

    @Override
    public double calculateDiscount(DiscountDTO discountDTO) {

        double totalPrice = discountDTO.getTotalPrice();
        
        String personalIDString = String.valueOf((int)personalID);
        String twoDigitsAge = personalIDString.substring(2, 4);
        double twoDigitsAgeAsDouble = Double.parseDouble(twoDigitsAge);
        double discount = ((100 - twoDigitsAgeAsDouble)*0.001);
        return totalPrice * discount;
    }
}
