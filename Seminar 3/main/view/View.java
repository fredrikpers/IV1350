package se.kth.iv1350.salesprocess.view;

import se.kth.iv1350.salesprocess.controller.Controller;
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
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller.
     * 
     */
    public void runFakeExecution(){
        contr.startSale();
        System.out.println("A new sale has been started.");
        System.out.println("Scanning items...");
        
        presentOnScreen((contr.scanItem(111)));
        presentOnScreen((contr.scanItem(112)));
        presentOnScreen((contr.scanItem(113)));
        presentOnScreen((contr.scanItem(113)));
        System.out.println("All items are scanned");
        contr.endSale();        
        contr.pay(40);
        contr.printReceipt();
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
}
