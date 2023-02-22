package gamemodel.combatengine;
import gamecontrol.GlobalVariables;
import ui.gui.ConstructHTMLString;
import ui.gui.components.JOptionPanes;
import ui.gui.components.panels.CombatPanel;

import javax.swing.*;
import java.util.Random;

public class GUICombatEngine {

    private CombatPanel combatPanel;
    private int round;

    private int roundInitiative;

    private Random random = new Random();

    public GUICombatEngine(CombatPanel combatPanel) {
        setCombatPanel(combatPanel);
        setRound(1);
    }


    // This will be called by the setText() of the CombatPanel when you enter combat
    public void enterCombat() {
        initiativeResultString();
        if(getRoundInitiative()==0) {
            EngageEnemy.enemySquadMove();
        }
    }

    public void autoCombat() {
        initiativeResultString();
        if(getRoundInitiative()>0) {
            EngageEnemy.playerAutoCombat(-1);
            EngageEnemy.restOfMySquadMove();
            EngageEnemy.enemySquadMove();

        } else {
            EngageEnemy.enemySquadMove();
            EngageEnemy.playerAutoCombat(-1);
            EngageEnemy.restOfMySquadMove();
        }
    }

    public void targetedCombat(int target) {
        initiativeResultString();
        if(getRoundInitiative()>0) {
            EngageEnemy.playerAutoCombat(target);
            EngageEnemy.restOfMySquadMove();
            EngageEnemy.enemySquadMove();

        } else {
            EngageEnemy.enemySquadMove();
            EngageEnemy.playerAutoCombat(target);
            EngageEnemy.restOfMySquadMove();
        }
    }


    public String getResultText() {
        return ConstructHTMLString.parseCombatTextString(UICombat.reportCombatProcess(getRoundInitiative()));
    }

    private int determineRoundInitiative() {
        return random.nextInt(3);
    }

    public String initiativeResultString() {
        JFrame frame = (JFrame) getCombatPanel().getTopLevelAncestor();
        boolean lose = JOptionPanes.youLosePane(frame);
        boolean win = JOptionPanes.youWinPane(frame);
        if(GlobalVariables.enemySquad.isEmpty() && !lose && !win) {
            JOptionPanes.combatWon(frame);
            getCombatPanel().backToMap();
        }
        setRoundInitiative(determineRoundInitiative());
        return UICombat.reportInitiativeStatus(getRoundInitiative());
    }

    public String getRoundText() {
        return String.format("Round: %s", getRound());
    }

    public CombatPanel getCombatPanel() {
        return combatPanel;
    }

    public void setCombatPanel(CombatPanel combatPanel) {
        this.combatPanel = combatPanel;
    }

    public int getRound() {
        return round;
    }
    public void setRound(int round) {
        this.round = round;
    }
    public int getRoundInitiative() { return roundInitiative; }
    public void setRoundInitiative(int roundInitiative) { this.roundInitiative = roundInitiative; }

}
