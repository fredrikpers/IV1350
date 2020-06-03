package se.kth.iv1350.salesprocess.model;

/**
 * A listener interface for recieving information about the amount payed.
 * 
 * @author fredrikpettersson
 */
public interface SaleObserver {
    /**
     * When the sale has been payed this method is changed to display for the listeners.
     * @param totalRevenue The amount paid
     */
    void newPayment(double totalRevenue);    
}
