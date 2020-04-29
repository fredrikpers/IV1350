package se.kth.iv1350.salesprocess.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory System that that holds the available items.
 * @author fredrikpettersson
 */
public class InventorySystem {
    private List<ItemDTO> itemList = new ArrayList<>();
    
    InventorySystem(){
        addItems();
    }
    /**
     * Adds all items in the store to an arraylist
     */
    private void addItems(){
        itemList.add(new ItemDTO("Toilet Paper", 111, 12.90, 1.12, "Use after toilet visit"));
        itemList.add(new ItemDTO("Chocolate", 112, 8.99, 1.12, "Eat when hangry" ));
        itemList.add(new ItemDTO("Tomatoes", 113, 4.99, 1.12, "Delicious in a salad" ));
        itemList.add(new ItemDTO("Milk", 114, 6.99, 1.12, "Drink when you are thirsty" ));
        itemList.add(new ItemDTO("Pasta", 115, 2.99, 1.12, "Italian delicacy" ));
    }
    /**
     * Gets an item from the arraylist of available items
     * @param barcode the barcode of an item
     * @return returns a new item as an ItemDTO.
     */
    public ItemDTO getItemFromInventorySystem(int barcode){
        int x = 0;
        while(itemList.get(x).getItemIdentifier() != barcode){
            x++;
        }
        ItemDTO item = itemList.get(x);
        return new ItemDTO(item.getName(), item.getItemIdentifier(), item.getPrice(), item.getVATRate(), item.getitemDescription());
        
    }    
    /**
     * Updates the inventory System after a finished sale.
     */
    public void updateInventorySystem(){}
    
}
