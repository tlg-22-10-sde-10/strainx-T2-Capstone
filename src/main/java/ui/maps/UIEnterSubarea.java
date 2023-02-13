package ui.maps;

import gamemodel.combatengine.EngageEnemy;
import gamecontrol.contents.Item;

import ui.UICommandHelper;
import ui.inventory.UIInventory;

import java.util.Scanner;

import static gamecontrol.GlobalVariables.*;

public class UIEnterSubarea {

    public static final int x_axis_subArea = 96;
    private static final StringBuilder outputString = new StringBuilder();

    private static final Scanner scanner = new Scanner(System.in);

    private static boolean exitSubAreaUI = false;

    private static void drawSubArea() throws InterruptedException {
        System.out.println("\n");

        drawHeader();

        displaySubareaName();

        drawHeader();

        displaySubareaDescription();

        drawHeader();

        displaySubareaItems();

        drawFooter();

        displaySubareaEnemy();

        drawFooter();

        int commandCode;

        while(true) {
            System.out.println("Enter a command. You may engage the enemy, loot items,"
                + " or return to area overlay >> ");

            String command = scanner.nextLine().toLowerCase();

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
                exitSubAreaUI = true;
                break;
            case 16:
                UIInventory.displayInventoryList();
                break;
            case 17:
                UICommandHelper.showHelp();
                break;
            case 19:
                UICommandHelper.showHelpSubArea();

                System.out.println("Press any key to continue >>");
                UIEnterSubarea.scanner.nextLine();
                break;
            case 24:
                if (currentSubAreaContents.getContents().enemies.size() == 0) {
                    if (currentSubAreaContents.getContents().items.size() > 0) {
                        for (Item i : currentSubAreaContents.getContents().items) {
                            UIInventory.pickUpItem(i);
                            outputString.append(i.getName());
                            outputString.append(", ");
                        }

                        outputString.append("added to your inventory.");
                        System.out.println(outputString);
                        currentSubAreaContents.getContents().items.clear();
                    } else {
                        System.out.println("Rummaging around you find there's nothing of value.");
                    }
                } else {
                    System.out.println("You may not loot the area with enemies present!");
                }
                UIEnterSubarea.scanner.nextLine();
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

    public static void displaySubarea() throws InterruptedException {
        exitSubAreaUI = false;

        while (!exitSubAreaUI && mySquad.get(0).getHP()>0 && !defeatBoss) {
            drawSubArea();
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
        var threatLvl = UIEnterMainMap.displayThreatLvl(currentSubAreaContents);
        if (enemyPack > 0) {
            System.out.println("You see " + enemyPack + " zombies walking around. Threat lvl: " + threatLvl) ;
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

    public static void setExitSubAreaUI(boolean exitSubAreaUI) {
        UIEnterSubarea.exitSubAreaUI = exitSubAreaUI;
    }
}
