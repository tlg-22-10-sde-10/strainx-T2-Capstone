package contents;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Enemy {

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String enemyType;

    private int HP;

    @JsonProperty("maxHP")
    private int maxHP;

    @JsonProperty("attack")
    private int attack;
    private String special_power;

    public Enemy(String name, String enemy_type, int HP,int attack,  String special_power) {
        this.name = name;
        this.enemyType = enemy_type;
        this.HP = HP;
        this.maxHP = HP;
        this.attack = attack;
        this.special_power = special_power;
    }

    public Enemy() {}

    public Enemy(String name, int maxHP, int attack, String type) {
        this.name = name;
        this.HP = maxHP;
        this.attack = attack;
        this.maxHP = maxHP;
        this.enemyType = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(String enemyType) {
        this.enemyType = enemyType;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.HP = maxHP;
        this.maxHP = maxHP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getSpecial_power() {
        return special_power;
    }

    public void setSpecial_power(String special_power) {
        this.special_power = special_power;
    }
}
