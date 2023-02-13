package ui.inventory;

import gamecontrol.contents.Medical;
import gamemodel.combatengine.UICombat;
import java.util.Scanner;
import java.util.stream.Collectors;

import static gamecontrol.GlobalVariables.InventoryMap;
import static gamecontrol.GlobalVariables.inGameCommands;
import static gamecontrol.GlobalVariables.mySquad;
import static ui.inventory.UIInventory.useItems;

public class UIDisplayMedicals {
    private static final int x_axis_inventory_medical = 96;
    private static final StringBuilder outputString = new StringBuilder();

    private static void displayMedicalsHeader() {
        outputString.setLength(0);
        String inv = "MEDICALS LIST";
        int invSpaceHolder = (x_axis_inventory_medical -inv.length())/2;
        outputString.append(" ".repeat(invSpaceHolder));
        outputString.append(inv);
        System.out.println(outputString);

        drawHeader();

        UICombat.reportMySquadStatus();
    }

    public static void displayMedicals() {
        while (true) {
            displayMedicalsHeader();

            drawHeader();

            var medicals = InventoryMap.values().stream()
                    .filter(i->i.getClass().equals(Medical.class))
                    .collect(Collectors.toList());

            int i = 1;

            int space = x_axis_inventory_medical*7/16;

            for(var k : medicals) {
                drawFooter();
                Medical meds = (Medical) k;

                String name = i + ". " + meds.getName();
                String heals = "Heal Points: " + meds.getValue();
                String qty = "QTY: " + meds.getQty();

                int space1 = space - name.length();
                int space2 = x_axis_inventory_medical - qty.length() - space1 - heals.length()-name.length();

                outputString.append(name);
                outputString.append(" ".repeat(space1));
                outputString.append(heals);
                outputString.append(" ".repeat(space2));
                outputString.append(qty);
                outputString.append("\n");
                outputString.append(meds.getDescription());

                System.out.println(outputString);

                i++;
            }
            drawHeader();

            String operation1 = "";

            if(medicals.size() > 0) {
                operation1 = "Choose Medical Item you want to use";
            }

            String operation2 = "Press 0 or enter to Go Back";
            int last_line_space = x_axis_inventory_medical -operation1.length()-operation2.length();
            outputString.append(operation1);
            outputString.append(" ".repeat(last_line_space));
            outputString.append(operation2);

            System.out.println(outputString);
            drawHeader();

            System.out.println("Press key to continue >> ");

            Scanner s = new Scanner(System.in);

            int thisCommandCode;

            Medical newMeds;

            while(true) {
                String userInput = s.nextLine();

                if(inGameCommands.containsKey(userInput)) {
                    if((inGameCommands.get(userInput) >= 0 && inGameCommands.get(userInput) <= medicals.size()) || userInput.equals("")) {
                        thisCommandCode = inGameCommands.get(userInput);
                        break;
                    }
                }
                System.out.println("Invalid Selection!");
            }

            if(thisCommandCode == 0 || thisCommandCode == 22) {
                break;
            } else {
                newMeds = (Medical) medicals.get(thisCommandCode-1);
            }

            while(true) {
                System.out.println("Choose soldier number use item, press 0 or enter to cancel");

                String userInput = s.nextLine();

                if(inGameCommands.containsKey(userInput)) {
                    if((inGameCommands.get(userInput) >= 0 && inGameCommands.get(userInput) <= mySquad.size()) || userInput.equals("")) {
                        thisCommandCode = inGameCommands.get(userInput);
                        break;
                    }
                }
                System.out.println("Invalid Selection!");
            }

            if(thisCommandCode>0 && thisCommandCode!=22) {
                var soldier = mySquad.get(thisCommandCode-1);

                int HP = Math.min(soldier.getMaxHP(), soldier.getHP() + newMeds.getValue());
                soldier.setHP(HP);

                useItems(newMeds.getName());
            }
        }
    }

    private static void drawFooter() {
        outputString.setLength(0);
        outputString.append("-".repeat(x_axis_inventory_medical));
        System.out.println(outputString);
        outputString.setLength(0);
    }

    private static void drawHeader() {
        outputString.setLength(0);
        outputString.append("=".repeat(x_axis_inventory_medical));
        System.out.println(outputString);
        outputString.setLength(0);
    }
}
