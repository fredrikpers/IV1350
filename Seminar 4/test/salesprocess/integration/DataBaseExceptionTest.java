package se.kth.iv1350.salesprocess.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the exception when the database can't be reached.
 * 
 */
public class DataBaseExceptionTest {
    
    private InventorySystem invSystem;
    
    @BeforeEach
    public void setUp() {
        invSystem = new InventorySystem();
    }
    
    @AfterEach
    public void tearDown() {
        invSystem = null;
    }

    @Test
    public void DataBaseThrown() throws InvalidItemIdentifierException {
       int testValueBarcode = 25;
       String errorMessage = "Data base can't be reached!";
       try{
           invSystem.getItemFromInventorySystem(testValueBarcode);
           fail("Barcode didn't throw error");       
       }
       catch(DataBaseException dbe){
           assertEquals(errorMessage, dbe.getMessage(),"Not the right message");
       }
    }    
    @Test
    public void DataBaseNotThrown() throws InvalidItemIdentifierException {
       int testValueBarcode = 112;
       try{
           invSystem.getItemFromInventorySystem(testValueBarcode);
       }
       catch(DataBaseException dbe){
           fail("Shouldn't throw exception");       
       }
    }
}
