package ui.inventory;

import gamecontrol.contents.Medical;

import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import static gamecontrol.GlobalVariables.InventoryMap;
import static gamecontrol.GlobalVariables.mySquad;
import static ui.inventory.UIInventory.useItems;

public class UIDisplayMedicals {
    private static final int x_axis_inventory_medical = 96;

    private static final StringBuilder outputString = new StringBuilder();

    private static void displayMedicalsHeader() {
        System.out.println("\n\n\n\n\n");

        outputString.setLength(0);
        String inv = "MEDICALS LIST";
        int invSpaceHolder = (x_axis_inventory_medical -inv.length())/2;
        outputString.append(" ".repeat(invSpaceHolder));
        outputString.append(inv);
        System.out.println(outputString);

        drawHeader();

        int rows = mySquad.size()/2 + 1;
        int spaceHolder = 16;
        int space = (x_axis_inventory_medical-spaceHolder)/2;

        for (int i = 0; i<rows; i+=2) {
            String soldierLeftName = (i+1) + ". " + mySquad.get(i).getRank() + " " + mySquad.get(i).getName();
            String soldierLeftHP = "HP: " + mySquad.get(i).getHP() + "/" + mySquad.get(i).getMaxHP();

            String soldierRightName = "";
            String soldierRightHP = "";

            if(i+1<mySquad.size()) {
                soldierRightName = (i+2) + ". " + mySquad.get(i+1).getRank() + " " + mySquad.get(i+1).getName();
                soldierRightHP = "HP: " + mySquad.get(i+1).getHP() + "/" + mySquad.get(i+1).getMaxHP();
            }

            int space1 = space - soldierLeftHP.length() - soldierLeftName.length();
            int space2 = space - soldierRightHP.length() -soldierRightName.length();

            outputString.append(soldierLeftName);
            outputString.append(" ".repeat(space1));
            outputString.append(soldierLeftHP);
            outputString.append(" ".repeat(spaceHolder));

            outputString.append(soldierRightName);
            outputString.append(" ".repeat(space2));
            outputString.append(soldierRightHP);

            System.out.println(outputString);
            outputString.setLength(0);
        }
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

            HashMap<String, Medical> medicalsMap = new HashMap<>();

            for(var k : medicals) {
                drawFooter();
                Medical meds = (Medical) k;

                medicalsMap.put(String.valueOf(i), meds);

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
                operation1 = "Choose Medical Number or ";
            }

            String operation2 = "Press 0 to Go Back";
            int last_line_space = x_axis_inventory_medical -operation1.length()-operation2.length();
            outputString.append(operation1);
            outputString.append(" ".repeat(last_line_space));
            outputString.append(operation2);

            System.out.println(outputString);
            drawHeader();

            System.out.println("Press number to continue >> ");

            Scanner s = new Scanner(System.in);
            String command1;

            while(true) {
                String userInput = s.nextLine();

                if(medicalsMap.containsKey(userInput) || userInput.equals("0")) {
                    command1 = userInput;

                    break;
                } else {
                    System.out.println("Invalid Selection!");
                }
            }

            Medical newMeds;

            if(command1.equals("0")) {
                System.out.println("\n\n\n\n\n");
                break;
            } else {
                newMeds = medicalsMap.get(command1);
            }

            String command2;

            HashMap<String, Integer> soldierMap = new HashMap<>();
            for(int j = 0; j < mySquad.size(); j++){
                soldierMap.put(String.valueOf(j+1), j);
            }

            while(true) {
                System.out.println("Choose soldier number use item, press 0 to cancel");

                String userInput = s.nextLine();

                if(soldierMap.containsKey(userInput) || userInput.equals("0")) {
                    command2 = userInput;

                    break;
                } else {
                    System.out.println("Invalid Selection!");
                }
            }

            if(!command2.equals("0")) {
                var soldier = mySquad.get(soldierMap.get(command2));

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
