package gamemodel.combatengine;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.MainMap;
import java.io.IOException;

import org.junit.Test;
import ui.maps.UIEnterMainMap;

public class UICombatTest {

  @Test
  public void reportMySquadStatus() throws IOException {
    GlobalVariables.inGameMap = new MainMap();

    GlobalVariables.gameInitialization();

    UIEnterMainMap.drawFooter();

    GlobalVariables.mySquad.remove(3);

    UICombat.reportMySquadStatus();

    UIEnterMainMap.drawFooter();

  }
}