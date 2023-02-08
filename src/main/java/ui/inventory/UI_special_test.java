package ui.inventory;

import client.GlobalVariables;
import contents.ItemFactory;

import java.io.IOException;
import teammember.CrewMember;

import static client.GlobalVariables.InventoryMap;
import static client.GlobalVariables.mySquad;
import static ui.inventory.UIInventory.pickUpItem;

public class UI_special_test {
    public static void main(String[] args) throws IOException {
        GlobalVariables.gameInitialization();

        for(int i=0; i<1002; i++) {

            var item = ItemFactory.createItem();

            pickUpItem(item);
        }

        for (var k : InventoryMap.keySet()) {
            System.out.println(InventoryMap.get(k).getName() + " x" + InventoryMap.get(k).getQty());
        }

        for (CrewMember c : mySquad) {
            System.out.println(c.getName() + ": " + c.getMaxHP());
        }
    }
}
