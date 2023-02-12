package ui.maps;

import static gamecontrol.GlobalVariables.DESTINATION;
import static gamecontrol.GlobalVariables.InventoryMap;
import static gamecontrol.GlobalVariables.currentSubAreaContents;
import static gamecontrol.GlobalVariables.defeatBoss;
import static gamecontrol.GlobalVariables.inGameCommands;
import static gamecontrol.GlobalVariables.inGameMap;
import static gamecontrol.GlobalVariables.mySquad;

import gamecontrol.GlobalVariables;
import gamecontrol.contents.Weapon;
import gamemodel.mapengine.MainMap;
import ui.UICommandHelper;
import ui.endgame.UIDisplayGameStatus;
import ui.endgame.UIWinningPage;

import ui.inventory.UIInventory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class UIMainMap {

  private static final MainMap mainMap = inGameMap;

  private static final StringBuilder outputString = new StringBuilder();

  private static final HashMap<Integer, String> threatLvlMap = new HashMap<>();

  private static final Scanner scanner = new Scanner(System.in);

  public static final int x_axis_map = 95; //need to be an odd number for map display

  private static boolean exit = false;

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
    threatLvlMapInitialize();

    while (mySquad.get(0).getHP() > 0 && !defeatBoss && !exit) {
      System.out.println("\n");
      displayMapTitle();

      displayMapBody();
    }

    if (defeatBoss) {
      UIDisplayGameStatus.displayInfo(UIWinningPage.displayWinning());
    }
  }

  private static void displayMapBody() throws InterruptedException, IOException {
    displayMapContent();

    int position = mainMap.getPosition();

    var subMaps = mainMap.gameMaps.get(position);

    String s;

    exit = false;

    while (true) {
      System.out.println("Choose Your Command >> ");

      s = scanner.nextLine().toLowerCase();

      if (inGameCommands.containsKey(s)) {
        break;
      }

      System.out.println("Invalid Input");
    }

    switch (inGameCommands.get(s)) {
      case 11:
        inGameMap.goNorth();
        break;
      case 12:
        inGameMap.goSouth();
        break;
      case 13:
        inGameMap.goWest();
        break;
      case 14:
        inGameMap.goEast();
        break;
      case 15:
        //display mini map
        UIMiniMap.displayMiniMap();
        break;
      case 16:
        UIInventory.displayInventoryList();
        break;
      case 17:
        UICommandHelper.showHelp();
        break;
      case 18:
        UICommandHelper.showHelpMap();
        break;
      case -1:
        while (true) {
          System.out.println("To confirm quit, type y or n >>");

          s = scanner.nextLine().toLowerCase();

          if (!s.equals("y") && !s.equals("n")) {
            System.out.println("Invalid entry, try again.");
          } else {
            if (s.equals("y")) { exit = true; }
            break;
          }
        }
      case -2:
        Weapon AR15 = new Weapon("ar-15", 75, "rare", "A lightweight, semi-automatic rifle.");
        InventoryMap.put("ar-15", AR15);
        break;
      default:
    }

    if (inGameCommands.get(s) <= subMaps.size() && inGameCommands.get(s) >= 0) {
      currentSubAreaContents = subMaps.get(inGameCommands.get(s)-1);

      if(currentSubAreaContents.getName().equals(DESTINATION)) {
        System.out.println("This place needs password to access");
        System.out.println("Enter the password >>");
        s = scanner.nextLine();
        if(s.equals(GlobalVariables.getPassWord())) {
          UIEnterSubarea.displaySubarea(); // when this is lab
        } else {
          System.out.println("Invalid password.");
        }
      }else {
        UIEnterSubarea.displaySubarea(); //when not lab
      }
    }
  }

  private static void displayMapContent() {
    outputString.setLength(0);

    int position = mainMap.getPosition();

    var subMaps = mainMap.gameMaps.get(position);

    int rows = subMaps.size();

    for (int i = 0; i < rows; i++) {
      var subMap = subMaps.get(i);

      String line1 = (i + 1) + ". " + subMap.getName() + "\n\n";

      String threatLevel = "??????";
      String itemsInMap = "??????";

      if (subMap.getVisited()) {

        int enemyPower = 0;
        if (subMap.getContents().enemies.size() > 0) {
          enemyPower = subMap.getContents().enemies.stream().
              mapToInt(e -> e.getHP() * e.getAttack()).sum();
        }

        int myPower;
        myPower = mySquad.stream().mapToInt(e -> e.getHP() * e.getAttack()).sum();

        if (enemyPower == 0) {
          threatLevel = threatLvlMap.get(0);
        } else if (myPower * 3 / 4 >= enemyPower) {
          threatLevel = threatLvlMap.get(1) + " (" + subMap.getContents().enemies.size() + " "
              + subMap.getContents().enemies.get(0).getEnemyType() + ")";
        } else if (myPower * 5 / 4 >= enemyPower) {
          threatLevel = threatLvlMap.get(2) + " (" + subMap.getContents().enemies.size() + " "
              + subMap.getContents().enemies.get(0).getEnemyType() + ")";
        } else if (myPower * 5 / 4 < enemyPower) {
          threatLevel = threatLvlMap.get(3) + " (" + subMap.getContents().enemies.size() + " "
              + subMap.getContents().enemies.get(0).getEnemyType() + ")";
        }

        if (subMap.getContents().items.size() > 0) {
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

    String title = "AREA " + inGameMap.getPosition();
    int space = (x_axis_map - title.length()) / 2;

    outputString.append(" ".repeat(space));
    outputString.append(title);
    outputString.append(" ".repeat(space));

    System.out.println(outputString);

    drawFooter();
  }
}
