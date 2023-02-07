package UI.Maps;

import CombatEngine.UI_combat;
import CombatEngine.engage_enemy;
import GameMap.Main_Map;
import UI.Inventory.UI_inventory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import static Client.GlobalVariables.*;

public class UI_enter_subarea {

    private static final int x_axis_subArea = 96;
    private static final StringBuilder outputString = new StringBuilder();

    private static Main_Map mainMap = gameMap;

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

    public static void display_subarea() throws InterruptedException {
        commandMapInitialize();

        System.out.println("\n\n\n");

        drawHeader();

        display_subarea_name();

        drawHeader();

        display_subarea_description();

        drawHeader();

        display_subarea_items();
        drawFooter();
        display_subarea_enemy();

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
                    engage_enemy.gameEnginePrototype();
                } else {
                    System.out.println("You search the place, and no zombies spot.");
                }
                break;
            case 0:
                break;
            case 11:
                UI_inventory.displayInventoryList();
                break;
            case 12:
                if (current_subArea.getContents().items.size() > 0) {
                    current_subArea.getContents().items.stream()
                            .forEach(i -> UI_inventory.pickUpItem(i));
                    current_subArea.getContents().items.clear();
                } else {
                    System.out.println("You tried to find if there are any hidden items, but nothing is there.");
                }
                break;
        }
    }

    private static void display_subarea_name() throws InterruptedException {
        String text = "You have entered into " + current_subArea.getName();

//        for(var c : text.toCharArray()) {
//            Thread.sleep(60);
//            System.out.print(c);
//        }
        System.out.println(text);

        current_subArea.setVisited(true);
    }

    private static void display_subarea_description() throws InterruptedException {
        String des = current_subArea.getDescription();

        while(des.length() >= x_axis_subArea) {
            int index = x_axis_subArea;
            while (des.charAt(index-1) != ' ') {
                index--;
            }
            String line = des.substring(0, index);
            des = des.substring(index);
            System.out.println(line);
        }
        System.out.println(des);
    }

    private static void display_subarea_items() {
        int itemsNumber = current_subArea.getContents().items.size();
        if (itemsNumber > 0) {
            System.out.println("You see " + itemsNumber + " item there.");
        } else{
            System.out.println("There isn't anything interested you in this place.");
        }
    }

    private static void display_subarea_enemy() {
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
