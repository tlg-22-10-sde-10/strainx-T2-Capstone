package UI;

import GameMap.Main_Map;
import GameMap.SubArea;

import java.util.Random;

import static Client.GlobalVariables.*;

public class UI_map {

    static Main_Map mainMap = gameMap;

    private static final StringBuilder outputString = new StringBuilder();

    public static final int x_axis_map = 95; //need to be an odd number for map display

    public static final int y_axis_map = 13; //need to be an odd number for map display

    private static final int border_width = 11;

    public static void displayMainMapUI() {
        System.out.println("\n\n\n\n\n");
        displayMapTitle();
        displayPlayerCommand();

        drawBorder();
    }

    private static void displayMap() {
        drawFooter();
    }

    private static void drawBorder() {
        drawUpperBorder();

        int lowerHalfStartingSubAreaIndex = 2;

        drawUpperHalfMap(lowerHalfStartingSubAreaIndex);
        drawEquatorialLine();
        drawHalfMap();

        drawLowerBorder();

        drawFooter();
    }

    private static void drawUpperBorder() {
        outputString.setLength(0);
        String borderSpaceHolder = " ".repeat(border_width);

        String goNorth = "Go North";
        String northSpaceHolder = " ".repeat((x_axis_map - goNorth.length())/2);

        outputString.append(northSpaceHolder);
        outputString.append(goNorth);
        outputString.append(northSpaceHolder);
        outputString.append("\n");

        outputString.append(borderSpaceHolder);
        outputString.append("# ".repeat((x_axis_map - borderSpaceHolder.length()*2)/2));
        outputString.append("#");
        outputString.append(borderSpaceHolder);

        System.out.println(outputString);
        outputString.setLength(0);
    }

    private static void drawLowerBorder() {
        outputString.setLength(0);
        String borderSpaceHolder = " ".repeat(border_width);

        outputString.append(borderSpaceHolder);
        outputString.append("# ".repeat((x_axis_map - borderSpaceHolder.length()*2)/2));
        outputString.append("#");
        outputString.append("\n");

        String goSouth = "Go South";
        String southSpaceHolder = " ".repeat((x_axis_map - goSouth.length())/2);

        outputString.append(southSpaceHolder);
        outputString.append(goSouth);
        outputString.append(southSpaceHolder);

        System.out.println(outputString);
        outputString.setLength(0);

        outputString.setLength(0);
    }

    private static void drawHalfMap() {
        outputString.setLength(0);
        String borderSpaceHolder = " ".repeat(border_width);

        for (int i =0; i< y_axis_map/2; i++) {
            outputString.append(borderSpaceHolder);
            outputString.append("#");
            outputString.append(" ".repeat(x_axis_map - 2 - borderSpaceHolder.length() * 2));
            outputString.append("#");
            outputString.append(borderSpaceHolder);

            System.out.println(outputString);
            outputString.setLength(0);
        }
    }

    private static void drawUpperHalfMap(int lowerHalfStartingSubAreaIndex) {
        switch (lowerHalfStartingSubAreaIndex) {
            case 0:
                drawZeroBlock();
                break;
            case 1:
                drawOneBlock(lowerHalfStartingSubAreaIndex);
                break;
            default:
                drawTwoBlocks(lowerHalfStartingSubAreaIndex);
        }
    }

    private static void drawLowerHalfMap(int lowerHalfStartingSubAreaIndex) {
        int lowerHalfBlocks = gameMap.Game_Maps.get(gameMap.getPosition()).size() -1 - lowerHalfStartingSubAreaIndex;
        switch (lowerHalfBlocks) {
            case 0:
                drawOneBlock(lowerHalfBlocks);
                break;
            case 1:
                drawTwoBlocks(lowerHalfBlocks);
                break;
            default:
                drawZeroBlock();
        }
    }

