package gamemodel.combatengine;

import gamecontrol.contents.Enemy;
import gamecontrol.contents.CrewMember;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ui.UICommandHelper;
import ui.inventory.UIInventory;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import ui.maps.UIEnterSubarea;

import static gamecontrol.GlobalVariables.*;
import static gamemodel.combatengine.UICombat.*;

public class EngageEnemy {
    private static final Random rg = new Random();
    private static final Scanner s1 = new Scanner(System.in);

    private static List<CrewMember> KIAList = new ArrayList<>();
    private static List<Enemy> enemyKIAList = new ArrayList<>();

    public static Map<String, Integer> enemyDmgMap = new HashMap<>();
    public static List<Enemy> summonedMinion = new ArrayList<>();
    public static boolean mySquadAttacked = false;

    static boolean retreat = false;
    private static boolean isInvalidCommand = true;

    private static int rounds = 1;

    public static void gameEnginePrototype() {
        rounds = 1;
        mySquadAttacked = false;

        int mySquadInitiative = 0;

        while (enemySquad.size() > 0 && mySquad.get(0).getHP() > 0) {
            System.out.println("\n\n");
            mySquadInitiative = rg.nextInt(3);

            retreat = false;

            if (mySquadInitiative > 0) {
                reportCombatRounds(rounds);
                reportInitiativeStatus(mySquadInitiative);
                reportCombatProcess(mySquadInitiative);
                reportEngage();

                enemyDmgMap.clear();

                playerMove();
                if (retreat) {
                    break;
                }

                restOfMySquadMove();

                enemySquadMove();
            } else {
                enemySquadMove();

                reportCombatRounds(rounds);
                reportInitiativeStatus(mySquadInitiative);
                reportCombatProcess(mySquadInitiative);
                reportEngage();

                enemyDmgMap.clear();

                if(mySquad.get(0).getHP() <= 0){
                    break;
                }

                playerMove();
                if (retreat) {
                    break;
                }

                restOfMySquadMove();
            }

            if(enemyKIAList.stream().anyMatch(e -> e.getName().equals(FINAL_BOSS.getName()))) {
                defeatBoss = true;
            }
            rounds += 1;
        }

        if(!retreat) { reportCombatProcess(mySquadInitiative); }
        reportEngage();

        if(mySquad.get(0).getHP() <= 0) { reportDefeated(); }

        System.out.println("Press any key to continue...");
        s1.nextLine();

        enemyKIAList.clear();
        enemyDmgMap.clear();
    }

