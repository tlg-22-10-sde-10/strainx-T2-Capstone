package teammember;

import contents.Weapon;

public class CrewMember {
    public String name;
    public String rank;

    public Weapon weapon = new Weapon();

    public String specialty;

    public int HP;
    private int maxHP;

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

    public CrewMember(String name, String rank, String specialty, int HP, int attack) {
        this.name = name;
        this.rank=rank;
        this.specialty = specialty;
        this.HP = HP;
        this.maxHP = HP;
        this.attack = attack;
    }
}
