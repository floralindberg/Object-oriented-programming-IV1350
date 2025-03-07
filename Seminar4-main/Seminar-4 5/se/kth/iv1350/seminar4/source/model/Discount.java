package se.kth.iv1350.seminar4.source.model;

/**
 * An interface for classes that wants to calculate a discount.
 */

public interface Discount {
    double calculateDiscount(DiscountDTO discountDTO);
}