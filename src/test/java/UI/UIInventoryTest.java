package UI;


import Client.GlobalVariables;
import Contents.ItemFactory;
import Contents.Medical;
import org.junit.Test;

import java.io.IOException;

import static Client.GlobalVariables.*;
import static UI.Inventory.UI_inventory.*;

public class UIInventoryTest {

    @Test
    public void pickUpItemTest() throws IOException {
        for(int i=0; i<100; i++) {
            var name = ItemFactory.createItemName();
            var item = ItemFactory.createItem(name);

            if(item.getClass().equals(Medical.class)) {
                pickUpItem(item);
            }
        }

        for(var c : InventoryMap.keySet()) {
            System.out.println(( InventoryMap.get(c)).getQty());
        }
    }

    @Test
    public void displayInventoryListTest() throws IOException {
        GlobalVariables.gameInitialization();

        for(int i=0; i<10; i++) {
            var name = ItemFactory.createItemName();
            var item = ItemFactory.createItem(name);

            pickUpItem(item);
        }

        displayInventoryList();
    }

    @Test
    public void useItemsTest() throws IOException {
        var weaponName = "AR-15";
        var medicalsName = "bandages";
        var keyItemsName = "body armor";

        var weapon = ItemFactory.createItem(weaponName);
        var medical1 = ItemFactory.createItem(medicalsName);
        var medical2 = ItemFactory.createItem(medicalsName);
        var key = ItemFactory.createItem(keyItemsName);

        pickUpItem(weapon);
        pickUpItem(medical1);
        pickUpItem(medical2);
        pickUpItem(key);

        for (var k : InventoryMap.keySet()) {
            System.out.println(InventoryMap.get(k).getName() + " x" + InventoryMap.get(k).getQty());
        }
        System.out.println("----------");

        GlobalVariables.gameInitialization();

        System.out.println(GlobalVariables.mySquad.get(0).getWeapon().getName());
        System.out.println("----------");
        System.out.println("use Items");

        useItems(weaponName);
        useItems(medicalsName);
        useItems(medicalsName);
        useItems(keyItemsName);

        for (var k : InventoryMap.keySet()) {
            System.out.println(InventoryMap.get(k).getName() + " x" + InventoryMap.get(k).getQty());
        }
        System.out.println("----------");

        System.out.println(GlobalVariables.mySquad.get(0).getWeapon().getName());
        System.out.println("----------");
    }
}