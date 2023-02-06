package UI;

import Contents.Weapon;

import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import static Client.GlobalVariables.InventoryMap;
import static Client.GlobalVariables.mySquad;
import static UI.UI_inventory.displayInventoryList;

public class UI_inventory_keyItems {
    private static int x_axis_inventory_keyItem = 96;
    private static final StringBuilder outputString = new StringBuilder();

    public static void displayKeyItem() {
        while (true) {
            System.out.println("\n\n\n\n\n");

            outputString.setLength(0);
            String inv = "KEY ITEM LIST";
            int invSpaceHolder = (x_axis_inventory_keyItem -inv.length())/2;
            outputString.append(" ".repeat(invSpaceHolder));
            outputString.append(inv);
            System.out.println(outputString);

            drawHeader();

            String currentWeapon = "Current Weapon: " + mySquad.get(0).getWeapon().getName();
            String currentWeaponDmg = "Base Damage: " + mySquad.get(0).getWeapon().getWeapon_base_dmg();


            int line1_space = x_axis_inventory_keyItem - currentWeapon.length() - currentWeaponDmg.length();

            outputString.append(currentWeapon);
            outputString.append(" ".repeat(line1_space));
            outputString.append(currentWeaponDmg);
            System.out.println(outputString);

            var weapons = InventoryMap.values().stream()
                    .filter(i->i.getClass().equals(new Weapon().getClass()))
                    .collect(Collectors.toList());

            int i = 1;
            HashMap<String, Weapon> weaponsMap = new HashMap<>();

            for(var k : weapons) {
                drawFooter();
                Weapon weapon = (Weapon) k;

                weaponsMap.put(String.valueOf(i), weapon);

                String weaponName = i + ". " + weapon.getName();
                String weaponBaseDMG = "Base Damage: " + weapon.getWeapon_base_dmg();

                int line_space = (x_axis_inventory_keyItem - weaponBaseDMG.length() - weaponName.length());

                outputString.append(weaponName);
                outputString.append(" ".repeat(line_space));
                outputString.append(weaponBaseDMG);
                outputString.append("\n");
                outputString.append(weapon.getWeapon_description());

                System.out.println(outputString);

                i++;
            }
            drawHeader();

            String operation1 = "";

            if(weapons.size() > 0) {
                operation1 = "Press Weapon Number to Equip.";
            }

            String operation2 = "Press 0 to Go Back";
            int last_line_space = x_axis_inventory_keyItem -operation1.length()-operation2.length();
            outputString.append(operation1);
            outputString.append(" ".repeat(last_line_space));
            outputString.append(operation2);

            System.out.println(outputString);
            drawHeader();

            System.out.println("Press number to continue >> ");

            Scanner s = new Scanner(System.in);
            String command;

            while(true) {
                String userInput = s.nextLine();

                if(weaponsMap.containsKey(userInput) || userInput.equals("0")) {
                    command = userInput;

                    break;
                } else {
                    System.out.println("Invalid Selection!");
                }
            }

            if(command.equals("0")) {
                System.out.println("\n\n\n\n\n");
                displayInventoryList();
                break;
            } else {
                var newWeapon = weaponsMap.get(command);
                mySquad.get(0).setWeapon(newWeapon);

                System.out.println("\n\n\n\n\n");
            }
        }
    }

    private static void drawFooter() {
        outputString.setLength(0);
        outputString.append("-".repeat(x_axis_inventory_keyItem));
        System.out.println(outputString);
        outputString.setLength(0);
    }

    private static void drawHeader() {
        outputString.setLength(0);
        outputString.append("=".repeat(x_axis_inventory_keyItem));
        System.out.println(outputString);
        outputString.setLength(0);
    }
}
