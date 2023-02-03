package CombatEngine;

import Client.GlobalVariables;
import Contents.Enemy;
import Team_Member.Crew_Member;

import java.util.ArrayList;

import static Client.GlobalVariables.*;

public class combat_environment {
    public static void squad_initialization(ArrayList<Crew_Member> squad1, ArrayList<Enemy> squad2) {
        Crew_Member p1 = new Crew_Member("joe", "PVT", "none", 100,   20);
        Crew_Member p2 = new Crew_Member("james", "PV2", "none", 100,  20);
        Crew_Member p3 = new Crew_Member("john", "PFC", "none", 100, 20);
        Crew_Member p4 = new Crew_Member("jill", "SPC", "none", 100,  20);

        squad1.add(p1);
        squad1.add(p2);
        squad1.add(p3);
        squad1.add(p4);

        var f1 = new Enemy("Soldier", "Zombie",100, 10, "none");
        var f2 = new Enemy("Scientist", "Zombie",100, 10, "none");
        var f3 = new Enemy("Researcher", "Zombie",100, 10, "none");
        var f4 = new Enemy("Nurse", "Zombie",100, 10, "none");

        squad2.add(f1);
        squad2.add(f2);
        squad2.add(f3);
        squad2.add(f4);
    }

    public static void global_squad_initialization() {
        Crew_Member p1 = new Crew_Member("Player", "SGT", "none", 100,   20);
        Crew_Member p2 = new Crew_Member("james", "PV2", "none", 100,  20);
        Crew_Member p3 = new Crew_Member("john", "PFC", "none", 100, 20);
        Crew_Member p4 = new Crew_Member("jill", "SPC", "none", 100,  20);

        mySquad.add(p1);
        mySquad.add(p2);
        mySquad.add(p3);
        mySquad.add(p4);

        var f1 = new Enemy("Soldier", "Zombie",100, 10, "none");
        var f2 = new Enemy("Scientist", "Zombie",100, 10, "none");
        var f3 = new Enemy("Researcher", "Zombie",100, 10, "none");
        var f4 = new Enemy("Nurse", "Zombie",100, 10, "none");

//        enemySquad.add(f1);
//        enemySquad.add(f2);
//        enemySquad.add(f3);
//        enemySquad.add(f4);
    }
}
