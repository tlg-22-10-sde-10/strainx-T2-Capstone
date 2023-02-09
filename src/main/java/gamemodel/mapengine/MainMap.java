package gamemodel.mapengine;

import gamecontrol.contents.Enemy;
import gamecontrol.GlobalVariables;

import gamecontrol.contents.Item;
import gamecontrol.contents.ItemFactory;
import gamecontrol.contents.Weapon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static gamecontrol.GlobalVariables.*;

public class MainMap {
    private static final int DEFAULT_X = 3;
    private static final int DEFAULT_Y = 3;

    private static final String DESTINATION = "Schrader Lab";
    private static final Enemy PATIENT_ZERO = new Enemy("patient zero", 1200, 40, "zombie");

    private static final Random rg = new Random();

    public HashMap<Integer, ArrayList<SubArea>> gameMaps = new HashMap<>();

    private int position = 9;

    private final int dimensionX;
    private final int dimensionY;

    public int getPosition() {
        return position;
    }

    public void setPosition(int p) {
        this.position = p;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    private static Map<Integer, Item> distribution = new HashMap<>();

    public MainMap() {
        this.dimensionY = DEFAULT_Y;
        this.dimensionX = DEFAULT_X;
    }

    public MainMap(int x, int y) {
        if(x< DEFAULT_X) {
            x = DEFAULT_X;
        }
        if(y<DEFAULT_Y) {
            y = DEFAULT_Y;
        }

        this.dimensionY = y;
        this.dimensionX = x;
    }

    public static void mustIncludeInitialize() {
        //var ar15 =ItemFactory.createItem("ar-15");
        var m249 =ItemFactory.createItem("m249");
        var kit =ItemFactory.createItem("squad equipment upgrades");
        var meds =ItemFactory.createItem("first aid kit");
        var armor =ItemFactory.createItem("body armor");

        List<Item> mustIncludedItems = new ArrayList<>();

        mustIncludedItems.add(m249);
        mustIncludedItems.add(kit);
        mustIncludedItems.add(meds);
        mustIncludedItems.add(armor);

        int mapSize = inGameMap.dimensionX*inGameMap.dimensionY;

        while(mustIncludedItems.size() > 0) {
            int spawnLocation = rg.nextInt(mapSize);
            if (!distribution.containsKey(spawnLocation)) {
                distribution.put(spawnLocation, mustIncludedItems.get(0));
                mustIncludedItems.remove(0);
            }
        }
    }

    public void initializeMap() {
        mustIncludeInitialize();

        for(int i=1; i<= this.dimensionX*this.dimensionY; i++) {
            int subAreaNumbers = rg.nextInt(3) + 1;

            ArrayList<SubArea> mapBlock = new ArrayList<>();

            int room = rg.nextInt(subAreaNumbers);

            for (int j = 0; j< subAreaNumbers; j++) {
                SubArea thisSubArea = newSubArea();

                if(j==subAreaNumbers-1 && i==this.dimensionX*this.dimensionY
                && subAreas.stream().filter(s->s.getName().equals(DESTINATION)).count() > 0) {
                    thisSubArea = newSubArea(DESTINATION);
                }

//                if(j == room) {
//                    var amendedContent = thisSubArea.getContents();
//                    amendedContent.items.add(distribution.get(i));
//                    distribution.remove(i);
//                    thisSubArea.setContents(amendedContent);
//                }

                mapBlock.add(thisSubArea);
            }
            gameMaps.put(i, mapBlock);
        }
    }

    private SubArea newSubArea() {
        SubArea thisSubArea = new SubArea();

        if (subAreas.size() > 0) {

            SubArea subAreaTemplate = subAreas.get(rg.nextInt(subAreas.size()));

            var content = newContent();

            thisSubArea.setName(subAreaTemplate.getName());
            thisSubArea.setDescription(subAreaTemplate.getDescription());

            if(subAreaTemplate.getName().equals(DESTINATION)) {
                Enemy boss = PATIENT_ZERO;
                content.enemies.add(0, boss);

                thisSubArea.setContents(content);
            }

            thisSubArea.setContents(newContent());

            subAreaTemplate.setReUseCap(subAreaTemplate.getReUseCap() - 1);

            if (subAreaTemplate.getReUseCap() < 1) {
                GlobalVariables.subAreas.remove(subAreaTemplate);
            }

        }
        return thisSubArea;
    }

    private SubArea newSubArea(String subAreaName) {
        SubArea thisSubArea = new SubArea();

        if (subAreas.size() > 0) {

            Optional<SubArea> selectedSubArea = subAreas.stream()
                .filter(s->s.getName().equals(subAreaName)).findFirst();

            if (selectedSubArea.isPresent()) {
                var subAreaTemplate = selectedSubArea.get();
                subAreaTemplate.setReUseCap(subAreaTemplate.getReUseCap()-1);

                thisSubArea.setName(subAreaTemplate.getName());
                thisSubArea.setDescription(subAreaTemplate.getDescription());
                thisSubArea.setContents(new Content());

                if(subAreaTemplate.getName().equals(DESTINATION)) {

                    var content = thisSubArea.getContents();

                    Enemy boss = PATIENT_ZERO;
                    content.enemies.add(0, boss);

                    thisSubArea.setContents(content);
                }

                if (subAreaTemplate.getReUseCap() < 1) {
                    GlobalVariables.subAreas.remove(subAreaTemplate);
                }
            }


        }
        return thisSubArea;
    }

    private Content newContent() {
        Content contents = new Content();

        //create enemy list
        int enemySize = rg.nextInt(ENEMY_SQUAD_SIZE_CAP+1);

        for (int i = 0; i< enemySize; i++) {
            Enemy newEnemy = newEnemy();
            contents.enemies.add(newEnemy);
        }

        //create item list
        int itemSize = rg.nextInt(ITEM_QUANTITY_CAP);

        for (int i = 0; i< itemSize; i++) {
            Item newItem = newItem();

            int roll = rg.nextInt(DROP_RATE_ROLL + 1);

            if(DROP_RATE_MAP.get(newItem.getRarity()) < roll) {
                contents.items.add(newItem);
            }
        }
        return contents;
    }

    public static Enemy newEnemy() {
        int enemyIndex = rg.nextInt(enemiesCollection.size());
        var randomEnemy = enemiesCollection.get(enemyIndex);

        String EnemyName = randomEnemy.getName();
        String EnemyType = randomEnemy.getEnemyType();
        int EnemyHP = randomEnemy.getHP();
        int EnemyAttack = randomEnemy.getAttack();

        String EnemySpecialPower = randomEnemy.getSpecial_power();

        return new Enemy(EnemyName, EnemyType, EnemyHP, EnemyAttack, EnemySpecialPower);
    }

    private Item newItem() {
        return ItemFactory.createItem();
    }

    //correct
    public void goNorth() {
        if (this.position < this.dimensionX) {
            System.out.println("Can't across the border of this place");
        } else {
            System.out.println("You are going north");
            position -= this.dimensionX;
        }
    }


    public void goSouth() {
        if (this.position > this.dimensionX * (this.dimensionY -1)) {
            System.out.println("Can't across the border of this place");
        } else {
            System.out.println("You are going south");
            position +=this.dimensionX;
        }
    }

    public void goEast() {
        if ((this.position)%dimensionX == 0) {
            System.out.println("Can't across the border of this place");
        } else {
            System.out.println("You are going east");
            position +=1;
        }
    }

    public void goWest() {
        if ((this.position)%dimensionX == 1) {
            System.out.println("Can't across the border of this place");
        } else {
            System.out.println("You are going west");
            position -=1;
        }
    }
}
