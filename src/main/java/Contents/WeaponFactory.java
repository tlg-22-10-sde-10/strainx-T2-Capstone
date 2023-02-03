package Contents;

import com.fasterxml.jackson.databind.JsonNode;
import jsonparsing.json;
import java.io.IOException;
import java.util.Iterator;

public class WeaponFactory {

    private static final String path = "src/main/resources/items2.json";
    private static final String term1 = "tire iron";
    private static final String term2 = "axe";
    private static final String term3 = "handgun";
    private static final String term4 = "ar-15";
    private static final String term5 = "m249";
    private static final String term6 = "big bertha";

    public static Weapon createWeapon(String key) throws IOException {
        String search_key = key.toLowerCase();
        JsonNode itemTree = json.jsonFileReader(path);

        if (term1.equals(search_key)) {
            Weapon tireIron = new Weapon();
            tireIron.setName(itemTree.get("items").get("weapons").get(search_key).get("name").textValue());

            return tireIron;
        } else if (term2.equals(search_key)) {
            Weapon axe = new Weapon();
            axe.setName(itemTree.get("items").get("weapons").get(search_key).get("name").textValue());

            return axe;
        } else if (term3.equals(search_key)) {
            Weapon handgun = new Weapon();
            handgun.setName(itemTree.get("items").get("weapons").get(search_key).get("name").textValue());

            return handgun;
        } else if (term4.equals(search_key)) {
            Weapon ar15 = new Weapon();
            ar15.setName(itemTree.get("items").get("weapons").get(search_key).get("name").textValue());

            return ar15;
        } else if (term5.equals(search_key)) {
            Weapon m249 = new Weapon();
            m249.setName(itemTree.get("items").get("weapons").get(search_key).get("name").textValue());

            return m249;
        } else if (term6.equals(search_key)) {
            Weapon bigBertha = new Weapon();
            bigBertha.setName(itemTree.get("items").get("weapons").get(search_key).get("name").textValue());

            return bigBertha;
        }
        throw new IllegalArgumentException("Unknown type:" + key);
    }

}
