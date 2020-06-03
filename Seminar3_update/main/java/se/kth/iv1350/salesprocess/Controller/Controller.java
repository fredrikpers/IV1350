package se.kth.iv1350.salesprocess.Controller;

import se.kth.iv1350.salesprocess.Integration.AccountingSystem;
import se.kth.iv1350.salesprocess.Integration.InventorySystem;
import se.kth.iv1350.salesprocess.Integration.ItemDTO;
import se.kth.iv1350.salesprocess.Integration.Printer;
import se.kth.iv1350.salesprocess.Integration.SystemCreator;
import se.kth.iv1350.salesprocess.Model.Sale;
import se.kth.iv1350.salesprocess.Model.Store;

/**
 *
 * This is the applications only controller. All calls to the model passes through this class.
 */
public class Controller {
    private Sale sale;
    private final Printer printer;
    private final InventorySystem inventorySystem;
    private final AccountingSystem accountingSystem;
    private final Store storeInfo;
    private double RunningTotal;
    
    /**
     * Creates a new instance
     * 
     * @param printer Interface to printer
     * @param creator Used to get all classes that handle database calls.
     * @param storeInfo
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
        sale.updateRunningTotal(soldItem.getVATRate(),soldItem.getPrice());
        sale.UpdateTotalVAT(soldItem.getVATRate(), soldItem.getPrice());
        return soldItem;
    }
    public double getRunningTotal(){
        double runningTotal = sale.getRunningTotal();
        return runningTotal;
    }
    
    /**
     * Handles the pay action.
     * @param paidAmount the amount that the customer pays.
     * @return change to get back from the payment.
     */
    public double pay(int paidAmount){
        double change = sale.calculateChange(paidAmount);
        return change;
    }
    /**
     * Ends the sale.returns the running total and prints it.
     * * 
     * @return the total price of the sale.
     */
    public double endSale(){
        double total = sale.getRunningTotal();
        sendInfoToExtSystems();
        return total;
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