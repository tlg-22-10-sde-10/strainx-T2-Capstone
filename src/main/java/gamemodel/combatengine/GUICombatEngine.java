package gamemodel.combatengine;

import gamemodel.mapengine.SubArea;

public class GUICombatEngine {

    private SubArea subArea;
    private int round;

    public GUICombatEngine(SubArea subArea) {
        setSubArea(subArea);
        setRound(0);
    }


    // This will be called by the setText() of the CombatPanel when you enter combat
    public String enterCombat() {

        // Determine who has initiative
        // determineRoundInitiative();
        // If enemies get initiative append to returnString "Enemy squad has won the initiative and attacks first."
        // else Your squad has won the initiative and attacks first.
        // If enemies have initiative, they attack when you enter the combat
        //      Enemies attack at random: target = (currentHp - attack)
        // Determine the damage for the round to display (how much each character took)

        // Set round to 1;
        // Returns a string with what happened upon entering combat
        return "";
    }

    private int determineRoundInitiative() {
        int roundInitiative;

        return -1;
    }


    public SubArea getSubArea() {
        return subArea;
    }

    public void setSubArea(SubArea subArea) {
        this.subArea = subArea;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
