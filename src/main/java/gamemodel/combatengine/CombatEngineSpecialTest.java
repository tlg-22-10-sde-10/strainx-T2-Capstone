package gamemodel.combatengine;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.MainMap;
import java.io.IOException;

public class CombatEngineSpecialTest {

  public static void main(String[] args) throws IOException {
    GlobalVariables.inGameMap = new MainMap(4,4);

    GlobalVariables.gameInitialization();

    var subArea = GlobalVariables.inGameMap.gameMap.get(1).get(0);

    GlobalVariables.enemySquad = subArea.getContents().enemies;
    GlobalVariables.currentSubAreaContents = subArea;

    EngageEnemy.gameEnginePrototype();
  }
}
