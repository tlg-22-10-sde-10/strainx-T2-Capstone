package contents;

import static client.GlobalVariables.inventory;

import java.io.IOException;
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
}