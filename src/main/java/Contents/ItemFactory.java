package Contents;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jsonparsing.json;
import java.io.IOException;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class ItemFactory {

    private static final String path = "src/main/resources/items.json";
    private static final String weapons = "weapons";
    private static final String medical = "medical";
    private static final String keyItems = "key items";

    public static Item createItem(String key) throws IOException, NullPointerException {
        String search_key = key.toLowerCase();
        JsonNode itemTree = json.jsonFileReader(path);

        Item item = null;

        for (JsonNode weapon : itemTree.get("items").get("weapons")) {
            if (search_key.equals(weapon.get("name").textValue())) {
                try {
                    Weapon weaponItem = new Weapon();
                    weaponItem.setName(itemTree.get("items").get(weapons).get(search_key).get("name").textValue());
                    weaponItem.setDamage(itemTree.get("items").get(weapons).get(search_key).get("damage").intValue());
                    weaponItem.setRarity(itemTree.get("items").get(weapons).get(search_key).get("rarity").textValue());

                    item = weaponItem;
                } catch (Exception e) {
                    if (e.getClass().equals(NullPointerException.class)) {
                        throw new NullPointerException("Double check spelling of provided key (" + key + ") against JSON keys");
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (JsonNode medicalItemNode : itemTree.get("items").get("medical")) {
            if (search_key.equals(medicalItemNode.get("name").textValue())) {
                try {
                    Medical medicalItem = new Medical();
                    medicalItem.setName(itemTree.get("items").get(medical).get(search_key).get("name").textValue());
                    medicalItem.setValue(itemTree.get("items").get(medical).get(search_key).get("value").intValue());
                    medicalItem.setRarity(itemTree.get("items").get(medical).get(search_key).get("rarity").textValue());

                    item = medicalItem;
                } catch (Exception e) {
                    if (e.getClass().equals(NullPointerException.class)) {
                        throw new NullPointerException("Double check spelling of provided key (" + key + ") against JSON keys");
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (JsonNode KI : itemTree.get("items").get("key items")) {
            if (search_key.equals(KI.get("name").textValue())) {
                try {
                    KeyItem keyItem = new KeyItem();
                    keyItem.setName(itemTree.get("items").get(keyItems).get(search_key).get("name").textValue());
                    keyItem.setHealth(itemTree.get("items").get(keyItems).get(search_key).get("health").intValue());
                    keyItem.setDamage(itemTree.get("items").get(keyItems).get(search_key).get("damage").intValue());
                    keyItem.setRarity(itemTree.get("items").get(keyItems).get(search_key).get("rarity").textValue());

                    item = keyItem;
                } catch (Exception e) {
                    if (e.getClass().equals(NullPointerException.class)) {
                        throw new NullPointerException("Double check spelling of provided key (" + key + ") against JSON keys");
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        }

        return item;
        //throw new IllegalArgumentException("key not found: " + key);
    }

    public static Item createItem() throws IOException, NullPointerException {
        String search_key = "";
        Item item = null;

        JsonNode itemTree = json.jsonFileReader(path);

        var itemList = itemTree.get("items");

        var weaponList = itemList.get("weapons");
        var medicalList = itemList.get("medical");
        var keyItemList = itemList.get("key items");

        Random rg = new Random();
        int situation = rg.nextInt(itemList.size());

        ObjectMapper mapper = new ObjectMapper();
        var idk = mapper.readTree(String.valueOf(itemList));

        var superList = itemList.get(0);

        System.out.println(idk);


//        for (JsonNode weapon : itemTree.get("items").get("weapons")) {
//            if (search_key.equals(weapon.get("name").textValue())) {
//                try {
//                    Weapon weaponItem = new Weapon();
//                    weaponItem.setName(itemTree.get("items").get(weapons).get(search_key).get("name").textValue());
//                    weaponItem.setDamage(itemTree.get("items").get(weapons).get(search_key).get("damage").intValue());
//                    weaponItem.setRarity(itemTree.get("items").get(weapons).get(search_key).get("rarity").textValue());
//
//                    item = weaponItem;
//                } catch (Exception e) {
//                    if (e.getClass().equals(NullPointerException.class)) {
//                        throw new NullPointerException("Double check spelling of provided key (" + key + ") against JSON keys");
//                    } else {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//        for (JsonNode medicalItemNode : itemTree.get("items").get("medical")) {
//            if (search_key.equals(medicalItemNode.get("name").textValue())) {
//                try {
//                    Medical medicalItem = new Medical();
//                    medicalItem.setName(itemTree.get("items").get(medical).get(search_key).get("name").textValue());
//                    medicalItem.setValue(itemTree.get("items").get(medical).get(search_key).get("value").intValue());
//                    medicalItem.setRarity(itemTree.get("items").get(medical).get(search_key).get("rarity").textValue());
//
//                    item = medicalItem;
//                } catch (Exception e) {
//                    if (e.getClass().equals(NullPointerException.class)) {
//                        throw new NullPointerException("Double check spelling of provided key (" + key + ") against JSON keys");
//                    } else {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//        for (JsonNode KI : itemTree.get("items").get("key items")) {
//            if (search_key.equals(KI.get("name").textValue())) {
//                try {
//                    KeyItem keyItem = new KeyItem();
//                    keyItem.setName(itemTree.get("items").get(keyItems).get(search_key).get("name").textValue());
//                    keyItem.setHealth(itemTree.get("items").get(keyItems).get(search_key).get("health").intValue());
//                    keyItem.setDamage(itemTree.get("items").get(keyItems).get(search_key).get("damage").intValue());
//                    keyItem.setRarity(itemTree.get("items").get(keyItems).get(search_key).get("rarity").textValue());
//
//                    item = keyItem;
//                } catch (Exception e) {
//                    if (e.getClass().equals(NullPointerException.class)) {
//                        throw new NullPointerException("Double check spelling of provided key (" + key + ") against JSON keys");
//                    } else {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }

        return item;
        //throw new IllegalArgumentException("key not found: " + key);
    }
}