package UI;

import Contents.KeyItem;

import java.util.Scanner;
import java.util.stream.Collectors;

import static Client.GlobalVariables.InventoryMap;
import static UI.UI_inventory.displayInventoryList;

public class UI_inventory_keyItems {
    private static int x_axis_inventory_keyItem = 96;
    private static final StringBuilder outputString = new StringBuilder();

    public static void displayKeyItems() {
        while (true) {
            System.out.println("\n\n\n\n\n");

            outputString.setLength(0);
            String inv = "KEY ITEM LIST";
            int invSpaceHolder = (x_axis_inventory_keyItem -inv.length())/2;
            outputString.append(" ".repeat(invSpaceHolder));
            outputString.append(inv);
            System.out.println(outputString);

            drawHeader();

            var keyItems = InventoryMap.values().stream()
                    .filter(i->i.getClass().equals(KeyItem.class))
                    .collect(Collectors.toList());

            int i = 1;

            for(var k : keyItems) {
                drawFooter();
                KeyItem keys = (KeyItem) k;

                String name = i + ". " + keys.getName() + "\n";
                String DMGBonus = "Damage Bonus: " + keys.getDamage()+ "\n";
                String HPBonus = "MAX HP Bonus: " + keys.getHealth()+ "\n";

                outputString.append(name);
                outputString.append(DMGBonus);
                outputString.append(HPBonus);
                outputString.append(keys.getDescription());

                System.out.println(outputString);

                i++;
            }
            drawHeader();

            String operation2 = "Press 0 to Go Back";
            int last_line_space = x_axis_inventory_keyItem -operation2.length();
            outputString.append(" ".repeat(last_line_space));
            outputString.append(operation2);

            System.out.println(outputString);
            drawHeader();

            System.out.println("Press number to continue >> ");

            Scanner s = new Scanner(System.in);
            String command;

            while(true) {
                String userInput = s.nextLine();

                if(userInput.equals("0")) {
                    command = userInput;

                    break;
                } else {
                    System.out.println("Invalid Selection!");
                }
            }

            if(command.equals("0")) {
                System.out.println("\n\n\n\n\n");

                break;
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
