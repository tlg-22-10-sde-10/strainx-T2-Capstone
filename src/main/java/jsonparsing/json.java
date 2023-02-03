package jsonparsing;

import GameMap.SubAreaFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class json {

    private static ObjectMapper objectMapper = getDefaultMapper();

    private static ObjectMapper getDefaultMapper() {
        ObjectMapper defaultMapper = new ObjectMapper();

        defaultMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return defaultMapper;
    }

    public static JsonNode jsonFileReader(String path) throws IOException {
        return objectMapper.readTree(new File(path));
    }

    public static void main(String[] args) throws IOException {

//        ArrayList<Item> itemList = new ArrayList();
//        String[] weaponsArray = {"tire iron", "axe", "handgun", "AR-15", "m249", "big Bertha"};
//        String[] medicalArray = {"bandages", "antibiotics"};
//        for (String weapon : weaponsArray) {
//            Item weaponToPickup = ItemFactory.createItem(weapon);
//            itemList.add(weaponToPickup);
//        }
//        for (int i = 0; i < itemList.size(); i++) {
//            System.out.println(itemList.get(i).getName());
//        }
//
//        Item item = ItemFactory.createItem(medicalArray[0]);
//        System.out.println(item.getName());
//        Item body_armor = ItemFactory.createItem("body armor");
//        System.out.println(body_armor.getName());

        SubAreaFactory.subAreaHashMap();

//        Weapon pickedUpWeapon = WeaponFactory.createWeapon("tire iron");
//        System.out.println(pickedUpWeapon.getName());
//        itemList.add(pickedUpWeapon);
//        System.out.println(itemList.get(0).getName());

//        Weapon weapon1 = new Weapon();
//        weapon1.setName(itemTree.get("items").get("weapons").get(0).get("name").textValue());
//        System.out.println(weapon1.getName());
//        itemList.add(weapon1);
//        System.out.println(itemList.get(0).getName());

    }

}
