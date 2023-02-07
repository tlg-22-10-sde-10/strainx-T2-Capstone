package UI.Maps;

import Client.GlobalVariables;
import GameMap.Main_Map;
import UI.Inventory.UI_inventory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import static Client.GlobalVariables.*;

public class UI_main_map {
    private static final Main_Map mainMap = gameMap;

    private static final StringBuilder outputString = new StringBuilder();

    private static final HashMap<String, Integer> commandMap = new HashMap<>();

    private static final HashMap<Integer, String> threatLvlMap = new HashMap<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static final int x_axis_map = 95; //need to be an odd number for map display

    //public static final int y_axis_map = 13; //need to be an odd number for map display

    private static void commandInitialize() {
        commandMap.put("w", 8);
        commandMap.put("s", 5);
        commandMap.put("a", 4);
        commandMap.put("d", 6);
        commandMap.put("i", 11);
    }

    private static void threatLvlMapInitialize() {
        threatLvlMap.put(3, "\033[31mHigh\33[0m");
        threatLvlMap.put(2, "\033[33mMedium\33[0m");
        threatLvlMap.put(1, "\033[32mLow\33[0m");
        threatLvlMap.put(0, "\033[34mSafe\33[0m");
    }

    private static void drawFooter() {
        outputString.setLength(0);
        outputString.append("-".repeat(x_axis_map));
        System.out.println(outputString);
        outputString.setLength(0);
    }

    public static void displayMainMapUI() throws IOException, InterruptedException {
        commandInitialize();
        threatLvlMapInitialize();

        while(mySquad.get(0).getHP()>0) {
            System.out.println("\n\n");
            displayMapTitle();

            displayMapBody();
        }
    }

    private static void displayMapBody() throws InterruptedException {
        displayMapContent();

        int position = mainMap.getPosition();

        var subMaps = mainMap.Game_Maps.get(position);

        String s;
        while(true) {
            System.out.println("Choose Your Command >> ");

            s = scanner.nextLine().toLowerCase();

            if(commandMap.containsKey(s)) {
                break;
            }

            System.out.println("Invalid Input");
        }

        if(commandMap.get(s) > 3) {
            switch (commandMap.get(s)){
                case 8:
                    gameMap.go_North();
                    break;
                case 5:
                    gameMap.go_South();
                    break;
                case 4:
                    gameMap.go_West();
                    break;
                case 6:
                    gameMap.go_East();
                    break;
                case 11:
                    UI_inventory.displayInventoryList();
                    break;
                default:
                    break;
            }
        } else {
            current_subArea =  subMaps.get(commandMap.get(s));

            UI_enter_subarea.display_subarea();
        }
    }

    private static void displayMapContent() {
        outputString.setLength(0);

        int position = mainMap.getPosition();

        var subMaps = mainMap.Game_Maps.get(position);

        int rows = subMaps.size();

        for (int i = 0; i< rows; i++) {
            commandMap.put(String.valueOf(i+1), i);

            var subMap = subMaps.get(i);

            String line1 = (i+1) + ". " + subMap.getName() + "\n\n";

            String threatLevel= "??????";
            String itemsInMap = "??????";

            if(subMap.getVisited()) {

                int enemyPower = 0;
                if(subMap.getContents().enemies.size()>0) {
                    enemyPower = subMap.getContents().enemies.stream().
                            mapToInt(e -> e.getHP() * e.getAttack()).sum();
                }

                int myPower;
                myPower = mySquad.stream().mapToInt(e->e.getHP()*e.getAttack()).sum();

                if(enemyPower == 0) {
                    threatLevel = "\033[34mSafe\33[0m";
                } else if (myPower * 3/4 >= enemyPower) {
                    threatLevel = "\033[32mLow\33[0m" + " (" + subMap.getContents().enemies.size() + " "+ subMap.getContents().enemies.get(0).getEnemy_type() + ")";
                } else if (myPower * 5/4 >= enemyPower) {
                    threatLevel = "\033[33mMedium\33[0m" + " (" + subMap.getContents().enemies.size() + " "+ subMap.getContents().enemies.get(0).getEnemy_type()+ ")";
                } else if (myPower * 5/4 < enemyPower) {
                    threatLevel = "\033[31mHigh\33[0m" + " (" + subMap.getContents().enemies.size() + " "+ subMap.getContents().enemies.get(0).getEnemy_type()+ ")";
                }

                if(subMap.getContents().items.size()>0) {
                    itemsInMap = String.valueOf(subMap.getContents().items.size());
                } else {
                    itemsInMap = "None";
                }
            }

            String line2 = "Threat level: " + threatLevel + "\n";
            String line3 = "Items inside: " + itemsInMap;

            outputString.append(line1);
            outputString.append(line2);
            outputString.append(line3);

            System.out.println(outputString);
            drawFooter();
        }
    }

    private static void displayMapTitle() {
        drawFooter();

        String title = "AREA " + gameMap.getPosition();
        int space = (x_axis_map - title.length())/2;

        outputString.append(" ".repeat(space));
        outputString.append(title);
        outputString.append(" ".repeat(space));

        System.out.println(outputString);

        drawFooter();
    }
}
