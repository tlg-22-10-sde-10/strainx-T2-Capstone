package Client;

import GameMap.Main_Map;

import java.util.Map;

public class GlobalVariables {

    public static int exit_code = 0;

    public static Main_Map gameMap = new Main_Map(3,3);

    public static void gameInitialization() {
        gameMap.initial_map();
    }
}
