package UI.Maps;

import GameMap.Main_Map;

import static Client.GlobalVariables.gameMap;

public class UI_mini_map {

    private static final StringBuilder outputString = new StringBuilder();

    private static final int x_axis_map = 95; //need to be an odd number for map display

    private static final int y_axis_map = 13; //need to be an odd number for map display

    private static final int border_width = 11;

    static Main_Map mainMap = gameMap;

    public static void displayMainMapUI() {

    }

    private static void drawFooter() {
        outputString.setLength(0);
        outputString.append("-".repeat(x_axis_map));
        System.out.println(outputString);
        outputString.setLength(0);
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
}
