package Contents;

import com.fasterxml.jackson.databind.JsonNode;
import jsonparsing.json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class ItemFactory {

    private static final String PATH = "src/main/resources/items.json";
    private static final String WEAPONS = "weapons";
    private static final String MEDICAL = "medical";
    private static final String KEY_ITEMS = "key items";

//    public static Item createItem(String key) throws IOException, NullPointerException {
//        String search_key = key.toLowerCase(Locale.ROOT);
//        JsonNode itemTree = json.jsonFileReader(PATH);
//
//        var items = itemTree.get("items");
//
//        Item item = null;
//
//        for (JsonNode weapon : items.get(WEAPONS)) {
//            if (search_key.equalsIgnoreCase(weapon.get("name").textValue())) {
//                try {
//                    Weapon weaponItem = new Weapon();
//                    weaponItem.setName(items.get(WEAPONS).get(search_key).get("name").textValue());
//                    weaponItem.setWeapon_base_dmg(items.get(WEAPONS).get(search_key).get("damage").intValue());
//                    weaponItem.setRarity(items.get(WEAPONS).get(search_key).get("rarity").textValue());
//
//                    item = weaponItem;
//                    break;
//                } catch (Exception e) {
//                    if (e.getClass().equals(NullPointerException.class)) {
//                        throw new NullPointerException("Double check spelling of provided key (" + search_key + ") against JSON keys");
//                    } else {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//        for (JsonNode medicalItemNode : items.get(MEDICAL)) {
//            if (search_key.equalsIgnoreCase(medicalItemNode.get("name").textValue())) {
//                try {
//                    Medical medicalItem = new Medical();
//                    medicalItem.setName(items.get(MEDICAL).get(search_key).get("name").textValue());
//                    medicalItem.setValue(items.get(MEDICAL).get(search_key).get("value").intValue());
//                    medicalItem.setRarity(items.get(MEDICAL).get(search_key).get("rarity").textValue());
//
//                    item = medicalItem;
//                    break;
//                } catch (Exception e) {
//                    if (e.getClass().equals(NullPointerException.class)) {
//                        throw new NullPointerException("Double check spelling of provided key (" + search_key + ") against JSON keys");
//                    } else {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//        for (JsonNode KI : items.get(KEY_ITEMS)) {
//            if (search_key.equalsIgnoreCase(KI.get("name").textValue())) {
//                try {
//                    KeyItem keyItem = new KeyItem();
//                    keyItem.setName(items.get(KEY_ITEMS).get(search_key).get("name").textValue());
//                    keyItem.setHealth(items.get(KEY_ITEMS).get(search_key).get("health").intValue());
//                    keyItem.setDamage(items.get(KEY_ITEMS).get(search_key).get("damage").intValue());
//                    keyItem.setRarity(items.get(KEY_ITEMS).get(search_key).get("rarity").textValue());
//
//                    item = keyItem;
//                    break;
//                } catch (Exception e) {
//                    if (e.getClass().equals(NullPointerException.class)) {
//                        throw new NullPointerException("Double check spelling of provided key (" + search_key + ") against JSON keys");
//                    } else {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//        return item;
//    }

    public static String createItemName() throws IOException, NullPointerException {
        String itemName;

        JsonNode itemTree = json.jsonFileReader(PATH);

        var itemList = itemTree.get("items");

        var weaponList = itemList.get("weapons");
        var medicalList = itemList.get("medical");
        var keyItemList = itemList.get("key items");

        ArrayList<String> weaponNames = new ArrayList<>();
        ArrayList<String> medicalNames = new ArrayList<>();
        ArrayList<String> keyItemNames = new ArrayList<>();

        for (var weapons : weaponList) {
            weaponNames.add(weapons.get("name").textValue());
        }

        for (var medicals : medicalList) {
            medicalNames.add(medicals.get("name").textValue());
        }

        for (var keyItems : keyItemList) {
            keyItemNames.add(keyItems.get("name").textValue());
        }

        Random rg = new Random();
        int r = rg.nextInt(3);

        if(r == 0) {
            itemName = weaponNames.get(rg.nextInt(weaponNames.size()));
        } else if (r == 1) {
            itemName = medicalNames.get(rg.nextInt(medicalNames.size()));
        } else {
            itemName = keyItemNames.get(rg.nextInt(keyItemNames.size()));
        }

        return itemName;
    }

    public static Item createItem(String key) throws IOException, NullPointerException {
        String search_key = key.toLowerCase();

        JsonNode itemTree = json.jsonFileReader(PATH);

        var items = itemTree.get("items");
        Item item = null;

        try {
            var weaponNode = items.get(WEAPONS).get(search_key);
            if(weaponNode!=null) {
                Weapon weapon = new Weapon();

                weapon.setName(weaponNode.get("name").textValue());
                weapon.setWeapon_base_dmg(weaponNode.get("damage").intValue());
                weapon.setRarity(weaponNode.get("rarity").textValue());
                //weapon.setDescription(weaponNode.get("description").textValue());

                item = weapon;
            }
        } catch (Exception e) {
            if (e.getClass().equals(NullPointerException.class)) {
                throw new NullPointerException("Double check spelling of provided key (" + search_key + ") against JSON keys");
            } else {
                e.printStackTrace();
            }
        }

        try {
            var medicalNode = items.get(MEDICAL).get(search_key);
            if(medicalNode!=null) {
                Medical medical = new Medical();

                medical.setName(medicalNode.get("name").textValue());
                medical.setRarity(medicalNode.get("rarity").textValue());
                medical.setValue(medicalNode.get("value").intValue());
                //medical.setDescription(medicalNode.get("description").textValue());

                item = medical;
            }
        } catch (Exception e) {
            if (e.getClass().equals(NullPointerException.class)) {
                throw new NullPointerException("Double check spelling of provided key (" + search_key + ") against JSON keys");
            } else {
                e.printStackTrace();
            }
        }

        try {
            var keyNode = items.get(KEY_ITEMS).get(search_key);
            if(keyNode!=null) {
                KeyItem keyItem = new KeyItem();

                keyItem.setName(keyNode.get("name").textValue());
                keyItem.setRarity(keyNode.get("rarity").textValue());
                keyItem.setDamage(keyNode.get("damage").intValue());
                keyItem.setHealth(keyNode.get("health").intValue());
                //keyItem.setDescription(keyNode.get("description").textValue());

                item = keyItem;
            }
        } catch (Exception e) {
            if (e.getClass().equals(NullPointerException.class)) {
                throw new NullPointerException("Double check spelling of provided key (" + search_key + ") against JSON keys");
            } else {
                e.printStackTrace();
            }
        }
        return item;
    }
}