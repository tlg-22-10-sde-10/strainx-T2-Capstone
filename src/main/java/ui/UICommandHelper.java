package ui;

import java.util.Scanner;

public class UICommandHelper {

  private static final StringBuilder outputString = new StringBuilder();
  public static final int x_axis_map = 95;
  private static Scanner scanner = new Scanner(System.in);

  private static void drawFooter() {
    outputString.setLength(0);
    outputString.append("-".repeat(x_axis_map));
    System.out.println(outputString);
    outputString.setLength(0);
  }

  public static void showHelp() {
    System.out.println("\n");

    drawFooter();

    System.out.println("Help:");
    System.out.println("There are 3 different help menus:");
    System.out.println("For help with gameplay in the Map (aka Area) interface, type helpMap");
    System.out.println("For help with gameplay in the Subarea interface, type helpSubarea");
    System.out.println("For help with gameplay in the Combat interface, type helpCombat");

    drawFooter();

    System.out.println("Press any key to continue >>");
    scanner.nextLine();
  }

  public static void showHelpMap() {
    System.out.println("\n");

    drawFooter();

    System.out.println("The following commands are for navigating the main map or toggling other actions:");
    System.out.println("Entering a digit corresponding to a Subarea in the command prompt will attempt to enter that Subarea.");
    System.out.println("Entering \"w\" in the command prompt will attempt to go North 1 grid space.");
    System.out.println("Entering \"a\" in the command prompt will attempt to go West 1 grid space.");
    System.out.println("Entering \"s\" in the command prompt will attempt to go South 1 grid space.");
    System.out.println("Entering \"d\" in the command prompt will attempt to go East 1 grid space.");
    System.out.println("Entering \"m\" in the command prompt will show a minimap of the players location on the"
        + "main grid map.");
    System.out.println("Entering \"i\" in the command prompt will display the player's inventory.");
    System.out.println("Entering \"e\", \"exit\", \"q\", or \"quit\" in the command prompt will exit the game.");
    drawFooter();

    System.out.println("Press any key to continue >>");
    scanner.nextLine();
  }

  public static void showHelpSubArea() {
    System.out.println("\n");
    drawFooter();

    System.out.println("The following commands are for making selections within a Subarea:");
    System.out.println("Entering \"y\" in the command prompt will engage nearby zombies in combat.");
    System.out.println("Entering \"yes\" in the command prompt will engage nearby zombies in combat.");
    System.out.println("Entering \"n\" in the command prompt will return to the Map/Area overlay");
    System.out.println("Entering \"no\" in the command prompt will return to the Map/Area overlay");
    System.out.println("Pressing enter (command prompt blank) will return to the Map/Area overlay");
    System.out.println("Entering \"l\" in the command prompt will attempt to loot items in the area");
    System.out.println("Entering \"p\" in the command prompt will attempt to loot items in the area");
    System.out.println("Entering \"pick up\" in the command prompt will attempt to loot items in the area");

    drawFooter();

    System.out.println("Press any key to continue >>");
    scanner.nextLine();
  }

  public static void showHelpCombat() {
    System.out.println("\n");

    drawFooter();

    System.out.println("The following commands are for making selections in while in Combat:");
    System.out.println("Entering \"f\" in the command prompt will attack the enemy");
    System.out.println("Entering \"u\" in the command prompt will open inventory to use items");
    System.out.println("Entering \"c\" in the command prompt will auto select a target and resolve 1 round of combat");
    System.out.println("Entering \"r\" in the command prompt will attempt to retreat.");

    drawFooter();

    System.out.println("Press any key to continue >>");
    scanner.nextLine();
  }

}
