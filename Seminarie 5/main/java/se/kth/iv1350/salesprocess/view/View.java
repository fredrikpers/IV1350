package se.kth.iv1350.salesprocess.view;

import se.kth.iv1350.salesprocess.controller.Controller;
import se.kth.iv1350.salesprocess.integration.DataBaseException;
import se.kth.iv1350.salesprocess.integration.InvalidItemIdentifierException;
import se.kth.iv1350.salesprocess.controller.Loghandler;
import se.kth.iv1350.salesprocess.integration.ItemDTO;

/**
 *
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all 
 * system operations in the controller.
 */
public class View {
    private Controller contr;
    private final ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private final Loghandler logger;
    
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    
    public View(Controller contr){
        this.contr = contr;
        this.logger = new Loghandler();
        contr.addSalesObserver(new TotalRevenueView());
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller.
     * 
     * @throws se.kth.iv1350.salesprocess.integration.DataBaseException
     * @throws se.kth.iv1350.salesprocess.integration.InvalidItemIdentifierException
     */
    public void runFakeExecution() throws DataBaseException,InvalidItemIdentifierException{
        contr.startSale();
        System.out.println("A new sale has been started.");
        System.out.println("Scanning items...");
        
        tryItem(111);
        tryItem(112);
        tryItem(113);
        tryItem(113);
        tryItem(110);
        tryItem(25);
 
        System.out.println("All items are scanned");
        double total = contr.endSale(); 
        System.out.printf("Total price is : %.2f \n" ,total);
        
        double change = contr.pay(40);
        System.out.printf("Change to get back from the payment: %.2f \n", change);
        
        contr.printing();
        logger.printLog();
    }
    /**
     * Method that tries to scan an item. If an error occurs it will call them.
     * @param barcode barcode of the scanned item.
     */
    private void tryItem(int barcode){
        try{
        ItemDTO item = contr.scanItem(barcode);
        double currentTotal = contr.getRunningTotalFromSale();
        System.out.printf("Running Total: %.2f inc VAT \n" ,currentTotal);        
        presentOnScreen(item);
        }
        catch(DataBaseException dbe){
            handleExeption("Couldn't connect to the database", dbe);
        }
        catch(InvalidItemIdentifierException iiie){
            handleExeption("Item with that item ID cant be found", iiie);
        }
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
     * Handles when an error occurs.
     * @param msg message that will be displayed
     * @param ex error to the logHandler
     */
    private void handleExeption(String msg, Exception ex){
        errorMsgHandler.displayErrorMessage(msg);
        logger.logException(ex);
    }  
    
    
}


