package gamemodel.combatengine;

import static gamecontrol.GlobalVariables.*;
import static gamemodel.combatengine.EngageEnemy.*;
import static gamemodel.combatengine.EngageEnemy.KIAList;
import static gamemodel.combatengine.EngageEnemy.enemyKIAList;

import ui.endgame.UIDisplayGameStatus;
import ui.endgame.UILosingPage;

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

        outputString.setLength(0);
    }

    public static void reportStartInitiativeStatus(int mySquadInitiative) {
        drawFooter();

        var respond = "";
        if (mySquadInitiative > 0) {
            respond = "You squad has won the initiative and will attack first.";
            System.out.println(respond);
        } else {
            respond = "Enemy squad has won the initiative and will attack first.";
            System.out.println(respond);
            reportEnemyMove();
        }
        drawFooter();
    }

    public static void reportInitiativeStatus(int mySquadInitiative) {
        var respond = "";
        if (mySquadInitiative > 0) {
            respond = "You squad has won the initiative and will attack first.";
            System.out.println(respond);
            if(mySquadAttacked) {
                reportMySquadMove();
            }
            if(enemyDmgMap.size() > 0) {
                reportEnemyMove();
            }
        } else {
            respond = "Enemy squad has won the initiative and will attack first.";
            System.out.println(respond);
            if(enemyDmgMap.size() > 0) {
                reportEnemyMove();
            }
            if(mySquadAttacked) {
                reportMySquadMove();
            }
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
        outputString.setLength(0);
    }

    public static void reportEngage() {
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

    public static void reportEnemyMove() {
        outputString.setLength(0);
        outputString.append("\nTotal DMG Received since last action:\n");

        for(var name : enemyDmgMap.keySet()) {
            outputString.append(name);
            outputString.append(" (");
            String receivedDMG = "\033[31m-" + enemyDmgMap.get(name) + "\033[0m";
            outputString.append(receivedDMG);
            outputString.append("); ");
        }
        System.out.println(outputString);

        outputString.setLength(0);

        if (KIAList.size() > 0) {
            for (var s : KIAList) {
                var line = "\033[31m" + s.rank + " " + s.getName() + "\033[0m"
                    + " is killed and turned into \033[35ma zombie\033[0m.\n";
                outputString.append(line);
            }
            KIAList.clear();
        }

        if(summonedMinion.size() > 0) {
            for (var e: summonedMinion) {
                System.out.println(FINAL_BOSS + " reanimated a \033[35m"+ e.getEnemyType() + " " + e.getName() + "\033[0m to join the fight!\n");
            }
        }
        summonedMinion.clear();

        System.out.print(outputString);
        outputString.setLength(0);
    }

    public static void reportMySquadMove() {
        outputString.setLength(0);

        if (enemyKIAList.size() == 0) {
            var line2 = "No enemies were killed since last action.\n";
            outputString.append(line2);
        } else {
            for(var e : enemyKIAList) {
                var line = "\033[32m" + e.getEnemyType() +" " + e.getName() + "\033[0m" + " is destroyed.\n";
                outputString.append(line);
            }
            enemyKIAList.clear();
        }
        System.out.print(outputString);

        outputString.setLength(0);
    }

    public static void reportFinalCombatResult() {
        if (mySquad.get(0).HP <= 0) {
            UIDisplayGameStatus.displayInfo(UILosingPage.display());
        } else if (enemySquad.size() == 0) {
            System.out.println("All enemies destroyed");
        }
    }

    //    private static void reportEngageStatusHeader() {
//        outputString.setLength(0);
//        //header
//        outputString.append(playerSideName);
//        outputString.append(" ".repeat((x_axis - playerSideName.length() - playerSideName.length()) / 2 - 1));
//        outputString.append("VS");
//        outputString.append(" ".repeat((x_axis - enemySideName.length() - enemySideName.length()) / 2 - 1));
//        outputString.append(enemySideName);
//
//        System.out.println(outputString);
//        drawFooter();
//    }
//
//    private static void reportEngageStatusInventory() {
//        outputString.setLength(0);
//
//        var weapon = mySquad.get(0).getWeapon();
//        String inventory = "Weapon: " + weapon.getName();
//
//        outputString.append(inventory);
//
//        System.out.println(outputString);
//        drawFooter();
//    }
}
