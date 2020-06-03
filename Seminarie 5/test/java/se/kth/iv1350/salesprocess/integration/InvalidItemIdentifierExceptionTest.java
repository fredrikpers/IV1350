package se.kth.iv1350.salesprocess.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author fredrikpettersson
 */
public class InvalidItemIdentifierExceptionTest {
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
    public void TestInvalidItemIdentifer() throws DataBaseException {
        int invalidBarcode = 110;
        String errorMessage = ("Can't find item with that item identifier " +invalidBarcode+ "");  
        try{
            invSystem.getItemFromInventorySystem(invalidBarcode);
            fail("Didn't throw error message as it should");        
        }
        catch(InvalidItemIdentifierException iiie){
           assertEquals(errorMessage, iiie.getMessage(),"Not the right message");        
        }
    }
   
    @Test
    public void TestValidItemIdentifier() throws DataBaseException {
       int ValidBarcode = 112;     
       try{
           invSystem.getItemFromInventorySystem(ValidBarcode);
       }
       catch(InvalidItemIdentifierException iiie){
           fail("Shouldn't throw exception");     
       }    
 
}

}