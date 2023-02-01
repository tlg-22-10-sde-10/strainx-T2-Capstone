package CombatEngine;

import Contents.Enemy;
import Team_Member.Crew_Member;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class engage {
    static ArrayList<Crew_Member> squad1 = new ArrayList<>();
    static ArrayList<Enemy> squad2 = new ArrayList<>();

    public static void gameEnginePrototype(ArrayList<Crew_Member> squad1, ArrayList<Enemy> squad2) {
        //combat_environment.squad_initialization(squad1, squad2);

        Random rg = new Random();
        int round = 1;

        boolean flag = true;

        while (flag) {
            System.out.println("combat round: " + round);

            for(Crew_Member crewMember : squad1) {
                if (squad2.size() > 0) {
                    int target = rg.nextInt(squad2.size());
                    var damage = crewMember.attack + rg.nextInt(crewMember.attack);

                    var victim = squad2.get(target);

                    victim.setHP(victim.getHP()-damage);
                    if(victim.getHP() <= 0) {
                        squad2.remove(victim);
                    }
                }

                System.out.println(crewMember.rank + " " + crewMember.name + " HP:"+ crewMember.HP);
            }

            if(squad1.size()==0 || squad2.size()==0) {
                break;
            }
            System.out.println("VS");

            for(Enemy enemy : squad2) {
                if(squad1.size() > 0) {
                    var target = rg.nextInt(squad1.size());
                    var damage = enemy.getAttack() + rg.nextInt(enemy.getAttack());

                    var victim = squad1.get(target);

                    victim.HP -= damage;
                    if(victim.HP <= 0) {
                        squad1.remove(victim);
                        System.out.println("One of your teammates has been killed by zombie");
                    }

                }

                System.out.println(enemy.getEnemy_type() + " " +enemy.getName() + " " + " HP:"+ enemy.getHP());
            }

            System.out.println("press any key to continue.");
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
            round+=1;

            flag = squad1.size() > 0 && squad2.size()>0;
        }

        if (squad1.size() > 0) {
            System.out.println("\nYou defeated the enemies\n");
        } else {
            System.out.println("\nYour team is wiped out");
            System.out.println("Game Over\n");
        }
    }
}
