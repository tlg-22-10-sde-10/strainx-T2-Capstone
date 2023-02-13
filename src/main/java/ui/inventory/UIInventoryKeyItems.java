package ui.inventory;

import contents.KeyItem;

import java.util.Scanner;
import java.util.stream.Collectors;

import static client.GlobalVariables.InventoryMap;

public class UIInventoryKeyItems {
    private static final int x_axis_inventory_keyItem = 96;
    private static final StringBuilder outputString = new StringBuilder();

    public static void displayKeyItems() {
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

        while(true) {
            String userInput = s.nextLine();

            if(userInput.equals("0")) {
                break;
            } else {
                System.out.println("Invalid Selection!");
            }
        }
        System.out.println("\n\n\n\n\n");
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
