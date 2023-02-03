package Client;

import Contents.Enemy;
import GameMap.Main_Map;
import Team_Member.Crew_Member;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalVariables {

    public static int exit_code = 0;
    public static int enemySquadInitiative = 0;

    public static Main_Map gameMap = new Main_Map(3,3);

    //squads
    public static ArrayList<Crew_Member> mySquad = new ArrayList<>();
    public static ArrayList<Enemy> enemySquad = new ArrayList<>();

    //subArea
    public static ArrayList<String> subAreaNameList = new ArrayList<>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();

    //combat engine UI
    public static HashMap<String, String> combatCommandDescription = new HashMap<>();
    public static HashMap<String, Integer> combatCommandCode = new HashMap<>();

    public static void gameInitialization() {
        sub_area_name_initialize();
        content_enemy_initialize();

        gameMap.initial_map();

        global_squad_initialize();
        combatCommandInitialize();
    }

    private static void combatCommandInitialize() {
        combatCommandDescription.put("1", "Attack Enemy");
        combatCommandDescription.put("2", "Use Items");
        combatCommandDescription.put("3", "Play Tricks");
        combatCommandDescription.put("4", "Auto Combat");
        combatCommandDescription.put("5", "Retreat");

        combatCommandCode.put("1", 1);
        combatCommandCode.put("2", 2);
        combatCommandCode.put("3", 3);
        combatCommandCode.put("4", 4);
        combatCommandCode.put("5", 5);
    }

    private static void global_squad_initialize() {
        Crew_Member p1 = new Crew_Member("Player", "SGT", "none", 100, 20);
        Crew_Member p2 = new Crew_Member("james", "PV2", "none", 100, 20);
        Crew_Member p3 = new Crew_Member("john", "PFC", "none", 100, 20);
        Crew_Member p4 = new Crew_Member("jill", "SPC", "none", 100, 20);

        mySquad.add(p1);
        mySquad.add(p2);
        mySquad.add(p3);
        mySquad.add(p4);
    }

    public static void sub_area_name_initialize() {
        subAreaNameList.add("Laboratory");
        subAreaNameList.add("Factory");
        subAreaNameList.add("Warehouse");
        subAreaNameList.add("Town House");
        subAreaNameList.add("Park");
        subAreaNameList.add("Bank");
        subAreaNameList.add("Gun Shop");
        subAreaNameList.add("Town Center");
        subAreaNameList.add("Hospital");
        subAreaNameList.add("School");
        subAreaNameList.add("Library");
        subAreaNameList.add("Gas Station");
    }

    public static void content_enemy_initialize() {
        var f1 = new Enemy("Soldier", "Zombie",120, 10, "none");
        //boss
        var f2 = new Enemy("Scientist", "Zombie",1000, 40, "none");
        var f3 = new Enemy("Researcher", "Zombie",110, 10, "none");
        var f4 = new Enemy("Nurse", "Zombie",130, 10, "none");
        var f5 = new Enemy("Doctor", "Zombie",150, 10, "none");
        var f6 = new Enemy("Big Guy", "Zombie",200, 10, "none");
        var f7 = new Enemy("Police Officer", "Zombie",140, 10, "none");
        var f8 = new Enemy("Dog", "Zombie",80, 20, "none");

        enemies.add(f1);
        enemies.add(f2);
        enemies.add(f3);
        enemies.add(f4);
        enemies.add(f5);
        enemies.add(f6);
        enemies.add(f7);
        enemies.add(f8);
    }
}
