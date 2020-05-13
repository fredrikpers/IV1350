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

   // private List<SaleObserver> saleObserver = new ArrayList<>();
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
     * Scans the barcode of a item in the POS and sees if the barcode is valid.
     * Also calls the logger that loggs all exceptions.
     * 
     * @param barcode the barcode of an item.
     * 
     */
    public void tryItem(int barcode)throws DataBaseException, InvalidItemIdentifierException{
        try{
        ItemDTO soldItem = inventorySystem.getItemFromInventorySystem(barcode);
        scanItem(soldItem);
        }
        catch(DataBaseException dbe){
        System.out.println(dbe);  
        logger.logException(dbe);
        }
        catch(InvalidItemIdentifierException iiie){
        System.out.println(iiie);
        logger.logException(iiie);
        }
    }
    
    /**
     * 
     * Adds the item to the current sale.
     * @param soldItem
     *  
     */
    public void scanItem(ItemDTO soldItem){     
        sale.updateListOfRegisteredItems(soldItem);
        presentOnScreen(soldItem);
        double currentTotal = sale.updateRunningTotal(soldItem.getVATRate(),soldItem.getPrice());
        sale.UpdateTotalVAT(soldItem.getVATRate(), soldItem.getPrice());
        System.out.printf("Running Total: %.2f inc VAT \n" ,currentTotal);
    }
    
    /**
     * Handles the pay action.
     * @param paidAmount the amount that the customer pays.
     */
    public void pay(int paidAmount){
        double change = sale.calculateChange(paidAmount);
        System.out.printf("Change to get back from the payment: %.2f \n", change);
        sale.notifyOberservers();
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
     * Prints the receipt and the printlog
     */
    public void printing(){
        sale.printReceipt(printer,storeInfo);
        logger.printLog();
    }
    /**
     * Sends info to external Systems after the sale is finished.
     */
    public void sendInfoToExtSystems(){
        accountingSystem.updateAccountingSystem();
        inventorySystem.updateInventorySystem();
    }
    
     /**
     * Present results on the screen for the viewer.
     * @param item The <code>ItemDTO</code> of the item that is going to present on screen.
     */
    public void presentOnScreen(ItemDTO item){
        System.out.println("ItemDescription: "+item.getitemDescription());
        System.out.println("Price: " +item.getPrice());
        System.out.println("");
    }
    /**
     * The specified observer that will be called to Sale class.
     * @param saleObserver 
     */
    public void addSalesObserver(SaleObserver saleObserver){
        this.saleObserver = saleObserver;
    }
}
