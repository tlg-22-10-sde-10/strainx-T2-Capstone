package UI;


import Client.GlobalVariables;
import Contents.ItemFactory;
import org.junit.Test;

import java.io.IOException;

import static Client.GlobalVariables.*;
import static UI.UI_inventory.*;
import static UI.UI_inventory_weapons.displayWeapons;

public class UIInventoryTest {

    @Test
    public void pickUpItemTest() throws IOException {
        for(int i=0; i<10; i++) {
            var name = ItemFactory.createItemName();
            var item = ItemFactory.createItem(name);

            pickUpItem(item);
        }

        for (var k : InventoryMap.keySet()) {
            System.out.println(InventoryMap.get(k).getName() + " x" + InventoryMap.get(k).getQty());
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

        //displayWeapons();
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