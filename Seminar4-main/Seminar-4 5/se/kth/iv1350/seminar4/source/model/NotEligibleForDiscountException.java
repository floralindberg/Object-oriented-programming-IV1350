package se.kth.iv1350.seminar4.source.model;

/**
 * Thrown when a person is not eligible for discount.
 */

public class NotEligibleForDiscountException extends Exception{

    public NotEligibleForDiscountException(int personalID) {
        super("Person born: " + personalID + " is not eligible for discount.");
    }
}
