package ui.maps;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import org.w3c.dom.ls.LSOutput;

import static gamecontrol.GlobalVariables.currentSubAreaContents;
import static gamecontrol.GlobalVariables.inGameMap;

public class UIMiniMap {

    private static final StringBuilder outputString = new StringBuilder();
//    private static final String PLAYER_INDICATOR = "\033[030;42mYOU ARE HERE \33[0m";

    private static Scanner scannyMcScanFace = new Scanner(System.in);

    public static void displayMiniMap() {
        drawMiniMap();
    }

    private static void drawMiniMap() {
        outputString.setLength(0);

        var map = inGameMap;

        int maxGrids = map.getDimensionY()*map.getDimensionX();

        String content = "Area";

        String playerIndicator = "\033[030;42mYOU ARE HERE\33[0m";
        String maxSubArea = "Random Base House";
        String unknownSubarea = "??????";

        String[] areas = new String[maxGrids];

        for (int i =0; i< maxGrids; i++) {
            areas[i] = content + " "+ (i+1) + ":";
            if((i+1) == map.getPosition()) {
                areas[i] = playerIndicator;
            }
        }

        int maxLength = Math.max(playerIndicator.length(), areas[maxGrids-1].length());

        int maxLengthEachBlock = maxLength + 1;

        maxLength = maxLengthEachBlock*map.getDimensionX() +1;

        for (int y =0; y<map.getDimensionY(); y++) {
            outputString.setLength(0);

            outputString.append("# ".repeat(maxLength/2));

            System.out.println(outputString);
            outputString.setLength(0);

            //place area headers
            for(int x=0; x<map.getDimensionX(); x++) {

                int blockSpace = (maxLengthEachBlock - 1 - areas[x+y*map.getDimensionX()].length())/2;

                outputString.append("#");

                if(x+y*map.getDimensionX() == map.getPosition()-1) {
                    blockSpace += 6;

                    //player_indicator can be customized and will be different
                    if(playerIndicator.length()%2 ==0) {
                        outputString.append(" ");
                    }
                }
                outputString.append(" ".repeat(blockSpace));
                outputString.append(areas[x+y*map.getDimensionX()]);

                if (blockSpace > 6 && areas[x+y*map.getDimensionX()].length() > 7) {
                    outputString.append(" ".repeat(blockSpace));
                } else if (blockSpace == 6) {
                    outputString.append(" ".repeat(blockSpace));
                } else {
                    outputString.append(" ".repeat(blockSpace-1));
                }
            }
            if(maxLength%2==0) {
                outputString.replace(outputString.length()-1, outputString.length()-1, "#");
            } else {
                outputString.append(" #");
            }

            //print area headers
            System.out.println(outputString);
            //reset
            outputString.setLength(0);

            //place first subarea headers
            for(int x=0; x<map.getDimensionX(); x++) {
                if (map.gameMaps.get(x+1+y*map.getDimensionX()).get(0).getVisited()) {
                    int blockSpace = (maxLengthEachBlock - 1 - map.gameMaps.get(x+1+y*map.getDimensionX()).get(0).getName().length())/2;

                    outputString.append("#");

                    if(map.gameMaps.get(x+1+y*map.getDimensionX()).get(0).getName().length() == maxSubArea.length()) {
                        blockSpace = 4;
                    }
                    outputString.append(" ".repeat(blockSpace));
                    outputString.append(map.gameMaps.get(x+1+y*map.getDimensionX()).get(0).getName());
                    outputString.append(" ".repeat(maxLengthEachBlock - 2 - blockSpace - map.gameMaps.get(x+1+y*map.getDimensionX()).get(0).getName().length()));
                } else {
                    int blockSpace = (maxLengthEachBlock - 1 - unknownSubarea.length())/2;

                    outputString.append("#");
                    outputString.append(" ".repeat(blockSpace));
                    outputString.append(unknownSubarea);
                    outputString.append(" ".repeat(blockSpace));
                }

            }
            if(maxLength%2==0) {
                outputString.replace(outputString.length()-1, outputString.length()-1, "#");
            } else {
                outputString.append(" #");
            }
            //print subarea headers
            System.out.println(outputString);
            //reset
            outputString.setLength(0);

            //place second subarea headers
            for(int x=0; x<map.getDimensionX(); x++) {
                if (map.gameMaps.get(x + 1 + y * map.getDimensionX()).toArray().length > 1) {
                    if (map.gameMaps.get(x+1+y*map.getDimensionX()).get(1).getVisited()) {
                        outputString.append("#");
                        if (map.gameMaps.get(x + 1 + y * map.getDimensionX()).get(1).getName()
                            .length() == maxSubArea.length()) {
                            int blockSpace = 4;
                            outputString.append(" ".repeat(blockSpace));
                            outputString.append(
                                map.gameMaps.get(x + 1 + y * map.getDimensionX()).get(1).getName());
                            outputString.append(" ".repeat(
                                maxLengthEachBlock - 2 - blockSpace - map.gameMaps.get(
                                    x + 1 + y * map.getDimensionX()).get(1).getName().length()));
                        } else {
                            int blockSpace = (maxLengthEachBlock - 1 - map.gameMaps.get(
                                x + 1 + y * map.getDimensionX()).get(1).getName().length()) / 2;
                            outputString.append(" ".repeat(blockSpace));
                            outputString.append(
                                map.gameMaps.get(x + 1 + y * map.getDimensionX()).get(1).getName());
                            outputString.append(" ".repeat(
                                maxLengthEachBlock - 2 - blockSpace - map.gameMaps.get(
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
            if(maxLength%2==0) {
                outputString.replace(outputString.length()-1, outputString.length()-1, "#");
            } else {
                outputString.append(" #");
            }
            //print subarea headers
            System.out.println(outputString);
            //reset
            outputString.setLength(0);

//            //place third subarea headers
            for(int x=0; x<map.getDimensionX(); x++) {
                if (map.gameMaps.get(x+1+y*map.getDimensionX()).toArray().length > 2) {
                    if (map.gameMaps.get(x+1+y*map.getDimensionX()).get(2).getVisited()) {
                        outputString.append("#");
                        if(map.gameMaps.get(x+1+y*map.getDimensionX()).get(2).getName().length() == maxSubArea.length()) {
                            int blockSpace = 4;
                            outputString.append(" ".repeat(blockSpace));
                            outputString.append(map.gameMaps.get(x+1+y*map.getDimensionX()).get(2).getName());
                            outputString.append(" ".repeat(maxLengthEachBlock - 2 - blockSpace - map.gameMaps.get(x+1+y*map.getDimensionX()).get(2).getName().length()));
                        } else {
                            int blockSpace = (maxLengthEachBlock - 1 - map.gameMaps.get(x+1+y*map.getDimensionX()).get(2).getName().length())/2;
                            outputString.append(" ".repeat(blockSpace));
                            outputString.append(map.gameMaps.get(x+1+y*map.getDimensionX()).get(2).getName());
                            outputString.append(" ".repeat(maxLengthEachBlock - 2 - blockSpace - map.gameMaps.get(x+1+y*map.getDimensionX()).get(2).getName().length()));
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
            if(maxLength%2==0) {
                outputString.replace(outputString.length()-1, outputString.length()-1, "#");
            } else {
                outputString.append(" #");
            }
            //print subarea headers
            System.out.println(outputString);
            //reset
            outputString.setLength(0);
        }
        outputString.setLength(0);
        outputString.append("# ".repeat(maxLength/2));
        System.out.println(outputString);
        outputString.setLength(0);

        System.out.println("\n");
        System.out.println("Press any key to continue >>");
        scannyMcScanFace.nextLine();

    }
}
