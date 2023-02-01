package Team_Member;

public class Crew_Member {
    public String name = "Joe";
    public String rank = "PVT";
    public String specialty = "Hero"; //true hero never die
    public int HP = 0;
    public int attack = 0;

    public Crew_Member(String name, String rank, String specialty, int HP, int attack) {
        this.name = name;
        this.rank=rank;
        this.specialty = specialty;
        this.HP = HP;
        this.attack = attack;
    }
}
