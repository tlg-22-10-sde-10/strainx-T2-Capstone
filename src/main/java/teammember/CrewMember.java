package teammember;

import com.fasterxml.jackson.annotation.JsonProperty;
import contents.Weapon;

public class CrewMember {

    @JsonProperty("name")
    public String name;

    @JsonProperty("rank")
    public String rank;

    public Weapon weapon = new Weapon();

    public int HP = 100;

    @JsonProperty("maxHP")
    private int maxHP;

    @JsonProperty("attack")
    public int attack;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
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

    public CrewMember() {}

    public CrewMember(String name, String rank, int HP, int attack) {
        this.name = name;
        this.rank=rank;
        this.HP = HP;
        this.maxHP = HP;
        this.attack = attack;
    }
}
