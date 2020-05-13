package se.kth.iv1350.salesprocess.integration;

/**
 * This exception is thrown when the database can't be called.
 * 
 */
public class DataBaseException extends Exception{
    
    public DataBaseException(String msg){
        super(msg);
    }
}
