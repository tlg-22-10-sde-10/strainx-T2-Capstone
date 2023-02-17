package gamemodel.combatengine;

import static gamecontrol.GlobalVariables.*;
import static gamemodel.combatengine.EngageEnemy.*;

import gamecontrol.contents.CrewMember;
import gamecontrol.contents.Enemy;
import java.util.ArrayList;
import java.util.List;
import ui.endgame.UIDisplayGameStatus;
import ui.endgame.UILosingPage;

public class UICombat {
    public static int x_axis = 96; // display column width

    static StringBuilder outputString = new StringBuilder();

    public static List<CrewMember> UIKIAList = new ArrayList<>();
    public static List<Enemy> UIEnemyKIAList = new ArrayList<>();

    public static void reportDefeated() {
        if (mySquad.get(0).HP <= 0) {
            UIDisplayGameStatus.displayInfo(UILosingPage.display());
        }
    }

    public static void reportCombatRounds(int round) {
        outputString.setLength(0);
        String combatRound = "  Round - " + round ;
        int space = (x_axis - combatRound.length()) / 2;

        outputString.append(" ".repeat(space));
        outputString.append(combatRound);
        outputString.append(" ".repeat(space));

        System.out.println(outputString);

        drawFooter();
    }


    // Refactored to Return String
    public static String reportInitiativeStatus(int mySquadInitiative) {
        var respond = "";
        if (mySquadInitiative > 0) {
            respond = "Your squad has won the initiative and attacks first.";
        } else {
            respond = "Enemy squad has won the initiative and attacks first.";
        }
        System.out.println(respond + "\n");
        return respond + "\n";
    }

    // Refactored to return a string with the details of the combat round
    public static String reportCombatProcess(int mySquadInitiative) {
        StringBuilder returnString = new StringBuilder();
        if (mySquadInitiative > 0) {
            if(mySquadAttacked) {
                returnString.append(reportMySquadMove());
            }
            if(enemyDmgMap.size() > 0) {
                returnString.append(reportEnemyMove());
            }
        } else {
            if(enemyDmgMap.size() > 0) {
                returnString.append(reportEnemyMove());
            }
            if(mySquadAttacked) {
                returnString.append(reportMySquadMove());
            }
        }
        if(enemySquad.size() == 0) {
            returnString.append(reportWinning());
        }
        drawFooter();
        return returnString.toString();
    }

    private static String reportWinning() {
        outputString.setLength(0);
        String winningStatement = "\33[33mYou Win!\33[0m";
        int space = (x_axis - winningStatement.length()) / 2 + 6;

        outputString.append(" ".repeat(space));
        outputString.append(winningStatement);

        System.out.println(outputString);
        return outputString.toString();
    }

    public static void reportRetreatResults(boolean retreat) {
        if (retreat) {
            System.out.println("Your squad retreated from the combat in one piece.");
        } else {
            System.out.println("The enemy presses the attack, retreat failed!");
        }
    }

    public static void reportPlayerMove() {
        outputString.setLength(0);

        for (var key : combatCommandDescription.keySet()) {
            String options = key + ". " + combatCommandDescription.get(key);
            outputString.append(options);
        }

        int whiteSpace = (x_axis - outputString.length())/(combatCommandDescription.size() + 1);
        outputString.setLength(0);

        for (var key : combatCommandDescription.keySet()) {
            String options = key + ". " + combatCommandDescription.get(key);
            outputString.append(" ".repeat(whiteSpace));
            outputString.append(options);
        }
        outputString.append(" ".repeat(whiteSpace));

        System.out.println(outputString);
        drawFooter();
    }

    private static void reportEngageStatusBody() {
        int lines = Math.max(mySquad.size(), enemySquad.size());
        for (int i = 0; i < lines; i++) {
            outputString.setLength(0);

            String mySquadLine = "";
            String enemySquadLine = "";

            if (i < mySquad.size()) {
                var crew = mySquad.get(i);

                mySquadLine = crew.getRank() + " " + crew.getName() +
                        " | HP: " + crew.getHP() + "/" + crew.getMaxHP() +
                        " | attack: " + (crew.getAttack() + crew.getWeapon().getWeapon_base_dmg());
                if (i == 0) {
                    mySquadLine = "\033[34m" + mySquadLine + "\033[0m";
                }
            }
            if (i < enemySquad.size()) {
                var enemy = enemySquad.get(i);

                enemySquadLine = (i+1) + ". " + enemy.getEnemyType() + " " + enemy.getName() +
                        " | HP: " + enemy.getHP() + "/" + enemy.getMaxHP() +
                        " | attack: " + enemy.getAttack();
            }

            int space = x_axis - enemySquadLine.length() - mySquadLine.length();

            if (i == 0) {
                space += 9;
            }

            outputString.append(mySquadLine);
            outputString.append(" ".repeat(space));
            outputString.append(enemySquadLine);

            System.out.println(outputString);
        }
        drawFooter();
    }

