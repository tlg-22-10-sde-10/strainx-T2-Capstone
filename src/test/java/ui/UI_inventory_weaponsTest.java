package ui;

import client.GlobalVariables;
import contents.ItemFactory;
import org.junit.Test;

import java.io.IOException;

import static client.GlobalVariables.InventoryMap;
import static ui.inventory.UIInventory.pickUpItem;

public class UI_inventory_weaponsTest {

    @Test
    public void displayWeapons() throws IOException {
        GlobalVariables.gameInitialization();

        for(int i=0; i<10; i++) {
            var item = ItemFactory.createItem();

            pickUpItem(item);
        }

        for (var k : InventoryMap.keySet()) {
            System.out.println(InventoryMap.get(k).getName() + " x" + InventoryMap.get(k).getQty());
        }

//        UI_inventory_weapons.displayWeapons();
    }
}