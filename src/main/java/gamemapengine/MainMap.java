package gamemapengine;

import client.GlobalVariables;
import contents.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static client.GlobalVariables.*;

public class MainMap {
    private static final int DEFAULT_X = 3;
    private static final int DEFAULT_Y = 3;

    private static final Random rg = new Random();

    public HashMap<Integer, ArrayList<SubArea>> Game_Maps = new HashMap<>();

    private int position = 1;

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

    public void initializeMap() throws IOException {
        for(int i=1; i<= this.dimensionX*this.dimensionY; i++) {
            int subAreaNumbers = rg.nextInt(3) + 1;

            ArrayList<SubArea> mapBlock = new ArrayList<>();

            for (int j = 0; j< subAreaNumbers; j++) {
                SubArea newSubArea = newSubArea();
                mapBlock.add(newSubArea);
            }
            Game_Maps.put(i, mapBlock);
        }
    }

    private SubArea newSubArea() throws IOException {
        SubArea subArea = new SubArea();

        var thisSubAreas = SubAreaFactory.subAreaHashMap();

        if (thisSubAreas.size() > 0) {
            var subAreasNames = thisSubAreas.keySet().toArray();
            String name = subAreasNames[rg.nextInt(thisSubAreas.size())].toString();

            int cap = thisSubAreas.get(name).getReUseCap();

            subArea.setReUseCap(cap);

            subArea.setName(name);

            String description = thisSubAreas.get(name).getDescription();

            subArea.setDescription(description);

            Content contents = newContent();

            subArea.setContents(contents);

            thisSubAreas.get(name).setReUseCap(cap-1);

            if(thisSubAreas.get(name).getReUseCap()<1) {
                GlobalVariables.subAreas.remove(thisSubAreas.get(name));
            }
        }
        return subArea;
    }

    private Content newContent() {
        Content contents = new Content();

        //create enemy list
        int enemySize = 0;

        if (rg.nextInt(3) > 0) {
            enemySize = rg.nextInt(ENEMY_SQUAD_SIZE_CAP)+1;
        }

        for (int i = 0; i< enemySize; i++) {
            Enemy newEnemy = newEnemy();
            contents.enemies.add(newEnemy);
        }

        //create item list
        int itemSize = 0;
        if(rg.nextInt(3) > 0) {
            itemSize = rg.nextInt(ITEM_QUANTITY_CAP);
        }

        dropRateInitialize();

        for (int i = 0; i< itemSize; i++) {
            Item newItem = newItem();

            int roll = rg.nextInt(100) + 1;

            if(DROP_RATE_MAP.get(newItem.getRarity()) < roll) {
                contents.items.add(newItem);
            }
        }
        return contents;
    }

    private Enemy newEnemy() {
        int enemyIndex = rg.nextInt(enemies.size());
        var randomEnemy = enemies.get(enemyIndex);

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
