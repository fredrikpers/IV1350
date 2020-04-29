package se.kth.iv1350.salesprocess.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class SystemCreatorTest {
    SystemCreator systemCreator;

    @BeforeEach
    void setUp() {
        systemCreator = new SystemCreator();
    }

    @AfterEach
    void tearDown() {
        systemCreator = null;
    }
    
     @Test
    void getInventorySystem() {
        InventorySystem inventorySystem = systemCreator.getInventorySystem();
        boolean result = inventorySystem instanceof InventorySystem;
        boolean expResult = true;
        assertEquals(result, expResult, "Couldn't create Inventory System");
    }
    
    @Test
    void getAccountingSystem() {
        AccountingSystem accountingSystem = systemCreator.getAccountingSystem();
        boolean result = accountingSystem instanceof AccountingSystem;
        boolean expResult = true;
        assertEquals(result, expResult, "Couldn't create Accounting System");
    }
        
    @Test
    public void testCreateInventorySystem(){
        SystemCreator instance = new SystemCreator();
        InventorySystem result = instance.getInventorySystem();
        assertTrue(result instanceof InventorySystem, "SystemCreator didn't create InventorySystem"); 
    }
    @Test
    public void testCreateAccountingSystem(){
        SystemCreator instance = new SystemCreator();
        AccountingSystem result = instance.getAccountingSystem();
        assertTrue(result instanceof AccountingSystem, "SystemCreator didn't create AccountingSystem"); 
    }
    
    
}
