package ui.maps;

import static org.junit.Assert.*;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.MainMap;
import java.io.IOException;
import org.junit.Test;

public class UIMiniMapTest {

  @Test
  public void displayMiniMap() throws IOException {
    GlobalVariables.inGameMap= new MainMap();

    GlobalVariables.gameInitialization();

    UIMiniMap.displayMiniMap();
  }

  @Test
  public void drawMiniMap3() throws IOException {
    GlobalVariables.inGameMap= new MainMap();

    GlobalVariables.gameInitialization();

    UIMiniMap.displayMiniMap();

   //GlobalVariables.inGameMap.setPosition(9);

    //UIMiniMap.drawMiniMap3();
  }
}