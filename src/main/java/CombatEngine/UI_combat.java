package CombatEngine;

import static Client.GlobalVariables.enemySquad;
import static Client.GlobalVariables.mySquad;
import static CombatEngine.engage_enemy.*;

public class UI_combat {

    static final int X_axis = 96;

    public static void reportCombatRounds(int round) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("-".repeat(X_axis));
        outputString.append("\n");
        String combatRound = "Round: " + round;
        int space = (X_axis - combatRound.length()) / 2;

        outputString.append(" ".repeat(space));

        outputString.append(combatRound);

        outputString.append(" ".repeat(space));

        outputString.append("\n");

        outputString.append("-".repeat(X_axis));

        System.out.println(outputString);
    }

    public static void reportInitiativeStatus(int mySquadInitiative) {
        if (mySquadInitiative > 0) {
            System.out.println("our squad wins the initiative roll, we attack first");
        } else {
            System.out.println("The enemy wins the initiative roll, they attack first");
        }
    }

    public static void reportRetreatResults(boolean retreat) {
        if (retreat) {
            System.out.println("Your squad retreated from the combat in one piece.");
        } else {
            System.out.println("The enemy is not done with you yet.");
        }
    }

    public static void reportMySquadStatus() {
        System.out.println("--------Squad Status--------");
        for (int i = 0; i < mySquad.size(); i++) {
            var crew = mySquad.get(i);
            if (i == 0) {
                System.out.println(crew.getRank() + " " + crew.getName() + " | HP:" + crew.getHP() + " | attack: " + (crew.getAttack() + crew.getWeapon().getWeapon_base_dmg()) + " | weapon: " + crew.getWeapon().getWeapon_name());
            } else {
                System.out.println(crew.getRank() + " " + crew.getName() + " | HP:" + crew.getHP() + " | attack: " + (crew.getAttack() + crew.getWeapon().getWeapon_base_dmg()));
            }
        }
        System.out.println("----------------------------");
    }

    public static void reportEngageStatus() {
        StringBuilder outputString = new StringBuilder();
        //header
        outputString.append("My Squad");
        outputString.append(" ".repeat((X_axis - "My Squad".length() - "Enemy Squad".length()) / 2));
        outputString.append("VS");
        outputString.append(" ".repeat((X_axis - "My Squad".length() - "Enemy Squad".length()) / 2 - 1));
        outputString.append("Enemy Squad\n");

        //header inventory
        outputString.append("-".repeat(X_axis));
        outputString.append("\n");

        var weapon = mySquad.get(0).getWeapon();
        String inventory = "Weapon: " + weapon.getWeapon_name();

        outputString.append(inventory);
        outputString.append("\n");

        //body
        outputString.append("-".repeat(X_axis));
        outputString.append("\n");

        int lines = Math.max(mySquad.size(), enemySquad.size());
        for (int i = 0; i < lines; i++) {
            String mySquadLine = "";
            String enemySquadLine = "";

            if (i < mySquad.size()) {
                var crew = mySquad.get(i);

                mySquadLine = crew.getRank() + " " + crew.getName() +
                        " | HP: " + crew.getHP() + "/" + crew.getMaxHP() +
                        " | attack: " + (crew.getAttack() + crew.getWeapon().getWeapon_base_dmg());
                if(i == 0) {
                    mySquadLine = "\033[34m" + mySquadLine + "\033[0m";
                }
            }
            if (i < enemySquad.size()) {
                var enemy = enemySquad.get(i);

                enemySquadLine = enemy.getEnemy_type() + " " + enemy.getName() +
                        " | HP: " + enemy.getHP() + "/" + enemy.getMaxHP() +
                        " | attack: " + enemy.getAttack();
            }

            int space = X_axis - enemySquadLine.length() - mySquadLine.length();

            if(i ==0 ) {
                space += 9;
            }

            outputString.append(mySquadLine);

            outputString.append(" ".repeat(space));

            outputString.append(enemySquadLine);
            outputString.append("\n");
        }

        //footer
        outputString.append("-".repeat(X_axis));
        System.out.println(outputString);
    }

    public static void reportEnemySquadStatus() {
        System.out.println("--------Enemy Status--------");
        for (var enemy : enemySquad) {
            String output = enemy.getEnemy_type() + " " + enemy.getName() + " | HP:" + enemy.getHP() + " | attack:" + enemy.getAttack();
            for (int i = 0; i < 60; i++) {
                output = " " + output;
            }
            System.out.println(output);
        }
        System.out.println("----------------------------");
    }

    public static void reportCombatResult() {
        String defeatedEnemy = "";
        String isAre = "is";

        System.out.println("fight result:");

        if (enemyKIAList.size() + KIAList.size() == 0) {
            System.out.println("No one get killed in this round");
        } else {
            if (enemyKIAList.size() > 1) {
                isAre = "are";
            }
            for (var en : enemyKIAList) {
                defeatedEnemy += en.getEnemy_type() + " " + en.getName() + ", ";
            }
            if (enemyKIAList.size() > 0) {
                System.out.println("\033[1;46m" + defeatedEnemy + "\033[0m" + isAre + " destroyed in the fight.");
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
                System.out.println("\033[1;41m" + lostSoldier + "\033[0m" + isAre + " killed in the fight");
            }
            KIAList.clear();
        }

        if (mySquad.get(0).HP <= 0) {
            System.out.println("\033[1;41mmission fail\033[0m");
        } else if (enemySquad.size() == 0) {
            System.out.println("All enemies destroyed");
        }
    }
}
