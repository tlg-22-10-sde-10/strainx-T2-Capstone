package gamecontrol.contents;

import static gamecontrol.GlobalVariables.inventory;

import java.util.ArrayList;
import java.util.Random;

public class ItemFactory {

  //using reflection
  public static Item createItem() throws NullPointerException {
    Item item;

    var weaponMap = inventory.getWeapons();
    var medicalMap = inventory.getMedicalItem();
    var keyItemMap = inventory.getKeyItems();

    Random rg = new Random();
    int r = rg.nextInt(3);

    if (r == 0) {
      item = new ArrayList<>(weaponMap.values()).get(rg.nextInt(weaponMap.size()));
    } else if (r == 1) {
      item = new ArrayList<>(medicalMap.values()).get(rg.nextInt(medicalMap.size()));
    } else {
      item = new ArrayList<>(keyItemMap.values()).get(rg.nextInt(keyItemMap.size()));
    }
    return item;
  }

  public static Item createItem(String itemName) throws NullPointerException {
    Item item = null;

    var weaponMap = inventory.getWeapons();
    var medicalMap = inventory.getMedicalItem();
    var keyItemMap = inventory.getKeyItems();

    if (weaponMap.containsKey(itemName)) {
      item = weaponMap.get(itemName);
    } else if (medicalMap.containsKey(itemName)) {
      item = medicalMap.get(itemName);
    } else if (keyItemMap.containsKey(itemName)) {
      item = keyItemMap.get(itemName);
    }

    return item;
  }
}