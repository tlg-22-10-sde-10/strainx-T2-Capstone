package Team_Member;

import Contents.Weapon;

public class Crew_Member {
    public String name = "Joe";
    public String rank = "PVT";

    public Weapon weapon = new Weapon();

    public String specialty = "Hero"; //true hero never die

    public int HP = 0;
    private int maxHP = 0;

    public int attack = 0;

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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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

    public Crew_Member(String name, String rank, String specialty, int HP, int attack) {
        this.name = name;
        this.rank=rank;
        this.specialty = specialty;
        this.HP = HP;
        this.maxHP = HP;
        this.attack = attack;
    }
}
