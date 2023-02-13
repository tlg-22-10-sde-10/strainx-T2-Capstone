package ui.maps;

import static gamecontrol.GlobalVariables.inGameMap;

import java.util.Scanner;

public class UIMiniMap {


  private static final StringBuilder outputString = new StringBuilder();
  private static final String PLAYER_INDICATOR = "\033[030;42mYOU ARE HERE\33[0m";

  private static final Scanner scannyMcScanFace = new Scanner(System.in);

  public static void displayMiniMap() {

    drawMiniMap3();
  }

  public static void drawMiniMap3() {
    outputString.setLength(0);

    var map = inGameMap;

    int maxGrids = map.getDimensionY() * map.getDimensionX();

    String content = "Area";

    String longestString = PLAYER_INDICATOR;

    for (int i = 1; i <= maxGrids; i++) {
      for (int j = 0; j < map.gameMap.get(i).size(); j++) {
        if (map.gameMap.get(i).get(j).getName().length() > longestString.length()) {
          longestString = map.gameMap.get(i).get(j).getName();
        }
      }
    }

    String[] areas = new String[maxGrids];

    for (int i = 0; i < maxGrids; i++) {
      areas[i] = content + " " + (i + 1) + ":";
      if ((i + 1) == map.getPosition()) {
        areas[i] = PLAYER_INDICATOR;
      }
    }

    int maxLength = Math.max(longestString.length(), areas[maxGrids - 1].length());

    int maxLengthEachBlock = maxLength + 1;

    maxLength = maxLengthEachBlock * map.getDimensionX() + 1;

    for (int y = 0; y < map.getDimensionY(); y++) {
      outputString.setLength(0);

      outputString.append("# ".repeat(maxLength / 2));

      System.out.println(outputString.append("#"));
      outputString.setLength(0);

      //place area headers
      for (int x = 0; x < map.getDimensionX(); x++) {
        int blockSpaceLeft =
            (maxLengthEachBlock - 1 - areas[x + y * map.getDimensionX()].length()) / 2;
        int blockSpaceRight =
            maxLengthEachBlock - 1 - areas[x + y * map.getDimensionX()].length() - blockSpaceLeft;

        outputString.append("#");

        if (x + y * map.getDimensionX() == map.getPosition() - 1) {
          blockSpaceLeft += 6;
          blockSpaceRight += 7;
        }

        outputString.append(" ".repeat(blockSpaceLeft));
        outputString.append(areas[x + y * map.getDimensionX()]);
        outputString.append(" ".repeat(blockSpaceRight));
      }

      outputString.append("#");
      System.out.println(outputString);

      outputString.setLength(0);

      //place sub areas
      for (int k = 0; k < 3; k++) {
        for (int x = 0; x < map.getDimensionX(); x++) {


          outputString.append("#");
          String subAreaName = "??????";

          if (map.gameMap.get(x + 1 + y * map.getDimensionX()).size() > k) {
            if(map.gameMap.get(x + 1 + y * map.getDimensionX()).get(k).getVisited()) {
              subAreaName = map.gameMap.get(x + 1 + y * map.getDimensionX()).get(k)
                  .getName();
            }

            int blockSpaceLeft =
                (maxLengthEachBlock - 1 - subAreaName.length()) / 2;
            int blockSpaceRight =
                maxLengthEachBlock - 1 - subAreaName.length() - blockSpaceLeft;

            outputString.append(" ".repeat(blockSpaceLeft));
            outputString.append(subAreaName);
            outputString.append(" ".repeat(blockSpaceRight));
          } else {
            outputString.append(" ".repeat(maxLengthEachBlock - 1));
          }
        }
        outputString.append("#");
        System.out.println(outputString);

        outputString.setLength(0);
      }
    }

    outputString.append("# ".repeat(maxLength / 2));
    outputString.append("#");
    System.out.println(outputString);
    outputString.setLength(0);

    System.out.println("\n");
    System.out.println("Press any key to continue >>");
    scannyMcScanFace.nextLine();
  }
}