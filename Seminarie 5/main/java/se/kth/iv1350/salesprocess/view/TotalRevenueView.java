package se.kth.iv1350.salesprocess.view;

import se.kth.iv1350.salesprocess.model.SaleObserver;

/**
 * Displays the total Revenue.
 * @author fredrikpettersson
 */
public class TotalRevenueView implements SaleObserver {

    @Override
    public void newPayment(double totalRevenue){
       printTotalRevenue(totalRevenue);
    }
    /**
     * Prints the total revenue.
     * @param totalRevenue 
     */
    private void printTotalRevenue(double totalRevenue){
        System.out.printf("Total Revenue: %.2f \n" , totalRevenue);
    }
 
}
