package ui.maps;

import static gamecontrol.GlobalVariables.inGameMap;

import java.util.Scanner;

public class UIMiniMap {

  private static final StringBuilder outputString = new StringBuilder();
  private static final String PLAYER_INDICATOR = "\033[030;42mYOU ARE HERE\33[0m";

  private static Scanner scannyMcScanFace = new Scanner(System.in);

  public static void displayMiniMap() {
    //drawMiniMap();
    drawMiniMap2();
  }

  private static void drawMiniMap() {
    outputString.setLength(0);

    var map = inGameMap;

    int maxGrids = map.getDimensionY() * map.getDimensionX();

    String content = "Area";

    //String playerIndicator = "\033[030;42mYOU ARE HERE\33[0m";
    String maxSubArea = "Random Base House";
    String unknownSubarea = "??????";

    String[] areas = new String[maxGrids];

    for (int i = 0; i < maxGrids; i++) {
      areas[i] = content + " " + (i + 1) + ":";
      if ((i + 1) == map.getPosition()) {
        areas[i] = PLAYER_INDICATOR;
      }
    }

    int maxLength = Math.max(PLAYER_INDICATOR.length(), areas[maxGrids - 1].length());

    int maxLengthEachBlock = maxLength + 1;

    maxLength = maxLengthEachBlock * map.getDimensionX() + 1;

    for (int y = 0; y < map.getDimensionY(); y++) {
      outputString.setLength(0);

      outputString.append("# ".repeat(maxLength / 2));

      System.out.println(outputString);
      outputString.setLength(0);

      //place area headers
      for (int x = 0; x < map.getDimensionX(); x++) {

        int blockSpace = (maxLengthEachBlock - 1 - areas[x + y * map.getDimensionX()].length()) / 2;

        outputString.append("#");

        if (x + y * map.getDimensionX() == map.getPosition() - 1) {
          blockSpace += 6;

          //player_indicator can be customized and will be different
          if (PLAYER_INDICATOR.length() % 2 == 0) {
            outputString.append(" ");
          }
        }
        outputString.append(" ".repeat(blockSpace));
        outputString.append(areas[x + y * map.getDimensionX()]);

        if (blockSpace > 6 && areas[x + y * map.getDimensionX()].length() > 7) {
          outputString.append(" ".repeat(blockSpace));
        } else if (blockSpace == 6) {
          outputString.append(" ".repeat(blockSpace));
        } else {
          outputString.append(" ".repeat(blockSpace - 1));
        }
      }
      if (maxLength % 2 == 0) {
        outputString.replace(outputString.length() - 1, outputString.length() - 1, "#");
      } else {
        outputString.append(" #");
      }

      //print area headers
      System.out.println(outputString);
      //reset
      outputString.setLength(0);

      //place first subarea headers
      for (int x = 0; x < map.getDimensionX(); x++) {
        if (map.gameMap.get(x + 1 + y * map.getDimensionX()).get(0).getVisited()) {
          int blockSpace =
              (maxLengthEachBlock - 1 - map.gameMap.get(x + 1 + y * map.getDimensionX()).get(0)
                  .getName().length()) / 2;

          outputString.append("#");

          if (map.gameMap.get(x + 1 + y * map.getDimensionX()).get(0).getName().length()
              == maxSubArea.length()) {
            blockSpace = 4;
          }
          outputString.append(" ".repeat(blockSpace));
          outputString.append(map.gameMap.get(x + 1 + y * map.getDimensionX()).get(0).getName());
          outputString.append(" ".repeat(
              maxLengthEachBlock - 2 - blockSpace - map.gameMap.get(x + 1 + y * map.getDimensionX())
                  .get(0).getName().length()));
        } else {
          int blockSpace = (maxLengthEachBlock - 1 - unknownSubarea.length()) / 2;

          outputString.append("#");
          outputString.append(" ".repeat(blockSpace));
          outputString.append(unknownSubarea);
          outputString.append(" ".repeat(blockSpace));
        }

      }
      if (maxLength % 2 == 0) {
        outputString.replace(outputString.length() - 1, outputString.length() - 1, "#");
      } else {
        outputString.append(" #");
      }
      //print subarea headers
      System.out.println(outputString);
      //reset
      outputString.setLength(0);

      //place second subarea headers
      for (int x = 0; x < map.getDimensionX(); x++) {
        if (map.gameMap.get(x + 1 + y * map.getDimensionX()).toArray().length > 1) {
          if (map.gameMap.get(x + 1 + y * map.getDimensionX()).get(1).getVisited()) {
            outputString.append("#");
            if (map.gameMap.get(x + 1 + y * map.getDimensionX()).get(1).getName()
                .length() == maxSubArea.length()) {
              int blockSpace = 4;
              outputString.append(" ".repeat(blockSpace));
              outputString.append(
                  map.gameMap.get(x + 1 + y * map.getDimensionX()).get(1).getName());
              outputString.append(" ".repeat(
                  maxLengthEachBlock - 2 - blockSpace - map.gameMap.get(
                      x + 1 + y * map.getDimensionX()).get(1).getName().length()));
            } else {
              int blockSpace = (maxLengthEachBlock - 1 - map.gameMap.get(
                  x + 1 + y * map.getDimensionX()).get(1).getName().length()) / 2;
              outputString.append(" ".repeat(blockSpace));
              outputString.append(
                  map.gameMap.get(x + 1 + y * map.getDimensionX()).get(1).getName());
              outputString.append(" ".repeat(
                  maxLengthEachBlock - 2 - blockSpace - map.gameMap.get(
                      x + 1 + y * map.getDimensionX()).get(1).getName().length()));
            }
          } else {
            int blockSpace = (maxLengthEachBlock - 1 - unknownSubarea.length()) / 2;

            outputString.append("#");
            outputString.append(" ".repeat(blockSpace));
            outputString.append(unknownSubarea);
            outputString.append(" ".repeat(blockSpace));
          }
        } else {
          outputString.append("#                        ");
        }
      }
      if (maxLength % 2 == 0) {
        outputString.replace(outputString.length() - 1, outputString.length() - 1, "#");
      } else {
        outputString.append(" #");
      }
      //print subarea headers
      System.out.println(outputString);
      //reset
      outputString.setLength(0);

//            //place third subarea headers
      for (int x = 0; x < map.getDimensionX(); x++) {
        if (map.gameMap.get(x + 1 + y * map.getDimensionX()).toArray().length > 2) {
          if (map.gameMap.get(x + 1 + y * map.getDimensionX()).get(2).getVisited()) {
            outputString.append("#");
            if (map.gameMap.get(x + 1 + y * map.getDimensionX()).get(2).getName().length()
                == maxSubArea.length()) {
              int blockSpace = 4;
              outputString.append(" ".repeat(blockSpace));
              outputString.append(
                  map.gameMap.get(x + 1 + y * map.getDimensionX()).get(2).getName());
              outputString.append(" ".repeat(maxLengthEachBlock - 2 - blockSpace - map.gameMap.get(
                  x + 1 + y * map.getDimensionX()).get(2).getName().length()));
            } else {
              int blockSpace =
                  (maxLengthEachBlock - 1 - map.gameMap.get(x + 1 + y * map.getDimensionX()).get(2)
                      .getName().length()) / 2;
              outputString.append(" ".repeat(blockSpace));
              outputString.append(
                  map.gameMap.get(x + 1 + y * map.getDimensionX()).get(2).getName());
              outputString.append(" ".repeat(maxLengthEachBlock - 2 - blockSpace - map.gameMap.get(
                  x + 1 + y * map.getDimensionX()).get(2).getName().length()));
            }
          } else {
            int blockSpace = (maxLengthEachBlock - 1 - unknownSubarea.length()) / 2;

            outputString.append("#");
            outputString.append(" ".repeat(blockSpace));
            outputString.append(unknownSubarea);
            outputString.append(" ".repeat(blockSpace));
          }
        } else {
          outputString.append("#                        ");
        }
      }
      if (maxLength % 2 == 0) {
        outputString.replace(outputString.length() - 1, outputString.length() - 1, "#");
      } else {
        outputString.append(" #");
      }
      //print subarea headers
      System.out.println(outputString);
      //reset
      outputString.setLength(0);
    }
    outputString.setLength(0);
    outputString.append("# ".repeat(maxLength / 2));
    System.out.println(outputString);
    outputString.setLength(0);

    System.out.println("\n");
    System.out.println("Press any key to continue >>");
    scannyMcScanFace.nextLine();
  }

