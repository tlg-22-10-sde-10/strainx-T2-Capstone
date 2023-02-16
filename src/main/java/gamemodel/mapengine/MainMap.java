package gamemodel.mapengine;

import static gamecontrol.GlobalVariables.DESTINATION;
import static gamecontrol.GlobalVariables.DROP_RATE_MAP;
import static gamecontrol.GlobalVariables.DROP_RATE_ROLL;
import static gamecontrol.GlobalVariables.ENEMY_SQUAD_SIZE_CAP;
import static gamecontrol.GlobalVariables.ITEM_QUANTITY_CAP;
import static gamecontrol.GlobalVariables.FINAL_BOSS;
import static gamecontrol.GlobalVariables.SUB_AREA_CAP;
import static gamecontrol.GlobalVariables.enemiesTemplateCollection;
import static gamecontrol.GlobalVariables.finalBoss;
import static gamecontrol.GlobalVariables.inGameMap;
import static gamecontrol.GlobalVariables.miniBoss;
import static gamecontrol.GlobalVariables.rg;
import static gamecontrol.GlobalVariables.subAreaTemplatesCollection;

import gamecontrol.GameDifficulty;
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

public class MainMap {

  public Map<Integer, List<SubArea>> gameMap = new HashMap<>();

  private static final Map<Integer, Item> customItemSpawnDistribution = new HashMap<>();

  private static final int DEFAULT_X = 3;
  private static final int DEFAULT_Y = 3;

  private int position = 1;

  private static int dimensionX;
  private static int dimensionY;

  private static int miniBossSpawnMap = 1;
  private static SubArea miniBossSubArea = new SubArea();

  private static int finalBossSpawnMap = 1;
  private static SubArea finalBossSubArea = new SubArea();

