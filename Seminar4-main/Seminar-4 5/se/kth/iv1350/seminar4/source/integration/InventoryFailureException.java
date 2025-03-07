package se.kth.iv1350.seminar4.source.integration;

/**
 * Thrown when the external inventory system cannot be reached.
 */

public class InventoryFailureException extends RuntimeException {

    public InventoryFailureException () {
        super("\n" + "External inventory system cannot be reached ");
    }
    
}