    private static void drawTwoBlocks(int startIndex) {
        outputString.setLength(0);
        String borderSpaceHolder = " ".repeat(border_width);

        //SubArea subArea1 = gameMap.Game_Maps.get(gameMap.getPosition()).get(startIndex);
        //SubArea subArea2 = gameMap.Game_Maps.get(gameMap.getPosition()).get(startIndex + 1);

        //String name1 = subArea1.getName();
        //String name2 = subArea2.getName();

        //line1
        outputString.append(borderSpaceHolder);
        outputString.append("#");
        outputString.append(" ".repeat(x_axis_map - 2 - borderSpaceHolder.length() * 2));
        outputString.append("#");
        outputString.append(borderSpaceHolder);

        System.out.println(outputString);
        outputString.setLength(0);
        //line2
        outputString.append(borderSpaceHolder);
        outputString.append("# ");
        outputString.append("- ".repeat((x_axis_map - 2 - borderSpaceHolder.length() * 2) / 4 - 1));
        outputString.append("-");
        outputString.append("   ");
        outputString.append("- ".repeat((x_axis_map - 2 - borderSpaceHolder.length() * 2) / 4 - 1));
        outputString.append("-");
        outputString.append(" #");
        outputString.append(borderSpaceHolder);

        System.out.println(outputString);
        outputString.setLength(0);
        //line3
        outputString.append(borderSpaceHolder);
        outputString.append("# ");
        outputString.append("|");
        outputString.append("  ".repeat((x_axis_map - 2 - borderSpaceHolder.length() * 2) / 4 - 2));
        outputString.append(" |");
        outputString.append("   ");
        outputString.append("|");
        outputString.append("  ".repeat((x_axis_map - 2 - borderSpaceHolder.length() * 2) / 4 - 2));
        outputString.append(" |");
        outputString.append(" #");
        outputString.append(borderSpaceHolder);

        System.out.println(outputString);
        outputString.setLength(0);
        //line4
        outputString.append(borderSpaceHolder);
        outputString.append("# ");
        outputString.append("|");
        outputString.append("  ".repeat((x_axis_map - 2 - borderSpaceHolder.length() * 2) / 4 - 2));
        outputString.append(" |");
        outputString.append("   ");
        outputString.append("|");
        outputString.append("  ".repeat((x_axis_map - 2 - borderSpaceHolder.length() * 2) / 4 - 2));
        outputString.append(" |");
        outputString.append(" #");
        outputString.append(borderSpaceHolder);

        System.out.println(outputString);
        outputString.setLength(0);
        //line5
        outputString.append(borderSpaceHolder);
        outputString.append("# ");
        outputString.append("|");
        outputString.append("  ".repeat((x_axis_map - 2 - borderSpaceHolder.length() * 2) / 4 - 2));
        outputString.append(" |");
        outputString.append("   ");
        outputString.append("|");
        outputString.append("  ".repeat((x_axis_map - 2 - borderSpaceHolder.length() * 2) / 4 - 2));
        outputString.append(" |");
        outputString.append(" #");
        outputString.append(borderSpaceHolder);

        System.out.println(outputString);
        outputString.setLength(0);

        //line6
        outputString.append(borderSpaceHolder);
        outputString.append("# ");
        outputString.append("- ".repeat((x_axis_map - 2 - borderSpaceHolder.length() * 2) / 4 - 1));
        outputString.append("-");
        outputString.append("   ");
        outputString.append("- ".repeat((x_axis_map - 2 - borderSpaceHolder.length() * 2) / 4 - 1));
        outputString.append("-");
        outputString.append(" #");
        outputString.append(borderSpaceHolder);

        System.out.println(outputString);
        outputString.setLength(0);

        System.out.println((x_axis_map - 2 - borderSpaceHolder.length() * 2) / 2 - 4);
    }

    private static void drawOneBlock(int startIndex) {

    }

    private static void drawZeroBlock() {
        outputString.setLength(0);
        String borderSpaceHolder = " ".repeat(border_width);

        for (int i =0; i< y_axis_map/2; i++) {
            outputString.append(borderSpaceHolder);
            outputString.append("#");
            outputString.append(" ".repeat(x_axis_map - 2 - borderSpaceHolder.length() * 2));
            outputString.append("#");
            outputString.append(borderSpaceHolder);

            System.out.println(outputString);
            outputString.setLength(0);
        }
    }

    private static void drawEquatorialLine() {
        outputString.setLength(0);
        String borderSpaceHolder = " ".repeat(border_width);

        String goEast = " Go East";
        String goWest = "Go West ";

        outputString.append(" ".repeat(borderSpaceHolder.length() - goWest.length()));
        outputString.append(goWest);
        outputString.append("#");
        int centerSpace = x_axis_map - 2 - borderSpaceHolder.length()*2;

        String title = "AREA " + gameMap.getPosition();
        int space = (centerSpace - title.length())/2;

        outputString.append(" ".repeat(space));
        outputString.append(title);
        outputString.append(" ".repeat(space +1));

        outputString.append("#");
        outputString.append(goEast);
        outputString.append(" ".repeat(borderSpaceHolder.length() - goEast.length()));

        System.out.println(outputString);
        outputString.setLength(0);
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

    private static void displayMapDescription() {
        //x_axis
    }

    private static void displaySquadStatus() {}

    private static void displayInventory() {}

    private static void displaySubAreaInMap() {

    }

    private static void displayPlayerCommand() {

    }

    private static void drawFooter() {
        outputString.setLength(0);
        outputString.append("-".repeat(x_axis_map));
        System.out.println(outputString);
        outputString.setLength(0);
    }
}
