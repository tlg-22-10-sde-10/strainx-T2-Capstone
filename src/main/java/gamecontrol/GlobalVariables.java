package gamecontrol;

import com.fasterxml.jackson.core.type.TypeReference;
import gamecontrol.contents.CrewMember;
import gamecontrol.contents.Enemy;
import gamecontrol.contents.Inventory;
import gamecontrol.contents.Item;
import gamemodel.mapengine.MainMap;
import gamemodel.mapengine.SubArea;

import gamemusic.AudioPlayer;
import gamemusic.MusicHelper;
import java.io.InputStream;
import java.util.Map;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.stream.Collectors;
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
    public static final int ENEMY_SQUAD_SIZE_CAP = 6;
    public static final int ITEM_QUANTITY_CAP = 6;
    private static final int INITIAL_COMPANION_SIZE = 3;

    public static final String DESTINATION = "Schrader Lab";
    public static final Enemy PATIENT_ZERO = new Enemy("patient zero", 1200, 20, "zombie");

    public static Enemy miniBoss = new Enemy();

    public static boolean defeatBoss = false;

    public static Map<String, Integer> DROP_RATE_MAP = new HashMap<>();
    public static final int DROP_RATE_ROLL = 100; //default is 100 unless the drop rate map is changed accordingly

    private static String passWord;

    public static Map<String, Integer> inGameCommands = new HashMap<>();

    public static SubArea currentSubAreaContents = new SubArea();

    public static MainMap inGameMap; //= new MainMap(3,3);
    //in game inventory
    public static Map<String, Item> InventoryMap = new HashMap<>();
    
    //squads
    public static List<CrewMember> mySquad = new ArrayList<>();
    public static List<Enemy> enemySquad = new ArrayList<>();

    //subArea
    public static List<SubArea> subAreasCollection = new ArrayList<>();
    public static List<Enemy> enemiesCollection = new ArrayList<>();
    public static List<CrewMember> squad = new ArrayList<>();

    //inventory from json file
    public static Inventory itemsCollection = new Inventory();

    //combat engine UI
    public static Map<String, String> combatCommandDescription = new HashMap<>();

    public static void gameInitialization() throws IOException {
        passWordInitialize();

        inventoryInitialize();

        subMapInitialize();

        dropRateMapInitialize();

        contentEnemyInitialize();

        miniBossInitialize();

        inGameMap.initializeMap();

        globalSquadInitialize();

        combatCommandInitialize();

        inGameCommandsInitialize();

        titleMusicInitialize();
    }

    private static void inGameCommandsInitialize() {
        /* Design Pattern For Commands Map
        * value 1-10: for general selections
        * value 11-20: for map commands
        * value 21-25: for subarea commands
        * value 26-30: for combat commands
        * value 31-40: for miscellaneous commands
        * value -1: for exit
        * other values: */
        //inGameCommands.put("", 0); //go back
        inGameCommands.put("0", 0); //go back

        inGameCommands.put("1", 1); //select index 1
        inGameCommands.put("2", 2); //select index 2
        inGameCommands.put("3", 3); //select index 3
        inGameCommands.put("4", 4); //select index 4
        inGameCommands.put("5", 5); //select index 5
        inGameCommands.put("6", 6); //select index 6
        inGameCommands.put("7", 7); //select index 7
        inGameCommands.put("8", 8); //select index 8
        inGameCommands.put("9", 9); //select index 9
        inGameCommands.put("10", 10); //select index 10

        inGameCommands.put("w", 11); //go up
        inGameCommands.put("s", 12); //go down
        inGameCommands.put("a", 13); //go west
        inGameCommands.put("d", 14); //go east
        inGameCommands.put("m", 15); //show minimap
        inGameCommands.put("i", 16); //show inventory
        inGameCommands.put("help", 17); //display help command
        inGameCommands.put("helpMap", 18); //display help command
        inGameCommands.put("helpmap", 18); //display help command
        inGameCommands.put("helpSubarea", 19); //display help command
        inGameCommands.put("helpsubarea", 19); //display help command
        inGameCommands.put("helpCombat", 20); //display help command
        inGameCommands.put("helpcombat", 20); //display help command
        inGameCommands.put("e", -1); //exit game
        inGameCommands.put("exit", -1); //exit game
        inGameCommands.put("q", -1); //exit game
        inGameCommands.put("quit", -1); //exit game

        inGameCommands.put("y", 21); //start combat
        inGameCommands.put("yes", 21); //start combat
        inGameCommands.put("n", 22); //leave area
        inGameCommands.put("no", 22); //leave area
        inGameCommands.put("", 22); //leave area
        inGameCommands.put("l", 24); //loot items
        inGameCommands.put("p", 24); //loot items
        inGameCommands.put("pick up", 24); //loot items

        inGameCommands.put("f", 26); //Attack Enemy
        inGameCommands.put("u", 27); //Use Items
//        inGameCommands.put("t", 23); //Play Tricks
        inGameCommands.put("c", 29); //Auto Combat
        inGameCommands.put("r", 30); //Retreat

        inGameCommands.put("musicon", 31); //start music
        inGameCommands.put("musicoff", 32); //stop music
        inGameCommands.put("v+", 33); //increase volume
        inGameCommands.put("v-", 34); //decrease volume

    }

    public static void titleMusicInitialize() {
        AudioPlayer.getInstance().playAudio();
    }

    public static void titleMusicStop() {
        AudioPlayer.getInstance().stopAudio();
    }

    public static String getPassWord() {
        return passWord;
    }

    private static void passWordInitialize() {
        Random rg = new Random();
        StringBuilder s = new StringBuilder();

        int passWordLength = 4;
        for(int i=0; i< passWordLength; i++) {
            var p = rg.nextInt(10);
            s.append(p);
        }
        passWord = s.toString();
    }

    private static void subMapInitialize() throws IOException {
        try (InputStream input = JsonParsing.openResource("locations.json")) {
            subAreasCollection = JsonParsing.getObjectMapper().readValue(input, new TypeReference<>() {});

            subAreasCollection.forEach(a->a.setDescription(convertDescription(a.getDescription())));
        }
    }

    private static void inventoryInitialize() throws IOException {
        try (InputStream input = JsonParsing.openResource("items.json")) {
            itemsCollection = JsonParsing.getObjectMapper().readValue(input, new TypeReference<>() {});
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
        combatCommandDescription.put("f", "Attack Enemy");
        combatCommandDescription.put("u", "Use Items");
//        combatCommandDescription.put("3", "Play Tricks");
        combatCommandDescription.put("c", "Auto Combat");
        combatCommandDescription.put("r", "Retreat");
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

    public static void miniBossInitialize() {
        Random rg = new Random();

        var miniBossList = enemiesCollection.stream().filter(e->e.getMaxHP()>300).collect(Collectors.toList());

        Enemy miniBossTemplate = null;

        if(enemiesCollection.size()>0) {
            miniBossTemplate = enemiesCollection.get(rg.nextInt(enemiesCollection.size()));
        }

        if (miniBossList.size()>0) {
            miniBossTemplate = miniBossList.get(rg.nextInt(miniBossList.size()));
        }

        if (miniBossTemplate != null) {
            GlobalVariables.miniBoss = MainMap.newEnemy(miniBossTemplate.getName());
        }

        enemiesCollection.remove(miniBossTemplate);
    }
}
