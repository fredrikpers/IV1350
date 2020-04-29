package se.kth.iv1350.salesprocess.controller;

import se.kth.iv1350.salesprocess.integration.AccountingSystem;
import se.kth.iv1350.salesprocess.integration.InventorySystem;
import se.kth.iv1350.salesprocess.integration.Printer;
import se.kth.iv1350.salesprocess.integration.SystemCreator;
import se.kth.iv1350.salesprocess.model.Sale;
import se.kth.iv1350.salesprocess.integration.ItemDTO;
import se.kth.iv1350.salesprocess.model.Store;

/**
 *
 * This is the applications only controller. All calls to the model passes through this class.
 */
public class Controller {
    private Sale sale;
    private Printer printer;
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private Store storeInfo;
    
    /**
     * Creates a new instance
     * 
     * @param printer Interface to printer
     * @param creator Used to get all classes that handle database calls.
     */
    public Controller(Printer printer, SystemCreator creator, Store storeInfo){
        this.printer = printer;
        this.inventorySystem = creator.getInventorySystem();
        this.accountingSystem = creator.getAccountingSystem();  
        this.storeInfo = storeInfo; 
    }    
    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     */    
    public void startSale(){
        sale = new Sale();
    }   
    /**
     * Scans the barcode of a item in the POS
     * 
     * @param barcode the barcode of an item.
     * @return returns soldItem which is a object of ItemDTO
     */
    public ItemDTO scanItem(int barcode){
        ItemDTO soldItem = inventorySystem.getItemFromInventorySystem(barcode);
        sale.updateListOfRegisteredItems(soldItem);
        double currentTotal = sale.updateRunningTotal(soldItem.getVATRate(),soldItem.getPrice());
        sale.UpdateTotalVAT(soldItem.getVATRate(), soldItem.getPrice());
        System.out.printf("Running Total: %.2f inc VAT \n" ,currentTotal);
        return soldItem;
    }
    
    /**
     * Handles the pay action.
     * @param paidAmount the amount that the customer pays.
     */
    public void pay(int paidAmount){
        double change = sale.calculateChange(paidAmount);
        System.out.printf("Change to get back from the payment: %.2f \n", change);
    }
    /**
     * Ends the sale.
     * returns the running total and prints it.     * 
     */
    public void endSale(){
        double total = sale.getRunningTotal();
        System.out.printf("Total Price is: %.2f \n",total);
        sendInfoToExtSystems();
    }
    /**
     * Prints the receipt
     */
    public void printReceipt(){
        sale.printReceipt(printer,storeInfo);
    }
    /**
     * Sends info to external Systems after the sale is finished.
     */
    public void sendInfoToExtSystems(){
        accountingSystem.updateAccountingSystem();
        inventorySystem.updateInventorySystem();
    }
}
