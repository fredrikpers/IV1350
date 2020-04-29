package se.kth.iv1350.salesprocess.model;

/**
 *  Class that contains information about the physical store.
 * 
 */
public class Store {
    private final String name;
    private final String address;    
    /**
     * 
     * @param name String that contains the name of the store;
     * @param address String that holds info about the address of the store.
     */
    public Store(String name, String address){
        this.name = name;
        this.address = address;
    }
    /**
     * Gets the name of the store.
     * @return 
     */
    public String getName(){
        return this.name;
    }
    /**
     * Gets the address of the store.
     * @return 
     */
    public String getAddress(){
        return this.address;
    }
}
