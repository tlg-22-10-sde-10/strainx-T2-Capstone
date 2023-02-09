package gamemodel.mapengine;

import static org.junit.Assert.*;

import gamecontrol.GlobalVariables;
import java.io.IOException;
import org.junit.Test;

public class MainMapTest {

  @Test
  public void mustInclude() throws IOException {
    GlobalVariables.gameInitialization();

    for(var k : GlobalVariables.inGameMap.gameMaps.keySet()) {
      var l = GlobalVariables.inGameMap.gameMaps.get(k);
      System.out.println("***" + k+ "***");
      for(var c : l) {
        System.out.println("*"+ c.getName()+ "*");
        if(c.getContents().items.size() > 0) {
          for(var i : c.getContents().items) {

            System.out.println(i.getName());
          }
        }
      }
    }
  }
}