package se.kth.iv1350.salesprocess.View;

import se.kth.iv1350.salesprocess.Controller.Controller;
import se.kth.iv1350.salesprocess.Integration.ItemDTO;


/**
 *
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all 
 * system operations in the controller.
 */
public class View {
    private final Controller contr;
    
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
        
        presentOnScreen((contr.scanItem(111)), contr.getRunningTotal());
        presentOnScreen((contr.scanItem(112)), contr.getRunningTotal());
        presentOnScreen((contr.scanItem(113)), contr.getRunningTotal());
        presentOnScreen((contr.scanItem(113)), contr.getRunningTotal());
       
        System.out.println("All items are scanned");
        double total = contr.endSale(); 
        System.out.printf("Total Price is: %.2f \n",total);

        double change = contr.pay(40);
        System.out.printf("Change to get back from the payment: %.2f \n", change);
        contr.printReceipt();
    }
    /**
     * Present results on the screen for the viewer.
     * @param item The <code>ItemDTO</code> of the item that is going to present on screen.
     */
    private void presentOnScreen(ItemDTO item, double RunningTotal){
        System.out.printf("Running Total: %.2f inc VAT \n" ,RunningTotal);
        System.out.println("ItemDescription: "+item.getitemDescription());
        System.out.println("Price: " +item.getPrice());
        System.out.println("");
    }
    
}