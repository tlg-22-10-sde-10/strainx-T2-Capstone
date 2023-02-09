package gamemapengine;

import gamecontrol.GlobalVariables;
import java.io.IOException;
import org.junit.Test;

public class MainMapTest {

  @Test
  public void initializeMap() throws IOException {
    GlobalVariables.gameInitialization();

    int i = 1;

    for(var m : GlobalVariables.inGameMap.gameMaps.keySet()) {
      var area = GlobalVariables.inGameMap.gameMaps.get(m);
      area.forEach(a-> System.out.println(a.getName()));
      System.out.println("*****" + i++ + "*****");
    }
  }
}