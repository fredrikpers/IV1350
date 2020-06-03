package se.kth.iv1350.salesprocess.Model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.salesprocess.Integration.ItemDTO;
import se.kth.iv1350.salesprocess.Integration.Printer;

/**
 *
 * One single sale made by one customer. Paid by one customer.
 */

public class Sale {
    private final LocalTime saleTime;
    private double runningTotal;
    private double totalVAT;
    private int paidAmount;
    private double change;
    private final List <ItemDTO> registeredItems = new ArrayList<ItemDTO>();
    private final int quantity[] = new int[5];
    
    /**
     * Creates a new instance and saves the time of the sale.
     * 
     */
    
    public Sale(){ 
        saleTime = LocalTime.now();     
    }
    /**
     * Updates a list with the scanned items that has been bought.
     * Alse updates the quantity of that item.
     * @param soldItem item that has been sold.
     */
    public void updateListOfRegisteredItems(ItemDTO soldItem){
        if(!(alreadyScanned(soldItem)))
            registeredItems.add(soldItem);
        updateQuantity(soldItem.getItemIdentifier());
    }
    /**
     * If an item has already been scanned we don't want the item to be added multiple.
     * @param soldItem
     * @return true/false depending on result.
     */
    private boolean alreadyScanned(ItemDTO soldItem){
        for(ItemDTO item: registeredItems){
            if(soldItem.getItemIdentifier() == item.getItemIdentifier())
                return true;  
        }
        return false;
    }
    /**
     * updates the running total included VAT
     * @param VATRate VAT rate of the item.
     * @param price Price of the item.
     * @return returning the running total.
     */
    public double updateRunningTotal(double VATRate, double price){
        runningTotal += (price*VATRate);
        return runningTotal;   
    }
    /**
     * updates the running VAT total.
     * @param VATRate VAT rate of the item.
     * @param price Price of the item.
     */
    public void UpdateTotalVAT(double VATRate, double price){
        totalVAT += price*(VATRate/10);
    }
    /**
     * Gets the registered item list
     * @return 
     */
    public List<ItemDTO> getRegisteredItems(){
        return registeredItems;
    }
    /**
     * Updates the quantity of the bought items.
     * @param barcode barcode of the item.
     */
    public void updateQuantity(int barcode){
        barcode -=111;
        for(int i = 0; i <quantity.length; i++){
            if(i == barcode){
                quantity[i] ++;            
            }
        }   
    }
    /**
     * Returns the quantity as an array.
     * @param barcode barcode of the item.
     * @return 
     */
    public int getQuantity(int barcode){
        int index = barcode -=111;
        return quantity[index];
    }
    /**
     * Gets the running total.
     * @return 
     */
    public double getRunningTotal(){
        return runningTotal;
    }
    /**
     * gets the running VAT total.
     * @return 
     */
    public double getTotalVAT(){
        return totalVAT;
    }
    /**
     * Calculates the change for an purchase
     * @param paidAmount
     * @return 
     */
    public double calculateChange(int paidAmount){
        change = paidAmount-runningTotal;
        return change;
    }
    /**
     * Gets the time when the sale is done.
     * @return 
     */
    public LocalTime getTimeOfSale(){
        return this.saleTime;
    }
    /**
     * Sends the needed information to the class receipt
     * @param printer printer object
     * @param storeInfo Store information that holds info about the store.
     */
    public void printReceipt(Printer printer, Store storeInfo){
        Receipt receipt = new Receipt(this, storeInfo);
        printer.printReceipt(receipt);
    }   
}