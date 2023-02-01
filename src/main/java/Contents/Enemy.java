package Contents;

public class Enemy {


    private String name;
    private String enemy_type;
    private int HP;
    private int maxHP;
    private int attack;
    private String special_power;

    public Enemy(String name, String enemy_type, int HP,int attack,  String special_power) {
        this.name = name;
        this.enemy_type = enemy_type;
        this.HP = HP;
        this.maxHP = HP;
        this.attack = attack;
        this.special_power = special_power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnemy_type() {
        return enemy_type;
    }

    public void setEnemy_type(String enemy_type) {
        this.enemy_type = enemy_type;
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
