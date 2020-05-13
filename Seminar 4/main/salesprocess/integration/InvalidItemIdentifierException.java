package se.kth.iv1350.salesprocess.integration;

/**
 * This exception is thrown when a speficific item identifier can't be found in the database.
 * 
 */
public class InvalidItemIdentifierException extends Exception {
    
    private int barcode;

    public InvalidItemIdentifierException(int barcode) {
        super("Can't find item with that item identifier " +barcode+ "");
        this.barcode = barcode;
    }
    
    public int getBarcode(){
        return barcode;
    }
    
}