  private static void drawMiniMap2() {
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

    String unknownSubarea = "??????";

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

      //place first subarea headers
      for (int k = 0; k < 3; k++) {

      }

      for (int x = 0; x < map.getDimensionX(); x++) {

        int blockSpaceLeft =
            (maxLengthEachBlock - 1 - map.gameMap.get(x + 1 + y * map.getDimensionX()).get(0)
                .getName().length()) / 2;
        int blockSpaceRight =
            maxLengthEachBlock - 1 - map.gameMap.get(x + 1 + y * map.getDimensionX()).get(0)
                .getName().length() - blockSpaceLeft;

        outputString.append("#");

        outputString.append(" ".repeat(blockSpaceLeft));
        outputString.append(map.gameMap.get(x + 1 + y * map.getDimensionX()).get(0).getName());
        outputString.append(" ".repeat(blockSpaceRight));
      }

      outputString.append("#");
      System.out.println(outputString);

      outputString.setLength(0);
      //place second subarea headers
      for (int x = 0; x < map.getDimensionX(); x++) {
        outputString.append("#");
        if (map.gameMap.get(x + 1 + y * map.getDimensionX()).size() > 1) {

          int blockSpaceLeft =
              (maxLengthEachBlock - 1 - map.gameMap.get(x + 1 + y * map.getDimensionX()).get(1)
                  .getName().length()) / 2;
          int blockSpaceRight =
              maxLengthEachBlock - 1 - map.gameMap.get(x + 1 + y * map.getDimensionX()).get(1)
                  .getName().length() - blockSpaceLeft;

          outputString.append(" ".repeat(blockSpaceLeft));
          outputString.append(map.gameMap.get(x + 1 + y * map.getDimensionX()).get(1).getName());
          outputString.append(" ".repeat(blockSpaceRight));
        } else {
          outputString.append(" ".repeat(maxLengthEachBlock - 1));
        }
      }
      outputString.append("#");
      System.out.println(outputString);

      outputString.setLength(0);
      //place third subarea headers
      for (int x = 0; x < map.getDimensionX(); x++) {
        outputString.append("#");

        if (map.gameMap.get(x + 1 + y * map.getDimensionX()).toArray().length > 2) {

          int blockSpaceLeft =
              (maxLengthEachBlock - 1 - map.gameMap.get(x + 1 + y * map.getDimensionX()).get(2)
                  .getName().length()) / 2;
          int blockSpaceRight =
              maxLengthEachBlock - 1 - map.gameMap.get(x + 1 + y * map.getDimensionX()).get(2)
                  .getName().length() - blockSpaceLeft;

          outputString.append(" ".repeat(blockSpaceLeft));
          outputString.append(map.gameMap.get(x + 1 + y * map.getDimensionX()).get(2).getName());
          outputString.append(" ".repeat(blockSpaceRight));
        } else {
          outputString.append(" ".repeat(maxLengthEachBlock - 1));
        }
      }

      outputString.append("#");

      //print subarea headers
      System.out.println(outputString);
      //reset
      outputString.setLength(0);
    }

