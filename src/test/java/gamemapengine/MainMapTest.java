package gamemapengine;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

public class MainMapTest {

  @Test
  public void initializeMap() throws IOException {
    var map = new MainMap();

    map.initializeMap();

    int i = 1;

    for(var m : map.Game_Maps.keySet()) {
      var area = map.Game_Maps.get(m);
      area.forEach(a-> System.out.println(a.getName()));
      System.out.println("*****" + i++ + "*****");
    }
  }
}