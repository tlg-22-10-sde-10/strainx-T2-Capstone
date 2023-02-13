package ui.maps;

import static gamecontrol.GlobalVariables.DESTINATION;
import static gamecontrol.GlobalVariables.currentSubAreaContents;
import static gamecontrol.GlobalVariables.defeatBoss;
import static gamecontrol.GlobalVariables.inGameCommands;
import static gamecontrol.GlobalVariables.inGameMap;
import static gamecontrol.GlobalVariables.mySquad;
import static gamecontrol.GlobalVariables.titleMusicInitialize;
import static gamecontrol.GlobalVariables.titleMusicStop;

import gamecontrol.GlobalVariables;
import gamemodel.combatengine.UICombat;
import gamemodel.mapengine.SubArea;
import gamemusic.AudioPlayer;
import java.util.HashMap;
import java.util.Scanner;
import ui.UICommandHelper;
import ui.endgame.UIDisplayGameStatus;
import ui.endgame.UIWinningPage;
import ui.inventory.UIInventory;

public class UIEnterMainMap {

  private static final StringBuilder outputString = new StringBuilder();

  private static final HashMap<Integer, String> threatLvlMap = new HashMap<>();

  private static final Scanner scanner = new Scanner(System.in);

  public static final int x_axis_map = 96;

  private static boolean exit = false;

  private static void threatLvlMapInitialize() {
    threatLvlMap.put(3, "\033[31mHigh\33[0m");
    threatLvlMap.put(2, "\033[33mMedium\33[0m");
    threatLvlMap.put(1, "\033[32mLow\33[0m");
    threatLvlMap.put(0, "\033[34mSafe\33[0m");
  }

  public static void drawFooter() {
    outputString.setLength(0);
    outputString.append("-".repeat(x_axis_map));
    System.out.println(outputString);
    outputString.setLength(0);
  }

  public static void drawFooter(int xAxis) {
    outputString.setLength(0);
    outputString.append("-".repeat(xAxis));
    System.out.println(outputString);
    outputString.setLength(0);
  }

  public static void displayMainMapUI() {
    threatLvlMapInitialize();

    exit = false;
    //defeatBoss = false;

    while (mySquad.get(0).getHP() > 0 && !defeatBoss && !exit) {
      System.out.println("\n");
      displayMapTitle();

      displayMapBody();
    }

    if (defeatBoss) {
      UIDisplayGameStatus.displayInfo(UIWinningPage.displayWinning());
    }
  }

  private static void displayMapBody() {
    UICombat.reportMySquadStatus();

    drawFooter();

    displayMapContent();

    int position = inGameMap.getPosition();

    var subMaps = inGameMap.gameMap.get(position);

    String s;

    exit = false;

    while (true) {
      System.out.println("Select An Action >> ");

      s = scanner.nextLine().toLowerCase();

      if (inGameCommands.containsKey(s)) {
        break;
      }

      System.out.println("Invalid Input. Type help if you need a list of actions.");
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
      case 31:
        titleMusicInitialize();
        break;
      case 32:
        titleMusicStop();
        break;
      case 33:
        AudioPlayer.changeVolume(1);
        break;
      case 34:
        AudioPlayer.changeVolume(2);
        break;
      case -1:
        System.out.println("To confirm quit, type \33[31mY\33[0m to continue >>");
        s = scanner.nextLine().toLowerCase();
        if (s.equals("y")) {
          exit = true;
        }
        break;
      default:
        if (inGameCommands.get(s) > subMaps.size() || inGameCommands.get(s) <= 0) {
          System.out.println("Invalid Command! Press any key to continue...");
          scanner.nextLine();
        }
    }

    if (inGameCommands.get(s) <= subMaps.size() && inGameCommands.get(s) > 0) {
      currentSubAreaContents = subMaps.get(inGameCommands.get(s) - 1);

      if (currentSubAreaContents.getName().equals(DESTINATION)) {
        if (GlobalVariables.firstVisitToLab) {
          System.out.println("Upon finally reaching the lab, you start making your way toward its Entry Control Point.\n"
              + "Inside, there is a massive blast proof door with a panel to the right prompting for a password.\n"
              + "\"Of course there was no mention of this in the mission briefing notes...\" you think to yourself.\n"
              + "Looking around you see the Entry Control officer's reception desk across the room. Behind the desk\n"
              + "you find a hastily, bloodstained note that reads: \"For entry, find the lead scientist..\"");
          GlobalVariables.firstVisitToLab = false;
        }
        System.out.println("\nAccess to this Lab is password protected. Enter the password >>");
        s = scanner.nextLine();
        if (s.equals(GlobalVariables.getPassWord())) {
          UIEnterSubarea.displaySubarea(); // when this is lab
        } else {
          System.out.println("Invalid password.");
        }
      } else {
        UIEnterSubarea.displaySubarea(); //when not lab
      }
    }
  }

  private static void displayMapContent() {
    outputString.setLength(0);

    int position = inGameMap.getPosition();

    var subMaps = inGameMap.gameMap.get(position);

    int rows = subMaps.size();

    for (int i = 0; i < rows; i++) {
      var subMap = subMaps.get(i);

      String line1 = (i + 1) + ". " + subMap.getName() + "\n\n";

      String threatLevel= "??????";
      String itemsInMap = "??????";

      if (subMap.getVisited()) {
        if (subMap.getContents().items.size() > 0) {
          itemsInMap = String.valueOf(subMap.getContents().items.size());
        } else {
          itemsInMap = "None";
        }

        threatLevel = displayThreatLvl(subMap);
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

  public static String displayThreatLvl(SubArea subMap) {
    String threatLevel = "??????";

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

    return threatLevel;
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
