package ui;


import gamecontrol.GlobalVariables;
import gamecontrol.contents.ItemFactory;
import gamecontrol.contents.Medical;
import org.junit.Test;

import java.io.IOException;

import static gamecontrol.GlobalVariables.*;
import static ui.inventory.UIInventory.*;

public class UIInventoryTest {

    @Test
    public void pickUpItemTest() {
        for(int i=0; i<0; i++) {
            var item = ItemFactory.createItem();

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
            var item = ItemFactory.createItem();

            pickUpItem(item);
        }
    }

    @Test
    public void useItemsTest() throws IOException {
        GlobalVariables.gameInitialization();

        var weaponName = "AR-15";
        var medicalsName = "bandages";
        var keyItemsName = "body armor";

        var weapon = ItemFactory.createItem();
        var medical1 = ItemFactory.createItem();
        var medical2 = ItemFactory.createItem();
        var key = ItemFactory.createItem();

        pickUpItem(weapon);
        pickUpItem(medical1);
        pickUpItem(medical2);
        pickUpItem(key);

        for (var k : InventoryMap.keySet()) {
            System.out.println(InventoryMap.get(k).getName() + " x" + InventoryMap.get(k).getQty());
        }
        System.out.println("----------");

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