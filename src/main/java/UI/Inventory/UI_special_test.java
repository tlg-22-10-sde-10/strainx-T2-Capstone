package UI.Inventory;

import Client.GlobalVariables;
import Contents.ItemFactory;

import java.io.IOException;

import static Client.GlobalVariables.InventoryMap;
import static Client.GlobalVariables.mySquad;
import static UI.Inventory.UI_inventory.pickUpItem;

public class UI_special_test {
    public static void main(String[] args) throws IOException {
        GlobalVariables.gameInitialization();

        for(int i=0; i<1002; i++) {
            var name = ItemFactory.createItemName();
            var item = ItemFactory.createItem(name);

            pickUpItem(item);
        }

        for (var k : InventoryMap.keySet()) {
            System.out.println(InventoryMap.get(k).getName() + " x" + InventoryMap.get(k).getQty());
        }

        mySquad.stream().forEach(c-> System.out.println(c.getName() + ": " + c.getMaxHP()));
    }
}
