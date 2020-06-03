package se.kth.iv1350.salesprocess.Integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.salesprocess.Model.Receipt;
import se.kth.iv1350.salesprocess.Model.Sale;
import se.kth.iv1350.salesprocess.Model.Store;

public class PrinterTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUpStreams() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void cleanUpStreams() {
        outContent = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testPrintReceipt() {
       String name = "Chocolate";
        int barcode = 112;
        double price = 8.99;
        double VATrate = 1.12;
        String itemDescription = "Eat when hangry";
        String storeName = "Freddans affär";
        String adress = "Flygaren 23";
        ItemDTO soldItem = new ItemDTO(name,barcode,price,VATrate,itemDescription);    
        Store storeInfo = new Store(storeName, adress);
        Sale sale = new Sale();
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
