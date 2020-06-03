package se.kth.iv1350.salesprocess.controller;

import se.kth.iv1350.salesprocess.integration.AccountingSystem;
import se.kth.iv1350.salesprocess.integration.DataBaseException;
import se.kth.iv1350.salesprocess.integration.InvalidItemIdentifierException;
import se.kth.iv1350.salesprocess.integration.InventorySystem;
import se.kth.iv1350.salesprocess.integration.Printer;
import se.kth.iv1350.salesprocess.integration.SystemCreator;
import se.kth.iv1350.salesprocess.model.Sale;
import se.kth.iv1350.salesprocess.integration.ItemDTO;
import se.kth.iv1350.salesprocess.model.SaleObserver;
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
    private Loghandler logger;
    private SaleObserver saleObserver;
    private double RunningTotal;

   // private List<SaleObserver> saleObserver = new ArrayList<>();
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
        this.logger = new Loghandler();
    }    
    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     */    
    public void startSale(){
        sale = new Sale();
        sale.addSaleObserver(saleObserver);
    }   

    /**
     * Gets the item that has been scanned and updates the class sale with information.
     * Also returns the sold item to the view. 
     * @param barcode barcode that has been scanned.
     * @return ItemDTO with the scanned item.
     * @throws se.kth.iv1350.salesprocess.integration.DataBaseException
     * @throws se.kth.iv1350.salesprocess.integration.InvalidItemIdentifierException
     */
    public ItemDTO scanItem(int barcode)throws DataBaseException, InvalidItemIdentifierException{    
        ItemDTO soldItem = inventorySystem.getItemFromInventorySystem(barcode);
        sale.updateListOfRegisteredItems(soldItem);
        sale.updateRunningTotal(soldItem.getVATRate(),soldItem.getPrice());
        sale.UpdateTotalVAT(soldItem.getVATRate(), soldItem.getPrice());
        return soldItem;
    }   

    /**
     * Gets the running total from the sale.
     * @return current running total.
     */
    public double getRunningTotalFromSale(){
        RunningTotal = sale.getRunningTotal();
        return RunningTotal;
    }
        
    /**
     * Handles the pay action.
     * @param paidAmount the amount that the customer pays.
     * @return double with the change.
     */
    public double pay(int paidAmount){
        double change = sale.calculateChange(paidAmount);
        sale.finnishSale();
        return change;
    }
    /**
     * Ends the sale.
     * returns the running total and prints it.
     * @return double with the total amount.
     */
    public double endSale(){
        double total = sale.getRunningTotal();
        sendInfoToExtSystems();
        return total;
    }
    /**
     * Prints the receipt and the printlog
     */
    public void printing(){
        sale.printReceipt(printer,storeInfo);
    }
    /**
     * Sends info to external Systems after the sale is finished.
     */
    public void sendInfoToExtSystems(){
        accountingSystem.updateAccountingSystem();
        inventorySystem.updateInventorySystem();
    }
    
    /**
     * The specified observer that will be called to Sale class.
     * @param saleObserver 
     */
    public void addSalesObserver(SaleObserver saleObserver){
        this.saleObserver = saleObserver;
    }
}
