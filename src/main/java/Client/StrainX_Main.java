package Client;

import CombatEngine.combat_environment;
import CombatEngine.engage;
import Contents.Enemy;
import Team_Member.Crew_Member;
import UI.UI_main_screen;

import java.util.ArrayList;
import java.util.Scanner;

import static UI.UI_mission_accomplish.*;


public class StrainX_Main {

    public static void main(String[] args) {

        GlobalVariables.gameInitialization();
        Scanner userInput = new Scanner(System.in);

        //UI_main_screen.load_main_screen();

        var map = GlobalVariables.gameMap.getGame_Map();

        ArrayList<Crew_Member> mySquad = new ArrayList<>();
        ArrayList<Enemy> enemySquad = new ArrayList<>();

        combat_environment.squad_initialization(mySquad, enemySquad);


        while (true) {
            System.out.println("------------------");
            System.out.println("Keep going!");

            int position = GlobalVariables.gameMap.getPosition();

            System.out.println("You are now in Area" + position);
            System.out.println("There are several places you can explore:\n");

            for (int i = 0; i< map[position].size(); i++) {
                System.out.println((i+1)+". "+ map[position].get(i).getName());
            }

            System.out.println("\nselect what you trying to do:");
            System.out.println("press 0 to exit game.\npress number to explore area\npress 6789 to go NSWE\n");
            var choice = userInput.nextInt();

            if (choice == 0) {
                break;
            }
            if (choice == 6) {
                GlobalVariables.gameMap.go_North();
            }
            if (choice == 7) {
                GlobalVariables.gameMap.go_South();
            }
            if (choice == 8) {
                GlobalVariables.gameMap.go_West();
            }
            if (choice == 9) {
                GlobalVariables.gameMap.go_East();
            }

            if (choice >0 && choice < map[position].size()+1) {
                enemySquad = map[position].get(choice-1).getContents().enemies;

                System.out.println("You find a group of enemies");

                engage.gameEnginePrototype(mySquad, enemySquad);
                if(mySquad.size() == 0) {
                    break;
                }
            }
        }
        //UI.UI_game_over.load_game_over();


        //load_mission_accomplish();



        //System.out.println("\033[1;41mThis text is highlighted!\033[0m");


        //engage.gameEnginePrototype();

    }
}
