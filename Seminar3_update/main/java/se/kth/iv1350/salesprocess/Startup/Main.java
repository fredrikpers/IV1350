package se.kth.iv1350.salesprocess.Startup;

import se.kth.iv1350.salesprocess.Controller.Controller;
import se.kth.iv1350.salesprocess.Integration.Printer;
import se.kth.iv1350.salesprocess.Integration.SystemCreator;
import se.kth.iv1350.salesprocess.Model.Store;
import se.kth.iv1350.salesprocess.View.View;

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
    public static void main(String[] args) {
        SystemCreator creator = new SystemCreator();
        Printer printer = new Printer();
        Store storeInfo = new Store("Freddans affär ","Flygaren 23");
        Controller contr = new Controller(printer, creator, storeInfo);
        View view = new View(contr);
        view.runFakeExecution();        
    }
}