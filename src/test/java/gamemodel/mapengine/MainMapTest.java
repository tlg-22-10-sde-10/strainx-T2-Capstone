package gamemodel.mapengine;

import static gamecontrol.GlobalVariables.inGameMap;
import static org.junit.Assert.*;
import gamecontrol.GlobalVariables;

import java.io.IOException;

import java.util.HashMap;

import java.util.Map;
import org.junit.Test;

public class MainMapTest {

  @Test
  public void newEnemy() throws IOException {
    inGameMap = new MainMap();
    GlobalVariables.gameInitialization();
    String name = inGameMap.newEnemy("doctor").getName();
    assertEquals(name, "doctor");
  }

  @Test
  public void gameMapInitialize() throws IOException {
    inGameMap = new MainMap(4,4);
    GlobalVariables.gameInitialization();

    var co = inGameMap.gameMap.values();

    Map<String, Integer> result = new HashMap<>();

    for (var li : co) {
      for (var su : li) {
        if(su != null) {
          if(result.containsKey(su.getName())) {
            result.put(su.getName(), result.get(su.getName())+1);
          } else {
            result.put(su.getName(), 1);
          }
        } else {
          break;
        }
      }
    }

    for(var ke : result.keySet()) {
      System.out.println(ke + ": " + result.get(ke));
    }
    System.out.println(result.size());
  }
}