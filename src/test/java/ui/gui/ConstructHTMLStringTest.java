package ui.gui;

import gamecontrol.GameDifficulty;
import gamecontrol.GlobalVariables;
import gamemodel.combatengine.EngageEnemy;
import gamemodel.combatengine.UICombat;
import gamemodel.mapengine.MainMap;
import gamemodel.mapengine.SubArea;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class ConstructHTMLStringTest {

    String testString;
    String realCombatTestString;
    String expectedString;
    @Before
    public void setUp() throws Exception {
        GlobalVariables.inGameMap = new MainMap(GameDifficulty.Hard);
        GlobalVariables.gameInitialization();
        for (List<SubArea> areasMap : GlobalVariables.inGameMap.gameMap.values()) {
            for(SubArea area : areasMap) {
                if(area.getContents().enemies.size() > 2) {
                    GlobalVariables.enemySquad = area.getContents().enemies;
                    break;
                }
            }
            if(!GlobalVariables.enemySquad.isEmpty()) {
                break;
            }
        }
        EngageEnemy.playerAutoCombat(-1);
        EngageEnemy.restOfMySquadMove();
        EngageEnemy.enemySquadMove();
        realCombatTestString = UICombat.reportCombatProcess(1);
        testString = "\\[0m \\[25m\\[0m\\[31m\\[32m\\[33m\\[35m\\[0m \n\\[31m";
    }

    @Test
    public void testThatParseCombatTestStringRemovesAllAnsiColorEscapeCodesInAString() {
        expectedString = "<html>\\</span> \\[25m\\</span>\\<span style='color: red;'>\\<span style='color: green;'>\\<span style='color: orange;'>\\<span style='color: purple;'>\\</span> <br/>\\<span style='color: red;'></html>";
        assertEquals(expectedString,ConstructHTMLString.parseCombatTextString(testString));
        assertTrue(ConstructHTMLString.parseCombatTextString(testString).contains("[25m"));
    }

    @Test
    public void testThatAStringDoesNotContainAnyEscapeCodesInItAfterFormatting() {
        String expected = ConstructHTMLString.parseCombatTextString(realCombatTestString);

        assertFalse(expected.contains("[0m"));
        assertFalse(expected.contains("[31m"));
        assertFalse(expected.contains("[32m"));
        assertFalse(expected.contains("[33m"));
        assertFalse(expected.contains("[35m"));
    }
}