package ui.inventory;

import gamecontrol.contents.Weapon;
import java.util.Scanner;
import java.util.stream.Collectors;

import static gamecontrol.GlobalVariables.*;

public class UIInventoryWeapons {
    private static final int x_axis_inventory_weapon = 96;
    private static final StringBuilder outputString = new StringBuilder();

    public static void displayWeapons() {
        while (true) {
            outputString.setLength(0);
            String inv = "WEAPON LIST";
            int invSpaceHolder = (x_axis_inventory_weapon-inv.length())/2;
            outputString.append(" ".repeat(invSpaceHolder));
            outputString.append(inv);
            System.out.println(outputString);

            drawHeader();

            String currentWeapon = "Current Weapon: " + mySquad.get(0).getWeapon().getName();
            String currentWeaponDmg = "Base Damage: " + mySquad.get(0).getWeapon().getWeapon_base_dmg();

            int line1_space = x_axis_inventory_weapon - currentWeapon.length() - currentWeaponDmg.length();

            outputString.append(currentWeapon);
            outputString.append(" ".repeat(line1_space));
            outputString.append(currentWeaponDmg);
            System.out.println(outputString);
            var weapons = InventoryMap.values().stream()
                    .filter(i->i.getClass().equals(Weapon.class))
                    .collect(Collectors.toList());

            int i = 1;
            for(var k : weapons) {
                drawFooter();
                Weapon weapon = (Weapon) k;

                String weaponName = i + ". " + weapon.getName();
                String weaponBaseDMG = "Base Damage: " + weapon.getWeapon_base_dmg();

                int line_space = (x_axis_inventory_weapon - weaponBaseDMG.length() - weaponName.length());

                outputString.append(weaponName);
                outputString.append(" ".repeat(line_space));
                outputString.append(weaponBaseDMG);
                outputString.append("\n\n");
                outputString.append(weapon.getDescription());

                System.out.println(outputString);

                i++;
            }
            drawHeader();

            String operation1 = "";

            if(weapons.size() > 0) {
                operation1 = "Press Weapon Number to Equip.";
            }

            String operation2 = "Press 0 or enter to Go Back";
            int last_line_space = x_axis_inventory_weapon-operation1.length()-operation2.length();
            outputString.append(operation1);
            outputString.append(" ".repeat(last_line_space));
            outputString.append(operation2);

            System.out.println(outputString);
            drawHeader();

            System.out.println("Press key to continue >> ");

            Scanner s = new Scanner(System.in);
            int thisCommandCode;
            while(true) {
                String userInput = s.nextLine().toLowerCase();

                if(inGameCommands.containsKey(userInput)) {
                    if((inGameCommands.get(userInput) >=0 && inGameCommands.get(userInput)<=weapons.size()) || userInput.equals("")) {
                        thisCommandCode = inGameCommands.get(userInput);
                        break;
                    }
                }
                System.out.println("Invalid Selection!");
            }
            if(thisCommandCode == 0 || thisCommandCode == 22) {
                break;
            } else {
                Weapon newWeapon = (Weapon) weapons.get(thisCommandCode-1);
                mySquad.get(0).setWeapon(newWeapon);

                System.out.println("You are equipped with " + "\033[36m" + newWeapon.getName() + "\33[0m now");
                System.out.println("Press any key to continue...");
                s.nextLine();
            }
        }
        System.out.println("\n\n");
    }

    private static void drawFooter() {
        outputString.setLength(0);
        outputString.append("-".repeat(x_axis_inventory_weapon));
        System.out.println(outputString);
        outputString.setLength(0);
    }

    private static void drawHeader() {
        outputString.setLength(0);
        outputString.append("=".repeat(x_axis_inventory_weapon));
        System.out.println(outputString);
        outputString.setLength(0);
    }
}