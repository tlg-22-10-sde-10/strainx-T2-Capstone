package UI;

import Contents.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import static Client.GlobalVariables.*;
import static UI.UI_display_medicals.displayMedicals;
import static UI.UI_inventory_keyItems.displayKeyItems;
import static UI.UI_inventory_weapons.displayWeapons;

public class UI_inventory {

    private static int x_axis_inventory = 96;
    private static StringBuilder outputString = new StringBuilder();

    private static HashMap<Integer, Item> inventoryMap = new HashMap<>();

    private static HashMap<String, String> inventoryCommands = new HashMap();

    private static HashMap<Integer, Weapon> weaponMap = new HashMap<>();
    private static HashMap<Integer, Medical> medicalMap = new HashMap<>();
    private static HashMap<Integer, KeyItem> keyItemMap = new HashMap<>();

    private static final String COL_1 = "1. Weapons";
    private static final String COL_2 = "2. Medicals";
    private static final String COL_3 = "3. Key Items";
    private static final String COL_4 = "4. Go Back";

    private static void inventoryCommandsInitialize() {
        inventoryCommands.put("1", "CHECK WEAPONS");
        inventoryCommands.put("2", "CHECK MEDICALS");
        inventoryCommands.put("3", "CHECK KEY ITEMS");
        inventoryCommands.put("4", "GO BACK");
    }

    public static void pickUpItem(Item item) throws IOException {
        int qty= 1;

        if(InventoryMap.containsKey(item.getName())) {
            qty += InventoryMap.get(item.getName()).getQty();
        }
        item.setQty(qty);

        InventoryMap.put(item.getName(),item);

        if(item.getName().equals("body armor")){
            var key = (KeyItem) item;
            mySquad.get(0).setMaxHP(mySquad.get(0).getMaxHP() + key.getHealth());
        }
    }

    private static void drawFooter() {
        outputString.setLength(0);
        outputString.append("-".repeat(x_axis_inventory));
        System.out.println(outputString);
        outputString.setLength(0);
    }

    private static void displayHeader() {
        System.out.println("\n\n\n\n\n");
        outputString.setLength(0);
        String inv = "INVENTORY LIST";
        int invSpaceHolder = (x_axis_inventory-inv.length())/2;
        outputString.append(" ".repeat(invSpaceHolder));
        outputString.append(inv);
        System.out.println(outputString);

        drawFooter();

        String[] cols = new String[]{ COL_1, COL_2, COL_3};

        int space = (x_axis_inventory - COL_1.length() - COL_2.length() -COL_3.length()-COL_4.length())/3;

        for(int i = 0; i< cols.length; i++) {
            outputString.append(cols[i]);
            outputString.append(" ".repeat(space));
        }
        outputString.append(COL_4);

        System.out.println(outputString);
        drawFooter();
    }

    private static void displayBody() {

        var weapons = InventoryMap.values().stream()
                .filter(i->i.getClass().equals(new Weapon().getClass()))
                .collect(Collectors.toList());
        var medicals = InventoryMap.values().stream()
                .filter(i->i.getClass().equals(new Medical().getClass()))
                .collect(Collectors.toList());
        var keys = InventoryMap.values().stream()
                .filter(i->i.getClass().equals(new KeyItem().getClass()))
                .collect(Collectors.toList());

        int rows = Math.max(weapons.size(), medicals.size());
        if(rows < keys.size()) {
            rows = keys.size();
        }

        for(int i = 0; i< rows; i++){
            outputString.setLength(0);

            String col1="";
            String col2 ="";
            String col3="";

            if(i<weapons.size()) {
                col1 = weapons.get(i).getName() + " x"+weapons.get(i).getQty();
            }
            if(i<medicals.size()) {
                col2 = medicals.get(i).getName() + " x"+medicals.get(i).getQty();
            }
            if(i<keys.size()) {
                col3 = keys.get(i).getName() + " x" + keys.get(i).getQty();
            }

            int space = (x_axis_inventory - COL_1.length() - COL_2.length() -COL_3.length()-COL_4.length())/3;

            int space1 = COL_1.length() + space - col1.length();
            int space2 = COL_2.length() + space - col2.length();

            outputString.append(col1);
            outputString.append(" ".repeat(space1));
            outputString.append(col2);
            outputString.append(" ".repeat(space2));
            outputString.append(col3);
            System.out.println(outputString);
        }
        drawFooter();
    }

    public static void displayInventoryList() {
        while(true) {
            Scanner s = new Scanner(System.in);
            inventoryCommandsInitialize();

            displayHeader();

            displayBody();

            String userInput = "";

            while (true) {
                System.out.println("Choose Number Options >> ");

                userInput = s.nextLine();

                if(inventoryCommands.containsKey(userInput)) {
                    break;
                }
                System.out.println("Invalid Options");
            }

            var command = inventoryCommands.get(userInput);

            if(command.equals("GO BACK")) {
                break;
            }

            switch (command) {
                case "CHECK WEAPONS":
                    displayWeapons();
                    break;
                case "CHECK MEDICALS":
                    System.out.println("\n\n\n\n\n");
                    displayMedicals();
                    break;
                case "CHECK KEY ITEMS":
                    System.out.println("\n\n\n\n\n");
                    displayKeyItems();
                    break;
            }
        }
    }

    public static void useItems(String itemName) {
        if(InventoryMap.containsKey(itemName)) {
            var item = InventoryMap.get(itemName);

            if(item.getClass().equals(Weapon.class)) {
                mySquad.get(0).setWeapon((Weapon) item);
            }

            item.setQty(-1);

            if(item.getQty() <= 0) {
                InventoryMap.remove(itemName);
            }
        } else {
            System.out.println("item not exist");
        }
    }
}
