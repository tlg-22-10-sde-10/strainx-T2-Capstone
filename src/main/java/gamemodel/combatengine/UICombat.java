package gamemodel.combatengine;

import static gamecontrol.GlobalVariables.*;
import static gamemodel.combatengine.EngageEnemy.KIAList;
import static gamemodel.combatengine.EngageEnemy.enemyKIAList;

import ui.endgame.UIDisplayGameStatus;
import ui.endgame.UILosingPage;
import ui.endgame.UIWinningPage;

public class UICombat {

    public static int x_axis = 96; // display column width

    static StringBuilder outputString = new StringBuilder();
    static String playerSideName = "My Squad";
    static String enemySideName = "Enemy Squad";

    public static final String FINAL_BOSS = "patient zero";

    public static void reportCombatRounds(int round) {
        drawFooter();

        outputString.setLength(0);
        String combatRound = "  Round " + round + "  ";
        int space = (x_axis - combatRound.length()) / 2;

        outputString.append(" ".repeat(space));
        outputString.append(combatRound);
        outputString.append(" ".repeat(space));

        System.out.println(outputString);

        drawFooter();
    }

    public static void reportInitiativeStatus(int mySquadInitiative) {
        if (mySquadInitiative > 0) {
            System.out.println("Enemy looks lost, let's take them down.");
        } else {
            System.out.println("Enemy attack.");
        }
        drawFooter();
    }

    public static void reportRetreatResults(boolean retreat) {
        if (retreat) {
            System.out.println("Your squad retreated from the combat in one piece.");
        } else {
            System.out.println("The enemy is not done with you yet.");
        }
    }

    public static void reportPlayerMove() {
        outputString.setLength(0);

        for (var key : combatCommandDescription.keySet()) {
            String options = key + ". " + combatCommandDescription.get(key) + "     ";
            outputString.append(options);
        }

        System.out.println(outputString);
        drawFooter();
        System.out.print("Waiting instructions >> ");
    }

    private static void reportEngageStatusHeader() {
        outputString.setLength(0);
        //header
        outputString.append(playerSideName);
        outputString.append(" ".repeat((x_axis - playerSideName.length() - playerSideName.length()) / 2 - 1));
        outputString.append("VS");
        outputString.append(" ".repeat((x_axis - enemySideName.length() - enemySideName.length()) / 2 - 1));
        outputString.append(enemySideName);

        System.out.println(outputString);
        drawFooter();

    }

    private static void reportEngageStatusInventory() {
        outputString.setLength(0);

        var weapon = mySquad.get(0).getWeapon();
        String inventory = "Weapon: " + weapon.getName();

        outputString.append(inventory);

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

                enemySquadLine = enemy.getEnemyType() + " " + enemy.getName() +
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
    }

    public static void reportEngage() {
        reportEngageStatusHeader();
        reportEngageStatusInventory();
        reportEngageStatusBody();
    }

    public static void reportMySquadStatus() {
        for (int i = 0; i < mySquad.size(); i++) {
            var crew = mySquad.get(i);
            if (i == 0) {
                System.out.println(crew.getRank() + " " + crew.getName() + " | HP:" + crew.getHP() + " | attack: " + (crew.getAttack() + crew.getWeapon().getWeapon_base_dmg()) + " | weapon: " + crew.getWeapon().getName());
            } else {
                System.out.println(crew.getRank() + " " + crew.getName() + " | HP:" + crew.getHP() + " | attack: " + (crew.getAttack() + crew.getWeapon().getWeapon_base_dmg()));
            }
        }
    }

    public static void reportEnemySquadStatus() {
        for (var enemy : enemySquad) {
            String output = enemy.getEnemyType() + " " + enemy.getName() + " | HP:" + enemy.getHP() + " | attack:" + enemy.getAttack();
            for (int i = 0; i < 60; i++) {
                output = " " + output;
            }
            System.out.println(output);
        }
    }

    public static void reportCombatResult(int mySquadInitiative) {
        String defeatedEnemy = "";
        String isAre = "is";

        outputString.setLength(0);

        String line1 = "";
        String line2 = "";
        if(mySquadInitiative > enemySquadInitiative) {
            line1 = "Report: our squad is engaging enemy. ";
        } else {
            line2 = "Report: enemy has broken through our defenses.";
        }

        line1 = "Report: our squad is engaging enemy. ";

        outputString.append(line1);

        outputString.append(line2);

        if (enemyKIAList.size() + KIAList.size() == 0) {
            line2 = "No one get killed in this round";
            outputString.append(line2);

            System.out.println("No one get killed in this round");
        } else {
            if (enemyKIAList.size() > 1) {
                isAre = "are";
            }
            for (var en : enemyKIAList) {
                if(en.getName().equals(FINAL_BOSS)) {
                    defeatBoss = true;
                }

                defeatedEnemy += en.getEnemyType() + " " + en.getName() + ", ";
            }
            if (enemyKIAList.size() > 0) {
                //when kill boss
                System.out.println("\033[31m" + defeatedEnemy + "\033[0m" + isAre + " destroyed in the fight.");
            }
            enemyKIAList.clear();

            String lostSoldier = "";
            if (KIAList.size() > 1) {
                isAre = "are";
            } else {
                isAre = "is";
            }
            for (var crew : KIAList) {
                lostSoldier += crew.getRank() + " " + crew.getName() + ", ";
            }
            if (KIAList.size() > 0) {
                System.out.println("\033[31m" + lostSoldier + "\033[0m" + isAre + " killed in the fight");
            }
            KIAList.clear();
        }

        if (mySquad.get(0).HP <= 0) {
            UIDisplayGameStatus.displayInfo(UILosingPage.display());

        } else if (enemySquad.size() == 0) {
            System.out.println("All enemies destroyed");
        }
    }
}
