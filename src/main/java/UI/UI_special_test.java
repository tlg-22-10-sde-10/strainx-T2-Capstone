package UI;

import Client.GlobalVariables;
import Contents.ItemFactory;

import java.io.IOException;

import static Client.GlobalVariables.InventoryMap;
import static UI.UI_display_medicals.displayMedicals;
import static UI.UI_inventory.displayInventoryList;
import static UI.UI_inventory.pickUpItem;
import static UI.UI_inventory_keyItems.displayKeyItems;

public class UI_special_test {
    public static void main(String[] args) throws IOException {
        GlobalVariables.gameInitialization();

        for(int i=0; i<10; i++) {
            var name = ItemFactory.createItemName();
            var item = ItemFactory.createItem(name);

            pickUpItem(item);
        }

        for (var k : InventoryMap.keySet()) {
            System.out.println(InventoryMap.get(k).getName() + " x" + InventoryMap.get(k).getQty());
        }

        displayInventoryList();
        //displayMedicals();
    }
}
