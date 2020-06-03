package se.kth.iv1350.salesprocess.Controller;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesprocess.Integration.ItemDTO;
import se.kth.iv1350.salesprocess.Integration.Printer;
import se.kth.iv1350.salesprocess.Integration.SystemCreator;
import se.kth.iv1350.salesprocess.Model.Sale;
import se.kth.iv1350.salesprocess.Model.Store;

/**
 *
 * @author fredrikpettersson
 */
public class ControllerTest {
    Store store = new Store("Freddans affär","Flygaren 23");
    private SystemCreator systemCreator;
    private Controller contr;
    Sale sale = new Sale();

    @BeforeEach
    public void setUp() {
        Printer print = new Printer();
        systemCreator = new SystemCreator();
        Store store = new Store("Freddans affär","Flygaren 23");
        contr = new Controller(print,systemCreator,store);
        contr.startSale();
    }
    
    @AfterEach
    public void tearDown() {
        contr = null;
        
    }

    @Test
    public void testScanItemRegisteringItem() {
        ItemDTO scannedItem = contr.scanItem(111);
        ItemDTO testItem = new ItemDTO("Toilet Paper", 111, 12.90, 1.12, "Use after toilet visit");
        String result = scannedItem.getName();
        String expResult = testItem.getName();
        
        assertEquals(result,expResult,"Item is missing in the sale");     
    }
    
    public void testScanItemRegisteringItemsToSale() {
        ItemDTO scannedItem = contr.scanItem(111);
        List <ItemDTO> registeredItems = sale.getRegisteredItems();
        String result = scannedItem.getName();
        String expResult = registeredItems.get(0).getName() ;
        
        assertEquals(result,expResult,"Item wasn't added to registeredItems");     
    }
    
    public void testScanItemRegisteringItemsToSaleUpdateRunningTotal() {
        ItemDTO scannedItem = contr.scanItem(111);
        double runningTotal = sale.getRunningTotal();
        double result = scannedItem.getPrice()*scannedItem.getVATRate();
        double expResult = runningTotal;
        
        assertEquals(result,expResult,"Didn't update runningTotal correctly");     
    }
    
    public void testScanItemRegisteringItemsToSaleUpdateTotalVAT() {
        ItemDTO scannedItem = contr.scanItem(111);
        double runningVAT = sale.getTotalVAT();
        double result = scannedItem.getPrice()*(scannedItem.getVATRate()/10);
        double expResult = runningVAT;
        
        assertEquals(result,expResult,"Didn't update Total VAT correctly");     
    }
    
    public void testpay(){
        ItemDTO scannedItem = contr.scanItem(111);
        int paidAmount = 20;
        double change = contr.pay(paidAmount);        
        double result = change;
        double expResult = paidAmount - (scannedItem.getPrice()*scannedItem.getVATRate());
        
        assertEquals(result,expResult,"The change is wrong!");     

    }
}
