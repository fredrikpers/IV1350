package se.kth.iv1350.salesprocess.integration;

/**
 *
 * This class creates all systems that is included in the application
 */

public class SystemCreator {
    private AccountingSystem accountingSystem = new AccountingSystem();
    private InventorySystem inventorySystem = new InventorySystem();
    
    /**
     * Returns inventorySystem
     *
     * @return An object of type <code>InventorySystem</code>
     */
    public InventorySystem getInventorySystem()
    {
        return inventorySystem;
    }
         
    /**
    * Returns accountingSystem
    * 
    * @return An object of type <code>AccountingSystem</code>
    */
         
    public AccountingSystem getAccountingSystem()
    {
        return accountingSystem;
    }
}
