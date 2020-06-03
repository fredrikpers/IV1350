package se.kth.iv1350.salesprocess.Integration;

import se.kth.iv1350.salesprocess.Model.Receipt;

/**
 * The physical printer that prints out the receipt.
 * 
 */
public class Printer {
    
    public Printer(){
    }
    /**
     * Prints the receipt
     * <code>System.out</code> instead of a printer.
     * @param receipt 
     */
    public void printReceipt(Receipt receipt){
    System.out.println(receipt.createReceiptString());
    }
}
