package se.kth.iv1350.salesprocess.integration;

/**
 * This exception is thrown when there is an error in the InventorySystem
 * 
 */
public class InventorySystemException extends Exception {
    
    public InventorySystemException(String msg, Exception cause){
        super(msg, cause);
    }    
}
