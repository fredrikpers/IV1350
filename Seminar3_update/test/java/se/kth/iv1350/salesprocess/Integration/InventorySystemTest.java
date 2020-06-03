package se.kth.iv1350.salesprocess.Integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class InventorySystemTest {
    InventorySystem inv = new InventorySystem();
    ItemDTO item;  
    
    @BeforeEach
    public void setUp() {
        item = new ItemDTO("Chocolate", 112, 8.99, 1.12, "Eat when hangry" ); 
    }
    
    @AfterEach
    public void tearDown() {
        item = null;
    }

    @Test 
    public void testGetItemFromInventorySystemWrongBarcode() { 
        boolean thrown = false;

        try {
            inv.getItemFromInventorySystem(110);
        } 
        catch (IndexOutOfBoundsException e) {
            thrown = true;
        }

        assertTrue(thrown,"Item is avaible!");
    }  

        @Test 
    public void testGetItemFromInventorySystemRightBarcode(){
        String expectedRes = inv.getItemFromInventorySystem(112).getName();
        String result = item.getName();         
        assertEquals(expectedRes, result, "Item not found");
    }
    
}    