  private static final List<Item> mustSpawnItems = new ArrayList<>();

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
    dimensionY = DEFAULT_Y;
    dimensionX = DEFAULT_X;
  }

  public MainMap(GameDifficulty difficulty) {
    if (GameDifficulty.Easy.equals(difficulty)) {
      dimensionY = 3;
      dimensionX = 3;
    } else if (GameDifficulty.Medium.equals(difficulty)) {
      if (GlobalVariables.rg.nextBoolean()) {
        dimensionY = 3;
        dimensionX = 4;
      } else {
        dimensionY = 4;
        dimensionX = 3;
      }
    } else if (GameDifficulty.Hard.equals(difficulty)) {
      dimensionY = 4;
      dimensionX = 4;
    }
  }

  //test purpose;
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

  private void generateBoss() {
    finalBossSpawnMap = rg.nextInt(dimensionX * dimensionY) + 1;

    finalBossSubArea = inGameMap.spawnSubArea(DESTINATION);

    var contents = finalBossSubArea.getContents();
    contents.items.clear();

    //not necessary have enemies in content
    if (contents.enemies.size() > 0) {
      contents.enemies.set(0, FINAL_BOSS);
    } else {
      contents.enemies.add(FINAL_BOSS);
    }

    finalBossSubArea.setContents(contents);
  }

  private void generateMiniBoss() {
    do {
      miniBossSpawnMap = rg.nextInt(dimensionX * dimensionY) + 1;
    }
    while (miniBossSpawnMap == finalBossSpawnMap);

    SubArea miniBossRoom = inGameMap.spawnSubArea();

    var contents = miniBossRoom.getContents();

    KeyItem passWordNote = new KeyItem();
    passWordNote.setQty(1);
    passWordNote.setDescription("In case I can't remember! " + GlobalVariables.getPassWord());
    passWordNote.setName("An odd note");
    passWordNote.setRarity("unique");

    contents.items.add(passWordNote);

    if (contents.enemies.size() > 0) {
      contents.enemies.set(0, miniBoss);
    } else {
      contents.enemies.add(miniBoss);
    }

    miniBossRoom.setContents(contents);
    miniBossSubArea = miniBossRoom;
  }

  private void customSpawnItemInitialize() {
    var m249 = ItemFactory.createItem("m249");
    var ar15 = ItemFactory.createItem("ar-15");
    var kit = ItemFactory.createItem("squad equipment upgrades");
    var meds1 = ItemFactory.createItem("first aid kit");
    var meds2 = ItemFactory.createItem("first aid kit");
    var meds3 = ItemFactory.createItem("first aid kit");
    var armor = ItemFactory.createItem("body armor");

    mustSpawnItems.add(ar15);
    mustSpawnItems.add(kit);
    mustSpawnItems.add(meds1);
    mustSpawnItems.add(meds2);
    mustSpawnItems.add(meds3);
    mustSpawnItems.add(armor);
  }

  private void customSpawnItems() {
    customSpawnItemInitialize();

    int mapSize = dimensionX * dimensionY;

    while (mustSpawnItems.size() > 0) {
      int spawnMap = rg.nextInt(mapSize) + 1;
      if (spawnMap != finalBossSpawnMap) {
        if (!customItemSpawnDistribution.containsKey(spawnMap)) {
          customItemSpawnDistribution.put(spawnMap, mustSpawnItems.get(0));

          mustSpawnItems.remove(0);
        }
      }
    }
  }

  //can be optimized
  // Generate
  public void gameMapInitialize() {
    generateBoss();
    customSpawnItems();
    generateMiniBoss();

    for (int i = 1; i <= dimensionX * dimensionY; i++) {
      var mapBlock = newMapBlock();

      //add final boss
      if (i == finalBossSpawnMap) {
        var finalBossSpawnRoomNumber = rg.nextInt(mapBlock.size());
        //var finalBossSpawnRoom = mapBlock.get(finalBossSpawnRoomNumber);
        mapBlock.set(finalBossSpawnRoomNumber, finalBossSubArea);
      }

      //add miniBoss
      if (i == miniBossSpawnMap) {
        var miniBossSpawnRoomNumber = rg.nextInt(mapBlock.size());
        var miniBossSpawnRoom = mapBlock.get(miniBossSpawnRoomNumber);
        miniBossSpawnRoom.setContents(miniBossSubArea.getContents());
      }

      //add must spawn item
      if(customItemSpawnDistribution.containsKey(i)) {
        var customItemSpawnRoomNumber = rg.nextInt(mapBlock.size());
        var customItemSpawnRoom = mapBlock.get(customItemSpawnRoomNumber);

        customItemSpawnRoom.getContents().items.add(customItemSpawnDistribution.get(i));
        customItemSpawnDistribution.remove(i);
      }

      gameMap.put(i, mapBlock);
    }
  }

  private List<SubArea> newMapBlock() {
    List<SubArea> mapBlock = new ArrayList<>();

    int subAreaCounts = rg.nextInt(SUB_AREA_CAP) + 1;

    for (int j = 0; j < subAreaCounts; j++) {
      SubArea thisSubArea = spawnSubArea();

      mapBlock.add(thisSubArea);
    }

    return mapBlock;
  }

  private SubArea spawnSubArea() {
    SubArea thisSubArea = new SubArea();

    if (subAreaTemplatesCollection.size() > 0) {
      var subAreaTemplateName = subAreaTemplatesCollection
          .get(rg.nextInt(subAreaTemplatesCollection.size())).getName();

      thisSubArea = spawnSubArea(subAreaTemplateName);
    }

    return thisSubArea;
  }

  private SubArea spawnSubArea(String subAreaName) {
    SubArea thisSubArea = new SubArea();

    if (subAreaTemplatesCollection.size() > 0) {
      Optional<SubArea> selectedSubArea = subAreaTemplatesCollection.stream()
          .filter(s -> s.getName().equals(subAreaName)).findFirst();

      if (selectedSubArea.isPresent()) {
        var subAreaTemplate = selectedSubArea.get();
        subAreaTemplate.setReUseCap(subAreaTemplate.getReUseCap() - 1);

        thisSubArea.setName(subAreaTemplate.getName());
        thisSubArea.setDescription(subAreaTemplate.getDescription());
        thisSubArea.setContents(spawnContent());

        if (subAreaTemplate.getReUseCap() < 1) {
          subAreaTemplatesCollection.remove(subAreaTemplate);
        }
      }
    }
    return thisSubArea;
  }

  private Content spawnContent() {
    Content contents = new Content();

    contents.enemies = spawnEnemyList();

    if (!contents.enemies.contains(finalBoss)) {
      contents.items = spawnItemList();
    }

    return contents;
  }

  private List<Enemy> spawnEnemyList() {
    List<Enemy> enemies = new ArrayList<>();

    int enemySize = rg.nextInt(ENEMY_SQUAD_SIZE_CAP + 1);

    for (int i = 0; i < enemySize; i++) {
      enemies.add(newEnemy());
    }

    return enemies;
  }

  private List<Item> spawnItemList() {
    List<Item> items = new ArrayList<>();

    int itemSize = rg.nextInt(ITEM_QUANTITY_CAP);

    for (int i = 0; i < itemSize; i++) {
      Item newItem = newItem();

      int roll = rg.nextInt(DROP_RATE_ROLL + 1);

      if (DROP_RATE_MAP.get(newItem.getRarity()) < roll) {
        items.add(newItem);
      }
    }

    return items;
  }

  public Enemy newEnemy() {
    int enemyIndex = GlobalVariables.rg.nextInt(enemiesTemplateCollection.size());
    var enemyName = enemiesTemplateCollection.get(enemyIndex).getName();

    return newEnemy(enemyName);
  }

  public Enemy newEnemy(String name) {
    var enemy = new Enemy();

    var enemyTemplate = enemiesTemplateCollection.stream()
        .filter(e -> e.getName().equals(name)).findFirst();

    if (enemyTemplate.isPresent()) {
      enemy.setName(enemyTemplate.get().getName());
      enemy.setEnemyType(enemyTemplate.get().getEnemyType());

      int EnemyHP =
          enemyTemplate.get().getHP() + GlobalVariables.rg.nextInt(enemyTemplate.get().getHP() / 5)
              - enemyTemplate.get().getHP() / 10;
      enemy.setMaxHP(EnemyHP);

      int EnemyAttack =
          enemyTemplate.get().getAttack() + GlobalVariables.rg.nextInt(
              enemyTemplate.get().getAttack() / 5)
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