package CombatEngine;

import Contents.Enemy;
import Team_Member.Crew_Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static Client.GlobalVariables.*;
import static CombatEngine.UI_combat.*;

public class engage_enemy {
    private static final Random rg = new Random();

    public static ArrayList<Crew_Member> KIAList = new ArrayList<>();
    public static ArrayList<Enemy> enemyKIAList = new ArrayList<>();

    static boolean retreat = false;

    public static void gameEnginePrototype() {
        int round = 1;

        while (enemySquad.size() > 0 && mySquad.get(0).getHP() > 0) {
            reportCombatRounds(round);

            int mySquadInitiative = rg.nextInt(3);

           reportEngageStatus();

            retreat = false;

            reportInitiativeStatus(mySquadInitiative);

            if (mySquadInitiative > 0) {
                playerMove();
                if (retreat) {
                    break;
                }
                restOfMySquadMove();

                enemySquadMove();
            } else {
                enemySquadMove();

                playerMove();
                if (retreat) {
                    break;
                }
                restOfMySquadMove();
            }

            reportCombatResult();
            round += 1;
        }
    }

    private static void playerMove() {
        System.out.println("--------------------");
        System.out.println("make a move:");
        for (var key : combatCommandDescription.keySet()) {
            System.out.println(key + ". " + combatCommandDescription.get(key));
        }
        System.out.println("--------------------");

        Integer behaviour = null;

        while (behaviour == null) {
            Scanner s1 = new Scanner(System.in);
            var input = s1.nextLine();

            behaviour = combatCommandCode.get(input);

            if (behaviour == null) {
                System.out.println("Invalid Command");
            }
        }

        switch (behaviour) {
            case 1:
                playerAttackEnemy();
                break;
            case 2:
                playerUseItems();
                break;
            case 3:
                playerPlayTricks();
                break;
            case 4:
                playerAutoCombat(-1);
                break;
            case 5:
                playerRetreat();
                break;
            default:
        }
    }

    private static void playerAttackEnemy() {
        System.out.println("-----choose a target-----");

        HashMap<String, Integer> targets = new HashMap<>();
        for (int i = 0; i < enemySquad.size(); i++) {
            targets.put(String.valueOf(i + 1), i);
        }

        Integer target = null;
        while (target == null) {
            Scanner s1 = new Scanner(System.in);
            var selection = s1.nextLine();

            target = targets.get(selection);
            if (target == null) {
                System.out.println("-----Invalid Selection-----");
            }
        }

        playerAutoCombat(target);
    }

    private static void playerUseItems() {
    }

    private static void playerPlayTricks() {

    }

    private static void playerRetreat() {
        retreat = rg.nextBoolean();
        reportRetreatResults(retreat);
    }

    private static void playerAutoCombat(Integer target) {
        if(target < 0) {
            target = rg.nextInt(enemySquad.size());
        }

        Enemy enemy = enemySquad.get(target);
        var player = mySquad.get(0);
        var dmg = player.getAttack() + player.getWeapon().getWeapon_base_dmg();

        enemy.setHP(enemy.getHP() - dmg);
        if (enemy.getHP() <= 0) {
            enemySquad.remove(enemy);
            enemyKIAList.add(enemy);
        }
    }

    private static void restOfMySquadMove() {
        for (int i = 1; i < mySquad.size(); i++) {
            if (enemySquad.size() == 0) {
                break;
            }

            int target = rg.nextInt(enemySquad.size());
            int dmg = mySquad.get(i).getAttack() + mySquad.get(i).getWeapon().getWeapon_base_dmg();

            Enemy enemy = enemySquad.get(target);
            enemy.setHP(enemy.getHP() - dmg);

            if (enemy.getHP() <= 0) {
                enemySquad.remove(enemy);
                enemyKIAList.add(enemy);
            }
        }
    }

    private static void enemySquadMove() {
        for (Enemy en : enemySquad) {
            if (mySquad.get(0).getHP() <= 0) {
                break;
            }

            int target = rg.nextInt(mySquad.size());
            int dmg = en.getAttack();

            Crew_Member crew = mySquad.get(target);
            crew.setHP(crew.getHP() - dmg);

            if (crew.getHP() <= 0) {
                KIAList.add(crew);
                if (target != 0) {
                    mySquad.remove(crew);
                }
            }
        }
    }
}