    private static void playerMove() {
        reportPlayerMove();

        isInvalidCommand = true;

        while (isInvalidCommand) {

            int thisCommandCode;

            while(true) {
                System.out.print("Waiting Instructions >> ");
                String userInput = s1.nextLine().toLowerCase();

                if(inGameCommands.containsKey(userInput)) {
                    thisCommandCode = inGameCommands.get(userInput);
                    break;
                }
                System.out.println("Invalid Command");
            }

            switch (thisCommandCode) {
                case 26:
                    isInvalidCommand = false;

                    int playersTarget = playerTargetedEnemy();

                    if(!isInvalidCommand) {
                        playerAutoCombat(playersTarget);
                    }
                    break;
                case 27:
                    isInvalidCommand = false;
                    playerUseItems();
                    break;
                case 29:
                    isInvalidCommand = false;
                    playerAutoCombat(-1);
                    break;
                case 30:
                    isInvalidCommand = false;
                    playerRetreat();
                    break;
                case 17:
                    UICommandHelper.showHelp();
                    System.out.println("Press any key to continue...");
                    s1.nextLine();
                    reportCombatRounds(rounds);
                    reportEngage();
                    reportPlayerMove();
                    break;
                case 20:
                    isInvalidCommand = false;
                    UICommandHelper.showHelpCombat();
                    break;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }

    private static int playerTargetedEnemy() {
        System.out.println("-----Select an enemy by entering their number-----");

        int target;

        while (true) {
            var selection = s1.nextLine().toLowerCase();

            if(inGameCommands.containsKey(selection)) {
                if(selection.equals("") || (inGameCommands.get(selection)>0 && inGameCommands.get(selection)<=enemySquad.size())) {
                    target = inGameCommands.get(selection);
                    break;
                }
                System.out.println("-----Invalid Selection-----");
            }
        }

        if(target == 22) {
            isInvalidCommand = true;
        } else {
            target -= 1;
        }

        return target;
    }

    private static void playerUseItems() {

        UIInventory.displayInventoryList();
    }

    // Refactored to return a string as well
    private static String playerRetreat() {
        retreat = rg.nextBoolean();
        return reportRetreatResults(retreat);
    }

    public static void playerAutoCombat(int target) {
        if(target == -1) {
            target = rg.nextInt(enemySquad.size());
        }

        mySquadAttacked = true;

        Enemy enemy = enemySquad.get(target);
        var player = mySquad.get(0);
        var dmg = player.getAttack() + player.getWeapon().getWeapon_base_dmg();

        enemy.setHP(enemy.getHP() - dmg);
        if (enemy.getHP() <= 0) {
            enemySquad.remove(enemy);
            enemyKIAList.add(enemy);
            UIEnemyKIAList.add(enemy);
        }
    }

    public static void restOfMySquadMove() {
        for (int i = 1; i < mySquad.size(); i++) {
            if (enemySquad.size() == 0) {
                break;
            }

            mySquadAttacked = true;

            int target = rg.nextInt(enemySquad.size());
            int dmg = mySquad.get(i).getAttack() + mySquad.get(i).getWeapon().getWeapon_base_dmg();

            Enemy enemy = enemySquad.get(target);
            enemy.setHP(enemy.getHP() - dmg);

            if (enemy.getHP() <= 0) {
                enemySquad.remove(enemy);
                enemyKIAList.add(enemy);
                UIEnemyKIAList.add(enemy);
            }
        }
    }

    public static void enemySquadMove() {
        for (Enemy en : enemySquad) {
            if (mySquad.get(0).getHP() <= 0) {
                UIEnterSubarea.setExitSubAreaUI(true);
            }

            int target = rg.nextInt(mySquad.size());
            int dmg = en.getAttack();

            CrewMember crew = mySquad.get(target);
            crew.setHP(crew.getHP() - dmg);

            var targetName = crew.getRank() + " " + crew.getName();
            if(enemyDmgMap.containsKey(targetName)) {
                dmg += enemyDmgMap.get(targetName);
            }
            enemyDmgMap.put(targetName, dmg);

            if (crew.getHP() <= 0 && !KIAList.contains(crew)) {
                KIAList.add(crew);
                UIKIAList.add(crew);
                if (target != 0) {
                    mySquad.remove(crew);
                }
            }
        }

        reanimateDead();
    }

    private static void reanimateDead() {
        if (enemySquad.size() > 0) {
            //reanimate dead soldiers
            if (enemySquad.get(0).getEnemyType().equals("zombie")) {
                for (int i = 0; i < KIAList.size(); i++) {
                    Enemy reanimatedTeamMate = inGameMap.newEnemy();
                    reanimatedTeamMate.setName(KIAList.get(i).getName());
                    enemySquad.add(reanimatedTeamMate);
                }
                KIAList.clear();
            }

            //enemy summon minions
            if (enemySquad.get(0).getName().equals(FINAL_BOSS.getName()) && rounds % 5 == 0
                && enemySquad.size() < 5) {
                Enemy ens = inGameMap.newEnemy();
                if (ens.getHP() < 200) {
                    enemySquad.add(ens);
                    summonedMinion.add(ens);
                }
            }
        }
    }

    public static List<Enemy> getEnemyKIAList() {
        return enemyKIAList;
    }
}