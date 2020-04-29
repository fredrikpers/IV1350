package se.kth.iv1350.salesprocess.integration;

/**
 * Class that holds all information needed for all items in the store
 * 
 */
public class ItemDTO {
    private final String name;
    private final int itemIdentifier;
    private final double price;
    private final double VATRate;
    private final String itemDescription;
   
    /**
     * Creates an <code>ItemDTO</code> instance.
     * 
     * @param itemIdentifier The itemIdentifer of the item.
     * @param name The name of the item.
     * @param price The price of the item.
     * @param VATRate The VAT Rate of the item.
     * @param itemDescription A description of the item.
     */
    public ItemDTO(String name, int itemIdentifier, double price, double VATRate, String itemDescription){
        this.name = name;
        this.itemIdentifier = itemIdentifier;
        this.price = price;
        this.VATRate = VATRate;
        this.itemDescription = itemDescription;
    }    
    
        /**
         * Returns the current items itemIdentifier
         * 
         * @return The return value as an integer.
         */
        public int getItemIdentifier(){
            return this.itemIdentifier;
        }    

        /**
         * Returns the current items name
         * 
         * @return The name as a String. 
         */
        public String getName(){
            return this.name;
        }

        /**
         * Returns the price of the item.
         * 
         * @return Return value as an double. 
         */
        public double getPrice(){
            return this.price;
        }

        /**
         * Returns the VATRate of the item.
         * 
         * @return Return value as an double.
         */
        public double getVATRate(){
            return this.VATRate;
        }

        /**
         * Returns the item Description of the item.
         * 
         * @return Return value as a String. 
         */
        public String getitemDescription(){
            return this.itemDescription;
        }        
}
