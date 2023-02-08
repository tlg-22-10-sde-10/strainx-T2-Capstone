package combatengine;

import contents.Enemy;
import teammember.CrewMember;
import org.junit.*;

import static client.GlobalVariables.*;
import static combatengine.UICombat.*;

class UI_combatTest {

    @Before
    public static void environmentInitialization() {
        CrewMember p1 = new CrewMember("Player", "SGT", "none", 100, 20);
        CrewMember p2 = new CrewMember("james", "PV2", "none", 100, 20);
        CrewMember p3 = new CrewMember("john", "PFC", "none", 100, 20);
        CrewMember p4 = new CrewMember("jill", "SPC", "none", 100, 20);

        mySquad.add(p1);
        mySquad.add(p2);
        mySquad.add(p3);
        mySquad.add(p4);

        var f2 = new Enemy("Scientist", "Zombie",1000, 40, "none");
        var f3 = new Enemy("Researcher", "Zombie",110, 10, "none");
        var f4 = new Enemy("Nurse", "Zombie",130, 10, "none");
        var f5 = new Enemy("Doctor", "Zombie",150, 10, "none");
        var f6 = new Enemy("Big Guy", "Zombie",200, 10, "none");
        var f1 = new Enemy("Soldier", "Zombie",120, 10, "none");
        //boss

        var f7 = new Enemy("Police Officer", "Zombie",140, 10, "none");
        var f8 = new Enemy("Dog", "Zombie",80, 20, "none");

        enemySquad.add(f2);
        enemySquad.add(f7);
        enemySquad.add(f1);
        enemySquad.add(f3);
        enemySquad.add(f4);
        enemySquad.add(f5);
        enemySquad.add(f6);
        enemySquad.add(f8);


        combatCommandDescription.put("1", "Attack Enemy");
        combatCommandDescription.put("2", "Use Items");
        combatCommandDescription.put("3", "Play Tricks");
        combatCommandDescription.put("4", "Auto Combat");
        combatCommandDescription.put("5", "Retreat");

        combatCommandCode.put("1", 1);
        combatCommandCode.put("2", 2);
        combatCommandCode.put("3", 3);
        combatCommandCode.put("4", 4);
        combatCommandCode.put("5", 5);
    }

    @Test
    public void reportCombatRoundsTest() {
        int round = 1;
        reportCombatRounds(round);
        //System.out.println("\033[34mColor Test\033[0m");
    }

    @Test
    public void reportEngageStatusTest() {
        environmentInitialization();

        int round = 1;
        reportCombatRounds(round);
        reportEngage();
        reportPlayerMove();
    }

    @Test
    public void reportPlayerMoveTest() {
        reportPlayerMove();
    }

    @Test
    public void reportCombatResultTest() {
        reportCombatResult(1);
    }
}