package ui;

import static gamecontrol.GameDifficultySelection.userSelection;
import static ui.maps.UIEnterMainMap.*;

import gamecontrol.GlobalVariables;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import ui.endgame.UIDisplayGameStatus;
import ui.endgame.UIIntroBlurb;
import ui.endgame.UITitlePage;
import ui.gui.components.InventoryPanel;
import ui.maps.UIEnterMainMap;

public class UIEnterGame {

    private static int x_axis = 96;
    private static final Scanner s = new Scanner(System.in);
    private static final Map<String, String> enterGameControl = new HashMap<>();
    private static final StringBuilder sb= new StringBuilder();

  public static void displayEnterGame(InventoryPanel inventoryPanel) throws InterruptedException, IOException {

    boolean exit = false;

    while(!exit) {
      String command;

//      UIDisplayGameStatus.displayInfo(UITitlePage.displayTitle());

//      drawFooter();
//
//      displayGameControl();

      while(true) {

        System.out.println("Please select option to proceed >> ");
        String userInput = s.nextLine().toLowerCase(Locale.ROOT);

        if(enterGameControl.containsKey(userInput)) {
          command = enterGameControl.get(userInput);
          break;
        }

        System.out.println("Invalid Command");
      }

      switch (command) {
        case "New Game":
          UIEnterMainMap.drawFooter();
          userSelection();
          GlobalVariables.gameInitialization();

          UIIntroBlurb.displayIntro();

          UIEnterMainMap.displayMainMapUI();
          break;
        case "Staff":
          displayStaff();
          break;
        case "Exit":
          exit = true;
          break;
        default:
      }
    }

    drawFooter();
  }

  public static void displayGameControl() {
    int space;
    enterGameControlInitialize();
    space = (x_axis - "1. New Game".length())/2;

    for (var k : enterGameControl.keySet()) {
      sb.setLength(0);
      //sb.append(" ".repeat(space));
      sb.append("\n");
      sb.append(k);
      sb.append(". ");
      sb.append(enterGameControl.get(k));

      System.out.println(sb);
    }
    System.out.println();
  }

  private static void enterGameControlInitialize() {
    enterGameControl.put("1", "New Game");
    enterGameControl.put("2", "Staff");
    enterGameControl.put("3", "Exit");
  }

  public static void displayStaff() throws InterruptedException {

    String staff = "Staff";
    String ma = "Chongwei Ma";
    String ethan = "Ethan Wang";
    String cameron = "Cameron Davis";

    var space = (x_axis - staff.length())/2;

    System.out.println("\n".repeat(10));

    sb.setLength(0);
    sb.append("\n".repeat(4));
    sb.append(" ".repeat(space));
    sb.append(staff);
    sb.append("\n\n\n");

    space = (x_axis - cameron.length())/2;
    sb.append(" ".repeat(space));
    sb.append(cameron);
    sb.append("\n\n\n");

    space = (x_axis - ma.length())/2;
    sb.append(" ".repeat(space));
    sb.append(ma);
    sb.append("\n\n\n");

    space = (x_axis - ethan.length())/2;
    sb.append(" ".repeat(space));
    sb.append(ethan);
    sb.append("\n\n\n\n");

    drawFooter();
    System.out.println(sb);
    drawFooter();

    Thread.sleep(4000);

    for(int i=0;i<24;i++) {
      Thread.sleep(120);
      System.out.println("\n");
    }
  }
}