    outputString.append("# ".repeat(maxLength / 2) + "#");
    System.out.println(outputString);
    outputString.setLength(0);

    System.out.println("\n");
    System.out.println("Press any key to continue >>");
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

    String unknownSubarea = "??????";

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

      //place first subarea headers

      for (int x = 0; x < map.getDimensionX(); x++) {
        for (int k = 0; k < 3; k++) {
          outputString.append("#");
          if (map.gameMap.get(x + 1 + y * map.getDimensionX()).size() > k) {

            int blockSpaceLeft =
                (maxLengthEachBlock - 1 - map.gameMap.get(x + 1 + y * map.getDimensionX()).get(k)
                    .getName().length()) / 2;
            int blockSpaceRight =
                maxLengthEachBlock - 1 - map.gameMap.get(x + 1 + y * map.getDimensionX()).get(k)
                    .getName().length() - blockSpaceLeft;

            outputString.append(" ".repeat(blockSpaceLeft));
            outputString.append(map.gameMap.get(x + 1 + y * map.getDimensionX()).get(k).getName());
            outputString.append(" ".repeat(blockSpaceRight));
          } else {
            outputString.append(" ".repeat(maxLengthEachBlock - 1));
          }
        }

        outputString.append("# ".repeat(maxLength / 2) + "#");
        System.out.println(outputString);
        outputString.setLength(0);
      }

      System.out.println("\n");
      System.out.println("Press any key to continue >>");
      scannyMcScanFace.nextLine();
    }
  }
}