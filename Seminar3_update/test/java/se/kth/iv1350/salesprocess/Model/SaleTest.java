package se.kth.iv1350.salesprocess.Model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesprocess.Integration.InventorySystem;
import se.kth.iv1350.salesprocess.Integration.ItemDTO;
import se.kth.iv1350.salesprocess.Integration.Printer;

/**
 *
 * @author fredrikpettersson
 */
public class SaleTest {
    InventorySystem inv = new InventorySystem();
    ItemDTO item;
    Sale sale = new Sale();
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    
    @BeforeEach
    public void setUp() {
        item = new ItemDTO("Chocolate", 112, 8.99, 1.12, "Eat when hangry" );  
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown() {
        item = null;
        outContent = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testUpdateListOfRegisteredItems() {
        sale.updateListOfRegisteredItems(item);
        List <ItemDTO> registeredItems = sale.getRegisteredItems();
        
        String result = registeredItems.get(0).getName();
        String expResult = item.getName();
        
        assertEquals(result,expResult,"Item is missing in the sale");   
    }
    
    @Test
    public void calculateChange(){
        sale.updateRunningTotal(item.getVATRate(), item.getPrice());
        int payedAmount = 20;
        double result = sale.calculateChange(payedAmount);
        double expResult = payedAmount - sale.getRunningTotal();
        assertEquals(result,expResult,"Wrong Change");
    }
    
    @Test
    public void UpdateTotalVAT(){
        sale.UpdateTotalVAT(item.getVATRate(), item.getPrice());
        double result = sale.getTotalVAT();
        double expResult = item.getPrice()*(item.getVATRate()/10);
        assertEquals(result,expResult,"Wrong total VAT");
    }
    @Test
    public void updateRunningTotal(){
        sale.updateRunningTotal(item.getVATRate(), item.getPrice());
        double result = sale.getRunningTotal();
        double expResult = item.getPrice()*item.getVATRate();
        assertEquals(result,expResult,"Wrong total Price");
    }
    
    @Test
    public void testPrintReceipt(){
        String name = "Chocolate";
        int barcode = 112;
        double price = 8.99;
        double VATrate = 1.12;
        String itemDescription = "Eat when hangry";
        String storeName = "Freddans affär";
        String adress = "Flygaren 23";
        ItemDTO soldItem = new ItemDTO(name,barcode,price,VATrate,itemDescription);    
        Store storeInfo = new Store(storeName, adress);
        Printer print = new Printer();
        LocalDateTime timeOfSale = LocalDateTime.now();
        sale.updateRunningTotal(VATrate, price);
        sale.UpdateTotalVAT(VATrate, price);
        sale.updateListOfRegisteredItems(soldItem);
        Receipt receipt = new Receipt(sale,storeInfo);
        print.printReceipt(receipt);
        String result = outContent.toString();
        
        assertTrue(result.contains(storeName), "Wrong Store Name on receipt");
        assertTrue(result.contains(adress), "Wrong adress on receipt");
        assertTrue(result.contains(name), "Wrong item name on receipt");
        assertTrue(result.contains(Integer.toString(timeOfSale.getMonthValue())), "Wrong month on receipt");
        assertTrue(result.contains(String.valueOf(sale.getRunningTotal())), "Wrong Total price on receipt");
        assertTrue(result.contains(String.valueOf(sale.getTotalVAT())), "Wrong Total VAT on receipt");
    }
   
}
