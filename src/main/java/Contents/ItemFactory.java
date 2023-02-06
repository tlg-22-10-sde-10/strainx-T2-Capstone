package Contents;

import com.fasterxml.jackson.databind.JsonNode;
import jsonparsing.json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class ItemFactory {

    private static final String path = "src/main/resources/items.json";
    private static final String weapons = "weapons";
    private static final String medical = "medical";
    private static final String keyItems = "key items";

    public static Item createItem(String key) throws IOException, NullPointerException {
        String search_key = key.toLowerCase(Locale.ROOT);
        JsonNode itemTree = json.jsonFileReader(path);

        var items = itemTree.get("items");

        Item item = null;

        for (JsonNode weapon : items.get(weapons)) {
            if (search_key.equalsIgnoreCase(weapon.get("name").textValue())) {
                try {
                    Weapon weaponItem = new Weapon();
                    weaponItem.setName(items.get(weapons).get(search_key).get("name").textValue());
                    weaponItem.setWeapon_base_dmg(items.get(weapons).get(search_key).get("damage").intValue());
                    weaponItem.setRarity(items.get(weapons).get(search_key).get("rarity").textValue());

                    item = weaponItem;
                } catch (Exception e) {
                    if (e.getClass().equals(NullPointerException.class)) {
                        throw new NullPointerException("Double check spelling of provided key (" + search_key + ") against JSON keys");
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (JsonNode medicalItemNode : items.get(medical)) {
            if (search_key.equalsIgnoreCase(medicalItemNode.get("name").textValue())) {
                try {
                    Medical medicalItem = new Medical();
                    medicalItem.setName(items.get(medical).get(search_key).get("name").textValue());
                    medicalItem.setValue(items.get(medical).get(search_key).get("value").intValue());
                    medicalItem.setRarity(items.get(medical).get(search_key).get("rarity").textValue());

                    item = medicalItem;
                } catch (Exception e) {
                    if (e.getClass().equals(NullPointerException.class)) {
                        throw new NullPointerException("Double check spelling of provided key (" + search_key + ") against JSON keys");
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (JsonNode KI : items.get(keyItems)) {
            if (search_key.equalsIgnoreCase(KI.get("name").textValue())) {
                try {
                    KeyItem keyItem = new KeyItem();
                    keyItem.setName(items.get(keyItems).get(search_key).get("name").textValue());
                    keyItem.setHealth(items.get(keyItems).get(search_key).get("health").intValue());
                    keyItem.setDamage(items.get(keyItems).get(search_key).get("damage").intValue());
                    keyItem.setRarity(items.get(keyItems).get(search_key).get("rarity").textValue());

                    item = keyItem;
                } catch (Exception e) {
                    if (e.getClass().equals(NullPointerException.class)) {
                        throw new NullPointerException("Double check spelling of provided key (" + search_key + ") against JSON keys");
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        }

        return item;
    }

    public static String createItemName() throws IOException, NullPointerException {
        String itemName = "I have no names";

        JsonNode itemTree = json.jsonFileReader(path);

        var itemList = itemTree.get("items");

        var weaponList = itemList.get("weapons");
        var medicalList = itemList.get("medical");
        var keyItemList = itemList.get("key items");

        ArrayList<String> itemsNames = new ArrayList<>();

        for (var weapons : weaponList) {
            itemsNames.add(weapons.get("name").textValue());
        }

        for (var medicals : medicalList) {
            itemsNames.add(medicals.get("name").textValue());
        }

        for (var keyItems : keyItemList) {
            itemsNames.add(keyItems.get("name").textValue());
        }

        Random rg = new Random();

        itemName = itemsNames.get(rg.nextInt(itemsNames.size()));

        return itemName;
    }
}