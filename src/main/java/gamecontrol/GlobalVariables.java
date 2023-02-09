package gamecontrol;

import com.fasterxml.jackson.core.type.TypeReference;
import gamecontrol.contents.CrewMember;
import gamecontrol.contents.Enemy;
import gamecontrol.contents.Inventory;
import gamecontrol.contents.Item;
import gamemodel.mapengine.MainMap;
import gamemodel.mapengine.SubArea;

import java.io.InputStream;
import java.util.Map;
import java.util.Random;
import jsonparsing.JsonParsing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ui.maps.UIEnterSubarea;


public class GlobalVariables {

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
    private static final int INITIAL_COMPANION_SIZE = 3;

    public static boolean defeatBoss = false;

    public static Map<String, Integer> DROP_RATE_MAP = new HashMap<>();
    public static final int DROP_RATE_ROLL = 100; //default is 100 unless the drop rate map is changed accordingly

    public static Map<String, Integer> inGameCommands = new HashMap<>();

    public static SubArea currentSubAreaContents = new SubArea();

    public static MainMap inGameMap = new MainMap(3,3);

    public static HashMap<String, Item> InventoryMap = new HashMap<>();
    //squads
    public static List<CrewMember> mySquad = new ArrayList<>();
    public static List<Enemy> enemySquad = new ArrayList<>();

    //subArea
    public static List<SubArea> subAreas = new ArrayList<>();
    public static List<Enemy> enemiesCollection = new ArrayList<>();
    public static List<CrewMember> squad = new ArrayList<>();

    //inventory from json file
    public static Inventory inventory = new Inventory();

    //combat engine UI
    public static HashMap<String, String> combatCommandDescription = new HashMap<>();
    public static HashMap<String, Integer> combatCommandCode = new HashMap<>();

    public static void gameInitialization() throws IOException {
        inventoryInitialize();

        subMapInitialize();

        dropRateMapInitialize();

        contentEnemyInitialize();

        inGameMap.initializeMap();

        globalSquadInitialize();

        combatCommandInitialize();

        inGameCommandsInitialize();
    }

    private static void inGameCommandsInitialize() {
        /* Design Pattern For Commands Map
        * value 1-10: for general selections
        * value 11-20: for map commands
        * value 21-30: for combat commands
        * value 31-40: for inventory commands
        * value -1: for exit
        * other values: */

        inGameCommands.put("1", 1); //select index 1
        inGameCommands.put("2", 2); //select index 2
        inGameCommands.put("3", 3); //select index 3
        inGameCommands.put("4", 4); //select index 1
        inGameCommands.put("5", 5); //select index 1
        inGameCommands.put("6", 6); //select index 1
        inGameCommands.put("7", 7); //select index 1
        inGameCommands.put("8", 8); //select index 1
        inGameCommands.put("9", 9); //select index 1
        inGameCommands.put("10", 10); //select index 1

        inGameCommands.put("w", 18); //go up
        inGameCommands.put("s", 12); //go down
        inGameCommands.put("a", 14); //select index 1
        inGameCommands.put("d", 16); //select index 1
        inGameCommands.put("m", 15); //select index 1
        inGameCommands.put("i", 11); //select index 1
        inGameCommands.put("e", -1); //select index 1
        inGameCommands.put("h", 17); //select index 1

        inGameCommands.put("f", 21); //Attack Enemy
        inGameCommands.put("u", 22); //Use Items
        inGameCommands.put("t", 23); //Play Tricks
        inGameCommands.put("c", 24); //Auto Combat
        inGameCommands.put("r", 25); //Retreat
    }

    private static void subMapInitialize() throws IOException {
        try (InputStream input = JsonParsing.openResource("locations.json")) {
            subAreas = JsonParsing.getObjectMapper().readValue(input, new TypeReference<>() {});

            subAreas.forEach(a->a.setDescription(convertDescription(a.getDescription())));
        }
    }

    private static void inventoryInitialize() throws IOException {
        try (InputStream input = JsonParsing.openResource("items.json")) {
            inventory = JsonParsing.getObjectMapper().readValue(input, new TypeReference<>() {});
        }
    }

    public static void dropRateMapInitialize() {
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
            for(int i=0;i<INITIAL_COMPANION_SIZE; i++) {
                var p = squad.get(rg.nextInt(squad.size()));
                mySquad.add(p);
                squad.remove(p);
            }
        }
    }

    public static String convertDescription(String des) {
        StringBuilder convertedDescription = new StringBuilder();

        while(des.length() >= UIEnterSubarea.x_axis_subArea) {
            int index = UIEnterSubarea.x_axis_subArea;
            while (des.charAt(index-1) != ' ') {
                index--;
            }
            convertedDescription.append(des, 0, index);
            convertedDescription.append("\n");

            des = des.substring(index);
        }

        convertedDescription.append(des);
        return convertedDescription.toString();
    }

    public static void contentEnemyInitialize() throws IOException {
        try (InputStream input = JsonParsing.openResource("enemies.json")) {
            enemiesCollection = JsonParsing.getObjectMapper().readValue(input, new TypeReference<>() {});
        }
    }
}
