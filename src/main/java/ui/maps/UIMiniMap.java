package ui.maps;

import java.util.Scanner;

import static gamecontrol.GlobalVariables.inGameMap;

public class UIMiniMap {

    private static final StringBuilder outputString = new StringBuilder();
    private static final Scanner s = new Scanner(System.in);
    private static final String PLAYER_INDICATOR = "\033[030;42mYOU ARE HERE \33[0m";

    public static void displayMiniMap() {
        System.out.println("\n\n");
        drawMiniMap();

        System.out.println("Press any key to continue...");
        s.nextLine();
    }

    private static void drawMiniMap() {
        outputString.setLength(0);

        int spaceHolder = 1;

        var map = inGameMap;

        int maxGrids = map.getDimensionY()*map.getDimensionX();

        String content = "Area";

        String[] areas = new String[maxGrids];
        for (int i =0; i< maxGrids; i++) {
            areas[i] = content + " "+ (i+1);
            if((i+1) == map.getPosition()) {
                areas[i] = PLAYER_INDICATOR;
            }
        }

        int maxLength = Math.max(PLAYER_INDICATOR.length(), areas[maxGrids-1].length());

        int maxLengthEachBlock = maxLength + spaceHolder*2 + 1;

        maxLength = maxLengthEachBlock*map.getDimensionX() +1;

        for (int y =0; y<map.getDimensionY(); y++) {
            outputString.setLength(0);

            outputString.append("# ".repeat(maxLength/2));

            System.out.println(outputString);
            outputString.setLength(0);

            for(int x=0; x<map.getDimensionX(); x++) {

                int blockSpace = (maxLengthEachBlock - 1 - areas[x+y*map.getDimensionX()].length())/2;

                outputString.append("#");

                if(x+y*map.getDimensionX() == map.getPosition()-1) {
                    blockSpace += 6;

                    //player_indicator can be customized and will be different
                    if(PLAYER_INDICATOR.length()%2 ==0) {
                        outputString.append(" ");
                    }
                }
                outputString.append(" ".repeat(blockSpace));
                outputString.append(areas[x+y*map.getDimensionX()]);

                outputString.append(" ".repeat(blockSpace));
            }
            if(maxLength%2==0) {
                outputString.replace(outputString.length()-1, outputString.length()-1, "#");
            } else {
                outputString.append(" #");
            }

            System.out.println(outputString);
        }

        outputString.setLength(0);
        outputString.append("# ".repeat(maxLength/2));
        System.out.println(outputString);
        outputString.setLength(0);
    }
}
