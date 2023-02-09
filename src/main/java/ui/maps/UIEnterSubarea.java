package ui.maps;

import gamemodel.combatengine.EngageEnemy;
import gamecontrol.contents.Item;
import gamemodel.mapengine.MainMap;
import java.io.IOException;
import ui.UICommandHelper;
import ui.inventory.UIInventory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import static gamecontrol.GlobalVariables.*;

public class UIEnterSubarea {

    public static final int x_axis_subArea = 96;
    private static final StringBuilder outputString = new StringBuilder();

    private static final MainMap mainMap = inGameMap;

    private static final HashMap<String, Integer> commandMap = new HashMap<>();

    private static final Scanner scanner = new Scanner(System.in);

//    private static void commandMapInitialize() {
//        commandMap.put("y", 1); //engage the enemy
//        commandMap.put("", 0); //go back
//        commandMap.put("yes", 1); //engage the enemy
//        commandMap.put("n", 0);
//        commandMap.put("i", 11);
//        commandMap.put("l", 12);
//        commandMap.put("p", 12);
//        commandMap.put("pick", 12);
//        commandMap.put("pick up", 12);
//    }

    private static boolean exit = false;

    public static void displaySubarea2() throws InterruptedException, IOException {
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
            System.out.println("Enter a command. You may engage the enemy, attempt to loot items,"
                + " or return to area overlay >> ");

            String command = s.nextLine().toLowerCase();

            if (inGameCommands.containsKey(command)) {
                commandCode = inGameCommands.get(command);
                break;
            }
            System.out.println("Invalid Command");
        }

        switch (commandCode) {
            case 21:
                enemySquad = currentSubAreaContents.getContents().enemies;
                if (enemySquad.size() > 0) {
                    EngageEnemy.gameEnginePrototype();
                } else {
                    System.out.println("Looking around, there are no zombies that want to fight.");
                }
                break;
            case 22:
                exit = true;
                break;
            case 16:
                UIInventory.displayInventoryList();
                break;
            case 17:
                UICommandHelper.showHelp();
                break;
            case 19:
                UICommandHelper.showHelpSubArea();
                break;
            case 24:
                if (currentSubAreaContents.getContents().items.size() > 0) {
                    for (Item i : currentSubAreaContents.getContents().items) {
                        UIInventory.pickUpItem(i);
                    }
                    currentSubAreaContents.getContents().items.clear();
                } else {
                    System.out.println("Rummaging around you find there's nothing of value.");
                }
                break;
        }
    }

    public static void displaySubarea() throws InterruptedException, IOException {
        exit = false;
        while (!exit) {
            displaySubarea2();
        }
    }

    private static void displaySubareaName() throws InterruptedException {
        String text = "You have entered into " + currentSubAreaContents.getName();

//        for(var c : text.toCharArray()) {
//            Thread.sleep(60);
//            System.out.print(c);
//        }

        System.out.println(text);

        currentSubAreaContents.setVisited(true);
    }

    private static void displaySubareaDescription() {
        System.out.println(currentSubAreaContents.getDescription());
    }

    private static void displaySubareaItems() {
        int itemsNumber = currentSubAreaContents.getContents().items.size();
        if (itemsNumber > 0) {
            System.out.println("You see " + itemsNumber + " item there.");
        } else{
            System.out.println("There isn't anything interested you in this place.");
        }
    }

    private static void displaySubareaEnemy() {
        int enemyPack = currentSubAreaContents.getContents().enemies.size();
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

    public static void setExit(boolean exit) {
        UIEnterSubarea.exit = exit;
    }
}
