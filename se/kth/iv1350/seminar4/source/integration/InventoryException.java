package se.kth.iv1350.seminar4.source.integration;

public class InventoryException extends RuntimeException {

    public InventoryException () {
        super("External inventory system cannot be reached ");
    }
    
}
