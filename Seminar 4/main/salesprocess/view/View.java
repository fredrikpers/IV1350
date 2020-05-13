package se.kth.iv1350.salesprocess.view;

import se.kth.iv1350.salesprocess.controller.Controller;
import se.kth.iv1350.salesprocess.integration.DataBaseException;
import se.kth.iv1350.salesprocess.integration.InvalidItemIdentifierException;
import se.kth.iv1350.salesprocess.integration.ItemDTO;

/**
 *
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all 
 * system operations in the controller.
 */
public class View {
    private Controller contr;
    
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    
    public View(Controller contr){
        this.contr = contr;
        contr.addSalesObserver(new TotalRevenueView());
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller.
     * 
     */
    public void runFakeExecution() throws DataBaseException,InvalidItemIdentifierException{
        contr.startSale();
        System.out.println("A new sale has been started.");
        System.out.println("Scanning items...");

        contr.tryItem(111); 
        contr.tryItem(112);
        contr.tryItem(113);
        contr.tryItem(113);
        contr.tryItem(110); 
        contr.tryItem(25);
        
        System.out.println("All items are scanned");
        contr.endSale();        
        contr.pay(40);
        contr.printing();
    }

}
