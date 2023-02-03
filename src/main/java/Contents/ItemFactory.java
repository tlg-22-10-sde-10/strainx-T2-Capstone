package Contents;

import com.fasterxml.jackson.databind.JsonNode;
import jsonparsing.json;
import java.io.IOException;

public class ItemFactory {

    private static final String path = "src/main/resources/items.json";

    private static final String weaponTerm1 = "tire iron";
    private static final String weaponTerm2 = "axe";
    private static final String weaponTerm3 = "handgun";
    private static final String weaponTerm4 = "ar-15";
    private static final String weaponTerm5 = "m249";
    private static final String weaponTerm6 = "big bertha";

    private static final String medicalTerm1 = "bandages";
    private static final String medicalTerm2 = "antibiotics";
    private static final String medicalTerm3 = "first aid kit";

    private static final String keyItemTerm1 = "body armor";
    private static final String keyItemTerm2 = "squad equipment upgrade";

    private static final String[] weaponsArray = {weaponTerm1, weaponTerm2, weaponTerm3, weaponTerm4, weaponTerm5, weaponTerm6};
    private static final String[] medicalArray = {medicalTerm1, medicalTerm2, medicalTerm3};
    private static final String[] keyItemArray = {keyItemTerm1, keyItemTerm2};

    public static Item createItem(String key) throws IOException, NullPointerException {
        String search_key = key.toLowerCase();
        JsonNode itemTree = json.jsonFileReader(path);

        for (String weapon : weaponsArray) {
            if (search_key.equals(weapon)) {
                try {
                    Weapon weaponItem = new Weapon();
                    weaponItem.setName(itemTree.get("items").get("weapons").get(search_key).get("name").textValue());

                    return weaponItem;
                } catch (Exception e) {
                    throw new NullPointerException("Double check spelling of provided key (" + key + ") against JSON keys");
                }
            }
        }

        for (String medical : medicalArray) {
            if (search_key.equals(medical)) {
                try {
                    Medical medicalItem = new Medical();
                    medicalItem.setName(itemTree.get("items").get("medical").get(search_key).get("name").textValue());

                    return medicalItem;
                } catch (Exception e) {
                    throw new NullPointerException("Double check spelling of provided key (" + key + ") against JSON keys");
                }
            }
        }

        for (String KI : keyItemArray) {
            if (search_key.equals(KI)) {
                try {
                    KeyItem keyItem = new KeyItem();
                    keyItem.setName(itemTree.get("items").get("key items").get(search_key).get("name").textValue());

                    return keyItem;
                } catch (Exception e) {
                    throw new NullPointerException("Double check spelling of provided key (" + key + ") against JSON keys");
                }
            }
        }
        throw new IllegalArgumentException("key not found: " + key);
    }

}
