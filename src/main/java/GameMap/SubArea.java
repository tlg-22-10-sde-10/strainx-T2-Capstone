package GameMap;

import Contents.Enemy;

import java.util.ArrayList;
import java.util.Random;

public class SubArea {
    private String name = "Laboratory";

    private String description = "";

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

    public void initialization(ArrayList<String> subAreaNameList, ArrayList<Enemy> enemies) {
        Random rg = new Random();

        this.name = subAreaNameList.get(rg.nextInt(subAreaNameList.size()));

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


}
