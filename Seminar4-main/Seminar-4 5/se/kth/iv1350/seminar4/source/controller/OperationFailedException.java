package se.kth.iv1350.seminar4.source.controller;

/**
 * Thrown when an operation fails.
 */

public class OperationFailedException extends Exception {

    public OperationFailedException (String message, Exception cause) {
        super(message, cause);
    }
    
}
