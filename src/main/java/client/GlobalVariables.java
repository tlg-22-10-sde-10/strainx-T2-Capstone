package client;

import com.fasterxml.jackson.core.type.TypeReference;
import contents.Enemy;
import contents.Inventory;
import contents.Item;
import gamemapengine.MainMap;
import gamemapengine.SubArea;
import java.io.InputStream;
import java.util.Random;
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
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<CrewMember> squad = new ArrayList<>();

    //inventory from json file
    public static Inventory inventory = new Inventory();

    //combat engine UI
    public static HashMap<String, String> combatCommandDescription = new HashMap<>();
    public static HashMap<String, Integer> combatCommandCode = new HashMap<>();

    public static void gameInitialization() throws IOException {
        inventoryInitialize();
        subMapInitialize();

        contentEnemyInitialize();

        gameMap.initializeMap();

        globalSquadInitialize();
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

    private static void globalSquadInitialize() throws IOException {
        try (InputStream input = JsonParsing.openResource("crew.json")) {
            squad = JsonParsing.getObjectMapper().readValue(input, new TypeReference<>() {});

            CrewMember p1 = new CrewMember("Player", "SGT", 100, 20);
            mySquad.add(p1);

            Random rg = new Random();
            for(int i=0;i<3; i++) {
                var p = squad.get(rg.nextInt(squad.size()));
                mySquad.add(p);
                squad.remove(p);
            }
        }
    }

    public static void contentEnemyInitialize() throws IOException {
        try (InputStream input = JsonParsing.openResource("enemies.json")) {
            enemies = JsonParsing.getObjectMapper().readValue(input, new TypeReference<>() {});
        }
    }
}
