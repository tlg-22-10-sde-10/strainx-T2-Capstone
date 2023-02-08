package ui.maps;

import combatengine.EngageEnemy;
import contents.Item;
import gamemapengine.MainMap;
import ui.inventory.UIInventory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import static client.GlobalVariables.*;

public class UIEnterSubarea {

    public static final int x_axis_subArea = 96;
    private static final StringBuilder outputString = new StringBuilder();

    private static final MainMap mainMap = gameMap;

    private static final HashMap<String, Integer> commandMap = new HashMap<>();

    private static final Scanner scanner = new Scanner(System.in);

    private static void commandMapInitialize() {
        commandMap.put("y", 1);
        commandMap.put("", 0);
        commandMap.put("yes", 1);
        commandMap.put("n", 0);
        commandMap.put("i", 11);
        commandMap.put("l", 12);
        commandMap.put("p", 12);
        commandMap.put("pick", 12);
        commandMap.put("pick up", 12);
    }

    public static void displaySubarea() throws InterruptedException {
        commandMapInitialize();

        System.out.println("\n\n\n");

        drawHeader();

        displaySubareaName();

        drawHeader();

        displaySubareaDescription();

        drawHeader();

        displaySubareaItems();
        drawFooter();
        displaySubareaEnemy();

        drawFooter();

        Scanner s = new Scanner(System.in);

        int commandCode;

        while(true) {
            System.out.println("Please Give Command >> ");

            String command = s.nextLine().toLowerCase(Locale.ROOT);

            if (commandMap.containsKey(command)) {
                commandCode = commandMap.get(command);
                break;
            }
            System.out.println("Invalid Command");
        }

        switch (commandCode) {
            case 1:
                enemySquad = current_subArea.getContents().enemies;
                if (enemySquad.size() > 0) {
                    EngageEnemy.gameEnginePrototype();
                } else {
                    System.out.println("You search the place, and no zombies spot.");
                }
                break;
            case 0:
                break;
            case 11:
                UIInventory.displayInventoryList();
                break;
            case 12:
                if (current_subArea.getContents().items.size() > 0) {
                    for (Item i : current_subArea.getContents().items) {
                        UIInventory.pickUpItem(i);
                    }
                    current_subArea.getContents().items.clear();
                } else {
                    System.out.println("You tried to find if there are any hidden items, but nothing is there.");
                }
                break;
        }
    }

    private static void displaySubareaName() throws InterruptedException {
        String text = "You have entered into " + current_subArea.getName();

//        for(var c : text.toCharArray()) {
//            Thread.sleep(60);
//            System.out.print(c);
//        }

        System.out.println(text);

        current_subArea.setVisited(true);
    }

    private static void displaySubareaDescription() {
        System.out.println(current_subArea.getDescription());
    }

    private static void displaySubareaItems() {
        int itemsNumber = current_subArea.getContents().items.size();
        if (itemsNumber > 0) {
            System.out.println("You see " + itemsNumber + " item there.");
        } else{
            System.out.println("There isn't anything interested you in this place.");
        }
    }

    private static void displaySubareaEnemy() {
        int enemyPack = current_subArea.getContents().enemies.size();
        if (enemyPack > 0) {
            System.out.println("You see " + enemyPack + " zombies walking around.");
        } else {
            System.out.println("This place looks safe, at least for now.");
        }
    }

    private static void drawFooter() {
        outputString.setLength(0);
        outputString.append("-".repeat(x_axis_subArea));
        System.out.println(outputString);
        outputString.setLength(0);
    }

    private static void drawHeader() {
        outputString.setLength(0);
        outputString.append("*".repeat(x_axis_subArea));
        System.out.println(outputString);
        outputString.setLength(0);
    }
}
