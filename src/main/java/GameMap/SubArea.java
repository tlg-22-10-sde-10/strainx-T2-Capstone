package GameMap;

import Contents.Enemy;

import java.util.ArrayList;
import java.util.Random;

public class SubArea {

    private ArrayList<String> subAreaNameList = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    private String name = "Laboratory";
    private Content contents = new Content();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Content getContents() {
        return contents;
    }

    public void setContents(Content contents) {
        this.contents = contents;
    }

    public void initialization() {
        sub_area_name_initialize();
        content_enemy_initialize();

        Random rg = new Random();

        this.name = subAreaNameList.get(rg.nextInt(subAreaNameList.size()))  ;

        this.contents = new Content();

        int enemySize = rg.nextInt(6)+1;
        for (int i = 0; i< enemySize; i++) {
            int enemyIndex = rg.nextInt(enemies.size());
            var randomEnemy = enemies.get(enemyIndex);

            String EnemyName = randomEnemy.getName();
            String EnemyType = randomEnemy.getEnemy_type();
            int EnemyHP = randomEnemy.getHP();
            int EnemyAttack = randomEnemy.getAttack();
            String EnemySpecialPower = randomEnemy.getSpecial_power();

            Enemy newEnemy = new Enemy(EnemyName, EnemyType, EnemyHP, EnemyAttack, EnemySpecialPower);

            this.contents.enemies.add(newEnemy);
        }
    }

    public void sub_area_name_initialize() {
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

    public void content_enemy_initialize() {
        var f1 = new Enemy("Soldier", "Zombie",120, 10, "none");
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
