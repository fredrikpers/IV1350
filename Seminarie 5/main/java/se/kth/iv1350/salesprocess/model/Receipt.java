package se.kth.iv1350.salesprocess.model;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.salesprocess.integration.ItemDTO;

/**
 * Class that is creating the receipt
 * @author fredrikpettersson
 */
public class Receipt {
    private Sale sale;
    private Store storeInfo;
    private List <ItemDTO> registeredItems = new ArrayList<ItemDTO>(); 
    private double runningTotal;

/**
 * Instance to create the receipt
 * @param sale gets the information needed from sale class
 * @param storeInfo gets the information need from store class
 */
    Receipt(Sale sale, Store storeInfo){
        this.sale = sale;
        this.registeredItems = sale.getRegisteredItems();
        this.storeInfo = storeInfo;
        this.runningTotal = sale.getRunningTotal();

    }
    /**
     * Creates the receipt
     * 
     * @return Returns the finished receipt as a string 
     */
    public String createReceiptString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("RECEIPT\n");
        
        sb.append("Store Name:\n" +storeInfo.getName()+ "\n");
        sb.append("Store address:\n" +storeInfo.getAddress()+ "\n");
        
        for(ItemDTO item: registeredItems){
            sb.append("Name: " +item.getName()+ "\n");
            sb.append("Price: " +item.getPrice()+ "\n"); 
            sb.append("Quantity: " +sale.getQuantity(item.getItemIdentifier())+ "\n");
        }
              
        sb.append("Time of sale: " +sale.getTimeOfSale()+ "\n");
        sb.append("Total Price: " +sale.getRunningTotal()+ "\n");        
        sb.append("Total VAT: "  +sale.getTotalVAT()+ "\n");
                
        return sb.toString();
    }

    
    
    
}
