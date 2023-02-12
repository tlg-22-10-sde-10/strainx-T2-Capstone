package gamemodel.mapengine;

import static gamecontrol.GlobalVariables.DESTINATION;
import static gamecontrol.GlobalVariables.DROP_RATE_MAP;
import static gamecontrol.GlobalVariables.DROP_RATE_ROLL;
import static gamecontrol.GlobalVariables.ENEMY_SQUAD_SIZE_CAP;
import static gamecontrol.GlobalVariables.ITEM_QUANTITY_CAP;
import static gamecontrol.GlobalVariables.PATIENT_ZERO;
import static gamecontrol.GlobalVariables.enemiesCollection;
import static gamecontrol.GlobalVariables.inGameMap;

import static gamecontrol.GlobalVariables.miniBoss;
import static gamecontrol.GlobalVariables.subAreasCollection;

import gamecontrol.GlobalVariables;
import gamecontrol.contents.Enemy;
import gamecontrol.contents.Item;
import gamecontrol.contents.ItemFactory;
import gamecontrol.contents.KeyItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class MainMap {

  private static final int DEFAULT_X = 3;
  private static final int DEFAULT_Y = 3;

  private static final Random rg = new Random();

  public HashMap<Integer, List<SubArea>> gameMaps = new HashMap<>();

  private int position = 1;

  private static int dimensionX;
  private static int dimensionY;

  private static int miniBossSpawnMap = 1;
  private static SubArea miniBossSubArea = new SubArea();

  private static int miniBossSpawnSubArea = 0;
  private static SubArea bossSubArea = new SubArea();

  private static int bossSpawnMap = 1;
  private static int bossSpawnSubArea = 0;

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

  private static final Map<Integer, Item> distribution = new HashMap<>();

  public MainMap() {
    dimensionY = DEFAULT_Y;
    dimensionX = DEFAULT_X;
  }

  public MainMap(int x, int y) {
    if (x < DEFAULT_X) {
      x = DEFAULT_X;
    }
    if (y < DEFAULT_Y) {
      y = DEFAULT_Y;
    }

    dimensionY = y;
    dimensionX = x;
  }

  private static void generateBoss() {
    bossSpawnMap = rg.nextInt(dimensionX*dimensionY) + 1;

    bossSubArea = inGameMap.newSubArea(DESTINATION);

    var contents = bossSubArea.getContents();
    contents.items.clear();

    bossSubArea.setContents(contents);

    bossSpawnSubArea = rg.nextInt(3);
  }

  private static void generateMiniBoss() {
    do {
      miniBossSpawnMap = rg.nextInt(dimensionX*dimensionY) +1;
    }
    while (miniBossSpawnMap==bossSpawnMap);

    SubArea miniBossRoom = inGameMap.newSubArea();

    var contents = miniBossRoom.getContents();

    KeyItem passWordNote = new KeyItem();
    passWordNote.setQty(1);
    passWordNote.setDescription("In case I can't remember! " + GlobalVariables.getPassWord());
    passWordNote.setName("An odd note");
    passWordNote.setDamage(0);
    passWordNote.setHealth(0);
    passWordNote.setRarity("unique");
    contents.items.add(passWordNote);

    var enemies = contents.enemies;
    if(enemies.size() > 0) {
      enemies.set(0, miniBoss);
    } else {
      enemies.add(miniBoss);
    }

    miniBossRoom.setContents(contents);
    miniBossSubArea = miniBossRoom;

    miniBossSpawnSubArea = rg.nextInt(3);
    System.out.println(miniBoss.getName());
    System.out.println(miniBossSpawnMap);
    System.out.println(miniBossSpawnSubArea);
  }

  public static void mustIncludeInitialize() {
    var m249 = ItemFactory.createItem("m249");
    var kit = ItemFactory.createItem("squad equipment upgrades");
    var meds = ItemFactory.createItem("first aid kit");
    var armor = ItemFactory.createItem("body armor");

    List<Item> mustIncludedItems = new ArrayList<>();

    mustIncludedItems.add(m249);
    mustIncludedItems.add(kit);
    mustIncludedItems.add(meds);
    mustIncludedItems.add(armor);

    int mapSize = dimensionX * dimensionY;

    while (mustIncludedItems.size() > 0) {
      int spawnLocation = rg.nextInt(mapSize);
      if(spawnLocation != bossSpawnMap) {
        if (!distribution.containsKey(spawnLocation)) {
          distribution.put(spawnLocation, mustIncludedItems.get(0));
          mustIncludedItems.remove(0);

        }
      }
    }
  }

  //can be optimized
  public void initializeMap() {
    mustIncludeInitialize();
    generateBoss();
    generateMiniBoss();

    for (int i = 1; i <= dimensionX * dimensionY; i++) {
      int subAreaNumbers = rg.nextInt(3) + 1;

      ArrayList<SubArea> mapBlock = new ArrayList<>();

      int room = rg.nextInt(subAreaNumbers);

      for (int j = 0; j < subAreaNumbers; j++) {
        SubArea thisSubArea = newSubArea();

//        if (j == subAreaNumbers - 1 && i == this.dimensionX * this.dimensionY
//            && subAreasCollection.stream().anyMatch(s -> s.getName().equals(DESTINATION))) {
//          thisSubArea = newSubArea(DESTINATION);
//        }

        if(i == bossSpawnMap && (j == bossSpawnSubArea || j == subAreaNumbers - 1)) {
          thisSubArea = bossSubArea;
        }

        if (i == miniBossSpawnMap && (j == miniBossSpawnSubArea || j == subAreaNumbers - 1)) {
          thisSubArea = miniBossSubArea;
        }

        //assign must spawn items into the map
        if (distribution.containsKey(i - 1) && j == room) {
          var amendedContent = thisSubArea.getContents();
          amendedContent.items.add(distribution.get(i - 1));

          distribution.remove(i - 1);

          thisSubArea.setContents(amendedContent);
        }

        mapBlock.add(thisSubArea);
      }
      gameMaps.put(i, mapBlock);
    }
  }

  private SubArea newSubArea() {
    SubArea thisSubArea = new SubArea();

    if (subAreasCollection.size() > 0) {
      SubArea subAreaTemplate = subAreasCollection.get(rg.nextInt(subAreasCollection.size()));

      var content = newContent();

//      if (subAreaTemplate.getName().equals(DESTINATION)) {
//        content.enemies.add(0, PATIENT_ZERO);
//      }

      thisSubArea.setName(subAreaTemplate.getName());
      thisSubArea.setDescription(subAreaTemplate.getDescription());
      thisSubArea.setContents(content);

      subAreaTemplate.setReUseCap(subAreaTemplate.getReUseCap() - 1);

      if (subAreaTemplate.getReUseCap() < 1) {
        GlobalVariables.subAreasCollection.remove(subAreaTemplate);
      }
    }
    return thisSubArea;
  }

  private SubArea newSubArea(String subAreaName) {
    SubArea thisSubArea = new SubArea();

    if (subAreasCollection.size() > 0) {
      Optional<SubArea> selectedSubArea = subAreasCollection.stream()
          .filter(s -> s.getName().equals(subAreaName)).findFirst();

      if (selectedSubArea.isPresent()) {
        var subAreaTemplate = selectedSubArea.get();
        subAreaTemplate.setReUseCap(subAreaTemplate.getReUseCap() - 1);

        thisSubArea.setName(subAreaTemplate.getName());
        thisSubArea.setDescription(subAreaTemplate.getDescription());
        thisSubArea.setContents(new Content());

        if (subAreaTemplate.getName().equals(DESTINATION)) {
          var content = thisSubArea.getContents();

          content.enemies.add(0, PATIENT_ZERO);

          thisSubArea.setContents(content);
        }

        if (subAreaTemplate.getReUseCap() < 1) {
          GlobalVariables.subAreasCollection.remove(subAreaTemplate);
        }
      }
    }
    return thisSubArea;
  }

  private Content newContent() {
    Content contents = new Content();

    //create enemy list
    int enemySize = rg.nextInt(ENEMY_SQUAD_SIZE_CAP + 1);

    for (int i = 0; i < enemySize; i++) {
      Enemy newEnemy = newEnemy();
      contents.enemies.add(newEnemy);
    }

    //create item list
    int itemSize = rg.nextInt(ITEM_QUANTITY_CAP);

    for (int i = 0; i < itemSize; i++) {
      Item newItem = newItem();

      int roll = rg.nextInt(DROP_RATE_ROLL + 1);

      if (DROP_RATE_MAP.get(newItem.getRarity()) < roll) {
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

    int EnemyHP =
        randomEnemy.getHP() + rg.nextInt(randomEnemy.getHP() / 5) - randomEnemy.getHP() / 10;
    int EnemyAttack = randomEnemy.getAttack() + rg.nextInt(randomEnemy.getAttack() / 5)
        - randomEnemy.getAttack() / 10;

    String EnemySpecialPower = randomEnemy.getSpecial_power();

    return new Enemy(EnemyName, EnemyType, EnemyHP, EnemyAttack, EnemySpecialPower);
  }

  public static Enemy newEnemy(String name) {
    var enemy = new Enemy();

    var enemyTemplate = enemiesCollection.stream()
        .filter(e -> e.getName().equals(name)).findFirst();

    if (enemyTemplate.isPresent()) {
      enemy.setName(enemyTemplate.get().getName());
      enemy.setEnemyType(enemyTemplate.get().getEnemyType());

      int EnemyHP = enemyTemplate.get().getHP() + rg.nextInt(enemyTemplate.get().getHP() / 5)
          - enemyTemplate.get().getHP() / 10;
      enemy.setMaxHP(EnemyHP);

      int EnemyAttack =
          enemyTemplate.get().getAttack() + rg.nextInt(enemyTemplate.get().getAttack() / 5)
              - enemyTemplate.get().getAttack() / 10;
      enemy.setAttack(EnemyAttack);
    }

    return enemy;
  }

  private Item newItem() {
    return ItemFactory.createItem();
  }

  //correct
  public void goNorth() {
    if (this.position <= dimensionX) {
      System.out.println("Can't across the border of this place");
    } else {
      System.out.println("You are going north");
      position -= dimensionX;
    }
  }


  public void goSouth() {
    if (this.position > dimensionX * (dimensionY - 1)) {
      System.out.println("Can't across the border of this place");
    } else {
      System.out.println("You are going south");
      position += dimensionX;
    }
  }

  public void goEast() {
    if ((this.position) % dimensionX == 0) {
      System.out.println("Can't across the border of this place");
    } else {
      System.out.println("You are going east");
      position += 1;
    }
  }

  public void goWest() {
    if ((this.position) % dimensionX == 1) {
      System.out.println("Can't across the border of this place");
    } else {
      System.out.println("You are going west");
      position -= 1;
    }
  }
}
