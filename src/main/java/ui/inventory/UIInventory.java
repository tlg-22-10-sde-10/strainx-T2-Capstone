package ui.inventory;

import contents.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import static client.GlobalVariables.*;
import static ui.inventory.UIDisplayMedicals.displayMedicals;
import static ui.inventory.UIInventoryKeyItems.displayKeyItems;
import static ui.inventory.UIInventoryWeapons.displayWeapons;

public class UIInventory {

    private static final int x_axis_inventory = 96;
    private static final StringBuilder outputString = new StringBuilder();

    private static final HashMap<String, String> inventoryCommands = new HashMap<>();

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

    public static void pickUpItem(Item item) {
        int qty= 1;

        if(InventoryMap.containsKey(item.getName())) {
            qty += InventoryMap.get(item.getName()).getQty();
        } else if (item.getClass().equals(KeyItem.class)) {
            var key = (KeyItem) item;

            switch (item.getName()) {
                case "body armor":
                    mySquad.get(0).setMaxHP(mySquad.get(0).getMaxHP() + key.getHealth());
                    break;
                case "squad equipment upgrades":
                    for (int i=1;i<mySquad.size();i++) {
                        var crew = mySquad.get(i);
                        crew.setMaxHP(crew.getMaxHP() + key.getHealth());
                        crew.setAttack(crew.getAttack() + key.getDamage());
                    }
                    break;
            }
        }

        item.setQty(qty);
        InventoryMap.put(item.getName(),item);
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

        for (var c : cols) {
            outputString.append(c);
            outputString.append(" ".repeat(space));
        }
        outputString.append(COL_4);

        System.out.println(outputString);
        drawFooter();
    }

    private static void displayBody() {

        var weapons = InventoryMap.values().stream()
                .filter(i->i.getClass().equals(Weapon.class))
                .collect(Collectors.toList());
        var medicals = InventoryMap.values().stream()
                .filter(i->i.getClass().equals(Medical.class))
                .collect(Collectors.toList());
        var keys = InventoryMap.values().stream()
                .filter(i->i.getClass().equals(KeyItem.class))
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

            String userInput;

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
                    System.out.println("\n\n\n");
                    displayMedicals();
                    break;
                case "CHECK KEY ITEMS":
                    System.out.println("\n\n\n");
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
