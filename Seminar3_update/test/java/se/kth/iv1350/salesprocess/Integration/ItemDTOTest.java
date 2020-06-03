package se.kth.iv1350.salesprocess.Integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemDTOTest {
     private ItemDTO itemDTO;
    
    @BeforeEach
    public void setUp() {
        itemDTO = new ItemDTO("banana", 101, 12.3, 1.12, "Yellow bended thing");
    }
    
    @AfterEach
    public void tearDown() {
        itemDTO = null;
    }

    @Test
    public void testGetItemIdentifier() {
        int expResult = 101;
        int result = itemDTO.getItemIdentifier();
        assertEquals(expResult, result,"Item Identifier is wrong.");

    }

    @Test
    public void testGetName() {
        String expResult = "banana";
        String result = itemDTO.getName();
        assertEquals(expResult, result,"Name wasn't Banana");

    }

    @Test
    public void testGetPrice() {
        double expResult = 12.3;
        double result = itemDTO.getPrice();
        assertEquals(expResult, result,"Price wasn't 12.3");
    }

    @Test
    public void testGetVATRate() {
        double expResult = 1.12;
        double result = itemDTO.getVATRate();
        assertEquals(expResult, result,"VAT is not equal");
    }

    @Test
    public void testGetitemDescription() {
        String expResult = "Yellow bended thing";
        String result = itemDTO.getitemDescription();
        assertEquals(expResult, result,"Item Desciption doesn't match");
    }
    
}
