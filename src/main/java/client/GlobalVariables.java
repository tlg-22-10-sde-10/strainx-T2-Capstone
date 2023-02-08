package client;

import com.fasterxml.jackson.core.type.TypeReference;
import contents.Enemy;
import contents.Inventory;
import contents.Item;
import gamemap.MainMap;
import gamemap.SubArea;
import java.io.InputStream;
import jsonparsing.JsonParsing;
import teammember.CrewMember;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GlobalVariables {

    public static void gameExitMessage() {
        System.out.println("Thank you for playing StrainX!");
    }

    public static void playerHelpCall() {
        System.out.println("Here are some tips:");
        System.out.println("You can interact with items, by typing get [item] or use [item].");
        System.out.println("You can move from your location to another location by typing go [direction].");
        System.out.println("Examples: go north or use shotgun");
    }

    //public static int exit_code = 0;
    public static int enemySquadInitiative = 0;
    public static final int ENEMY_SQUAD_SIZE_CAP = 6;
    public static final int ITEM_QUANTITY_CAP = 6;

    public static HashMap<String, Integer> DROP_RATE_MAP = new HashMap<>();

    public static SubArea current_subArea = new SubArea();

    public static MainMap gameMap = new MainMap(3,3);

    public static HashMap<String, Item> InventoryMap = new HashMap<>();
    //squads
    public static List<CrewMember> mySquad = new ArrayList<>();
    public static List<Enemy> enemySquad = new ArrayList<>();

    //subArea
    public static List<SubArea> subAreas = new ArrayList<>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();

    //inventory from json file
    public static Inventory inventory = new Inventory();

    //combat engine UI
    public static HashMap<String, String> combatCommandDescription = new HashMap<>();
    public static HashMap<String, Integer> combatCommandCode = new HashMap<>();

    public static void gameInitialization() throws IOException {
        inventoryInitialize();
        subMapInitialize();

        content_enemy_initialize();

        gameMap.initialize_map();

        global_squad_initialize();
        combatCommandInitialize();
    }

    private static void subMapInitialize() throws IOException {
        try (InputStream input = JsonParsing.openResource("locations.json")) {
            subAreas = JsonParsing.getObjectMapper().readValue(input, new TypeReference<>() {});
        }
    }

    private static void inventoryInitialize() throws IOException {
        try (InputStream input = JsonParsing.openResource("items.json")) {
            inventory = JsonParsing.getObjectMapper().readValue(input, new TypeReference<>() {});
        }
    }

    public static void dropRateInitialize() {
        DROP_RATE_MAP.put("common", 50);
        DROP_RATE_MAP.put("uncommon", 60);
        DROP_RATE_MAP.put("rare", 70);
        DROP_RATE_MAP.put("epic", 80);
        DROP_RATE_MAP.put("unique", 90);
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
        CrewMember p1 = new CrewMember("Player", "SGT", "none", 100, 20);
        CrewMember p2 = new CrewMember("james", "PV2", "none", 100, 20);
        CrewMember p3 = new CrewMember("john", "PFC", "none", 100, 20);
        CrewMember p4 = new CrewMember("jill", "SPC", "none", 100, 20);

        mySquad.add(p1);
        mySquad.add(p2);
        mySquad.add(p3);
        mySquad.add(p4);
    }

    public static void content_enemy_initialize() {
        var f1 = new Enemy("Soldier", "Zombie",120, 10, "none");
        //boss
        var f2 = new Enemy("Scientist", "Zombie",1000, 40, "none");
        var f3 = new Enemy("Researcher", "Zombie",110, 10, "none");
        var f4 = new Enemy("Nurse", "Zombie",130, 10, "none");
        //mini boss
        var f5 = new Enemy("Doctor", "Zombie",600, 10, "none");
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
