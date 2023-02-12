package gamemodel.mapengine;

import static org.junit.Assert.*;

import gamecontrol.GlobalVariables;
import java.io.IOException;
import org.junit.Test;

public class MainMapTest {

  @Test
  public void newEnemy() throws IOException {
    GlobalVariables.gameInitialization();
    String name = MainMap.newEnemy("doctor").getName();
    assertEquals(name, "doctor");
  }
}