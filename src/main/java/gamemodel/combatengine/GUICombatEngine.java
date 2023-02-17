package gamemodel.combatengine;

import gamemodel.mapengine.SubArea;
import ui.gui.ConstructHTMLString;
import ui.gui.components.panels.CombatPanel;
import ui.gui.components.panels.SubareaPanel;

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
    public String enterCombat() {
        StringBuilder returnString = new StringBuilder();
        // Determine who has initiative
        setRoundInitiative(determineRoundInitiative());
        returnString.append(initiativeResultString());
        // If enemies get initiative append to returnString "Enemy squad has won the initiative and attacks first."
        if(getRoundInitiative()==0) {
            EngageEnemy.enemySquadMove();
        }
        // else Your squad has won the initiative and attacks first.
        // If enemies have initiative, they attack when you enter the combat
        //      Enemies attack at random: target = (currentHp - attack)
        // Determine the damage for the round to display (how much each character took)

        // Set round to 1;
        // Returns a string with what happened upon entering combat
        return "";
    }

    public void autoCombat() {
        String initiative = initiativeResultString();
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

    public String getResultText() {
        return ConstructHTMLString.parseCombatTextString(UICombat.reportCombatProcess(getRoundInitiative()));
    }

    private int determineRoundInitiative() {
        return random.nextInt(3);
    }

    public String initiativeResultString() {
        return UICombat.reportInitiativeStatus(determineRoundInitiative());
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