    private static void drawFooter() {
        outputString.setLength(0);
        outputString.append("-".repeat(x_axis));
        System.out.println(outputString);
        outputString.setLength(0);
    }

    public static void reportEngage() {
        reportEngageStatusBody();
    }

    public static String reportEnemyMove() {
        StringBuilder returnString = new StringBuilder();
        outputString.setLength(0);
        outputString.append("DMG Received since last action:\n");

        for(var name : enemyDmgMap.keySet()) {
            outputString.append(name);
            outputString.append(" (");
            String receivedDMG = "\033[31m-" + enemyDmgMap.get(name) + "\033[0m";
            outputString.append(receivedDMG);
            outputString.append("); ");
        }
        System.out.println(outputString);
        returnString.append(outputString).append("\n");
        outputString.setLength(0);

        if (UIKIAList.size() > 0) {
            for (var s : UIKIAList) {
                var line = "\033[31m" + s.rank + " " + s.getName() + "\033[0m"
                    + " is killed and turned into \033[35ma zombie\033[0m.\n";
                outputString.append(line);
            }
            UIKIAList.clear();
        }

        if(summonedMinion.size() > 0) {
            for (var e: summonedMinion) {
                System.out.println(
                    FINAL_BOSS.getName() + " reanimated a \033[35m"+ e.getEnemyType() + " " + e.getName() + "\033[0m to join the fight!\n");
            }
        }
        summonedMinion.clear();

        System.out.println(outputString);
        returnString.append(outputString);
        outputString.setLength(0);
        return returnString.toString();
    }

    public static String reportMySquadMove() {
        outputString.setLength(0);

        if (UIEnemyKIAList.size() == 0) {
            var line2 = "No enemies were killed since last action.\n";
            outputString.append(line2);
        } else {
            for(var e : UIEnemyKIAList) {
                var line = "\033[32m" + e.getEnemyType() +" " + e.getName() + "\033[0m" + " is destroyed.\n";
                outputString.append(line);
            }
            UIEnemyKIAList.clear();
        }
        System.out.println(outputString);
        return outputString.toString();
        //outputString.setLength(0);
    }

    public static void reportMySquadStatus() {
        int rows = (mySquad.size()-1)/2 + 1;
        int spaceHolder = 0;

        int maxAttack = 0;

        for (CrewMember crewMember : mySquad) {
            if (crewMember.getAttack() > maxAttack) {
                maxAttack = crewMember.getAttack();
            }
        }

        int attackLength = "Attack: ".length() + String.valueOf(maxAttack).length();

        int space = (x_axis-spaceHolder - attackLength)/5;

        for (int i = 0; i<=rows; i+=2) {
            String soldierLeftName = (i+1) +". "+ mySquad.get(i).getRank() + " " + mySquad.get(i).getName();
            String soldierLeftHP = "HP: " + mySquad.get(i).getHP() + "/" + mySquad.get(i).getMaxHP();
            String soldierLeftAttack = "Attack: " + (mySquad.get(i).getAttack() + mySquad.get(i).getWeapon().getWeapon_base_dmg());

            String soldierRightName = "";
            String soldierRightHP = "";
            String soldierRightAttack = "";

            if(i+1<mySquad.size()) {
                soldierRightName = (i+2) +". "+ mySquad.get(i+1).getRank() + " " + mySquad.get(i+1).getName();
                soldierRightHP = "HP: " + mySquad.get(i+1).getHP() + "/" + mySquad.get(i+1).getMaxHP();
                soldierRightAttack = "Attack: " + mySquad.get(i+1).getAttack();
            }

            int space1l = space - soldierLeftName.length();
            int space2l = space - soldierLeftHP.length();
            int space3l = space - soldierLeftAttack.length();

            outputString.append(soldierLeftName);
            outputString.append(" ".repeat(space1l));
            outputString.append(soldierLeftHP);
            outputString.append(" ".repeat(space2l));
            outputString.append(soldierLeftAttack);
            outputString.append(" ".repeat(space3l + spaceHolder));

            int space1r = space - soldierRightName.length();
            int space2r = space - soldierRightHP.length();

            outputString.append(soldierRightName);
            outputString.append(" ".repeat(space1r));
            outputString.append(soldierRightHP);
            outputString.append(" ".repeat(space2r));
            outputString.append(soldierRightAttack);

            System.out.println(outputString);
            outputString.setLength(0);
        }
    }
}
