package se.kth.iv1350.salesprocess.startup;

import se.kth.iv1350.salesprocess.controller.Controller;
import se.kth.iv1350.salesprocess.integration.DataBaseException;
import se.kth.iv1350.salesprocess.integration.InvalidItemIdentifierException;
import se.kth.iv1350.salesprocess.integration.Printer;
import se.kth.iv1350.salesprocess.integration.SystemCreator;
import se.kth.iv1350.salesprocess.model.Store;
import se.kth.iv1350.salesprocess.view.View;

/**
 *
 * Contains the main method to start the entire application.
 */
public class Main {
    
    /**
     * The main method used to start the entire application
     *      *
     * @param args The application doesn't take any command lines.
     */        
    public static void main(String[] args) throws DataBaseException, InvalidItemIdentifierException {
        SystemCreator creator = new SystemCreator();
        Printer printer = new Printer();
        Store storeInfo = new Store("Freddans affär ","Flygaren 23");
        Controller contr = new Controller(printer, creator, storeInfo);
        View view = new View(contr);
        view.runFakeExecution();        
    }
}
 