package gamecontrol;

import static gamecontrol.GlobalVariables.inGameMap;

import gamemodel.mapengine.MainMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameDifficultySelection {

  private final static Scanner s = new Scanner(System.in);

  private final static Map<String, GameDifficulty> uiMainGameControl = new HashMap<>();

  public static void userSelection() {
    uiMainGameControlInitialize();

    while (true) {
      System.out.println("Please select game difficulty from the list below:\n");

      int i = 1;
      for(var v : uiMainGameControl.values().stream().distinct().collect(Collectors.toList())) {
        System.out.println(i + ". " + v.name() + "\n");
        i++;
      }

      String userInput = s.nextLine().toLowerCase(Locale.ROOT);

      if(uiMainGameControl.containsKey(userInput)) {
        inGameMap = new MainMap(uiMainGameControl.get(userInput));
        break;
      }

      System.out.println("Invalid Input");
    }
  }

  public static void uiMainGameControlInitialize() {
    uiMainGameControl.put("1", GameDifficulty.Easy);
    uiMainGameControl.put("2", GameDifficulty.Medium);
    uiMainGameControl.put("3", GameDifficulty.Hard);
    uiMainGameControl.put(GameDifficulty.Easy.name().toLowerCase(Locale.ROOT), GameDifficulty.Easy);
    uiMainGameControl.put(GameDifficulty.Medium.name().toLowerCase(Locale.ROOT), GameDifficulty.Medium);
    uiMainGameControl.put(GameDifficulty.Hard.name().toLowerCase(Locale.ROOT), GameDifficulty.Hard);
  }
}