package ui.maps;

import static client.GlobalVariables.current_subArea;
import static client.GlobalVariables.gameMap;
import static client.GlobalVariables.mySquad;

import gamemap.MainMap;
import ui.endgame.UIIntroBlurb;
import ui.inventory.UIInventory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class UIMainMap {

  private static final MainMap mainMap = gameMap;

  private static final StringBuilder outputString = new StringBuilder();

  private static final HashMap<String, Integer> commandMap = new HashMap<>();

  private static final HashMap<Integer, String> threatLvlMap = new HashMap<>();

  private static final Scanner scanner = new Scanner(System.in);

  public static final int x_axis_map = 95; //need to be an odd number for map display

  //public static final int y_axis_map = 13; //need to be an odd number for map display

  private static boolean exit = false;

  private static void commandInitialize() {
    /* avoid give command code from 1-10 because this command map will add command codes when playing the game*/
    commandMap.put("w", 18); //Go North
    commandMap.put("s", 15); //Go South
    commandMap.put("a", 14); //Go East
    commandMap.put("d", 16); //Go West
    commandMap.put("i", 11); //Inventory
    commandMap.put("m", 12); //mini map
    commandMap.put("e", -1); //exit game
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
    UIIntroBlurb.displayIntro();
    commandInitialize();
    threatLvlMapInitialize();

    while (mySquad.get(0).getHP() > 0) {
      System.out.println("\n\n");
      displayMapTitle();

      displayMapBody();
      if (exit) {
        break;
      }
    }
  }

  private static void displayMapBody() throws InterruptedException, IOException {
    displayMapContent();

    int position = mainMap.getPosition();

    var subMaps = mainMap.Game_Maps.get(position);

    String s;

    exit = false;

    while (true) {
      System.out.println("Choose Your Command >> ");

      s = scanner.nextLine().toLowerCase();

      if (commandMap.containsKey(s)) {
        break;
      }

      System.out.println("Invalid Input");
    }

    switch (commandMap.get(s)) {
      case 18:
        gameMap.go_North();
        break;
      case 15:
        gameMap.go_South();
        break;
      case 14:
        gameMap.go_West();
        break;
      case 16:
        gameMap.go_East();
        break;
      case 11:
        UIInventory.displayInventoryList();
        break;
      case -1:
        exit = true;
        break;
      case 12:
        //display mini map
        UIMiniMap.displayMiniMap();
      default:
    }

    if (commandMap.get(s) < subMaps.size() && commandMap.get(s) >= 0) {
      current_subArea = subMaps.get(commandMap.get(s));

      UIEnterSubarea.displaySubarea();
    }
  }

  private static void displayMapContent() {
    outputString.setLength(0);

    int position = mainMap.getPosition();

    var subMaps = mainMap.Game_Maps.get(position);

    int rows = subMaps.size();

    for (int i = 0; i < rows; i++) {
      commandMap.put(String.valueOf(i + 1), i);

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
          threatLevel = "\033[34mSafe\33[0m";
        } else if (myPower * 3 / 4 >= enemyPower) {
          threatLevel = "\033[32mLow\33[0m" + " (" + subMap.getContents().enemies.size() + " "
              + subMap.getContents().enemies.get(0).getEnemy_type() + ")";
        } else if (myPower * 5 / 4 >= enemyPower) {
          threatLevel = "\033[33mMedium\33[0m" + " (" + subMap.getContents().enemies.size() + " "
              + subMap.getContents().enemies.get(0).getEnemy_type() + ")";
        } else if (myPower * 5 / 4 < enemyPower) {
          threatLevel = "\033[31mHigh\33[0m" + " (" + subMap.getContents().enemies.size() + " "
              + subMap.getContents().enemies.get(0).getEnemy_type() + ")";
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

    String title = "AREA " + gameMap.getPosition();
    int space = (x_axis_map - title.length()) / 2;

    outputString.append(" ".repeat(space));
    outputString.append(title);
    outputString.append(" ".repeat(space));

    System.out.println(outputString);

    drawFooter();
  }
}
